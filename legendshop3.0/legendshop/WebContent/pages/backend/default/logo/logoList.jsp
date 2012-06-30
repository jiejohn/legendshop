<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ taglib uri="/WEB-INF/tld/options.tld" prefix="option"%>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
<head>
	<script type='text/javascript' src="<ls:templateResource item='/dwr/interface/CommonService.js'/>"></script>
	<script type='text/javascript' src="<ls:templateResource item='/dwr/interface/optionService.js'/>"></script>
	<script type='text/javascript' src="<ls:templateResource item='/dwr/engine.js'/>"></script>
	<script type='text/javascript' src="<ls:templateResource item='/dwr/util.js'/>"></script>
	<script src="<ls:templateResource item='/common/js/default/alternative.js'/>" type="text/javascript"></script>
	<title>Logo 列表</title>
</head>
<body class="bodymargin">
	<%
	    Integer offset = (Integer) request.getAttribute("offset");
	%>
	<form action="${pageContext.request.contextPath}/admin/logo/query${applicationScope.WEB_SUFFIX}" id="form1" method="post">
	    <table class="${tableclass}" style="width: 100%">
	    <thead>
	    	<tr><td><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商城管理  &raquo; <a href="${pageContext.request.contextPath}/admin/logo/query${applicationScope.WEB_SUFFIX}">Logo管理</a></td></tr>
	    </thead>
	    </table>
		<input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
		<auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
			商城名称&nbsp;
			<input type="text" name="userName" maxlength="50"
				value="${bean.userName}" size="15" />
			<input type="submit" value="搜索"/>
		</auth:auth>
		<c:if test="${fn:length(list) == 0}">
			<input type="button" value="创建首页Logo" onclick='window.location="${pageContext.request.contextPath}/admin/logo/load${applicationScope.WEB_SUFFIX}"'/>
		</c:if>
	</form>
	<div align="center">
        <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
        <display:table name="list" requestURI="/admin/logo/query${applicationScope.WEB_SUFFIX}" id="item"
            export="true" class="${tableclass}" style="width:100%">
            <display:column title="顺序" class="orderwidth"><%=offset++%></display:column>
            <display:column title="图片"><img src="${pageContext.request.contextPath}/photoserver/photo/${item.banner}" height="65px" width="180px"/></display:column>           
            <display:column title="链接地址"><a href="${item.url}" target="_blank">${item.url}</a></display:column>
            <display:column title="备注" property="memo"></display:column>
             <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
           		 <display:column title="商城名称" property="userName" sortable="true"></display:column>
             </auth:auth>
            <display:column title="操作" media="html" class="actionwidth">
                 <a href= "${pageContext.request.contextPath}/admin/logo/load/${item.id}${applicationScope.WEB_SUFFIX}" title="修改"><img alt="修改" src="<ls:templateResource item='/common/images/default/grid_edit.png'/> "></a> 
                 <auth:auth ifAnyGranted="F_OPERATOR">
                    <a href='javascript:deleteById("${item.id}")' title="删除"><img alt="删除" src="<ls:templateResource item='/common/images/default/grid_delete.png'/> "></a>
                 </auth:auth>
            </display:column>
        </display:table>
		<c:if test="${not empty toolBar}">
			<c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
		</c:if>
		</div>
	    <table style="width: 100%; border: 0px"><tr><td align="left">说明：<br>
   1. 每个商城可以设置自己的Logo，如果不设置则采用默认Logo<br>
   </td><tr></table> 	
		<script language="JavaScript" type="text/javascript">
<!--

  function deleteById(id) {
	  if(confirm("  确定删除 ?")){
            window.location = "${pageContext.request.contextPath}/admin/logo/delete/"+id+"${applicationScope.WEB_SUFFIX}";
	    }
    }

  function pager(curPageNO){
			document.getElementById("curPageNO").value=curPageNO;
			document.getElementById("form1").submit();
		}
		
		highlightTableRows("item"); 
//-->
</script>
</body>
</html>

