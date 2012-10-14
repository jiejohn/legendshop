<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
<%@ taglib uri="/WEB-INF/tld/options.tld" prefix="option"%>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
<head>
	<title>用户列表</title>
	  	<script type='text/javascript'  src="<ls:templateResource item='/dwr/engine.js'/>"></script>
		<script type='text/javascript'  src="<ls:templateResource item='/dwr/util.js'/>"></script>
		<script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
  		<script type='text/javascript'  src="<ls:templateResource item='/dwr/interface/CommonService.js'/>"></script>

<script language="JavaScript" type="text/javascript">
<!--
  function confirmDelete()
	{
	    return con = confirm("确定要删除吗？");
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
	<form action="<ls:url address='/admin/member/user/query'/>" id="form1" method="post">
        <table class="${tableclass}" style="width: 100%">
	    <thead>
	    	<tr><td><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 用户管理  &raquo; <a href="<ls:url address='/admin/member/user/query'/>">用户管理</a> </td></tr>
	    </thead>
	     <tbody><tr><td>
 <div align="left" style="padding: 3px">
    <input type="hidden" id="curPageNO" name="curPageNO" value="<%=request.getAttribute("curPageNO")%>">
			&nbsp; 用户名
			<input type="text" name="name" maxlength="50" value="${bean.name }" />
				&nbsp;状态 
				<select id="enabled" name="enabled">
				  <option:optionGroup type="select" required="false" cache="true"  beanName="ENABLED" selectedValue="${bean.enabled}"/>
	            </select>
			<input type="submit" value="搜索"/>
			<!--
			<input type="button" value="创建用户" onclick='window.location="<ls:url address='/admin/member/user/load'/>"'/>
			  -->
 </div>
 </td></tr></tbody>
	    </table>
	</form>
	 <div align="center">
        <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
    <display:table name="list" requestURI="${pageContext.request.contextPath}/admin/member/user/query"  id="item" export="true" class="${tableclass}" style="width:100%">
      <display:column title="顺序"><%=offset++%></display:column>
      <display:column title="用户名 " property="name" sortable="true"></display:column>
      <display:column title="状态">
      <option:optionGroup type="label" required="true" cache="true"
	                beanName="ENABLED" selectedValue="${item.enabled}" defaultDisp=""/>
      </display:column>
      <display:column title="备注" property="note"></display:column>
      <display:column title="用户角色">
      	<a href="<ls:url address='/admin/member/user/roles/${item.id}'/>">用户角色</a>
      </display:column>
      <display:column title="用户权限">
      	<a href="<ls:url address='/admin/member/user/functions/${item.id}'/>" >用户权限</a>
      </display:column>
      <display:column title="修改密码">
      	<a href="<ls:url address='/admin/member/user/update/${item.id}'/>">修改密码</a> 
      </display:column>
    </display:table>
        <c:if test="${not empty toolBar}">
            <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
        </c:if>
    </div>
	<div align="center">
    
</body>
</html>
