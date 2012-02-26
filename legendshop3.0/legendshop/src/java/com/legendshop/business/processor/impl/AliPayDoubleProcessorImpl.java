/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.processor.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Required;

import com.legendshop.business.dao.UserDetailDao;
import com.legendshop.business.payment.alipay.AlipayService;
import com.legendshop.business.payment.alipay.config.AlipayConfig;
import com.legendshop.business.processor.PaymentProcessor;
import com.legendshop.model.entity.PayType;
import com.legendshop.model.entity.UserDetail;

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
public class AliPayDoubleProcessorImpl implements PaymentProcessor {
	
	/** The user detail dao. */
	private UserDetailDao userDetailDaoImpl;

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
		// 必填参数//

		// 物流费用，即运费。
		String logistics_fee = "0.00";
		// 物流类型，三个值可选：EXPRESS（快递）、POST（平邮）、EMS（EMS）
		String logistics_type = "EXPRESS";
		// 物流支付方式，两个值可选：SELLER_PAY（卖家承担运费）、BUYER_PAY（买家承担运费）
		String logistics_payment = "SELLER_PAY";

		// 商品数量，建议默认为1，不改变值，把一次交易看成是一次下订单而非购买一件商品。
		String quantity = "1";

		PayType payType = AlipayConfig.getPayType(shopName, payTypeId);

		String seller_email = payType.getSellerEmail();
		String partner = payType.getPartner();
		String key = payType.getValidateKey();
		// 扩展参数//

		// 买家收货信息（推荐作为必填）
		// 该功能作用在于买家已经在商户网站的下单流程中填过一次收货信息，而不需要买家在支付宝的付款流程中再次填写收货信息。
		// 若要使用该功能，请至少保证receive_name、receive_address有值
		UserDetail userDetail = userDetailDaoImpl.getUserDetail(userName);

		String receive_name = userDetail.getNickName(); // 收货人姓名，如：张三
		String receive_address = userDetail.getUserAdds(); // 收货人地址，如：XX省XXX市XXX区XXX路XXX小区XXX栋XXX单元XXX号
		String receive_zip = userDetail.getUserPostcode(); // 收货人邮编，如：123456
		String receive_phone = userDetail.getUserTel(); // 收货人电话号码，如：0571-81234567
		String receive_mobile = userDetail.getUserMobile(); // 收货人手机号码，如：13312341234

		// 网站商品的展示地址，不允许加?id=123这类自定义参数
		String show_url = AlipayConfig.show_url;

		// ////////////////////////////////////////////////////////////////////////////////

		// 把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("payment_type", "1");
		sParaTemp.put("show_url", show_url);
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("subject", subject);
		sParaTemp.put("body", body);
		sParaTemp.put("price", price);
		sParaTemp.put("logistics_fee", logistics_fee);
		sParaTemp.put("logistics_type", logistics_type);
		sParaTemp.put("logistics_payment", logistics_payment);
		sParaTemp.put("quantity", quantity);
		sParaTemp.put("receive_name", receive_name);
		sParaTemp.put("receive_address", receive_address);
		sParaTemp.put("receive_zip", receive_zip);
		sParaTemp.put("receive_phone", receive_phone);
		sParaTemp.put("receive_mobile", receive_mobile);

		sParaTemp.put("partner", partner);
		sParaTemp.put("seller_email", seller_email);

		// 构造函数，生成请求URL
		return AlipayService.trade_create_by_buyer(sParaTemp, key);

	}

	/**
	 * Sets the user detail dao.
	 * 
	 * @param userDetailDaoImpl
	 *            the new user detail dao
	 */
	@Required
	public void setUserDetailDao(UserDetailDao userDetailDaoImpl) {
		this.userDetailDaoImpl = userDetailDaoImpl;
	}
}
