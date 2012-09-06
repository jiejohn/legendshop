/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import com.legendshop.business.helper.impl.CookiesSSOHandler;
import com.legendshop.core.exception.ApplicationException;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.util.ContextServiceLocator;

/**
 * The Class LegendFilter.
 */
public class LegendFilter extends OncePerRequestFilter{

	/** The log. */
	private static Logger log = LoggerFactory.getLogger(LegendFilter.class);
   
	private List<Handler> handlers; 
	
	private String SYSTEMINTERCEPTOR_EXECUTED="SYSTEMINTERCEPTOR_EXECUTED";
	
	/* (non-Javadoc)
	 * @see org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		try {
			if(supportURL(request.getRequestURI()) && !isExecuted(request) ){
				for (Handler handlerProcessor : handlers) {
					handlerProcessor.handle(request,response,null);
				}
			}
			chain.doFilter(request, response);
		} catch (Exception e) {
			throw new ApplicationException(e, "LegendFilter Fail", EntityCodes.SYSTEM);
		}
		
	}

	  
	  private boolean isExecuted(HttpServletRequest request){
		  String result = (String)request.getAttribute(SYSTEMINTERCEPTOR_EXECUTED);
		  if(result == null){
			  request.setAttribute(SYSTEMINTERCEPTOR_EXECUTED, "Y");
			  return false;
		  }
		  return true;
	  }
	  
	  /**
	   * 不包括有后缀的URL,只是支持Controller的动作（不带后缀）
	   * @param url
	   * @return
	   */
	  private boolean supportURL(String url){
		  if(url == null){
			  return false;
		  }
		  return url.indexOf(".") <0;
	  }
	  
		
	public void setHandlers(List<Handler> handlers) {
			this.handlers = handlers;
		}

	/* (non-Javadoc)
	 * @see org.springframework.web.filter.GenericFilterBean#destroy()
	 */
	@Override
	public void destroy() {
		
	}

	@Override
	protected void initFilterBean() throws ServletException {
		System.out.println("LegendFilter initFilterBean");
		handlers = new ArrayList<Handler>();
		if (ContextServiceLocator.getInstance().containsBean("cookiesSSOHandler")) {
			CookiesSSOHandler handler = (CookiesSSOHandler) ContextServiceLocator.getInstance().getBean(
					"cookiesSSOHandler");
			handlers.add(handler);
		}
	}
	
}
