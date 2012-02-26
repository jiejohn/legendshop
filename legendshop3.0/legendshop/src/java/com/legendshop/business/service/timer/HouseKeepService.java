/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.timer;

/**
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * ----------------------------------------------------------------------------
 * -------- 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * --------
 * ----------------------------------------------------------------------
 * -------.
 */
public interface HouseKeepService {

	/**
	 * 将长期不登录的商城用户下线.
	 */
	public void turnOffShop();

	/**
	 * 将过期的购物车变为过时.
	 */
	public void turnOffBasket();
}
