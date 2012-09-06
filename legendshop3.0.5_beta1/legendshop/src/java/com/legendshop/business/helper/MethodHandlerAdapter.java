package com.legendshop.business.helper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import com.legendshop.spi.service.LoginService;

/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 */
public class MethodHandlerAdapter extends AnnotationMethodHandlerAdapter {
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(MethodHandlerAdapter.class);
	private  LoginService loginService;
	
    private boolean invalidateHttpSession = true;
	
	public void setInvalidateHttpSession(boolean invalidateHttpSession) {
		this.invalidateHttpSession = invalidateHttpSession;
	}
	
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
