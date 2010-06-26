<%@page import="com.done.util.SessionUserManagement"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><%=SessionUserManagement.getUsername(session) %> - 后台管理</title>
<style>
body
{
  scrollbar-base-color:#C0D586;
  scrollbar-arrow-color:#FFFFFF;
  scrollbar-shadow-color:DEEFC6;
}
</style>
</head>
<frameset rows="60,*" cols="*" frameborder="no" border="0" framespacing="0">
  <frame src="top.jsp" name="topFrame" scrolling="no">
  <frameset cols="180,*" name="btFrame" id="btFrame" frameborder="NO" border="0" framespacing="0">
    <frame src="menu1.html" noresize name="menu" scrolling="yes">
    <frame src="<%=request.getContextPath() %>/admin/userCommentList.do?status=0" noresize name="main" scrolling="yes">
  </frameset>
</frameset>
<noframes>
	<body>Your browser does not support Frame!</body>
</noframes>
</html>