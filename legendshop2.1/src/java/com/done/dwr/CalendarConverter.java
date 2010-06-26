package com.done.dwr;

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

import bingosoft.jcf.util.StringUtil;
/**
 * 2007-9-24 上午11:15:50
 * Author:Emil Lu(LuLiang@bingosoft.net)
 * ReadMe:
 * ChangeLog:
 */
public class CalendarConverter extends BaseV20Converter {
	private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	private Date converToDate(String dateString) throws ParseException {
		if(StringUtil.isNotEmpty(dateString)) {
			return df.parse(dateString);
		}
		return null;
	}

	public Object convertInbound(Class clazz, InboundVariable inboundVariable, InboundContext inboundContext) throws MarshallException {
		if(Calendar.class.equals(clazz)) {
			try {
				Calendar ca = Calendar.getInstance();
				ca.setTime(converToDate(inboundVariable.getValue()));
				return ca;
			} catch (ParseException e) {
				// TODO WORK_MODE_AUTO-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}

	public OutboundVariable convertOutbound(Object object, OutboundContext outboundVariable) throws MarshallException {
		
		String returnValue = null;
		if(object instanceof Calendar) {
			Calendar calendar = (Calendar) object;
			returnValue = df.format(calendar.getTime());
		}
		return new SimpleOutboundVariable(returnValue, outboundVariable, true);
	}
}
