<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/common/jsp/taglib.jsp'%>
<link rel="stylesheet" type="text/css" media='screen' href="${root}/common/css/overlay.css" />

	<table cellspacing="0" cellpadding="0" width="740px">
	            <tr> 
                    <td height="28" class="headerbg" align="center">
                    <font color="#FFFFFF"><b><bean:message key="leagueShop"/></b></font></td>
                  </tr>
	<c:if test="${toolBar!=null}">
		<tr height="40" valign="bottom">
			<td>
				<p align="right">
					<c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
				</p>
			</td>
		</tr>
	</c:if>
	<tr>
		<td width="740px">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
                                        <tr>
						                    <c:choose>
						                       <c:when test="${leagues != null}">
						                          <c:forEach items="${requestScope.leagues}" var="league" varStatus="status">
                                                    <td>
                                                    <table border="0">
                                                        <tr>
                                                            <td align="center" >
                                                            <div id="apple">
                                                                <a href="${root}/shop/${league.friendId}">
                                                                    <img src="${PHOTO_PATH}/${league.banner}" 
                                                                        height="65px" style="margin: 2px" title="${league.friendName}"></a>
                                                            </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="center">${league.friendName}</td>
                                                         </tr>
                                                    </table>

                                                    </td>
                                            <c:if test="${(status.index+1)%3==0&&(status.index+1)>=3}">
                                                </tr>
                                            	<tr>
                                            </c:if>
                                                </c:forEach>
						                       </c:when>
						                       <c:otherwise>
						                       <td  align="center">
						                          <bean:message key="nothingfound"/>
						                        </td>
						                       </c:otherwise>
						                    </c:choose>   
                                            </tr>
                                        </table>
		</td>
	</tr>
	<tr>
		<td><c:if test="${toolBar!=null}">
			<p align="right">
				<c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
			</p>
			</c:if>
		</td>
	</tr>
</table>

