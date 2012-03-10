<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="com.legendshop.business.common.CommonServiceUtil"%>
<%@page import="com.legendshop.business.common.Constants"%>
<%@include file='/pages/common/taglib.jsp'%>
<%@include file='/pages/common/common.jsp'%>
<%
	request.getSession().setAttribute(Constants.TOKEN, CommonServiceUtil.generateRandom());
%>
<c:if test="${sessionScope.SPRING_SECURITY_LAST_USERNAME != null}">
<table style="margin-top: 5px" class="tables" width="100%" cellpadding="0" cellspacing="0">
  <tr> 
    <td class="titlebg"><fmt:message key="cash.bar"/></td>
  </tr>
  <tr> 
     <td><jsp:include flush="true" page="/bought${applicationScope.WEB_SUFFIX}"></jsp:include></td>
  </tr>
  <tr><td><jsp:include flush="true" page="/cashsave${applicationScope.WEB_SUFFIX}"></jsp:include></td></tr>
</table>
</c:if>