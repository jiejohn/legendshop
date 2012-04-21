/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.group.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.legendshop.core.base.BaseController;

/**
 * The Class GroupController.
 */
@Controller
@RequestMapping("/group")
public class GroupController extends BaseController {
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(GroupController.class);

	@RequestMapping("/index")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		log.debug("Index starting calling");
		System.out.println("123123");
		return "/group/default/index";
//		return PathResolver.getPath(request, null);		
	}
	
	@RequestMapping(value = "/view/{id}")
	public String view(HttpServletRequest request, HttpServletResponse response,@PathVariable String id) {
		log.debug("view starting calling");
		return "/group/default/view";
	}
	
	@RequestMapping("/sort")
	public String sort(HttpServletRequest request, HttpServletResponse response) {
		log.debug("sort starting calling");
		return "/group/default/sort";
	}
	
	@RequestMapping("/question")
	public String question(HttpServletRequest request, HttpServletResponse response) {
		log.debug("question starting calling");
		return "/group/default/question";
	}
}
