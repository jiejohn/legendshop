/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.dwr;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.directwebremoting.convert.BaseV20Converter;
import org.directwebremoting.dwrp.SimpleOutboundVariable;
import org.directwebremoting.extend.InboundContext;
import org.directwebremoting.extend.InboundVariable;
import org.directwebremoting.extend.MarshallException;
import org.directwebremoting.extend.OutboundContext;
import org.directwebremoting.extend.OutboundVariable;

import com.legendshop.util.StringUtil;

/**
 * 
 * 日期转换器
 */
public class CalendarConverter extends BaseV20Converter {
	
	/** The df. */
	private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * Conver to date.
	 * 
	 * @param dateString
	 *            the date string
	 * @return the date
	 * @throws ParseException
	 *             the parse exception
	 */
	private Date converToDate(String dateString) throws ParseException {
		if (StringUtil.isNotEmpty(dateString)) {
			return df.parse(dateString);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.directwebremoting.extend.Converter#convertInbound(java.lang.Class, org.directwebremoting.extend.InboundVariable, org.directwebremoting.extend.InboundContext)
	 */
	@Override
	public Object convertInbound(Class clazz, InboundVariable inboundVariable, InboundContext inboundContext)
			throws MarshallException {
		if (Calendar.class.equals(clazz)) {
			try {
				Calendar ca = Calendar.getInstance();
				ca.setTime(converToDate(inboundVariable.getValue()));
				return ca;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	/* (non-Javadoc)
	 * @see org.directwebremoting.extend.Converter#convertOutbound(java.lang.Object, org.directwebremoting.extend.OutboundContext)
	 */
	@Override
	public OutboundVariable convertOutbound(Object object, OutboundContext outboundVariable) throws MarshallException {

		String returnValue = null;
		if (object instanceof Calendar) {
			Calendar calendar = (Calendar) object;
			returnValue = df.format(calendar.getTime());
		}
		return new SimpleOutboundVariable(returnValue, outboundVariable, true);
	}
}
