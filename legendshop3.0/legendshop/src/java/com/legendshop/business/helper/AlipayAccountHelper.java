/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */

package com.legendshop.business.helper;

import java.util.List;

import com.legendshop.util.AppUtils;

/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * ------------------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ------------------------------------------------------------------------------------
 * 
 * 官方网站：http://www.legendesign.net
 */
public class AlipayAccountHelper {
	
	/** The alipay accounts. */
	private List<AlipayAccount> alipayAccounts;

	/**
	 * 得到分润接口的字符串 totalCash:订单总数 memo:备注， 一般是seller_mail.
	 *
	 * @param totalCash the total cash
	 * @param memo the memo
	 * @return the account info
	 */
	public String getAccountInfo(String totalCash, String memo) {
		String result = "";
		if (AppUtils.isNotBlank(alipayAccounts)) {
			Double total = null;
			try {
				total = Double.valueOf(totalCash);
			} catch (Exception e) {// error happened, return
				return result;
			}

			for (AlipayAccount account : alipayAccounts) {
				result += account.getAccountInfo(total, memo);
			}
		}
		if (AppUtils.isNotBlank(result)) {// 截取去掉最后一位 “|”
			return result.substring(0, result.length() - 1);
		}
		return result;
	}

	/**
	 * Sets the alipay accounts.
	 *
	 * @param alipayAccounts the new alipay accounts
	 */
	public void setAlipayAccounts(List<AlipayAccount> alipayAccounts) {
		this.alipayAccounts = alipayAccounts;
	}

}
