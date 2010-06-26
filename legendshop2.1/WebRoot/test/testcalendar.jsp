<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    session.setAttribute("root", request.getContextPath());
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <script type='text/javascript' src='${root}/common/calendar.js'></script>  
    <script src="${root}/common/js/jquery.js" type="text/javascript"></script>  
    <title>My JSP 'testcalendar.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <script type="text/javascript">
     $(function() {
       // $("#datepicker").get(0).onclick=function(evt){ calendar.show(this,"yyyy-MM-dd",evt); };
        document.getElementById("datepicker").onclick=function(evt){ calendar.show(this,"yyyy-MM-dd",evt); };
        document.getElementById("datepicker1").onclick=function(evt){ calendar.show(this,"yyyy-MM-dd hh:mm:ss",evt); };
        document.getElementById("datepicker2").onclick=function(evt){ calendar.show(this,"yyyy-MM-dd hh:00:00",evt); };
    });
    </script>
  </head>
  
  <body>
    <input type="text" id="datepicker" name="datepicker" size="25"  > <br>
    <input id="datepicker1" name="datepicker1" type="text"> <br>
    <input id="datepicker2" name="datepicker2" type="text"> <br>
  </body>
</html>
