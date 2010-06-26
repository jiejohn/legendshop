<%
	String name = request.getServerName();
	if("www.123done.com.cn".equals(name)){
%>
<jsp:forward page="/shop/123done"></jsp:forward>

<%}else{
	%>
<jsp:forward page="/index.do"></jsp:forward>
<%} %>
