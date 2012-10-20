 <%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
 <table width="954px" cellpadding="3px" cellspacing="3px" align="center" >
  <td width="100%" align="center">
           <c:forEach items="${requestScope.newsBottomList}" var="newsBottom">
           	  <a href="${pageContext.request.contextPath}/news/${newsBottom.newsId}" target="_blank">${newsBottom.newsTitle}</a> |
           </c:forEach>
           <a href="${pageContext.request.contextPath}/ipsearch" target="_blank">IP地址查询</a>
  </td></tr>
    <tr> 
  <td width="100%" align="center">
    	 &copy;Copyright 2009 - 2012 Power By 
    	 <c:choose>
	    	 	<c:when test="${shopDetail.domainName != null }"></c:when>
	    	 	<c:otherwise>
	    	 			<a href="http://www.legendesign.net" target="_blank">LegendShop ${LEGENDSHOP_VERSION} ${licenseDesc}</a>  All Rights Reserved.
	    	 	</c:otherwise>
    	 </c:choose>
       </td></tr>
  <tr> 
</table>