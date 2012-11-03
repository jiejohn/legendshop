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
    <title>缓存列表</title>
</head>
<body>
    <%
        Integer offset = 1;
    %>
	 <table class="${tableclass}" style="width: 100%">
	    <thead>
	    	<tr><td><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 系统管理  &raquo; <a href="${pageContext.request.contextPath}/system/plugin/query">插件列表</a></td></tr>
	    </thead>
	    </table>
    
    <div align="center">
        <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
    <display:table name="pluginList" requestURI="/system/plugin/query" id="item"
         export="false" class="${tableclass}" style="width:80%">
       <display:column title="顺序"  style="width: 80px">&nbsp;<%=offset++%>&nbsp;&nbsp;&nbsp;</display:column>
      <display:column title="名称" property="pluginConfig.pulginId"></display:column>
      <display:column title="版本" property="pluginConfig.pulginVersion"></display:column>
      <display:column title="状态" property="pluginConfig.status"></display:column>
      <display:column title="是否必须" property="pluginConfig.required"></display:column>
      <display:column title="描述" property="pluginConfig.description"></display:column>
    </display:table>
    </div>
    
</body>
</html>

