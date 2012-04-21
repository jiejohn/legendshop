<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/pages/common/common.jsp'%>
<%@include file='/pages/common/taglib.jsp'%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ taglib uri="/WEB-INF/tld/options.tld" prefix="option"%>
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
	    <lb:shopDetail var="shopDetail" />
        <script type='text/javascript' src="<ls:templateResource item='/dwr/interface/CommonService.js'/>"></script>
        <script type='text/javascript' src="<ls:templateResource item='/dwr/interface/optionService.js'/>"></script>
  		<script type='text/javascript' src="<ls:templateResource item='/dwr/engine.js'/>"></script>
		<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/util.js'></script>
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/common/js/linked-select.js"></script>
  <script src="${pageContext.request.contextPath}/common/js/jquery.js" type="text/javascript"></script>
  <script>
		function changeCity(provinceid) {
  		DWREngine.setAsync(false);
		if(provinceid!=null &&provinceid!=''){
				var sql ="select cityid id, city name from ls_cities where provinceid = '"+provinceid+"'";
		        changeLinkedOptions1("cityid", false, sql,"-- 城市 --");
	        }
	        DWREngine.setAsync(true); 
        }
        
    function changeAreas(cityid) {
        DWREngine.setAsync(false);
        if(cityid!=null && cityid!=''){
                var sql ="select areaid id, area name from ls_areas where cityid = '"+cityid+"'";
                changeLinkedOptions1("areaid", false, sql,"-- 地区 --");
            }
            DWREngine.setAsync(true); 
        }
        
     function initSelect(provinceidValue,cityidValue,areasidValue){
			DWRUtil.setValues({provinceid:provinceidValue});
			changeCity(provinceidValue);
			DWRUtil.setValues({cityid:cityidValue});
			changeAreas(cityidValue);
			DWRUtil.setValues({areaid:areasidValue});
		}
		
	jQuery(document).ready(function() {
	   //jQuery("b").css("color","red");
	  // jQuery("#apple img[rel]").overlay({effect: 'apple'}); 
	   initSelect('${provinceid}','${cityid}','${areaid}');
	});
		
		
  </script>
  <!--[if lt IE 7]>
    <link rel="stylesheet" type="text/css" media='screen' href="${pageContext.request.contextPath}/common/css/overlay-ie6.css" />
<![endif]-->
<style type="text/css">
a:visited {
	text-decoration: underline
}

a:hover {
	text-decoration: underline
}

a:active {
	text-decoration: underline
}

a:link {
	text-decoration: underline
}

BODY {
	PADDING-RIGHT: 0px;
	PADDING-LEFT: 0px;
	BACKGROUND: #ffffff;
	PADDING-BOTTOM: 0px;
	MARGIN: 0px auto;
	PADDING-TOP: 0px;
}
</style>
		<title>LegendShop - Java企业级多用户商城系统</title>
		<link rel="icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="description"
			content="${shopDetail.storeName},${shopDetail.briefDesc}" />
		<meta name="keywords"
			content="${shopDetail.storeName},${shopDetail.briefDesc}" />
		<meta name="keywords"
			content="shop,legend,LegendShop,B2B,B2C,网上商城,商城,电子商务,连锁店" />
	</head>
	<body>
		<center>
			<table width="98%">
				<tr>
					<td width="140px" style="BORDER-RIGHT: #999999 1px solid;vertical-align: top;" align="left">
					<c:choose>
						<c:when test="${entityType == 0}">
						<div style="font-weight: bold;"><fmt:message key="search.by.price"/></div><br>
						<div><fmt:message key="from.hint"/><input type="text" id="priceStart" name="priceStart" value="${priceStart}" maxlength="8" size="8"/> ￥</div><br>
						<div><fmt:message key="to.hint"/><input type="text" id="priceEnd" name="priceEnd" maxlength="8" value="${priceEnd}" size="8"/> ￥</div>
							</c:when>
						<c:when test="${entityType == 1}">
							   			<div style="font-weight: bold;"><fmt:message key="search.shop.by.address"/></div><br>
			<select id="provinceid" name="provinceid" onChange="changeCity(this.value)">
	            <option:optionGroup type="select" required="false" cache="fasle"
	                defaultDisp="-- 省份 --" 
	                sql="select t.provinceid, t.province from ls_provinces t"/>
			</select>&nbsp;<br/><br/>
			<select id="cityid" name="cityid" onChange="changeAreas(this.value)">
				<option value="">-- 城市 --</option>
			</select>&nbsp;<br/><br/>
			 <select id="areaid" name="areaid">
	            <option value="">-- 地区 --</option><br/>
	        </select>
						</c:when>
						<c:otherwise></c:otherwise>
					</c:choose>

						
					</td>
					<td align="left" valign="top">
						<c:choose>
							<c:when test="${requestScope.searchResult != null && entityType == 0}">
							<div style="margin-left: 20px;font-size: 12pt" align="left">
				
				<c:forEach items="${requestScope.searchResult}" var="record" varStatus="status">
					<table cellpadding="2">
						<tr>
							<td>
                              <img src="${pageContext.request.contextPath}/photoserver/images/${record.pic}" width="120px" height="100px"  style="margin-right: 3px"/>				
							</td>
							<td style="vertical-align: top"><div>
							<div style="margin-top: 8px;font-size: 13pt">
								 <a href="${pageContext.request.contextPath}/views/${record.prodId}${applicationScope.WEB_SUFFIX}" target="_blank">${record.name}</a>
								
							</div>
							<div>${record.content}</div>
							<div>
							<c:if test="${record.price != null}">
								<fmt:message key="prod_price"/>  <s><fmt:formatNumber type="currency" value="${record.price}" pattern="${CURRENCY_PATTERN}"/></s>
							</c:if>
							<c:if test="${record.cash != null}">
								<fmt:message key="prod_cash"/>
								<span style="font-weight: bold;"><fmt:formatNumber type="currency" value="${record.cash}" pattern="${CURRENCY_PATTERN}"/></span>
							</c:if>
							</div>
							<div><fmt:message key="product.sort"/> - <a href="${pageContext.request.contextPath}/sort/${record.sortId}${applicationScope.WEB_SUFFIX}" target="_blank">${record.sortName}</a> 
							<a href="${pageContext.request.contextPath}/nsort/${record.sortId}-${record.nsortId}${applicationScope.WEB_SUFFIX}" target="_blank">${record.nsortName}</a> 
							<a href="${pageContext.request.contextPath}/nsort/${record.sortId}-${record.nsortId}${applicationScope.WEB_SUFFIX}?subNsortId=${record.subNsortId}" target="_blank">${record.subNsortName}</a>
							</div>
							<c:if test="${'C2C' == applicationScope.BUSINESS_MODE}">
							<div style="color: green">
							<fmt:message key="shop.name"/> - <a href="${pageContext.request.contextPath}/shop/${record.userName }${applicationScope.WEB_SUFFIX}" style="color: green" target="_blank">${record.userName}</a>
							</div>
							</c:if>
							</div></td>
						</tr>
					</table>
					</c:forEach>
					<c:if test="${toolBar!=null}">
			<p align="right">
				<c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
			</p>
			</c:if>
			
					</div>	
							</c:when>
							<c:when test="${requestScope.searchResult != null && entityType == 1}">
