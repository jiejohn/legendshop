<%@ page contentType="text/html;charset=UTF-8" language="java"%>
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
</div>  