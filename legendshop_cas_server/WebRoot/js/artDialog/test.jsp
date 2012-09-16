<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<title>test</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/artDialog/skins/blue.css"/>
</head>

<body id="cas">
	<div id="header">
		<h1 id="app-name">Test dialog2222</h1>
	</div>

	<div id="content">
		<div>
			<input type="button" value="test" onclick="testDialog();" id="testBtn"/>
		</div>
	</div>
	<div id="footer">
		<div>
			<p>Copyright &copy; 广州新软计算机技术有限公司 &nbsp;All rights reserved.</p>
		</div>
	</div>
</body>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/artDialog/jquery.artDialog.js"></script>
<script type="text/javascript">
<!--
	function testDialog() {
		$.dialog({
			title:"提示",
			content : "这是一个测试弹出窗口数据!",
			icon: "succeed",		    
		    lock:false,
		    follow: document.getElementById('testBtn'),
		    ok: function(){}
		});
	}
//-->
</script>
</html>
