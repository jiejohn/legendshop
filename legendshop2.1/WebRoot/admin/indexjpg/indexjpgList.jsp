<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common.jsp"%>
<%@ include file="/common/jsp/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
<html:html>
<head>
     <script src="${root}/common/js/jquery.js" type="text/javascript"></script>
         <style type="text/css" media="all">
       @import url("${root}/css/screen.css");
    </style>
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
<script language="JavaScript" type="text/javascript">
<!--
function deleteById(id) {
    if(confirm("  确定删除 ?")){
          window.location = "${root}/admin/deleteindexJpg.do?id="+id;
      }
  }

  $(document).ready(function() {
	      $("#col1 tr").each(function(i){
	      if(i>0){
	         if(i%2 == 0){
	             $(this).addClass('even');
	         }else{    
	              $(this).addClass('odd'); 
	         }   
	    }
	     });   
	         $("#col1 th").addClass('sortable'); 
	});
//-->
</script>

<title>首页广告图片列表</title>
</head>
<body class="bodymargin">
<%
			String curPageNO = (String) request.getParameter("curPageNO");
			int curpage = 1;
			if ("".equals(curPageNO) || curPageNO == null)
				curpage = 1;
			else {
				curpage = Integer.parseInt(curPageNO);
			}
			Integer offset = (Integer)request.getAttribute("offset");
	%>
<form action="${root}/admin/indexjpgList.do">
    <table class="mars" style="width: 100%">
    <thead>
    	<tr><td>商家管理  >> 首页广告图片管理</td></tr>
    </thead>
    </table>
	        <input type="hidden" name="curPageNO" value="${curPageNO}">
			<auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
			店铺
			<input type="text" name="search" maxlength="50" value="<%=request.getAttribute("search")%>" />
			<html:submit value="搜索" property="Submit" />
			</auth:auth>
			<a href="${root}/admin/indexjpg/saveIndexJpg.jsp" title="创建首页图片"><img src="${root}/img/grid_add.png"></a>
			&nbsp;注意:最多上传7张图片
<c:if test="${requestScope.list != null && fn:length(requestScope.list) > 0}">
<table class="${tableclass}" id="col1" style="width:100%">
<thead style="font-weight: bold">
  <tr>
    <td>顺序</td>
    <td>图片</td>
    <td>说明文字</td>
    <td>标题</td>
    <td>小标题</td>
    <td>图片链接</td>
    <td>文字链接</td>
    <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
        <td align="center"  width="10%">店铺</td>
    </auth:auth>
     <td>操作</td>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${requestScope.list}" var="pic">
  <tr>
    <td width="30px"><%=offset++%></td>
    <td>
     <a href="${PHOTO_PATH}/${pic.img}" target="_blank"><img src="${root}/img/filter_search.png"></a>
    </td>
    <td>${pic.alt}</td>
    <td>${pic.title}</td>
    <td>${pic.stitle}</td>
    <td align="center" >
    <a href="${pic.link}" target="_blank"><img src="${root}/img/grid_refresh.png"></a>
    </td>
    <td>
    <a href="${root}/${pic.titleLink}" target="_blank"><img src="${root}/img/grid_refresh.png"></a>
    </td>
    <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
         <td align="center" >${pic.userName}</td>
    </auth:auth>
    <td width="40px">
     <a href="${root}/admin/updateIndexJpg.do?id=${pic.id}" title="修改"><img alt="修改" src="${root}/img/grid_edit.png"></a>
     <auth:auth ifAnyGranted="F_OPERATOR">
        <a href='javascript:deleteById("${pic.id}")' title="删除"><img alt="删除" src="${root}/img/grid_delete.png"></a>
     </auth:auth>
     </td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</c:if>
</form>
	<logic:present name="toolBar">
		<bean:write name="toolBar" filter="false"/>
	</logic:present>
</body>
</html:html>

