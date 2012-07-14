/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.process.pay;

/**
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * ----------------------------------------------------------------------------
 * 
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * 
 * ----------------------------------------------------------------------------.
 */
public interface PaymentProcessor {
	
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
			String body, String price, String ip);
}
