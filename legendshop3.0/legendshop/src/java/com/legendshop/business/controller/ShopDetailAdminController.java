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
import com.legendshop.business.common.PageLetEnum;
import com.legendshop.business.common.ShopStatusEnum;
import com.legendshop.business.common.ShopTypeEnum;
import com.legendshop.business.search.facade.ShopDetailSearchFacade;
import com.legendshop.business.service.ShopDetailService;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.BusinessException;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.core.helper.FileProcessor;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.RealPathUtil;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.model.entity.ShopDetail;
import com.legendshop.model.entity.UserDetail;
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
		hasAllDataFunction(cq, request, "storeName", StringUtils.trim(shopDetail.getStoreName()));

		if (!CommonServiceUtil.isDataForExport(cq, request)) {// 非导出情况
			cq.setPageSize(PropertiesUtil.getObject(ParameterEnum.PAGE_SIZE, Integer.class));
		}
		if (!CommonServiceUtil.isDataSortByExternal(cq, request)) {
			cq.addOrder("desc", "modifyTime");
		}
		cq.eq("type", shopDetail.getType());
		cq.eq("colorStyle", shopDetail.getColorStyle());
		cq.eq("status", shopDetail.getStatus());
		cq.add();
		PageSupport ps = shopDetailService.getShopDetail(cq);
		savePage(ps, request);
		request.setAttribute("shopDetail", shopDetail);
		return PathResolver.getPath(request, PageLetEnum.SHOP_DETAIL_LIST_PAGE);
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
			String userName = shopDetail.getStoreName();

			String shopPic = null;
			checkPrivilege(request, UserManager.getUsername(request.getSession()), userName);
			if (AppUtils.isBlank(userName)) {
				throw new NotFoundException("user name can not be null!",EntityCodes.SHOP);
			}
			UserDetail userDetail = shopDetailService.getShopDetailByName(userName);
			if (AppUtils.isBlank(userDetail)) {
				throw new NotFoundException("userDetail can not be null!",EntityCodes.SHOP);
			}
			try {
				SafeHtml safeHtml = new SafeHtml();
				shopDetail.setUserId(userDetail.getUserId());
				Date date = new Date();
				shopDetail.setModifyTime(date);
				shopDetail.setAddtime(date);
				shopDetail.setVisitTimes(0);
				shopDetail.setOffProductNum(0);
				shopDetail.setCommNum(0);
				shopDetail.setProductNum(0);
				shopDetail.setWeb(safeHtml.makeSafe(shopDetail.getStoreName()));
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

			return PathResolver.getPath(request, PageLetEnum.SHOP_DETAIL_LIST_QUERY);
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
		return PathResolver.getPath(request, PageLetEnum.SHOP_DETAIL_LIST_QUERY);
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
			checkPrivilege(request, UserManager.getUsername(request), shopDetail.getStoreName());
		}
		
		request.setAttribute("shopDetail", shopDetail); 
		request.setAttribute("id", request.getParameter("userId"));
		return PathResolver.getPath(request, PageLetEnum.SHOP_DETAIL_EDIT_PAGE);
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
		return PathResolver.getPath(request, PageLetEnum.SHOP_DETAIL_EDIT_PAGE);
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
		String originShopPic = shop.getShopPic();
		String shopPic = null;
		checkPrivilege(request, UserManager.getUsername(request.getSession()), shopDetail.getStoreName());
		try {
			String subPath = shopDetail.getStoreName() + "/shop/";
			SafeHtml safeHtml = new SafeHtml();
			shop.setSitename(safeHtml.makeSafe(shopDetail.getSitename()));
			shop.setMaddr(safeHtml.makeSafe(shopDetail.getMaddr()));
			shop.setMsn(safeHtml.makeSafe(shopDetail.getMsn()));
			shop.setMname(safeHtml.makeSafe(shopDetail.getMname()));
			shop.setCode(safeHtml.makeSafe(shopDetail.getCode()));
			shop.setYmaddr(safeHtml.makeSafe(shopDetail.getYmaddr()));
			shop.setYmname(safeHtml.makeSafe(shopDetail.getYmname()));
			shop.setColorStyle(shopDetail.getColorStyle());
			shop.setLangStyle(shopDetail.getLangStyle());
			shop.setBriefDesc(safeHtml.makeSafe(shopDetail.getBriefDesc()));
			shop.setDetailDesc(safeHtml.makeSafe(shopDetail.getDetailDesc()));
			shop.setModifyTime(new Date());
			shop.setProvinceid(shopDetail.getProvinceid());
			shop.setCityid(shopDetail.getCityid());
			shop.setAreaid(shopDetail.getAreaid());
			if (CommonServiceUtil.haveViewAllDataFunction(request)
					|| !ShopStatusEnum.AUDITING.value().equals(shop.getStatus())) {
				if (shopDetail.getStatus() != null) {
					shop.setStatus(shopDetail.getStatus());
				}
			}
			if (shopDetail.getFile().getSize() > 0) {
				shopPic = FileProcessor.uploadFileAndCallback(shopDetail.getFile(), subPath, "so"
						+ shopDetail.getStoreName());
				shop.setShopPic(shopPic);
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
		return PathResolver.getPath(request, PageLetEnum.SHOP_DETAIL_LIST_QUERY);
	}

}
