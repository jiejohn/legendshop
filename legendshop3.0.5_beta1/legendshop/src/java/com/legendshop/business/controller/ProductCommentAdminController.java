/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

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
import com.legendshop.business.service.ProductCommentService;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.Product;
import com.legendshop.model.entity.ProductComment;
import com.legendshop.spi.constants.Constants;
import com.legendshop.util.AppUtils;
import com.legendshop.util.SafeHtml;
import com.legendshop.util.sql.ConfigCode;

/**
 * 用户评论控制器.
 */
@Controller
@RequestMapping("/admin/productcomment")
public class ProductCommentAdminController extends BaseController {
	
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(ProductCommentAdminController.class);
	
	/** The product comment service. */
	@Autowired
	private ProductCommentService productCommentService;


	/**
	 * Query.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param curPageNO
	 *            the cur page no
	 * @param productComment
	 *            the product comment
	 * @return the string
	 */
	@RequestMapping("/query")
	public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, ProductComment productComment) {

		String userName = UserManager.getUsername(request.getSession());
		Map<String, String> map = new HashMap<String, String>();
		HqlQuery hql = new HqlQuery(PropertiesUtil.getObject(SysParameterEnum.PAGE_SIZE, Integer.class), curPageNO);
		if (!CommonServiceUtil.haveViewAllDataFunction(request)) {
			map.put("ownerName", userName); // 商品所有人
			hql.addParams(userName);
		} else {
			if (AppUtils.isNotBlank(productComment.getOwnerName())) {
				map.put("ownerName", productComment.getOwnerName());
				hql.addParams(productComment.getOwnerName());
			}
		}

		if (AppUtils.isNotBlank(productComment.getUserName())) {// 评价人
			map.put("userName", productComment.getUserName());
			hql.addParams("%" + productComment.getUserName() + "%");
		}

		if (AppUtils.isNotBlank(productComment.getProdName())) {
			map.put("prodName", productComment.getProdName());
			hql.addParams("%" + productComment.getProdName() + "%");
		}

		if (!CommonServiceUtil.isDataForExport(hql, request)) {// 非导出情况
			hql.setPageSize(PropertiesUtil.getObject(SysParameterEnum.PAGE_SIZE, Integer.class));
		}
		if (!CommonServiceUtil.isDataSortByExternal(hql, request, map)) {
			map.put(Constants.ORDER_INDICATOR, "order by hc.addtime desc");
		}
		hql.setAllCountString(ConfigCode.getInstance().getCode("biz.getProductCommentCount", map));
		hql.setQueryString(ConfigCode.getInstance().getCode("biz.getProductComment", map));
		PageSupport ps = productCommentService.getProductCommentList(hql);
		savePage(ps, request);
		request.setAttribute("bean", productComment);
		return PathResolver.getPath(request,response,BackPage.PROD_COMM_LIST_PAGE);
	}

	/**
	 * Save.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param productComment
	 *            the product comment
	 * @return the string
	 */
	@RequestMapping(value = "/save")
	public String save(HttpServletRequest request, HttpServletResponse response, ProductComment productComment) {
		// only for update
		ProductComment comment = productCommentService.getProductCommentById(productComment.getId());
		if (comment == null) {
			throw new NotFoundException("ProductComment Not Found",EntityCodes.PROD);
		}
		String username = UserManager.getUsername(request.getSession());
		String result = checkPrivilege(request, username, comment.getOwnerName());
		if(result!=null){
			return result;
		}
		SafeHtml safe = new SafeHtml();
		comment.setReplyContent(safe.makeSafe(productComment.getReplyContent()));
		comment.setReplyName(username);
		comment.setReplyTime(new Date());
		productCommentService.update(comment);
		saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
		return PathResolver.getPath(request,response,FowardPage.PROD_COMM_LIST_QUERY);
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
		String username = UserManager.getUsername(request.getSession());
		ProductComment productComment = productCommentService.getProductCommentById(id);
		String result = checkPrivilege(request, username, productComment.getOwnerName());
		if(result!=null){
			return result;
		}
		log.info("{}, delete ProductComment Addtime {}, delete person", new Object[] { productComment.getUserName(),
				productComment.getAddtime(), username });
		productCommentService.delete(id);
		saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
		return PathResolver.getPath(request,response,FowardPage.PROD_COMM_LIST_QUERY);
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
		ProductComment productComment = productCommentService.getProductCommentById(id);
		if (productComment == null) {
			throw new NotFoundException("ProductComment not found with Id " + id,EntityCodes.PROD);
		}
		Product product = productCommentService.getProduct(productComment.getProdId());
		productComment.setProdName(product.getName());
		String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), productComment.getOwnerName());
		if(result!=null){
			return result;
		}
		request.setAttribute("bean", productComment);
		return PathResolver.getPath(request,response,BackPage.PROD_COMM_EDIT_PAGE);
	}

	/**
	 * Update.
	 * 
	 * @param productComment
	 *            the product comment
	 * @return the string
	 */
	@RequestMapping(value = "/update")
	public String update(HttpServletRequest request,HttpServletResponse response,@PathVariable ProductComment productComment) {
		productCommentService.update(productComment);
		return PathResolver.getPath(request,response,FowardPage.PROD_COMM_LIST_QUERY);
	}

}
