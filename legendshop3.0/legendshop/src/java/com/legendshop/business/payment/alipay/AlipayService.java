/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.payment.alipay;

/**
 *类名：alipay_service
 *功能：支付宝外部服务接口控制
 *详细：该页面是请求参数核心处理文件，不需要修改
 *版本：3.1
 *修改日期：2010-11-24
 *说明：
 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.legendshop.business.payment.alipay.config.AlipayConfig;
import com.legendshop.business.payment.alipay.util.AlipaySubmit;

/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * -----------------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * -----------------------------------------------------------------------------------
 * 
 * 官方网站：http://www.legendesign.net
 */
public class AlipayService {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(AlipayService.class);

	/** 支付宝提供给商户的服务接入网关URL(新). */
	public static final String ALIPAY_GATEWAY = "https://mapi.alipay.com/gateway.do?";

	/** The Constant ALIPAY_GATEWAY_OLD. */
	public static final String ALIPAY_GATEWAY_OLD = "https://www.alipay.com/cooperate/gateway.do?";

	/**
	 * 构造确认发货接口.
	 * 
	 * @param sParaTemp
	 *            请求参数集合
	 * @param validateKey
	 *            the validate key
	 * @return 支付宝返回XML处理结果
	 * @throws Exception
	 *             the exception
	 */
	public static String send_goods_confirm_by_platform(Map<String, String> sParaTemp, String validateKey)
			throws Exception {
		// 增加基本配置
		sParaTemp.put("service", "send_goods_confirm_by_platform");
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		return AlipaySubmit.sendPostInfo(sParaTemp, ALIPAY_GATEWAY, validateKey);
	}

	/**
	 * 构造即时到帐接口.
	 * 
	 * @param sParaTemp
	 *            请求参数集合
	 * @param validateKey
	 *            the validate key
	 * @return 表单提交HTML信息
	 */
	public static String create_direct_pay_by_user(Map<String, String> sParaTemp, String validateKey) {

		// 增加基本配置
		sParaTemp.put("service", "create_direct_pay_by_user");
		sParaTemp.put("return_url", AlipayConfig.return_url);
		sParaTemp.put("notify_url", AlipayConfig.notify_url);
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		String strButtonName = "确认";

		return AlipaySubmit.buildForm(sParaTemp, ALIPAY_GATEWAY, "post", strButtonName, validateKey);
	}

	/**
	 * 构造标准双接口.
	 * 
	 * @param sParaTemp
	 *            请求参数集合
	 * @param validateKey
	 *            the validate key
	 * @return 表单提交HTML信息
	 */
	public static String trade_create_by_buyer(Map<String, String> sParaTemp, String validateKey) {

		// 增加基本配置
		sParaTemp.put("service", "trade_create_by_buyer");
		sParaTemp.put("return_url", AlipayConfig.return_url);
		sParaTemp.put("notify_url", AlipayConfig.notify_url);
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);

		String strButtonName = "确认";

		return AlipaySubmit.buildForm(sParaTemp, ALIPAY_GATEWAY, "get", strButtonName, validateKey);
	}

	/**
	 * 用于防钓鱼，调用接口query_timestamp来获取时间戳的处理函数 注意：远程解析XML出错，与服务器是否支持SSL等配置有关.
	 * 
	 * @param partner
	 *            the partner
	 * @return 时间戳字符串
	 */
	public static String query_timestamp(String partner) {
		try {
			// 构造访问query_timestamp接口的URL串
			String strUrl = ALIPAY_GATEWAY + "service=query_timestamp&partner=" + partner;
			StringBuffer result = new StringBuffer();
			// System.out.println("strUrl = " + strUrl);
			SAXReader reader = new SAXReader();
			Document doc = reader.read(new URL(strUrl).openStream());

			List<Node> nodeList = doc.selectNodes("//alipay/*");

			for (Node node : nodeList) {
				// 截取部分不需要解析的信息
				if (node.getName().equals("is_success") && node.getText().equals("T")) {
					// 判断是否有成功标示
					List<Node> nodeList1 = doc.selectNodes("//response/timestamp/*");
					for (Node node1 : nodeList1) {
						result.append(node1.getText());
					}
				}
			}

			return result.toString();
		} catch (Exception e) {
			log.error("query_timestamp", e);
			return "";
		}

	}
}
