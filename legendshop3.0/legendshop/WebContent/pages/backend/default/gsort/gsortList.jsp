<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/back-common.jsp"%>
<%@ include file="/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
<head>
     <script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
     <script src="<ls:templateResource item='/css/alternative.js'/>" type="text/javascript"></script>
<title>分类列表</title>
</head>
<body class="bodymargin">
<%
			Integer offset = (Integer)request.getAttribute("offset");
	%>
    <table class="${tableclass}" style="width: 100%">
    <thead>
    	<tr><td><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 团购管理  &raquo; <a href="${pageContext.request.contextPath}/admin/gsort/query${applicationScope.WEB_SUFFIX}">团购分类</a></td></tr>
    </thead>
    </table>
    <form id="form1" action="${pageContext.request.contextPath}/admin/gsort/query${applicationScope.WEB_SUFFIX}">
		<input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}">
			&nbsp; 类型名称
			<input type="text" name="sortName"  id="sortName" maxlength="50" value="${sort.sortName}" />
			<auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
			&nbsp; 商城名称
			<input type="text" name="userName"  id="userName" maxlength="50" value="${sort.userName}" />
			</auth:auth>
			<input type="submit" value="搜索"/>
			<input type="button" value="创建商品类型" onclick='window.location="${pageContext.request.contextPath}/admin/gsort/load${applicationScope.WEB_SUFFIX}"'/>
			<input type="button" value="返回商品列表" onclick='window.location="<ls:url address='/admin/product/query'/>"'/>
			</form>
  <div align="center">
        <%@ include file="/pages/common/messages.jsp"%>
    <display:table name="list" requestURI="/admin/gsort/query${applicationScope.WEB_SUFFIX}" id="item"
         export="true" class="${tableclass}" style="width:100%" sort="external">
      <display:column title="顺序" class="orderwidth"><%=offset++%></display:column>
      <display:column title="名称" property="sortName" sortable="true" sortName="sortName"></display:column>
      <display:column title="次序" property="seq" sortable="true" sortName="seq"></display:column>
      <display:column title="图片" style="width:300px" media="html"><a href="${pageContext.request.contextPath}/photoserver/photo/${item.picture}" target="_blank"><img src="${pageContext.request.contextPath}/photoserver/photo/${item.picture}" height="60" width="300"/></a></display:column>
      <auth:auth ifAnyGranted="F_VIEW_ALL_DATA"><display:column title="商城名称" property="userName" sortable="true" sortName="userName"></display:column></auth:auth>
      <display:column title="操作" media="html" style="width:100px">
    <a href="${pageContext.request.contextPath}/admin/nsort/query${applicationScope.WEB_SUFFIX}?sortId=${item.sortId}">二级类型</a>
     <a href="${pageContext.request.contextPath}/admin/gsort/update/${item.sortId}${applicationScope.WEB_SUFFIX}" title="修改"><img alt="修改" src="<ls:templateResource item='/img/grid_edit.png'/> "></a>
     <auth:auth ifAnyGranted="F_OPERATOR">
        <a href='javascript:deleteSort("${item.sortId}")' title="删除"><img alt="删除" src="<ls:templateResource item='/img/grid_delete.png'/> "></a>
     </auth:auth>  
      </display:column>
    </display:table>
        <c:if test="${not empty toolBar}">
            <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
        </c:if>
        </div>
     <table style="width: 100%; border: 0px"><tr><td align="left">说明：<br>
   1. 商品类型分为三级，一级商品类型带有一个说明图片，用于页面广告介绍，三级分类下可以挑选对应的商品品牌<br>
   2. 商品可以挂在一级，二级或者三级之上，三个级别有关联关系<br>
   3. <img alt="创建商品类型" src="${pageContext.request.contextPath}/img/grid_add.png">&nbsp;创建商品类型，在不同列表中创建不同级别的商品类型<br>
   4. <img alt="修改" src="<ls:templateResource item='/img/grid_edit.png'/> "> 修改按钮，在一级商品类型列表中编辑一级商品类型，其他级别一样<br>
   5. <img alt="删除" src="<ls:templateResource item='/img/grid_delete.png'/> "> 删除按钮，在一级商品类型列表中编辑删除一级商品类型，其他级别一样，删除前确保其下的元素已经删除<br>
   </td><tr></table> 
   
   <script language="JavaScript" type="text/javascript">
<!--
  function confirmDelete()
	{
	    return con = confirm("确定要删除吗？");
	}
	
  function deleteSort(sortId){
  if(confirmDelete()){
  	 window.location.href = "${pageContext.request.contextPath}/admin/gsort/delete/"+sortId +"${applicationScope.WEB_SUFFIX}";
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

