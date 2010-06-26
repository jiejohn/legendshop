<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<html>
<head>
		<script src="${root}/common/js/jquery.validate.js" type="text/javascript"></script>
		<link rel="stylesheet" type="text/css" media="screen" href="${root}/common/css/indexJpgForm.css" />
		<script type='text/javascript' src='${root}/dwr/interface/CommonService.js'></script>
		<script type='text/javascript' src='${root}/dwr/engine.js'></script>
<title>USER Register</title>
<script type="text/javascript">
	jQuery.validator.setDefaults({

	});
	
	jQuery(document).ready(function() {
	jQuery("#userRegForm").validate({
		rules: {
			name: {
				required: true,
				minlength: 4
			},
			nickName: "required",
			password: {
				required: true,
				minlength: 6
			},
			password2: {
				required: true,
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
            userMail:{
               required: '<bean:message key="errors.required" arg0=""/>',
               email: '<bean:message key="errors.email" arg0=""/>'
            },
            agreement:{
               required: '<bean:message key="errors.required" arg0=""/>'
            }
        }
	});
	
	// check if confirm password is still valid after password changed
	jQuery("#password").blur(function() {
		jQuery("#password2").valid();
	})
});

	// propose username by combining first- and lastname
	function checkName() {
		var result = true;
		DWREngine.setAsync(false);
	if(jQuery("#name").val()!=null&&jQuery("#name").val()!=''){
	if(jQuery("#name").val().length>=4){
		CommonService.isUserExist(jQuery("#name").val(), function(retData){
	       if(retData){
		      document.getElementById("userAreardyExists").innerHTML  = "<bean:message key='errors.user.exists' arg0='"+ jQuery("#name").val() +"'/>";
		      document.getElementById("userAreardyExists").style.color = "red";
		      document.getElementById("name").focus();
		      result = false;
	       }else{
	    	   document.getElementById("userAreardyExists").innerHTML  = "<bean:message key='errors.user.ok' arg0='"+ jQuery("#name").val() +"'/>";
	    	   document.getElementById("userAreardyExists").style.color = "green";
	       }
	       
	    }) ;
	    }else{
	    	document.getElementById("userAreardyExists").innerHTML = "";
		    }
	    }
    DWREngine.setAsync(true);
    return result;
	}

			
</script>
</head>
<body>
   <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td width="100%" height="28" class="titlebg" >
                  <div align="center"><font color="#FFFFFF"><b><bean:message key="register.title"/></b></font></div></td>
                </tr>
                <tr> 
                  <td height="2" bgcolor="#ECECEC"><table width="100%" border="0" cellspacing="1" cellpadding="0" height="100%">
                      <tr> 
                        <td align="left">
                        <form action="${root}/userReg.do" method="post" name="userRegForm" id="userRegForm" onsubmit="return checkName();">
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <bean:message key="register.hint"/><br>
                            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" style="margin-bottom: 6">
                              <tr> 
                                <td width="33%" height="25"><div align="right">
                               <font color="#ff0000">*</font><bean:message key="user.name"/>：</div></td>
                                <td width="67%"><div align="left">
                                <input style="FONT-SIZE: 12px; WIDTH: 150px" type="text" name="name" id="name" class="input" onchange="javascript:checkName();"/>
                                    <bean:message key="openShop"/><input type="checkbox" id="openShop" name="openShop"/>
                                    <div id="userAreardyExists"></div>
                                  </div></td>
                              </tr>
                              <tr> 
                                <td height="25"><div align="right"><font color="#ff0000">*</font><bean:message key="real.name"/>：</div></td>
                                <td height="25">
                                  <div align="left">
                                     <input style="FONT-SIZE: 12px; WIDTH: 150px" type="text" name="nickName" id="nickName" size="20" class=input>
                                  </div>
                                  </td>
                              </tr>
                              <tr> 
                                <td height="25"><div align="right">
                                <font color="#ff0000">*</font><bean:message key="password"/>：</div></td>
                                <td height="25"><div align="left"> 
                                    <input style="FONT-SIZE: 12px; WIDTH: 150px" type="password" name="password" id="password" size="20" class=input>
                                  </div></td>
                              </tr>
                              <tr>
                                <td height="25"><div align="right"><font color="#ff0000">*</font><bean:message key="Confirmation"/>：</div></td>
                                <td height="25"><div align="left"> 
                                    <input style="FONT-SIZE: 12px; WIDTH: 150px" type="password" name="password2" id="password2" size="20" class=input>
                                  </div></td>
                              </tr>
                              <tr> 
                                <td height="25"><div align="right"><font color="#ff0000">*</font>E-mail ：</div></td>
                                <td height="25"><div align="left"> 
                                    <input style="FONT-SIZE: 12px; WIDTH: 150px" type="text" name="userMail" id="userMail" size="20" class=input>
                                  </div></td>
                              </tr>
                              <tr> 
                                <td height="25"><div align="right"><bean:message key="Fax"/>：</div></td>
                                <td height="25"><div align="left"> 
                                    <input style="FONT-SIZE: 12px; WIDTH: 150px" type="text" name="fax" id="fax" size="20" class=input>
                                  </div></td>
                              </tr>
                              
                              <tr> 
                                <td height="25"><div align="right"><bean:message key="Telphone"/>：</div></td>
                                <td height="25"><div align="left"> 
                                    <input style="FONT-SIZE: 12px; WIDTH: 150px" type="text" name="userTel" id="userTel" size="20" class=input>
                                  </div></td>
                              </tr>
                              <tr> 
                                <td height="25"><div align="right"><bean:message key="PostCode"/>：</div></td>
                                <td height="25"><div align="left"> 
                                   <input style="FONT-SIZE: 12px; WIDTH: 150px" type="text" name="userPostcode" id="userPostcode" size="20" class=input>
                                  </div></td>
                              </tr>
 							  <tr> 
                                <td height="25"><div align="right"><bean:message key="Address"/>：</div></td>
                                <td height="25"><div align="left"> 
                                   <input style="FONT-SIZE: 12px; WIDTH: 250px" type="text" name="userAdds" id="userAdds" size="20" class=input>
                                  </div></td>
                              </tr>  
                              <tr> 
                                <td height="25"><div align="right"></div></td>
                                <td height="25"><div align="left"> 
                                   <p><a href="${root}/regItem.html" target="_blank"><bean:message key="UserAgreement"/></a><input type="checkbox" id="agreement" name="agreement" checked="checked"/>
                                    </p>
                                  </div></td>
                              </tr>                              
                              <tr> 
                                <td height="35" colspan="2"><div align="center"> 
                                    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                                      <tr> 
                                        <td><div align="left"> 
                                            <input type="submit" value="<bean:message key="submit"/>" class="s" style="margin-left: 250px"/>
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
             </body>
           </html>