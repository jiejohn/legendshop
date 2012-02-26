/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.security.web.util.TextEscapeUtils;

import com.legendshop.business.common.Constants;
import com.legendshop.business.dao.BasketDao;
import com.legendshop.model.entity.Basket;

/**
 * The Class MyRememberMeAuthenticationFilter.
 */
public class MyRememberMeAuthenticationFilter extends RememberMeAuthenticationFilter {
	
	/** The basket dao. */
	private BasketDao basketDaoImpl;

	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter#onSuccessfulAuthentication(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
	 */
	@Override
	protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			Authentication authResult) {
		request.getSession().setAttribute(
				ValidateCodeUsernamePasswordAuthenticationFilter.SPRING_SECURITY_LAST_USERNAME_KEY,
				TextEscapeUtils.escapeEntities(authResult.getName()));

		// 处理在session中的购物车
		Map<String, Basket> basketMap = (Map<String, Basket>) request.getSession().getAttribute(Constants.BASKET_KEY);
		if (basketMap != null) {
			// 保存进去数据库
			for (Basket basket : basketMap.values()) {
				basketDaoImpl.saveToCart(basket.getProdId(), basket.getPic(), authResult.getName(), basket.getShopName(),
						basket.getBasketCount(), basket.getAttribute(), basket.getProdName(), basket.getCash(), basket
								.getCarriage());
			}
			request.getSession().setAttribute(Constants.BASKET_KEY, null);
		}
		//request.getSession().setAttribute("userName", authResult.getName());

	}

	/**
	 * Sets the basket dao.
	 * 
	 * @param basketDaoImpl
	 *            the new basket dao
	 */
	@Required
	public void setBasketDao(BasketDao basketDaoImpl) {
		this.basketDaoImpl = basketDaoImpl;
	}

}
