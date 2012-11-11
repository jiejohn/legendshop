/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.business.common.page.BackPage;
import com.legendshop.business.common.page.FowardPage;
import com.legendshop.business.search.facade.ShopDetailSearchFacade;
import com.legendshop.business.service.ShopDetailService;
import com.legendshop.core.OperationTypeEnum;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.constant.ShopStatusEnum;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.event.impl.FireEvent;
import com.legendshop.core.exception.BusinessException;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.core.helper.FileProcessor;
import com.legendshop.core.helper.FoundationUtil;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.RealPathUtil;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.event.EventHome;
import com.legendshop.model.entity.ShopDetail;
import com.legendshop.model.entity.UserDetail;
import com.legendshop.spi.constants.ShopTypeEnum;
import com.legendshop.util.AppUtils;
import com.legendshop.util.SafeHtml;

/**
 * 商城详细信息控制器.
 */
@Controller
@RequestMapping("/admin/shopDetail")
public class ShopDetailAdminController extends BaseController {
	
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(ShopDetailAdminController.class);
	
	/** The shop detail service. */
	@Autowired
	private ShopDetailService shopDetailService;

	/** The shop detail search facade. */
	@Autowired
	private ShopDetailSearchFacade shopDetailSearchFacade;

	/**
	 * Query.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param curPageNO
	 *            the cur page no
	 * @param shopDetail
	 *            the shop detail
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping("query")
	public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO,
			ShopDetail shopDetail) throws Exception {
		CriteriaQuery cq = new CriteriaQuery(ShopDetail.class, curPageNO, "javascript:pager");
		hasAllDataFunction(cq, request, "userName", StringUtils.trim(shopDetail.getUserName()));

		if (!CommonServiceUtil.isDataForExport(cq, request)) {// 非导出情况
			cq.setPageSize(PropertiesUtil.getObject(SysParameterEnum.PAGE_SIZE, Integer.class));
		}
		if (!CommonServiceUtil.isDataSortByExternal(cq, request)) {
			cq.addOrder("desc", "modifyTime");
		}
		cq.eq("type", shopDetail.getType());
		cq.eq("colorStyle", shopDetail.getColorStyle());
		cq.eq("status", shopDetail.getStatus());
		
		PageSupport ps = shopDetailService.getShopDetail(cq);
		savePage(ps, request);
		request.setAttribute("shopDetail", shopDetail);
		return PathResolver.getPath(request,response,BackPage.SHOP_DETAIL_LIST_PAGE);
	}

	/**
	 * Save.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param shopDetail
	 *            the shop detail
	 * @return the string
	 */
	@RequestMapping(value = "/save")
	public String save(HttpServletRequest request, HttpServletResponse response, ShopDetail shopDetail) {
		if ((shopDetail != null) && (!AppUtils.isBlank(shopDetail.getShopId()))) {
			return update(request, response, shopDetail);
		} else {
			// 检查用户权限，只有超级用户可以使用
			String userName = shopDetail.getUserName();

			String shopPic = null;
			String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), userName);
			if(result!=null){
				return result;
			}
			if (AppUtils.isBlank(userName)) {
				throw new NotFoundException("user name can not be null!",EntityCodes.SHOP);
			}
			UserDetail userDetail = shopDetailService.getShopDetailByName(userName);
			if (AppUtils.isBlank(userDetail)) {
				throw new NotFoundException("userDetail can not be null!",EntityCodes.SHOP);
			}
			try {
				//SafeHtml safeHtml = new SafeHtml();
				shopDetail.setUserId(userDetail.getUserId());
				Date date = new Date();
				shopDetail.setModifyTime(date);
				shopDetail.setAddtime(date);
				shopDetail.setVisitTimes(0);
				shopDetail.setOffProductNum(0);
				shopDetail.setCommNum(0);
				shopDetail.setProductNum(0);
				shopDetail.setType(ShopTypeEnum.BUSINESS.value());
				shopDetail.setStatus(ShopStatusEnum.ONLINE.value());
				String subPath = userName + "/shop/";
				if (shopDetail.getFile().getSize() > 0) {
					shopPic = FileProcessor.uploadFileAndCallback(shopDetail.getFile(), subPath, "so" + userName);
					shopDetail.setShopPic(shopPic);
				}
				shopDetailService.save(shopDetail);
				saveMessage(request, ResourceBundleHelper.getSucessfulString());
			} catch (Exception e) {
				if (shopPic != null) {
					FileProcessor.deleteFile(shopPic);
				}
				throw new BusinessException(e, "error happened when save shop");
			}

