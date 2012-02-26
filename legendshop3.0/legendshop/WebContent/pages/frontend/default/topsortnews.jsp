<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/pages/common/taglib.jsp'%>
<table width="205px" cellspacing="0" cellpadding="0"
	bgcolor="#FFFFF6" style="margin-bottom: 5px;margin-right: 5px;table-layout: fixed;" class="tables">
	<tr>
		<td class="titlebg"><fmt:message key="newsCenter"/>
		          <a href="${pageContext.request.contextPath}/allNews${applicationScope.WEB_SUFFIX}"><img src="${pageContext.request.contextPath}/img/more.gif" width="45"></a></td>
	</tr>
	<tr height="135px">
		<td valign="top" align="left">
			<c:forEach items="${requestScope.newList}" var="new">
				<div class="topnewsfixed">
                        <c:choose>
                          <c:when test="${fn:length(new.newsTitle) > 18}">
                            <div  title="${new.newsTitle}" class="topnewsfixed">
                                <img src="${pageContext.request.contextPath}/img/dot.gif" style="margin-left: 3px"><a href="${pageContext.request.contextPath}/news/${new.newsId}${applicationScope.WEB_SUFFIX}">${new.newsTitle}</a>
                            </div>
                          </c:when>
                          <c:otherwise>
                            <div class="topnewsfixed">
                                <img src="${pageContext.request.contextPath}/img/dot.gif" width="15" height="15" style="margin-left: 3px"><a href="${pageContext.request.contextPath}/news/${new.newsId}${applicationScope.WEB_SUFFIX}">${new.newsTitle}</a>
                            </div>
                          </c:otherwise>
                        </c:choose>
				</div>
			</c:forEach>
		</td>
	</tr>
</table>