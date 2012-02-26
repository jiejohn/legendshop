<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/common.jsp"%>
<%@ include file="/pages/common/taglib.jsp"%>
<html:html>
<head>
<LINK title=Style href="${pageContext.request.contextPath}/common/css/back_style.css" type=text/css rel=stylesheet>
<title>角色列表</title>
</head>
<body>
<%int offset=1; %>
<br>
<table width="900"  align="center" class="tableBorder">
  <tr>
    <th height="29" colspan="9" scope="col">拥有权限 <bean:write name="function" property="name"/> 的角色列表</th>
  </tr>
  <logic:present name="list" scope="request">
  <tr>
    <td align="center" class="forumRow">顺序</td>
    <td align="center" class="forumRow">名称</td>
    <td align="center" class="forumRow">角色名称</td>
    <td align="center" class="forumRow">状态</td>
    <td align="center" class="forumRow">备注</td>
  </tr>
  <logic:iterate id="role" name="list">
  <logic:present name="role">
  <tr>
    <td align="center" class="forumRow"><%=offset++%></td>
    <td align="center" class="forumRow"><bean:write name="role" property="name"/></td>
    <td align="center" class="forumRow"><bean:write name="role" property="roleType"/></td>
    <td align="center" class="forumRow">
        <logic:equal name="role" property="enabled" value="0">无效</logic:equal>
        <logic:equal name="role" property="enabled" value="1">有效</logic:equal>  
    </td>
    <td align="center" class="forumRow"><bean:write name="role" property="note"/></td>
  </logic:present>
  </logic:iterate>
  </logic:present>
</table>
<div align="center">
<P><a href="javascript:history.go(-1)">返回</a></div>
</body>
</html:html>

