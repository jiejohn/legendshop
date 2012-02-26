/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.payment.alipay.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.legendshop.business.dao.SubDao;
import com.legendshop.business.payment.alipay.config.AlipayConfig;
import com.legendshop.business.service.PayTypeService;
import com.legendshop.core.ContextServiceLocator;
import com.legendshop.model.entity.PayType;
import com.legendshop.model.entity.Sub;

/* *
 *类名：AlipayNotify
 *功能：支付宝通知处理类
 *详细：处理支付宝各接口通知返回
 *版本：3.2
 *日期：2011-03-25
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考

 *************************注意*************************
 *调试通知返回时，可查看或改写log日志的写入TXT里的数据，来检查通知返回是否正常
 */
/**
 * The Class AlipayNotify.
 */
public class AlipayNotify {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(AlipayNotify.class);

	/** HTTPS形式消息验证地址. */
	private static final String HTTPS_VERIFY_URL = "https://www.alipay.com/cooperate/gateway.do?service=notify_verify&";

	/** HTTP形式消息验证地址. */
	private static final String HTTP_VERIFY_URL = "http://notify.alipay.com/trade/notify_query.do?";

	/**
	 * 验证消息是否是支付宝发出的合法消息.
	 * 
	 * @param params
	 *            通知返回来的参数数组
	 * @param validateKey
	 *            the validate key
	 * @return 验证结果
	 */
	public static boolean verify(Map<String, String> params, String validateKey) {
		String mysign = getMysign(params, validateKey);
		String responseTxt = "true";
		String order_no = params.get("out_trade_no"); // 获取订单号
		if (order_no == null) {
			log.warn("获取订单号失败");
			return false;
		}
		SubDao subDaoImpl = (SubDao) ContextServiceLocator.getInstance().getBean("subDao");
		Sub sub = subDaoImpl.getSubBySubNumber(order_no);
		log.debug("AlipayNotify get sub result : {}", sub == null);
		if (sub != null) {
			PayTypeService payTypeServiceImpl = (PayTypeService) ContextServiceLocator.getInstance().getBean(
					"payTypeService");
			PayType payType = payTypeServiceImpl.getPayTypeById(sub.getPayId());

			if (params.get("notify_id") != null && payType != null && payType.getPartner() != null) {
				responseTxt = verifyResponse(params.get("notify_id"), payType.getPartner());
			}
		}

		String sign = "";
		if (params.get("sign") != null) {
			sign = params.get("sign");
		}

		// 写日志记录（若要调试，请取消下面两行注释）
		// String sWord = "responseTxt=" + responseTxt + "\n notify_url_log:sign=" + sign + "&mysign="
		// + mysign + "\n 返回的参数：" + AlipayCore.createLinkString(params);
		// AlipayCore.logResult(sWord);

		// 验证
		// responsetTxt的结果不是true，与服务器设置问题、合作身份者ID、notify_id一分钟失效有关
		// mysign与sign不等，与安全校验码、请求时的参数格式（如：带自定义参数等）、编码格式有关
		if (mysign.equals(sign) && responseTxt.equals("true")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 根据反馈回来的信息，生成签名结果.
	 * 
	 * @param Params
	 *            通知返回来的参数数组
	 * @param validateKey
	 *            the validate key
	 * @return 生成的签名结果
	 */
	private static String getMysign(Map<String, String> Params, String validateKey) {
		Map<String, String> sParaNew = AlipayCore.paraFilter(Params);// 过滤空值、sign与sign_type参数
		String mysign = AlipayCore.buildMysign(sParaNew, validateKey);// 获得签名结果
		return mysign;
	}

	/**
	 * 获取远程服务器ATN结果,验证返回URL.
	 * 
	 * @param notify_id
	 *            通知校验ID
	 * @param partner
	 *            the partner
	 * @return 服务器ATN结果 验证结果集： invalid命令参数不对 出现这个错误，请检测返回处理中partner和key是否为空 true
	 *         返回正确信息 false 请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
	 */
	private static String verifyResponse(String notify_id, String partner) {
		// 获取远程服务器ATN结果，验证是否是支付宝服务器发来的请求
		String transport = AlipayConfig.transport;
		String veryfy_url = "";
		if (transport.equalsIgnoreCase("https")) {
			veryfy_url = HTTPS_VERIFY_URL;
		} else {
			veryfy_url = HTTP_VERIFY_URL;
		}
		veryfy_url = veryfy_url + "partner=" + partner + "&notify_id=" + notify_id;

		return checkUrl(veryfy_url);
	}

	/**
	 * 获取远程服务器ATN结果.
	 * 
	 * @param urlvalue
	 *            指定URL路径地址
	 * @return 服务器ATN结果 验证结果集： invalid命令参数不对 出现这个错误，请检测返回处理中partner和key是否为空 true
	 *         返回正确信息 false 请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
	 */
	private static String checkUrl(String urlvalue) {
		String inputLine = "";

		try {
			URL url = new URL(urlvalue);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			inputLine = in.readLine().toString();
		} catch (Exception e) {
			e.printStackTrace();
			inputLine = "";
		}

		return inputLine;
	}
}
