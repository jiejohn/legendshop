<%@page pageEncoding="UTF-8" language="java"%>
<%@ include file="/pages/common/common.jsp"%>
<%@ include file="/pages/common/taglib.jsp"%>
		<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/CommonService.js'></script>
        <script type='text/javascript' src='${pageContext.request.contextPath}/dwr/engine.js'></script>
        <script type='text/javascript' src='${pageContext.request.contextPath}/dwr/util.js'></script>
        <link rel="stylesheet" type="text/css" media='screen' href="${pageContext.request.contextPath}/common/css/showdynamic.css" />
	${dynamicProperties}
