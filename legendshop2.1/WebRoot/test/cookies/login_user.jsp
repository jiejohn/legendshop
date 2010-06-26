<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:directive.page import="com.done.util.UserManagement"/>
<%@page import="org.acegisecurity.context.SecurityContextImpl"%>
<%@page import="org.acegisecurity.Authentication"%>
<%@page import="org.acegisecurity.context.SecurityContext"%>
<%@include file='/common/jsp/taglib.jsp'%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
if(UserManagement.getUser()!=null)
    pageContext.setAttribute("auth",UserManagement.getUser().getAuthorities());
pageContext.setAttribute("function",UserManagement.getPrincipalFunctionByAuthorities());
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户信息</title>
    
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
    UserId：<%=UserManagement.getUserId() %> <br>
    Username：<%=UserManagement.getUsername() %> <br><br>
    Has Function F_VIEW_ALL_DATA：<%=UserManagement.hasFunction("F_VIEW_ALL_DATA") %> <br><br>
    登录用户所拥有的角色： <br>
    <c:forEach items="${auth}" var="authorities" varStatus="status">
    	${status.index+1}: ${authorities}<br>
    </c:forEach>
    <br><br>
    登录用户所拥有的权限： <br>
     <c:forEach items="${function}" var="function" varStatus="status">
        ${status.index+1}: ${function}<br>
    </c:forEach>
    <br> 
    <br> 
    <br> 
    <%
	// 得到用户名称
	SecurityContext context=(SecurityContext)session.getAttribute("ACEGI_SECURITY_CONTEXT");
	System.out.println("Authentication = "+context.getAuthentication());
	// 得到用户的密码
	SecurityContextImpl securityContext=(SecurityContextImpl)session.getAttribute("ACEGI_SECURITY_CONTEXT");
	Authentication authentication=securityContext.getAuthentication();
	//String password=authentication.getCredentials(); 
     %>  
  </body>
</html>
