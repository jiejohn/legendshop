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
  <table border=1> 

    <tr><td>Name</td><td>value</td></tr> 

    <% 

    Cookie cookies[]=request.getCookies();
    Cookie sCookie=null;
    String svalue=null;
    String sname=null;
    if(cookies!=null)
    for(int i=0;i<cookies.length;i++)
    {
    sCookie=cookies[i];
    svalue=sCookie.getValue();
    sname=sCookie.getName();
    %> 
    <tr><td><%=sname%></td><td><%=svalue%></td></tr> 
    <% 
    } 
    %> 
    </table>
  </body>
</html>
