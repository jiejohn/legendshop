<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="com.legendshop.core.helper.PropertiesUtil"%>

<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/top.js"></script>
<script type=text/javascript>
$(document).ready(function() {

	$(".menu").mouseover(function(){
		var _this = $(this);
		 _this.addClass("on");
	});

	$(".menu").mouseout(function(){
		var _this = $(this);
		 _this.removeClass("on");
	});
});



</script>
<!--顶部menu-->
<div id="shortcut">
	<div class="w">
		<ul class="fl lh">
		  <li  class="fore1"><a href="<ls:url address='/index'/>">&lt;&lt;返回商城</a></li>	
		  <li>
		  
				<fmt:message key="nows.location" />
				<c:if test="${shopDetail.province != null}">
				  		 ${shopDetail.province}/${shopDetail.city}/${shopDetail.area}/
				  </c:if>
				<b><a href="${pageContext.request.contextPath}/shopcontact${applicationScope.WEB_SUFFIX}">${sessionScope.shopName}</a>
				</b>
		  </li>	
		  
			<c:if test="${'C2C' == applicationScope.BUSINESS_MODE}">
				<li>
					<a href="${pageContext.request.contextPath}/reg${applicationScope.WEB_SUFFIX}?openshop=1" target="_blank"><fmt:message
							key="register.shop" />
					</a>
				</li>
			</c:if>
			<li class="favorite">
				<a href='#'
					onclick="javascript:bookmark('<fmt:message key="welcome.to.legendshop"/><%=PropertiesUtil.getDefaultShopName()%> - LegendShop购物商城','${DOMAIN_NAME}/shop/<%=PropertiesUtil.getDefaultShopName()%>${applicationScope.WEB_SUFFIX}');">
					<fmt:message key="shop.favorite" />
				</a>
			</li>
		  	
		</ul>
		<ul class="fr lh">
 <c:choose>
   <c:when test="${sessionScope.SPRING_SECURITY_LAST_USERNAME != null}">
   
			<li id="loginbar"  class="ld">${sessionScope.SPRING_SECURITY_LAST_USERNAME}			
			<span>
			<a href="<ls:url address='/p/myaccount'/>")>[<fmt:message key="myaccount"/>]</a> 
			<a href="${pageContext.request.contextPath}/p/logout" target="_parent">[<fmt:message key="logout"/>]</a>
			</span></li>
   
   </c:when>
   <c:otherwise>
			<li id="loginbar"  class="ld">欢迎来到LengedShop！
			<span><a href="<ls:url address='/p/login'/>">[<fmt:message key="login"/>]</a> 
			<a class="link-regist" href="<ls:url address='/reg'/>">[<fmt:message key="regFree"/>]</a></span></li>
   </c:otherwise>
</c:choose>

			
			<c:if test="${sessionScope.SPRING_SECURITY_LAST_USERNAME != null}">
			<li ><a href="<ls:url address='/leaveword'/>"><fmt:message key="leaveword"/></a></li>
			<li ><a href="<ls:url address='/p/order'/>">我的订单</a></li>
	        </c:if>
	        <li><a href="<ls:url address='/allNews'/>"><fmt:message key="newsCenter"/></a></li>
<%-- 	        <li class="menu">
				<dl>
					<dt class="ld">常见问题<b></b></dt>
					<dd>
					   <c:forEach items="${newsTopList}" var="newsTop">
					       <div><a href="<ls:url address='/news/${newsTop.newsId}'/>">${newsTop.newsTitle}</a></div>
					   </c:forEach>
					</dd>
				</dl>
			</li> --%>
		  
	  </ul>
		<span class="clr"></span>
	</div>
</div>
<!--顶部menu end-->
