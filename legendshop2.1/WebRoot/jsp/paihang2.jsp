<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/common/jsp/taglib.jsp'%>
<table width="205" border="0" cellspacing="0" cellpadding="0" style="margin-bottom: 5px;margin-left: 5px;" >

	<tr>
		<td align="center" height="28" class="headerbg" colspan="2">
			<font color="white"><bean:message key="paihang" /></font>
		</td>
	</tr>
		<tr align="center" height="105">
		<c:if test="${hwList != null}">
			<c:forEach items="${requestScope.paihangList}" var="paihang" varStatus="status">
				<td class="bodybg" width="50%" >
				<table> 
					<tr>
						<td>
					<a href="${root}/views/${paihang.hwId}">
					  <img border="0" src="${SMALL_PHOTO_PATH}/${paihang.hwPic}" width="55" height="50" class="banner"></a>
					  </td>
					</tr>
					<tr>
						<td>
						 <font color="red" style="font-weight: bold;"><fmt:formatNumber type="currency" pattern="￥0" value="${paihang.hwCash}"/></font><br>
          <font color="#006699"><a href="${root}/views/${paihang.hwId}" target="_blank">
          ${paihang.hwName}
          </a></font></td>
					</tr>
				</table>
				</td>
			<c:if test="${(status.index+1)%2==0&&(status.index+1)>=2}">
				</tr><tr align="center">
			</c:if>
	</c:forEach>
	</c:if>
	</tr>
</table>
