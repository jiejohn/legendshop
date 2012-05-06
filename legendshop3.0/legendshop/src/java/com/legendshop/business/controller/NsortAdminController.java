/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.business.common.page.BackPage;
import com.legendshop.business.common.page.FowardPage;
import com.legendshop.business.service.NsortService;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.AdminController;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.core.exception.PermissionException;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.model.entity.Nsort;
import com.legendshop.model.entity.Sort;
import com.legendshop.util.AppUtils;
import com.legendshop.util.sql.ConfigCode;

/**
 * 产品子分类控制器.
 */
@Controller
@RequestMapping("/admin/nsort")
public class NsortAdminController extends BaseController implements AdminController<Nsort, Long> {

	/** The log. */
	private final Logger log = LoggerFactory.getLogger(NsortAdminController.class);

	/** The nsort service. */
	@Autowired
	private NsortService nsortService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.legendshop.core.base.AdminController#query(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.String, java.lang.Object)
	 */
	@Override
	@RequestMapping("/query")
	public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, Nsort nsort) {
		Sort sort = nsortService.getSort(nsort.getSortId());
		if (AppUtils.isBlank(sort)) {
			throw new NotFoundException("Sort is null!", EntityCodes.SORT);
		}
		HqlQuery hql = new HqlQuery();
		hql.setPageSize(PropertiesUtil.getObject(ParameterEnum.PAGE_SIZE, Integer.class));
		hql.setCurPage(curPageNO);
		Map<String, String> map = new HashMap<String, String>();
		hql.addParams(sort.getSortId());
		String userName = UserManager.getUsername(request.getSession());
		if (!CommonServiceUtil.haveViewAllDataFunction(request)) {
			map.put("userName", userName);
			hql.addParams(userName);
		}
		String name = request.getParameter("name");
		if (!AppUtils.isBlank(name)) {
			map.put("nsortName", name);
			hql.addParams(name);
			request.setAttribute("name", name);
		}
		String QueryNsortCount = ConfigCode.getInstance().getCode("biz.QueryNsortCount", map);
		String QueryNsort = ConfigCode.getInstance().getCode("biz.QueryNsort", map);
		hql.setAllCountString(QueryNsortCount);
		hql.setQueryString(QueryNsort);
		PageSupport ps = nsortService.getNsortList(hql);
		List<Nsort> list = ps.getResultList();// 2级分类
		List<Nsort> subNsort = nsortService.getNSortBySort(nsort.getSortId());// 3级分类
		if (!AppUtils.isBlank(list)) {
			for (Nsort n : list) {
				for (Nsort nsort2 : subNsort) {
					n.addSubSort(nsort2);
				}
			}
		}
		savePage(ps, request);
		request.setAttribute("bean", nsort);
		return PathResolver.getPath(request, BackPage.NSORT_LIST_PAGE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.legendshop.core.base.AdminController#save(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.Object)
	 */
	@Override
	@RequestMapping(value = "/save")
	public String save(HttpServletRequest request, HttpServletResponse response, Nsort nsort) {
		String name = UserManager.getUsername(request.getSession());
		Sort sort = nsortService.getSort(nsort.getSortId());
		String result = checkPrivilege(request, name, sort.getUserName());
		if(result!=null){
			return result;
		}
		System.out.println("-----------------"+nsort.getNsortName()+nsort.getSortDeputy());
		nsortService.save(nsort);
		saveMessage(request, ResourceBundleHelper.getSucessfulString());
		Nsort bean = (Nsort) request.getAttribute("bean");
		if (bean != null) {
			bean.setNsortName(null);
			request.setAttribute("bean", bean);
		}
		
		return PathResolver.getPath(request, FowardPage.NSORT_LIST_QUERY);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.legendshop.core.base.AdminController#delete(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.Object)
	 */
	@Override
	@RequestMapping(value = "/delete/{id}")
	public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
		Nsort nsort = nsortService.getNsort(id);
		Sort sort = nsortService.getSort(nsort.getSortId());
		String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), sort.getUserName());
		if(result!=null){
			return result;
		}
		log.info("{} delete Pub NsortName {}", sort.getUserName(), nsort.getNsortName());
		if (nsortService.isHasChildNsort(id)) {
			throw new PermissionException("发现三级分类，请先删除三级分类再删除二级分类！", EntityCodes.SORT);
		}
		if (nsortService.isHasChildNsortBrand(id)) {
			throw new PermissionException("发现三级分类有对应的品牌，请先删除三级分类下的品牌再删除三级分类！", EntityCodes.SORT);
		}
		nsortService.delete(id);
		saveMessage(request, ResourceBundleHelper.getDeleteString());
		return PathResolver.getPath(request, FowardPage.NSORT_LIST_QUERY) + "?sortId=" + nsort.getSortId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.legendshop.core.base.AdminController#load(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value = "/load")
	public String load(HttpServletRequest request, HttpServletResponse response) {

		Nsort nsort = new Nsort();
		long sortId=ServletRequestUtils.getLongParameter(request, "sortId", -1);
		if(sortId!=-1){
			nsort.setSortId(sortId);
		}
		long parentNsortId=ServletRequestUtils.getLongParameter(request, "parentNsortId", -1);		
		if(parentNsortId!=-1){			
			nsort.setParentNsortId(parentNsortId);
		}
		
		

		request.setAttribute("bean", nsort);
		
		return PathResolver.getPath(request, BackPage.NSORT_EDIT_PAGE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.legendshop.core.base.AdminController#update(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.Object)
	 */
	@Override
	@RequestMapping(value = "/update/{id}")
	public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
		checkNullable("nsort Id", id);
		String result = checkLogin(request,UserManager.getUsername(request));
		if(result != null){
			return result;
		}
		Nsort nsort = nsortService.getNsortById(id);
		request.setAttribute("bean", nsort);
		return PathResolver.getPath(request, BackPage.NSORT_EDIT_PAGE);
	}

	/**
	 * Append brand.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param id
	 *            the id
	 * @return the string
	 */
	@RequestMapping(value = "/appendBrand/{id}")
	public String appendBrand(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
		request.setAttribute("nsortId", id);
		return PathResolver.getPath(request, BackPage.NSORT_APPENDBRAND_PAGE);
	}

}
