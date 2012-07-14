/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.legendshop.business.processor.pay.PaymentProcessor;
import com.legendshop.business.service.PaymentService;

/**
 * 支付宝双接口支付接口
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * ----------------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------------
 * 
 * 官方网站：http://www.legendesign.net
 */
public class PaymentServiceImpl implements PaymentService {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);
	
	/** The payment processor. */
	private Map<Integer, PaymentProcessor> paymentProcessor;

	/**
	 * Payto.
	 * 
	 * @param shopName
	 *            店铺名称
	 * @param userName
	 *            the user name
	 * @param payTypeId
	 *            支付类型
	 * @param out_trade_no
	 *            请与贵网站订单系统中的唯一订单号匹配
	 * @param subject
	 *            订单名称，显示在支付宝收银台里的“商品名称”里，显示在支付宝的交易管理的“商品名称”的列表里。
	 * @param body
	 *            订单描述、订单详细、订单备注，显示在支付宝收银台里的“商品描述”里
	 * @param price
	 *            订单总金额，显示在支付宝收银台里的“应付总额”里
	 * @param ip
	 *            the ip
	 * @return the string
	 */
	public String payto(String shopName, String userName, Integer payTypeId, String out_trade_no, String subject,
			String body, String price, String ip) {
		log.debug("payto shopName = {},userName = {},payTypeId = {}", new Object[] { shopName, userName, payTypeId });
		return getPaymentProcessor(payTypeId).payto(shopName, userName, payTypeId, out_trade_no, subject, body, price,
				ip);

	}

	/**
	 * Gets the payment processor.
	 * 
	 * @param payTypeId
	 *            the pay type id
	 * @return the payment processor
	 */
	private PaymentProcessor getPaymentProcessor(Integer payTypeId) {
		PaymentProcessor processor = paymentProcessor.get(payTypeId);
		if (processor == null) {
			processor = paymentProcessor.get(1);
		}
		return processor;
	}

	/**
	 * Sets the payment processor.
	 * 
	 * @param paymentProcessor
	 *            the payment processor
	 */
	@Required
	public void setPaymentProcessor(Map<Integer, PaymentProcessor> paymentProcessor) {
		this.paymentProcessor = paymentProcessor;
	}
}
