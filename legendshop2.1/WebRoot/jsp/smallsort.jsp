<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/common/jsp/taglib.jsp'%>
<%@include file='/common/jsp/common.jsp'%>
<link rel="stylesheet" type="text/css" media='screen' href="${root}/common/css/overlay.css" />
      	<script type="text/javascript">
		function turnToPage(id) {
			document.getElementById('nsortId').value= id;
			document.getElementById('subNsortId').value= null;
			pager(1);//turn to another nosrt page
		}
		
		function turnToSubSortPage(id) {
            document.getElementById('subNsortId').value= id;
            pager(1);//turn to another nosrt page
        }
		
		function pager(curPageNO){
			document.getElementById("curPageNO").value=curPageNO;
			document.getElementById("form1").submit();
		}
	</script>
    <table border="0" cellpadding="0" cellspacing="0">
                    <tr> 
                      <td>
						<a target="_blank" href="${root}/sort/${sort.sortId}">
						<img src="${PHOTO_PATH}/${sort.picture}" width="740px" class="banner" ></a>
					  </td>
                    </tr>
        <tr> 
          <td width="740px" style="border-bottom:#cccccc 1px solid" bgcolor="#f7f7f7"> 
            <table width="100%" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="25" align="left">&nbsp;
               <a href="${root}/sort/${sort.sortId}">${sort.sortName}</a>
                &gt;&gt;&nbsp;<a href="${root}/nsort.do?sortId=${sort.sortId}&nsortId=${nsort.nsortId}">${nsort.nsortName}</a>
                <c:if test="${subNsort.nsortName!=null}">
                 &gt;&gt;&nbsp;
                 <a href="${root}/nsort.do?sortId=${sort.sortId}&nsortId=${nsort.nsortId}&subNsortId=${subNsort.nsortId}">${subNsort.nsortName}</a>
                </c:if>
                &nbsp;</td>
                <td  align="right"> 
      		<c:if test="${not empty nsort}">
				<select onchange="turnToPage(this.value)">
					<c:forEach items="${requestScope.nsortList}" var="nsort">
						<c:choose>
						   <c:when test="${CurrentNsortId == nsort.nsortId}">
						   	<option id="${nsort.nsortId}" value="${nsort.nsortId}" class="c" selected>
						   	${nsort.nsortName}
						   	</option>
						   </c:when>
						   <c:otherwise>
						  	<option id="${nsort.nsortId}" value="${nsort.nsortId}" class="c">
								${nsort.nsortName}
								</option>
						   </c:otherwise>
						</c:choose>	
				    </c:forEach> 
				</select>
			</c:if>
			 <c:if test="${not empty hasSubSort}">
                <select onchange="turnToSubSortPage(this.value)">
                   <option id="allProduct" value="0"><bean:message key="refer.category"/></option>
                    <c:forEach items="${requestScope.subNsortList}" var="subNsort">
                        <c:choose>
                           <c:when test="${CurrentSubNsortId == subNsort.nsortId}">
                            <option id="${subNsort.nsortId}" value="${subNsort.nsortId}" class="c" selected>
                            ${subNsort.nsortName}
                            </option>
                           </c:when>
                           <c:otherwise>
                            <option id="${subNsort.nsortId}" value="${subNsort.nsortId}" class="c">
                                ${subNsort.nsortName}
                                </option>
                           </c:otherwise>
                        </c:choose> 
                    </c:forEach> 
                </select>
            </c:if>
                </td>
              </tr>
            </table></td>
        </tr>
          <tr height="40" valign="bottom">
                      <td > 
                        <p align="right"><c:out value="${toolBar}" escapeXml="${toolBar}"></c:out></p>
                      </td>
                    </tr>
        <tr> 
          <td width="100%">
           				<form action="${root}/nsort.do" id="form1" method="post">
										<input type="hidden" id="curPageNO" name="curPageNO"
											value="${hw.curPageNO}">
										<input type="hidden" name="sortId" value="${sort.sortId}">
         							    <input type="hidden" id="nsortId" name="nsortId" value="${nsort.nsortId}">
         							    <input type="hidden" id="subNsortId" name="subNsortId" value="${subNsort.nsortId}">
          <table width="100%" border="0" cellspacing="10"
											cellpadding="0">
											<tr>
												<c:forEach items="${requestScope.hwDetailList}"
													var="hwDetail" varStatus="status">
													<c:choose>
														<c:when
															test="${(status.index+1)%2==0&&(status.index+1)>=2}">
															<td width="50%" align="center">
														</c:when>
														<c:otherwise>
															<td width="50%" align="center"
																style="position: relative;border-right:#989DA5 1px dotted;">
														</c:otherwise>
													</c:choose>


													<table width="100%" border="0" align="center"
														cellpadding="0" cellspacing="0">
														<tr>
															<td rowspan="3" align="center" width="50%">
															<div id="apple">
																<a href="${root}/views/${hwDetail.hwId}/${hwDetail.sortId}">
																	<img src="${SMALL_PHOTO_PATH}/${hwDetail.hwPic}" width="142px"
																		height="105px" style="margin: 5px" ></a></div>
															</td>
														</tr>
														<tr align="left" >
															<td width="50%">
																<a href="${root}/views/${hwDetail.hwId}/${hwDetail.sortId}">
																<font color="#006699"><u>${hwDetail.hwName}</u> </font> </a>
															</td>
														</tr>
														<c:if test="${not empty hwDetail.hwCash}">
														<tr>
															<td width="50%"
																align="left" valign="top">
																<bean:message key="hw_price" /> 
																<s><fmt:formatNumber type="currency"
																		value="${hwDetail.hwPrice}" /> </s>
																<br>
																<bean:message key="hw_cash" /> 
																<font color="red"> <fmt:formatNumber
																		type="currency" value="${hwDetail.hwCash}" /> </font>
																<br>

																<a href='javascript:openbag(${hwDetail.hwId})'><bean:message
																		key="order" /> <img border="0" src="${root}/img/order.gif"
																		align="middle"> </a>
															</td>
														</tr>
							                             </c:if>
													</table>

													</td>
											<c:if test="${(status.index+1)%2==0&&(status.index+1)>=2}">
												</tr>
												<tr>
											</c:if>
												</c:forEach>
											</tr>
										</table>
          </form>
          
          
          </td>
         
        </tr>
        <tr> 
          <td width="100%" height="20"> <div align="center"></div></td>
        </tr>
        <tr> 
          <td width=740 height="23"> <p align="right"><c:out value="${toolBar}"  escapeXml="${toolBar}"></c:out></p></td>
        </tr>
      </table>