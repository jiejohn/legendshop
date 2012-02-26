<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/pages/common/taglib.jsp'%>
<%@include file='/pages/common/common.jsp'%>
<%@ taglib uri="/WEB-INF/tld/options.tld" prefix="option"%>
	<script src="${pageContext.request.contextPath}/common/js/jquery.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/jquery.validate.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/common/css/indexJpgForm.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/dwr/engine.js" ></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/dwr/util.js" ></script>
<script language="JavaScript">
jQuery.validator.setDefaults({

	});
	
	jQuery(document).ready(function() {
	jQuery("#cashSaveForm").validate({
		rules: {
			orderName: {
				required: true,
				minlength: 3
			},
			userPostcode: "required",
			userTel: {
				required: true,
				minlength: 8,
				number: true
			},
			userMail: {
				required: true,
				email: true
			},
			userAdds: {
				required: true,
				minlength: 6
			}
		},
		messages: {
			orderName: {
				required: '<fmt:message key="errors.orderName.required"/>',
				minlength: '<fmt:message key="errors.minlength"><fmt:param value=""/><fmt:param value="3"/></fmt:message>'
			},
			userPostcode:{
               required: '<fmt:message key="errors.required"><fmt:param value=""/></fmt:message>'
            },
            userTel:{
               required: '<fmt:message key="errors.required"><fmt:param value=""/></fmt:message>',
               minlength: '<fmt:message key="errors.minlength"><fmt:param value=""/><fmt:param value="8"/></fmt:message>',
               number: '<fmt:message key="errors.phone"><fmt:param value=""/></fmt:message>'
            },
            userAdds:{
              required: '<fmt:message key="errors.required"><fmt:param value=""/></fmt:message>',
              minlength: '<fmt:message key="errors.minlength"><fmt:param value=""/><fmt:param value="3"/></fmt:message>'
            }
		}
		});
	});
	
	function submitCashSaveForm(){
    	document.getElementById("cashSaveForm").submit();
    }
</script>
    <link href="${pageContext.request.contextPath}/common/style/style_${shopDetail.colorStyle}.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/common/style/global_${shopDetail.colorStyle}.css" rel="stylesheet" type="text/css" />
  <c:choose>
   <c:when test="${sessionScope.SPRING_SECURITY_LAST_USERNAME == null}">
		<logic:forward name="nologon"/>
   </c:when>
   	<c:otherwise>
   	<form action="${pageContext.request.contextPath}/sub/save${applicationScope.WEB_SUFFIX}" method="post" id="cashSaveForm" name="cashSaveForm">
   	<table width="100%" class="tablesnoborder" cellpadding="0" cellspacing="0">
              <tr> 
                <td class="titlebg"><fmt:message key="order.detail"/></td>
              </tr> 
              <tr> 
                <td> 
               <input name="userName" value="${member.userName}" type="hidden"/>
               <input name="basketId" value="${basketId}" type="hidden"/>
               <input name="subNember" value="${subNember}" type="hidden"/>
               <input name="total" value="${requestScope.totalcash}" type="hidden"/>
               <input type="hidden" name="SESSION_TOKEN" value="${SESSION_TOKEN}" />
				<table width="100%" cellpadding="0" cellspacing="0">
                            <tr>
                              <td align="left"><br>
                                　 <fmt:message key="save.sub.tip"/><br> <br> 
                                <table width="100%" cellpadding="0" cellspacing="0">
                                  <tr> 
                                    <td height="25" colspan="2" style="margin-left: 20px">&nbsp;&nbsp;<b><fmt:message key="input.detail"/></b></td>
                                  </tr>
                                  <tr> 
                                    <td width="19%" height="30"> 
                                      <div align="right"><font color="#ff0000">*</font><fmt:message key="name"/>：</div>
                                    </td>
                                    <td width="81%">
                                      <input class="input" type="text" name="orderName" size="30" value="${member.nickName}" maxlength="50">
                                    </td>
                                  </tr>
                                  <tr> 
                                    <td width="19%" height="30"> 
                                      <div align="right"><font color="#ff0000">*</font><fmt:message key="postcode"/>：</div>
                                    </td>
                                    <td>
                                    <input maxlength="15" class="input"type="text" name="userPostcode" size="30"  value="${member.userPostcode}">
                                    </td>
                                  </tr>
                                  <tr> 
                                    <td width="19%" height="30"> 
                                      <div align="right"><font color="#ff0000">*</font><fmt:message key="Phone"/>：</div>
                                    </td>
                                    <td>
                                    <input maxlength="30" class="input" type="text" name="userTel" size="30"  value="${member.userTel}">
                                    </td>
                                  </tr>
                                 <tr> 
                                    <td width="19%" height="30">
                                      <div align="right"><font color="#ff0000">*</font><fmt:message key="address"/>：</div>
                                    </td>
                                    <td height="25">
                                    <input maxlength="200" style="WIDTH: 300px" class="input" type="text" name="userAdds" value="${member.userAdds}">
                                    <a href="${pageContext.request.contextPath}/myaccount${applicationScope.WEB_SUFFIX}" target="_blank"><fmt:message key="modify.address"/></a>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td width="19%" height="50">
                                      <div align="right"><fmt:message key="memo"/>：<br>
                                        </div>
                                    </td>
                                    <td height="50"><textarea style="WIDTH: 300px;font-size: 10pt" class="input2" name="other" cols="100" rows="5"></textarea>
                                    <br>(<fmt:message key="cashsave.description"/>)
                                    </td>
                                  </tr>    	
                                  <tr height="35"> 
                                    <td colspan="2"> 
                                      <table width="40%" align="center" cellpadding="0" cellspacing="0">
                                        <tr> 
                                          <td> 
                                            <div align="center"> 
												<input type="submit" value='<fmt:message key="order.hint"/>' class="s"/>
                                            </div>
                                          </td>
                                          <td> 
                                            <div align="center"> 
                                            <input type="reset" value="<fmt:message key="cancel"/>" class="s" />
                                            </div>
                                          </td>
                                        </tr>
                                      </table>
                                    </td>
                                  </tr>
                                </table>
                              </td>
                                </tr>
                          </table>
				
                  </td>
              </tr>
            </table>
            </form>
	</c:otherwise>
</c:choose>