/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.permission.form;

import com.legendshop.model.entity.User;

/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
public class UsersForm extends BaseValidatorForm{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -840523994012455010L;
    
    /** The user. */
    public User user=new User();

/**
 * Gets the user.
 * 
 * @return the user
 */
public User getUser() {
	return user;
}

/**
 * Sets the user.
 * 
 * @param user
 *            the new user
 */
public void setUser(User user) {
	this.user = user;
}


}
