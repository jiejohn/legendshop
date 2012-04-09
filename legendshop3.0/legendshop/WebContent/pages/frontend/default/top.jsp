<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/pages/common/common.jsp'%>
<%@include file='/pages/common/taglib.jsp'%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
  <script src="<ls:templateResource item='/common/js/top.js'/>" type="text/javascript"></script>
  <c:choose>
   <c:when test="${sessionScope.SPRING_SECURITY_LAST_USERNAME == null}">
      <script src="<ls:templateResource item='/common/js/jquery.tools.min.js'/>" type="text/javascript"></script>
      <link rel="stylesheet" type="text/css" media='screen' href="<ls:templateResource item='/common/css/overlay-minimal.css'/>" />
   </c:when>
   <c:otherwise>
     <link rel="stylesheet" href="<ls:templateResource item='/common/css/jquery.superbox.css'/>" type="text/css" media="all" />
	 <script type="text/javascript" src="<ls:templateResource item='/common/js/jquery.superbox-min.js'/>"></script>
   </c:otherwise>
</c:choose>
    <script type='text/javascript' src="<ls:templateResource item='/dwr/interface/CommonService.js'/>"></script>
    <script type='text/javascript' src="<ls:templateResource item='/dwr/engine.js'/>"></script>
    <script type='text/javascript' src="<ls:templateResource item='/dwr/util.js'/>"></script>
<style type="text/css">
        body {behavior:url("<ls:templateResource item='/common/css/csshover.htc'/>"); }
</style>
<script>
 	 function form1_onsubmit() {
      var words = document.getElementById("headerkeywordp");
      if( words.value == null || words.value == '' || words.value == '关键字'){
      	alert('<fmt:message key="errors.keyword.required"/>');
      	words.focus();
      	return false;
      }
      return true;
 	 }

	function addMyLeague(userName,shopName) {
		  if(confirm('<fmt:message key="addLeague"/> '+shopName+' ?')){
	        CommonService.addMyLeague(userName,shopName,'${shopDetail.sitename}', function(retData){
		       switch(retData){
		       case 0:
		    	   alert('<fmt:message key="addLeagueSuccess"/>') ;
		    	   break;
		       default:
		    	   alert('<fmt:message key="addLeagueError"/>') ;	    	   	    	   		    	   	       
		       }	       
		    }) ;
	    }
	}
	
</script>
<lb:shopDetail var="shopDetail" >
    <link href="<ls:templateResource item='/common/style/style_${shopDetail.colorStyle}.css'/>" rel="stylesheet" type="text/css" />
    <link href="<ls:templateResource item='/common/style/global_${shopDetail.colorStyle}.css'/>" rel="stylesheet" type="text/css" />
</lb:shopDetail>
<title>${shopDetail.sitename}</title>
<link rel="icon" href="<ls:templateResource item='/favicon.ico'/>" type="image/x-icon" />
<meta name="description" content="${shopDetail.storeName},${shopDetail.briefDesc}" />
<meta name="keywords" content="${shopDetail.storeName},${shopDetail.briefDesc}"/>
<meta name="keywords" content="LegendShop 网购平台, 网店系统, 商城系统, 商城系统, 电子商务系统, B2C系统, 购物系统, 网上商店系统, 网上交易系统, JAVA网店, JAVA商城, JSP网店, JSP商城, SSH项目, JAVA开源项目"/>
</head>
<!--  id="master" -->
<body>
 <%@ include file="header.jsp"%>