<!-- shop -->
<div style="margin-left: 20px;font-size: 12pt" align="left">
				
				<c:forEach items="${requestScope.searchResult}" var="record" varStatus="status">
					<table cellpadding="2">
						<tr>
							<td style="vertical-align: top" align="left">
							<div>
							<div style="margin-top: 8px;font-size: 13pt">
							  <a href="${pageContext.request.contextPath}/shop/${record.storeName}${applicationScope.WEB_SUFFIX}" target="_blank">${record.sitename}</a>
								
							</div>
							<div>${record.detailDesc}</div>
							<div><fmt:message key="shop.contact.method"/> - <fmt:message key="Phone"/>：
								<span style="color: green">${record.userTel}</span> &nbsp;  
								QQ:<span style="color: green">${record.qq}</span> &nbsp; 
								MSN:<span style="color: green"> ${record.msnNumber}</span>
							</div>
							<div>
								<c:if test="${record.province != null}">
								<fmt:message key="address"/> -  ${record.province}
								 <c:if test="${record.city != null}">&raquo; ${record.city}
								 	<c:if test="${record.area != null}">&raquo; ${record.area}</c:if>
								 </c:if>
								</c:if>
							</div>
							<c:if test="${record.ymaddr != null}">
								<div><fmt:message key="detail.address"/> - ${record.ymaddr}</div>
							</c:if>
							
							
							</div></td>
						</tr>

					</table>
					</c:forEach>
					<c:if test="${toolBar!=null}">
			<p align="right">
				<c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
			</p>
			</c:if>
			
					</div>
							</c:when>
							<c:otherwise>
			<div style="font-size: 13pt;margin-left: 20px">
				<fmt:message key="can.not.found"><fmt:param value="${keyword}"/></fmt:message>
			</div>
							</c:otherwise>
						</c:choose>

					</td>
					<td width="255px" valign="top" align="left">
					<c:if test="${sessionScope.VisitHistory.visitedShop != null && fn:length(sessionScope.VisitHistory.visitedShop) > 0 && 'C2C' == applicationScope.BUSINESS_MODE}">
					<table width="100%" style="margin-bottom: 4px;margin-right: 5px;" class="tables" cellpadding="0" cellspacing="0">
					    <tr><td class="titlebg"><fmt:message key="visited.mall"/></td></tr>
						<tr><td align="left">
							  <c:forEach items="${sessionScope.VisitHistory.visitedShop}" var="visitedShop" varStatus="status">
							  <div style="margin: 2px;"><a href="${pageContext.request.contextPath}/shop/${visitedShop.name}${applicationScope.WEB_SUFFIX}" title="${visitedShop.title}" target="_blank">${visitedShop.name}</a></div>
							  </c:forEach>
						</td></tr>
					</table>
					</c:if>
					<br/>
					<c:if test="${sessionScope.VisitHistory.visitedProd != null && fn:length(sessionScope.VisitHistory.visitedProd) > 0}">
					<table  width="100%" style="margin-bottom: 4px;margin-right: 5px;" class="tables" cellpadding="0" cellspacing="0">
					    <tr><td class="titlebg"><fmt:message key="visited.product"/></td></tr>
						<tr><td align="left">
							  <c:forEach items="${sessionScope.VisitHistory.visitedProd}" var="visitProd" varStatus="status">
							  <div style="margin: 2px;"><a href="${pageContext.request.contextPath}/views/${visitProd.id}${applicationScope.WEB_SUFFIX}" title="${visitProd.title}" target="_blank">${visitProd.name}</a></div>
							  </c:forEach>
						</td></tr>
						<tr><td></td></tr>
					</table>
					</c:if>
					</td>
				</tr>
			</table>
		</center>