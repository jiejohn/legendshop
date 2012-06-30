<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/common.jsp"%>
<%@ include file="/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
<html:html>
<head>
<LINK title=Style href="${pageContext.request.contextPath}/common/css/default/back_style.css" type=text/css rel=stylesheet>
<title>创建权限</title>
</head>
<br>
<br>
<script language="javascript">
   function checkform() {
   var name = document.getElementById("name").value;
   var protectFunction = document.getElementById("protectFunction").value;
	  if(name == "" || name == null)
		{
			alert("请输入权限名!");
			name.focus();
			return false;
		}
		
		if(protectFunction == "" || protectFunction == null)
		{
			alert("请输入受保护的权限名称!");
			protectFunction.focus();
			return false;
		}
		return true ;
  }
</script>
<html:form action="member/right/saveFunction${applicationScope.WEB_SUFFIX}">
  <table width="489" height="129"  align="center" bordercolor="#0099FF" class="tableBorder">
    <tr>
      <th colspan="2">创建权限</th>
    </tr>
    <tr>
      <td class="forumRow"><div align="center">名称<font color="#ff0000">*</font></div></td>
      <td class="forumRow"><input id="name" name="function.name" type="text" value=""></td>
    </tr>
    <tr>
      <td class="forumRow"><div align="center">权限名称<font color="#ff0000">*</font></div></td>
      <td class="forumRow"><input id="protectFunction" name="function.protectFunction" type="text" value=""></td>
    </tr>
    <tr>
      <td class="forumRow"><div align="center">备注</div></td>
      <td class="forumRow"><input name="function.note" type="text" value=""></td>
    </tr>
    <tr>
      <td colspan="2" class="forumRow"><div align="center">        
        <input type="submit" value="添加"/>
        <html:reset  value="重置"/>
		          
      </div></td>
    </tr>
  </table>
</html:form>
</html:html>