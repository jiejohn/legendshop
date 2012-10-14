package com.legendshop.business.process.event;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.event.processor.ThreadProcessor;
import com.legendshop.model.MailInfo;

public class SendMailProcessor extends ThreadProcessor<MailInfo>{
	private  Logger log = LoggerFactory.getLogger(SendMailProcessor.class);
	private  JavaMailSenderImpl javaMailSender;


	@Override
	public void process(MailInfo task) {
		log.info("SendMailProcessor process calling, task= " + task);
		if (log.isDebugEnabled()) {
			log.debug("send mail to {} ,subject is {}", new Object[] {task.getTo(),task.getSubject() });
		}
		try {
			sendHTMLMail(task.getTo(),task.getSubject(),task.getText());
		} catch (Exception e) {
			log.error("send mail fail for ",e);
		}
	}

	/**
	 * Send html mail.
	 * 
	 * @param to
	 *            the to
	 * @param subject
	 *            the subject
	 * @param text
	 *            the text
	 * @throws MessagingException
	 *             the messaging exception
	 */
	private void sendHTMLMail(String to, String subject, String text) throws MessagingException {
		configJavaMailSender();
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		helper.setFrom(PropertiesUtil.getObject(ParameterEnum.MAIL_NAME, String.class));
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(text, true);
		javaMailSender.send(message);
	}
	
	/**
	 * Config java mail sender.
	 */
	private void configJavaMailSender() {
		Boolean changed = PropertiesUtil.getObject(ParameterEnum.MAIL_PROPERTIES_CHANGED, Boolean.class);
		if (changed == null || changed) {
			javaMailSender.setDefaultEncoding("UTF-8");
			javaMailSender.setHost(PropertiesUtil.getObject(ParameterEnum.MAIL_HOST, String.class));
			javaMailSender.setPort(PropertiesUtil.getObject(ParameterEnum.MAIL_PORT, Integer.class));
			String mailname = PropertiesUtil.getObject(ParameterEnum.MAIL_NAME, String.class);
			javaMailSender.setUsername(mailname.substring(0, mailname.indexOf("@")));
			javaMailSender.setPassword(PropertiesUtil.getObject(ParameterEnum.MAIL_PASSWORD, String.class));
			Properties properties = new Properties();
			properties.setProperty("mail.smtp.auth", (PropertiesUtil.getObject(ParameterEnum.MAIL_STMP_AUTH,
					Boolean.class)).toString());
			properties.setProperty("mail.smtp.timeout", PropertiesUtil.getObject(ParameterEnum.MAIL_STMP_TIMEOUT,
					String.class));
			javaMailSender.setJavaMailProperties(properties);
			log.info("Configuration Mail Sender successful Host: {}, Username {}, mail.smtp.auth: {}", new Object[] {
					javaMailSender.getHost(), javaMailSender.getUsername(),
					javaMailSender.getJavaMailProperties().get("mail.smtp.auth") });
			PropertiesUtil.setObject(ParameterEnum.MAIL_PROPERTIES_CHANGED, Boolean.FALSE);
		}

	}
	
	public void setJavaMailSender(JavaMailSenderImpl javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	
}
