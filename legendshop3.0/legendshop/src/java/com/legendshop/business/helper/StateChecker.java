/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.helper;

import javax.servlet.http.HttpServletRequest;

import com.legendshop.command.framework.State;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.exception.InternalException;
import com.legendshop.core.helper.Checker;
import com.legendshop.model.UserMessages;

/**
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * ------------------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ------------------------------------------------------------------------------------
 * 
 * 官方网站：http://www.legendesign.net
 */
public class StateChecker implements Checker<State> {

	/* (non-Javadoc)
	 * @see com.legendshop.core.helper.Checker#check(java.lang.Object, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public boolean check(State state, HttpServletRequest request) {

		if(!state.isOK()){
			UserMessages uem = new UserMessages();
			uem.setCode(state.getErrCode());
			uem.setTitle("系统状态异常");
			if(state.getThrowable() != null){
				uem.setDesc(state.getThrowable().getMessage());
			}
			request.setAttribute(UserMessages.MESSAGE_KEY, uem);
			throw new InternalException("State Check Failr", EntityCodes.SYSTEM);
		}

		return true;
	}

}
