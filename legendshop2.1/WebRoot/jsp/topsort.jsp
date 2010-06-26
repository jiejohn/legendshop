<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/common/jsp/taglib.jsp'%>
<table width="205" border="0" cellspacing="0" cellpadding="0" bgcolor="#F8F8F8" style="margin-bottom: 5px;margin-right: 5px;">
                <tr> 
                  <td align="center" height="28" class="titlebg">
                        <font color="white" style="font-family: sans-serif;font-weight: bold;"><bean:message key="product.sort"/></font>
                   </td>
                </tr>
                <tr> 
                  <td valign="top"> <table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr> 
                        <td> 
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr> 
                              <td height="16" valign="top"> 

<c:choose>
   <c:when test="${sortList == null}">
   		&nbsp;
   </c:when>
   <c:otherwise>
   <c:forEach items="${requestScope.sortList}" var="sort">   
  	        <table cellspacing="0" cellpadding="0" width="100%" border="0"> 
                                    <tr height=22> 
                                      <td width="15" align="center">
                                      	<img src="${root}/img/dot.gif">
                                      </td>
                                      <td width="124" align="left">
                                      	<a href="${root}/sort/${sort.sortId}">
                                      	<c:choose>
                                      	<c:when test="${fn:length(sort.sortName) >12}">
                                      	   ${fn:substring(sort.sortName,0,12)}..
                                      	</c:when>
                                      	<c:otherwise>
                                      	   ${sort.sortName}
                                      	</c:otherwise>
                                      	</c:choose>
                                      	</a>
                                      </td>
                                    </tr>
                                     <tr> 
                                      <td align="right" colspan="2">
                                      <table width="88%" border="0" cellspacing="0" cellpadding="0">
                                      <tr>
                                     
                                      <c:forEach items="${sort.nsort}" var="nsort" varStatus="status"> 
                          <c:choose>
                          <c:when test="${fn:length(nsort.nsortName) > 8}">
                            <td align="left" title="${nsort.nsortName}">
                                <img src="${root}/img/004.gif"><a href="${root}/nsort/${sort.sortId}/${nsort.nsortId}">${fn:substring(nsort.nsortName,0,8)}..</a>
                            </td>
                          </c:when>
                          <c:otherwise>
                            <td align="left"">
                                <img src="${root}/img/004.gif"><a href="${root}/nsort/${sort.sortId}/${nsort.nsortId}">${nsort.nsortName}</a>
                            </td>
                          </c:otherwise>
                        </c:choose>
                                                   <c:if test="${(status.index+1)%2==0&&(status.index+1)>=2}">
														 </tr><tr>
             									    </c:if>	     
                                      </c:forEach>
                                      </tr>
                                      </table>
                                      </td> 
                                    </tr>                               
                                </table>
       </c:forEach> 
   </c:otherwise>
</c:choose>

                     </td>
                            </tr>
                          </table></td>
                      </tr>
                    </table></td>
                </tr>
              </table>