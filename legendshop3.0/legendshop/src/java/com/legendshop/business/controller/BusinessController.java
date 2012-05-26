/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.business.common.page.FowardPage;
import com.legendshop.business.common.page.FrontPage;
import com.legendshop.business.common.page.TilesPage;
import com.legendshop.business.form.SearchForm;
import com.legendshop.business.form.UserForm;
import com.legendshop.business.service.BusinessService;
import com.legendshop.business.service.LoginService;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.ConfigPropertiesEnum;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.exception.ErrorCodes;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.core.exception.PermissionException;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.UserMessages;
import com.legendshop.model.entity.ShopDetail;
import com.legendshop.model.entity.Sub;
import com.legendshop.spi.constants.Constants;
import com.legendshop.util.AppUtils;
import com.legendshop.util.FileConfig;

/**
 * 前台主要功能.
 */
@Controller
public class BusinessController extends BaseController {
	
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(BusinessController.class);

	/** The business service. */
	@Autowired
	private BusinessService businessService;
	
	@Autowired
	private  LoginService loginService;

	
	/**
	 * 前台首页.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping("/index")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		log.debug("Index starting calling");
		try {
			//test
//			EventHome.publishEvent(new UserRegEvent("testObject"));
//			EventHome.publishEvent(new SendMailEvent("mail content"));
			
			
			return businessService.getIndex(request, response);
		} catch (Exception e) {
			log.error("invoking index", e);
			if (!PropertiesUtil.isSystemInstalled()) {
				String version = PropertiesUtil.getProperties(FileConfig.GlobalFile,
						ConfigPropertiesEnum.LEGENDSHOP_VERSION.name());
				UserMessages uem = new UserMessages();
				uem.setTitle("系统还没有安装成功");
				uem.setDesc("System will be available until install operation is finished!");
				uem.setCode(ErrorCodes.SYSTEM_UNINSTALLED);
				uem.addCallBackList("安装系统", "LegendShop " + version, "install");
				request.setAttribute(UserMessages.MESSAGE_KEY, uem);
				return "redirect:install/index.jsp";
			}
			return PathResolver.getPath(request, FrontPage.FAIL);
		}
	}
	
	/**
	 * Topall.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping("/topall")
	public String topall(HttpServletRequest request, HttpServletResponse response) {
		return PathResolver.getPath(request, FrontPage.TOPALL);
	}
	
	/**
	 * Topsort.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping("/topsort")
	public String topsort(HttpServletRequest request, HttpServletResponse response) {
		return businessService.getTopSort(request, response);
	}
	
	/**
	 * Topnews.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping("/topnews")
	public String topnews(HttpServletRequest request, HttpServletResponse response) {
		return businessService.getTopnews(request, response);
	}
	
	/**
	 * Views.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param prodId
	 *            the prod id
	 * @return the string
	 */
	@RequestMapping("/views/{prodId}")
	public String views(HttpServletRequest request, HttpServletResponse response,@PathVariable Long prodId) {
		return businessService.getViews(request, response,prodId);
	}
	
	/**
	 * News.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param newsId
	 *            the news id
	 * @return the string
	 */
	@RequestMapping("/news/{newsId}")
	public String news(HttpServletRequest request, HttpServletResponse response,@PathVariable Long newsId) {
		return businessService.getNews(request, response,newsId);
	}
	
	/**
	 * Top.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping("/top")
	public String top(HttpServletRequest request, HttpServletResponse response) {
		return businessService.getTop(request, response);
	}
	
	/**
	 * Nsort.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param sortId
	 *            the sort id
	 * @param nsortId
	 *            the nsort id
	 * @return the string
	 */
	@RequestMapping("/nsort/{sortId}-{nsortId}")
	public String nsort(HttpServletRequest request, HttpServletResponse response,@PathVariable Long sortId,@PathVariable Long nsortId) {
		if (nsortId == null|| sortId == null) {
			log.error("sortId or nsortId is null! ");
			return PathResolver.getPath(request, FowardPage.INDEX_QUERY);
		}
		return businessService.getSecSort(request, response,sortId,nsortId,null);
	}
	
	/**
	 * Nsort.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param sortId
	 *            the sort id
	 * @param nsortId
	 *            the nsort id
	 * @param subNsortId
	 *            the sub nsort id
	 * @return the string
	 */
	@RequestMapping("/nsort")
	public String nsort(HttpServletRequest request, HttpServletResponse response,Long sortId, Long nsortId,Long subNsortId) {
		if (nsortId == null|| sortId == null) {
			log.error("sortId or nsortId is null! ");
			return PathResolver.getPath(request, FowardPage.INDEX_QUERY);
		}
		return businessService.getSecSort(request, response,sortId,nsortId,subNsortId);
	}
	
