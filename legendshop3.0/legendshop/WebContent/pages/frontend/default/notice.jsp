<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/pages/common/taglib.jsp'%>
<table width="205px" height="116px" cellspacing="0" cellpadding="0"  style="margin-right: 5px;margin-bottom: 0px;margin-left: 0px;margin-top: 0px" class="tables">
                    <tr> 
                      <td class="titlebg"><fmt:message key="index.notice"/></td>
                    </tr>
                    <tr> 
                    <td height="87px" valign="top">                         
   <c:choose>
   <c:when test="${requestScope.pubList == null}">
   	  <p>&nbsp;<br>
   </c:when>
   <c:otherwise>
  	 <c:forEach items="${requestScope.pubList}" var="pub">
  	 <div class="topnewsfixed"> 
                         <c:choose>
                          <c:when test="${fn:length(pub.title) > 14}">
                            <div title="${pub.title}" align="left">
                                <img src="${pageContext.request.contextPath}/common/images/default/dot.gif" width="15" height="15" style="margin-left: 3px"><a href="${pub.msg}">${pub.title}</a>
                            </div>
                          </c:when>
                          <c:otherwise>
                            <div align="left">
                                <img src="${pageContext.request.contextPath}/common/images/default/dot.gif" width="15" height="15" style="margin-left: 3px"><a href="${pub.msg}">${pub.title}</a>
                            </div>
                          </c:otherwise>
                        </c:choose>  
          </div>             
  	 </c:forEach>
   </c:otherwise>
</c:choose>

                      </td>
                    </tr>
</table>