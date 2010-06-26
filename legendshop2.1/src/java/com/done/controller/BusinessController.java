package com.done.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import bingosoft.jcf.dao.support.CriteriaQuery;
import bingosoft.jcf.dao.support.PageSupport;
import bingosoft.jcf.sql.ConfigCode;
import bingosoft.jcf.util.AppUtils;

import com.done.base.BaseSpringController;
import com.done.common.Constants;
import com.done.dao.SqlQuery;
import com.done.model.LoginHistory;
import com.done.model.Nsort;
import com.done.model.ShopDetail;
import com.done.model.UserComment;
import com.done.service.AdminService;
import com.done.service.BusinessService;

/**
 * @author He-WenQiang. Create in 2009-10-08 20:40:06.
 * 
 */
@Controller
public class BusinessController extends BaseSpringController {
	private Logger log = LoggerFactory.getLogger(BusinessController.class);
	@Autowired
	private AdminService adminService;
	@Autowired
	private BusinessService businessService;
/*
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		System.out.println("initBinder calling");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
*/
	@RequestMapping("/businessAction/saveNSort")
	public String saveNSort(HttpServletRequest request,
			HttpServletResponse response, Map map) {
		String nsortName = (String) map.get("nsortName");
		String sortId = (String) map.get("sortId");
		String nsortId = (String) map.get("nsortId");
		Nsort nsort = new Nsort();
		nsort.setNsortName(nsortName);
		if (!AppUtils.isBlank(nsortId)) {
			nsort.setNsortId(Integer.valueOf(nsortId));
		}
		if (!AppUtils.isBlank(sortId)) {
			nsort.setSortId(Integer.valueOf(sortId));
		}
		adminService.saveNsort(nsort);
		request.setAttribute("sortId", sortId);
		return "forward:/nsortList.do";
	}

	@RequestMapping("/businessAction/leaveWord")
	public String leaveWord(HttpServletRequest request,
			HttpServletResponse response, UserComment comment) {
		String shopName = getShopName(request, response);
		comment.setAddtime(new Date());
		comment.setCommentType(Constants.COMMONTALK);
		comment.setPostip(request.getRemoteAddr());
		comment.setToUserName(shopName);
		comment.setStatus(Constants.COMMENT_UN_READ);
		adminService.saveOrUpdate(comment);
		ShopDetail shopDetail = businessService.getShopDetail(shopName);
		request.setAttribute("shopDetail", shopDetail);
		return "forward:/operateSucessful.do";

	}
	

	@RequestMapping("/businessAction/loginHistoryList")
	public String loginHistoryList(HttpServletRequest request,
			HttpServletResponse response, LoginHistory login) {
		try {
			// Qbc查找方式
			CriteriaQuery cq = new CriteriaQuery(LoginHistory.class, login
					.getCurPageNO(), "javascript:pager");
			cq.setPageSize(18);
			cq.eq("userName", login.getUserName());
			cq.ge("time", login.getStartTime());
			cq.le("time", login.getEndTime());
			cq.addOrder("desc", "time");
			cq.add();
			PageSupport ps = adminService.getDataByCriteriaQuery(cq);
			request.setAttribute("offset", new Integer(ps.getOffset() + 1));
			request.setAttribute("list", ps.getResultList());
			request.setAttribute("login", login);
			if (ps.hasMutilPage()) {
				request.setAttribute("toolBar", ps
						.getToolBar((Locale) getSessionAttribute(request,
								Globals.LOCALE_KEY)));
			}
			return "/loginhistory/loginHistoryList";
		} catch (Exception e) {
			log.error("ERROR happened: ", e);
			throw new RuntimeException("loginHistoryList");
		}

	}

	/**
	 * 登录历史表
	 * 
	 * @param login
	 */
	@RequestMapping("/businessAction/loginHistorySum")
	public String loginHistorySum(HttpServletRequest request,
			HttpServletResponse response, LoginHistory login) {
		try {
			// HQL查找方式
			SqlQuery query = new SqlQuery("javascript:pager");
			query.setCurPage(login.getCurPageNO());
			query.setPageSize(Constants.PAGE_SIZE);
			Map<String, String> map = new HashMap<String, String>();
			if (!AppUtils.isBlank(login.getStartTime())) {
				map.put("startTime", login.getStartTime().toString());
				query.addParam(login.getStartTime());
			}
			if (!AppUtils.isBlank(login.getEndTime())) {
				map.put("endTime", login.getEndTime().toString());
				query.addParam(login.getEndTime());
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
			PageSupport ps = adminService.getDataBySQL(query);

			request.setAttribute("offset", new Integer(ps.getOffset() + 1));
			request.setAttribute("list", ps.getResultList());
			request.setAttribute("login", login);
			// if(ps.hasMutilPage())
			request.setAttribute("toolBar", ps
					.getToolBar((Locale) getSessionAttribute(request,
							Globals.LOCALE_KEY)));
			return "/loginhistory/loginHistorySum";
		} catch (Exception e) {
			log.error("ERROR happened: ", e);
			throw new RuntimeException("loginHistorySum");
		}
	}
}
