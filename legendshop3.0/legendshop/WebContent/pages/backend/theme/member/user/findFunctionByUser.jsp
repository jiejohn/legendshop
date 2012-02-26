<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/common.jsp"%>
<%@ include file="/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
<html:html>
<head>
<LINK title=Style href="${pageContext.request.contextPath}/common/css/back_style.css" type=text/css rel=stylesheet>
<script language="JavaScript" type="text/javascript">
<!--
  function confirmDelete()
	{
	    return con = confirm("确定要删除吗？");
	}
	
  function selAll(){
    chk = document.getElementById("checkbox");
	allSel = document.getElementsByName("strArray");
	
	if(!chk.checked)
		for(i=0;i<allSel.length;i++)
		{
			   allSel[i].checked=false;
		}
	else
		for(i=0;i<allSel.length;i++)
		{
			   allSel[i].checked=true;
		}
	}
	
 function deleteAction()
{
	//获取选择的记录集合
	selAry = document.getElementsByName("strArray");
	count = 0;
	selValue = "";
	for(i=0;i<selAry.length;i++)
	{
		if(selAry[i].checked)
		{
			count++;
			selValue = selAry[i].value;
		}
	}
	if(count<1)
	{
		window.alert('删除时至少选中一条记录！');
	}
	else
	{
	 if( confirmDelete()){
     document.forms[0].action="../../member/user/deleteUserRoleByUserId${applicationScope.WEB_SUFFIX}";  
     document.forms[0].submit(); 
     return true ;
     }else{
       return false ;
     } 
	}
	return false;
}
	
//-->
</script>
<title>权限列表</title>
</head>
<body>
<%
			int offset=1;
	%>
<br>
<br>

<table width="900"  align="center" class="tableBorder">
  <tr>
    <th height="29" colspan="9" scope="col">用户 <bean:write name="user" property="name"/> 权限列表</th>
  </tr>
  <logic:present name="functions" scope="request">
  <tr>
    <td align="center" class="forumRow">顺序</td>
    <td align="center" class="forumRow">名称</td>
    <td align="center" class="forumRow">权限名称</td>
    <td align="center" class="forumRow">备注</td>
  </tr>
  <logic:iterate id="function" name="functions">
  <logic:present name="function">
  <tr>
    <td align="center" class="forumRow"><%=offset++%></td>
    <td align="center" class="forumRow"><bean:write name="function" property="name"/></td>
    <td align="center" class="forumRow"><bean:write name="function" property="protectFunction"/></td>
    <td align="center" class="forumRow"><bean:write name="function" property="note"/></td>
  </logic:present>
  </logic:iterate>
  </logic:present>
</table>
<div align="center">
<P><a href="javascript:history.go(-1)">返回</a></div>
</body>
</html:html>

