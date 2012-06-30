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
    <title>栏目列表</title>
</head>
<body>
    <%
        Integer offset = (Integer) request.getAttribute("offset");
    %>
    <form action="${pageContext.request.contextPath}/admin/newsCategory/query${applicationScope.WEB_SUFFIX}" id="form1" method="post">
        <table class="${tableclass}" style="width: 100%">
    <thead>
    	<tr><td><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商城管理  &raquo; <a href="${pageContext.request.contextPath}/admin/newsCategory/query${applicationScope.WEB_SUFFIX}">栏目管理</a></td></tr>
    </thead>
    </table>
        <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
        	栏目名称
	            <input type="text" name="newsCategoryName" maxlength="50" value="${bean.newsCategoryName}" />
	     状态
	           <select id="status" name="status">
				  <option:optionGroup type="select" required="true" cache="true"
	                beanName="ONOFF_STATUS" selectedValue="${bean.status}"/>
	            </select>
         <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
                 商城 
               <input type="text" name="userName" maxlength="50" value="${bean.userName}" />
        </auth:auth>
               <input type="submit" value="搜索"/>
            <input type="button" value="创建栏目" onclick='window.location="${pageContext.request.contextPath}/admin/newsCategory/load${applicationScope.WEB_SUFFIX}"'/>
            <input type="button" value="新闻列表" onclick='window.location="${pageContext.request.contextPath}/admin/news/query${applicationScope.WEB_SUFFIX}"'/>
    </form>
    <div align="center">
        <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
    <display:table name="list" requestURI="/admin/newsCategory/query${applicationScope.WEB_SUFFIX}" id="item"
         export="true" class="${tableclass}" style="width:100%">
      <display:column title="顺序"><%=offset++%></display:column>
      <display:column title="栏目名称" property="newsCategoryName"></display:column>
      <display:column title="状态">
           <c:choose>
                <c:when test="${item.status == 0}"><font color="red">下线</font></c:when>
                <c:otherwise>上线</c:otherwise>
           </c:choose>
      
      </display:column>
      <display:column title="建立时间" property="newsCategoryDate" format="{0,date,yyyy-MM-dd HH:mm}" sortable="true"></display:column>
      <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
            <display:column title="商城" property="userName"></display:column>
      </auth:auth>
      <display:column title="操作" media="html" style="width:40px">
      <a href= "${pageContext.request.contextPath}/admin/newsCategory/load/${item.newsCategoryId}${applicationScope.WEB_SUFFIX}" title="修改"><img alt="修改" src="<ls:templateResource item='/common/images/default/grid_edit.png'/> "></a>
      <auth:auth ifAnyGranted="F_OPERATOR">
      <a href='javascript:deleteById("${item.newsCategoryId}")'title="删除"><img alt="删除" src="<ls:templateResource item='/common/images/default/grid_delete.png'/> "></a>
      </auth:auth>
      </display:column>
    </display:table>
        <c:if test="${not empty toolBar}">
            <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
        </c:if>
    </div>
 <table style="width: 100%; border: 0px"><tr><td align="left">说明：<br>
   1. 每个文章都是挂在栏目下面，是文章的分类<br>
   2. 栏目处于上线状态页面可见，处于下线状态页面不可见<br>
   </td><tr></table> 
    <script language="JavaScript" type="text/javascript">
<!--

  function deleteById(id) {
      if(confirm("  确定删除 ?")){
            window.location = "${pageContext.request.contextPath}/admin/newsCategory/delete/"+id+"${applicationScope.WEB_SUFFIX}";
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

