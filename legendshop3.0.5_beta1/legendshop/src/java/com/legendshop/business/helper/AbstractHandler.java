package com.legendshop.business.helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.legendshop.core.helper.Handler;

public abstract class AbstractHandler implements Handler {

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,Exception ex)
			throws Exception {
		
	}

}
