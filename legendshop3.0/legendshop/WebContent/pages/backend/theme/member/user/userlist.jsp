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
	<form action="${pageContext.request.contextPath}/member/user/query${applicationScope.WEB_SUFFIX}" id="form1" method="post">
	
        <table class="${tableclass}" style="width: 100%">
	    <thead>
	    	<tr><td><a href="${pageContext.request.contextPath}/admin/index${applicationScope.WEB_SUFFIX}" target="_parent">首页</a> &raquo; 用户管理  &raquo; <a href="${pageContext.request.contextPath}/member/user/query${applicationScope.WEB_SUFFIX}">权限用户管理</a> </td></tr>
	    </thead>
	    </table>
      
    <input type="hidden" id="curPageNO" name="curPageNO" value="<%=request.getAttribute("curPageNO")%>">
			&nbsp; 用户名
			<input type="text" name="name" maxlength="50" value="${bean.name }" />
				&nbsp;状态 
				<select id="enabled" name="enabled">  
			        <c:if test="${not empty bean.enabled}">
						<option value="${bean.enabled}">      
						<option:optionGroup type="label" required="true" cache="true" beanName="ENABLED" selectedValue="${bean.enabled}" defaultDisp=""/>
						</option>							            
			        </c:if>
				  <option:optionGroup type="select" required="true" cache="true" 
	                beanName="ENABLED" selectedValue="${bean.enabled}"/>
	            </select>
			<input type="submit" value="搜索"/>
	</form>
	 <div align="center">
        <%@ include file="/pages/common/messages.jsp"%>
    <display:table name="list" requestURI="/member/user/query${applicationScope.WEB_SUFFIX}" id="item" export="true" class="${tableclass}" style="width:100%">
      <display:column title="顺序"><%=offset++%></display:column>
      <display:column title="用户名 " property="name" sortable="true"></display:column>
      <display:column title="状态">
      <option:optionGroup type="label" required="true" cache="true"
	                beanName="ENABLED" selectedValue="${item.enabled}" defaultDisp=""/>
      </display:column>
      <display:column title="备注" property="note"></display:column>
      <display:column title="用户角色">
      	<a href="${pageContext.request.contextPath}/member/user/roles/${item.id}${applicationScope.WEB_SUFFIX}">用户角色</a>
      </display:column>
      <display:column title="用户权限">
      	<a href="${pageContext.request.contextPath}/member/user/findFunctionByUser${applicationScope.WEB_SUFFIX}?userId=${item.id}" >用户权限</a>
      </display:column>
      <display:column title="修改密码">
      	<a href="${pageContext.request.contextPath}/member/user/update/${item.id}${applicationScope.WEB_SUFFIX}">修改密码</a> 
      </display:column>
    </display:table>
        <c:if test="${not empty toolBar}">
            <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
        </c:if>
    </div>
	<div align="center">
    <P> 点击此处
    <a href="${pageContext.request.contextPath}/member/user/load${applicationScope.WEB_SUFFIX}">创建用户</a>
       </div>
</body>
</html>
