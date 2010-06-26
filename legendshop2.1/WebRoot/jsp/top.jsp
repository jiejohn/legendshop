<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<jsp:directive.page import="com.done.util.UserManagement"/>
<%@include file='/common/jsp/common.jsp'%>
<%@include file='/common/jsp/taglib.jsp'%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	request.setAttribute("ServerName",request.getScheme()+"://"+request.getServerName());
	if(UserManagement.getUsername()!=null)
	   session.setAttribute("userName",UserManagement.getUsername());

%>
<head>
    <base href="<%=basePath%>" />
 <script src="${root}/common/js/jquery.js" type="text/javascript"></script>
  <c:choose>
   <c:when test="${userName == null}">
      <script src="${root}/common/js/jquery.tools.min.js" type="text/javascript"></script>
      <link rel="stylesheet" type="text/css" media='screen' href="${root}/common/css/overlay-minimal.css" />
   </c:when>
   <c:otherwise>
     <link rel="stylesheet" href="${root}/common/css/jquery.superbox.css" type="text/css" media="all" />
	 <script type="text/javascript" src="${root}/common/js/jquery.superbox-min.js"></script>
   </c:otherwise>
</c:choose>
		<script type='text/javascript' src='${root}/dwr/interface/CommonService.js'></script>
  		<script type='text/javascript' src='${root}/dwr/engine.js'></script>
		<script type='text/javascript' src='${root}/dwr/util.js'></script>

<script>
  function openbag(id) {
    window.open("${root}/basket.do?hwId="+id,"","height=420,width=530,left=190,top=10,resizable=yes,scrollbars=yes,status=no,toolbar=no,menubar=no,location=no");
  } 
  function openScript(url, width, height){
	var Win = window.open(url,"openScript",'width=' + width + ',height=' + height + ',resizable=1,scrollbars=yes,menubar=no,status=yes' );
	}
  function bookmark(title, url) {
  if (document.all)
    window.external.AddFavorite(url, title);
  else if (window.sidebar)
    window.sidebar.addPanel(title, url, "")
}

  function form1_onsubmit() {
      var words = document.getElementById("headerkeywordp");
      if( words.value == null || words.value == '' ){
      	alert('<bean:message key="errors.keyword.required"/>');
      	return false;
      }
      return true;
  }
  
  		function searchPager(curPageNO){
			document.getElementById("curPageNOTop").value=curPageNO;
			document.getElementById("searchForm").submit();
		}
	jQuery(function() {
	var username = '${userName}';
	if( username == null || username == '' ){
    jQuery("a[rel]").overlay({
        expose: '#ECECEC',
        effect: 'apple',
        onBeforeLoad: function() {
            var wrap = this.getContent().find(".contentWrap");
            wrap.load(this.getTrigger().attr("href"));
        }
    });

    }else{
                jQuery.superbox.settings = {
                closeTxt: "<bean:message key="close"/>",
                loadTxt: "Loading...",
                nextTxt: "Next",
                prevTxt: "Previous",
                overlayOpacity: 0.7
            };
            jQuery.superbox();
            }
});

	function changeLocale(country,language){
		var serverName = '${ServerName}';
		var location = window.location.href;
		var page = "";
		var pos = location.lastIndexOf("page");

		if(pos > -1){
			page = location.substring(pos+5);
			}else{
				page = location.substring(serverName.length);
			}
		var pos1 = page.indexOf(";");
		if(pos1>-1){
			page = page.substring(0,pos1);
			}
		
		//window.location.href = "${root}/localeAction.do?country="+country+"&language="+language+"&page="+page;
		window.location.href = "${root}/localeAction.do?country="+country+"&language="+language;
		}

	//增加店铺联盟
	function addMyLeague(userName,shopName) {
		  if(confirm('<bean:message key="addLeague"/> '+shopName+' ?')){
	        CommonService.addMyLeague(userName,shopName,'${shopDetail.sitename}', function(retData){
		       switch(retData){
		       case 0:
		    	   alert('<bean:message key="addLeagueSuccess"/>') ;
		    	   break;
		       default:
		    	   alert('<bean:message key="addLeagueError"/>') ;	    	   	    	   		    	   	       
		       }	       
		    }) ;
	    }
	}
</script>

    <link href="${root}/common/style/style_${shopDetail.colorStyle}.css" rel="stylesheet" type="text/css" />
    <link href="${root}/common/style/global_${shopDetail.colorStyle}.css" rel="stylesheet" type="text/css" />
<title>
<c:choose>
   <c:when test="${shopDetail != null}">
     ${shopDetail.sitename}
   </c:when>
   <c:otherwise>
  	 LegendShop
   </c:otherwise>
