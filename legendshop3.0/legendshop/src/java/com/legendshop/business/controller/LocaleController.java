/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;

import com.legendshop.business.common.PageLetEnum;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.util.AppUtils;

/**
 * Locale控制器.
 */
@Controller
public class LocaleController extends BaseController {
	
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(LocaleController.class);

	/** The LANGUAGE. */
	private final String LANGUAGE = "language";

	/** The COUNTRY. */
	private final String COUNTRY = "country";

	/** The PAGE. */
	private final String PAGE = "page";

	/** The locale resolver. */
	@Autowired
	private LocaleResolver localeResolver;

	/**
	 * Change locale.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping("/changeLocale")
	public String changeLocale(HttpServletRequest request, HttpServletResponse response) {
		String language = request.getParameter(LANGUAGE);
		String country = request.getParameter(COUNTRY);
		Locale locale = null;
		log.debug("language = {}, country = {}", language, country);
		if (AppUtils.isNotBlank(language) && AppUtils.isNotBlank(country)) {
			locale = new Locale(language, country);
		} else if (AppUtils.isNotBlank(language)) {
			locale = new Locale(language, "");
		}
		if (locale != null) {
			localeResolver.setLocale(request, response, locale);
		}
		String target = request.getParameter(PAGE);
		if (AppUtils.isNotBlank(target)) {
			return PathResolver.getPath(request, target,PageLetEnum.FOWARD);
		} else {
			return PathResolver.getPath(request, PageLetEnum.INDEX_PAGE);
		}

	}

}
