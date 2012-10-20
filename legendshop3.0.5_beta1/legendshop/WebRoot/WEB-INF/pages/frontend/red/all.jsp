<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/common.jsp'%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
<%@ taglib uri="/WEB-INF/tld/options.tld" prefix="option"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>
<lb:shopDetail var="shopDetail" />
<c:choose>
	<c:when test="${shopDetail != null}">
		${shopDetail.siteName}
	</c:when>
	<c:otherwise>
		LegendShop - Java企业级商城系统
	</c:otherwise>
</c:choose>
</title>
<link type="text/css" href="<ls:templateResource item='/common/red/css/legend.css'/>" rel="stylesheet"/>
</head>
<body>
<!--顶部menu-->
<div id="shortcut">
	<div class="w">
		<ul class="fl lh">
		  <li  class="fore1"><a href="${pageContext.request.contextPath}/index"><fmt:message key="shop.index"/></a></li>
		  <c:choose>
					 <c:when test="${sessionScope.SPRING_SECURITY_LAST_USERNAME != null}">
					   	<li>
					   	<span style="color: red; font-weight: bold;">${sessionScope.SPRING_SECURITY_LAST_USERNAME}</span>&nbsp;
					   	<a href="${pageContext.request.contextPath}/p/usercenter"><fmt:message key="myaccount"/></a></li>
					   	<li><a href="${pageContext.request.contextPath}/p/order"><fmt:message key="myorder"/></a></li>
						<li><a href="${pageContext.request.contextPath}/p/logout" target="_parent"><fmt:message key="logout"/></a></li>
       			        <auth:auth ifAnyGranted="F_ADMIN">
       			          <c:if test="${'C2C' == applicationScope.BUSINESS_MODE}">
       			               <li> <a href="<ls:domain shopName='${sessionScope.SPRING_SECURITY_LAST_USERNAME}' />"><fmt:message key="myShop"/></a></li>
       			           </c:if>
					            <a href="<ls:url address='/admin/index'/>"><fmt:message key="system.management"/></a>
					    </auth:auth>
					   </c:when>
					   <c:otherwise>
					     	<li><a href="${pageContext.request.contextPath}/p/login"><fmt:message key="login"/></a></li>
				   </c:otherwise>
	    		</c:choose>
	    		<li><a href="${pageContext.request.contextPath}/reg"><fmt:message key="register.title"/></a> </li>
	    		<c:if test="${'C2C' == applicationScope.BUSINESS_MODE}">
	    			<li><a href="${pageContext.request.contextPath}/reg?openshop=1"><fmt:message key="register.shop"/></a></li>
	    		</c:if>		
		</ul>
		
		<span class="clr"></span>
	</div>
</div>
<!--顶部menu end-->

<!--logo+search--> 
<div class="w sspage">
  
      <p class="ss_logo"><img src="<ls:templateResource item='/common/red/images/logo.gif'/>"  /></p>
      
      <div class="ss_area">
        <div class="search">
          <div class="sleft"><select><option>商品</option><option>商场</option></select></div>
          <input type="text"/>
        </div>
         <p class="searchpic"><a href="#"><img src="<ls:templateResource item='/common/red/images/search.gif'/>"  /></a></p>
         <div class="clear"></div>
         <p class="hotword"><a href="#">所有商品</a>|<a href="#">所有店铺</a>|<a href="#">所有商城</a></p>
      </div>
      
      <div class="clear"></div>
</div>
<!--logo+search-->

<!----foot---->

<div class="w footline footnav" style="text-align:center;">
<a href="#">关于我们</a>|<a href="#">联系我们</a>|<a href="#">人才招聘</a>|<a href="#">商家入驻</a>|<a href="#">广告服务</a>|<a href="#">移动终端</a>|<a href="#">友情链接</a>|<a href="#">销售联盟</a>|<a href="#">legendshop论坛</a><br /><br />
<a href="#">北京市公安局朝阳分局备案编号110105014669</a>|<a href="#">京ICP证070359号</a>|<a href="#">互联网药品信息服务资格证编号(京)-非经营性-2011-0034</a><br />
<a href="#">音像制品经营许可证苏宿批005号</a>|<a href="#">出版物经营许可证编号新出发(苏)批字第N-012号</a>|<a href="#">互联网出版许可证编号新出网证(京)字150号</a><br />
Copyright©2004-2012  legendshop 版权所有

</div>

<!----foot end---->
 
</body>
</html>
