<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="com.done.util.UserManagement"%>
<%@include file='/common/jsp/taglib.jsp'%>
<%@include file='/common/jsp/common.jsp'%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
		<script src="${root}/common/js/jquery.validate.js" type="text/javascript"></script>
		<link rel="stylesheet" type="text/css" media="screen" href="${root}/common/css/indexJpgForm.css" />
<script type="text/javascript">
<!--
 function changePass(obj){
 	if(obj.checked){
 		document.getElementById("pass1").style.display="block";
 	}else{
 	 	document.getElementById("pass1").style.display="none";
 	}
 }
 
 jQuery.validator.setDefaults({

	});
	
	jQuery(document).ready(function() {
	jQuery("#userUpdateForm").validate({
		rules: {
			name: {
				required: true,
				minlength: 5
			},
			nickName: "required",
			passwordOld: {
				required: "#modifyPass:checked"
			},
			password: {
				required: "#modifyPass:checked",
				minlength: 6
			},
			password2: {
				required: "#modifyPass:checked",
				minlength: 6,
				equalTo: "#password"
			},
			userMail: {
				required: true,
				email: true
			}
		},
		messages: {
			name: {
				required: '<bean:message key="username.required"/>',
				minlength: '<bean:message key="username.minlength"/>'
			},
			password: {
				required: '<bean:message key="password.required"/>',
				minlength: '<bean:message key="password.minlength"/>'
			},
			password2: {
				required: '<bean:message key="password.required"/>',
				minlength: '<bean:message key="password.minlength"/>',
				equalTo: '<bean:message key="password.equalTo"/>'
			},
			nickName:{
			   required: '<bean:message key="errors.required" arg0=""/>'
			},
			passwordOld:{
			   required: '<bean:message key="errors.required" arg0=""/>'
			},
			userTel:{
               required: '<bean:message key="errors.required" arg0=""/>',
               minlength: '<bean:message key="errors.minlength" arg0="" arg1="8"/>',
               number: '<bean:message key="errors.phone" arg0=""/>'
            },
            userMail:{
               required: '<bean:message key="errors.required" arg0=""/>',
               email: '<bean:message key="errors.email" arg0=""/>'
            },
            fax:{
               required: '<bean:message key="errors.required" arg0=""/>'
            },
            userPostcode:{
               required: '<bean:message key="errors.required" arg0=""/>'
            },
            userAdds:{
              required: '<bean:message key="errors.required" arg0=""/>',
              minlength: '<bean:message key="errors.minlength" arg0="" arg1="3"/>'
            }
		}
	});
	
	// propose username by combining first- and lastname
	jQuery("#name").blur(function() {
	if(jQuery("#name").val()!=null&&jQuery("#name").val()!=''){
	if(jQuery("#name").val().length>=6){
		CommonService.isUserExist(jQuery("#name").val(), function(retData){
	       if(retData){
	          alert("alerm:user "+jQuery("#name").val()+" had exist!") ;
	          return false;
	       }else{

	       }
	       
	    }) ;
	    }
	    }
	});
	
	// check if confirm password is still valid after password changed
	jQuery("#password").blur(function() {
		jQuery("#password2").valid();
	})
});
 
