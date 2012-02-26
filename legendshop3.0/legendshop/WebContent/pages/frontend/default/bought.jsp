<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/pages/common/taglib.jsp'%>
    <link href="${pageContext.request.contextPath}/common/style/style_${shopDetail.colorStyle}.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/common/style/global_${shopDetail.colorStyle}.css" rel="stylesheet" type="text/css" />
		<table class="tablesnoborder" width="100%" cellpadding="0" cellspacing="0">
			<tr style="font-weight: bold">
			  <c:if test="${'C2C' == applicationScope.BUSINESS_MODE}">
				<td width="10%">
					<div align="center">
						<fmt:message key="shop.name" />
					</div>
				</td>
				</c:if>
				<td width="42%" height="25">
					<div align="center">
						<fmt:message key="product.name" />
					</div>
				</td>
				<td width="12%">
					<div align="center">
						<fmt:message key="product.price" />
					</div>
				</td>
				<td width="12%">
					<div align="center">
						<fmt:message key="product.attribute" />
					</div>
				</td>
				<td width="8%">
					<div align="center">
						<fmt:message key="product.number" />
					</div>
				</td>
				<td width="150px">
					<div align="center">
						<fmt:message key="price.total" />
					</div>
				</td>
				<td width="100px">
					<div align="center">
						<fmt:message key="operation"/>
					</div>
				</td>
			</tr>
			<c:forEach items="${requestScope.baskets}" var="basket">
				<tr>
				  <c:if test="${'C2C' == applicationScope.BUSINESS_MODE}">
					<td height="25">
						<div align="center">
							<a href="${pageContext.request.contextPath}/shop/${basket.shopName}">${basket.shopName}</a>
						</div>
					</td>
				</c:if>
					<td>
						<a target="_blank" href="${pageContext.request.contextPath}/views/${basket.prodId}${applicationScope.WEB_SUFFIX}"><font
							color="#FF0000">${basket.prodName}</font>
						</a>
					</td>
					<td height="25">
						<div align="center">
							<fmt:formatNumber type="currency" value="${basket.cash}" pattern="${CURRENCY_PATTERN}"/>
						</div>
					</td>
					<td height="25">
						<div align="center">
							${basket.attribute}
						</div>
					</td>
					<td width="65" height="25">
						<div align="center">${basket.basketCount}</div>
					</td>
					<td height="25">
						<div align="center">
							<fmt:formatNumber type="currency" value="${basket.total}" pattern="${CURRENCY_PATTERN}"/>
							<c:if test="${basket.carriage != null && basket.carriage > 0}">
                  (<fmt:message key="carriage.charge"/><fmt:formatNumber type="currency"
									value="${basket.carriage}" pattern="${CURRENCY_PATTERN}"/>)</c:if>
						</div>
					</td>
					<td>
						<a href="${pageContext.request.contextPath}/clear${applicationScope.WEB_SUFFIX}?basketId=${basket.basketId}"
							title='<fmt:message key="product.name.delete"/>'><fmt:message key="delete"/></a>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${requestScope.totalcash != null}">
				<tr>
					<td height="25" colspan="8">
						<div align="right" style="margin-right: 86px;">
							<b><fmt:message key="price.total" />
								<fmt:formatNumber type="currency"
									value="${requestScope.totalcash}" pattern="${CURRENCY_PATTERN}"/>
							</b>
						</div>
					</td>
				</tr>
			</c:if>
			<c:if
				test="${requestScope.baskets != null && fn:length(requestScope.baskets) > 0}">
				<tr>
					<td colspan="8">
						<div style="margin: 3px">
							<a href="${pageContext.request.contextPath}/buy${applicationScope.WEB_SUFFIX}" onMouseOver="btnMouseOver('mbtn0')"
								onMouseOut="btnMouseOut('mbtn0')"
								onMouseDown="btnMouseDown('mbtn0')"> <img name="mbtn0" />
							</a>
							<a href="${pageContext.request.contextPath}/clear${applicationScope.WEB_SUFFIX}" onMouseOver="btnMouseOver('mbtn1')"
								onMouseOut="btnMouseOut('mbtn1')"
								onMouseDown="btnMouseDown('mbtn1')"> <img name="mbtn1" />
							</a>
							<a href="${pageContext.request.contextPath}/cash${applicationScope.WEB_SUFFIX}" onMouseOver="btnMouseOver('mbtn2')"
								onMouseOut="btnMouseOut('mbtn2')"
								onMouseDown="btnMouseDown('mbtn2')"> <img name="mbtn2" />
							</a>
						</div>
					</td>
				</tr>

				<script type="text/javascript">
<!--
var btnCount = 3;
var staCount = 3;
var btnImages = new Array();
for (i= 0; i< btnCount; i++)
{
    btnImages[i] = new Array();
    for (j= 0; j< staCount; j++)
    {
        btnImages[i][j] = new Image();
        btnImages[i][j].src = '${pageContext.request.contextPath}<fmt:message key="img.btn"/>' + i + '_' + j + '.gif';
    }

}

    document.images['mbtn0'].src = '${pageContext.request.contextPath}<fmt:message key="img.btn"/>' + '0_0.gif';
    document.images['mbtn1'].src = '${pageContext.request.contextPath}<fmt:message key="img.btn"/>' + '1_0.gif';
    document.images['mbtn2'].src = '${pageContext.request.contextPath}<fmt:message key="img.btn"/>' + '2_0.gif';
    
function btnMouseOut(img)
{
    document.images[img].src = btnImages[img.substring(img.indexOf('mbtn')+4,img.length)][0].src;
};
function btnMouseOver(img)
{
    document.images[img].src = btnImages[img.substring(img.indexOf('mbtn')+4,img.length)][1].src;
};
function btnMouseDown(img)
{
    document.images[img].src = btnImages[img.substring(img.indexOf('mbtn')+4,img.length)][2].src;
};
//-->
</script>
			</c:if>
		</table>
  
  