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
<title>权限列表</title>
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
	<html:form action="/member/right/saveFunctionsToRoleList${applicationScope.WEB_SUFFIX}">
    <input type="hidden" name="curPageNO" value="<%=request.getAttribute("curPageNO")%>"> 
    <input type="hidden" name="roleId" value="<bean:write name="role" property="id"/>"> 
<table width="900"  align="center" class="tableBorder">
  <tr>
    <th height="29" colspan="9" scope="col">权限列表</th>
  </tr>
  <logic:present name="functionList" scope="request">
  <tr>
    <td align="center" class="forumRow"><input name="checkbox" type=checkbox id=checkbox onClick="javascript:selAll();" value=checkbox>全选</td>
    <td align="center" class="forumRow">名称</td>
    <td align="center" class="forumRow">权限名称</td>
    <td align="center" class="forumRow">备注</td>
  </tr>
  <logic:iterate id="function" name="functionList">
  <logic:present name="function">
  <tr>
    <td align="center" class="forumRow"><html:multibox property="strArray"><bean:write name="function" property="id"/></html:multibox>&nbsp;<%=offset++%></td>
    <td align="center" class="forumRow"><bean:write name="function" property="name"/></td>
    <td align="center" class="forumRow"><bean:write name="function" property="protectFunction"/></td>
    <td align="center" class="forumRow"><bean:write name="function" property="note"/></td>
  </tr>
  </logic:present>
  </logic:iterate>
  </logic:present>
</table><p></p>
	<div align="center">
		<input type="submit" value="增加" onclick="return saveLeastOne();"/>
	</div>
	</html:form>
	<div align="center">
	<logic:present name="toolBar">
		<bean:write name="toolBar" filter="false"/>
	</logic:present>
	</div>  
</body>
</html:html>

