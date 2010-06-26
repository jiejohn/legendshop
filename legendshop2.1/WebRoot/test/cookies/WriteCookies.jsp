<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file='/common/jsp/taglib.jsp'%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'writeCookies.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  增加Cookies
    <%
    String cookieName=request.getParameter("name");
    if(cookieName==null) cookieName = "defaultName";
	String cookieValue=request.getParameter("value");
	if(cookieValue==null) cookieValue = "defaultValue";
    Cookie cookie=new Cookie(cookieName,cookieValue);
    cookie.setMaxAge(10000);
    cookie.setPath("/");
    response.addCookie(cookie);

    %>
 <br>
  </body>
</html>