			return PathResolver.getPath(request,response,FowardPage.SHOP_DETAIL_LIST_QUERY);
		}
	}

	/**
	 * Delete.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param id
	 *            the id
	 * @return the string
	 */
	@RequestMapping(value = "/delete/{id}")
	public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable
	Long id) {
		ShopDetail shopDetail = shopDetailService.getShopDetailById(id);
		if (shopDetail != null) {
			shopDetailService.delete(shopDetail);
			shopDetailSearchFacade.delete(shopDetail);
		}

		saveMessage(request, ResourceBundleHelper.getDeleteString());
		return PathResolver.getPath(request,response,FowardPage.SHOP_DETAIL_LIST_QUERY);
	}

	/**
	 * Load.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param id
	 *            the id
	 * @return the string
	 */
	@RequestMapping(value = "/load/{id}")
	public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable
	Long id) {
		ShopDetail shopDetail = shopDetailService.getShopDetailById(id);
		if (shopDetail != null) {
			String result = checkPrivilege(request, UserManager.getUsername(request), shopDetail.getUserName());
			if(result!=null){
				return result;
			}
		}
		
		request.setAttribute("shopDetail", shopDetail); 
		request.setAttribute("id", request.getParameter("userId"));
		return PathResolver.getPath(request,response,BackPage.SHOP_DETAIL_EDIT_PAGE);
	}
	
	/**
	 * Load.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping(value = "/load")
	public String load(HttpServletRequest request, HttpServletResponse response) {
		return PathResolver.getPath(request,response,BackPage.SHOP_DETAIL_EDIT_PAGE);
	}

	/**
	 * Update.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param shopDetail
	 *            the shop detail
	 * @return the string
	 */
	private String update(HttpServletRequest request, HttpServletResponse response, ShopDetail shopDetail) {
		ShopDetail shop = shopDetailService.getShopDetailById(shopDetail.getShopId());
		if (shop == null) {
			throw new NotFoundException("shop can not be null, getShopId = " + shopDetail.getShopId(),EntityCodes.SHOP);
		}
		EventHome.publishEvent(new FireEvent(shopDetail, OperationTypeEnum.UPDATE));
		String originShopPic = shop.getShopPic();
		String shopPic = null;
		String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), shopDetail.getUserName());
		if(result!=null){
			return result;
		}
		try {
			String subPath = shopDetail.getUserName() + "/shop/";
			SafeHtml safeHtml = new SafeHtml();
			shop.setSiteName(safeHtml.makeSafe(shopDetail.getSiteName()));
			shop.setShopAddr(safeHtml.makeSafe(shopDetail.getShopAddr()));
			shop.setBankCard(safeHtml.makeSafe(shopDetail.getBankCard()));
			shop.setPayee(safeHtml.makeSafe(shopDetail.getPayee()));
			shop.setCode(safeHtml.makeSafe(shopDetail.getCode()));
			shop.setPostAddr(safeHtml.makeSafe(shopDetail.getPostAddr()));
			shop.setRecipient(safeHtml.makeSafe(shopDetail.getRecipient()));
			shop.setColorStyle(shopDetail.getColorStyle());
			shop.setLangStyle(shopDetail.getLangStyle());
			shop.setBriefDesc(safeHtml.makeSafe(shopDetail.getBriefDesc()));
			shop.setDetailDesc(safeHtml.makeSafe(shopDetail.getDetailDesc()));
			shop.setModifyTime(new Date());
			shop.setProvinceid(shopDetail.getProvinceid());
			shop.setCityid(shopDetail.getCityid());
			shop.setAreaid(shopDetail.getAreaid());
			shop.setFrontType(shopDetail.getFrontType());
			shop.setEndType(shopDetail.getEndType());
			if (CommonServiceUtil.haveViewAllDataFunction(request)
					|| !ShopStatusEnum.AUDITING.value().equals(shop.getStatus())) {
				if (shopDetail.getStatus() != null) {
					shop.setStatus(shopDetail.getStatus());
				}
			}
			if (shopDetail.getFile().getSize() > 0) {
				shopPic = FileProcessor.uploadFileAndCallback(shopDetail.getFile(), subPath, "so"
						+ shopDetail.getUserName());
				shop.setShopPic(shopPic);
			}
			if(FoundationUtil.haveViewAllDataFunction(request)){
				shop.setDomainName(shopDetail.getDomainName());
				shop.setIcpInfo(shopDetail.getIcpInfo());
			}

			shopDetailService.update(shop);
		} catch (Exception e) {
			if (shopPic != null) {
				FileProcessor.deleteFile(shopPic);
			}
			throw new BusinessException(e, "error happened when update shop");
		} finally {
			if (shopPic != null && originShopPic != null) {
				FileProcessor.deleteFile(RealPathUtil.getBigPicRealPath() + "/" + originShopPic);
			}
		}

		shopDetailSearchFacade.update(shop);
		saveMessage(request, ResourceBundleHelper.getSucessfulString());
		return PathResolver.getPath(request,response,FowardPage.SHOP_DETAIL_LIST_QUERY);
	}

}
