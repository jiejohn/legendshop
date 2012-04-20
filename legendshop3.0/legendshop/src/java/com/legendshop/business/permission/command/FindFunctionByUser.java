/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.permission.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.legendshop.permission.dao.FunctionDao;
import com.legendshop.permission.dao.RoleDao;
import com.legendshop.command.framework.AbstractCommand;
import com.legendshop.command.framework.JCFException;
import com.legendshop.model.entity.Function;
import com.legendshop.model.entity.Role;
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
public class FindFunctionByUser extends AbstractCommand
{
	
	/** The dao. */
	private FunctionDao dao;
	
	/** The role dao. */
	private RoleDao roleDao;
	
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
            List roles=roleDao.FindRoleByUserId((String) params.get("userId")); //先查找该用户的角色
            if(!AppUtils.isBlank(roles)){
            	response.put("functions",findFunctionByRole(roles));
            }else{
            	response.put("functions",null);
            }
	}
	
	/**
	 * Find function by role.
	 * 
	 * @param roles
	 *            the roles
	 * @return the list
	 */
	public List findFunctionByRole(List roles)
	{
		Map functionMap=new HashMap();
		List functionList=new ArrayList();
		for (int i=0;i<roles.size();i++){
			Role role = (Role) roles.get(i);
			if(!AppUtils.isBlank(role.getId())){
				List fs=dao.FindFunctionByRoleId(role.getId());
				if(!AppUtils.isBlank(fs))
				    for(int j=0;j<fs.size();j++){
						 Function f= (Function) fs.get(j);
						 if(!functionMap.containsKey(f.getId())){
							 functionMap.put(f.getId(), null);
							 functionList.add(f);
						 }
	
					}

	
			}
			
		}
		return functionList;
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

	/**
	 * Sets the role dao.
	 * 
	 * @param roleDao
	 *            the new role dao
	 */
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}


}
