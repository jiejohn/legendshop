<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'testJquery.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="${root}/common/js/jquery.js"></script>
<script type="text/javascript" src="${root}/test/tools.tabs-1.0.4.js"></script>

<script src="http://cdn.jquerytools.org/1.0.2/jquery.tools.min.js"></script>
<link rel="stylesheet" type="text/css" media='screen' href="${root}/test/test.css" />
<script type="text/javascript">
$(function() { 
    // 将ul.tabs区域设定为选项卡，用来控制div.panes区域中最近一层的各div区域
   // 注意tabs和panes与html中class的对应关系
    $("ul.tabs").tabs("div.panes > div",{history: true}); 
});

//-->
</script>
  </head>
  
  <body>
   <ul class="tabs"> 
    <li><a href="#first">选项卡1</a></li> 
    <li><a href="#second">选项卡2</a></li> 
    <li><a href="#third">选项卡3</a></li> 
</ul> 
<!-- 定义选项卡切换的每个区域，注意最外层的class定义为panes --> 
<div class="panes"> 
    <div>第一个选项卡内容，<a href="#second">跳转至选项卡2</a></div> 
    <div>第二个选项卡内容，<a href="#third">跳转至选项卡3</a></div> 
    <div>第三个选项卡内容，<a href="#first">跳转至选项卡1</a></div> 
</div>
  </body>
</html>
