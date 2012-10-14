<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ taglib uri="/WEB-INF/tld/options.tld" prefix="option"%>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
<head>
  		<script type='text/javascript' src="<ls:templateResource item='/dwr/engine.js'/>"></script>
		<script type='text/javascript' src="<ls:templateResource item='/dwr/util.js'/>"></script>
  		<script type='text/javascript' src="<ls:templateResource item='/dwr/interface/CommonService.js'/>"></script>
		<script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
<title>权限列表</title>
</head>
<body>
<%
			int offset=1;
	%>
	   <table class="${tableclass}" style="width: 100%">
	    <thead>
	    	<tr><td><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 用户管理  &raquo; <a href="${pageContext.request.contextPath}/admin/member/user/query">权限用户管理</a>&raquo;用户[${bean.name }]权限列表 </td></tr>
	    </thead>
	    </table>

	 <div align="center">
        <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
        
    <display:table name="list" id="item" export="true" class="${tableclass}" style="width:100%">
      <display:column style="width:70px" title="顺序"><%=offset++%></display:column>
      <display:column title="名称 " property="name" sortable="true"></display:column>
      <display:column title="权限名称 " property="protectFunction" sortable="true"></display:column>
      <display:column title="备注" property="note"></display:column>
    </display:table>
     </div>
     
<div align="center">
<a href="javascript:history.go(-1)">返回</a></div>
</body>
</html>

