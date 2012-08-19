<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<lb:shopDetail var="shopDetail" >
<title>${shopDetail.siteName}</title>
</lb:shopDetail>
<link type="text/css" href="<ls:templateResource item='/common/red/css/legend.css'/>" rel="stylesheet"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/jquery1.6.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/red/js/menu.js"></script>
</head>

<body>

<jsp:include flush="true" page="/top${applicationScope.WEB_SUFFIX}">
	<jsp:param name="sortId" value="-1" />
</jsp:include>

<tiles2:insertAttribute name="main" ignore="true" />

 <jsp:include page="/bottom${applicationScope.WEB_SUFFIX}"/>
</body>
</html>
