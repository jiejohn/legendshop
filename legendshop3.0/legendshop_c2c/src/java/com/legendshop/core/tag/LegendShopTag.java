/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.core.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.legendshop.core.ContextServiceLocator;

/**
 * The Class LegendShopTag.
 */
public abstract class LegendShopTag extends SimpleTagSupport {
	
	/**
	 * Request.
	 * 
	 * @return the http servlet request
	 */
	protected HttpServletRequest request() {
		return (HttpServletRequest)this.pageContext().getRequest();
	}
	
	/**
	 * Sets the attribute.
	 * 
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 */
	protected void setAttribute(String key, Object value) {
		this.request().setAttribute(key, value);
	}
	
	/**
	 * Response.
	 * 
	 * @return the http servlet response
	 */
	protected HttpServletResponse response() {
		return (HttpServletResponse)this.pageContext().getResponse();
	}
	
	/**
	 * Write.
	 * 
	 * @param content
	 *            the content
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	protected void write(String content) throws IOException {
		this.pageContext().getOut().write(content);
	}
	
	/**
	 * Invoke jsp body.
	 * 
	 * @throws JspException
	 *             the jsp exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	protected void invokeJspBody() throws JspException, IOException {
		this.getJspBody().invoke(this.pageContext().getOut());
	}
	
	/**
	 * Gets the bean.
	 * 
	 * @param beanId
	 *            the bean id
	 * @return the bean
	 */
	protected Object getBean(String beanId) {
			return ContextServiceLocator.getInstance().getBean(beanId);
	}
	
	/**
	 * Gets the bean.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param c
	 *            the c
	 * @return the bean
	 */
	@SuppressWarnings("unchecked")
	protected <T> T getBean(Class<T> c) {
		return (T)this.getBean(c.getName());
	}
	
	/**
	 * Page context.
	 * 
	 * @return the page context
	 */
	protected PageContext pageContext() {
		return (PageContext)this.getJspContext();
	}
}
