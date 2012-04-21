<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/back-common.jsp"%>
<%@ include file="/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ taglib uri="/WEB-INF/tld/options.tld" prefix="option"%>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
<head>
    <script type='text/javascript' src="<ls:templateResource item='/dwr/interface/CommonService.js'/>"></script>
    <script type='text/javascript' src="<ls:templateResource item='/dwr/interface/optionService.js'/>"></script>
    <script type='text/javascript' src="<ls:templateResource item='/dwr/engine.js'/>"></script>
   <script type='text/javascript' src="<ls:templateResource item='/dwr/util.js'/>"></script>
    <script src="<ls:templateResource item='/css/alternative.js'/>" type="text/javascript"></script>
    <title>加盟商城列表</title>
</head>
<body class="bodymargin">
    <%
        Integer offset = (Integer) request.getAttribute("offset");
    %>
    <form action="${pageContext.request.contextPath}/admin/myleague/query${applicationScope.WEB_SUFFIX}" id="form1" method="post">
        <table class="${tableclass}" style="width: 100%">
	    <thead>
	    	<tr><td><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商城管理  &raquo; <a href="${pageContext.request.contextPath}/admin/myleague/query${applicationScope.WEB_SUFFIX}">加盟商城管理</a></td></tr>
	    </thead>
	    </table>
	         <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">&nbsp;商城
               <input type="text" name="userId" maxlength="50" value="${bean.userId}" />
             </auth:auth>
            <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />加盟商城
            <input type="text" name="friendId" maxlength="50" value="${bean.friendId}" />
	        <input type="submit" value="搜索"/>
    </form>
    <div align="center">
        <%@ include file="/pages/common/messages.jsp"%>
    <display:table name="list" requestURI="/admin/myleague/query${applicationScope.WEB_SUFFIX}" id="item"
         export="true" class="${tableclass}" style="width:100%">
      <display:column title="顺序" class="orderwidth"><%=offset++%></display:column>
      <display:column title="加盟商城">
      	<a href="${pageContext.request.contextPath}/shop/${item.friendId}${applicationScope.WEB_SUFFIX}" target="_blank">${item.friendId}</a>
      </display:column>
      <display:column title="加盟商城说明" property="friendName"></display:column>
      <display:column title="显示顺序" property="displayOrder" sortable="true"></display:column>
      <display:column title="申请时间" property="addtime" sortable="true"></display:column>
       <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
        <display:column title="商城" property="userId" sortable="true"></display:column>
      </auth:auth>
      <display:column title="操作" media="html" class="actionwidth">
      <a href= "${pageContext.request.contextPath}/admin/myleague/load/${item.id}${applicationScope.WEB_SUFFIX}"><img alt="修改" src="<ls:templateResource item='/img/grid_edit.png'/> "></a>
      <auth:auth ifAllGranted="F_OPERATOR">
      <a href='javascript:deleteById("${item.id}")'><img alt="删除" src="<ls:templateResource item='/img/grid_delete.png'/> "></a>
      </auth:auth>
      </display:column>
    </display:table>
        <c:if test="${not empty toolBar}">
            <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
        </c:if>
    </div>
   <table style="width: 100%; border: 0px"><tr><td align="left">说明：<br>
   1. 加盟商城将会出现在您的商城的加盟商城页面，用于商城之间的导航和组成联盟关系<br>
   2. 请在前台浏览商城时将你喜欢的商城加为加盟商城，没有任何限制<br>
   3. 删除加盟商城之后将不会在前台显示<br>
   </td><tr></table> 
     <script language="JavaScript" type="text/javascript">
<!--

  function deleteById(id) {
      if(confirm("  确定删除 ?")){
            window.location = "${pageContext.request.contextPath}/admin/myleague/delete/"+id+"${applicationScope.WEB_SUFFIX}";
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

