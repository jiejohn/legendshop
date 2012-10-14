/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.event.impl;

import com.legendshop.business.event.EventId;
import com.legendshop.event.SystemEvent;
import com.legendshop.model.MailInfo;

public class SendMailEvent extends SystemEvent<MailInfo>{

	/**
	 * Instantiates a new user reg event.
	 * 
	 * @param source
	 *            the source
	 */
	public SendMailEvent( String to,String subject,String text) {
		super(EventId.SEND_MAIL_EVENT);
		MailInfo mail = new MailInfo();
		mail.setSubject(subject);
		mail.setText(text);
		mail.setTo(to);
		setSource(mail);
	}

}
