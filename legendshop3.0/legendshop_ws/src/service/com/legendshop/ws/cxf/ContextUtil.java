/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.ws.cxf;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * The Class ContextUtil.
 * 
 * {@link ServiceContext}处理工具类
 * 
 * @author hewq 2008-12-13
 */
public final class ContextUtil {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(ContextUtil.class);
	
    /**
	 * Gets the http request.
	 * 
	 * @param context
	 *            the context
	 * @return the http request
	 */
    public static final HttpServletRequest getHttpRequest(WebServiceContext context){
        MessageContext ctx = context.getMessageContext();
        return (HttpServletRequest) ctx.get(AbstractHTTPDestination.HTTP_REQUEST);        
    }
    
    /**
	 * Gets the iP address.
	 * 
	 * @param context
	 *            the context
	 * @return the iP address
	 */
    public static final String getIPAddress(WebServiceContext context){
        return getHttpRequest(context).getRemoteAddr();
    }
    
    /**
	 * Creates the service context.
	 * 
	 * @param context
	 *            the context
	 * @return the service context
	 */
    public static final ServiceContext createServiceContext(WebServiceContext context){
        HttpServletRequest request  = getHttpRequest(context);
        return createServiceContext(request);
    }
    
    /**
	 * Creates the service context.
	 * 
	 * @param request
	 *            the request
	 * @return the service context
	 */
    public static final ServiceContext createServiceContext(HttpServletRequest request){
        ServiceContext serviceCtx   = new ServiceContext();
        ServiceClient serviceClient = null;
        String remoteHost  = request.getRemoteHost();
        String remoteAddr  = request.getRemoteAddr();
       // serviceCtx.setAttribute("UUID", UUID.randomUUID().toString());
        //log.debug("remoteAddr = {} ,UUID = {}",remoteAddr,serviceCtx.getAttribute("UUID"));
        serviceClient = new ServiceClient(remoteHost,remoteAddr);
        serviceCtx.setClient(serviceClient);
        
        return serviceCtx;        
    }
}
