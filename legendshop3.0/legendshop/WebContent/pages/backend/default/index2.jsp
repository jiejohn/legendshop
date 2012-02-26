<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/pages/common/back-common.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>LegendShop后台管理</title>

<!-- CSS -->
<link href="style/css/transdmin.css" rel="stylesheet" type="text/css" media="screen" />
<!--[if IE 6]><link rel="stylesheet" type="text/css" media="screen" href="style/css/ie6.css" /><![endif]-->
<!--[if IE 7]><link rel="stylesheet" type="text/css" media="screen" href="style/css/ie7.css" /><![endif]-->

<!-- JavaScripts-->
<script type="text/javascript" src="style/js/jquery.js"></script>
<script type="text/javascript" src="style/js/jNice.js"></script>
</head>

<body>
	<div id="wrapper">
    	<!-- h1 tag stays for the logo, you can use the a tag for linking the index page -->
    	<h1><a href="#"><span>Transdmin Light</span></a></h1>
        <!-- You can name the links with lowercase, they will be transformed to uppercase by CSS, we prefered to name them with uppercase to have the same effect with disabled stylesheet -->
        <ul id="mainNav">
            <li><a href="${pageContext.request.contextPath}/admin/index${applicationScope.WEB_SUFFIX}">首页</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/order/query${applicationScope.WEB_SUFFIX}">订单管理</a></li>
        	<li><a href="${pageContext.request.contextPath}/admin/product/query${applicationScope.WEB_SUFFIX}" class="active">商品管理</a></li> <!-- Use the "active" class for the active menu item  -->
        	<li><a href="${pageContext.request.contextPath}/admin/shopDetail/query${applicationScope.WEB_SUFFIX}">商城管理</a></li>
        	<li><a href="${pageContext.request.contextPath}/">用户管理</a></li>
        	<li><a href="${pageContext.request.contextPath}/">系统管理</a></li>
        	<li class="logout"><a href="#">LOGOUT</a></li>
        </ul>
        <!-- // #end mainNav -->
        
        <div id="containerHolder">
			<div id="container">
        		<div id="sidebar">
                	<ul class="sideNav">
                    	<li><a href="${pageContext.request.contextPath}/admin/product/query${applicationScope.WEB_SUFFIX}" class="active">商品列表</a></li>
                    	<li><a href="${pageContext.request.contextPath}/admin/sort/query${applicationScope.WEB_SUFFIX}" >类型管理</a></li>
                    	<li><a href="${pageContext.request.contextPath}/admin/brand/query${applicationScope.WEB_SUFFIX}">品牌管理</a></li>
                    	<li><a href="${pageContext.request.contextPath}/admin/userComment/query${applicationScope.WEB_SUFFIX}?status=0">消息管理</a></li>
                    	<li><a href="${pageContext.request.contextPath}/admin/indexjpg/query${applicationScope.WEB_SUFFIX}">图片管理</a></li>
                    	<li><a href="${pageContext.request.contextPath}/admin/hotsearch/query${applicationScope.WEB_SUFFIX}">热门商品</a></li>
                    	<li><a href="${pageContext.request.contextPath}/admin/productcomment/query${applicationScope.WEB_SUFFIX}">评论管理</a></li>
                    </ul>
                    <!-- // .sideNav -->
                </div>    
                <!-- // #sidebar -->
                <div id="mainframe">
                <!-- h2 stays for breadcrumbs -->
                <div id="title">
                <h2><a href="#">商品管理</a> &raquo; <a href="#" class="active">商品列表</a></h2>
                </div>
                <div id="main">
                	<form action="" class="jNice">
					<h3>Sample section</h3>
                    	<table cellpadding="0" cellspacing="0">
							<tr>
                                <td>Vivamus rutrum nibh in felis tristique vulputate</td>
                                <td class="action"><a href="#" class="view">View</a><a href="#" class="edit">Edit</a><a href="#" class="delete">Delete</a></td>
                            </tr>                        
							<tr class="odd">
                                <td>Duis adipiscing lorem iaculis nunc</td>
                                <td class="action"><a href="#" class="view">View</a><a href="#" class="edit">Edit</a><a href="#" class="delete">Delete</a></td>
                            </tr>                        
							<tr>
                                <td>Donec sit amet nisi ac magna varius tempus</td>
                                <td class="action"><a href="#" class="view">View</a><a href="#" class="edit">Edit</a><a href="#" class="delete">Delete</a></td>
                            </tr>                        
							<tr class="odd">
                                <td>Duis ultricies laoreet felis</td>
                                <td class="action"><a href="#" class="view">View</a><a href="#" class="edit">Edit</a><a href="#" class="delete">Delete</a></td>
                            </tr>                        
							<tr>
                                <td>Vivamus rutrum nibh in felis tristique vulputate</td>
                                <td class="action"><a href="#" class="view">View</a><a href="#" class="edit">Edit</a><a href="#" class="delete">Delete</a></td>
                            </tr>                        
                        </table>
					<h3>Another section</h3>
                    	<fieldset>
                        	<p><label>Sample label:</label><input type="text" class="text-long" /></p>
                        	<p><label>Sample label:</label><input type="text" class="text-medium" /><input type="text" class="text-small" /><input type="text" class="text-small" /></p>
                            <p><label>Sample label:</label>
                            <select>
                            	<option>Select one</option>
                            	<option>Select two</option>
                            	<option>Select tree</option>
                            	<option>Select one</option>
                            	<option>Select two</option>
                            	<option>Select tree</option>
                            </select>
                            </p>
                        	<p><label>Sample label:</label><textarea rows="1" cols="1"></textarea></p>
                            <input type="submit" value="Submit Query" />
                        </fieldset>
                    </form>
                </div>
                <!-- // #main -->
                
                <div class="clear"></div>
            </div>
            </div>
            <!-- // #container -->
        </div>	
        <!-- // #containerHolder -->
        
        <p id="footer">Power by. <a href="http://www.legendesign.net">LegendShop 3.0.3.2</a></p>
    </div>
    <!-- // #wrapper -->
</body>
</html>
