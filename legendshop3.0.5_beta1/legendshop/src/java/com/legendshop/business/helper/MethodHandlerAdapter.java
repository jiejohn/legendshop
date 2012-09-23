package com.legendshop.business.helper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 */
public class MethodHandlerAdapter extends AnnotationMethodHandlerAdapter {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		String unAuthMessage = (String) request.getSession().getServletContext()
//				.getAttribute(AttributeKeys.UN_AUTH_MSG);
//		if (LicenseEnum.UN_AUTH.name().equals(unAuthMessage)) {
//			throw new PermissionException(unAuthMessage,EntityCodes.RIGHT);
//		}
		return super.handle(request, response, handler);
	}
}
