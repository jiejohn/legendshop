/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.permission.common;

import org.apache.log4j.Logger;

import com.legendshop.command.framework.ErrorHandler;
import com.legendshop.command.framework.GoOnException;
import com.legendshop.command.framework.JCFException;
import com.legendshop.command.framework.Response;
/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
public class ProcessorErrorHandler implements ErrorHandler {

    /** The bean name. */
    private String beanName;
    
    /** The logger. */
    private static Logger logger=Logger.getLogger(ProcessorErrorHandler.class);

    /* (non-Javadoc)
     * @see com.legendshop.command.framework.ErrorHandler#handleError(com.legendshop.command.framework.Response, java.lang.Throwable)
     */
    public void handleError(Response resp, Throwable th) throws Exception{

        if (th instanceof GoOnException) {
        	logger.error("throw GoOnException 异常", th);
        }else if(th instanceof JCFException){
            logger.error("throw JCFException 异常",th);
			resp.setReturnCode(Response.APPLICATION_LEVEL_ERROR);
			resp.getState().setErrCode("A JCFException happened");
        	throw (JCFException)th;
        }
        else{
        	logger.error(th+" error occured, forcing a stop");
			resp.setReturnCode(Response.APPLICATION_LEVEL_ERROR);
			resp.getState().setErrCode("A Exception happened");
			if(th instanceof Exception)
        	       throw (Exception)th;
        }

    }

    /* (non-Javadoc)
     * @see org.springframework.beans.factory.BeanNameAware#setBeanName(java.lang.String)
     */
    public void setBeanName(String beanName) {
        this.beanName = beanName;

    }

}