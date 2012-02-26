<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/pages/backend/default/common/taglib2.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><tiles:insertAttribute name="title"  ignore="true" /></title>

<!-- CSS -->
<link href="${root }/pages/backend/default/style/css/transdmin.css" rel="stylesheet" type="text/css" media="screen" />
<!--[if IE 6]><link rel="stylesheet" type="text/css" media="screen" href="${root }/pages/backend/default/style/css/ie6.css" /><![endif]-->
<!--[if IE 7]><link rel="stylesheet" type="text/css" media="screen" href="${root }/pages/backend/default/style/css/ie7.css" /><![endif]-->

<!-- JavaScripts-->
<script type="text/javascript" src="${root }/pages/backend/default/style/js/jquery.js"></script>
<script type="text/javascript" src="${root }/pages/backend/default/style/js/jNice.js"></script>
</head>

<body>
	<div id="wrapper">
    	<!-- h1 tag stays for the logo, you can use the a tag for linking the index page -->
    	<h1><a href="#"><span>LegendShop</span></a></h1>
         <tiles:insertAttribute name="navigator" ignore="true" />
        <div id="containerHolder">
			<div id="container">
        		<tiles:insertAttribute name="left" ignore="true" />
                <tiles:insertAttribute name="container" ignore="true" />
            </div>
</div>
        <tiles:insertAttribute name="bottom" ignore="true" />
    </div>
</body>
</html>
