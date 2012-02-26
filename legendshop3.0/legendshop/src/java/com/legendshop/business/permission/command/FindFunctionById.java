/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.permission.command;

import java.util.Map;

import com.legendshop.business.permission.dao.FunctionDao;
import com.legendshop.command.framework.AbstractCommand;
import com.legendshop.core.exception.JCFException;
import com.legendshop.model.entity.Function;
/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
public class FindFunctionById extends AbstractCommand
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
			//HQL语法
			/*
			
			String strHQL="select f from Function f where f.id=?";
			Object[] param = {id};
			List functions =  dao.find(strHQL, param);
			Function function=(Function)functions.get(0);
			 */
		
			//QBC语法
			response.put("function",(Function)dao.get(Function.class, (String) params.get("id")));

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
