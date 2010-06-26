<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/common/jsp/taglib.jsp'%>
<table width="740px" border="0" cellpadding="0" cellspacing="0"
	style="TABLE-LAYOUT: fixed; word-break: break-all">
	<c:choose>
		<c:when test="${news.status != 1}">
			<tr>
				<td height="28" class="headerbg" width="100%">
					<div align="center">
						<font color="#FFFFFF"><b>${news.newsTitle}</b></font>
					</div>
				</td>
			</tr>
		</c:when>
		<c:otherwise>
			<tr>
				<td height="28" class="headerbg" width="100%">
					<div align="center">
						<font color="#FFFFFF"><b><bean:message key="newsCenter" />
						</b>
						</font>
						<a href="${root}/allNews.do">
						<img alt="<bean:message key="newsCenter"/>"
								src="${root}/img/pics/more.gif"></a>
					</div>
				</td>
			</tr>
			<tr>
				<td height="28px" width="100%">
					<div align="center">
						${news.newsTitle}
					</div>
				</td>
			</tr>
		</c:otherwise>
	</c:choose>



	<tr>
		<td height="200px" valign="top">
		<!-- 
			<div align="center">
				<c:if test="${news.status == 1}">
					<fmt:formatDate value="${news.newsDate}" pattern="yyyy-MM-dd" />
				</c:if>
			</div>
			 -->
			<div align="center">
				<div align="left" width="100%">
					${news.newsContent}
				</div>
			</div>
		</td>
	</tr>
</table>