</c:choose>
</title>
<link rel="icon" href="${root}/favicon.ico" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="${shopDetail.storeName},${shopDetail.briefDesc}" />
<meta name="keywords" content="${shopDetail.storeName},${shopDetail.briefDesc}"/>
<meta name="keywords" content="123done"/>
<meta name="keywords" content="shop"/>
<meta name="keywords" content="legend"/>
<meta name="keywords" content="LegendShop"/>
<meta name="keywords" content="B2B,B2C"/>
<meta name="keywords" content="网上商城"/>
<meta name="keywords" content="商城"/>
<meta name="keywords" content="电子商务"/>
<meta name="keywords" content="社区"/>
<meta name="keywords" content="连锁店"/>
</head>
<body id="master">
<div id="header">
	<div id="logo">
			<a href="${logo.url}">
			<c:choose>
			 <c:when test="${not empty logo }">
			     <img style="Clear: Both; Border: 0px" src="${PHOTO_PATH}/${logo.banner}" height="65px" title="${logo.memo}" />
			 </c:when>
			 <c:otherwise>
			     <img src="${root}/img/logo.png" width="180px" height="65px" title="123done"/>
			 </c:otherwise>
			</c:choose>
		  </a>
	</div>
	<div>
	   <ul id="topnews">
	   <c:forEach items="${offLineNewsList}" var="offLineNews">
	       <li><a href="${root}/news/${offLineNews.newsId}">${offLineNews.newsTitle}</a></li>
	   </c:forEach>
    </ul>
    </div>
	<div>
	<ul id="topnav">
	<c:if test="${not empty userName}">
	   <c:if test="${shopExists}">
               <li><a href="${root}/shop/${userName}"><bean:message key="myShop"/></a></li>
        </c:if>
	   <auth:auth ifAnyGranted="F_ADMIN">
	     <li>    
            <a href="${root}/member/index.jsp"><bean:message key="system.management"/></a>
         </li>
       </auth:auth>
		<li><a href="${root}/order.do"><bean:message key="myorder"/></a></li>
		<li><a href="${root}/jsp/cash.jsp" rel="superbox[iframe][525x420]"><bean:message key="cashBar"/></a></li>
	</c:if>
	<li class="navCartSum">
		<c:choose>
		   <c:when test="${userName == null}">
		      <a href="${root}/buy.do"><bean:message key="shopingCar"/></a>
		   </c:when>
		    <c:otherwise>
		        <a href="${root}/buy.do" rel="superbox[iframe][525x420]"><bean:message key="shopingCar"/></a>
		    </c:otherwise>
     </c:choose>
			<!-- external page is given in the href attribute (as it should be) -->
	
	<!-- overlayed element -->
	<div class="overlay" id="overlay">
    <!-- the external content is loaded inside this tag -->
        <div class="contentWrap"></div>
    </div>
    
		</li>	
		<li><a href="${root}/leaveword.do"><bean:message key="leaveword"/></a></li>
		<li class="hl"><a href="${root}/allNews.do"><bean:message key="newsCenter"/></a></li>
	</ul>
	</div>
 <div id="headerlogin">
	<span>
 <c:choose>
   <c:when test="${userName != null}">
   <%-- 
  <bean:message key="hello"/>
  --%>
  <span>${userName}</span>
   	<a href="${root}/myaccount.do")>[<bean:message key="myaccount"/>]</a>
	<a href="${root}/logout.jsp" target="_parent">[<bean:message key="logout"/>]</a>
   </c:when>
   <c:otherwise>
      <%-- 
  	<bean:message key="hello"/>
  	--%>
  	<a href="${root}/login.do">[<bean:message key="login"/>]</a>
   </c:otherwise>
</c:choose>
<a href="${root}/reg.do" class="n2">[<bean:message key="regFree"/>]</a>
</span>
	</div>
</div>
<div id="headernav">
<form action="" method="post">
	<ul>
	<!--  
		<li id="zone_us"><a href="${root}/reg.do" class="n2">免费注册</a></li>
		-->
        <li class="n2"><a href="javascript:changeLocale('CN','zh')"><bean:message key="home"/></a></li>
        <li class="n2"><a href="javascript:changeLocale('US','en')">English</a></li>
        <c:if test="${leagueShopExists}">
                       <li class="n2"><a href="${root}/league.do"><bean:message key="leagueShop"/></a></li>
        </c:if>
	</ul>
</form>
	<ul class="nright">
	<c:forEach items="${requestScope.sortList}" var="sort" end="8">     
	 	<li><a href="${root}/sort/${sort.sortId}">${sort.sortName}</a></li>
	</c:forEach> 
	</ul>
</div>
<div id="searchwrapper">
	<div id="qchannel">
	<div id="headersearch">
			<form method="post" onsubmit="return form1_onsubmit();" action="${root}/search.do" class="c" id="searchForm" name="searchForm">
				<fieldset>
				<input type="text" value="${searchForm.keyword}" name="keyword" id="headerkeywordp"/>
				<input type="hidden" id="curPageNOTop" name="curPageNOTop" value="${searchForm.curPageNOTop}"/>
				<select name="sortId" id="headersearchcategory">
					<option id="allProduct" value="0"><bean:message key="all.product"/></option>
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
				<input type="submit" value="<bean:message key="search"/>" class="button2" />
				</fieldset>
			</form>
			<div id="hsearch">
			<c:if test="${requestScope.searchList!=null}">
				<c:forEach items="${requestScope.searchList}" var="search">
					<a href="${search.msg}">${search.title}</a>
			     </c:forEach>
			</c:if>		
			</div>
		</div>
		<div id="topcatalog">
		 
			<h2>
			    <a href='#' onclick="javascript:bookmark('${shopDetail.storeName}','${DOMAIN_NAME}/shop/${shopDetail.storeName}');">
                    <bean:message key="favorite"/>
                </a>
			</h2>
            <!--
            <ul>
                <li><a href="http://localhost/123done" target="_self">中文</a></li>
                <li><hr/></li>
                <li><a href="http://localhost/123done" target="_self">English</a></li>
            </ul>
             --> 
	  </div>
	  <div>
		<ul id="favourite">
			<li>
			<c:choose>
				<c:when test="${!canbeLeagueShop}">
					<center><a href="${root}/shop/${sessionScope.shopName}">${sessionScope.shopName}</a></center>
				</c:when>
				<c:otherwise>
					<center><a href='javascript:addMyLeague("${userName}","${shopName}")'><bean:message key="addLeague"/>&nbsp;${sessionScope.shopName}</a></center>
				</c:otherwise>
			</c:choose>
                
			</li>
		</ul>
		</div>
	</div>
</div>

