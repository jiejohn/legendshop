/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.ws.cxf;

/**
 * 表示服务客户端.
 */
public class ServiceClient {
    
    /** The UNKNOW. */
    public static ServiceClient UNKNOW = new ServiceClient("unknow","unknow",false);
    
    /** The LOCAL. */
    public static ServiceClient LOCAL  = new ServiceClient("local","0.0.0.0",true);
    
    /** The name. */
    protected String name;

    /** The address. */
    protected String address;
    
    /** The trust. */
    protected boolean trust;
    
    /**
	 * Instantiates a new service client.
	 */
    public ServiceClient(){
        
    }
    
    /**
	 * Instantiates a new service client.
	 * 
	 * @param name
	 *            the name
	 * @param address
	 *            the address
	 */
    public ServiceClient(String name, String address) {
        this.name = name;
        this.address = address;
    }
    
    /**
	 * Instantiates a new service client.
	 * 
	 * @param name
	 *            the name
	 * @param address
	 *            the address
	 * @param trust
	 *            the trust
	 */
    public ServiceClient(String name, String address,boolean trust) {
        this(name,address);
        this.trust = trust;
    }

    /**
	 * Gets the name.
	 * 
	 * @return the name
	 */
    public String getName() {
        return name;
    }

    /**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 */
    public void setName(String name) {
        this.name = name;
    }

    /**
	 * Gets the address.
	 * 
	 * @return the address
	 */
    public String getAddress() {
        return address;
    }

    /**
	 * Sets the address.
	 * 
	 * @param address
	 *            the new address
	 */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
	 * Checks if is trust.
	 * 
	 * @return true, if is trust
	 */
    public boolean isTrust() {
        return trust;
    }

    /**
	 * Sets the trust.
	 * 
	 * @param trust
	 *            the new trust
	 */
    public void setTrust(boolean trust) {
        this.trust = trust;
    }
}
