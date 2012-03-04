<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/common.jsp"%>
<%@ include file="/pages/common/taglib.jsp"%>
<%@ include file="/pages/common/back-common.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
<%@ taglib uri="/WEB-INF/tld/options.tld" prefix="option"%>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
<head>
	<title>用户列表</title>
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
	
	     window.onload=function(){
        	var enabled = '${enabled}';
        	initStatus(enabled);
        	}
        	
        function initStatus(statusValue){
			DWRUtil.setValues({enabled:statusValue});
		}	

		function pager(curPageNO){
			document.getElementById("curPageNO").value=curPageNO;
			document.getElementById("form1").submit();
		}  
        
//-->
</script>
</head>

<body>
	<%
			int offset=((Integer)request.getAttribute("offset")).intValue();
	%>
	
	<br>
	<form action="${pageContext.request.contextPath}/member/user/usersList${applicationScope.WEB_SUFFIX}" id="form1">
    <input type="hidden" id="curPageNO" name="curPageNO" value="<%=request.getAttribute("curPageNO")%>">
			&nbsp; 用户名
			<input type="text" id="search" name="search" maxlength="50" value="<%=request.getAttribute("search")%>" />
				&nbsp;状态 
	            <select id="enabled" name="enabled">
					<option value="">请选择</option>	
			    	<option value="1">有效</option>	
	      			<option value="0" >无效</option>
			</select>
			<input type="submit" value="搜索"/>
	</form>
	 <div align="center">
        <%@ include file="/pages/common/messages.jsp"%>
    <display:table name="list" requestURI="/member/user/usersList${applicationScope.WEB_SUFFIX}" id="item" export="true" class="${tableclass}" style="width:100%">
      <display:column title="顺序"><%=offset++%></display:column>
      <display:column title="用户名 " property="name" sortable="true"></display:column>
      <display:column title="状态" property="enabled" sortable="true"></display:column>
      <display:column title="注释" property="note"></display:column>
      <display:column title="用户角色">
      	<a href="${pageContext.request.contextPath}/member/user/findRoleByUser${applicationScope.WEB_SUFFIX}?userId=${item.id}">用户角色</a>
      </display:column>
      <display:column title="用户权限">
      	<a href="${pageContext.request.contextPath}/member/user/findFunctionByUser${applicationScope.WEB_SUFFIX}?userId=${item.id}" >用户权限</a>
      </display:column>
      <display:column title="修改密码">
      	<a href="${pageContext.request.contextPath}/findUserById${applicationScope.WEB_SUFFIX}?userId=${item.id}">修改密码</a> 
      </display:column>
    </display:table>
        <c:if test="${not empty toolBar}">
            <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
        </c:if>
    </div>
	<div align="center">
    <P> 点击此处
    <a href="/member/user/saveUser.jsp">创建用户</a>
       </div>
</body>
</html>
