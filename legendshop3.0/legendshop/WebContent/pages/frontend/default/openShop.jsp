<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="com.legendshop.core.helper.PropertiesUtil"%>
<%@page import="com.legendshop.core.constant.ParameterEnum"%>
<%@include file='/pages/common/taglib.jsp'%>
<%@include file='/pages/common/common.jsp'%>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/common/css/indexJpgForm.css" />
<style type="text/css">
	.input {FONT-SIZE: 12px; WIDTH: 150px;margin-left: 10px}
</style>
<script type="text/javascript">
jQuery.validator.setDefaults({

	});

	jQuery(document).ready(function() {
	jQuery("#openShopForm").validate({
		rules: {
			"shopDetail.sitename": {
				required: true,
				minlength: 2
			},
            "shopDetail.ymaddr": {
				required: true,
				minlength: 2
			},
		    "shopDetail.idCardNum": {
				required: true,
				minlength: 15
			},
		    "shopDetail.type": {
				required: true
			},
		    "shopDetail.idCardPicFile": {
				required: true
			},			
		    "shopDetail.trafficPicFile": {
				required: "#type:checked"
			}		
			
		},
		messages: {
            "shopDetail.sitename": {
               required: '<fmt:message key="errors.required"><fmt:param value=""/></fmt:message>',
               minlength: '<fmt:message key="errors.minlength"><fmt:param value=""/><fmt:param value="2"/></fmt:message>'
            },   
            "shopDetail.ymaddr":{
               required: '<fmt:message key="errors.required"><fmt:param value=""/></fmt:message>',
               minlength: '<fmt:message key="errors.minlength"><fmt:param value=""/><fmt:param value="2"/></fmt:message>'
            }, 
            "shopDetail.idCardNum": {
               required: '<fmt:message key="errors.required"><fmt:param value=""/></fmt:message>',
               minlength: '<fmt:message key="errors.minlength"><fmt:param value=""/><fmt:param value="15"/></fmt:message>'
            }, 
            "shopDetail.type": {
               required: '<fmt:message key="errors.required"><fmt:param value=""/></fmt:message>'
            },             
 		    "shopDetail.idCardPicFile":  {
				required: '<fmt:message key="errors.required"><fmt:param value=""/></fmt:message>'
			},		           
 		    "shopDetail.trafficPicFile": {
				required: '<fmt:message key="errors.required"><fmt:param value=""/></fmt:message>'
			}
        }
	});
	
});

   function changeType(obj){
 	if(obj == 1){
 		document.getElementById("trafficPicDiv").style.display="block";
 	}else{
 	 	document.getElementById("trafficPicDiv").style.display="none";
 	}
 }	
</script>
<%if(PropertiesUtil.getObject(ParameterEnum.OPEN_SHOP,Boolean.class)){ %>
<form action="${pageContext.request.contextPath}/addShop${applicationScope.WEB_SUFFIX}" method="post" name="openShopForm" id="openShopForm" enctype="multipart/form-data">
   <table width="100%"  cellpadding="0" cellspacing="0" class="tables">
                               <tr>
				                  <td class="titlebg"><fmt:message key="register.shop"/></td>
				                </tr>
                               <tr>
	                              <td align="left">
	                           <div style="margin-left: 100px">
			                       <fmt:message key="reality.material"/>
                               </div>
                               <br>
	                              </td>
                              </tr>
                              <tr><td>
                              <table width="100%">
                               <tr>
                                <td width="33%"><div align="right">
                                <font color="#ff0000">*</font><fmt:message key="mall.name"/></div></td>
                                <td height="27"><div align="left">
                                    <input name="shopDetail.sitename" id="sitename" class="input"/>
                                  </div></td>
                              </tr>
                              <tr>
                                <td width="33%"><div align="right"><font color="#ff0000">*</font><fmt:message key="idcard.number"/></div></td>
                                <td height="27"><div align="left"> 
                                    <input type="text" name="shopDetail.idCardNum" id="idCardNum" class="input" maxlength="20" />
                                  </div></td>
                              </tr> 
                              <tr>
                                <td width="33%"><div align="right"><font color="#ff0000">*</font><fmt:message key="mall.type"/></div></td>
                                <td height="27"><div align="left"> 
                                    &nbsp;&nbsp;<fmt:message key="type.business"/><input type="radio" name="shopDetail.type" id="type" value="1" onclick="javascript:changeType(1)"/>&nbsp;
                                         <fmt:message key="type.individual"/><input type="radio" name="shopDetail.type" value="0" onclick="javascript:changeType(0)"/>
                                  </div></td>
                              </tr>

                              <tr>
                                <td width="33%"><div align="right"><font color="#ff0000">*</font><fmt:message key="upload.IDcard.pic"/></div></td>
                                <td height="27"><div align="left"> 
                                    <input type="file" style="FONT-SIZE: 12px; WIDTH: 250px;margin-left: 10px" name="shopDetail.idCardPicFile" id="trafficPicFile" class="input" />
                                  </div></td>
                              </tr>
                              <tr>
                                <td colspan="2">
                                  <div id="trafficPicDiv" style="display: none">
                                <table width="100%" cellpadding="0" cellspacing="0"><tr>
                                   <td width="33%">
	                                 <div align="right"><font color="#ff0000">*</font><fmt:message key="upload.business.pic"/><br>
	                                </div>  
	                                </td>
	                                <td>
	                                <div align="left">
	                                <input type="file" style="FONT-SIZE: 12px; WIDTH: 250px;margin-left: 10px;" name="shopDetail.trafficPicFile" id="trafficPicFile" class="input" />
	                                </div></td>
	                                </tr>
                                </table>
                    			</div>
                                </td>
                              </tr> 
                              </table>
                              </td></tr>
                              
                         <tr> 
                                <td height="35"><div align="center"> 
                                    <table width="100%" align="center" cellpadding="0" cellspacing="0">
                                      <tr> 
                                        <td><div align="left" style="margin-left: 100px"> 
                                            <input type="submit" value="<fmt:message key="submit"/>" class="s" style="margin-left: 250px"/>
                                            <input type="reset" value="<fmt:message key="cancel"/>" class="s">
                                          </div>
                                          </td>
                                </tr>
                </table>
</form>
<% } else {%>
Not Support for this version!
<%}%>