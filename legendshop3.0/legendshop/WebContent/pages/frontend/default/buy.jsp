<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<html>
<head>
<%@include file='/WEB-INF/pages/common/common.jsp'%>
<lb:shopDetail var="shopDetail" >
    <link href="<ls:templateResource item='/common/style/style_${shopDetail.colorStyle}.css'/>" rel="stylesheet" type="text/css" />
    <link href="<ls:templateResource item='/common/style/global_${shopDetail.colorStyle}.css'/>" rel="stylesheet" type="text/css" />
</lb:shopDetail>
<title>

<c:choose>
   <c:when test="${sessionScope.SPRING_SECURITY_LAST_USERNAME != null}">
    ${sessionScope.SPRING_SECURITY_LAST_USERNAME} <fmt:message key="shopingCar"/>
   </c:when>
   <c:otherwise>
    <fmt:message key="shopingCar"/>
   </c:otherwise>
</c:choose>
</title>
</head> 
<body topmargin="0">
   	<c:forEach items="${requestScope.USER_REG_ADV_950}" var="adv">
<table width="100%" cellpadding="0" cellspacing="0" style="margin-bottom: 5px" class="picstyle">
<tr><td><a href="${adv.linkUrl}"><img src="${pageContext.request.contextPath}/photoserver/photo/${adv.picUrl}" title="${adv.title}" width="950px"/></a></td></tr>
 </table>
</c:forEach>
<table width="100%" class="tables" cellpadding="0" cellspacing="0">
  <tr> 
    <td class="titlebg"><fmt:message key="product.subscribed.list"/></td>    
  </tr>
  <tr><td><jsp:include flush="true" page="/bought${applicationScope.WEB_SUFFIX}"></jsp:include></td></tr>
 </table>
</body>
</html>