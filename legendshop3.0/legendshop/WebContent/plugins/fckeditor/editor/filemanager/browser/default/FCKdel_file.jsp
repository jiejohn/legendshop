<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.legendshop.core.helper.FileProcessor"%>
<%@page import="com.legendshop.core.helper.RealPathUtil;"%>
<%
    String filePath = request.getParameter("filePath");
    String realPath = RealPathUtil.getFCKRealPath(filePath);  
/**
    int last = realPath.lastIndexOf("/");
    if(last != -1){
        realPath = realPath.substring(0,last);
    }
*/
   //String p = StringUtils.replace(realPath, File.separator, "/");
   int result = FileProcessor.deleteFile(realPath);
   response.setContentType("text/xml");
   response.getWriter().write(String.valueOf(result));
 %>
