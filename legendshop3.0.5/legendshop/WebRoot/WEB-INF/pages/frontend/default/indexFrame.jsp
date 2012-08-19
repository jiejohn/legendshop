<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<jsp:include flush="true" page="/top${applicationScope.WEB_SUFFIX}" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/default/js/funcs.js"></script>

<tiles2:insertAttribute name="main" ignore="true" />

<jsp:include page="/bottom${applicationScope.WEB_SUFFIX}"/>
</body>
</html>