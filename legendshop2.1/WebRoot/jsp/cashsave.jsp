<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/common/jsp/taglib.jsp'%>
<html>
<head>
		<script src="${root}/common/js/jquery.js" type="text/javascript"></script>
		<script src="${root}/common/js/jquery.validate.js" type="text/javascript"></script>
		<link rel="stylesheet" type="text/css" media="screen" href="${root}/common/css/indexJpgForm.css" />
	<script type="text/javascript" src="${root}/dwr/engine.js" ></script>
	<script type="text/javascript" src="${root}/dwr/util.js" ></script>
<%@include file='/common/jsp/common.jsp'%>
<script language="JavaScript">
jQuery.validator.setDefaults({

	});
	
	jQuery(document).ready(function() {
	jQuery("#form1").validate({
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
				required: '<bean:message key="errors.orderName.required"/>',
				minlength: '<bean:message key="errors.minlength" arg0="" arg1="3"/>'
			},
			userPostcode:{
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
// -->
</script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><bean:message key="your.subNember"/>: ${subNember}</title>
    <link href="${root}/common/style/style_${shopDetail.colorStyle}.css" rel="stylesheet" type="text/css" />
    <link href="${root}/common/style/global_${shopDetail.colorStyle}.css" rel="stylesheet" type="text/css" />
</head> 
<body topmargin="0">
  <c:choose>
   <c:when test="${userName == null}">
		<logic:forward name="nologon"/>
   </c:when>
   	<c:otherwise>
   		
  <c:choose>
   <c:when test="${subNember == null}">
   <center>
	   &nbsp;<br>
      <br>
	<a href=# onClick="javascript:window.close();"><bean:message key="close"/></a>
	</center>
   </c:when>
   	<c:otherwise>
   	<table width="500" border="0"  cellspacing="0" cellpadding="0" align="center" style="margin-top: 5px;">

              <tr> 
                <td height="28" class="headerbg" align="center">
                <font color="white"><bean:message key="your.subNember"/>: <b>${subNember}</b></font></td>
              </tr> 
              <tr> 
                <td colspan="2"> 
               <form method="POST" action="saveto.do" id="form1" name="form1">
               <input name="userName" value="${member.userName}" type="hidden"/>
               <input name="basketId" value="${basketId}" type="hidden"/>
               <input name="subNember" value="${subNember}" type="hidden"/>
               <input name="total" value="${total}" type="hidden"/>
<!-- begin -->
<table width="100%" border="0" cellspacing="0" cellpadding="0" >
                      <tr> 
                        <td>
                        <table width="100%"  border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td class="bodybg"><br>
                                　 <bean:message key="save.sub.tip"/><br> <br> 
                                <div align="center">
                               
                                <table width="100%" border="0" cellpadding="1" cellspacing="1">
                                  <tr> 
                                    <td height="25" colspan="2" style="margin-left: 20px">&nbsp;&nbsp;<b><bean:message key="input.detail"/></b></td>
                                  </tr>
                                  <tr> 
                                    <td width="19%" height="25" class="bodybg"> 
                                      <div align="right"><font color="#ff0000">*</font><bean:message key="name"/>：</div>
                                    </td>
                                    <td width="81%" height="25" class="bodybg">
                                    <p>
                                      <input style="FONT-SIZE: 12px; WIDTH: 125px" type="text" name="orderName" size="30" value="${member.nickName}" maxlength="50">
                                    </p>
                                    </td>
                                  </tr>
                                 
                                  <tr> 
                                    <td width="19%" height="25" class="bodybg"> 
                                      <div align="right"><font color="#ff0000">*</font><bean:message key="postcode"/>：</div>
                                    </td>
                                    <td height="25" class="bodybg">
                                    <p>
                                    <input maxlength="15" style="FONT-SIZE: 12px; WIDTH: 125px" type="text" name="userPostcode" size="30"  value="${member.userPostcode}">
                                    </p>
                                    </td>
                                  </tr>
                                  <tr> 
                                    <td width="19%" height="25" class="bodybg"> 
                                      <div align="right"><font color="#ff0000">*</font><bean:message key="Phone"/></div>
                                    </td>
                                    <td height="25" class="bodybg">
                                    <p>
                                    <input maxlength="30" style="FONT-SIZE: 12px; WIDTH: 125px" type="text" name="userTel" size="30"  value="${member.userTel}">
                                    </p>
                                    </td>
                                  </tr>
                                  <tr> 
                                    <td width="19%" height="25" class="bodybg"> 
                                      <div align="right"><font color="#ff0000">*</font>E-mail：</div>
                                    </td>
                                    <td height="25" class="bodybg">
                                    <p>
                                    <input maxlength="50"  style="FONT-SIZE: 12px; WIDTH: 125px" type="text" name="userMail" id="userMail" size="30"  value="${member.userMail}">
                                    </p>
                                    </td>
                                  </tr>
                                  <tr style="display: none"> 
                                    <td width="19%" height="19" class="bodybg">
                                      
                                    <br><br><br></td>
                                    <td height="19" class="bodybg">nam<select size="1" name="payType" style="FONT-SIZE: 12px; WIDTH: 125px">
                                        <option value="货到付款">货到付款</option>
                                        <option value="银行转帐">银行转帐</option>
                                        <option value="邮政汇款">邮政汇款</option>
                                      </select>
                                    <br><br><br></td>
                                  </tr>
                                 <tr> 
                                    <td width="19%" height="25" class="bodybg"> 
                                      <div align="right"><font color="#ff0000">*</font><bean:message key="address"/>：</div>
                                    </td>
                                    <td height="25" class="bodybg">
                                    <p>
                                    <input maxlength="200" style="FONT-SIZE: 12px; WIDTH: 250px" type="text" name="userAdds" size="50" value="${member.userAdds}">
                                    </p>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td width="19%" height="50" class="bodybg">
                                      <div align="right"><bean:message key="memo"/>：<br>
                                        </div>
                                    </td>
                                    <td height="50" class="bodybg"><textarea  style="FONT-SIZE: 12px; WIDTH: 250px" name="other" cols="100" rows="5"></textarea>
                                    </td>
                                  </tr>
          	
                                  <tr height="35"> 
                                    <td colspan="2" class="bodybg"> 
                                      <table width="40%" border="0" align="center" cellpadding="0" cellspacing="0">
                                        <tr> 
                                          <td> 
                                            <div align="center"> 
												<input type="submit" value="<bean:message key="submit"/>" class="s" />
                                            </div>
                                          </td>
                                          <td> 
                                            <div align="center"> 
                                            <input type="reset" value="<bean:message key="cancel"/>" class="s" />
                                        
                                            </div>
                                          </td>
                                        </tr>
                                      </table>
                                    </td>
                                  </tr>
                                </table>
 
                          </table> </td>
                      </tr>
                    </table>   

</form>
                  </td>
              </tr>
            </table>
	</c:otherwise>
</c:choose>
	</c:otherwise>
</c:choose>
</body>
</html>