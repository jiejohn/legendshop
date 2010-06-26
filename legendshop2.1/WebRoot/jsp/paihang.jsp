<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/common/jsp/taglib.jsp'%>
<table width="205" border="0" cellspacing="0" cellpadding="0" style="margin-bottom: 5px;margin-left: 5px;">

	<tr>
		<td align="center" height="28" class="headerbg">
			<font color="white"><bean:message key="paihang" /></font>
		</td>
	</tr>

	<tr align="center" height="105">
		<td width="100%">
			<table width="100%" border="0">
				<c:if test="${hwList != null}">
					<c:forEach items="${requestScope.paihangList}" var="paihang" varStatus="status">
						<tr>
							<td>
								<a href="${root}/views/${paihang.hwId}"> 
<%--								<img border="0" src="${SMALL_PHOTO_PATH}/${paihang.hwPic}" width="55" height="50" class="banner">--%>

								<img src="${root}/common/images/number/num_${status.index+1}.gif">
								</a>
							</td>
							<td align="left">
											<font color="#006699"><a
												href="${root}/views/${paihang.hwId}" target="_blank">
													${paihang.hwName} </a>
											</font>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</td>
	</tr>
</table>
