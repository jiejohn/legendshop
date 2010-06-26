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
  function confirmDelete()
	{
	    return con = confirm(" 确定要删除？");
	}
	
  function openWindows(id) {
 		 var win = window.open('${root}/admin/viewUserComment.do?id='+id,'回复用户留言','height=320px,width=650px, scroll=no, status=yes,top=250,left=250');	
		 win.focus() ;
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
		$("#status").val('${status}');
	});
</script>
<title>用户留言列表</title>
</head>
<body>
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
	<form action="${root}/admin/userCommentList.do">
	<table class="mars" style="width: 100%">
    <thead>
    	<tr><td>商家管理  >> 留言管理</td></tr>
    </thead>
    </table>
	<input type="hidden" name="curPageNO" value="${curPageNO}" id="form1">
			登录用户
			<input type="text" name="userName" maxlength="50" value="<%=request.getAttribute("userName")%>" />
			<auth:auth ifAnyGranted="F_VIEW_ALL_DATA">

			店铺
			<input type="text" name="search" maxlength="50" value="<%=request.getAttribute("search")%>" />
			</auth:auth>
			<select id="status" name="status">
				<option value="">全部</option>
				<option value="0">未读</option>
				<option value="1">已读</option>
			</select>
			<html:submit value="搜索" property="Submit" />
<c:if test="${requestScope.list != null && fn:length(requestScope.list) > 0}">
<table class="${tableclass}" id="col1" style="width:100%">
<thead>
  <tr>
    <th>顺序</th>
    <th style="display: none">留言类型<br>1:投诉，2：留言</th>
    <th>登录用户</th>
    <th>用户昵称</th>
    <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
    <th>店铺</th>
    </auth:auth>
    <th>留言内容</th>
    <th>回复内容</th>
    <th>添加时间</th>
    <th>IP来源</th>
    <th>状态</th>
     <th>操作</th>
  </tr>
   </thead>
    <tbody>
  <c:forEach items="${requestScope.list}" var="comment">
  <tr>
    <td style="width:30px"><%=offset++%></td>
    <td style="display: none">
     <c:choose>
    	<c:when test="${comment.commentType==1}">投诉</c:when>
    	<c:when test="${comment.commentType==2}">留言</c:when>
    	<c:otherwise>其他</c:otherwise>
    </c:choose>
   
    </td>
    <td>
    <c:choose>
    	<c:when test="${comment.userName !=null && comment.userName != 'null'}">${comment.userName}</c:when>
    	<c:otherwise>匿名用户</c:otherwise>
    </c:choose>
    </td>
    <td>${comment.yourName}</td>
    <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
    <td>${comment.toUserName}</td>
    </auth:auth>
    <td>${comment.content}</td>
    <td>${comment.answer}</td>
    <td style="width:80px">${comment.addtime}</td>
    <td style="width:60px">${comment.postip}</td>
    <td style="width:30px">
    <c:choose>
    	<c:when test="${comment.status==1}">已读</c:when>
    	<c:when test="${comment.status==0}">未读</c:when>
    	<c:otherwise>其他</c:otherwise>
    </c:choose>
    </td>
    <td style="width:40px">
     <a href="javascript:openWindows('${comment.id}')" title="回复"><img alt="回复" src="${root}/img/grid_edit.png"></a>
     <auth:auth ifAnyGranted="F_OPERATOR">
        <a href='${root}/admin/deleteUserComment.do?id=${comment.id}' onclick="return confirmDelete();" title="删除"><img alt="删除" src="${root}/img/grid_delete.png"></a>
     </auth:auth>
     </td>
    </tr>
  </c:forEach>
</tbody>
</table>
</c:if>
</form>
	<div>
	<logic:present name="toolBar">
		<bean:write name="toolBar" filter="false"/>
	</logic:present>
	</div>
</body>
</html:html>

