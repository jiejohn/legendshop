<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common.jsp"%>
<%@ page import="com.done.util.UserManagement" %>
<%@  page  import="java.util.*;"  %>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
<jsp:useBean  id="onlineuser"  class="com.done.struts.OnLineUser"  scope="application"/>  
<HTML><HEAD><TITLE></TITLE>
<LINK title=Style href="${root}/common/css/back_style.css" type=text/css rel=stylesheet>

<META content="MSHTML 6.00.2900.2722" name=GENERATOR>

<style type="text/css">
<!--
body {
	margin-left: 10px;
	margin-right: 0px;
	margin-top: 10px;
	margin-bottom: 0px;
}
-->
</style></HEAD>
<BODY topMargin=0>
<auth:auth ifAnyGranted="F_OPERATOR">
<%
      String  ip  =  request.getRemoteAddr(); 
      out.println(UserManagement.getUsername()+" ，你的IP地址:<font  color=red>"+ip+"</font><br>");  
      out.println("<br>当前在线用户人数:<font  color=red>"+onlineuser.getCount()+"</font><br>");  
       Vector  vt=onlineuser.getOnLineUser();  
       Enumeration  e  =  vt.elements();  
       out.println("在线用户列表");  
       out.println("<table style='border:#cccccc 1px solid'>"); 
       out.println("<tr><td>在线用户名列表</td></tr>");  
         while(e.hasMoreElements()){

              out.println("<tr>"); 
               out.println("<td>"); 
              out.println((String)e.nextElement()+"<br>");  
              out.println("</td>");  
              out.println("</tr>"); 
    }
    out.println("</table>");
   //  }
%>
</auth:auth>
</BODY></HTML>

