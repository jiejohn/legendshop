<%@include file='/pages/common/taglib.jsp'%>
<%@include file='/pages/common/common.jsp'%>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">   
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
  		<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/CommonService.js'></script>
  		<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/engine.js'></script>
		<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/util.js'></script>
		<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/common/css/indexJpgForm.css" />
	 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/jquery.superbox-min.js"></script>
	 <link rel="stylesheet" href="${pageContext.request.contextPath}/common/css/jquery.superbox.css" type="text/css" media="all" />

	<script type="text/javascript">
		jQuery(function(){
			jQuery.superbox.settings = {
				closeTxt: "<fmt:message key="close"/>",
				loadTxt: "Loading...",
				nextTxt: "Next",
				prevTxt: "Previous"
			};
			jQuery.superbox();
		});
	</script>
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/common/js/randomimage.js"></script>
</style>
    <table width="954px" cellpadding="0" cellspacing="0" class="tables">
                    <tr>
                  <td class="titlebg"><font style="font-size: 1.4em"><fmt:message key="login"/></font></td>
                </tr>
	<tr><td><center>
			<form name="loginform" action="j_spring_security_check" method="POST"  onsubmit="return checkRandNum();">
			<input type="hidden" id="rand" name="rand"/>
			<input type="hidden" id="cannonull" name="cannonull" value='<fmt:message key="randomimage.errors.required"/>'/>
			<input type="hidden" id="charactors4" name="charactors4" value='<fmt:message key="randomimage.charactors4.required"/>'/>
			<input type="hidden" id="errorImage" name="errorImage" value='<fmt:message key="error.image.validation"/>'/>
			<input type="hidden" id="returnUrl" name="returnUrl" value="${param.returnUrl}"/>
            <table><tr><td>
            <table cellpadding="5px">
            		<tr>
						<td colspan="2" align="left">
						  <b><fmt:message key="username.password"/></b> 
						  <c:if test="${param.error}">
						 <br><label class="error"><fmt:message key="error.password.noright"/></label>
						  </c:if>
						</td>
					</tr>
					<tr>
						<td>
							<fmt:message key="user.name"/>
						</td>
						<td align="left"><input id="username" type='text' name="j_username" style="font-size:11pt; width: 180px" size="50" class="inputbutton2" tabindex="1"/> </td>
					</tr>
					<tr>
						<td><fmt:message key="editForm.password"/></td>
						<td align="left"><input id="pwd" type='password' name='j_password' style="font-size:11pt; width: 180px" size="50" autocomplete="off" class="inputbutton2" tabindex="2" onload="this.value=''"/></td>
					</tr>
					<tr>
						<td><fmt:message key="validation.code"/></td>
						<td align="left"><input type="text" id="randNum" name="randNum" class="inputbutton2" maxlength="4" size="4" tabindex="3" >
						<img id="randImage" name="randImage"/></td>
					</tr>					
					<tr><td></td><td><fmt:message key="change.random1"/> &nbsp;<a href="javascript:void(0)" onclick="javascript:changeRandImg('${pageContext.request.contextPath}')" style="font-weight: bold;"><fmt:message key="change.random2"/></a></td></tr>
					<tr>
						<td></td>
						<td><input name="submit" type="submit" value='<fmt:message key="login"/>' class="s" tabindex="4">
						<input name="reset" type="reset" value='<fmt:message key="reset.hint"/>' class="s" tabindex="5"></td>
					</tr>
				</table>
            </td>
            <td>
            	<table cellpadding="15px"><tr><td><fmt:message key="security.hint"/></td></tr></table>
            
            	<table cellpadding="2px" style="margin-left: 20px" align="left">
	            	<tr><td align="left"><img src="${pageContext.request.contextPath}/img/004.gif"/><fmt:message key="not.member.hint"/>,&nbsp;<a href="${pageContext.request.contextPath}/reg${applicationScope.WEB_SUFFIX}"><fmt:message key="regFree"/></a></td></tr>
	            	<tr><td align="left"><img src="${pageContext.request.contextPath}/img/004.gif"/><fmt:message key="goto.home"/>&nbsp;<a href="${pageContext.request.contextPath}/index${applicationScope.WEB_SUFFIX}"><fmt:message key="shop.index"/></a></td></tr>
	            	<tr><td align="left"><img src="${pageContext.request.contextPath}/img/004.gif"/><fmt:message key="forget.password.hint"/>
	            	<a href="${pageContext.request.contextPath}/resetpassword${applicationScope.WEB_SUFFIX}" rel="superbox[iframe][300x200]">&nbsp;<fmt:message key="find.my.password"/></a>	   	
	            	</td>
	            	</tr>
            	</table>
            </td>
            </tr>
           </table>
				
		
			</form>
		</center>
	</td>
	</tr>
	</table>
	<script type="text/javascript">	
	window.onload = function(){
	   if(window.top.location.href!=location.href)    
		{    
    		window.top.location.href=location.href;    
		}
		changeRandImg('${pageContext.request.contextPath}');
		document.getElementById("username").focus();
    }
 
	</script>
