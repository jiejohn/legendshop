<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/common/jsp/taglib.jsp'%>
<table width="205px"  border="0" cellspacing="0" cellpadding="0"  style="margin-bottom: 4px;margin-right: 5px;" class="tables">
                    <tr> 
                      <td align="center" height="28" class="titlebg">
                      <font color="white">
                      	<bean:message key="index.notice"/>
                      </font>
                      </td>
                    </tr>
                    <tr> 
                    <td height="87px" valign="top">                         
   <c:choose>
   <c:when test="${requestScope.pubList == null}">
   	  <p>&nbsp;<br>
   </c:when>
   <c:otherwise>
  	 <c:forEach items="${requestScope.pubList}" var="pub">
  	 <div style="margin-left: 5px;margin-right: 5px;table-layout: fixed;overflow: hidden;margin-top: 1px;width: 93%" > 
                         <c:choose>
                          <c:when test="${fn:length(pub.title) > 14}">
                            <div title="${pub.title}" align="left" style="height: 22px;padding-right: 5px;" nowrap>
                                <img src="${root}/img/dot.gif" width="15" height="15"><a href="${pub.msg}">${pub.title}</a>
                            </div>
                          </c:when>
                          <c:otherwise>
                            <div align="left" style="height: 22px;padding-right: 5px;" nowrap>
                                <img src="${root}/img/dot.gif" width="15" height="15"><a href="${pub.msg}">${pub.title}</a>
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