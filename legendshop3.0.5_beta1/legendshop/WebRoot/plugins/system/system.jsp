<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.io.*"%>
<%@page import="java.text.NumberFormat"%>
<br>系统参数配置<br>
<%
			try {
				System.getProperties().list(new PrintWriter(out));
			}
			catch (Exception e) {
				e.printStackTrace() ;
			}
%>
<br><br/><br/>系统内存（M）<br/>
<%
Runtime runtime = Runtime.getRuntime(); 

NumberFormat format = NumberFormat.getInstance(); 

StringBuilder sb = new StringBuilder(); 
long maxMemory = runtime.maxMemory(); 
long allocatedMemory = runtime.totalMemory(); 
long freeMemory = runtime.freeMemory(); 

sb.append("free memory: " + format.format(freeMemory / 1024 /1024) + "<br/>");  
sb.append("allocated memory: " + format.format(allocatedMemory / 1024/1024) + "<br/>"); 
sb.append("max memory: " + format.format(maxMemory / 1024/1024) + "<br/>"); 
sb.append("total free memory: " + format.format((freeMemory + (maxMemory - allocatedMemory))/1024/1024));
out.print(sb.toString());
%>
