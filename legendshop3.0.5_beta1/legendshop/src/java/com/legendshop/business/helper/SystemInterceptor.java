package com.legendshop.business.helper;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SystemInterceptor extends HandlerInterceptorAdapter {
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(SystemInterceptor.class);
	
	private List<Handler> handlers; 
	
	private String SYSTEMINTERCEPTOR_EXECUTED="SYSTEMINTERCEPTOR_EXECUTED";
	
	  @Override  
	    public boolean preHandle(HttpServletRequest request,  
	            HttpServletResponse response, Object handler) throws Exception {
			if(supportURL(request.getRequestURI()) && !isExecuted(request) ){
				for (Handler handlerProcessor : handlers) {
					handlerProcessor.handle(request,response,handler);
				}
			}
			
		  return super.preHandle(request, response, handler);  
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

}
