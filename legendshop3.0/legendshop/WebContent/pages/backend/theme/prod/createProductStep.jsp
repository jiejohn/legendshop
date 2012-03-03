<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/common.jsp"%>
<%@ include file="/pages/common/taglib.jsp"%>
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/common/js/common.js"></script>
  <script language="javascript">
	function appendProduct(id){
	    var win = openWindow("${pageContext.request.contextPath}/admin/product/append/"+id + "${applicationScope.WEB_SUFFIX}", "appendBrand",'500','450') ;
	    win.focus() ;
	}

</script>
      <c:choose>
      	<c:when test="${prod.prodId == null}">
      		商品详细信息
      	</c:when>
      	<c:otherwise>
      	  <a href="${pageContext.request.contextPath}/admin/product/update/${prod.prodId}${applicationScope.WEB_SUFFIX}">
      	  <c:choose>
      	  	<c:when test="${param.step == 1}"><b>1.商品详细信息</b></c:when>
      	  	<c:otherwise>1.商品详细信息</c:otherwise>
      	  </c:choose>
      	 </a> 
	      <a href="${pageContext.request.contextPath}/admin/imgFile/query${applicationScope.WEB_SUFFIX}?productId=${prod.prodId}">
	           	  <c:choose>
		      	  	<c:when test="${param.step == 2}"><b>2.商品图片</b></c:when>
		      	  	<c:otherwise>2.商品图片</c:otherwise>
		      	  </c:choose> 
	      
	      </a>  
	      <a href="${pageContext.request.contextPath}/admin/dynamic/loadAttribute/${prod.prodId}${applicationScope.WEB_SUFFIX}">
	     		  <c:choose>
		      	  	<c:when test="${param.step == 3}">
			      	  	<c:choose>
							<c:when test="${DYNAMIC_TYPE == 1}"><b>3.动态属性</b></c:when>
							<c:otherwise>3.动态属性</c:otherwise>
						</c:choose>
		      	  	</c:when>
		      	  	<c:otherwise>3.动态属性</c:otherwise>
		      	  </c:choose>  
	      </a> 
	      <a href="${pageContext.request.contextPath}/admin/dynamic/loadParameter/${prod.prodId}${applicationScope.WEB_SUFFIX}">
	     	    <c:choose>
		      	  	<c:when test="${param.step == 3}">
			      	  	<c:choose>
						<c:when test="${DYNAMIC_TYPE == 2}"><b>4.动态参数</b></c:when>
						<c:otherwise>4.动态参数</c:otherwise>
						</c:choose>
					</c:when>
		      	  	<c:otherwise>4.动态参数</c:otherwise>
		      	</c:choose>   
	      
	      </a> 
	      <a href="javascript:appendProduct(${prod.prodId})">
	      	    <c:choose>
		      	  	<c:when test="${param.step == 5}"><b>5.相关商品</b></c:when>
		      	  	<c:otherwise>5.相关商品</c:otherwise>
		      	  </c:choose>  
	      </a>

      	</c:otherwise>
      </c:choose>