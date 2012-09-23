/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.common;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.legendshop.core.constant.LuceneIndexerEnum;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.helper.FunctionUtil;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.security.AuthenticationFailureHandlerImpl;
import com.legendshop.model.entity.Basket;
import com.legendshop.model.entity.Product;
import com.legendshop.model.entity.ShopDetail;
import com.legendshop.search.SearchEntity;
import com.legendshop.util.AppUtils;
import com.legendshop.util.Arith;
import com.legendshop.util.TimerUtil;
import com.legendshop.util.des.DES2;

/**
 * 公用服务类.
 */
public class CommonServiceUtil extends FunctionUtil {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(CommonServiceUtil.class);

	/**
	 * 得到订单随机数.
	 * 
	 * @param userName
	 *            the user name
	 * @return the sub nember
	 */
	public synchronized static String getSubNember(String userName) {
		String subNumber = "";
		String now = TimerUtil.dateToStr(new Date());
		subNumber = now;
		subNumber = subNumber.replace("-", "");
		subNumber = subNumber.replace(" ", "");
		subNumber = subNumber.replace(":", "");
		Random r = new Random();
		subNumber = subNumber + randomNumeric(r, 8);
		return subNumber;
	}

	/**
	 * Random numeric.
	 * 
	 * @param random
	 *            the random
	 * @param count
	 *            the count
	 * @return the string
	 */
	private static String randomNumeric(Random random, int count) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < count; i++) {
			int val = random.nextInt(10);
			sb.append(String.valueOf(val));
		}
		return sb.toString();
	}

	/**
	 * Random.
	 * 
	 * @param count
	 *            the count
	 * @return the int
	 */
	public static int random(int count) {
		Random random = new Random();
		return random.nextInt(count);
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		long t1 = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			int re = random(2);
			System.out.println(re);
		}
		long t2 = System.currentTimeMillis();
		System.out.println("t2 - t1 = " + (t2 - t1));

	}

	/**
	 * Creates the search entity.
	 * 
	 * @param obj
	 *            the obj
	 * @return the search entity
	 */
	public static SearchEntity createSearchEntity(Object obj) {
		SearchEntity entity = null;
		if (obj instanceof Product) {
			Product product = (Product) obj;
			entity = new SearchEntity();
			entity.setContents(new StringBuffer().append(product.getName()).append(" ").append(product.getContent())
					.toString());
			entity.setDate(product.getRecDate());
			entity.setEntityType(LuceneIndexerEnum.SEARCH_ENTITY_PROD);
			entity.setProdId(product.getProdId());
			entity.setPrice(product.getCash());
			entity.setUserId(product.getUserId());
		} else if (obj instanceof ShopDetail) {
			ShopDetail sd = (ShopDetail) obj;
			entity = new SearchEntity();
			entity.setShopId(sd.getShopId());
			entity.setContents(new StringBuffer().append(sd.getUserName()).append(" ").append(sd.getSiteName())
					.append(" ").append(sd.getBriefDesc()).append(" ").append(sd.getDetailDesc()).toString());
			entity.setDate(sd.getAddtime());
			entity.setEntityType(LuceneIndexerEnum.SEARCH_ENTITY_SHOPDETAIL);
			entity.setUserId(sd.getUserId());
			entity.setProvinceid(String.valueOf(sd.getProvinceid()));
			entity.setCityid(String.valueOf(sd.getCityid()));
			entity.setAreaid(String.valueOf(sd.getAreaid()));
		}
		return entity;
	}

	/**
	 * Calculate total cash.
	 * 
	 * @param baskets
	 *            the baskets
	 * @return the double
	 */
	public static Double calculateTotalCash(List<Basket> baskets) {
		if (AppUtils.isNotBlank(baskets)) {
			Double totalcash = 0d;
			for (Basket bo : baskets) {
				try {
					double total = Arith.mul(bo.getBasketCount() == null ? 1d : bo.getBasketCount(), bo.getCash());
					if (bo.getCarriage() != null) {
						total = Arith.add(total, bo.getCarriage());
					}
					bo.setTotal(total);
					totalcash = Arith.add(totalcash, bo.getTotal());
				} catch (Exception e) {
					log.error("calculateTotalCash ", e);
				}
			}
			return totalcash;
		} else {
			return null;
		}
	}

	/**
	 * Calculate total cash.
	 * 
	 * @param basketMap
	 *            the basket map
	 * @return the double
	 */
	public static Double calculateTotalCash(Map<String, Basket> basketMap) {
		Double totalcash = 0d;
		for (Basket basket : basketMap.values()) {
			try {
				double total = Arith.mul(basket.getBasketCount() == null ? 1d : basket.getBasketCount(), basket
						.getCash());
				if (basket.getCarriage() != null) {
					total = Arith.add(total, basket.getCarriage());
				}
				basket.setTotal(total);
				totalcash = Arith.add(totalcash, basket.getTotal());
			} catch (Exception e) {
				log.error("convert count", e);
			}
		}
		return totalcash;
	}

	/**
	 * Generate random.
	 * 
	 * @return the integer
	 */
	public static Integer generateRandom() {
		Random r = new Random();
		return new Integer(r.nextInt(100000));
	}

	/**
	 * Gets the dES.
	 * 
	 * @return the dES
	 */
	public static DES2 getDES() {
		return new DES2("!23done!");
	}
	
	//是否需要验证码
	public static boolean needToValidation(HttpSession session) {
		if(session == null){
			return false;
		}
		Integer count = (Integer)session.getAttribute(AuthenticationFailureHandlerImpl.TRY_LOGIN_COUNT);
		
		Boolean needToValidation = true;
		if(count == null || count <= 3){
			needToValidation  = false;
		}
		Boolean result = PropertiesUtil.getBooleanObject(ParameterEnum.VALIDATION_IMAGE.name());
		return (result!= null && result) && needToValidation;
	}

}
