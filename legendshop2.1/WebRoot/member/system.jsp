<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.io.*"%>
<%
			try {
				System.getProperties().list(new PrintWriter(out));
			}
			catch (Exception e) {
				e.printStackTrace() ;
			}
%>
