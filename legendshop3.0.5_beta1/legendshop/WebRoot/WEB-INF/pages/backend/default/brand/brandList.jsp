<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ taglib uri="/WEB-INF/tld/options.tld" prefix="option"%>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
<head>
     <script type='text/javascript' src="<ls:templateResource item='/dwr/engine.js'/>"></script>
     <script type='text/javascript' src="<ls:templateResource item='/dwr/util.js'/>"></script>
     <script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
    <title>商品品牌列表</title>
</head>
<body class="bodymargin">
    <%
        Integer offset = (Integer) request.getAttribute("offset");
    %>
    <form action="${pageContext.request.contextPath}/admin/brand/query" id="form1" method="post">
    	<table class="${tableclass}" style="width: 100%">
	    <thead>
	    	<tr><td><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商品管理  &raquo; <a href="${pageContext.request.contextPath}/admin/brand/query">品牌管理</a></td></tr>
	    </thead>
	    <tbody><tr><td>
	    <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
        <div align="left" style="padding: 3px">
        <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
            商城名称&nbsp;<input type="text" name="userName" maxlength="50" value="${bean.userName}" />
            <input type="submit" value="搜索"/>
            </auth:auth>
            <input type="button" value="创建商品品牌" onclick='window.location="${pageContext.request.contextPath}/admin/brand/load"'/>
            <input type="button" value="返回商品列表" onclick='window.location="<ls:url address='/admin/product/query'/>"'/>
            </div>
	    </td></tr></tbody>
	    </table>
        
    </form>
    <div align="center">
        <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
    <display:table name="list" requestURI="/admin/brand/query" id="item"
         export="true" class="${tableclass}" style="width:100%">
      <display:column title="顺序"><%=offset++%></display:column>
      <display:column title="品牌名称" property="brandName"></display:column>
      <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
      <display:column title="商城名称" property="userName"></display:column>
      </auth:auth>
      <display:column title="备注" property="memo"></display:column>
      <display:column title="操作" media="html" style="width:40px">
      <a href= "${pageContext.request.contextPath}/admin/brand/update/${item.brandId}" title="修改"><img alt="修改" src="<ls:templateResource item='/common/default/images/grid_edit.png'/> "></a>
      <auth:auth ifAnyGranted="F_OPERATOR">
      	<a href='javascript:deleteById("${item.brandId}")' title="删除"><img alt="删除" src="<ls:templateResource item='/common/default/images/grid_delete.png'/> "></a>
      </auth:auth>
      </display:column>
    </display:table>
        <c:if test="${not empty toolBar}">
            <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
        </c:if>
    </div>
         <table style="width: 100%; border: 0px"><tr><td align="left">说明：<br>
   1. 商品品牌带有一个品牌图片<br>
   2. 品牌可以挂在商品三级类型上，在用户选择了三级类型之后出现对应的品牌<br>
   3. <img alt="修改" src="<ls:templateResource item='/common/default/images/grid_edit.png'/> "> 修改按钮<br>
   4. <img alt="删除" src="<ls:templateResource item='/common/default/images/grid_delete.png'/> "> 删除按钮，删除品牌，如果删除品牌则不影响其下的商品<br>
   </td><tr></table> 
   
   <script language="JavaScript" type="text/javascript">
<!--

  function deleteById(id) {
      if(confirm("  确定删除 ?")){
            window.location = "${pageContext.request.contextPath}/admin/brand/delete/"+id;
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

