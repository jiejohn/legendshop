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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.business.common.page.BackPage;
import com.legendshop.business.common.page.FowardPage;
import com.legendshop.business.common.page.TilesPage;
import com.legendshop.business.service.BusinessService;
import com.legendshop.business.service.UserCommentService;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.AdminController;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.AuthorizationException;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.model.entity.UserComment;
import com.legendshop.spi.constants.CommentTypeEnum;
import com.legendshop.util.AppUtils;

/**
 * 用户产品评论.
 */
@Controller
@RequestMapping("/admin/userComment")
public class UserCommentAdminController extends BaseController implements AdminController<UserComment, Long>{ 
	
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(UserCommentAdminController.class);

	/** The user comment service. */
	@Autowired
	private UserCommentService userCommentService;
	
	@Autowired
	private BusinessService businessService;

	/* (non-Javadoc)
	 * @see com.legendshop.core.base.AdminController#query(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.String, java.lang.Object)
	 */
	@Override
	@RequestMapping("/query")
	public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, UserComment userComment) {
		String search = request.getParameter("search") == null ? "" : request.getParameter("search").trim();
		String status = request.getParameter("status") == null ? "0" : request.getParameter("status");// 默认显示为未读
		String userName = request.getParameter("userName") == null ? "" : request.getParameter("userName").trim();
		CriteriaQuery cq = new CriteriaQuery(UserComment.class, curPageNO, "javascript:pager");
		cq.setPageSize(PropertiesUtil.getObject(SysParameterEnum.PAGE_SIZE, Integer.class));
		//right check
		if (!AppUtils.isBlank(status)) {
			cq.eq("status", Integer.valueOf(status));
		}
		if (CommonServiceUtil.haveViewAllDataFunction(request)) {
			if (!AppUtils.isBlank(search)) {
				cq.like("toUserName", "%" + search + "%");
			}
		} else {
			String name = UserManager.getUsername(request);
			if (name == null) {
				throw new AuthorizationException("you are not logon yet!",EntityCodes.USER);
			}
			cq.eq("toUserName", name);
		}
		if (!AppUtils.isBlank(userName)) {
			cq.like("userName", "%" + userName + "%");
		}

		if (!CommonServiceUtil.isDataForExport(cq, request)) {// 非导出情况
			cq.setPageSize(PropertiesUtil.getObject(SysParameterEnum.PAGE_SIZE, Integer.class));
		}
		if (!CommonServiceUtil.isDataSortByExternal(cq, request)) {
			cq.addOrder("desc", "addtime");
		}
		
		PageSupport ps = userCommentService.getUserCommentList(cq);
		savePage(ps, request);
		request.setAttribute("search", search);
		request.setAttribute("status", status);
		request.setAttribute("userName", userName);
		request.setAttribute("bean", userComment);
		return PathResolver.getPath(request,response,BackPage.USER_COMM_LIST_PAGE);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.core.base.AdminController#save(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	@RequestMapping(value = "/save")
	public String save(HttpServletRequest request, HttpServletResponse response, UserComment comment) {
		String loginName = UserManager.getUsername(request);
		String result = checkPrivilege(request, loginName, comment.getUserName());
		if(result!=null){
			return result;
		}
		String shopName = ThreadLocalContext.getCurrentShopName(request,response);
		ThreadLocalContext.getShopDetailView(request,response,shopName);
		comment.setAddtime(new Date());
		comment.setCommentType(CommentTypeEnum.COMMONTALK.value());
		comment.setPostip(request.getRemoteAddr());
		comment.setToUserName(shopName);
		comment.setStatus(CommentTypeEnum.COMMENT_UN_READ.value());
		userCommentService.saveOrUpdateUserComment(comment);
		return PathResolver.getPath(request,response,TilesPage.AFTER_OPERATION);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.core.base.AdminController#delete(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	@RequestMapping(value = "/delete/{id}")
	public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable
	Long id) {
		UserComment userComment = userCommentService.getUserComment(id);
		String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), userComment.getUserName());
		if(result!=null){
			return result;
		}
		log.info("{} delete UserComment Id {}, ToUserName {}", new Object[] { userComment.getUserName(), userComment.getId(),
				userComment.getToUserName() });
		userCommentService.delete(userComment);
		saveMessage(request, ResourceBundleHelper.getDeleteString());
		return PathResolver.getPath(request,response,FowardPage.USER_COMM_LIST_QUERY);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.core.base.AdminController#load(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value = "/load")
	public String load(HttpServletRequest request, HttpServletResponse response) {
		return PathResolver.getPath(request,response,BackPage.USER_COMM_EDIT_PAGE);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.core.base.AdminController#update(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	@RequestMapping(value = "/update/{id}")
	public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
		checkNullable("userCpmment Id", id);
		UserComment comment = userCommentService.getUserComment(id);
		if (!CommonServiceUtil.haveViewAllDataFunction(request)) {
			//管理员不能update别人的消息
			userCommentService.updateUserCommentToReaded(comment);
		}
		String result = checkPrivilege(request, UserManager.getUsername(request), comment.getToUserName());
		if(result!=null){
			return result;
		}
		request.setAttribute("comment", comment);
		return PathResolver.getPath(request,response,BackPage.USER_COMM_EDIT_PAGE);
	}

}
