<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
<%@ taglib uri="/WEB-INF/tld/options.tld" prefix="option"%>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
<head>
<script src="<ls:templateResource item='/common/js/default/alternative.js'/>" type="text/javascript"></script>
  		<script type='text/javascript' src="<ls:templateResource item='/dwr/interface/CommonService.js'/>"></script>
  		<script type='text/javascript' src="<ls:templateResource item='/dwr/engine.js'/>"></script>
		<script type='text/javascript' src="<ls:templateResource item='/dwr/util.js'/>"></script>
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
			int offset=((Integer)request.getAttribute("offset")).intValue();
	%>	
	<form action="${pageContext.request.contextPath}/member/right/query${applicationScope.WEB_SUFFIX}" method="post" id="form1">
        <table class="${tableclass}" style="width: 100%">
	    <thead>
	    	<tr><td><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 用户管理  &raquo; <a href="${pageContext.request.contextPath}/member/right/query${applicationScope.WEB_SUFFIX}">权限管理</a> </td></tr>
	    </thead>
	    </table>
    <input type="hidden" name="curPageNO" value="<%=request.getAttribute("curPageNO")%>">
			&nbsp; 名称 ：
			<input type="text" name="name" maxlength="50" value="${bean.name }" />
			<input type="submit" value="搜索"/>
			<input type="button" value="创建权限" onclick='window.location="${pageContext.request.contextPath}/member/right/load${applicationScope.WEB_SUFFIX}"'/>
			&nbsp;&nbsp;
	</form>		
	
	
	<div align="center">
        <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
    <display:table name="list" requestURI="${pageContext.request.contextPath}/member/right/query${applicationScope.WEB_SUFFIX}" id="item" export="true" class="${tableclass}" style="width:100%">
      <display:column title="顺序"><%=offset++%></display:column>
      <display:column title="名称 " property="name" sortable="true"></display:column>
      <display:column title="权限名称 " property="protectFunction"></display:column>
      <display:column title="备注" property="note"></display:column>
      <display:column title="对应角色">
      	<a href="${pageContext.request.contextPath}/member/right/roles/${item.id}${applicationScope.WEB_SUFFIX}">角色</a>
      </display:column>
      <display:column title="操作" media="html" style="width:75px">
      	<a href="${pageContext.request.contextPath}/member/right/update/${item.id}${applicationScope.WEB_SUFFIX}" ><img alt="修改" src="<ls:templateResource item='/common/images/default/grid_edit.png'/> "></a>
      	<a href="${pageContext.request.contextPath}/member/right/delete/${item.id}${applicationScope.WEB_SUFFIX}" title="删除"><img alt="删除" src="<ls:templateResource item='/common/images/default/grid_delete.png'/> "></a>
      </display:column>
    </display:table>
        <c:if test="${not empty toolBar}">
            <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
        </c:if>
    </div>
	
</body>
</html>

