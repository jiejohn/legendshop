<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/common.jsp"%>
<%@ include file="/pages/common/taglib.jsp"%>
<html:html>
<head>
<LINK title=Style href="${pageContext.request.contextPath}/common/css/back_style.css" type=text/css rel=stylesheet>
<title>修改角色</title>
</head>


<body>
<br>
<br>
<div align="center">
  <table width="386" height="185" >
    <tr>
      <td width="380" height="181">
      <html:form  action="/member/user/updateUserPassowrd${applicationScope.WEB_SUFFIX}">
       <input type="hidden" name="user.id" value="<bean:write name="user" property="id"/>">
        <table width="380"  align="center" bordercolor="#66CCFF" class="tableBorder" style="border-collapse:collapse " >
		  <tr>
            <th colspan="2">修改用户</th>
          </tr>
          <tr>
            <td height="27" align="center" class="forumRow">名称</td>
            <td align="center" class="forumRow"><input type="text" name="user.name" readonly="true" value="<bean:write name="user" property="name"/>"></td>
          </tr>
          <tr>
            <td height="27" align="center" class="forumRow">密码<font color="#ff0000">*</font></td>
            <td align="center" class="forumRow"><input type="password" name="user.password" value=""></td>
          </tr>
          <tr>
            <td height="27" align="center" class="forumRow">确认密码<font color="#ff0000">*</font></td>
            <td align="center" class="forumRow"><input type="password" name="user.passwordag" value=""></td>
          </tr>
          <!-- 
          <tr>
            <td height="27" align="center" class="forumRow">note：</td>
            <td align="center" class="forumRow"><input type="text" name="user.note" value="<bean:write name="user" property="note"/>"></td>
          </tr>
            -->
          <tr bordercolor="#FFFFFF">
            <td height="42" colspan="2" class="forumRow"><table width="338" >
                <tr>
                  <td width="162" align="center"> <input type="submit" name="Submit" value="修改"></td>
                  <td width="166" align="center"><input type="reset" name="cancel" value="重置"></td>
                </tr>
            </table></td>
          </tr>
</table>
      </html:form></td>
    </tr>
  </table>
</div>
<p>&nbsp; </p>
</body>
</html:html>

