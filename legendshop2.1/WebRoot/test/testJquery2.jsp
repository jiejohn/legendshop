<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common.jsp"%>
<%@ include file="/common/jsp/taglib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'testJquery.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" media='screen' href="${root}/common/css/overlay.css" />
	<script type="text/javascript" src="${root}/common/js/jquery.js"></script>
	<script src="${root}/common/js/jquery.tools.min.js"></script>


<script type="text/javascript">
$(function() { 
    $("#apple img[rel]").overlay({effect: 'apple'}); 
});
</script>
  </head>
  
  <body>

        
<!--[if lt IE 7]>
<style> 
div.apple_overlay {
    background-image:url('${root}/img/overlay_IE6.gif');
    color:#fff;
}

/* default close button positioned on upper right corner */
div.apple_overlay div.close {
    background-image:url('${root}/img/overlay_close_IE6.gif');

}   
</style>
<![endif]-->

<!-- trigger elements -->
<div id="apple">
    <img src="${root}/img/gustavohouse.jpg" rel="#photo1"/>
    <img src="${root}/img/alexanderplatz-station.jpg" rel="#photo2"/>
</div>

<!-- overlayed element, which is styled with external stylesheet -->
<div class="apple_overlay black" id="photo1">
    <img src="${root}/img/gustavohouse-medium.jpg" />

    <div class="details">
        <h2>Berlin Gustavohouse</h2>

        <p>
            The Gustavo House in Storkower Strasse. It was built in 1978 and reconstructed in
            1998 by the Spanish artist Gustavo.
        </p>

    </div>
</div>

<div class="apple_overlay" id="photo2">
    <img src="${root}/img/alexanderplatz-station-medium.jpg" />

    <div class="details">
        <h2>Berlin Alexanderplatz Station</h2>

        <p>
            Berlin Alexanderplatz is a railway station in the Berlin city centre and is one
            of the city's most important interchange points for local public transport.
        </p>

    </div>



  </body>
</html>
