/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.permission.common;


import com.legendshop.permission.service.RightDelegate;
import com.legendshop.core.ContextServiceLocator;
/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
public class BeanFactory { 
	
	/** The instance. */
	private static BeanFactory instance;
	
	/**
	 * Gets the single instance of BeanFactory.
	 * 
	 * @return single instance of BeanFactory
	 */
	public static BeanFactory getInstance(){
		if(instance==null)
			instance=new BeanFactory();
		return instance;
}
	
	/**
	 * Gets the bean.
	 * 
	 * @param beanName
	 *            the bean name
	 * @return the bean
	 */
	public static Object getBean(String beanName){
			return ContextServiceLocator.getInstance().getBean(beanName);
	}
	//RightDelegateImpl
	/**
	 * Gets the right delegate.
	 * 
	 * @return the right delegate
	 */
	public  RightDelegate getRightDelegate(){
		//RightDelegateTarget1
		return (RightDelegate)ContextServiceLocator.getInstance().getBean("RightDelegateImpl");
	}

}
