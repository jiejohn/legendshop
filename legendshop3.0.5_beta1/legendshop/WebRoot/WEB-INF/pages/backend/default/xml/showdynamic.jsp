<%@page pageEncoding="UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
		<script type='text/javascript' src="<ls:templateResource item='/dwr/interface/CommonService.js'/>"></script>
        <script type='text/javascript' src="<ls:templateResource item='/dwr/engine.js'/>"></script>
       <script type='text/javascript' src="<ls:templateResource item='/dwr/util.js'/>"></script>
        <link rel="stylesheet" type="text/css" media='screen' href="${pageContext.request.contextPath}/common/default/css/showdynamic.css" />
	${dynamicProperties}