<div id="header">
	<div id="logo">
			<a href="${logo.url}">
			<c:choose>
			 <c:when test="${logo.id != -1}">
			     <img style="Clear: Both; Border: 0px" src="${pageContext.request.contextPath}/photoserver/photo/${logo.banner}" height="65px" title="${logo.memo}" />
			 </c:when>
			 <c:otherwise>
			     <img src="<ls:templateResource item='/img/legendshop.gif'/>" height="65px" title="LegendShop"/>
			 </c:otherwise>
			</c:choose>
		  </a>
	</div>
	<div>
	   <ul id="topnews">
	   <c:forEach items="${newsTopList}" var="newsTop">
	       <li><a href="<ls:url address='/news/${newsTop.newsId}'/>">${newsTop.newsTitle}</a></li>
	   </c:forEach>
	   <c:if test="${'userChoice' eq sessionScope.shopDetail.langStyle}">
        <li class="hl"><a href="<ls:url address='/changeLocale'/>?country=CN&language=zh">中文</a></li>
        <li class="hl"><a href="<ls:url address='/changeLocale'/>?country=US&language=en">English</a></li>
        </c:if>
    </ul>
    </div>
	<div>
	<ul id="topnav">
	<c:if test="${sessionScope.SPRING_SECURITY_LAST_USERNAME != null}">
	   <c:if test="${shopExists && 'C2C' == applicationScope.BUSINESS_MODE}">
               <li><a href="<ls:url address='/shop/${sessionScope.SPRING_SECURITY_LAST_USERNAME}'/>"><fmt:message key="myShop"/></a></li>
        </c:if>
	   <auth:auth ifAnyGranted="F_ADMIN">
	   	<c:if test="${canbeLeagueShop}"><li><a href='javascript:addMyLeague("${sessionScope.SPRING_SECURITY_LAST_USERNAME}","${sessionScope.shopName}")'><fmt:message key="addLeague"/><fmt:message key="this.shop"/></a></li></c:if>
	     <li><a href="<ls:url address='/admin/index'/>"><b><fmt:message key="system.management"/></b></a></li>
       </auth:auth>
      	<li><a href="<ls:url address='/leaveword'/>"><fmt:message key="leaveword"/></a></li>
		<li><a href="<ls:url address='/order'/>"><fmt:message key="myorder"/></a></li>
	</c:if>
		<li class="navCartSum"><a href="<ls:url address='/buy'/>"><fmt:message key="shopingCar"/></a></li>	
		<li class="hl"><a href="<ls:url address='/allNews'/>"><fmt:message key="newsCenter"/></a></li>
	</ul>
	</div>
 <div id="headerlogin">
	<span>
 <c:choose>
   <c:when test="${sessionScope.SPRING_SECURITY_LAST_USERNAME != null}">
  <span><b>${sessionScope.SPRING_SECURITY_LAST_USERNAME}</b></span>
   	<a href="<ls:url address='/myaccount'/>")>[<fmt:message key="myaccount"/>]</a>
	<a href="${pageContext.request.contextPath}/logout.jsp" target="_parent">[<fmt:message key="logout"/>]</a>
   </c:when>
   <c:otherwise>
  	<a href="<ls:url address='/login'/>">[<fmt:message key="login"/>]</a>
   </c:otherwise>
</c:choose>
<a href="<ls:url address='/reg'/>" class="n2">[<fmt:message key="regFree"/>]</a>
</span>
	</div>
</div>
<div id="headernav">
	<ul>
		<li class="n2"><a href="<ls:url address='/index'/>"><fmt:message key="shop.index"/></a></li>
		<c:if test="${'C2C' == applicationScope.BUSINESS_MODE }">
        <li class="n2"><a href="<ls:url address='/league'/>"><fmt:message key="leagueShop"/></a></li>
        </c:if>
        <c:forEach items="${newsSortList}" var="newsSort">
	       <li><a href="<ls:url address='/news/${newsSort.newsId}'/>">${newsSort.newsTitle}</a></li>
	   </c:forEach>
	</ul>
	<ul class="nright">
	<c:forEach items="${requestScope.sortList}" var="sort" end="8">    
	 	<li><a href="<ls:url address='/sort/${sort.sortId}'/>">${sort.sortName}</a></li>
	</c:forEach> 
	</ul>
</div>
<form method="post" onsubmit="return form1_onsubmit();" action="<ls:url address='/search'/>" id="searchForm" name="searchForm" style="margin: 0px;padding: 0px">
<div id="searchwrapper">
	<div id="qchannel">
	<div id="headersearch">
				<input type="hidden" id="curPageNOTop" name="curPageNOTop" value="${searchForm.curPageNOTop}"/>
				<select name="sortId" id="headersearchcategory" style="margin-right: 3px">
					<option id="allProduct" value="0"><fmt:message key="all.product"/></option>
					<c:forEach items="${requestScope.sortList}" var="sort">
						<c:choose>
						   <c:when test="${CurrentSortId == sort.sortId}">
						   	<option id="${sort.sortId}" value="${sort.sortId}" class="c" selected="selected">${sort.sortName}</option>
						   </c:when>
						   <c:otherwise>
						  	<option id="${sort.sortId}" value="${sort.sortId}" class="c">${sort.sortName}</option>
						   </c:otherwise>
						</c:choose>	
				    </c:forEach>
				</select>
					<input type="text" value="${searchForm.keyword}" name="keyword" id="headerkeywordp"  class="input2" onMouseOver="this.focus()" onBlur="if (this.value =='') this.value='关键字'" onFocus="this.select()" onClick="if (this.value=='关键字') this.value=''"/>
			 		<input type="submit" value='<fmt:message key="search"/>' class="input3" onmouseover="this.style.background='#99CC00'" onmouseout="this.style.background='#FFCC00'"/> 
					<a href="javascript:advanceSearch('${pageContext.request.contextPath}')" style="margin: 6px"><font color="white"><fmt:message key="advance.search"/></font></a>
		
		</div>
		<div id="topcatalog">
			<h2>
			    <a href='#' onclick="javascript:bookmark('${shopDetail.storeName} - ${shopDetail.sitename}','${DOMAIN_NAME}/shop/${shopDetail.storeName}${applicationScope.WEB_SUFFIX}');">
                    <fmt:message key="favorite"/>
                </a>
			</h2>
	  </div>
	  <div>
		<ul id="favourite">
			<li><center><a href="<ls:url address='/shopcontact'/>?shop=${sessionScope.shopName}" title="${sessionScope.shopName}<fmt:message key='online.customer'/>"><fmt:message key="online.customer"/></a></center></li>
		</ul>
		</div>
	</div>
</div>
</form>