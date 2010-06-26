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

<script src="${root}/test/jquery.tools.min.js"></script>
<link rel="stylesheet" type="text/css" media='screen' href="${root}/test/test.css" />

<style>  
/* overlay元素*/ 
div.overlay {
 
    /* overlay背景图 */ 
    background-image:url(/123done/test/white.png); 
 
    /* overlay的最终尺寸，可以由此改变overlay大小  */ 
    width:600px; 
    height:470px;         
 
    /* overlay默认隐藏 */ 
    display:none; 
 
    /* 设定内嵌元素padding，获得好的视觉效果  */ 
    padding:55px; 
} 
 
/* 关闭按钮样式， 放置在右上角 */ 
div.overlay div.close { 
    background-image:url(/123done/test/close.png); 
    position:absolute; 
    right:5px; 
    top:5px; 
    cursor:pointer; 
    height:35px; 
    width:35px; 
}

div.apple_overlay { 
    background-image:url(http://static.flowplayer.org/tools/img/overlay/overlay_IE6.gif); 
    color:#fff; 
} 
 
/* default close button positioned on upper right corner */ 
div.apple_overlay div.close { 
    background-image:url(http://static.flowplayer.org/tools/img/overlay/overlay_close_IE6.gif); 
 
}  
</style> 



<script type="text/javascript">
$(function() { 
   //为页面中所有拥有rel属性的button元素绑定overlay效果
    $("button[rel]").overlay(
   {effect: 'apple'}
    ); 
       
});



</script>
  </head>
  
  <body>
<button type="button" rel="#overlay">Open overlay</button>
<%@ include file="includejsp.jsp"%>

  </body>
</html>
