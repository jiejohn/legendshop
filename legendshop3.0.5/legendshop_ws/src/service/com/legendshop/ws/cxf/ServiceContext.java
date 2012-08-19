/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.ws.cxf;

import java.util.HashMap;
import java.util.Map;


/**
 * 服务上下文.
 */
public class ServiceContext {
    
    /** The Constant context. */
    private static final ThreadLocal<ServiceContext> context = new ThreadLocal<ServiceContext>();

    /** The attributes. */
    private Map<String, Object> attributes = null;
    

    /** The client. */
    protected ServiceClient client;
    
    
    /** 是否已经处理完成. */
    protected boolean finished;
    
    /**
	 * Gets the or create.
	 * 
	 * @return the or create
	 */
    public static ServiceContext getOrCreate(){
        ServiceContext ctx = context.get();
        if(null == ctx){
            ctx = new ServiceContext();
            context.set(ctx);
        }
        return ctx;
    }
    
    /**
	 * Gets the.
	 * 
	 * @return the service context
	 */
    public static ServiceContext get(){
        return context.get();
    }
    
    /**
	 * Sets the.
	 * 
	 * @param ctx
	 *            the ctx
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 */
    public static void set(ServiceContext ctx) throws IllegalAccessException {
        if(null != get()){
            throw new IllegalAccessException("can't set the context again");
        }
        context.set(ctx);
    }
    
    /**
	 * Reset.
	 */
    public static void reset(){
        context.set(null);
    }
    

    /**
	 * Gets the client.
	 * 
	 * @return the client
	 */
    public ServiceClient getClient() {
        if(null == client){
            client = ServiceClient.UNKNOW;
        }
        return client;
    }

    /**
	 * Sets the client.
	 * 
	 * @param client
	 *            the new client
	 */
    public void setClient(ServiceClient client) {
        this.client = client;
    }
  

    /**
	 * Checks if is finished.
	 * 
	 * @return true, if is finished
	 */
    public boolean isFinished() {
        return finished;
    }
    
    /**
	 * Sets the finished.
	 * 
	 * @param finished
	 *            the new finished
	 */
    public void setFinished(boolean finished) {
        this.finished = finished;
    }
    
    /**
	 * Sets the attribute.
	 * 
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 */
    public void setAttribute(String key,Object value){
        getAttributes().put(key, value);
    }
    
    /**
	 * Gets the attribute.
	 * 
	 * @param key
	 *            the key
	 * @return the attribute
	 */
    public Object getAttribute(String key){
        return getAttributes().get(key);
    }
    
    /**
	 * Gets the attribute.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param type
	 *            the type
	 * @param key
	 *            the key
	 * @return the attribute
	 */
    @SuppressWarnings("unchecked")
    public <T> T getAttribute(Class<T> type,String key){
        return (T)getAttribute(key);
    }
    
    /**
	 * Gets the attributes.
	 * 
	 * @return the attributes
	 */
    public Map<String, Object> getAttributes() {
        if(null == attributes){
            attributes = new HashMap<String, Object>();
        }
        return attributes;
    }

    /**
	 * Sets the attributes.
	 * 
	 * @param attributes
	 *            the attributes
	 */
    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
}
