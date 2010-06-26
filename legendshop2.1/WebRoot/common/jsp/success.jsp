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
          <tr height="28px">
            <th class="headerbg"><bean:message key="operation.successful"/></th>
          </tr>
       <tr> 
    <td height="195" valign="middle"> <div align="center"><img src="${root}/img/error.gif" width="128" height="112"></div></td>
  </tr>
      <tr>
       <td align="center">
         ${ERROR_MESSAGE}
       </td>
     </tr>
          <tr height="28px">
            <td class="headerbg">
            <div align="center">
            <c:if test="${not empty CALL_BACK}">
                 <a href="${root}/${CALL_BACK}"><bean:message key="go.back"/></a>
            </c:if>
              <a href="javascript:history.go(-1)"><bean:message key="previous.page"/></a>
              </div>
            </td>
          </tr>
  </table>
</center>
</body>
</html>
