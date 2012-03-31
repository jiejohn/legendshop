/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.controller;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.business.common.page.BackPage;
import com.legendshop.business.common.page.FowardPage;
import com.legendshop.business.service.VisitLogService;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.AdminController;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.AuthorizationException;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.VisitLog;
import com.legendshop.util.AppUtils;

/**
 * 访问历史控制器.
 */
@Controller
@RequestMapping("/admin/visitLog")
public class VisitLogAdminController extends BaseController implements AdminController<VisitLog, Long>{
	
	/** The LIS t_ page. */
	public static String LIST_PAGE = "/visitLog/visitLogList";
	
	/** The visit log service. */
	@Autowired
	private VisitLogService visitLogService;

	/* (non-Javadoc)
	 * @see com.legendshop.core.base.AdminController#query(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.String, java.lang.Object)
	 */
	@Override
	@RequestMapping("/query")
	public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, VisitLog visitLog) {

		CriteriaQuery cq = new CriteriaQuery(VisitLog.class, curPageNO, "javascript:pager");

		if (CommonServiceUtil.haveViewAllDataFunction(request)) {
			if (!AppUtils.isBlank(visitLog.getShopName())) {
				cq.like("shopName", "%" + StringUtils.trim(visitLog.getShopName()) + "%");
			}
		} else {
			String name = UserManager.getUsername(request.getSession());
			if (name == null) {
				throw new AuthorizationException("you are not logon yet!",EntityCodes.LOG);
			}
			cq.eq("shopName", name);
		}
		cq.ge("date", visitLog.getStartTime());
		cq.le("date", visitLog.getEndTime());
		cq.eq("page", visitLog.getPage());
		if (AppUtils.isNotBlank(visitLog.getUserName())) {
			cq.like("userName", visitLog.getUserName() + "%");
		}

		if (AppUtils.isNotBlank(visitLog.getProductName())) {
			cq.like("productName", "%" + visitLog.getProductName() + "%");
		}
		if (!CommonServiceUtil.isDataForExport(cq, request)) {// 非导出情况
			cq.setPageSize(PropertiesUtil.getObject(ParameterEnum.PAGE_SIZE, Integer.class));
		}
		if (!CommonServiceUtil.isDataSortByExternal(cq, request)) {
			cq.addOrder("desc", "date");
		}
		cq.add();
		PageSupport ps = visitLogService.getVisitLogList(cq);
		savePage(ps, request);
		request.setAttribute("bean", visitLog);
		return PathResolver.getPath(request, BackPage.VLOG_LIST_PAGE);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.core.base.AdminController#save(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	@RequestMapping(value = "/save")
	public String save(HttpServletRequest request, HttpServletResponse response, VisitLog visitLog) {
		visitLogService.save(visitLog);
		saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
		return PathResolver.getPath(request, FowardPage.VLOG_LIST_QUERY);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.core.base.AdminController#delete(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	@RequestMapping(value = "/delete/{id}")
	public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable
	Long id) {
		visitLogService.delete(id);
		saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
		return PathResolver.getPath(request, FowardPage.VLOG_LIST_QUERY);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.core.base.AdminController#load(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String load(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.core.base.AdminController#update(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public String update(HttpServletRequest request, HttpServletResponse response, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
