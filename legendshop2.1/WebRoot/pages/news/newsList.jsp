<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common.jsp"%>
<%@ include file="/common/jsp/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ taglib uri="/WEB-INF/tld/options.tld" prefix="option"%>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<%@ taglib uri="/WEB-INF/tld/options.tld" prefix="option" %>
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
            window.location = "${root}/admin/news/delete/"+id+".c";
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

    <title>新闻列表</title>
</head>
<body class="bodymargin">
    <%
        Integer offset = (Integer) request.getAttribute("offset");
    %>
    <form action="${root}/admin/news/query.c" id="form1" method="post">
    <table class="mars" style="width: 100%">
    <thead>
    	<tr><td>商家管理  >> 新闻管理</td></tr>
    </thead>
    </table>
        <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
	            标题
	            <input type="text" name="newsTitle" maxlength="50" value="${bean.newsTitle}" />
	     状态
	           <select id="status" name="status">
				  <option:optionGroup type="select" required="false" cache="true"
	                beanName="NEW_STAUS" selectedValue="${bean.status}"/>
	            </select>
         <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
                 店铺 
               <input type="text" name="userName" maxlength="50" value="${bean.userName}" />
        </auth:auth>
               <input type="submit" value="搜索"/>
            <a href="${root}/admin/addnews.jsp" title="创建新闻"><img src="${root}/img/grid_add.png"></a>
            <br>
    </form>
        <%@ include file="/common/jsp/messages.jsp"%>
    <display:table name="list" requestURI="/admin/news/query.c" id="item"
         export="false" class="${tableclass}" style="width:100%">
      <display:column title="顺序" class="orderwidth"><%=offset++%></display:column>
      <display:column title="标题"><a href="${root}/news/${item.newsId}" target="_blank">${item.newsTitle}</a></display:column>
      <display:column title="录入时间" property="newsDate" format="{0,date,yyyy-MM-dd hh:mm:ss}" sortable="true"></display:column>
      <display:column title="状态" sortable="true">
           <c:choose>
                <c:when test="${item.status == 0}">链接</c:when>
                <c:when test="${item.status == 1}"><font color="blue">新闻</font></c:when>
                <c:otherwise><font color="red">下线</font></c:otherwise>
           </c:choose>
      </display:column>
      <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
        <display:column title="店铺" property="userName" sortable="true"></display:column>
      </auth:auth>
      <display:column title="操作" media="html" class="actoinwidth">
      <a href= "${root}/admin/updateNews.do?newsId=${item.newsId}" title="修改"><img alt="修改" src="${root}/img/grid_edit.png"></a>
      <auth:auth ifAnyGranted="F_OPERATOR">
        <a href='javascript:deleteById("${item.newsId}")' title="删除"><img alt="删除" src="${root}/img/grid_delete.png"></a>
      </auth:auth>
      </display:column>
    </display:table>
        <c:if test="${not empty toolBar}">
            <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
        </c:if>
</body>
</html>

