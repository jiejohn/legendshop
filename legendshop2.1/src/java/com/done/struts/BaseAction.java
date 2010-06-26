package com.done.struts;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.sql.BatchUpdateException;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import bingosoft.jcf.exeception.JCFException;
import bingosoft.jcf.service.impl.ContextServiceLocator;
import bingosoft.jcf.sql.ConfigCode;
import bingosoft.jcf.util.BeanHelper;

/**
 * Author:hewq
 */
public abstract class BaseAction extends DispatchAction {
    protected Logger log = LoggerFactory.getLogger(BaseAction.class);
    private String ACTION_MESSAGE = "ACTION_MESSAGE";//错误页面
    private String TOKEN_KEY = org.apache.struts.taglib.html.Constants.TOKEN_KEY;
    private Integer MAX_FILE_SIZE = Integer.parseInt(ConfigCode.getInstance().getCode("config.MaxFileSize"));
    protected String CALL_BACK = "CALL_BACK";

    /**
     * Struts 的主调用方法,在此进行了扩展,并添加一个沙盒
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    //	static {
    //		registConverter();
    //	}
    //
    //	/**
    //	 * 设置Struts 中数字<->字符串转换，字符串为空值时,数字默认为null，而不是0. 也可以在web.xml中设置struts的参数达到相同效果，在这里设置可以防止用户漏设web.xml.
    //	 */
    //	public static void registConverter() {
    //		ConvertUtils.register(new StringConverter(), String.class);
    //		ConvertUtils.register(new IntegerConverter(null), Integer.class);
    //		ConvertUtils.register(new LongConverter(null), Long.class);
    //		ConvertUtils.register(new FloatConverter(null), Float.class);
    //		ConvertUtils.register(new DoubleConverter(null), Double.class);
    //		ConvertUtils.register(new DateConverter("yyyy-MM-dd"), Date.class);
    //	}
    /**
     * 将FormBean中的内容通过BeanUtils的copyProperties()绑定到Object中.
     * 因为BeanUtils中两个参数的顺序很容易搞错，因此封装此函数.
     */
    protected void bindEntity(ActionForm form, Object object) {
        if (form != null) {
            try {
                BeanUtils.copyProperties(object, form);
            } catch (Exception e) {
                ReflectionUtils.handleReflectionException(e);
            }
        }
    }

    /**
     * 将Object内容通过BeanUtils的copyProperties 复制到FormBean中.
     * 因为BeanUtils中两个参数的顺序很容易搞错，因此封装此函数。
     */
    protected void bindForm(ActionForm form, Object object) {
        if (object != null) {
            try {
                BeanUtils.copyProperties(form, object);
            } catch (Exception e) {
                ReflectionUtils.handleReflectionException(e);
            }
        }
    }

    protected void onInit() {
        log.info(this.getClass().getSimpleName() + "开始初始化...");
    }

    protected ActionForward handleException(ActionMapping mapping, HttpServletRequest request, String messageName,
            String message, String callBack) {
        request.setAttribute("ERROR_TITLE", messageName);
        request.setAttribute("ERROR_MESSAGE", message);
        if (callBack != null) {
            request.setAttribute("CALL_BACK", callBack);
        }
        return mapping.findForward("error");
    }

    protected ActionForward handleException(ActionMapping mapping, HttpServletRequest request, String messageName,
            String message) {
        return handleException(mapping, request, messageName, message, null);
    }

    public String getExceptionMessage(Exception e) {
        Throwable throwable = e;
        while (throwable.getCause() != null) {
            throwable = throwable.getCause();
        }
        if (throwable instanceof BatchUpdateException) {
            return "数据库错误，尝试删除子记录或注意主键是否重复";
        }
        if (throwable instanceof NullPointerException) {
            return "找不到该记录，请与管理员联系";
        }
        return throwable.getMessage();
    }

