/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.legendshop.business.common.page.BackPage;
import com.legendshop.business.service.PaymentService;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.exception.BusinessException;
import com.legendshop.core.exception.ErrorCodes;
import com.legendshop.util.AppUtils;

/**
 * 支付控制器.
 */
@Controller
@RequestMapping("/payment")
public class PaymentController extends BaseController {
	
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(PaymentController.class);
	
	/** The payment service. */
	@Autowired
	private PaymentService paymentService;

	/**
	 * Payment.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping("/payto")
	public String payment(HttpServletRequest request, HttpServletResponse response) {
		String userName = UserManager.getUsername(request.getSession());
		if (AppUtils.isBlank(userName)) {
			throw new RuntimeException("not logined yet!");
		}
		String shopName = request.getParameter("shopName");
		checkNull("shopName", shopName);
		String payTypeId = request.getParameter("payTypeId");
		checkNull("payTypeId", payTypeId);
		// 必填参数
		// UtilDate date = new UtilDate();//调取支付宝工具类生成订单号
		// String out_trade_no = date.getOrderNum();//请与贵网站订单系统中的唯一订单号匹配
		String out_trade_no = request.getParameter("subNumber");
		checkNull("out_trade_no", out_trade_no);
		// 订单名称，显示在支付宝收银台里的“商品名称”里，显示在支付宝的交易管理的“商品名称”的列表里。
		String subject = request.getParameter("aliorder"); // prodName, 作为标题
		// 订单描述、订单详细、订单备注，显示在支付宝收银台里的“商品描述”里
		String body = request.getParameter("alibody");
		// 订单总金额，显示在支付宝收银台里的“应付总额”里
		String price = request.getParameter("alimoney");
		checkNull("price", price);

		String payment_result = paymentService.payto(shopName, userName, Integer.valueOf(payTypeId), out_trade_no,
				subject, body, price, request.getRemoteAddr());
		log.debug("payment result = {}", payment_result);
		// System.out.println("payment_result = " + payment_result);
		request.setAttribute("payment_result", payment_result);
		return PathResolver.getPath(BackPage.PAY_PAGE);
	}

	/**
	 * Check null.
	 * 
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 */
	private void checkNull(String name, String value) {
		if (AppUtils.isBlank(value)) {
			throw new BusinessException(name + " can no be null", ErrorCodes.NON_NULLABLE);
		}
	}

}
