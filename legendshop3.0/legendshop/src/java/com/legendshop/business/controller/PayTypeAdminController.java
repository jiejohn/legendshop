/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.controller;

import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.legendshop.business.common.page.BackPage;
import com.legendshop.business.common.page.FowardPage;
import com.legendshop.business.service.PayTypeService;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.AuthorizationException;
import com.legendshop.core.exception.BusinessException;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.tag.TableCache;
import com.legendshop.model.entity.PayType;
import com.legendshop.util.AppUtils;

/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * 官方网站：http://www.legendesign.net
 */
@Controller
@RequestMapping("/admin/paytype")
public class PayTypeAdminController extends BaseController {
	
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(PayTypeAdminController.class);
	
	/** The pay type service. */
	@Autowired
	private PayTypeService payTypeService;

	/** The code tables cache. */
	@Autowired
	private TableCache codeTablesCache;

	/**
	 * Query.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param curPageNO
	 *            the cur page no
	 * @param payType
	 *            the pay type
	 * @return the string
	 */
	@RequestMapping("/query")
	public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, PayType payType) {
		CriteriaQuery cq = new CriteriaQuery(PayType.class, curPageNO, "javascript:pager");
		cq.setPageSize(PropertiesUtil.getObject(ParameterEnum.PAGE_SIZE, Integer.class));
		cq = hasAllDataAndOperationFunction(cq, request, StringUtils.trim(payType.getUserName()));
		cq.add();
		PageSupport ps = payTypeService.getPayTypeList(cq);
		savePage(ps, request);
		request.setAttribute("bean", payType);
		return PathResolver.getPath(request, BackPage.PAY_TYPE_LIST_PAGE);
	}

	/**
	 * Save.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param payType
	 *            the pay type
	 * @return the string
	 */
	@RequestMapping(value = "/save")
	public String save(HttpServletRequest request, HttpServletResponse response, PayType payType) {
		String name = UserManager.getUsername(request.getSession());
		if (AppUtils.isBlank(name)) {
			throw new AuthorizationException("you are not logon yet!",EntityCodes.PAY);
		}
		payType.setUserName(name);
		Map<String, String> map = codeTablesCache.getCodeTable("PAY_TYPE");
		if (AppUtils.isNotBlank(map)) {
			payType.setPayTypeName(map.get(String.valueOf(payType.getPayTypeId())));
		}
		if (AppUtils.isNotBlank(payType.getPayId())) {
			return update(request, response, payType);
		}
		PayType origin = payTypeService.getPayTypeByIdAndName(payType.getUserName(), payType.getPayTypeId());
		if (AppUtils.isNotBlank(origin)) {
			throw new BusinessException("你已经创建一个叫 “" + payType.getPayTypeName() + "” 的支付方式",EntityCodes.PAY);
		}
		payTypeService.save(payType);
		saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
		return PathResolver.getPath(request, FowardPage.PAY_TYPE_LIST_QUERY);
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
		payTypeService.delete(id);
		saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
		return PathResolver.getPath(request, FowardPage.PAY_TYPE_LIST_QUERY);
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
		PayType payType = payTypeService.getPayTypeById(id);
		String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), payType.getUserName());
		if(result!=null){
			return result;
		}
		request.setAttribute("bean", payType);
		return PathResolver.getPath(request, BackPage.PAY_TYPE_EDIT_PAGE);
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
		return PathResolver.getPath(request, BackPage.PAY_TYPE_EDIT_PAGE);
	}

	/**
	 * Update.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param payType
	 *            the pay type
	 * @return the string
	 */
	private String update(HttpServletRequest request, HttpServletResponse response, PayType payType) {
		PayType origin = payTypeService.getPayTypeById(payType.getPayId());
		String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), origin.getUserName());
		if(result!=null){
			return result;
		}
		log.info("{} update paytype, Partner: {}, ValidateKey: {}, SellerEmail: {} ", new Object[] {
				origin.getUserName(), origin.getPartner(), origin.getValidateKey(), origin.getSellerEmail() });
		origin.setMemo(payType.getMemo());
		origin.setPartner(payType.getPartner());
		origin.setPayTypeId(payType.getPayTypeId());
		origin.setPayTypeName(payType.getPayTypeName());
		origin.setSellerEmail(payType.getSellerEmail());
		origin.setValidateKey(payType.getValidateKey());
		try {
			payTypeService.update(origin);
		} catch (DataIntegrityViolationException e) {
			throw new BusinessException(e, "你已经创建一个叫 “" + payType.getPayTypeName() + "” 的支付方式");
		}
		return PathResolver.getPath(request, FowardPage.PAY_TYPE_LIST_QUERY);
	}

}
