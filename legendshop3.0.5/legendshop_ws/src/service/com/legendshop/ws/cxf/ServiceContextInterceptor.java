/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.ws.cxf;

import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * 在WebService调用前执行，从请求中取出客户端信息(如IP地址),初始化{@link ServiceContext}对象.
 * 
 */
public class ServiceContextInterceptor extends AbstractPhaseInterceptor<Message> {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(ServiceContextInterceptor.class);
    
    /**
	 * Instantiates a new service context interceptor.
	 */
    public ServiceContextInterceptor(){
        super(Phase.PRE_INVOKE);
    }
    
    /* (non-Javadoc)
     * @see org.apache.cxf.phase.AbstractPhaseInterceptor#handleFault(org.apache.cxf.message.Message)
     */
    @Override
	public void handleFault(Message message) {
    }
    
    /* (non-Javadoc)
     * @see org.apache.cxf.interceptor.Interceptor#handleMessage(org.apache.cxf.message.Message)
     */
    @Override
	public void handleMessage(Message message) throws Fault {
        HttpServletRequest request = (HttpServletRequest)message.get(AbstractHTTPDestination.HTTP_REQUEST);
//        System.out.println("request "+ request);    
//        System.out.println("request RealPath = "+ request.getRealPath(""));
//        System.out.println("request RemoteUser = "+ request.getRemoteUser());
//        System.out.println("request getRemoteHost = "+ request.getRemoteHost());
//        System.out.println("request RemotePort = "+ request.getRemotePort());
//        System.out.println("request RequestURI = "+ request.getRequestURI());       
//        System.out.println("request getRemoteAddr = "+ request.getRemoteAddr());
	      try {
	    	ServiceContext.reset();
	    	ServiceContext.set(ContextUtil.createServiceContext(request));
		} catch (IllegalAccessException e) {
			throw new Fault(e);
		}
	}
    
    /**
	 * 验证IP是否合法.
	 * 
	 * @param ip
	 *            the ip
	 * @return true, if successful
	 */
    public boolean validateIp(String ip){
    	if(ip==null) return false;
    	if(ip.equals("127.0.0.1")) return true;
    	return true;
    }

    
}
