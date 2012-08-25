/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.business.common.page.FrontPage;
import com.legendshop.business.common.page.TilesPage;
import com.legendshop.business.form.BasketForm;
import com.legendshop.business.service.AdvertisementService;
import com.legendshop.business.service.BasketService;
import com.legendshop.business.service.UserDetailService;
import com.legendshop.business.service.timer.SubService;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.core.exception.PermissionException;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.model.entity.Basket;
import com.legendshop.model.entity.Sub;
import com.legendshop.model.entity.UserDetail;
import com.legendshop.spi.constants.Constants;
import com.legendshop.util.AppUtils;

@Controller
@RequestMapping("/p")
public class OrderController extends BaseController {
	
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(OrderController.class);
	
	/** The default value. */
	private final String defaultValue = "0";

	/** The advertisement service. */
	@Autowired
	private AdvertisementService advertisementService;
	
	/** The order service. */
	@Autowired
	private SubService subService;
	
	/** The basket service. */
	@Autowired
	private BasketService basketService;
	
	/** The user detail service. */
	@Autowired
	private UserDetailService userDetailService;
	
	/**
	 * Order.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param curPageNO
	 *            the cur page no
	 * @param entity
	 *            the entity
	 * @return the string
	 */
	@RequestMapping("/order")
	public String order(HttpServletRequest request, HttpServletResponse response, String curPageNO, Sub entity) {

	
		String userName = UserManager.getUsername(request);

		if (userName == null) {
			request.setAttribute(Constants.RETURN_URL, PropertiesUtil.getDomainName() + "/p/order");
			return PathResolver.getPath(request,response,TilesPage.NO_LOGIN);
		} 
		if(entity!=null && entity.getSubCheck() == null){
			entity.setSubCheck(Constants.FALSE_INDICATOR);
		}
		advertisementService.getAndSetOneAdvertisement(request,response,ThreadLocalContext.getCurrentShopName(request,response), Constants.USER_REG_ADV_950);
		String subNumber = entity.getSubNumber();
		if (AppUtils.isNotBlank(subNumber)) {
			subNumber = subNumber.trim();
		}
		log.debug("find order userName {}, subNumber {}",userName ,subNumber);
			// Qbc查找方式
			CriteriaQuery cq = new CriteriaQuery(Sub.class, curPageNO);
			cq.setPageSize(PropertiesUtil.getObject(ParameterEnum.FRONT_PAGE_SIZE, Integer.class));
			cq.eq("userName", userName);
			if (AppUtils.isNotBlank(subNumber)) {
				cq.like("subNumber", subNumber + "%");
			}

			cq.eq("status", entity.getStatus());
			cq.eq("subCheck", entity.getSubCheck());
			cq.addOrder("desc", "subDate");
			cq.add();
			PageSupport ps = subService.getOrderList(cq);
			savePage(ps, request);
			request.setAttribute("subForm", entity);


		return PathResolver.getPath(request,response,TilesPage.ORDER);
	}

	/**
	 * Update.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param basket
	 *            the basket
	 * @return the string
	 */
	@RequestMapping("/buy")
	public String update(HttpServletRequest request, HttpServletResponse response, BasketForm basket) {
		String userName = UserManager.getUsername(request.getSession());
		if ("buy".equals(basket.getAction())) {
			String shopName = ThreadLocalContext.getCurrentShopName(request,response);
			Integer count = basket.getCount();
			if (count == null) {
				count = 1;
			}
			basketService.saveToCart(basket.getProdId(), basket.getPic(), userName, shopName, basket.getCount(), basket
					.getAttribute() == null ? "" : basket.getAttribute(), basket.getProdName(), basket.getCash(), basket.getCarriage());
			request.getSession().setAttribute(Constants.BASKET_HW_COUNT, count);
		}
		advertisementService.getAndSetOneAdvertisement(request,response,ThreadLocalContext.getCurrentShopName(request,response), Constants.USER_REG_ADV_950);
		return PathResolver.getPath(request,response,TilesPage.BUY);
	}
	
	
	/**
	 * Clear.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping("/clear")
	public String clear(HttpServletRequest request, HttpServletResponse response) {
		String userName = UserManager.getUsername(request);
		if (AppUtils.isBlank(userName)) {
			return PathResolver.getPath(request,response,TilesPage.NO_LOGIN);
		}
		String basketId = request.getParameter("basketId");
		if (basketId == null) {
			basketService.deleteBasketByUserName(userName);
		} else {
			try {
				Long id = Long.valueOf(basketId);
				basketService.deleteBasketById(id);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return PathResolver.getPath(request,response,TilesPage.BUY);
	}
	
	/**
	 * Bought.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping("/bought")
	public String bought(HttpServletRequest request, HttpServletResponse response) {
		List<Basket> baskets = basketService.getBasketByuserName(UserManager.getUsername(request));
		if (!AppUtils.isBlank(baskets)) {
			Double totalcash = CommonServiceUtil.calculateTotalCash(baskets);
			request.setAttribute("baskets", baskets);
			request.setAttribute("totalcash", totalcash);
		}

		return PathResolver.getPath(request,response,FrontPage.BOUGHT);
	}
	
	/**
	 * 准备保存订单.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping("/cashsave")
	public String cashsave(HttpServletRequest request, HttpServletResponse response) {
		String total = request.getParameter("total");
		if (total != null) {
			request.setAttribute("total", total);
		} else {
			total = defaultValue;// 没有找到总的cash
		}
		String userName = UserManager.getUsername(request);
		if (AppUtils.isBlank(userName)) {
			return PathResolver.getPath(request,response,TilesPage.NO_LOGIN);
		}

		UserDetail member = userDetailService.getUserDetail(userName);
		if (!AppUtils.isBlank(member)) {
			request.setAttribute("member", member);
		}
		setSessionAttribute(request, Constants.SHOP_NAME, ThreadLocalContext.getCurrentShopName(request,response));
		return PathResolver.getPath(request,response,FrontPage.CASH_SAVE);
		
	}
	
	/**
	 * Order detail.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param subNumber
	 *            the sub number
	 * @return the string
	 */
	@RequestMapping("/orderDetail/{subNumber}")
	public String orderDetail(HttpServletRequest request, HttpServletResponse response ,@PathVariable String subNumber) {
		String userName = UserManager.getUsername(request);
		if (AppUtils.isBlank(userName)) {
			return PathResolver.getPath(request,response,TilesPage.LOGIN);
		}
	
		Sub sub = subService.getSubBySubNumber(subNumber);
		if(sub == null){
			throw new NotFoundException("sub not found with userName: " + userName,EntityCodes.SUB);
		}
		
		if (!userName.equals(sub.getUserName()) && !userName.equals(sub.getShopName())) {
			if (!CommonServiceUtil.haveViewAllDataFunction(request)) {
				throw new PermissionException("can not modify others order detail!",EntityCodes.SUB);
			}
		}
		return subService.getOrderDetail(request,response, sub,userName,subNumber);
	}	
	
	/**
	 * Cash.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping("/cash")
	public String cash(HttpServletRequest request, HttpServletResponse response) {
		// 加入token
		//request.getSession().setAttribute(Constants.TOKEN, CommonServiceUtil.generateRandom()); // 生成随机数并保存到token
		return PathResolver.getPath(request,response,TilesPage.PAGE_CASH);
	}
	

}
