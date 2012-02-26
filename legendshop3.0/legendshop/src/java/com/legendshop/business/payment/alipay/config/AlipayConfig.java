/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.payment.alipay.config;

import com.legendshop.business.service.PayTypeService;
import com.legendshop.core.ContextServiceLocator;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.page.PagerUtil;
import com.legendshop.model.entity.PayType;

/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * ----------------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------------
 * 
 * 官方网站：http://www.legendesign.net
 */
public class AlipayConfig {
	// 如何获取安全校验码和合作身份者ID
	// 1.访问支付宝商户服务中心(b.alipay.com)，然后用您的签约支付宝账号登陆.
	// 2.访问“技术服务”→“下载技术集成文档”（https://b.alipay.com/support/helperApply.htm?action=selfIntegration）
	// 3.在“自助集成帮助”中，点击“合作者身份(Partner ID)查询”、“安全校验码(Key)查询”

	// ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	// public static String partner = "";

	// 交易安全检验码，由数字和字母组成的32位字符串
	// public static String key = "";

	// 签约支付宝账号或卖家收款支付宝帐户
	// public static String seller_email = "";

	// notify_url 交易过程中服务器通知的页面 要用 http://格式的完整路径，不允许加?id=123这类自定义参数
	// public static String notify_url =
	// "http://www.xxx.cn/dj_jsp_gb/notify_url.jsp";
	/** The notify_url. */
	public static String notify_url = PropertiesUtil.getDomainName() + PagerUtil.getPath()
			+ "/jsp/alipay/notify_url.jsp";

	// 付完款后跳转的页面 要用 http://格式的完整路径，不允许加?id=123这类自定义参数
	// public static String return_url =
	// "http://localhost:8080/dj_jsp_gb/return_url.jsp";
	/** The return_url. */
	public static String return_url = PropertiesUtil.getDomainName() + PagerUtil.getPath()
			+ "/jsp/alipay/return_url.jsp";

	// 网站商品的展示地址，不允许加?id=123这类自定义参数
	/** The show_url. */
	public static String show_url = PropertiesUtil.getDomainName() + "/order.do";

	// 收款方名称，如：公司名称、网站名称、收款人姓名等
	// public static String mainname = "收款方名称";
	// ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

	// 字符编码格式 目前支持 gbk 或 utf-8
	/** The input_charset. */
	public static String input_charset = "UTF-8";

	// 签名方式 不需修改
	/** The sign_type. */
	public static String sign_type = "MD5";

	// 访问模式,根据自己的服务器是否支持ssl访问，若支持请选择https；若不支持请选择http
	/** The transport. */
	public static String transport = "http";

	/**
	 * Gets the pay type.
	 * 
	 * @param userName
	 *            the user name
	 * @param payTypeId
	 *            the pay type id
	 * @return the pay type
	 */
	public static PayType getPayType(String userName, Integer payTypeId) {
		PayTypeService payTypeServiceImpl = (PayTypeService) ContextServiceLocator.getInstance().getBean("payTypeService");
		return payTypeServiceImpl.getPayTypeByIdAndName(userName, payTypeId);
	}
}
