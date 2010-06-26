<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="bingosoft.jcf.sql.ConfigCode"%>
<%
		ConfigCode sQLCode = ConfigCode.refresh();
		System.out.println("sQLCode isDebug = " + sQLCode.isDebug());
%>

<a href="sqlCode.jsp">刷新成功，返回</a>
