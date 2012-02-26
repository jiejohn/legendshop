/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.permission.command;

import java.util.Map;

import com.legendshop.business.permission.common.ErrorCode;
import com.legendshop.business.permission.dao.UserDao;
import com.legendshop.command.framework.AbstractCommand;
import com.legendshop.core.exception.JCFException;
import com.legendshop.model.entity.User;
import com.legendshop.util.AppUtils;

/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
public class UpdateUser extends AbstractCommand
{
	
	/** The dao. */
	private UserDao dao;
	
	/**
	 * Sets the dao.
	 * 
	 * @param dao
	 *            the new dao
	 */
	public void setDao(UserDao dao) {
		this.dao = dao;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.command.framework.Command#execute(java.util.Map, java.util.Map)
	 */
	public void execute(Map params, Map response) throws Exception
	{
		User user = (User) params.get("user");
		if (checkUser(user)) {
			String msg = "UpdateRole PARAM_ERR ,role is not validated!";
			throw new JCFException(ErrorCode.PARAMETER_ERROR, msg);
		}
			dao.update(user);

	}
	 
 	/**
	 * 应该已在前台做好验证，此处做个例子.
	 * 
	 * @param user
	 *            the user
	 * @return true, if successful
	 */
	private boolean checkUser(User user){
		if(AppUtils.isBlank(user)||AppUtils.isBlank(user.getEnabled())||AppUtils.isBlank(user.getId())||
				AppUtils.isBlank(user.getName())||AppUtils.isBlank(user.getPassword()))
			return true;
		else
			return false;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.command.framework.Command#fini()
	 */
	public void fini() throws JCFException
	{
		
	}

	/* (non-Javadoc)
	 * @see com.legendshop.command.framework.Command#init(java.lang.String)
	 */
	public void init(String arg0) throws JCFException
	{
		
	}


}

