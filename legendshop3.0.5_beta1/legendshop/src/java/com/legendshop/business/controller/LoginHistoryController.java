/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.legendshop.business.common.page.BackPage;
import com.legendshop.business.service.LoginHistoryService;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SqlQuery;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.LoginHistory;
import com.legendshop.util.AppUtils;
import com.legendshop.util.sql.ConfigCode;

/**
 * 登录历史
 */
@Controller
@RequestMapping("/admin/loginHistory")
public class LoginHistoryController extends BaseController{ 
	
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(LoginHistoryController.class);
	
	/** The login history service. */
	@Autowired
	private  LoginHistoryService loginHistoryService;
	
	/**
	 * Query.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param curPageNO
	 *            the cur page no
	 * @param login
	 *            the login
	 * @return the string
	 */
	@RequestMapping("/query")
	public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO,  LoginHistory login) {
		// Qbc查找方式
		CriteriaQuery cq = new CriteriaQuery(LoginHistory.class,curPageNO);
		cq.setPageSize(PropertiesUtil.getObject(SysParameterEnum.PAGE_SIZE,
				Integer.class));
		cq.eq("userName", login.getUserName());
		cq.ge("time", login.getStartTime());
		cq.le("time", login.getEndTime());
		cq.addOrder("desc", "time");
		
		PageSupport ps = loginHistoryService.getLoginHistory(cq);
		savePage(ps, request);
		request.setAttribute("login", login);
		return PathResolver.getPath(request,response,BackPage.LOGIN_HIST_LIST_PAGE);
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
		return PathResolver.getPath(request,response,BackPage.LOGIN_HIST_LIST_PAGE);
	}
	
	/**
	 * 登录历史表.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param curPageNO
	 *            the cur page no
	 * @param login
	 *            the login
	 * @return the string
	 */
	@RequestMapping("/sum")
	public String loginHistorySum(HttpServletRequest request, HttpServletResponse response,String curPageNO,  LoginHistory login) {
			// HQL查找方式
			SqlQuery query = new SqlQuery(PropertiesUtil.getObject(SysParameterEnum.PAGE_SIZE, Integer.class),curPageNO);
			Map<String, String> map = new HashMap<String, String>();
			if (!AppUtils.isBlank(login.getStartTime())) {
				map.put("startTime", login.getStartTime().toString());
				query.addParams(login.getStartTime());
			}
			if (!AppUtils.isBlank(login.getEndTime())) {
				map.put("endTime", login.getEndTime().toString());
				query.addParams(login.getEndTime());
			}
			if (!AppUtils.isBlank(login.getUserName())) {// 因为name直接写进SQL，不用{?模式，不用设置参数
				map.put("userName", login.getUserName());
			}

			String sql = ConfigCode.getInstance().getCode(
					"biz.loginHistorySum", map);
			String countSql = ConfigCode.getInstance().getCode(
					"biz.loginHistoryCount", map);
			query.setQueryString(sql);
			query.setAllCountString(countSql);
			PageSupport ps = loginHistoryService.getLoginHistoryBySQL(query);
			savePage(ps, request);
			request.setAttribute("login", login);
			return PathResolver.getPath(request,response,BackPage.LOGIN_HIST_SUM_PAGE);

	}

}
