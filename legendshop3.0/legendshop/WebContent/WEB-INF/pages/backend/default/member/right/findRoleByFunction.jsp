<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ taglib uri="/WEB-INF/tld/options.tld" prefix="option"%>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
<head>
  		<script type='text/javascript' src="<ls:templateResource item='/dwr/interface/CommonService.js'/>"></script>
  		<script type='text/javascript' src="<ls:templateResource item='/dwr/engine.js'/>"></script>
		<script type='text/javascript' src="<ls:templateResource item='/dwr/util.js'/>"></script>
		<script src="<ls:templateResource item='/common/js/default/alternative.js'/>" type="text/javascript"></script>
<title>角色列表</title>
</head>
<body>
<%int offset=1; %>

	   <table class="${tableclass}" style="width: 100%">
	    <thead>
	    	<tr><td><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 用户管理  &raquo; <a href='<ls:url address="/member/right/query"/>'>权限管理</a>&raquo;拥有[${bean.name }]权限的角色列表 </td></tr>
	    </thead>
	    </table>
	    
	  
	 <div align="center">
        <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
        
    <display:table name="list" id="item" export="true" class="${tableclass}" style="width:100%">
      <display:column style="width:70px" title="顺序"><%=offset++%></display:column>
      <display:column title="名称 " property="name" sortable="true"></display:column>
      <display:column title="角色名称 " property="roleType" sortable="true"></display:column>
      <display:column title="状态">
      <option:optionGroup type="label" required="true" cache="true"
	                beanName="ENABLED" selectedValue="${item.enabled}" defaultDisp=""/>
      </display:column>
      <display:column title="备注" property="note"></display:column>
    </display:table>
     </div>  
	    
	    
<div align="center">
<P><a href="javascript:history.go(-1)">返回</a></div>
</body>
</html>

