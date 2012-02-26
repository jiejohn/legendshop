<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/common.jsp"%>
<%@ include file="/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
<html:html>
<head>
<LINK title=Style href="${pageContext.request.contextPath}/common/css/back_style.css" type=text/css rel=stylesheet>
<title>创建角色</title>
</head>
<body>
<br>
<br>
<html:form action="member/right/saveRole${applicationScope.WEB_SUFFIX}" onsubmit="return checkform()">
  <table width="260" height="129"  align="center" bordercolor="#0099FF" class="tableBorder">
    <tr>
      <th colspan="2">创建角色</th>
    </tr>
    <tr>
      <td class="forumRow"><div align="center">名称 <font color="#ff0000">*</font></div></td>
      <td class="forumRow"><input name="role.name" type="text" value=""></td>
    </tr>
    <tr>
      <td class="forumRow"><div align="center">角色名称 <font color="#ff0000">*</font></div></td>
      <td class="forumRow"><input name="role.roleType" type="text" value=""></td>
    </tr>
    <tr>
      <td class="forumRow"><div align="center">状态</div></td>
      <td class="forumRow">
        <p>
          <label>
            <input type="radio" name="role.enabled" value="1" checked="checked"/>
            有效</label> &nbsp; &nbsp; 
          <label>
            <input type="radio" name="role.enabled" value="0" />
            无效</label>
          <br />
        </p>     </td>
    </tr>
    <tr>
      <td class="forumRow"><div align="center">备注 </div></td>
      <td class="forumRow"><input name="role.note" type="text" value=""></td>
    </tr>
    <tr>
      <td colspan="2" class="forumRow"><div align="center">        
        <input type="submit" value="添加"/>
        <html:reset  value="重置"/>
		          
      </div></td>
    </tr>
  </table>
</html:form>
</body>
</html:html>