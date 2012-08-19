/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.permission.command;

import java.util.List;
import java.util.Map;

import com.legendshop.permission.common.ErrorCode;
import com.legendshop.permission.dao.RoleDao;
import com.legendshop.command.framework.AbstractCommand;
import com.legendshop.command.framework.JCFException;
import com.legendshop.model.entity.UserRole;
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
public class SaveRolesToUser extends AbstractCommand
{
	
	/** The dao. */
	private RoleDao dao;
	
	/**
	 * Sets the dao.
	 * 
	 * @param dao
	 *            the new dao
	 */
	public void setDao(RoleDao dao) {
		this.dao = dao;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.command.framework.Command#execute(java.util.Map, java.util.Map)
	 */
	public void execute(Map params, Map response) throws Exception
	{
		List userRoles = (List) params.get("userRoles");
		if (checkPermissions(userRoles)) {
			String msg = "SaveFunctionsToRole PARAM_ERR ,userRoles is not validated!";
			throw new JCFException(ErrorCode.PARAMETER_ERROR, msg);
		}

			for(int i=0;i<userRoles.size();i++){
				UserRole userRole=(UserRole)userRoles.get(i);
				dao.save(userRole);
				if(i%50==0){
					dao.flush();
					dao.clear();
				}
			}
	
	}
	
	/**
	 * Check permissions.
	 * 
	 * @param userRoles
	 *            the user roles
	 * @return true, if successful
	 */
	private boolean checkPermissions(List userRoles){
		return AppUtils.isBlank(userRoles);
			
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
