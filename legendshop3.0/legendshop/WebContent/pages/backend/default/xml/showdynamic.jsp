<%@page pageEncoding="UTF-8" language="java"%>
<%@ include file="/pages/common/common.jsp"%>
<%@ include file="/pages/common/taglib.jsp"%>
		<script type='text/javascript' src="<ls:templateResource item='/dwr/interface/CommonService.js'/>"></script>
        <script type='text/javascript' src="<ls:templateResource item='/dwr/engine.js'/>"></script>
       <script type='text/javascript' src="<ls:templateResource item='/dwr/util.js'/>"></script>
        <link rel="stylesheet" type="text/css" media='screen' href="${pageContext.request.contextPath}/common/css/showdynamic.css" />
	${dynamicProperties}
