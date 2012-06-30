<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/pages/common/taglib.jsp'%>
<!--[if lt IE 7]>
    <link rel="stylesheet" type="text/css" media='screen' href="${pageContext.request.contextPath}/common/css/default/overlay-ie6.css" />
<![endif]-->
<div class="recommond">
		<fmt:message key="product.recommend" />
	<table width="100%" cellpadding="0" cellspacing="10">
		<tr>
			<c:forEach items="${requestScope.productList}" var="prod" varStatus="status">
				<td width="20%" align="center" style="font-weight: normal;color: #666666">
						<div>
							<a href="${pageContext.request.contextPath}/views/${prod.prodId}${applicationScope.WEB_SUFFIX}" ><img
									src="${pageContext.request.contextPath}/photoserver/images/${prod.pic}" width="165px" height="120px"
									title="${prod.name}" id="pic"></a></div>
						<div class="topnewsfixed" title="${prod.name}">${prod.name}</div>
					<c:if test="${not empty prod.cash}">
						<fmt:message key="prod_cash" /> <font color="red"><fmt:formatNumber type="currency" value="${prod.cash}"  pattern="${CURRENCY_PATTERN}"/></font>

					</c:if>
				</td>
				<c:if test="${(status.index+1)%5==0&&(status.index+1)>=5}"></tr><tr></c:if>
			</c:forEach>
		</tr>
	</table>
</div>

