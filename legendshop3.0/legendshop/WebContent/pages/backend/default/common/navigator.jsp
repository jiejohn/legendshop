     <%@ page contentType="text/html;charset=UTF-8" language="java"%>
        <ul id="mainNav">
            <li><a href="${pageContext.request.contextPath}/admin/index${applicationScope.WEB_SUFFIX}">首页</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/order/query${applicationScope.WEB_SUFFIX}">订单管理</a></li>
        	<li><a href="${pageContext.request.contextPath}/admin/product/query${applicationScope.WEB_SUFFIX}" class="active">商品管理</a></li> <!-- Use the "active" class for the active menu item  -->
        	<li><a href="${pageContext.request.contextPath}/admin/shopDetail/query${applicationScope.WEB_SUFFIX}">商城管理</a></li>
        	<li><a href="${pageContext.request.contextPath}/">用户管理</a></li>
        	<li><a href="${pageContext.request.contextPath}/">系统管理</a></li>
        	<li class="logout"><a href="#">LOGOUT</a></li>
        </ul>