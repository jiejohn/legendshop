<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/common.jsp"%>
<%@ include file="/pages/common/taglib.jsp"%>
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
<html:form action="/member/user/findRoleByUser${applicationScope.WEB_SUFFIX}">
<input type="hidden" name="userId" value="<bean:write name="user" property="id"/>"> 
<table width="900"  align="center" class="tableBorder">
  <tr>
    <th height="29" colspan="9" scope="col">用户 <bean:write name="user" property="name"/>  角色列表</th>
  </tr>
  <logic:present name="roles" scope="request">
  <tr>
    <td align="center" class="forumRow"><input name="checkbox" type=checkbox id=checkbox onClick="javascript:selAll();" value=checkbox>全选</td>
    <td align="center" class="forumRow">名称</td>
    <td align="center" class="forumRow">角色名称</td>
    <td align="center" class="forumRow">状态</td>
    <td align="center" class="forumRow">备注</td>
  </tr>
  <logic:iterate id="role" name="roles">
  <logic:present name="role">
  <tr>
    <td align="center" class="forumRow"><html:multibox property="strArray"><bean:write name="role" property="id"/></html:multibox>&nbsp;<%=offset++%></td>
    <td align="center" class="forumRow"><bean:write name="role" property="name"/></td>
    <td align="center" class="forumRow"><bean:write name="role" property="roleType"/></td>
    <td align="center" class="forumRow">
         <logic:equal name="user" property="enabled" value="0">无效</logic:equal>
         <logic:equal name="user" property="enabled" value="1">有效</logic:equal>
    </td>
    <td align="center" class="forumRow"><bean:write name="role" property="note"/></td>
  </logic:present>
  </logic:iterate>
  </logic:present>
</table>
<div align="center">
  <P><input type="submit" value="删除" onclick="return deleteAction();"/><p>
  <html:link page="/member/user/findOtherRoleByUser${applicationScope.WEB_SUFFIX}" paramId="userId" paramName="user" paramProperty="id" > 增加</html:link>
</div>
  </html:form>
</body>
</html:html>

