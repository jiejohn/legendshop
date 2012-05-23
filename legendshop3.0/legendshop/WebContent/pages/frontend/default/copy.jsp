<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/pages/common/common.jsp"%>
<%@include file='/pages/common/taglib.jsp'%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
<lb:shopDetail var="shopDetail" />
<c:if test="${requestScope.adList!=null}">
<table width="954px" cellpadding="0" cellspacing="0" style="margin-top: 5px">
<tr><td align="center">
<table cellpadding="2" cellspacing="2">
<tr>
<c:forEach items="${requestScope.adList}" var="ad">
  <td height="40px" align="center">
      		<a href="${ad.url}" target=_blank title="${ad.content}">
      		<c:choose>
      			<c:when test="${ad.picture != null}">
      			<img src="${pageContext.request.contextPath}/photoserver/photo/${ad.picture}" title="${ad.wordlink}" width="88px" height="31px"/><br>${ad.wordlink}
      			</c:when>
      			<c:otherwise>${ad.wordlink}</c:otherwise>
      		</c:choose>
      		</a>   
     
    </td>
     </c:forEach>
     </tr>
  </table>
  </td></tr></table>
 </c:if> 
  
 <c:choose>
 	<c:when test="${shopDetail != null}">
<table width="954px" class="tables" style="margin-top: 5px" cellpadding="0" cellspacing="0">
<tr>
 <td class="titlebg"><fmt:message key="order.step"/></td>
  </tr><tr>
 <td>
 <jsp:include page="/copyAll${applicationScope.WEB_SUFFIX}" flush="true"/>
</td>
</tr>
</table> 	
 	</c:when>
 	<c:otherwise>
    <jsp:include page="/copyAll${applicationScope.WEB_SUFFIX}" flush="true"/>
 	</c:otherwise>
 </c:choose>
<br>
