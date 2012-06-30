<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<table width="205" cellspacing="0" cellpadding="0" style="margin-bottom: 5px;margin-left: 5px;">

	<tr>
		<td class="titlebg"><fmt:message key="hotsale" /></td>
	</tr>

	<tr align="center" height="105">
		<td width="100%">
			<table width="100%">
				<c:if test="${productList != null}">
					<c:forEach items="${requestScope.hotsaleList}" var="hotsale" varStatus="status">
						<tr>
							<td>
								<a href="${pageContext.request.contextPath}/views/${hotsale.prodId}${applicationScope.WEB_SUFFIX}"> 
								<img src="${pageContext.request.contextPath}/common/images/default/number/num_${status.index+1}.gif">
								</a>
							</td>
							<td align="left">
											<font color="#006699"><a
												href="${pageContext.request.contextPath}/views/${hotsale.prodId}${applicationScope.WEB_SUFFIX}" target="_blank">
													${hotsale.name} </a>
											</font>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</td>
	</tr>
</table>
