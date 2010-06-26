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
            window.location = "${root}/admin/advertisement/delete/"+id+".c";
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

    <title>广告列表</title>
</head>
<body class="bodymargin">
    <%
        Integer offset = (Integer) request.getAttribute("offset");
    %>
    <form action="${root}/admin/advertisement/query.c" id="form1" method="post">
    <table class="mars" style="width: 100%">
    <thead>
    	<tr><td>商家管理  >> 广告管理</td></tr>
    </thead>
    </table>
        <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
                <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
            店铺
            <input type="text" name="userName" maxlength="50" value="${bean.userName}" />
            <input type="submit" value="搜索"/>
            </auth:auth>
            <a href="${root}/pages/advertisement/advertisement.jsp" title="创建广告"><img src="${root}/img/grid_add.png"></a>
    </form>
    <div align="center">
        <%@ include file="/common/jsp/messages.jsp"%>
        <c:if test="${not empty list}">
		    <display:table name="list" requestURI="/admin/advertisement/query.c" id="item"
		         export="false" class="${tableclass}" style="width:100%">
		      <display:column title="顺序" class="orderwidth"><%=offset++%></display:column>
		      <display:column title="类型">
		      	<c:if test='${item.type eq "COUPLET" }'>对联广告</c:if>
		      	<c:if test='${item.type eq "COUPLET1" }'>横幅广告</c:if>
		      </display:column>
		      <display:column title="链接地址"><a href="${item.linkUrl }" target="_blank">${item.linkUrl }</a></display:column>
		      <display:column title="图片">
		      <a href="${PHOTO_PATH}/${item.picUrl}" target="_blank"><img src="${root}/img/filter_search.png"></a></a>
		      </display:column>
                <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
		          <display:column title="店铺" property="userName"></display:column>
                </auth:auth>
		      <display:column title="操作" media="html" class="actionwidth">
		      <a href= "${root}/admin/advertisement/load/${item.id}.c" title="修改"><img alt="修改" src="${root}/img/grid_edit.png"></a>
		      <auth:auth ifAnyGranted="F_OPERATOR">
		          <a href='javascript:deleteById("${item.id}")' title="删除"><img alt="删除" src="${root}/img/grid_delete.png"></a>
		      </auth:auth>
		      </display:column>
		    </display:table>
        </c:if>
        <c:if test="${not empty toolBar}">
            <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
        </c:if>
    </div>
</body>
</html>

