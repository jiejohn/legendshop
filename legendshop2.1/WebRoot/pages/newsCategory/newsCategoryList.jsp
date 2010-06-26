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
            window.location = "${root}/admin/newsCategory/delete/"+id+".c";
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

    <title>NewsCategory 列表</title>
</head>
<body>
    <%
        Integer offset = (Integer) request.getAttribute("offset");
    %>
    <br>
    <form action="${root}/admin/newsCategory/query.c" id="form1" method="post">
    
        <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
        <div align="center">
            <input type="text" name="userName" maxlength="50" value="key word" />
            <input type="submit" value="搜索"/>
            &nbsp;&nbsp;
            <a href="${root}/pages/newsCategory/newsCategory.jsp">创建 NewsCategory</a>
            <br>
        </div>
    </form>
    <div align="center">
        <%@ include file="/common/jsp/messages.jsp"%>
    <display:table name="list" requestURI="/admin/newsCategory/query.c" id="item"
         export="false" class="${tableclass}">
      <display:column title="Order"><%=offset++%></display:column>
      <display:column title="NewsCategoryId" property="newsCategoryId"></display:column>
      <display:column title="NewsCategoryName" property="newsCategoryName"></display:column>
      <display:column title="Status" property="status"></display:column>
      <display:column title="NewsCategoryDate" property="newsCategoryDate"></display:column>
      <display:column title="UserId" property="userId"></display:column>
      <display:column title="UserName" property="userName"></display:column>
      <display:column title="Action" media="html">
      <a href= "${root}/admin/newsCategory/load/${item.newsCategoryId}.c"> 修改</a>
      <a href='javascript:deleteById("${item.newsCategoryId}")'>删除</a>
      </display:column>
    </display:table>
        <c:if test="${not empty toolBar}">
            <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
        </c:if>
    </div>
</body>
</html>

