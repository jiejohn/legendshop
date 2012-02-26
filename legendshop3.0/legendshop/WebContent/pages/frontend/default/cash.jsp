<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/pages/common/taglib.jsp'%>
<%@include file='/pages/common/common.jsp'%>
  <c:choose>
   <c:when test="${sessionScope.SPRING_SECURITY_LAST_USERNAME == null}">
   	 
   </c:when>
   	<c:otherwise>
<table style="margin-top: 5px" class="tables" width="100%" cellpadding="0" cellspacing="0">
  <tr> 
    <td class="titlebg"><fmt:message key="cash.bar"/></td>
  </tr>
  <tr> 
     <td><jsp:include flush="true" page="/bought${applicationScope.WEB_SUFFIX}"></jsp:include></td>
  </tr>
  <tr><td><jsp:include flush="true" page="/cashsave${applicationScope.WEB_SUFFIX}"></jsp:include></td></tr>
</table>
	</c:otherwise>
</c:choose>