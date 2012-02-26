<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/pages/common/common.jsp"%>
<%@ include file="/pages/common/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/common/js/randomimage.js"></script>
  		<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/CommonService.js'></script>
  		<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/engine.js'></script>
		<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/util.js'></script>
<title>激活LegendShop</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/admin/license/postUpgrade${applicationScope.WEB_SUFFIX}" method="POST" onsubmit="return checkRandNum();">
	        <input type="hidden" id="rand" name="rand"/>
			<input type="hidden" id="cannonull" name="cannonull" value='<fmt:message key="randomimage.errors.required"/>'/>
			<input type="hidden" id="charactors4" name="charactors4" value='<fmt:message key="randomimage.charactors4.required"/>'/>
			<input type="hidden" id="errorImage" name="errorImage" value='<fmt:message key="error.image.validation"/>'/>            
		输入激活码：<input type="text" id="liensekey" name="liensekey"/>
		<input name="submit" type="submit" value='确认' class="s" tabindex="4">
		<br>
		<img id="randImage" name="randImage"/>
		<input type="text" id="randNum" name="randNum" class="inputbutton2" maxlength="4" size="4" tabindex="3" >
		 &nbsp;<a href="javascript:void(0)" onclick="javascript:changeRandImg('${pageContext.request.contextPath}')" style="font-weight: bold;"><fmt:message key="change.random2"/></a>
		 <br>
		<c:if test="${postUpgrade}">
			升级成功
		</c:if>
	</form>
		<script type="text/javascript">	
		window.onload = function(){
		changeRandImg('${pageContext.request.contextPath}');
    }
 
	</script>
</body>
</html>