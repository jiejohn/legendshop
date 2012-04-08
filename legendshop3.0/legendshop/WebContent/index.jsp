<!-- 
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String name = request.getServerName();
	if("www.legendesign.net".equals(name) || "legendesign.net".equals(name)){
	response.sendRedirect("http://www.legendesign.net/legendshop");
	}else{ %>
 <jsp:forward page="/index${applicationScope.WEB_SUFFIX}"></jsp:forward>
<% }%>
 -->
 <jsp:forward page="/index${applicationScope.WEB_SUFFIX}"></jsp:forward>