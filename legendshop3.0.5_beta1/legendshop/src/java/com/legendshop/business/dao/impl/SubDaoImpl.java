/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.legendshop.business.dao.BasketDao;
import com.legendshop.business.dao.ScoreDao;
import com.legendshop.business.dao.SubDao;
import com.legendshop.business.payment.alipay.AlipayService;
import com.legendshop.core.OperationTypeEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.event.impl.FireEvent;
import com.legendshop.event.EventHome;
import com.legendshop.model.entity.Basket;
import com.legendshop.model.entity.PayType;
import com.legendshop.model.entity.Product;
import com.legendshop.model.entity.Sub;
import com.legendshop.spi.constants.Constants;
import com.legendshop.spi.constants.OrderStatusEnum;
import com.legendshop.spi.constants.PayTypeEnum;
import com.legendshop.spi.constants.SubStatusEnum;
import com.legendshop.util.AppUtils;

/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * -------------------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ------------------------------------------------------------------------------------
 * 
 * 官方网站：http://www.legendesign.net
 */
public class SubDaoImpl extends SubCommonDaoImpl implements SubDao {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(SubDaoImpl.class);
	
	/** The score dao. */
	private ScoreDao scoreDao;
	
	/** The basket dao. */
	private BasketDao basketDao;

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.SubDao#saveSub(com.legendshop.model.entity.Sub)
	 */
	@Override
	public void saveSub(Sub sub) {
		saveOrUpdate(sub);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.SubDao#deleteSub(com.legendshop.model.entity.Sub)
	 */
	@Override
	public boolean deleteSub(Sub sub) {
		if (sub != null) {
			saveSubHistory(sub, SubStatusEnum.ORDER_DEL.value());
			delete(sub);
			basketDao.deleteBasketBySubNumber(sub.getSubNumber());
			EventHome.publishEvent(new FireEvent(sub, OperationTypeEnum.DELETE));
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.SubDao#adminChangeSubPrice(com.legendshop.model.entity.Sub, java.lang.String, java.lang.Double)
	 */
	@Override
	public boolean updateSubPrice(Sub sub, String userName, Double totalPrice) {
		if (sub != null) {
			saveSubHistory(sub, SubStatusEnum.PRICE_CHANGE.value());
			sub.setTotal(totalPrice);
			update(sub);
			EventHome.publishEvent(new FireEvent(sub, OperationTypeEnum.UPDATE_PRICE));
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.SubDao#getSubById(java.lang.Long)
	 */
	@Override
	public Sub getSubById(Long subId) {
		return get(Sub.class, subId);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.SubDao#findSubBySubNumber(java.lang.String)
	 */
	@Override
	public Sub getSubBySubNumber(String subNumber) {
		return findUniqueBy("from Sub where subNumber = ?", Sub.class, subNumber);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.SubDao#updateSub(java.lang.Long, java.lang.Integer, java.lang.String)
	 */
	@Override
	public boolean updateSub(Sub sub, Integer status, String userName) {
		if (sub != null) {
			saveSubHistory(sub, SubStatusEnum.CHANGE_STATUS.value());// 保存历史
			// 交货接口
			if (status.equals(OrderStatusEnum.CONSIGNMENT.value())) {
				try {
					String result = alipaySendGoods(sub);
					log.debug("alipaySendGoods result = {}", result);
				} catch (Exception e) {
					log.warn("alipaySendGoods fail", e);
				}

			}
			if (status.equals(OrderStatusEnum.SUCCESS.value()) || status.equals(OrderStatusEnum.CLOSE.value())) {// 关闭订单或者完成订单
				sub.setSubCheck(Constants.TRUE_INDICATOR);
			}
			if (status.equals(OrderStatusEnum.SUCCESS.value())) {
				// 订单结束，给用户增加积分
				scoreDao.saveScore(sub);
			}
			// TODO Stocks & ActualStocks 更新商品订购数量和库存
			List<Basket> baskets = getBasketBySubNumber(sub.getSubNumber());
			if (AppUtils.isNotBlank(baskets)) {
				for (Basket basket : baskets) {
					Integer basketCount = basket.getBasketCount();
					Product product = getByLockMode(Product.class, basket.getProdId(), LockMode.UPGRADE_NOWAIT);// 乐观锁
					Integer actualStocks = product.getActualStocks() == null ? 0 : product.getActualStocks();
					Integer stocks = product.getStocks() == null ? 0 : product.getStocks();
					if (status.equals(OrderStatusEnum.SUCCESS.value())) {
						Integer amount = stocks - basketCount; // 订单成功,才开始减去显示的库存
						if (amount < actualStocks) { // 以实际库存为准
							amount = actualStocks;
						}
						product.setStocks(amount);
						product.setBuys(product.getBuys() + basketCount);
					} else if (status.equals(OrderStatusEnum.REFUNDMENT.value())) {
						// 退货,此时只是hold住actualStocks, stocks还没有减去，应该加上actualStocks
						product.setActualStocks(actualStocks + basketCount);
					}
					update(product);
				}
			}

			sub.setStatus(status);
			sub.setUpdateDate(new Date());
			log.info("userName {} update sub with status {}", userName, status);
			update(sub);
			return true;
		}
		return false;
	}

	/**
	 * 支付宝发货接口.
	 * 
	 * @param sub
	 *            the sub
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	private String alipaySendGoods(Sub sub) throws Exception {
		// 构造函数，生成请求URL
		if (sub != null) {
			PayType payType = get(PayType.class, sub.getPayId());
			if (PayTypeEnum.isAlipay(payType)) {
				Map<String, String> sParaTemp = new HashMap<String, String>();
				// 发货时的运输类型
				String transport_type = "EXPRESS";
				sParaTemp.put("trade_no", sub.getTradeNo());
				sParaTemp.put("logistics_name", "Legendshop");
				sParaTemp.put("invoice_no", sub.getSubNumber());
				sParaTemp.put("transport_type", transport_type);
				sParaTemp.put("partner", payType.getPartner());
				String sHtmlText = AlipayService.send_goods_confirm_by_platform(sParaTemp, payType.getValidateKey());
				log.debug("alipaySendGoods sHtmlText = {}", sHtmlText);
				return sHtmlText;
			}

		}

		return "";
	}

	// basket_check = Y 表示已经下单了，形成了一条订单
	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.SubDao#getBasketBySubNumber(java.lang.String)
	 */
	@Override
	public List<Basket> getBasketBySubNumber(String subNumber) {
		return findByHQL("from Basket where subNumber=? and basket_check=? order by basketDate desc", subNumber,
				Constants.TRUE_INDICATOR);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.SubDao#finishUnPay(int, java.util.Date)
	 */
	@Override
	public List<Sub> getFinishUnPay(int maxNum, Date expireDate) {
		return findByHQLLimit("from Sub where subDate < ? and status = ?", 0, maxNum, expireDate, OrderStatusEnum.UNPAY
				.value());
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.SubDao#findUnAcklodgeSub(int, java.util.Date)
	 */
	@Override
	public List<Sub> getUnAcklodgeSub(int maxNum, Date expireDate) {
		return findByHQLLimit("from Sub where updateDate < ? and status = ?", 0, maxNum, expireDate,
				OrderStatusEnum.CONSIGNMENT.value());
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.SubDao#removeOverTimeBasket(java.util.Date)
	 */
	@Override
	public void deleteOverTimeBasket(Date date) {
		Integer result = exeByHQL("delete from Basket where basketCheck != ? and basketDate < ?",
				Constants.TRUE_INDICATOR, date);
		log.debug("removeOverTimeBasket, date = {}, result = {}", date, result);
	}

	/**
	 * Sets the score dao.
	 * 
	 * @param scoreDao
	 *            the new score dao
	 */
	@Required
	public void setScoreDao(ScoreDao scoreDaoImpl) {
		this.scoreDao = scoreDaoImpl;
	}

	/**
	 * Sets the basket dao.
	 * 
	 * @param basketDao
	 *            the new basket dao
	 */
	@Required
	public void setBasketDao(BasketDao basketDaoImpl) {
		this.basketDao = basketDaoImpl;
	}

	@Override
	public void updateSub(Sub sub) {
		update(sub);
	}
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.OrderDao#findOrder(com.legendshop.core.dao.support.CriteriaQuery)
	 */
	@Override
	public PageSupport getOrder(CriteriaQuery cq) {
		return find(cq);
	}

	/**
	 * 所有正在处理的订单
	 */
	@Override
	public Long getTotalProcessingOrder(String userName) {
		return findUniqueBy("select count(*) from Sub where subCheck = ? and userName = ?", Long.class,
				Constants.FALSE_INDICATOR, userName);
	}
	
	// 判断用户是否已经订购产品，条件:订单状态为Y
	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.BusinessDao#isUserOrderProduct(java.lang.Long, java.lang.String)
	 */
	@Override
	public boolean isUserOrderProduct(Long prodId, String userName) {
		String sql = "select count(*) from Basket b, Sub s where s.subNumber = b.subNumber and s.subCheck = ? and b.prodId = ? and b.userName = ?";
		Long result = findUniqueBy(sql, Long.class, Constants.TRUE_INDICATOR, prodId, userName);
		return result > 0;
	}

}
