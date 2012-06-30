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
    <title>商品评论列表</title>
</head>
<body>
    <%
        Integer offset = (Integer) request.getAttribute("offset");
    %>
    <form action="${pageContext.request.contextPath}/admin/productcomment/query${applicationScope.WEB_SUFFIX}" id="form1" method="post">
	   <table class="${tableclass}" style="width: 100%">
	    <thead>
	    	<tr><td><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商品管理  &raquo; <a href="${pageContext.request.contextPath}/admin/productcomment/query${applicationScope.WEB_SUFFIX}">评论管理</a></td></tr>
	    </thead>
	    </table>
        	<input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
        	评价人&nbsp;<input type="text" name="userName" id="userName" maxlength="50" value="${bean.userName}" />
              商品名称&nbsp;<input type="text" name="prodName" id="prodName" maxlength="50" value="${bean.prodName}" />
           <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
            	商城名称&nbsp;<input type="text" name="ownerName" maxlength="50" value="${bean.ownerName}" />
            </auth:auth>
            <input type="submit" value="搜索"/>
            <br>
    </form>
    <div align="center">
        <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
    <display:table name="list" requestURI="/admin/productcomment/query${applicationScope.WEB_SUFFIX}" id="item"
         export="true" class="${tableclass}" style="width:100%" sort="external">
      <display:column title="顺序"><%=offset++%></display:column>
      <display:column title="商品" sortable="true" sortName="prod.name">
      	<a target="_blank" href="${pageContext.request.contextPath}/views/${item.prodId}${applicationScope.WEB_SUFFIX}">${item.prodName}</a>
      </display:column>
      <display:column title="评价人" property="userName"></display:column>
      <display:column title="评论内容" property="content"></display:column>
      <display:column title="评价时间" sortable="true" sortName="hc.addtime"><fmt:formatDate value="${item.addtime}" pattern="yyyy-MM-dd HH:mm"/></display:column>
      <display:column title="评价人IP" property="postip" sortable="true" sortName="hc.postip"></display:column>
      <display:column title="回复内容" property="replyContent"></display:column>
      <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
      	<display:column title="回复人" property="replyName"></display:column>
      	<display:column title="商城名称" property="ownerName"></display:column>
      </auth:auth>
      <display:column title="回复时间"><fmt:formatDate value="${item.replyTime}" pattern="yyyy-MM-dd HH:mm"/></display:column>
      <display:column title="操作" media="html" class="actionwidth">
      		<a href= "${pageContext.request.contextPath}/admin/productcomment/load/${item.id}${applicationScope.WEB_SUFFIX}" title="回复"><img alt="回复" src="<ls:templateResource item='/common/images/default/grid_edit.png'/> "></a>
		      <auth:auth ifAllGranted="F_OPERATOR">
		    <a href='javascript:deleteById("${item.id}")' title="删除"><img alt="删除" src="<ls:templateResource item='/common/images/default/grid_delete.png'/> "></a>
		      </auth:auth>
      </display:column>
    </display:table>
        <c:if test="${not empty toolBar}">
            <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
        </c:if>
    </div>
     <table style="width: 100%; border: 0px"><tr><td align="left">说明：<br>
   1. 用户在前台录入商品评论<br>
   2. 可以回复商品评论或者删除不合理的评论<br>
   </td><tr></table>    
    <script language="JavaScript" type="text/javascript">
<!--

  function deleteById(id) {
      if(confirm("  确定删除 ?")){
            window.location = "${pageContext.request.contextPath}/admin/productcomment/delete/"+id+"${applicationScope.WEB_SUFFIX}";
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

