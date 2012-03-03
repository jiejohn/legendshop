<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/back-common.jsp"%>
<%@ include file="/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ taglib uri="/WEB-INF/tld/options.tld" prefix="option"%>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
<head>
    <script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/CommonService.js'></script>
    <script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/optionService.js'></script>
    <script type='text/javascript' src='${pageContext.request.contextPath}/dwr/engine.js'></script>
    <script type='text/javascript' src='${pageContext.request.contextPath}/dwr/util.js'></script>
    <script src="${pageContext.request.contextPath}/css/alternative.js" type="text/javascript"></script>
    <title>公告列表</title>
</head>
<body class="bodymargin">
    <%
        Integer offset = (Integer) request.getAttribute("offset");
    %>
    <form action="${pageContext.request.contextPath}/admin/pub/query${applicationScope.WEB_SUFFIX}" id="form1" method="post">
    <table class="${tableclass}" style="width: 100%">
    <thead>
    	<tr><td><a href="${pageContext.request.contextPath}/admin/index${applicationScope.WEB_SUFFIX}" target="_parent">首页</a> &raquo; 商城管理  &raquo; <a href="${pageContext.request.contextPath}/admin/pub/query${applicationScope.WEB_SUFFIX}">公告管理</a></td></tr>
    </thead>
    </table>
        <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
        <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
           所属商城 <input type="text" name="userName" maxlength="50" value="${bean.userName}" />
            <input type="submit" value="搜索"/>
        </auth:auth>
            <input type="button" value="创建公告" onclick='window.location="${pageContext.request.contextPath}/admin/pub/load${applicationScope.WEB_SUFFIX}"'/>
            <br>
    </form>
    <div align="center">
        <%@ include file="/pages/common/messages.jsp"%>
    <display:table name="list" requestURI="/admin/pub/query${applicationScope.WEB_SUFFIX}" id="item"
         export="true" class="${tableclass}" style="width: 100%">
      <display:column title="顺序" class="orderwidth"><%=offset++%></display:column>
      <display:column title="标题" property="title"></display:column>
      <display:column title="链接地址"><a href="${item.msg}" target="_blank">${item.msg}</a></display:column>
      <display:column title="日期" property="date" format="{0,date,yyyy-MM-dd HH:mm}" sortable="true"></display:column>
      <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
        <display:column title="商城" property="userName" sortable="true"></display:column>
      </auth:auth>
      <display:column title="操作" media="html" class="actionwidth">
      <a href= "${pageContext.request.contextPath}/admin/pub/load/${item.id}${applicationScope.WEB_SUFFIX}" title="修改"><img alt="修改" src="${pageContext.request.contextPath}/img/grid_edit.png"></a>
      <auth:auth ifAnyGranted="F_OPERATOR">
        <a href='javascript:deleteById("${item.id}")' title="删除"><img alt="删除" src="${pageContext.request.contextPath}/img/grid_delete.png"></a>
      </auth:auth>
      </display:column>
    </display:table>
        <c:if test="${not empty toolBar}">
            <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
        </c:if>
    </div>
   <table style="width: 100%; border: 0px"><tr><td align="left">说明：<br>
   1. 出现在商城首页，用于商家发布公告信息<br>
   </td><tr></table> 
    <script language="JavaScript" type="text/javascript">
<!--

  function deleteById(id) {
      if(confirm("  确定删除 ?")){
            window.location = "${pageContext.request.contextPath}/admin/pub/delete/"+id+"${applicationScope.WEB_SUFFIX}";
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

