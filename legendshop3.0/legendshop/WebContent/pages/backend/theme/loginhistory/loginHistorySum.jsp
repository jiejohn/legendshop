<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/back-common.jsp"%>
<%@ include file="/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ taglib uri="/WEB-INF/tld/options.tld" prefix="option"%>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<script src="${pageContext.request.contextPath}/common/js/jquery.js" type="text/javascript"></script>  
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/plugins/My97DatePicker/WdatePicker.js"></script>
<html>
<%
			Integer offset = (Integer)request.getAttribute("offset");
	%>
<head>
	<script src="${pageContext.request.contextPath}/css/alternative.js" type="text/javascript"></script>
<title>用户登录历史列表次数</title>
</head>
<body>
<auth:auth ifAnyGranted="F_SYSTEM">
    <table class="${tableclass}" style="width: 100%">
    <thead>
    	<tr><td><a href="/member/" target="_parent">首页</a> &raquo; 用户管理  &raquo; <a href="${pageContext.request.contextPath}/admin/loginHistory/sum${applicationScope.WEB_SUFFIX}">用户登录次数历史列表</a></td></tr>
    </thead>
    </table>
<form action="${pageContext.request.contextPath}/admin/loginHistory/sum${applicationScope.WEB_SUFFIX}" id="form1">
<input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}">
			&nbsp;用户名称
			<input type="text" name="userName" id="userName" maxlength="50" value="${login.userName}" size="15"/>
			&nbsp;开始时间
			<input readonly="readonly"  name="startTime" id="startTime" class="Wdate" type="text" onClick="WdatePicker()" value='<fmt:formatDate value="${login.startTime}" pattern="yyyy-MM-dd"/>' />
			&nbsp;结束时间
			<input readonly="readonly" name="endTime" id="endTime" class="Wdate" type="text" onClick="WdatePicker()" value='<fmt:formatDate value="${login.endTime}" pattern="yyyy-MM-dd"/>' />
			<input type="submit" value="搜索" /> 
</form>
<div align="center">
        <%@ include file="/pages/common/messages.jsp"%>
    <display:table name="list" requestURI="/admin/loginHistory/sum${applicationScope.WEB_SUFFIX}" id="item"
         export="true"  class="${tableclass}" style="width:100%" sort="external">
      <display:column title="顺序"><%=offset++%></display:column>
      <display:column title="用户名">${item[0]}</display:column>
      <display:column title="登录次数">${item[1]}</display:column>
    </display:table>
        <c:if test="${not empty toolBar}">
            <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
        </c:if>
    </div>
	</auth:auth>
	
	<script language="JavaScript" type="text/javascript">
<!--
		function pager(curPageNO){
			document.getElementById("curPageNO").value=curPageNO;
			document.getElementById("form1").submit();
		}
	 highlightTableRows("item");  	
//-->
</script>
</body>
</html>

