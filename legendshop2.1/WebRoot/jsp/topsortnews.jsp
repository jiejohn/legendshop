<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/common/jsp/taglib.jsp'%>
<table width="205px" border="0" cellspacing="0" cellpadding="0"
	bgcolor="#F8F8F8" style="margin-bottom: 5px;margin-right: 5px;table-layout: fixed;">
	<tr>
		<td align="center" class="titlebg" height="28">
			<font color="white" style="font-weight: bold;font-family: sans-serif;"> <bean:message key="newsCenter"/></font>
		          <a href="${root}/allNews.do"><img src="${root}/img/pics/more.gif" width="45" border="0"></a>
		</td>
	</tr>
	<tr height="135px">
		<td valign="top" align="left">
			<c:forEach items="${requestScope.newList}" var="new">
				<div style="margin-left: 5px;margin-right: 5px;table-layout: fixed;overflow: hidden;width: 93%">
                        <c:choose>
                          <c:when test="${fn:length(new.newsTitle) > 18}">
                            <div   title="${new.newsTitle}" style="height: 22px;padding-right: 5px" nowrap>
                                <img src="${root}/img/dot.gif" width="15" height="15"><a href="${root}/news/${new.newsId}">${new.newsTitle}</a>
                            </div>
                          </c:when>
                          <c:otherwise>
                            <div style="height: 22px;padding-right: 5px" nowrap>
                                <img src="${root}/img/dot.gif" width="15" height="15"><a href="${root}/news/${new.newsId}">${new.newsTitle}</a>
                            </div>
                          </c:otherwise>
                        </c:choose>
				</div>
			</c:forEach>
		</td>
	</tr>
</table>
