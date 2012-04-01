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
			//String toolBar = (String) request.getAttribute("toolBar");
	%>
<br>
<br>
	<html:form action="/member/right/listFunction${applicationScope.WEB_SUFFIX}">
    <input type="hidden" name="curPageNO" value="<%=request.getAttribute("curPageNO")%>">
			<div align="center">
			&nbsp; 根据权限名称查找 ：
			<input type="text" name="search" maxlength="50" value="<%=request.getAttribute("search")%>" />
			<input type="submit" value="搜索"/>
			&nbsp;&nbsp;
			</div>
<table width="900"  align="center" class="tableBorder">
  <tr>
    <th height="29" colspan="9" scope="col">权限列表</th>
  </tr>
  <logic:present name="functionList" scope="request">
  <tr>
    <td align="center" class="forumRow">顺序</td>
    <td align="center" class="forumRow">名称</td>
    <td align="center" class="forumRow">权限名称</td>
    <td align="center" class="forumRow">备注</td>
    <td align="center" class="forumRow">对应角色</td>
    <td align="center" class="forumRow">修改&nbsp;</td>
    <td align="center" class="forumRow">删除&nbsp;</td>
  </tr>
  <logic:iterate id="function" name="functionList">
  <logic:present name="function">
  <tr>
    <td align="center" class="forumRow"><%=offset++%></td>
    <td align="center" class="forumRow"><bean:write name="function" property="name"/></td>
    <td align="center" class="forumRow"><bean:write name="function" property="parentName"/></td>
    <td align="center" class="forumRow"><bean:write name="function" property="url"/></td>
    <td align="center" class="forumRow"><bean:write name="function" property="protectFunction"/></td>
    <td align="center" class="forumRow"><bean:write name="function" property="note"/></td>
    <td align="center" class="forumRow"><html:link href="findRoleByFunction${applicationScope.WEB_SUFFIX}" paramId="functionId" paramName="function" paramProperty="id">
    角色
        </html:link></td>
    <td align="center" class="forumRow"><html:link href="findFunctionById${applicationScope.WEB_SUFFIX}" paramId="id" paramName="function" paramProperty="id">修改</html:link> </td>
    <td align="center" class="forumRow"><html:link page="/member/right/deleteFunctionById${applicationScope.WEB_SUFFIX}" paramId="id" paramName="function" paramProperty="id" onclick="return confirmDelete();">删除</html:link> </td>
  </tr>
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
  <html:link page="/member/right/saveFunction.jsp">增加权限</html:link>
</div>
  
</body>
</html:html>

