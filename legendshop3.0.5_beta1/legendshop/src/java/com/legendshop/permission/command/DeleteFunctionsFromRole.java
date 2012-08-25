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

import com.legendshop.command.framework.AbstractCommand;
import com.legendshop.command.framework.JCFException;
import com.legendshop.permission.dao.FunctionDao;
/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
public class DeleteFunctionsFromRole extends AbstractCommand
{
	
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
		dao.DeletePermissionByFunctionId((List) params.get("permissions"));
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