    @Override
    public final ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        //权限验证
        if (!canExecute(request)) {
            throw new UnsupportedOperationException("无法执行对应的操作,请检查权限...");
        }
        ActionForward forward = null;
        try {
            beforeExecute(request, response);
            //------------------------------------------
            if (isCancelled(request)) {
                ActionForward af = cancelled(mapping, actionForm, request, response);
                if (af != null) {
                    return af;
                }
            }
            //get dispath method name
            String mothodName = mapping.getParameter();
            //			if("execute".equals(mothodName) || "perform".equals(mothodName)){
            //				String message = messages.getMessage("dispatch.recursive", mapping.getPath());
            //				log.error(message);
            //				throw new ServletException(message);
            //			} else {
            //				if(StringUtils.isNotEmpty(mothodName)){
            //					log.debug("Call method : " + mothodName);	
            //				}
            //				forward = dispatchMethod(mapping, actionForm, request, response, mothodName);
            //			}
            forward = dispatchMethod(mapping, actionForm, request, response, mothodName);
            //------------------------------------------
            afterExecute(request, response);
        } catch (Exception ex) {
            forward = onException(mapping, actionForm, request, response, ex);
        }
        return forward;
    }

    /**
     * 执行主方法之前调用的方法
     * 
     * @param request
     * @param response
     */
    protected void beforeExecute(HttpServletRequest request, HttpServletResponse response) {
        if (log.isDebugEnabled()) {
            log.debug("Enter into " + this.getClass().getSimpleName() + "...");
        }
    }

    /**
     * 方法执行之后调用的方法
     * 
     * @param request
     * @param response
     */
    protected void afterExecute(HttpServletRequest request, HttpServletResponse response) {
        if (log.isDebugEnabled()) {
            log.debug("Out off " + this.getClass().getSimpleName() + "...");
        }
        log.debug("issyn  = " + isSyn());
        if (isSyn()) {
            saveToken(request);
        }
    }

    /**
     * 发生异常时候的处理方法,在次对业务异常进行了处理
     * 
     * @param ex
     * @throws Exception
     */
    protected ActionForward onException(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response, Exception ex) throws Exception {
        if (ex instanceof JCFException) {
            JCFException jcfException = (JCFException) ex;
            String code = jcfException.getErrorCode();
            if (StringUtils.isNotEmpty(code)) {
                String error = getErrorMessage(request, code);
                if (StringUtils.isNotEmpty(error)) {
                    log.error("error : " + error);
                }

            }
        }
        log.error("Exception occured in " + this.getClass().getSimpleName(), ex);
        throw ex;
    }

    /**
     * 检查是否可以执行
     * 
     * @param request
     * @return
     */
    protected boolean canExecute(HttpServletRequest request) {
        if (isSyn() && !isTokenValid(request)) {
            request.setAttribute(ACTION_MESSAGE, "这是个过时的页面，不能操作，请自己进入个新页面操作 ...");
            return false;
        } else {
            return true;
        }
    }

    /**
     * 是否使用同步令牌的标志
     * 
     * @return 返回true为使用
     */
    protected boolean isSyn() {
        return false;
    }

    /**
     * 如果没有在request中放令牌请求，则认为不启动同步令牌
     */
    @Override
    protected boolean isTokenValid(HttpServletRequest request) {
        String token = request.getParameter(TOKEN_KEY);
        if (token == null) {
            return true;
        }
        return super.isTokenValid(request);
    }

    /**
     * 获取bean
     * 
     * @param beanID
     * @return
     */
    protected Object getBean(String beanID) {
        return ContextServiceLocator.getInstance().getBean(beanID);
    }

    /**
     * 获取bean
     * 
     * @param entityClass
     * @return
     */
    public <T> T getBean(Class<T> entityClass) {
        return ContextServiceLocator.getInstance().getBean(entityClass);
    }

    /**
     * 转向成功提示页面
     * 
     * @param successInfo 成功提示信息
     * @param returnPath 提示页面返回地址
     * @param request
     * @return ActionForward 对象
     */
    protected ActionForward turnToSuccess(String successInfo, String returnPath, HttpServletRequest request,
            ActionMapping mapping) {
        request.setAttribute("MESSAGE", successInfo);
        request.setAttribute("RETURN_PATH", returnPath);
        return mapping.findForward("Success");
    }

    /**
     * 从form中抽取查询字符串 !!!!! 注意调用该方法之后,form中的属性将会被置空, 请在调用前保存可能要用到的相关数据
     * 
     * @param form ActionForm
     * @return 查询字符串
     */
    protected String getQueryString(ActionForm form) {
        return getQueryString(form, true);
    }

    /**
     * 从form中抽取查询字符串
     * 
     * @param form ActionForm
     * @param deReset 调用该方法后是否清空form
     * @return 查询字符串
     */
    protected String getQueryString(ActionForm form, boolean doReset) {
        StringBuffer buffer = new StringBuffer();
        String result = "";
        try {
            BeanInfo bi = Introspector.getBeanInfo(form.getClass(), ActionForm.class);
            PropertyDescriptor[] pds = bi.getPropertyDescriptors();
            for (PropertyDescriptor element : pds) {
                String name = element.getName();
                Method reader = element.getReadMethod();
                if (reader == null) {
                    continue;
                }
                Object obj = reader.invoke(form, new Object[] {});
                if ((obj != null) && StringUtils.isNotEmpty(String.valueOf(obj))) {
                    String value = String.valueOf(obj);
                    buffer.append("&").append(name).append("=").append(URLEncoder.encode(value, "UTF-8"));
                    Method writer = element.getWriteMethod();
                    if (writer == null) {
                        continue;
                    }
                    if (doReset) {
                        element.getWriteMethod().invoke(form, new Object[] { null });//do reset
                    }
                }
            }
            if (buffer.length() > 0) {
                result = "?" + buffer.toString().substring(1);
            }
            log.debug("QueryString : " + result);
        } catch (Exception e) {
            log.error("从form中抽取查询条件时出错", e);
        }
        return result;
    }

    /**
     * 复制Bean属性,根据要进行拷贝的源和目标的数据类型,自动完成java.util.Date到java.sql.Date
     * java.util.Date到String及String到java.util.Date的类型转换,该方法拷贝所有同名属性,默认不拷贝
     * 源对象中为null的属性
     * 
     * @param src 源对象
     * @param dest 目标对象
     */
    protected static void copyProperties(Object dest, Object src) {
        copyProperties(dest, src, true);
    }

    /**
     * 复制Bean属性,根据要进行拷贝的源和目标的数据类型自动完成数据转换,目前可以实现
     * java.util.Date到String, String 到Integer ，String 到 Double
     * 及String到java.util.Date的相互类型转换该方法拷贝所有同名属性
     * 
     * @param src 源对象
     * @param dest 目标对象
     * @param ignoreNull 是否忽略空的属性
     */
    protected static void copyProperties(Object dest, Object src, boolean ignoreNull) {
        BeanHelper.copyProperties(dest, src, ignoreNull);
    }

    /**
     * 日期解析
     * 
     * @param date
     * @return
     */
    protected static Date parseDate(String date) {
        return BeanHelper.parseDate(date);
    }

    /**
     * 根据错误代码获取对应在资源文件中定义的错误消息
     * 
     * @param request
     * @param errorCode 错误代码
     * @return
     */
    protected String getErrorMessage(HttpServletRequest request, String errorCode) {
        return getStrutsMessage(request, "errorCode", errorCode);
    }

    /**
     * 
     * @param request
     * @param bundelKey 消息文件在Struts-config.xml 文件中对应的key值
     * @param errorCode 消息的键值
     * @param args 消息参数
     * @return
     */
    protected String getStrutsMessage(HttpServletRequest request, String bundelKey, String errorCode, String... args) {
        return getResources(request, bundelKey).getMessage(errorCode, args);
    }

    /**
     * 转向成功页面
     * 
     * @param mapping
     * @return
     */
    protected ActionForward forwardToSuccess(ActionMapping mapping) {
        return mapping.findForward("success");
    }

    protected void removeSessionAttribute(HttpServletRequest request, String name) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(name);
        }
    }

    protected void removeRequestAttribute(final ActionMapping mapping, final HttpServletRequest request) {
        if (mapping.getAttribute() != null) {
            if ("request".equals(mapping.getScope())) {
                request.removeAttribute(mapping.getAttribute());
            } else {
                request.getSession().removeAttribute(mapping.getAttribute());
            }
        }
    }

    protected void setSessionAttribute(HttpServletRequest request, String name, Object obj) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute(name, obj);
        }
    }

    protected Object getSessionAttribute(HttpServletRequest request, String name) {
        Object obj = null;
        HttpSession session = request.getSession(false);
        if (session != null) {
            obj = session.getAttribute(name);
        }
        return obj;
    }

    protected void printSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Enumeration sessionContent = session.getAttributeNames();
            log.debug("Session 现有的内容:");
            while (sessionContent.hasMoreElements()) {
                log.debug("    " + sessionContent.nextElement());
            }
        }
    }

    protected void printApplication() {
        Enumeration applicationContent = servlet.getServletContext().getAttributeNames();
        log.debug("Application 现有的内容:");
        while (applicationContent.hasMoreElements()) {
            log.debug("    " + applicationContent.nextElement());
        }
    }

    protected void printRequest(HttpServletRequest req) {
        if (req != null) {
            Enumeration requestContent = req.getAttributeNames();
            log.debug("Request 现有的内容:");
            while (requestContent.hasMoreElements()) {
                log.debug("    " + requestContent.nextElement());
            }
        }
    }

    /**
     * 给链接URL添加参数
     * 
     * @param URL
     * @param paramName
     * @param paramValue
     * @return
     */
    protected static String appendParamToURI(String URL, String paramName, String paramValue) {
        String appendedURI = URL;
        if (null != appendedURI) {
            if (appendedURI.indexOf("?") < 0) {
                appendedURI += "?";
            } else {
                appendedURI += "&";
            }
            appendedURI += (paramName + "=" + paramValue);
        }

        return appendedURI;
    }

    //验证文件大小和文件格式
    protected String validateFile(FormFile file) {
        //3145728 : 3M
        if ((file.getFileSize() < 0) || (file.getFileSize() > MAX_FILE_SIZE)) {
            return "图片大小超过" + Math.floor(MAX_FILE_SIZE / 1048576) + "M,请选择其他图片";
        }
        String filename = file.getFileName();
        String extName = null;
        try {
            extName = filename.substring(filename.lastIndexOf(".")).toLowerCase();
        } catch (Exception e) {
            log.warn("can not get file extName,maybe there is not a file!");
            return "文件格式无效";
        }

        if (extName.equalsIgnoreCase(".jpg") || extName.equalsIgnoreCase(".gif") || extName.equalsIgnoreCase(".png")
                || extName.equalsIgnoreCase(".bmp") || extName.equalsIgnoreCase(".jpeg")) {
            return null;
        } else {
            return "只能上传图片格式：.jpg .gif .png .bmp .jpeg";
        }
    }
}
