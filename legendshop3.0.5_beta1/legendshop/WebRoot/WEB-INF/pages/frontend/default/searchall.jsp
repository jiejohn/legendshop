<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/common.jsp'%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ taglib uri="/WEB-INF/tld/options.tld" prefix="option"%>
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
	    <lb:shopDetail var="shopDetail" />
<script src="<ls:templateResource item='/common/js/linked-select.js'/>" type="text/javascript"></script>
  <script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
  <script>
		function changeCity(provinceid) {
  		dwr.engine.setAsync(false);
		if(provinceid!=null &&provinceid!=''){
				var sql ="select cityid id, city name from ls_cities where provinceid = '"+provinceid+"'";
		        changeLinkedOptions1("cityid", false, sql,"-- 城市 --");
	        }
	        dwr.engine.setAsync(true); 
        }
        
    function changeAreas(cityid) {
        dwr.engine.setAsync(false);
        if(cityid!=null && cityid!=''){
                var sql ="select areaid id, area name from ls_areas where cityid = '"+cityid+"'";
                changeLinkedOptions1("areaid", false, sql,"-- 地区 --");
            }
            dwr.engine.setAsync(true); 
        }
        
     function initSelect(provinceidValue,cityidValue,areasidValue){
			dwr.util.setValues({provinceid:provinceidValue});
			changeCity(provinceidValue);
			dwr.util.setValues({cityid:cityidValue});
			changeAreas(cityidValue);
			dwr.util.setValues({areaid:areasidValue});
		}
		
	jQuery(document).ready(function() {
	   //jQuery("b").css("color","red");
	  // jQuery("#apple img[rel]").overlay({effect: 'apple'}); 
	   initSelect('${provinceid}','${cityid}','${areaid}');
	});
		
		
  </script>
  <!--[if lt IE 7]>
    <link rel="stylesheet" type="text/css" media='screen' href="${pageContext.request.contextPath}/common/default/css/overlay-ie6.css" />
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
		<link rel="icon" href="${pageContext.request.contextPath}/common/favicon.ico" type="image/x-icon" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="description"
			content="${shopDetail.userName},${shopDetail.briefDesc}" />
		<meta name="keywords"
			content="${shopDetail.userName},${shopDetail.briefDesc}" />
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
                              <img src="<ls:images item='${record.pic}'/>" width="120px" height="100px"  style="margin-right: 3px"/>				
							</td>
							<td style="vertical-align: top"><div>
							<div style="margin-top: 8px;font-size: 13pt">
								 <a href="${pageContext.request.contextPath}/views/${record.prodId}" target="_blank">${record.name}</a>
								
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
							<div><fmt:message key="product.sort"/> - <a href="${pageContext.request.contextPath}/sort/${record.sortId}" target="_blank">${record.sortName}</a> 
							<a href="${pageContext.request.contextPath}/nsort/${record.sortId}-${record.nsortId}" target="_blank">${record.nsortName}</a> 
							<a href="${pageContext.request.contextPath}/nsort/${record.sortId}-${record.nsortId}?subNsortId=${record.subNsortId}" target="_blank">${record.subNsortName}</a>
							</div>
							<c:if test="${'C2C' == applicationScope.BUSINESS_MODE}">
							<div style="color: green">
							<fmt:message key="shop.name"/> - <a href="<ls:domain shopName='${record.userName}' />"  style="color: green" target="_blank">${record.userName}</a>
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
							  <a href="<ls:domain shopName='${record.userName}' />"  target="_blank">${record.siteName}</a>
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
							<c:if test="${record.postAddr != null}">
								<div><fmt:message key="detail.address"/> - ${record.postAddr}</div>
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
					<td width="255px" valign="top" align="left"><jsp:include flush="true" page="/visitedShop" /></td>
				</tr>
			</table>
		</center>