	/**
	 * Shopcontact.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping("/shopcontact")
	public String shopcontact(HttpServletRequest request, HttpServletResponse response) {
		return businessService.getShopcontact(request, response);
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
			return PathResolver.getPath(request, TilesPage.LOGIN);
		}
	
		Sub sub = businessService.getSubBySubNumber(subNumber);
		if(sub == null){
			throw new NotFoundException("sub not found with userName: " + userName,EntityCodes.SUB);
		}
		
		if (!userName.equals(sub.getUserName()) && !userName.equals(sub.getShopName())) {
			if (!CommonServiceUtil.haveViewAllDataFunction(request)) {
				throw new PermissionException("can not modify others order detail!",EntityCodes.SUB);
			}
		}
		return businessService.getOrderDetail(request, sub,userName,subNumber);
	}	
	
	/**
	 * All news.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param curPageNO
	 *            the cur page no
	 * @param newsCategory
	 *            the news category
	 * @return the string
	 */
	@RequestMapping("/allNews")
	public String allNews(HttpServletRequest request, HttpServletResponse response,String curPageNO,Long newsCategoryId) {
		return businessService.getAllNews(request, response,curPageNO,newsCategoryId);
	}
	
	/**
	 * Hotsale.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param curPageNO
	 *            the cur page no
	 * @param newsCategory
	 *            the news category
	 * @return the string
	 */
	@RequestMapping("/hotsale")
	public String hotsale(HttpServletRequest request, HttpServletResponse response,String curPageNO,String newsCategory) {
		return businessService.getHotSale(request, response);
	}
	
	/**
	 * Hoton.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param curPageNO
	 *            the cur page no
	 * @param newsCategory
	 *            the news category
	 * @return the string
	 */
	@RequestMapping("/hoton")
	public String hoton(HttpServletRequest request, HttpServletResponse response,String curPageNO,String newsCategory) {
		return businessService.getHotProduct(request, response);
	}

	/**
	 * Myaccount.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param curPageNO
	 *            the cur page no
	 * @param newsCategory
	 *            the news category
	 * @return the string
	 */
	@RequestMapping("/myaccount")
	public String myaccount(HttpServletRequest request, HttpServletResponse response,String curPageNO,String newsCategory) {
		return businessService.getMyAccount(request, response);
	}
	
	
	/**
	 * Ipsearch.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param ipAddress
	 *            the ip address
	 * @return the string
	 */
	@RequestMapping("/ipsearch")
	public String ipsearch(HttpServletRequest request, HttpServletResponse response,String ipAddress) {
		return businessService.getIpAddress(request, response,ipAddress);
	}
	
	/**
	 * Leaveword.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param ipAddress
	 *            the ip address
	 * @return the string
	 */
	@RequestMapping("/leaveword")
	public String leaveword(HttpServletRequest request, HttpServletResponse response,String ipAddress) {
		return PathResolver.getPath(request, TilesPage.LEAVEWORD);
	}
	
	/**
	 * Copy all.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param curPageNO
	 *            the cur page no
	 * @param newsCategory
	 *            the news category
	 * @return the string
	 */
	@RequestMapping("/copyAll")
	public String copyAll(HttpServletRequest request, HttpServletResponse response,String curPageNO,String newsCategory) {
		return businessService.getNewsforCommon(request, response);
	}
	
	/**
	 * Search.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param searchForm
	 *            the search form
	 * @return the string
	 */
	@RequestMapping("/search")
	public String search(HttpServletRequest request, HttpServletResponse response,SearchForm searchForm) {
		return businessService.search(request, response,searchForm);
	}
	
