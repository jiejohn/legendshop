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
import com.legendshop.business.permission.dao.FunctionDao;
import com.legendshop.command.framework.AbstractCommand;
import com.legendshop.core.exception.JCFException;
import com.legendshop.model.entity.Permission;
/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
public class SaveFunctionToRole extends AbstractCommand
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5575511149689866328L;
	
	/** The dao. */
	private FunctionDao dao;
	
	/**
	 * Sets the dao.
	 * 
	 * @param dao
	 *            the new dao
	 */
	public void setDao(FunctionDao dao) {
		this.dao = dao;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.command.framework.Command#execute(java.util.Map, java.util.Map)
	 */
	public void execute(Map params, Map response) throws Exception
	{
		Permission permission = (Permission) params.get("permission");
		String result=checkPermission(permission);
		if (result!=null) {
			throw new JCFException(ErrorCode.PARAMETER_ERROR, result);
		}

			response.put("id",dao.save(permission));

	}
	
	/**
	 * Check permission.
	 * 
	 * @param permission
	 *            the permission
	 * @return the string
	 */
	private String checkPermission(Permission permission){
		if(permission.getId().getFunctionId()==null){
			return "SaveFunctionToRole PARAM_ERR ,FunctionId is NULL";
		}else if(permission.getId().getRoleId()==null){
			return "SaveFunctionToRole PARAM_ERR ,FunctionId is NULL";
		}else{
			return null;
		}
			
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
