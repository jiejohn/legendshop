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
            window.location = "${root}/admin/nsort/delete/"+id+".c";
        }
    }

        function pager(curPageNO){
            document.getElementById("curPageNO").value=curPageNO;
            document.getElementById("form1").submit();
        }
//-->
</script>

<jsp:scriptlet> String lClass = "its";
   if( request.getParameter( "class" ) != null ) {
      lClass = request.getParameter( "class" );
      if (!("isis".equals(lClass) || "its".equals(lClass) || "mars".equals(lClass) || "simple".equals(lClass) || "report".equals(lClass) || "mark".equals(lClass)))
      {
        lClass="";
      }
   }
   pageContext.setAttribute("tableclass", lClass);
</jsp:scriptlet>

    <title>Nsort 列表</title>
</head>
<body>
    <%
        Integer offset = (Integer) request.getAttribute("offset");
    %>
    <br>
    <form action="${root}/admin/nsort/query.c" id="form1" method="post">
        <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
        <input type="hidden" id="sortId" name="sortId" value="${bean.sortId}" />
        <div align="center">二级分类名称：
            <input type="text" name="name" maxlength="50" value="${name}" />
            <input type="submit" value="搜索"/>
            &nbsp;&nbsp;
            <a href="${root}/pages/nsort/nsort.jsp?sortId=${bean.sortId}">创建二级分类</a>
            <br>
        </div>
    </form>

    <div align="center">
        <%@ include file="/common/jsp/messages.jsp"%>
    <display:table name="list" requestURI="/admin/nsort/query.c" id="parent"
         export="true" class="${tableclass}" style="width:100%">
      <display:column title="顺序"><%=offset++%></display:column>
      <display:column title="二级分类名字" property="nsortName"></display:column>
      <display:column title="次序" property="seq"></display:column>
      <c:set var="subSortList" value="${parent.subSort}" scope="request"/>
      <display:column title="三级分类" >
      <c:if test="${fn:length(subSortList) > 0}">
	    <display:table name="subSortList"  id="child" cellpadding="0" cellspacing="0">
	      <display:column title="三级分类名字" property="nsortName"></display:column>
	      <display:column title="次序" property="seq"></display:column>
	      <display:column title="操作" media="html" autolink="true">
	      <auth:auth ifAnyGranted="F_OPERATOR">
	      <a href= "${root}/admin/nsort/load/${child.nsortId}.c?sortId=${bean.sortId}&parentNsortId=${parent.nsortId}"> 修改</a>
	      <a href='javascript:deleteById("${child.nsortId}")'>删除</a>
	      </auth:auth>
      </display:column>
	    </display:table>
	    </c:if>
      </display:column>
      <display:column title="操作" media="html">
      <auth:auth ifAnyGranted="F_OPERATOR">
      <a href= "${root}/pages/nsort/nsort.jsp?sortId=${bean.sortId}&parentNsortId=${parent.nsortId}">新增三级分类</a>
      <a href= "${root}/admin/nsort/load/${parent.nsortId}.c?sortId=${bean.sortId}">修改</a>
      <a href='javascript:deleteById("${parent.nsortId}")'>删除</a>
      </auth:auth>
      </display:column>
    </display:table>
        <c:if test="${not empty toolBar}">
            <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
        </c:if>
    </div>
</body>
</html>