	/**
	 * Searchall.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param keyword
	 *            the keyword
	 * @param entityType
	 *            the entity type
	 * @return the string
	 */
	@RequestMapping("/searchall")
	public String searchall(HttpServletRequest request, HttpServletResponse response) {
		String keyword = request.getParameter("keyword");
		String entityType = request.getParameter("entityType");
		int type = 0;
		try {
			type =Integer.parseInt(entityType);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return businessService.searchall(request, response,keyword,type);
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
		return PathResolver.getPath(request, TilesPage.PAGE_CASH);
	}
	
	/**
	 * 用户注册动作
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param userForm
	 *            the user form
	 * @return the string
	 */
	@RequestMapping("/userReg")
	public String userReg(HttpServletRequest request, HttpServletResponse response,UserForm userForm) {
		String result =  businessService.saveUserReg(request, response,userForm);
		//用户注册即登录
		if(loginService!=null){
			Authentication authentication = loginService.onAuthentication(request, response, userForm.getName(), userForm.getPassword());
			SecurityContextHolder.getContext().setAuthentication(authentication);
			request.getSession().setAttribute(Constants.SPRING_SECURITY_CONTEXT, SecurityContextHolder.getContext());
		}
		return result;
	}
	
	/**
	 * Update account.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param userForm
	 *            the user form
	 * @return the string
	 */
	@RequestMapping("/updateAccount")
	public String updateAccount(HttpServletRequest request, HttpServletResponse response,UserForm userForm) {
		return businessService.updateAccount(request, response,userForm);
	}
	
	/**
	 * Login.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		return PathResolver.getPath(request, TilesPage.LOGIN);
	}
	
	/**
	 * 加载用户注册页面.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping("/reg")
	public String reg(HttpServletRequest request, HttpServletResponse response) {
		return businessService.saveUserReg(request, response);
	}
	
	/**
	 * All.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping("/all")
	public String all(HttpServletRequest request, HttpServletResponse response) {
		return PathResolver.getPath(request, FrontPage.ALL);
	}
	
	/**
	 * League.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping("/league")
	public String league(HttpServletRequest request, HttpServletResponse response) {
		return  businessService.getLeague(request, response);
	}
	
	
	/**
	 * Friendlink.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping("/friendlink")
	public String friendlink(HttpServletRequest request, HttpServletResponse response) {
		return businessService.getFriendlink(request, response);
	}
	
	/**
	 * Adds the shop.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param shopDetail
	 *            the shop detail
	 * @return the string
	 */
	@RequestMapping("/addShop")
	public String addShop(HttpServletRequest request, HttpServletResponse response,ShopDetail shopDetail) {
		// 用户需要登录
		String userName = UserManager.getUsername(request);
		if (AppUtils.isBlank(userName)) {
			return PathResolver.getPath(request, TilesPage.NO_LOGIN);
		}
		return businessService.saveShop(request, response,shopDetail);
	}	
	
	
	/**
	 * Hotview.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping("/hotview")
	public String hotview(HttpServletRequest request, HttpServletResponse response) {
		return  businessService.getHotView(request, response);
	}
	
	/**
	 * User reg success.
	 * 
	 * 用户用registerCode激活帐号
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param userName
	 *            the user name
	 * @param registerCode
	 *            the register code
	 * @return the string
	 */
	@RequestMapping("/userRegSuccess")
	public String userRegSuccess(HttpServletRequest request, HttpServletResponse response,String userName, String registerCode) {
		return businessService.updateUserReg(request, response,userName,registerCode);
	}

	/**
	 * Sort.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param curPageNO
	 *            the cur page no
	 * @param sortId
	 *            the sort id
	 * @return the string
	 */
	@RequestMapping("/sort/{sortId}")
	public String sort(HttpServletRequest request, HttpServletResponse response, String curPageNO, @PathVariable Long sortId ) {
		return businessService.getSort(request, response, curPageNO, sortId);
	}
	
	/**
	 * Sort by id.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param curPageNO
	 *            the cur page no
	 * @param sortId
	 *            the sort id
	 * @return the string
	 */
	@RequestMapping("/sort")
	public String sortById(HttpServletRequest request, HttpServletResponse response, String curPageNO,Long sortId ) {
		return businessService.getSort(request, response, curPageNO, sortId);
	}
	
	/**
	 * Shop.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param curPageNO
	 *            the cur page no
	 * @param shopName
	 *            the shop name
	 * @return the string
	 */
	@RequestMapping("/shop/{shopName}")
	public String shop(HttpServletRequest request, HttpServletResponse response, String curPageNO, @PathVariable String shopName) {
		request.setAttribute(Constants.SHOP_NAME, shopName);
		return PathResolver.getPath(request, FowardPage.INDEX_QUERY);
	}
	
	/**
	 * After operation.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping("/afterOperation")
	public String afterOperation(HttpServletRequest request, HttpServletResponse response) {
		return PathResolver.getPath(request, TilesPage.AFTER_OPERATION);
	}
	
	/**
	 * Product gallery.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param prodId
	 *            the prod id
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping("/productGallery/{prodId}")
	public String productGallery(HttpServletRequest request, HttpServletResponse response, @PathVariable Long prodId) throws Exception {
		if (AppUtils.isBlank(prodId)) {
			return PathResolver.getPath(request, FowardPage.INDEX_QUERY);
		}
		return businessService.getProductGallery(request, response,prodId);
	}
	
	/**
	 * Resetpassword.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping("/resetpassword")
	public String resetpassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return PathResolver.getPath(request, FrontPage.RESETPASSWORD);
	}
	
	@RequestMapping("/openShop")
	public String openShop(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return PathResolver.getPath(request, TilesPage.OPENSHOP);
	}
	
	
}
