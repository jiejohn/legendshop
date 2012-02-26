<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/pages/common/taglib.jsp'%>
                              <table width="180" cellspacing="0" cellpadding="0" style="margin-bottom: 5px;margin-right: 5px;">
                                <tr> 
                                  <td align="center"><img src="${pageContext.request.contextPath}/img/promo_list_top.gif" width="100%" height="1"></td>
                                </tr>
                                <tr><td class="titlebg"><fmt:message key="friend.link"/></td></tr>
                                <tr> 
                                  <td bgcolor="#ECECEC"> <p style="line-height:150%" align=center> 
                                     
      <c:forEach items="${requestScope.adList}" var="ad">
      	<a href="${ad.url}" target=_blank>${ad.wordlink}</a><br>
      </c:forEach>
                                    </p></td>
                                </tr>
                              </table>