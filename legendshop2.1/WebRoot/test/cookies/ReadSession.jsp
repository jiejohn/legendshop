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
  request
    <% 
    Enumeration requestContent = request.getAttributeNames();
    %>
    <table border="1" style="border: 1px solid red;border-collapse: collapse;">
    <%
    while (requestContent.hasMoreElements()) {
        String next = (String) requestContent.nextElement();
        %>
        <tr>
        <td><%=next %></td>
        <td><%=request.getAttribute(next).toString() %></td>
        </tr>
    <%    
     }
    %> 
    </table>
    <br>
      session
    <% 
    Enumeration sessionContent = session.getAttributeNames();
    %>
    <table border="1" style="border: 1px solid red;border-collapse: collapse;">
    <%
    while (sessionContent.hasMoreElements()) {
        String next = (String) sessionContent.nextElement();
        %>
        <tr>
        <td><%=next %></td>
        <td><%=session.getAttribute(next).toString() %></td>
        </tr>
    <%    
     }
    %> 
    </table>
    <br>
   Application
    <% 
    Enumeration applicationContent = session.getServletContext().getAttributeNames();
    %>
    <table border="1" style="border: 1px solid red;border-collapse: collapse;">
    <%
    while (applicationContent.hasMoreElements()) {
        String next = (String) applicationContent.nextElement();
        %>
        <tr>
        <td><%=next %></td>
        <td><%=session.getServletContext().getAttribute(next).toString() %></td>
        </tr>
    <%    
     }
    %> 
    </table>
  </body>
</html>
