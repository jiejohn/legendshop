<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="bingosoft.jcf.sql.ConfigCode"%>
<%
		String isDebug = request.getParameter("isDebug");
		int debug = Integer.valueOf(isDebug);
		if(debug == 1){
		ConfigCode.getInstance().setDebug(true);

		}else{
		ConfigCode.getInstance().setDebug(false);
		}
	
		System.out.println("sQLCode isDebug = " + ConfigCode.getInstance().isDebug());
%>
设置调试模式成功，当前值为<%=ConfigCode.getInstance().isDebug() %><br>
<a href="sqlCode.jsp">返回</a>
