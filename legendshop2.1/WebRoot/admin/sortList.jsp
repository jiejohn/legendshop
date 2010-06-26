<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common.jsp"%>
<%@ include file="/common/jsp/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
<html:html>
<head>
<LINK title=Style href="${root}/common/css/back_style.css" type=text/css rel=stylesheet>
<script language="JavaScript" type="text/javascript">
<!--
  function confirmDelete()
	{
	    return con = confirm("确定要删除吗？");
	}
	
  function deleteSort(sortId){
  if(confirmDelete()){
  	 window.location.href = "${root}/admin/deleteSort.do?sortId="+sortId;
  	}
  }
//-->
</script>
<title>分类列表</title>
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
			Integer offset = (Integer)request.getAttribute("offset");
	%>
<br>
<form action="${root}/admin/sortList.do">
<input type="hidden" name="curPageNO" value="${curPageNO}">
			<div align="center">
			&nbsp; 根据类型名称查找 ：
			<input type="text" name="search" maxlength="50" value="<%=request.getAttribute("search")%>" />
			<auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
			&nbsp; 根据用户名称查找 ：
			<input type="text" name="userName" maxlength="50" value="<%=request.getAttribute("userName")%>" />
			</auth:auth>
			<html:submit value="搜索" property="Submit" />
			&nbsp;&nbsp; <html:link page="/admin/saveSort.jsp">创建类型</html:link>
			</div>

<table width="900" border="0" align="center" class="tableBorder">
  <tr>
    <th height="29" colspan="10" scope="col">类型列表</th>
  </tr>
  <c:if test="${list != null }">
  <tr>
    <td align="center" class="forumRow" width="5%">顺序</td>
    <td align="center" class="forumRow" width="10%">类型名称</td>
     <td align="center" class="forumRow" width="5%">次序</td>
    <td align="center" class="forumRow" width="30%">类型图片</td>
    <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
        <td align="center" class="forumRow" width="10%">所属用户</td>
    </auth:auth>
    <td align="center" class="forumRow" width="20%">操作&nbsp;</td>
  </tr>
  <c:forEach items="${requestScope.list}" var="sort">
  <tr>
    <td align="center" class="forumRow"><%=offset++%></td>
    <td align="center" class="forumRow">${sort.sortName}</td>
    <td align="center" class="forumRow">${sort.seq}</td>
    <td align="center" class="forumRow">
    <a href="${PHOTO_PATH}/${sort.picture}" target="_blank"><img src="${PHOTO_PATH}/${sort.picture}" height="60" width="300"/></a>
    </td>
    <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
         <td align="center" class="forumRow">${sort.userName}</td>
    </auth:auth>
    <td align="center" class="forumRow">
     <a href="${root}/admin/updateSort.do?sortId=${sort.sortId}"> 修改</a>
     <a href="${root}/admin/nsort/query.c?sortId=${sort.sortId}">子类型管理</a>
     <auth:auth ifAnyGranted="F_OPERATOR">
        <a href='javascript:deleteSort("${sort.sortId}")'>删除</a>
     </auth:auth>
    </td>
    </tr>
  </c:forEach>
  </c:if>
</table>
</form>
	<div align="center">
	<logic:present name="toolBar">
		<bean:write name="toolBar" filter="false"/>
	</logic:present>
	</div>
</body>
</html:html>

