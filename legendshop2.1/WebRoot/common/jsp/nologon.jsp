<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/common/jsp/taglib.jsp'%>
<html>
<head>
<%@include file='/common/jsp/common.jsp'%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><bean:message key="operation.error"/></title>
    <link href="${root}/common/style/style_${shopDetail.colorStyle}.css" rel="stylesheet" type="text/css" />
    <link href="${root}/common/style/global_${shopDetail.colorStyle}.css" rel="stylesheet" type="text/css" />
</head> 
<body topmargin="0">

<table width="500" border="0" cellspacing="2" cellpadding="2" align="center" >
  <tr valign="middle" height="100px"> 
    <td align="center" style="font-family: serif;font-size: 1.5em;"><bean:message key="no.login.hint"/>:</td>
  </tr>
  <tr> 
    <td height="80px"  align="left" style="margin-left: 80px;">
     <bean:message key="nologin.hint" arg0="${root}/login.do" arg1="${root}/reg.do"/>
      </td>
  </tr>
</table>
</body>
</html>
