package com.done.base;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.Globals;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import bingosoft.jcf.dao.support.CriteriaQuery;
import bingosoft.jcf.dao.support.PageSupport;
import bingosoft.jcf.sql.ConfigCode;
import bingosoft.jcf.util.AppUtils;

import com.done.common.Constants;
import com.done.util.BeanUtils;
import com.done.util.RealPathUtil;
import com.done.util.SessionUserManagement;

/**
 * @author hewq 2009-10-7 base class of controller
 */
public class BaseSpringController extends MultiActionController {
    private Integer MAX_FILE_SIZE = Integer.parseInt(ConfigCode.getInstance().getCode("config.MaxFileSize"));
    static {
        //注册converters,见 SystemInitPlugin.java
        // ConvertRegisterHelper.registerConverters();
    }

    public void copyProperties(Object target, Object source) {
        BeanUtils.copyProperties(target, source);
    }

    public <T> T copyProperties(Class<T> destClass, Object orig) {
        return BeanUtils.copyProperties(destClass, orig);
    }

    /**
     * 初始化binder的回调函数.
     * 
     * @see MultiActionController#createBinder(HttpServletRequest,Object)
     */

    /*
     *     @Override

    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        logger.info("BaseSpringController initBinder calling");
        /*
        System.out.println("BaseSpringController initBinder calling123");
         binder.registerCustomEditor(Short.class, new
          CustomNumberEditor(Short.class, true));
         binder.registerCustomEditor(Integer.class, new
          CustomNumberEditor(Integer.class, true));
          binder.registerCustomEditor(Long.class, new
          CustomNumberEditor(Long.class, true));
          binder.registerCustomEditor(Float.class, new
         CustomNumberEditor(Float.class, true));
         binder.registerCustomEditor(Double.class, new
          CustomNumberEditor(Double.class, true));
          binder.registerCustomEditor(BigDecimal.class, new
          CustomNumberEditor(BigDecimal.class, true));
          binder.registerCustomEditor(BigInteger.class, new
          CustomNumberEditor(BigInteger.class, true));
          SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
          dateFormat.setLenient(false);
          binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    
    }
*/

    /**
     * 用于保存分页信息
     */
    public void savePage(PageSupport ps, HttpServletRequest request) {
        request.setAttribute("offset", new Integer(ps.getOffset() + 1));
        request.setAttribute("list", ps.getResultList());
        request.setAttribute("curPageNO", ps.getCurPageNO());
        if (ps.hasMutilPage()) {
            request.setAttribute("toolBar", ps.getToolBar((Locale) request.getSession()
                    .getAttribute(Globals.LOCALE_KEY)));
        }
    }

    /**
     * 保存消息在request中,messages.jsp会取出来显示此消息
     */
    protected static void saveMessage(HttpServletRequest request, String message) {
        if (StringUtils.isNotBlank(message)) {
            List list = getOrCreateRequestAttribute(request, "springMessages", ArrayList.class);
            list.add(message);
        }
    }

    /**
     * 保存错误消息在request中,messages.jsp会取出来显示此消息
     */
    protected static void saveError(HttpServletRequest request, String errorMsg) {
        if (StringUtils.isNotBlank(errorMsg)) {
            List list = getOrCreateRequestAttribute(request, "springErrors", ArrayList.class);
            list.add(errorMsg);
        }
    }

