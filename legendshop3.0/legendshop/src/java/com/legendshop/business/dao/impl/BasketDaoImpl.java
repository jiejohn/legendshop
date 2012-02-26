/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.legendshop.business.common.Constants;
import com.legendshop.business.dao.BasketDao;
import com.legendshop.business.dao.ProductDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.exception.BusinessException;
import com.legendshop.core.exception.ErrorCodes;
import com.legendshop.model.entity.Basket;
import com.legendshop.model.entity.Product;
import com.legendshop.util.AppUtils;

/**
 * 购物车Dao.
 */
public class BasketDaoImpl extends BaseDaoImpl implements BasketDao {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(BasketDaoImpl.class);
	
	/** The product dao. */
	private ProductDao productDao;

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.BasketDao#deleteBasketById(java.lang.Long)
	 */
	@Override
	public void deleteBasketById(Long basketId) {
		log.debug("deleteBasketById, basketId = {}", basketId);
		exeByHQL("delete from Basket where basketId=?", basketId);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.BasketDao#getBasketByuserName(java.lang.String)
	 */
	@Override
	public List<Basket> getBasketByuserName(String userName) {
		log.debug("getBasketByuserName,userName = {}", userName);
		return findByHQL("from Basket where userName=? and basket_check=? order by basketDate desc", userName,
				Constants.FALSE_INDICATOR);

	}

	// 得到有效订单总数
	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.BasketDao#getTotalBasketByuserName(java.lang.String)
	 */
	@Override
	public Long getTotalBasketByuserName(String userName) {
		return findUniqueBy("select count(*) from Basket where userName=? and basket_check=? order by basketDate desc",
				Long.class, userName, Constants.FALSE_INDICATOR);
	}

	// group by shopName
	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.BasketDao#getBasketByuserNameGroupByShopName(java.lang.String)
	 */
	@Override
	public Map<String, List<Basket>> getBasketByuserNameGroupByShopName(String userName) {
		List<Basket> list = findByHQL("from Basket where userName=? and basket_check=? order by basketDate desc",
				userName, Constants.FALSE_INDICATOR);
		if (AppUtils.isBlank(list)) {
			return null;
		} else {
			Map<String, List<Basket>> map = new HashMap<String, List<Basket>>();
			for (Basket basket : list) {
				List<Basket> baskets = map.get(basket.getShopName());
				if (baskets == null) {
					baskets = new ArrayList<Basket>();
				}
				baskets.add(basket);
				map.put(basket.getShopName(), baskets);
			}
			return map;
		}

	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.BasketDao#getBasketById(java.lang.Long)
	 */
	@Override
	public Basket getBasketById(Long id) {
		return get(Basket.class, id);

	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.BasketDao#getBasketByIdName(java.lang.Long, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Basket getBasketByIdName(Long prodId, String userName, String shopName, String attribute) {
		return findUniqueBy(
				"from Basket where prodId=? and userName=? and basket_check=? and shopName=? and attribute = ?",
				Basket.class, prodId, userName, Constants.FALSE_INDICATOR, shopName, attribute);
	}

	// 用户在shopName的订购数
	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.BasketDao#getBasketByUserName(java.lang.String, java.lang.String)
	 */
	@Override
	public Long getBasketByUserName(String userName, String shopName) {
		return findUniqueBy("select count(*) from Basket where userName=? and basket_check=? and shopName=?",
				Long.class, userName, Constants.FALSE_INDICATOR, shopName);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.BasketDao#saveBasket(com.legendshop.model.entity.Basket)
	 */
	@Override
	public Long saveBasket(Basket basket) {
		return (Long) save(basket);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.BasketDao#updateBasket(com.legendshop.model.entity.Basket)
	 */
	@Override
	public void updateBasket(Basket basket) {
		update(basket);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.BasketDao#holdStocks(java.lang.Long, java.lang.Integer)
	 */
	// 下订单时，增加商品购买数,TODO 应该在订单成功时进行
	@Override
	public void saveStocks(Long prodId, Integer basketCount) {
		Product product = getByLockMode(Product.class, prodId, LockMode.UPGRADE_NOWAIT);
		if (product != null) {
			Integer actualStocks = product.getActualStocks();
			if (actualStocks == null || actualStocks == 0) {
				actualStocks = product.getStocks();
			}
			if (actualStocks == null) {
				actualStocks = 0;
			}
			if (actualStocks - basketCount < 0) {
				throw new BusinessException("not enough stocks", ErrorCodes.NOT_ENOUGH_STOCKS);
			}
			product.setActualStocks(actualStocks - basketCount);
			update(product);
		}
	}


	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.BasketDao#getBasket(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Basket> getBasket(String prodId, String userName) {
		return findByHQL("from Basket where prodId = ? and userName = ? and basketCheck=?", prodId, userName,
				Constants.FALSE_INDICATOR);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.BasketDao#deleteBasketByUserName(java.lang.String)
	 */
	@Override
	public void deleteBasketByUserName(String userName) {
		List<Basket> list = findByHQL("from Basket where userName=? and basketCheck=?", new Object[] { userName,
				Constants.FALSE_INDICATOR });
		if (!AppUtils.isBlank(list)) {
			deleteAll(list);
		}
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.BasketDao#deleteBasketBySubNumber(java.lang.String)
	 */
	@Override
	public void deleteBasketBySubNumber(String subNumber) {
		List<Basket> list = findByHQL("from Basket where subNumber=?", subNumber);
		if (!AppUtils.isBlank(list)) {
			deleteAll(list);
		}
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.BasketDao#addtocart(java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.String, java.lang.String, java.lang.Double, java.lang.Double)
	 */
	@Override
	public boolean saveToCart(Long prodId, String pic, String userName, String shopName, Integer count,
			String attribute, String prodName, Double cash, Double carriage) {
		Basket basket = getBasketByIdName(prodId, userName, shopName, attribute);
		Product product = productDao.getProduct(prodId);
		if (product == null) {
			log.error("addtocart failed for prod does not exist prodId = {}", prodId);
			return false;
		}

		if (basket == null) {// 没有保存过
			// 检查库存
			if (product.getStocks() != null && product.getStocks() < count) {
				return false;
			}
			Basket b = new Basket();
			b.setProdId(prodId);
			b.setPic(pic);
			b.setUserName(userName);
			b.setBasketCount(count);
			b.setProdName(prodName);
			b.setCash(cash);
			b.setAttribute(attribute);
			b.setCarriage(carriage);
			b.setBasketDate(new Date());
			b.setBasketCheck(Constants.FALSE_INDICATOR);
			b.setShopName(shopName);
			saveBasket(b);
		} else {
			if (product.getStocks() != null && product.getStocks() < basket.getBasketCount() + count) {
				return false;
			} else {
				if (count > 0) {
					basket.setBasketCount(basket.getBasketCount() + count);
					updateBasket(basket);
				}
			}
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.BasketDao#setProductDao(com.legendshop.business.dao.impl.ProductDaoImpl)
	 */
	@Required
	public void setProductDao(ProductDao productDaoImpl) {
		this.productDao = productDaoImpl;
	}

}
