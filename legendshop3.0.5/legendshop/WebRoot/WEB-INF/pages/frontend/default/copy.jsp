 <%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
 <table width="954px" cellpadding="3px" cellspacing="3px" align="center" >
  <td width="100%" align="center">
           <c:forEach items="${requestScope.newsBottomList}" var="newsBottom">
           	  <a href="${pageContext.request.contextPath}/news/${newsBottom.newsId}${applicationScope.WEB_SUFFIX}" target="_blank">${newsBottom.newsTitle}</a> |
           </c:forEach>
           <a href="${pageContext.request.contextPath}/ipsearch${applicationScope.WEB_SUFFIX}" target="_blank">IP地址查询</a>
  </td></tr>
    <tr> 
  <td width="100%" align="center"> 
  &copy;Copyright 2009 - 2011 Power By <a href="http://www.legendesign.net" target="_blank">LegendShop ${LEGENDSHOP_VERSION} ${licenseDesc}</a>  All Rights Reserved.
       </td></tr>
  <tr> 
</table>