    public static <T> T getOrCreateRequestAttribute(HttpServletRequest request, String key, Class<T> clazz) {
        Object value = request.getAttribute(key);
        if (value == null) {
            try {
                value = clazz.newInstance();
            } catch (Exception e) {
                ReflectionUtils.handleReflectionException(e);
            }
            request.setAttribute(key, value);
        }
        return (T) value;
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

    //上传文件
    protected String uploadFileAndCallback(HttpServletRequest request, MultipartFile file, String subPath) {
        if (validateFile(file)) {
            String filename = file.getOriginalFilename();
            String name = filename.substring(0, filename.lastIndexOf("."));
            String extName = filename.substring(filename.lastIndexOf(".")).toLowerCase();
            String lastFileName = name + "-" + System.currentTimeMillis() + extName;
            //图片存储的相对路径 
            String path = subPath + lastFileName;
            File dirPath = new File(getRealPath(request) + subPath);
            if (!dirPath.exists()) {
                dirPath.mkdirs();
            }
            String fileFullPath = getRealPath(request) + path;

            File f = new File(fileFullPath);
            try {
                FileCopyUtils.copy(file.getBytes(), f);
            } catch (Exception e) {
                logger.error("upload file:", e);
            }
            return path;
        } else {
            throw new RuntimeException("上传文件不符合规格");
        }
    }

    //验证文件大小和文件格式
    protected boolean validateFile(MultipartFile file) {
        //3145728 : 3M
        if ((file.getSize() < 0) || (file.getSize() > MAX_FILE_SIZE)) {
            return false;
        }
        String filename = file.getOriginalFilename();
        String extName = null;
        try {
            extName = filename.substring(filename.lastIndexOf(".")).toLowerCase();
        } catch (Exception e) {
            logger.warn("can not get file extName,maybe there is not a file!");
            return false;
        }

        if (extName.equalsIgnoreCase(".jpg") || extName.equalsIgnoreCase(".gif") || extName.equalsIgnoreCase(".png")
                || extName.equalsIgnoreCase(".bmp") || extName.equalsIgnoreCase(".jpeg")) {
            return true;
        } else {
            return false;
        }
    }

    //多文件上传，获得多个文件
    protected Set<MultipartFile> getFileSet(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Set<MultipartFile> fileset = new LinkedHashSet<MultipartFile>();
        for (Iterator it = multipartRequest.getFileNames(); it.hasNext();) {
            String key = (String) it.next();
            MultipartFile file = multipartRequest.getFile(key);
            if (file.getOriginalFilename().length() > 0) {
                fileset.add(file);
            }
        }
        return fileset;
    }

    /**
     * 是否有查看所有数据权限
     * 
     * @param cq
     * @param request
     * @param userName
     * @return
     */
    protected CriteriaQuery hasAllDataFunction(CriteriaQuery cq, HttpServletRequest request, String userName) {
        if (SessionUserManagement.hasFunction(request.getSession(), Constants.FUNCTION_VIEW_ALL_DATA)) {
            if (!AppUtils.isBlank(userName)) {
                cq.like("userName", "%"+StringUtils.trim(userName)+"%");
            }
        } else {
            String name = SessionUserManagement.getUsername(request.getSession());
            if (name == null) {
                throw new RuntimeException("your are not logon yet!");
            }
            cq.eq("userName", name);
        }
        return cq;
    }

    protected CriteriaQuery hasAllDataFunction(CriteriaQuery cq, HttpServletRequest request, String key, String name) {
        if (SessionUserManagement.hasFunction(request.getSession(), Constants.FUNCTION_VIEW_ALL_DATA)) {
            if (!AppUtils.isBlank(name)) {
                cq.eq(key, StringUtils.trim(name));
            }
        } else {
            String userName = SessionUserManagement.getUsername(request.getSession());
            if (userName == null) {
                throw new RuntimeException("your are not logon yet!");
            }
            cq.eq(key, userName);
        }
        return cq;
    }

    protected void checkPrivilege(HttpServletRequest request, String loginName, String userName) {
        if (loginName == null) {
            throw new RuntimeException("login name is null!");
        }
        if (!SessionUserManagement.hasFunction(request.getSession(), Constants.FUNCTION_VIEW_ALL_DATA)) {
            if (!loginName.equals(userName)) {
                throw new RuntimeException(loginName + " can not edit this object!");
            }
        }
    }

    /**
     * //取当前系统路径
     * 
     * @param request
     * @return
     */
    private String getRealPath(HttpServletRequest request) {
        return RealPathUtil.getRealPath(this.getServletContext());
    }
    
    protected String getShopName(HttpServletRequest request, HttpServletResponse response) {
        String shopName = (String) getSessionAttribute(request, Constants.SHOP_NAME);
        if (!AppUtils.isBlank(shopName)) {
            return shopName;
        }
        Cookie cookies[] = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (Constants.SHOP_NAME.equalsIgnoreCase(cookie.getName())) {
                    shopName = cookie.getValue();
                }
            }
        }
        if (AppUtils.isBlank(shopName)) {
            shopName = Constants.DEFAULT_SHOP;
            setShopName(request, response, shopName);
        }
        return shopName;
    }

    protected void setShopName(HttpServletRequest request, HttpServletResponse response, String shopName) {
        setSessionAttribute(request, Constants.SHOP_NAME, shopName);
        Cookie cookie = new Cookie(Constants.SHOP_NAME, shopName);
        //生命周期    
        cookie.setMaxAge(60 * 60 * 24 * 365);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
    
    protected Object getSessionAttribute(HttpServletRequest request, String name) {
        Object obj = null;
        HttpSession session = request.getSession(false);
        if (session != null) {
            obj = session.getAttribute(name);
        }
        return obj;
    }
    
    protected void setSessionAttribute(HttpServletRequest request, String name, Object obj) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute(name, obj);
        }
    }

}
