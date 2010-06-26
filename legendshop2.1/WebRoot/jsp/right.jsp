<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/common/jsp/taglib.jsp'%>
<link rel="stylesheet" type="text/css" media='screen' href="${root}/common/css/overlay.css" />
<script type="text/javascript">
		function turnToPage(id) {
			document.getElementById('nsortId').value= id;
			document.getElementById('PagingForm').submit();
		}
		
		function pager(curPageNO){
			document.getElementById("curPageNO").value=curPageNO;
			document.getElementById("form1").submit();
		}
	</script>
	<table cellspacing="0" cellpadding="0" width="740px">
	    <tr>
               <c:choose>
                    <c:when test="${sort.picture !=null}">
                      <td>
                        <a target="_blank" href="${root}/sort/${sort.sortId}">
                           <img src="${PHOTO_PATH}/${sort.picture}" width="735px" class="banner">
                        </a>
                      </td>
                    </c:when>
                    <c:otherwise>
                        <td height="0px" width="735px"></td>                      
                    </c:otherwise>
                </c:choose>
    </tr>
    <c:if test="${sort.sortName != null}">
	<tr>
		<td style="border-bottom:#cccccc 1px solid;background-color:#f7f7f7 " width="100%">
			<table width="740px">
				<tr>
					<td height="25" align="left">
						${sort.sortName}
					</td>
					<td align="right">
						<c:if test="${not empty requestScope.nsortList}">
							<select onchange="turnToPage(this.value)">
								<option id="allProduct" value="0">
									<bean:message key="all.category" />
								</option>
								<c:forEach items="${requestScope.nsortList}" var="nsort">
									<c:choose>
										<c:when test="${CurrentNsortId == nsort.nsortId}">
											<option id="${nsort.nsortId}" value="${nsort.nsortId}"
												class="c" selected>
												${nsort.nsortName}
											</option>
										</c:when>
										<c:otherwise>
											<option id="${nsort.nsortId}" value="${nsort.nsortId}"
												class="c">
												${nsort.nsortName}
											</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</c:if>
						<form id="PagingForm" method="post" action="${root}/nsort.do">
							<input type="hidden" id="sortId" name="sortId" value="${sort.sortId}">
							<input type="hidden" id="nsortId" name="nsortId">
						</form>

					</td>
				</tr>
			</table>
		</td>
	</tr>
	</c:if>
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
            <table width="740px">
                            <tr>
                                <td valign="top" width="740px">
                                    <form action="${root}/sort.do" id="form1" method="post">
                                        <input type="hidden" id="curPageNO" name="curPageNO" value="${hw.curPageNO}">
                                        <input type="hidden" name="sortId" value="${sort.sortId}">
                                        <table width="100%" border="0" cellspacing="0"
                                            cellpadding="0">
                                            <tr>
						                    <c:choose>
						                       <c:when test="${hwDetailList != null && fn:length(hwDetailList) > 0}">
						                          <c:forEach items="${requestScope.hwDetailList}"
                                                    var="hwDetail" varStatus="status">
                                                    <td  align="center">
                                                    <table border="0">
                                                        <tr>
                                                            <td align="center" >
                                                            <div id="apple">
                                                                <a href="${root}/views/${hwDetail.hwId}/${hwDetail.sortId}">
                                                                    <img src="${SMALL_PHOTO_PATH}/${hwDetail.hwPic}" width="160px"
                                                                        height="118px" style="margin: 2px" ></a>
                                                            </div>
                                                            </td>
                                                        </tr>
                                                        <tr align="center">                                                           
                                                         <c:choose>
                                                          <c:when test="${fn:length(hwDetail.hwName) > 18}">
                                                            <td width="100%" align="center" title="${hwDetail.hwName}">
                                                                <a href="${root}/views/${hwDetail.hwId}/${hwDetail.sortId}">
                                                                <font color="#006699"><u>${fn:substring(hwDetail.hwName,0,18)}...</u></font></a>
                                                            </td>
                                                          </c:when>
                                                          <c:otherwise>
                                                            <td width="100%" align="center"">
                                                                <a href="${root}/views/${hwDetail.hwId}/${hwDetail.sortId}">
                                                                    <font color="#006699"><u>${hwDetail.hwName}</u></font>
                                                                </a>
                                                            </td>
                                                          </c:otherwise>
                                                          </c:choose>
                                                        </tr>
                                                        <tr>
                                                            <td align="left" valign="top">
                                                            <%-- 
                                                                <bean:message key="hw_price" />:
                                                                <s><fmt:formatNumber type="currency"
                                                                        value="${hwDetail.hwPrice}" /> </s>
                                                                <br>
                                                                --%>
                                                             <c:if test="${not empty hwDetail.hwCash}">
                                                                <bean:message key="hw_cash" /> 
                                                                <font color="red"><fmt:formatNumber
                                                                        type="currency" value="${hwDetail.hwCash}" /></font>
                                                                <c:if test="${empty userName}">
                                                                <a href="${root}/basket.do?hwId=${hwDetail.hwId}" rel="#overlay">
                                                                <img border="0" src="${root}/img/order.gif" align="middle" title="<bean:message key='order'/>"></a>
                                                                </c:if>
                                                                
                                                                <c:if test="${not empty userName}">
                                                                <a href="${root}/basket.do?hwId=${hwDetail.hwId}" rel="superbox[iframe][525x420]">
                                                                <img border="0" src="${root}/img/order.gif" align="middle" title="<bean:message key='order'/>"></a>                                                                 
                                                                </c:if>
                                                              </c:if>
                                                            </td>
                                                        </tr>
                            
                                                    </table>

                                                    </td>
                                            <c:if test="${(status.index+1)%4==0&&(status.index+1)>=4}">
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
                                    </form>
                                </td>
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

