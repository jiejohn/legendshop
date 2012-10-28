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
	    	<tr><td><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 系统管理  &raquo; <a href="${pageContext.request.contextPath}/system/cache/query">缓存列表</a></td></tr>
	    </thead>
	    	     <tbody><tr><td>
 <div align="left" style="padding: 3px">
 <auth:auth ifAnyGranted="F_OPERATOR">
 <a href='javascript:clearCache()'>
<input type="button" value="一键清除缓存" />
</a>
</auth:auth>
 </td></tr></tbody>
	    </table>
    
    <div align="center">
        <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
    <display:table name="cacheList" requestURI="/system/cache/query" id="item"
         export="false" class="${tableclass}" style="width:100%">
       <display:column title="顺序"  style="width: 80px">&nbsp;<%=offset++%>&nbsp;&nbsp;&nbsp;</display:column>
      <display:column title="缓存名称" property="key"></display:column>
      <display:column title="缓存内容" property="value"></display:column>
    </display:table>
    </div>
    
    <script type="text/javascript">
      function clearCache() {
        CommonService.clearSecondLevelCache(function(retData){
	       if(retData == null ){
	          window.location.reload() ;
	       }else{
	          alert("除缓存失败！") ;
	       }
	       
	    }) ;
}
    </script>
</body>
</html>

