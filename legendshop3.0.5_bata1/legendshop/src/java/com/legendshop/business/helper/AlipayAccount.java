/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.helper;

import com.legendshop.util.Arith;

/**
 * 分润帐号 type ：1 按百分比，2 实收
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------.
 */
public class AlipayAccount {

	/** The account. */
	private String account;

	/** The money. */
	private Double money;

	/** The type. */
	private int type;

	/**
	 * Gets the account info.
	 *
	 * @param totalCash 订单总数
	 * @param memo the memo
	 * @return the account info
	 */
	public String getAccountInfo(Double totalCash, String memo) {
		// royalty_parameters = "111@126.com^0.01^分润备注一|222@126.com^0.01^分润备注二" 备注是seller_email
		StringBuffer sb = new StringBuffer();
		sb.append(account).append("^").append(calculteCash(totalCash)).append("^").append(memo).append("|");
		return sb.toString();
	}

	/**
	 * Calculte cash.
	 *
	 * @param totalCash the total cash
	 * @return the double
	 */
	private Double calculteCash(Double totalCash) {
		Double result = null;
		if (1 == type) {
			result = Arith.mul(totalCash, money);
		} else {
			result = money;
		}
		return result;
	}

	/**
	 * Gets the account.
	 *
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * Sets the account.
	 *
	 * @param account the new account
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * Gets the money.
	 *
	 * @return the money
	 */
	public Double getMoney() {
		return money;
	}

	/**
	 * Sets the money.
	 *
	 * @param money the new money
	 */
	public void setMoney(Double money) {
		this.money = money;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(int type) {
		this.type = type;
	}
}
