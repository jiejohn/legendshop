<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/common/jsp/taglib.jsp'%>
<c:if test="${hotonList != null && fn:length(hotonList) > 0}">
<table width="205px" border="0" cellspacing="0" cellpadding="0" style="margin-bottom: 5px">
<%--                                 <tr> --%>
<%--                                  <td align="center"> <img border="0" src="img/promo_list_top.gif" width="100%" height="1"></td>--%>
<%--                                </tr>--%>
                      <tr>
                        <td height="28" align="center" class="titlebg"><font color="white" style="font-family: sans-serif;font-weight: bold"><bean:message key="hot.sale"/></font></td>
                      </tr>
                      <tr> 
                        <td> 
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr> 
                              <td bgcolor="#F8F8F8">                  
                                <c:forEach items="${requestScope.hotonList}" var="hoton">
	                                <table width="100%">
   									<tr height=22> 
                                      <td width=34 align="center">
                                      	<img src="${root}/img/dot.gif">
                                      </td>
                        <c:choose>
                          <c:when test="${fn:length(hoton.hwName) > 14}">
                            <td width="90%" align="left" title="${hoton.hwName}">
                                <a href="${root}/views/${hoton.hwId}">${fn:substring(hoton.hwName,0,14)}...</a>
                            </td>
                          </c:when>
                          <c:otherwise>
                            <td width="90%" align="left"">
                                <a href="${root}/views/${hoton.hwId}">${hoton.hwName}</a>
                            </td>
                          </c:otherwise>
                        </c:choose>          
                                    </tr>
                                 </table>
                        </c:forEach>
                              </td>
                            </tr>
                          </table></td>
                      </tr>
                    </table>
</c:if>