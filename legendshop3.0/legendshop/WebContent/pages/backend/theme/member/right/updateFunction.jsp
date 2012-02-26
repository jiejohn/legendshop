<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/common.jsp"%>
<%@ include file="/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
<html:html>
<head>
<LINK title=Style href="${pageContext.request.contextPath}/common/css/back_style.css" type=text/css rel=stylesheet>
<title>修改权限</title>
</head>


<body>
<br>
<br>
<div align="center">
  <table width="386" height="185" >
    <tr>
      <td width="380" height="181"><html:form  action="/member/right/updateFunctionById${applicationScope.WEB_SUFFIX}">
        <table width="380"  align="center" bordercolor="#66CCFF" class="tableBorder" style="border-collapse:collapse " >
         
		  <tr>
            <th colspan="2">   
                   修改权限
            </th>
          </tr>
          
		 <tr style="display: none">
            <td width="163" height="29" align="center" class="forumRow"> 主键：</td>
            <td width="211" align="center" class="forumRow">
			<input type="text" name="function.id" value=<%=request.getParameter("id")%> readonly="true">
			</td>
        </tr>
          <tr>
            <td height="27" align="center" class="forumRow">名称 <font color="#ff0000">*</font></td>
            <td align="center" class="forumRow"><input type="text" name="function.name" value="<bean:write name="function" property="name"/>"></td>
          </tr>
          <tr>
            <td height="27" align="center" class="forumRow">权限名称 <font color="#ff0000">*</font></td>
            <td align="center" class="forumRow"><input type="text" name="function.protectFunction" value="<bean:write name="function" property="protectFunction"/>"></td>
          </tr>
          <tr>
            <td height="27" align="center" class="forumRow">备注：</td>
            <td align="center" class="forumRow"><input type="text" name="function.note" value="<bean:write name="function" property="note"/>"></td>
          </tr>
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

