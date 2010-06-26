<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="bingosoft.jcf.util.FileProcessor"%>
<%@page import="org.springframework.util.StringUtils"%>
<%@page import="java.io.File"%>
<%@page import="bingo.vasms.bizstreet.util.RealPathUtil"%>
<%
    String filePath = request.getParameter("filePath");
    String realPath = RealPathUtil.getFCKRealPath(this.getServletContext(),filePath);  
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
