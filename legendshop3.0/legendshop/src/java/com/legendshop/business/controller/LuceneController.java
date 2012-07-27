/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.controller;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.legendshop.business.common.page.BackPage;
import com.legendshop.business.search.LuceneReindexer;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.search.LuceneReindexArgs;

/**
 * Lucene控制器.
 */
@Controller
@RequestMapping("/system/lucene")
public class LuceneController extends BaseController {
	
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(LuceneController.class);

	/** The lucene reindexer. */
	@Autowired
	private LuceneReindexer luceneReindexer;

	/**
	 * Query.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param curPageNO
	 *            the cur page no
	 * @return the string
	 */
	@RequestMapping("/query")
	public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO) {
		return PathResolver.getPath(BackPage.LUCENE_PAGE);
	}

	/**
	 * Reindex.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping("/reindex")
	public String reindex(HttpServletRequest request, HttpServletResponse response) {

		LuceneReindexArgs args = this.buildReindexArgs(request);
		boolean recreate = "recreate".equals(request.getParameter("indexOperationType"));
		log.info("reindex starting, recreate {} ", recreate);
		luceneReindexer.startBackgroundProcess(args, recreate);
		luceneReindexer.list();
		saveMessage(request, ResourceBundleHelper.getSucessfulString());
		return PathResolver.getPath(BackPage.LUCENE_PAGE);
	}

	/**
	 * Builds the reindex args.
	 * 
	 * @param request
	 *            the request
	 * @return the lucene reindex args
	 */
	private LuceneReindexArgs buildReindexArgs(HttpServletRequest request) {
		Date fromDate = this.buildDateFromRequest(request, "from");
		Date toDate = this.buildDateFromRequest(request, "to");

		int firstPostId = 0;
		int lastPostId = 0;
		int entityType = 0;

		if (!StringUtils.isEmpty(request.getParameter("firstPostId"))) {
			firstPostId = Integer.valueOf(request.getParameter("firstPostId"));
		}

		if (!StringUtils.isEmpty(request.getParameter("lastPostId"))) {
			lastPostId = Integer.valueOf(request.getParameter("lastPostId"));
		}

		if (!StringUtils.isEmpty(request.getParameter("entityType"))) {
			entityType = Integer.valueOf(request.getParameter("entityType"));
		}

		return new LuceneReindexArgs(fromDate, toDate, firstPostId, lastPostId, "yes".equals(request
				.getParameter("avoidDuplicatedRecords")), Integer.valueOf(request.getParameter("type")), entityType);

	}

	/**
	 * Builds the date from request.
	 * 
	 * @param request
	 *            the request
	 * @param prefix
	 *            the prefix
	 * @return the date
	 */
	private Date buildDateFromRequest(HttpServletRequest request, String prefix) {
		String day = request.getParameter(prefix + "Day");
		String month = request.getParameter(prefix + "Month");
		String year = request.getParameter(prefix + "Year");

		String hour = request.getParameter(prefix + "Hour");
		String minutes = request.getParameter(prefix + "Minutes");

		Date date = null;

		if (!StringUtils.isEmpty(day) && !StringUtils.isEmpty(month) && !StringUtils.isEmpty(year)
				&& !StringUtils.isEmpty(hour) && !StringUtils.isEmpty(minutes)) {
			date = new GregorianCalendar(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(year),
					Integer.parseInt(hour), Integer.parseInt(minutes), 0).getTime();
		}

		return date;
	}

}
