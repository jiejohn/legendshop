<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'includejsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <!-- 定义一个按钮作为触发器. 这里通过rel属性，指定遮罩层元素的id --> 

   <!-- 遮罩元素，你可以通过样式表定义样式 --> 
<!-- 遮罩元素可以使任何html元素，一般我们用div，注意id的设定 --> 
<div class="overlay" id="overlay"> 
    <!--这里是遮罩层里面要放的东西 --> 
    <h2 style="margin:10px 0">Here is my overlay</h2>  
    <p style="float: left; margin:0px 20px 0 0;"> 
        <img src="/123done/test/eye192.png" /> 
    </p>  
    <p> 
         Class aptent taciti sociosqu ad litora torquent per conubia nostra, 
         per inceptos himenaeos. Donec lorem ligula, elementum vitae, 
         imperdiet a, posuere nec, ante. Quisque mattis massa id metus. 
    </p> 
</div>
  </body>
</html>
