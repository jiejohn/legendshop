<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common.jsp"%>
<%@ include file="/common/jsp/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ taglib uri="/WEB-INF/tld/options.tld" prefix="option"%>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
<head>
	<script type='text/javascript' src='${root}/dwr/interface/CommonService.js'></script>
	<script type='text/javascript' src='${root}/dwr/interface/optionService.js'></script>
	<script type='text/javascript' src='${root}/dwr/engine.js'></script>
	<script type='text/javascript' src='${root}/dwr/util.js'></script>
	<style type="text/css" media="all">
	   @import url("${root}/css/screen.css");
	</style>
	<script language="JavaScript" type="text/javascript">
<!--

  function deleteById(id) {
	  if(confirm("  确定删除 ?")){
            window.location = "${root}/admin/logo/delete/"+id+".c";
	    }
    }

		function pager(curPageNO){
			document.getElementById("curPageNO").value=curPageNO;
			document.getElementById("form1").submit();
		}
//-->
</script>

<jsp:scriptlet> String lClass = "mars";
   if( request.getParameter( "class" ) != null ) {
      lClass = request.getParameter( "class" );
      if (!("isis".equals(lClass) || "its".equals(lClass) || "mars".equals(lClass) || "simple".equals(lClass) || "report".equals(lClass) || "mark".equals(lClass)))
      {
        lClass="";
      }
   }
   pageContext.setAttribute("tableclass", lClass);
</jsp:scriptlet>

	<title>Logo 列表</title>
</head>
<body class="bodymargin">
	<%
	    Integer offset = (Integer) request.getAttribute("offset");
	%>
	<form action="${root}/admin/logo/query.c" id="form1" method="post">
	    <table class="mars" style="width: 100%">
	    <thead>
	    	<tr><td>商家管理  >> Logo管理</td></tr>
	    </thead>
	    </table>
		<input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
		<auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
			店铺
			<input type="text" name="userName" maxlength="50"
				value="${bean.userName}" size="15" />
			<html:submit value="搜索" property="Submit" />
		</auth:auth>
		<c:if test="${fn:length(list) == 0}">
			<html:link page="/pages/logo/logo.jsp" title="创建首页Logo"><img src="${root}/img/grid_add.png" title="创建首页Logo"> </html:link>
		</c:if>
	</form>
        <%@ include file="/common/jsp/messages.jsp"%>
        <display:table name="list" requestURI="/admin/logo/query.c" id="item"
            export="false" class="${tableclass}" style="width:100%">
            <display:column title="顺序" class="orderwidth"><%=offset++%></display:column>
            <display:column title="图片"><img src="${PHOTO_PATH}/${item.banner}" height="65px" width="180px"/></display:column>           
            <display:column title="链接地址"><a href="${item.url}" target="_blank">${item.url}</a></display:column>
            <display:column title="备注" property="memo"></display:column>
            <display:column title="店铺" property="userName" sortable="true"></display:column>
            <display:column title="操作" media="html" class="actionwidth">
                 <a href= "${root}/admin/logo/load/${item.id}.c" title="修改"><img alt="修改" src="${root}/img/grid_edit.png"></a> 
                 <auth:auth ifAnyGranted="F_OPERATOR">
                    <a href='javascript:deleteById("${item.id}")' title="删除"><img alt="删除" src="${root}/img/grid_delete.png"></a>
                 </auth:auth>
            </display:column>
        </display:table>
		<c:if test="${not empty toolBar}">
			<c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
		</c:if>
</body>
</html>

