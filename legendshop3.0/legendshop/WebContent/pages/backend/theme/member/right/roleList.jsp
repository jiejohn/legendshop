<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/common.jsp"%>
<%@ include file="/pages/common/taglib.jsp"%>
<%@ include file="/pages/common/back-common.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
<%@ taglib uri="/WEB-INF/tld/options.tld" prefix="option"%>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
<head>
<script src="${pageContext.request.contextPath}/css/alternative.js" type="text/javascript"></script>
  		<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/CommonService.js'></script>
  		<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/engine.js'></script>
		<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/util.js'></script>
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
<form action="${pageContext.request.contextPath}/member/right/findAllRole${applicationScope.WEB_SUFFIX}">
<input type="hidden" name="curPageNO" value="<%=request.getAttribute("curPageNO")%>">
			<div align="center">
			&nbsp; 根据角色名称查找 ：
			<input type="text" name="search" maxlength="50" value="<%=request.getAttribute("search")%>" />
			<input type="submit" value="搜索"/>
			&nbsp;&nbsp;
			</div>	
</form>	

	 <div align="center">
        <%@ include file="/pages/common/messages.jsp"%>
    <display:table name="list" requestURI="/member/right/findAllRole${applicationScope.WEB_SUFFIX}" id="item" export="true" class="${tableclass}" style="width:100%">
      <display:column title="顺序"><%=offset++%></display:column>
      <display:column title="名称 " property="name" sortable="true"></display:column>
      <display:column title="角色名称 " property="roleType"></display:column>
      <display:column title="状态" sortable="true">
       <c:choose>
       		<c:when test="${item.enabled == 1}">有效</c:when>
       		<c:otherwise><font color="red">无效</font></c:otherwise>
       </c:choose>
      </display:column>
      <display:column title="备注" property="note"></display:column>
      <display:column title="对应权限">
      	<a href="findFunctionByRole${applicationScope.WEB_SUFFIX}?roleId=${item.id}">权限</a>
      </display:column>
      <display:column title="操作" media="html" style="width:75px">
      	<a href="findRoleById${applicationScope.WEB_SUFFIX}?id=${item.id}" ><img alt="修改" src="${pageContext.request.contextPath}/img/grid_edit.png"></a>
      	<a href="/member/right/deleteRoleById${applicationScope.WEB_SUFFIX}?id=${item.id}" title="删除"><img alt="删除" src="${pageContext.request.contextPath}/img/grid_delete.png"></a>
      </display:column>
    </display:table>
        <c:if test="${not empty toolBar}">
            <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
        </c:if>
    </div>
	<div align="center">
    <P> 点击此处
    <a href="/member/right/saveRole.jsp">创建角色</a>
       </div>
 
</body>
</html>

