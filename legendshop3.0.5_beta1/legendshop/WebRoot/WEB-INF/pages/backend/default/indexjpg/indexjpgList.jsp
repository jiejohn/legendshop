<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.legendshop.core.helper.PropertiesUtil"%>
<%@page import="com.legendshop.core.constant.ParameterEnum"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
<%@ taglib uri="/WEB-INF/tld/options.tld" prefix="option"%>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
<head>
     <script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
      <script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
<title>首页广告图片列表</title>
</head>
<body>
<% Integer offset = (Integer)request.getAttribute("offset");%>
    <table class="${tableclass}" style="width: 100%">
    <thead>
    	<tr><td><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商品管理  &raquo; <a href="${pageContext.request.contextPath}/admin/indexjpg/query">首页Flash图片管理</a></td></tr>
    </thead>
     <tbody><tr><td>
 <div align="left" style="padding: 3px">
<form id="form1" action="${pageContext.request.contextPath}/admin/indexjpg/query">
	        <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}">
			<auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
			商城名称
			<input type="text" name="userName" id="userName" maxlength="50" value="${indexJpg.userName}" />
			<input type="submit" value="搜索"/>
			</auth:auth>
			<input type="button" value="创建首页图片" onclick='window.location="${pageContext.request.contextPath}/admin/indexjpg/load"'/>
	</form>
 </div>
 </td></tr></tbody>
    </table>
	<div align="center">
        <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
    <display:table name="list" requestURI="/admin/indexjpg/query" id="item"
         export="true" class="${tableclass}" style="width:100%">
      <display:column title="顺序" class="orderwidth"><%=offset++%></display:column>
      <display:column title="说明文字" property="alt"></display:column>
      <display:column title="标题" property="title"></display:column>
      <display:column title="小标题" property="stitle"></display:column>
      <display:column title="图片"><a href="<ls:photo item='${item.img}'/>" target="_blank"><img src="${pageContext.request.contextPath}/common/default/images/filter_search.png"></a></display:column>
      <display:column title="链接">
      	    	<a href="${item.link}" target="_blank"><img src="${pageContext.request.contextPath}/common/default/images/grid_refresh.png" title="图片"></a>
    	        <a href="${pageContext.request.contextPath}/${item.titleLink}" target="_blank"><img src="${pageContext.request.contextPath}/common/default/images/grid_refresh.png" title="文字"></a>
      </display:column>
      <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
            <display:column title="商城名称" property="userName"></display:column>
      </auth:auth>
      <display:column title="操作" media="html" class="actionwidth">
	     <a href="${pageContext.request.contextPath}/admin/indexjpg/update/${item.id}" title="修改"><img alt="修改" src="<ls:templateResource item='/common/default/images/grid_edit.png'/> "></a>
	     <auth:auth ifAnyGranted="F_OPERATOR">
	        <a href='javascript:deleteById("${item.id}")' title="删除"><img alt="删除" src="<ls:templateResource item='/common/default/images/grid_delete.png'/> "></a>
	     </auth:auth>
      </display:column>
    </display:table>
        <c:if test="${not empty toolBar}">
            <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
        </c:if>
        </div>
	
	
<table style="width: 100%; border: 0px"><tr><td align="left">说明：<br>
   1. 首页中上部广告图片，文字均可以加上超链接<br>
   2. 创建首页中上部广告图片，注意:最多上传<b><%=PropertiesUtil.getObject(ParameterEnum.MAX_INDEX_JPG,Integer.class)%></b>张图片<br>
   3. <img src="${pageContext.request.contextPath}/common/default/images/grid_refresh.png" title="文字连接">指向图片、文字连接地址<br>
   4. <img alt="修改" src="<ls:templateResource item='/common/default/images/grid_edit.png'/> "> 修改按钮<br>
   5. <img alt="删除" src="<ls:templateResource item='/common/default/images/grid_delete.png'/> "> 删除按钮<br>
   </td><tr></table> 
   
   <script language="JavaScript" type="text/javascript">
<!--
function deleteById(id) {
    if(confirm("  确定删除 ?")){
          window.location = "${pageContext.request.contextPath}/admin/indexjpg/delete/"+id ;
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

