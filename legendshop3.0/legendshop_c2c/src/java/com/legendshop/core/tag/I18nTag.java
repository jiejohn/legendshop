/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.core.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.DynamicAttributes;

import org.springframework.web.servlet.LocaleResolver;

import com.legendshop.core.AttributeKeys;
import com.legendshop.core.helper.ResourceBundleHelper;

/**
 * The Class I18nTag.
 */
public class I18nTag extends LegendShopTag implements DynamicAttributes {
	
	/** The locale resolver. */
	private static LocaleResolver localeResolver;
	
	/** The key. */
	private String key;
	
	/** The params. */
	private final List<Object> params = new ArrayList<Object>();

	/**
	 * Instantiates a new i18n tag.
	 */
	public I18nTag() {
		if(localeResolver == null){
			localeResolver = (LocaleResolver) getBean(AttributeKeys.LOCALE_RESOLVER);
		}
	}

	/**
	 * Do tag.
	 * 
	 * @throws JspException
	 *             the jsp exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @see javax.servlet.jsp.tagext.SimpleTagSupport#doTag()
	 */
	@Override
	public void doTag() throws JspException, IOException {
		Locale locale = localeResolver.resolveLocale(this.request());
		if (this.params.size() == 0) {
			String message = ResourceBundleHelper.getString(locale,this.key);

			this.write(message);
		}
		else {
			String[] array = new String[params.size()];
			for (int i = 0; i < params.size(); i++) {
				String value = (String)params.get(i);
				if(value!=null && value.startsWith(AttributeKeys.LOCALE_MESSAGE_PREFIX)){
					array[i] = ResourceBundleHelper.getString(locale,value.substring(AttributeKeys.LOCALE_MESSAGE_PREFIX.length()));
				}else{
					array[i] = value;
				}
			}
			String message = ResourceBundleHelper.getString(locale,this.key, array);

			this.write(message);
		}
	}

	/**
	 * Sets the key.
	 * 
	 * @param key
	 *            the message to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Sets the dynamic attribute.
	 * 
	 * @param uri
	 *            the uri
	 * @param localName
	 *            the local name
	 * @param value
	 *            the value
	 * @throws JspException
	 *             the jsp exception
	 * @see javax.servlet.jsp.tagext.DynamicAttributes#setDynamicAttribute(java.lang.String,
	 *      java.lang.String, java.lang.Object)
	 */
	public void setDynamicAttribute(String uri, String localName, Object value) throws JspException {
		params.add(value);
	}
}
