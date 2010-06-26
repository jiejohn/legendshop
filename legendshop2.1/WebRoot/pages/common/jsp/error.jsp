<%@ page language="java" isErrorPage="true"  contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/jsp/common.jsp"%>
<%@include file='/common/jsp/taglib.jsp'%>
<html >
<head>
<html:base/>
<title><bean:message key="operation.fail"/></title>
    <link href="${root}/common/style/style_${shopDetail.colorStyle}.css" rel="stylesheet" type="text/css" />
    <link href="${root}/common/style/global_${shopDetail.colorStyle}.css" rel="stylesheet" type="text/css" />
</head>
<body>
<br>
<center>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableBorder">
          <tr height="25">
            <th class="headerbg" > <bean:message key="SYSTEM_ERROR"/></th>
          </tr>
       <tr> 
    <td height="195" colspan="4" valign="middle"> <div align="center"><img src="${root}/img/error.gif" width="128" height="112"></div></td>
  </tr>
      <tr>
       <td align="center">
         ${ERROR_MESSAGE}<br>
        <!--  
         ${exception}
         -->
       </td>
     </tr>
          <tr height="25" class="headerbg" >
            <td >
            <div align="center">
            <a href="javascript:history.go(-1)"><bean:message key="go.back"/></a>
            
            </div>
            </td>
          </tr>
  </table>
</center>
</body>
</html>



