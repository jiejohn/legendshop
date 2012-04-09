<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/pages/common/common.jsp"%>
<%@ include file="/pages/common/taglib.jsp"%>
<html>
<head>
	<script type="text/javascript" language="javascript" src="<ls:templateResource item='/common/js/randomimage.js'/>"></script>
	<script type='text/javascript' src="<ls:templateResource item='/dwr/interface/CommonService.js'/>"></script>
    <script type='text/javascript' src="<ls:templateResource item='/dwr/engine.js'/>"></script>
    <script type='text/javascript' src="<ls:templateResource item='/dwr/util.js'/>"></script>
<title>激活LegendShop</title>
</head>
<body>
	<form action="<ls:url address='/admin/license/postUpgrade'/>" method="POST" onsubmit="return validateRandNum('${pageContext.request.contextPath}');">
	        <input type="hidden" id="rand" name="rand"/>
			<input type="hidden" id="cannonull" name="cannonull" value='<fmt:message key="randomimage.errors.required"/>'/>
			<input type="hidden" id="charactors4" name="charactors4" value='<ls:i18n key="randomimage.charactors.required" length="6"/>'/> 
			<input type="hidden" id="errorImage" name="errorImage" value='<fmt:message key="error.image.validation"/>'/>            
		 <table>
		 	<tr><td>激活码：</td><td><input type="text" id="liensekey" name="liensekey"/><input name="submit" type="submit" value='确认' class="s" tabindex="4"></td></tr>
		 	<tr><td>验证码：</td><td><input type="text" id="randNum" name="randNum" class="inputbutton2" maxlength="7" size="7" tabindex="3" ></td></tr>
		 	<tr><td></td><td>
				<img id="randImage" name="randImage" src="<ls:templateResource item='/captcha.svl'/>" />
				&nbsp;<a href="javascript:void(0)" onclick="javascript:changeRandImg('${pageContext.request.contextPath}')" style="font-weight: bold;"><fmt:message key="change.random2"/></a>
			</td></tr>
		 </table>
		 
		<c:if test="${postUpgrade}">
			升级成功,请关闭本页！
		</c:if>
	</form>
</body>
</html>