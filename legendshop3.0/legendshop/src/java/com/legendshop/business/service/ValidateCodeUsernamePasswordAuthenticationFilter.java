/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.TextEscapeUtils;

import com.legendshop.business.common.Constants;
import com.legendshop.business.dao.BasketDao;
import com.legendshop.model.entity.Basket;

/**
 * 带验证码校验功能的用户名、密码认证过滤器
 * 
 * 支持不输入验证码；支持验证码忽略大小写。
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * 官方网站：http://www.legendesign.net
 */
public class ValidateCodeUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	/** The log. */
	Logger log = LoggerFactory.getLogger(ValidateCodeUsernamePasswordAuthenticationFilter.class);
	
	/** The post only. */
	private boolean postOnly = true;
	
	/** The login history service. */
	private LoginHistoryService loginHistoryServiceImpl;
	
	/** The basket dao. */
	private BasketDao basketDaoImpl;
	
	/** The allow empty validate code. */
	private boolean allowEmptyValidateCode = false;
	
	/** The sessionvalidate code field. */
	private String sessionvalidateCodeField = DEFAULT_SESSION_VALIDATE_CODE_FIELD;
	
	/** The Constant DEFAULT_SESSION_VALIDATE_CODE_FIELD. */
	public static final String DEFAULT_SESSION_VALIDATE_CODE_FIELD = "randNum";
	
	/** The support sso. */
	private boolean supportSSO = false;

	/**
	 * Sets the support sso.
	 * 
	 * @param supportSSO
	 *            the new support sso
	 */
	public void setSupportSSO(boolean supportSSO) {
		this.supportSSO = supportSSO;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter#attemptAuthentication(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}

		String username = obtainUsername(request);
		String password = obtainPassword(request);

		if (username == null) {
			username = "";
		}

		if (password == null) {
			password = "";
		}

		username = username.trim();

		// String pass= MD5Util.Md5Password(username, password);

		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

		// Place the last username attempted into HttpSession for views
		HttpSession session = request.getSession(false);

		if (session != null || getAllowSessionCreation()) {
			request.getSession().setAttribute(SPRING_SECURITY_LAST_USERNAME_KEY,
					TextEscapeUtils.escapeEntities(username));
		}

		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);
		// check validate code
		if (!isAllowEmptyValidateCode()) {
			checkValidateCode(request);
		}

		Authentication auth = this.getAuthenticationManager().authenticate(authRequest);
		if (auth != null) {
			// 处理登录历史
			loginHistoryServiceImpl.saveLoginHistory(username, request.getRemoteAddr());
			// 增加session登录信息
			request.getSession().setAttribute(Constants.USER_NAME, username);

			// 处理在session中的购物车
			Map<String, Basket> basketMap = (Map<String, Basket>) request.getSession().getAttribute(
					Constants.BASKET_KEY);
			if (basketMap != null) {
				// 保存进去数据库
				for (Basket basket : basketMap.values()) {
					basketDaoImpl.saveToCart(basket.getProdId(), basket.getPic(), username, basket.getShopName(), basket
							.getBasketCount(), basket.getAttribute(), basket.getProdName(), basket.getCash(), basket
							.getCarriage());
				}
				request.getSession().setAttribute(Constants.BASKET_KEY, null);
			}

			if (supportSSO) {
				// 增加BBS的登录用户 SSO to club
				Cookie cookie = new Cookie(Constants.CLUB_COOIKES_NAME, username);
				cookie.setPath("/");
				cookie.setMaxAge(-1); // -1为永不过期,或者指定过期时间
				response.addCookie(cookie);// 在退出登录操作中删除cookie.
			}
		}
		return auth;
	}

	/**
	 * <li>比较session中的验证码和用户输入的验证码是否相等</li>.
	 * 
	 * @param request
	 *            the request
	 */
	protected void checkValidateCode(HttpServletRequest request) {
		String sessionValidateCode = obtainSessionValidateCode(request);
		String validateCodeParameter = obtainValidateCodeParameter(request);
		if (StringUtils.isEmpty(validateCodeParameter) || !sessionValidateCode.equalsIgnoreCase(validateCodeParameter)) {
			log.error("输入的验证码 {}不匹配 {}", validateCodeParameter, sessionValidateCode);
			throw new AuthenticationServiceException("验证码失败");
		}
	}

	/**
	 * Obtain validate code parameter.
	 * 
	 * @param request
	 *            the request
	 * @return the string
	 */
	private String obtainValidateCodeParameter(HttpServletRequest request) {
		return request.getParameter(DEFAULT_SESSION_VALIDATE_CODE_FIELD);
	}

	/**
	 * Obtain session validate code.
	 * 
	 * @param request
	 *            the request
	 * @return the string
	 */
	protected String obtainSessionValidateCode(HttpServletRequest request) {
		Object obj = request.getSession().getAttribute(sessionvalidateCodeField);
		return null == obj ? "" : obj.toString();
	}

	/**
	 * Checks if is post only.
	 * 
	 * @return true, if is post only
	 */
	public boolean isPostOnly() {
		return postOnly;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter#setPostOnly(boolean)
	 */
	@Override
	public void setPostOnly(boolean postOnly) {
		this.postOnly = postOnly;
	}

	/**
	 * Gets the validate code name.
	 * 
	 * @return the validate code name
	 */
	public String getValidateCodeName() {
		return sessionvalidateCodeField;
	}

	/**
	 * Sets the validate code name.
	 * 
	 * @param validateCodeName
	 *            the new validate code name
	 */
	public void setValidateCodeName(String validateCodeName) {
		this.sessionvalidateCodeField = validateCodeName;
	}

	/**
	 * Checks if is allow empty validate code.
	 * 
	 * @return true, if is allow empty validate code
	 */
	public boolean isAllowEmptyValidateCode() {
		return allowEmptyValidateCode;
	}

	/**
	 * Sets the allow empty validate code.
	 * 
	 * @param allowEmptyValidateCode
	 *            the new allow empty validate code
	 */
	public void setAllowEmptyValidateCode(boolean allowEmptyValidateCode) {
		this.allowEmptyValidateCode = allowEmptyValidateCode;
	}

	/**
	 * Sets the login history service.
	 * 
	 * @param loginHistoryServiceImpl
	 *            the new login history service
	 */
	public void setLoginHistoryService(LoginHistoryService loginHistoryServiceImpl) {
		this.loginHistoryServiceImpl = loginHistoryServiceImpl;
	}

	/**
	 * Sets the basket dao.
	 * 
	 * @param basketDaoImpl
	 *            the new basket dao
	 */
	public void setBasketDao(BasketDao basketDaoImpl) {
		this.basketDaoImpl = basketDaoImpl;
	}

}