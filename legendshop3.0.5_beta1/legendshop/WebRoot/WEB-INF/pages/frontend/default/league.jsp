<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<link rel="stylesheet" type="text/css" media='screen' href="${pageContext.request.contextPath}/common/default/css/overlay.css" />
<c:forEach items="${requestScope.USER_REG_ADV_740}" var="adv">
<table width="100%" cellpadding="0" cellspacing="0" style="margin-bottom: 5px" class="picstyle">
<tr><td><a href="${adv.linkUrl}"><img src="<ls:photo item='${adv.picUrl}'/>" title="${adv.title}" width="740px"/></a></td></tr>
 </table>
</c:forEach>
	<table class="tables" cellpadding="0" cellspacing="0">
	            <tr> 
                    <td class="titlebg">
                    <fmt:message key="leagueShop"/></td>
                  </tr>
	<tr>
		<td width="744px">
			<table width="100%" cellspacing="0" cellpadding="0">
                                        <tr>
						                    <c:choose>
						                       <c:when test="${leagues != null}">
						                          <c:forEach items="${requestScope.leagues}" var="league" varStatus="status">
                                                    <td align="left">
                                                    <table>
                                                        <tr>
                                                            <td align="center" >
                                                            <div id="apple">
                                                             <a href="<ls:domain shopName='${league.friendId}'/>">
                                                             <c:choose>
                                                             		<c:when test="${league.banner == null}">
                                                             		                   <img src="common/images/legendshop.gif" 
                                                                                         height="65px" style="margin: 2px" title="${league.province}/${league.city}/${league.area}/${league.friendId}">
                                                             		</c:when>
                                                             		<c:otherwise>
                                                             			          <img src="<ls:photo item='${league.banner}'/>" 
                                                                                     height="65px" style="margin: 2px" title="${league.province}/${league.city}/${league.area}/${league.friendId}">
                                                             		</c:otherwise>
                                                             </c:choose>
       
                                                             </a>
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
						                       <td align="center">
						                          <fmt:message key="nothingfound"/>
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
 <form action="${pageContext.request.contextPath}/league" id="form1" method="post">
 	<input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
 </form>
    <script language="JavaScript" type="text/javascript">
<!--

        function pager(curPageNO){
            document.getElementById("curPageNO").value=curPageNO;
            document.getElementById("form1").submit();
        }
//-->
</script>
