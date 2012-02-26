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
	function  saveLeastOne()
{
	 //获取选择的记录集合
	selAry = document.getElementsByName("strArray");
	count = 0;
	selValue = "";
	//判断是否只有一条记录被选中
	for(i=0;i<selAry.length;i++)
	{
		if(selAry[i].checked)
		{
			count++;
			selValue = selAry[i].value;
		}
	}
	if(count==0)
	{
		window.alert('至少选中一条记录！');
	}
	else
	{
      return true;
	}
	  return false;
} 
//-->
</script>
<title>角色列表</title>
</head>
<body>
<%
			String curPageNO = (String) request.getParameter("curPageNO");
			int curpage = 1;
			if ("".equals(curPageNO) || curPageNO == null)
				curpage = 1;
			else {
				curpage = Integer.parseInt(curPageNO);
			}
			int offset=((Integer)request.getAttribute("offset")).intValue();
	%>
<br>
<br>
	<html:form action="/member/user/saveRoleToUser${applicationScope.WEB_SUFFIX}">
    <input type="hidden" name="curPageNO" value="<%=request.getAttribute("curPageNO")%>"> 
    <input type="hidden" name="userId" value="<bean:write name="user" property="id"/>"> 
<table width="900"  align="center" class="tableBorder">
  <tr>
    <th height="29" colspan="9" scope="col">角色列表</th>
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
	    <logic:equal name="role" property="enabled" value="0">无效</logic:equal>
	    <logic:equal name="role" property="enabled" value="1">有效</logic:equal>
    </td>
    <td align="center" class="forumRow"><bean:write name="role" property="note"/></td>
  </tr>
  </logic:present>
  </logic:iterate>
  </logic:present>
</table><p></p>
	<div align="center">
		<input type="submit" value="添加" onclick="return saveLeastOne();"/>
	</div>
	</html:form>
	<div align="center">
	<logic:present name="toolBar">
		<bean:write name="toolBar" filter="false"/>
	</logic:present>
	</div>  
</body>
</html:html>

