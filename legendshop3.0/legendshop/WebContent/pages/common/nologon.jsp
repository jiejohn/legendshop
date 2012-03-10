<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/pages/common/taglib.jsp'%>
<html>
<head>
<%@include file='/pages/common/common.jsp'%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="operation.error"/></title>
    <link href="${pageContext.request.contextPath}/common/style/style_${sessionScope.shopDetail.colorStyle}.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/common/style/global_${sessionScope.shopDetail.colorStyle}.css" rel="stylesheet" type="text/css" />
</head> 
<body topmargin="0">

<table width="500" cellspacing="2" cellpadding="2" align="center" >
  <tr valign="middle" height="100px"> 
    <td align="center" style="font-family: serif;font-size: 1.5em;"><fmt:message key="no.login.hint"/></td>
  </tr>
  <tr> 
    <td height="80px"  align="left" style="margin-left: 80px;">
     <fmt:message key="nologin.hint">
	     <fmt:param value='${pageContext.request.contextPath}/login${applicationScope.WEB_SUFFIX}'/>
	     <fmt:param value='${pageContext.request.contextPath}/reg${applicationScope.WEB_SUFFIX}'/>
     </fmt:message>
      </td>
  </tr>
</table>
</body>
</html>
