<%@ page language="java" pageEncoding="UTF-8"%>
<jsp:directive.page import="com.done.util.UserManagement;"/>
<%@ include file="/common/jsp/common.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
<jsp:useBean  id="onlineuser"  class="com.done.struts.OnLineUser"  scope="application"/>  
<HTML><HEAD><TITLE></TITLE>
<LINK title=Style href="${root}/common/css/back_style.css" type=text/css rel=stylesheet>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style></HEAD>
<BODY topMargin=0>
<%
	String userName=UserManagement.getUsername();
	String  ip  =  request.getRemoteAddr(); 
	session.setMaxInactiveInterval(350);  //Sesion有效时长，以秒为单位  
    session.setAttribute(userName,onlineuser);
%>
<div align="center">
  <p>&nbsp;</p>
  <table class=tableBorder width="782" border="0">
  <tr><th width="775">当前登录用户</th></tr>
    <tr>
      <td><table width="775" border="0">
        <tr>
          <td class="forumRow" width="90">当前用户</td>
          <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
          <td class="forumRow" width="90">在线用户数</td>
          </auth:auth>
           <td class="forumRow" width="90">你的IP</td>
        </tr>
        <tr>
          <td class="forumRow"><%=userName %></td>
          <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
          <td class="forumRow"><a href="onLineUsers.jsp"><%=onlineuser.getCount() %> (个用户)</a></td>
           </auth:auth>
          <td class="forumRow"><%=ip %></td>
          </tr>
      </table></td>
	  
    </tr>
  </table>
</div>
</BODY></HTML>