//-->
</script>
   <table width="100%" border="0" cellspacing="0" cellpadding="0" >
   
                <tr> 
                  <td width="100%" height="24" class="headerbg" >
                  <div align="center"><font color="#FFFFFF"><b><bean:message key="register.title"/></b></font></div></td>
                </tr>
                <tr> 
                  <td height="2" bgcolor="#ECECEC"> <table width="100%" border="0" cellspacing="1" cellpadding="0" height="100%">
                      <tr> 
                        <td align="left"> 
                        <form action="${root}/updateAccount.do" method="post" name="userUpdateForm" id="userUpdateForm">
                        <input type="hidden" id="userId" name="userId" value="${user.userId}">
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<bean:message key="register.hint"/><br>
                            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-bottom: 6">
                              <tr> 
                                <td width="33%" height="25"><div align="right">
                               <font color="#ff0000">*</font><bean:message key="user.name"/>：</div></td>
                                <td width="67%"><div align="left"> 
                                    <input style="FONT-SIZE: 12px; WIDTH: 150px" type="text" name="name" id="name" size="20" class=input value="<%=UserManagement.getUsername()%>" disabled="disabled">
                                 &nbsp; 
                                 <c:if test="${!shopExists}">
	                                  <bean:message key="openShop"/>
	                                  <input type="checkbox" id="openShop" name="openShop"/>
                                  </c:if>
                                  <c:if test="${shopExists}">
                                  	<a href="${DOMAIN_NAME}/shop/${userName}"><bean:message key="myShop"/></a>
                                  </c:if>
                                  </div></td>
                              </tr>
                              <tr> 
                                <td height="25"><div align="right"><font color="#ff0000">*</font><bean:message key="real.name"/>：</div></td>
                                <td height="25"><div align="left"> 
                                    <input style="FONT-SIZE: 12px; WIDTH: 150px" type="text" name="nickName" id="nickName" size="20" class=input value="${user.nickName}">
                                  </div></td>
                              </tr>
                              
                              <tr> 
                                <td height="25"><div align="right"><font color="#ff0000">*</font>E-mail ：</div></td>
                                <td height="25"><div align="left"> 
                                    <input style="FONT-SIZE: 12px; WIDTH: 150px" type="text" name="userMail" id="userMail" size="20" class=input value="${user.userMail}">
                                  </div></td>
                              </tr>
                              <tr> 
                                <td height="25"><div align="right"><bean:message key="Fax"/>：</div></td>
                                <td height="25"><div align="left"><input style="FONT-SIZE: 12px; WIDTH: 150px" type="text" name="fax" id="fax" size="20" class=input value="${user.fax}"></div></td>
                              </tr>
                              
                              <tr> 
                                <td height="25"><div align="right"><bean:message key="Telphone"/>：</div></td>
                                <td height="25"><div align="left"> 
                                    <input style="FONT-SIZE: 12px; WIDTH: 150px" type="text" name="userTel" id="userTel" size="20" class=input value="${user.userTel}">
                                  </div></td>
                              </tr>
                              <tr> 
                                <td height="25"><div align="right"><bean:message key="PostCode"/>：</div></td>
                                <td height="25"><div align="left"> 
                                    <input style="FONT-SIZE: 12px; WIDTH: 150px" type="text" name="userPostcode" id="userPostcode" size="20" class=input value="${user.userPostcode}"> 
                                  </div></td>
                              </tr>
                              <tr> 
                                <td height="25"><div align="right"><bean:message key="Address"/>：</div></td>
                                <td height="25"><div align="left"> 
                                    <input style="FONT-SIZE: 12px; WIDTH: 250px" type="text" name="userAdds" id="userAdds" size="20" class=input value="${user.userAdds}">
                                  </div></td>
                              </tr>
                              <tr> 
                                <td height="25"><div align="right">
                                <bean:message key="modify.password"/>：</div></td>
                                <td height="25"><div align="left">
                                    <input type="checkbox" name="modifyPass" id="modifyPass" onclick="javascript:changePass(this)">
                                  </div></td>
                              </tr>
                              <tr><td colspan="2" width="100%">
                              <div id="pass1" style="display: none">
                                <table  border="0" width="100%"  cellpadding="0" cellspacing="0">
                                   <tr> 
                                <td width="33%" height="25"><div align="right">
                                <font color="#ff0000">*</font><bean:message key="origin.password"/>：</div></td>
                                <td width="67%" height="25"><div align="left">
                                    <input style="FONT-SIZE: 12px; WIDTH: 150px" type="password" name="passwordOld" id="passwordOld" size="20" class="input" />
                                  </div></td>
                              </tr>
                              <tr> 
                                <td height="25"><div align="right">
                                <font color="#ff0000">*</font><bean:message key="password"/>：</div></td>
                                <td height="25"><div align="left"> 
                                    <input style="FONT-SIZE: 12px; WIDTH: 150px" type="password" name="password" id="password" size="20" class="input" />
                                  </div></td>
                              </tr>
                              <tr>
                                <td height="25"><div align="right"><font color="#ff0000">*</font><bean:message key="Confirmation"/>：</div></td>
                                <td height="25"><div align="left"> 
                                    <input style="FONT-SIZE: 12px; WIDTH: 150px" type="password" name="password2" id="password2" size="20" class="input" />
                                  </div></td>
                              </tr> 
                                </table>
                                </div>
                              </td></tr>
                        
                        
                              
                              <tr> 
                                <td height="35" colspan="2"><div align="center"> 
                                    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                                      <tr> 
                                        <td><div align="center">
                                        <auth:auth ifAnyGranted="F_OPERATOR,F_USER">
                                            <input type="submit" value="<bean:message key="submit"/>" class="s"/>
                                        </auth:auth>
                                            <input type="reset" value="<bean:message key="cancel"/>" class="s">
                                          </div></td>
                
                                      </tr>
                                    </table>
                                  </div></td>
                              </tr>
                            </table>
                          </form>
                         </td>
                      </tr>
                    </table></td>
                </tr>
              </table>