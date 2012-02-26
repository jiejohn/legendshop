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
<html:form action="/member/right/findAllRole${applicationScope.WEB_SUFFIX}">
<input type="hidden" name="curPageNO" value="<%=request.getAttribute("curPageNO")%>">
			<div align="center">
			&nbsp; 根据角色名称查找 ：
			<input type="text" name="search" maxlength="50" value="<%=request.getAttribute("search")%>" />
			<input type="submit" value="搜索"/>
			&nbsp;&nbsp;
			</div>
<table width="100%"  align="center" class="tableBorder" style="margin-top: 5px">
  <tr>
    <th height="32" colspan="10" scope="col">角色列表</th>
  </tr>
  <logic:present name="list" scope="request">
  <tr>
    <td align="center" class="forumRow">顺序</td>
    <td align="center" class="forumRow">名称</td>
    <td align="center" class="forumRow">角色名称</td>
    <td align="center" class="forumRow">状态</td>
    <td align="center" class="forumRow">备注</td>
    <td align="center" class="forumRow">对应权限</td>
    <td align="center" class="forumRow">修改&nbsp;</td>
    <td align="center" class="forumRow">删除&nbsp;</td>
  </tr>
  <logic:iterate id="role" name="list">
  <logic:present name="role">
  <tr>
    <td align="center" class="forumRow"><%=offset++%></td>
    <td align="center" class="forumRow"><bean:write name="role" property="name"/></td>
    <td align="center" class="forumRow"><bean:write name="role" property="roleType"/></td>
    <td align="center" class="forumRow">
	    <logic:equal name="role" property="enabled" value="0"><font color="red">无效</font></logic:equal>
		<logic:equal name="role" property="enabled" value="1">有效</logic:equal>    
	</td>
    <td align="center" class="forumRow"><bean:write name="role" property="note"/></td>
    <td align="center" class="forumRow">
    <html:link href="findFunctionByRole${applicationScope.WEB_SUFFIX}" paramId="roleId" paramName="role" paramProperty="id">权限</html:link>
    </td>
    <td align="center" class="forumRow">
    <html:link href="findRoleById${applicationScope.WEB_SUFFIX}" paramId="id" paramName="role" paramProperty="id">修改</html:link> 
     </td>
    <td align="center" class="forumRow">
        <html:link page="/member/right/deleteRoleById${applicationScope.WEB_SUFFIX}" paramId="id" paramName="role" paramProperty="id"  onclick="return confirmDelete();">删除</html:link>
     </td>
  </logic:present>
  </logic:iterate>
  </logic:present>
</table>
</html:form>
	<div align="center">
	<logic:present name="toolBar">
		<bean:write name="toolBar" filter="false"/>
	</logic:present>
	</div>
	<div align="center">
	<P> 点击此处
	  <html:link page="/member/right/saveRole.jsp">创建角色</html:link>
	</div>
</body>
</html:html>

