<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/common/jsp/taglib.jsp'%>
<table width="205px" border="0" cellspacing="0" cellpadding="0"  style="margin-bottom: 4px;margin-left: 5px;" class="tables">
	<tr>
		<td align="center" height="28" class="titlebg" valign="middle" width="205px">
			<font color="white" style="font-family: sans-serif"><b><bean:message key="new.product" /></b></font>
		</td>
	</tr>
	<tr align="center" height="260px">
		<td width="205px" align="left">
			<table width="93%" height="258px" border="0" cellspacing="0" cellpadding="0" style="margin-left: 5px;margin-right: 5px;table-layout: fixed;overflow: hidden;">
				<c:if test="${hwList != null}">
					<c:forEach items="${requestScope.newestList}" var="hw" varStatus="status">
						<tr align="left">
						<c:choose>
                          <c:when test="${fn:length(hw.hwName) > 14}">
                             <td width="93%" align="left" title="${hw.hwName}" style="height: 25px;padding-right: 5px" nowrap>
                                      <a href="${root}/views/${hw.hwId}/${hw.sortId}"> 
								<img src="${root}/common/images/number/num_${status.index+1}.gif" style="margin-left: 3px;"></a>
								<font color="#006699">
                                       <a href="${root}/views/${hw.hwId}/${hw.sortId}" style="margin-left: 3px;">
                                ${hw.hwName}</a>
                                     </font>
                            </td>
                          </c:when>
                          <c:otherwise>
                          <td width="93%" align="left" style="height: 25px;padding-right: 5px" nowrap>
                               <a href="${root}/views/${hw.hwId}/${hw.sortId}">
								<img src="${root}/common/images/number/num_${status.index+1}.gif" style="margin-left: 3px;"></a>
                              <font color="#006699">
                               <a href="${root}/views/${hw.hwId}/${hw.sortId}" style="margin-left: 3px;">${hw.hwName}</a>
                              </font>
                            </td>
                          </c:otherwise>
                        </c:choose>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</td>
	</tr>
</table>
