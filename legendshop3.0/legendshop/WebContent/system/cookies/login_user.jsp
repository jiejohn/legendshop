<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:directive.page import="com.legendshop.core.UserManager"/>
<%@page import="org.springframework.security.core.context.SecurityContextImpl"%>
<%@page import="org.springframework.security.core.Authentication"%>
<%@page import="org.springframework.security.core.context.SecurityContext"%>

<%@include file='/pages/common/taglib.jsp'%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
if(UserManager.getUser(request.getSession())!=null)
	if(UserManager.getUser(request.getSession()) != null){
	    pageContext.setAttribute("auth",UserManager.getUser(request.getSession()).getAuthorities());
		pageContext.setAttribute("function",UserManager.getPrincipalFunctionByAuthorities(request.getSession()));
		
	}

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
    UserId：<%=UserManager.getUserId(request) %> <br>
    Username：${sessionScope.SPRING_SECURITY_LAST_USERNAME} <br><br>
    Has Function F_VIEW_ALL_DATA：<%=UserManager.hasFunction(request,"F_VIEW_ALL_DATA") %> <br><br>
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
	//ACEGI_SECURITY_CONTEXT
	SecurityContext context=(SecurityContext)session.getAttribute("SPRING_SECURITY_CONTEXT");
	// 得到用户的密码
	SecurityContextImpl securityContext=(SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
	Authentication authentication = null;
	if(securityContext != null){
		 authentication = securityContext.getAuthentication();
	}
	
	//String password=authentication.getCredentials(); 
     %>  
  </body>
</html>
