<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common.jsp"%>
<%@ include file="/common/jsp/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
<script src="${root}/common/js/jquery.js" type="text/javascript"></script>  
<script type='text/javascript' src='${root}/common/calendar.js'></script>
<html:html>
<%
			Integer offset = (Integer)request.getAttribute("offset");
	%>
<head>
		<LINK title=Style href="${root}/common/css/back_style.css" type=text/css rel=stylesheet>
<script language="JavaScript" type="text/javascript">
<!--
		function pager(curPageNO){
			document.getElementById("curPageNO").value=curPageNO;
			document.getElementById("form1").submit();
		}
		  $(function() {
		 document.getElementById("startTime").onclick=function(evt){ calendar.show(this,"yyyy-MM-dd",evt); };
		  document.getElementById("endTime").onclick=function(evt){ calendar.show(this,"yyyy-MM-dd",evt); };
		    });
//-->
</script>
<title>用户登录历史列表</title>
</head>
<body>
<auth:auth ifAnyGranted="F_SYSTEM">
<br>
<form action="${root}/businessAction/loginHistoryList.c" id="form1">
<input type="hidden" id="curPageNO" name="curPageNO" value="${login.curPageNO}">
			<div align="center">
			
			用户名称 ：
			<input type="text" name="userName" id="userName" maxlength="50" value="${login.userName}" size="15"/>
			开始时间(格式yyyy-MM-dd) ：
			<input type="text" name="startTime" id="startTime" maxlength="50" value='<fmt:formatDate value="${login.startTime}" pattern="yyyy-MM-dd"/>' size="15" />
			结束时间(格式yyyy-MM-dd) ：
			<input type="text" name="endTime" id="endTime" maxlength="50" value='<fmt:formatDate value="${login.endTime}" pattern="yyyy-MM-dd"/>' size="15" />
			<html:submit value="搜索" property="Submit" />
	</div>

<table width="100%" border="0" align="center" class="tableBorder">
  <tr>
    <th height="29" colspan="7" scope="col">用户登录历史列表</th>
  </tr>
  <c:if test="${list != null }">
  <tr>
    <td align="center" class="forumRow" width="5%">顺序</td>
    <td align="center" class="forumRow" width="6%">用户名</td>
    <td align="center" class="forumRow" width="6%">IP</td>
    <td align="center" class="forumRow" width="12%">登录时间</td>

    </tr>
  <c:forEach items="${requestScope.list}" var="login">
  <tr>
    <td align="center" class="forumRow"><%=offset++%></td>
    <td align="center" class="forumRow">${login.userName}</td>
    <td align="center" class="forumRow">${login.ip}</td>
    <td align="center" class="forumRow"><fmt:formatDate value="${login.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
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
	</auth:auth>
</body>
</html:html>

