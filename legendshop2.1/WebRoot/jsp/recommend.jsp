<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
    session.setAttribute("root", request.getContextPath());
%>
<%@include file='/common/jsp/taglib.jsp'%>
<div class="recommond">
	<div style="font-weight: bold; margin-bottom: 10px; margin-top: 10px">
	    <center>
		  <font color="white" size="3"> <bean:message key="product.recommend" /></font>
		</center>
	</div>

	<table width="950px" cellpadding="0" cellspacing="10">
		<tr>
			<c:forEach items="${requestScope.hwList}" var="hw" varStatus="status">
				<%-- 
	style="position: relative;border:#989DA5 1px dotted;"
	--%>
				<td width="20%" align="center">
					<c:choose>
						<c:when test="${fn:length(hw.hwName) > 30}">
						<span>
							<a href="${root}/views/${hw.hwId}/${hw.sortId}" ><img
									src="${SMALL_PHOTO_PATH}/${hw.hwPic}" width="165px" height="120px"
									title="${hw.hwName}" id="hwpic"></a></span><br>${fn:substring(hw.hwName,0,30)}...<br>
									
						</c:when>
						<c:otherwise>
						<span >
							<a href="${root}/views/${hw.hwId}/${hw.sortId}"><img
									src="${SMALL_PHOTO_PATH}/${hw.hwPic}" width="165px" height="120px"
									 id="hwpic"></a></span><br>${hw.hwName}<br>
									
						</c:otherwise>
					</c:choose>
					<%-- 
											<bean:message key="hw_price" />:
																<s><fmt:formatNumber type="currency"
																		value="${hw.hwPrice}" /> </s>
																<br>
															--%>
					<c:if test="${not empty hw.hwCash}">
						<bean:message key="hw_cash" /> <font color="red"><fmt:formatNumber
								type="currency" value="${hw.hwCash}" /></font>
						<c:if test="${empty userName}">
							<a href="${root}/basket.do?hwId=${hw.hwId}" rel="#overlay"><img
									border="0" src="${root}/img/order.gif" align="middle" title="<bean:message key='order'/>"></a>
						</c:if>
						<c:if test="${not empty userName}">
							<a href="${root}/basket.do?hwId=${hw.hwId}"
								rel="superbox[iframe][525x420]"><img border="0"
									src="${root}/img/order.gif" align="middle" title="<bean:message key='order'/>"></a>
						</c:if>
					</c:if>
				</td>
				<c:if test="${(status.index+1)%5==0&&(status.index+1)>=5}">
		</tr>
		<tr>
			</c:if>
			</c:forEach>
		</tr>
	</table>
</div>

