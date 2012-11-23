<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<lb:shopDetail var="shopDetail" />
	<div id="logo">
			<a href="/">
			<c:choose>
			       <c:when test="${shopDetail.logoPic == null}">
                            <img style="Clear: Both; Border: 0px" src="/common/images/legendshop.gif" height="65px" />
                     </c:when>
                     <c:otherwise>
                              <img style="Clear: Both; Border: 0px" src="<ls:photo item='${shopDetail.logoPic}'/>" height="65px" />
                    </c:otherwise>
             </c:choose>
            </a>
	</div>
	<div>
	   <ul id="topnews">
	   <c:forEach items="${newsTopList}" var="newsTop">
	       <li><a href="<ls:url address='/news/${newsTop.newsId}'/>">${newsTop.newsTitle}</a></li>
	   </c:forEach>
	   <c:if test="${'userChoice' eq shopDetail.langStyle}">
        <li class="hl"><a href="<ls:url address='/changeLocale'/>?country=CN&language=zh">中文</a></li>
        <li class="hl"><a href="<ls:url address='/changeLocale'/>?country=US&language=en">English</a></li>
        </c:if>
    </ul>
    </div>
	<div>
	<ul id="topnav">
	<lb:logined >
	   <ls:myshop>
               <li><a href="<ls:domain shopName='${sessionScope.SPRING_SECURITY_LAST_USERNAME}' />"><ls:i18n key="myShop"/></a></li>
        </ls:myshop>
	   <auth:auth ifAnyGranted="F_ADMIN">
	   	<c:if test="${canbeLeagueShop}">
	   		<li><a href='javascript:addMyLeague("${sessionScope.SPRING_SECURITY_LAST_USERNAME}","<lb:currentShop />")'><ls:i18n key="addLeague"/><ls:i18n key="this.shop"/></a></li>
	   	</c:if>
	     <li><a href="<ls:url address='/admin/index'/>"><b><ls:i18n key="system.management"/></b></a></li>
       </auth:auth>
      	<li><a href="<ls:url address='/leaveword'/>"><ls:i18n key="leaveword"/></a></li>
		<li><a href="<ls:url address='/p/order'/>"><ls:i18n key="myorder"/></a></li>
	</lb:logined>
		<li class="navCartSum"><a href="<ls:url address='/p/buy'/>"><ls:i18n key="shopingCar"/></a></li>	
		<li class="hl"><a href="<ls:url address='/allNews'/>"><ls:i18n key="newsCenter"/></a></li>
	</ul>
	</div>
 <div id="headerlogin">
	<span>
 <c:choose>
   <c:when test="${sessionScope.SPRING_SECURITY_LAST_USERNAME != null}">
  <span><b>${sessionScope.SPRING_SECURITY_LAST_USERNAME}</b></span>
   	<a href="<ls:url address='/p/myaccount'/>")>[<ls:i18n key="myaccount"/>]</a>
	<a href="${pageContext.request.contextPath}/logout" target="_parent">[<ls:i18n key="logout"/>]</a>
   </c:when>
   <c:otherwise>
  	<a href="<ls:url address='/login'/>">[<ls:i18n key="login"/>]</a>
   </c:otherwise>
</c:choose>
<a href="<ls:url address='/reg'/>" class="n2">[<ls:i18n key="regFree"/>]</a>
</span>
	</div>