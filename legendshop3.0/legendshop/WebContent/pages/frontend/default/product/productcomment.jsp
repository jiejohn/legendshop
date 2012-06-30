<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="com.legendshop.core.helper.PropertiesUtil"%>
<%@page import="com.legendshop.core.constant.ParameterEnum"%>
<%@include file='/pages/common/taglib.jsp'%>
<%@include file='/pages/common/common.jsp'%>
		<script type='text/javascript' src="<ls:templateResource item='/dwr/interface/CommonService.js'/>"></script>
  		<script type='text/javascript' src="<ls:templateResource item='/dwr/engine.js'/>"></script>
		<script type='text/javascript' src="<ls:templateResource item='/dwr/util.js'/>"></script>

<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/common/js/randomimage.js"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/common/css/default/indexJpgForm.css" />
<c:if test="${requestScope.productCommentList != null }">
<table width="100%" class="tables" cellpadding="0" cellspacing="0">
	<tr>
		<td style="background-color: #ECECEC;font-weight: bold;">
		<table width="100%">
			<tr>
				<td width="80%"><fmt:message key="comment.content" /></td>
				<td><fmt:message key="comment.Spokesperson"/></td>
			</tr>
		</table>
		</td>
	<tr>
	<c:forEach items="${requestScope.productCommentList}" var="comment" varStatus="status">
	<tr>
		<td align="left" style="width: 80%">
			<div><span style="color: red">&nbsp;[<fmt:formatDate value="${comment.addtime}" pattern="yyyy-MM-dd HH:mm"/>]</span>  &nbsp; ${comment.content}</div>
			<c:if test="${comment.replyContent != null}">
			<div style="margin-left: 10px"><fmt:message key="comment.content"/> ${comment.replyContent}</div>
			</c:if>
		</td>
		<td>
			${comment.userName}
		</td>
	</tr>
	</c:forEach>
</table>
</c:if>
<br>
<table width="100%" class="tables" cellpadding="0" cellspacing="0">
							<tr>
								<td style="background-color: #ECECEC;font-weight: bold;height: 30px">
									<div align="center"><fmt:message key="comment.want"/></div>
								</td>
							</tr>
							<tr>
								<td>
										<table style="width: 100%">
																		<tr>
																			<td width="150px" align="right">
																					<font color="#ff0000">*</font>
																					<fmt:message key="content" />
																			</td>
																			<td align="left"><textarea rows="5" cols="50" id="content" name="content"></textarea></td>
																		</tr>
																		<tr>
																		<td align="right"><fmt:message key="validation.code"/></td>
																			<input type="hidden" id="rand" name="rand"/>
																			<input type="hidden" id="cannonull" name="cannonull" value="<fmt:message key="randomimage.errors.required"/>"/>
																			<input type="hidden" id="charactors4" name="charactors4" value="<ls:i18n key="randomimage.charactors.required" length="6"/>"/>
																			<input type="hidden" id="errorImage" name="errorImage" value="<fmt:message key="error.image.validation"/>"/>
																			<td align="left"><input type="text" id="randNum" name="randNum" class="inputbutton2" maxlength="7" size="7" tabindex="3" >
																			<img id="randImage" name="randImage" src="<ls:templateResource item='/captcha.svl'/>"/></td>
																		</tr>					
																		<tr><td></td><td align="left"><fmt:message key="change.random1"/> &nbsp;<a href="javascript:void(0)" onclick="javascript:changeRandImg('${pageContext.request.contextPath}')" style="font-weight: bold;"><fmt:message key="change.random2"/></a></td></tr>
																		<tr>										
																			<td colspan="2">
																				<div align="center">
																				<%
																				if(!(PropertiesUtil.getObject(ParameterEnum.COMMENT_LEVEL,String.class)).equals("ANONYMOUS_COMMENT")){
																				 %>
																				<c:choose>
																					<c:when test="${sessionScope.SPRING_SECURITY_LAST_USERNAME == null}"><input type="button" value='<fmt:message key="logon.first"/>' class="s" disabled="disabled"></c:when>
																					<c:otherwise><input type="button" value="<fmt:message key="submit"/>" class="s" onclick="addProdcutComment();"/></c:otherwise>
																				</c:choose>
																				<% }else{%>
																					<input type="button" value="<fmt:message key="submit"/>" class="s" onclick="addProdcutComment();"/>
																				<% }%>
																				</div>
																			</td>
																		</tr>
																	</table>

								</td>
							</tr>
						</table>
						<script type="text/javascript">
   function checkform(content) {
		{
		if(content.value == null || content.value == ''){
			alert('<fmt:message key="errors.required"><fmt:param value=""/></fmt:message>');
			document.getElementById("content").focus();
			return false;
		}else if(content.value.length <3){
			alert('<fmt:message key="errors.minlength"><fmt:param value=""/><fmt:param value="3"/></fmt:message>');
			return false;
		}else if(content.value.length >200){
		alert('<fmt:message key="errors.minlength"><fmt:param value=""/><fmt:param value="200"/></fmt:message>');
		return false;
			}
		}		
		return true ;
  }
  
  function addProdcutComment(){
	var prodId = ${prod.prodId};
	var content = document.getElementById("content"); 
	if(validateRandNum('${pageContext.request.contextPath}')){
	if(checkform(content)){
             CommonService.addProdcutComment(prodId,content.value,function(dataArray){
                try{
                if("NOLOGON" == dataArray){
                	alert('<fmt:message key="no.login.hint" />');
                }else if("NOBUYED" == dataArray){
                	alert('<fmt:message key="error.nobuyed" />');
                }else if("NOCOMMENT" == dataArray){
                	alert('<fmt:message key="error.nocomment" />');
                }else{
                document.getElementById("productComment").innerHTML =dataArray;
                content.value = "";
                changeRandImg('${pageContext.request.contextPath}');
                alert('<fmt:message key="comment.success" />');
                }
               }catch(e){}
           }) ;
		}
	}
}
   
</script>