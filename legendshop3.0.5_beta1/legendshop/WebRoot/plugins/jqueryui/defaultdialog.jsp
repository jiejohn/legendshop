<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<link rel="stylesheet" href="<ls:templateResource item='/plugins/jqueryui/css/jquery.ui.all.css'/>">
     <script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
<script src="<ls:templateResource item='/plugins/jqueryui/js/jquery.ui.core.js'/>"  type="text/javascript"></script>
<script src="<ls:templateResource item='/plugins/jqueryui/js/jquery.ui.widget.js'/>"  type="text/javascript"></script>
<script src="<ls:templateResource item='/plugins/jqueryui/js/jquery.ui.dialog.js'/>"  type="text/javascript"></script>

<script src="<ls:templateResource item='/plugins/jqueryui/js/jquery.ui.draggable.js'/>"  type="text/javascript"></script>
<script src="<ls:templateResource item='/plugins/jqueryui/js/jquery.ui.mouse.js'/>"  type="text/javascript"></script>
<script src="<ls:templateResource item='/plugins/jqueryui/js/jquery.ui.position.js'/>"  type="text/javascript"></script>
<script src="<ls:templateResource item='/plugins/jqueryui/js/jquery.ui.resizable.js'/>"  type="text/javascript"></script>
<link rel="stylesheet" href="<ls:templateResource item='/plugins/jqueryui/css/demos.css'/>">
<html>
<head>
	<title>jQuery UI Dialog - Default functionality</title>

	<script>
	$(function() {
	  $( "#dialog" ).dialog();
	  //$( "#dialog" ).html(<iframe src="http://www.sohu.com"  width="100%" height="100%"></iframe>');
	  
	});
	</script>
</head>
<body>

<div class="demo">

<div id="dialog" title="Basic dialog">
	<p>This is the default dialog which is useful for displaying information. The dialog window can be moved, resized and closed with the 'x' icon.</p>
</div>
</div><!-- End demo -->


</body>
</html>
