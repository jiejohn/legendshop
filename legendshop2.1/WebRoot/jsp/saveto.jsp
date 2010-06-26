<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file='/common/jsp/taglib.jsp'%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file='/common/jsp/common.jsp'%>
<title><bean:message key="operation.successful"/></title>
    <link href="${root}/common/style/style_${shopDetail.colorStyle}.css" rel="stylesheet" type="text/css" />
    <link href="${root}/common/style/global_${shopDetail.colorStyle}.css" rel="stylesheet" type="text/css" />
</head> 
<body topmargin="0">
  <c:choose>
   <c:when test="${userName == null}">
		<logic:forward name="nologon"/>
   </c:when>
   	<c:otherwise>
   	<html:errors/>
    <table border="0" width="500" cellspacing="0" cellpadding="0" align="center" style="margin-top: 5px;">
      <tr> 
        <td height="28" class="headerbg" align="center"> 
         <font color="white"> <bean:message key="your.subNember"/>：<b>${subNember}</b></font>
          
        </td>
      </tr>
      <tr> 
        <td>
        <table width="100%" height="34" border="0" cellpadding="0" cellspacing="0">
              <td valign="bottom" class="bodybg" ><br>
                　<font size="+1"><b><bean:message key="operation.successful"/></b></font><br> <br>
                <div align="center">
                  <center>
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                  <tr> 
                    <td width="20%" height="25"> 
                      <div align="right"><bean:message key="orderer"/>：</div>
                    </td>
                    <td width="80%" height="25"> 
                      <div align="left">${member.orderName} </div>
                    </td>
                  </tr>
                  <tr> 
                    <td  height="25"> 
                      <div align="right"><bean:message key="address"/>：</div>
                    </td>
                    <td height="25"> 
                      <div align="left">${member.userAdds}</div>
                    </td>
                  </tr>
                  <tr> 
                    <td height="25"> 
                      <div align="right"><bean:message key="postcode"/>：</div>
                    </td>
                    <td  height="25"> 
                      <div align="left">${member.userPostcode}</div>
                    </td>
                  </tr>
                  <tr> 
                    <td  height="25"> 
                      <div align="right"><bean:message key="Phone"/>：</div>
                    </td>
                    <td  height="25"> 
                      <div align="left">${member.userTel}</div>
                    </td>
                  </tr>
                  <tr> 
                    <td  height="25"> 
                      <div align="right">E-mail：</div>
                    </td>
                    <td  height="25"> 
                      <div align="left">${member.userMail}</div>
                    </td>
                  </tr>
                  <tr style="display: none"> 
                    <td  height="12"> 
                      <div align="right">付款方式：</div>
                    </td>
                    <td  height="25"> 
                      <div align="left">${member.payType}</div>
                    </td>
                  </tr>
                  <tr>
                    <td  height="12">
                      <div align="right"><bean:message key="memo"/>：</div>
                    </td>
                    <td  height="25"> 
                      <div align="left">${member.other}</div>
                    </td>
                  </tr>
                  <tr> 
                    <td colspan="2" height="21"> 
                      <table width="90%" border="0" cellspacing="0" cellpadding="0" align="center">
                        <tr> 
                          <td> 
                            <p style="word-spacing: 2; line-height: 150%; margin-top: 4; margin-bottom: 6"> 
                              <font color="#FF9900"><bean:message key="after.order"/></font></td>
                        </tr>
                      </table>
                    </td>
                  </tr>
                </table>
                  </center>
              </div>

</td>
 
          </table> </td>

      </tr>
       <tr> 
        <td height="28" class="headerbg" align="center" colspan="2" > &nbsp;
            <div align="right" style="display: none">修改收货人信息<input type="submit" value="<bean:message key="go.back"/>" class="s" onclick="javasrcript:history.go(-1);"/></div>
        </td>
      </tr>     
    </table>
    
    	</c:otherwise>
</c:choose>
</body>
</html>