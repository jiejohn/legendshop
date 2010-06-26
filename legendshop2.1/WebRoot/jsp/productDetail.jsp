<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file='/common/jsp/taglib.jsp'%>
<link rel="stylesheet" type="text/css" media='screen' href="${root}/common/css/overlay.css" />
<script src="${root}/common/js/jquery.tools.min.js"></script>
     <link rel="stylesheet" href="${root}/common/css/jquery.superbox.css" type="text/css" media="all" />
     <script type="text/javascript" src="${root}/common/js/jquery.superbox-min.js"></script>
<script type="text/javascript">
$(function() { 
    $("#apple img[rel]").overlay({effect: 'apple'}); 
});
</script>
<!--[if lt IE 7]>
    <link rel="stylesheet" type="text/css" media='screen' href="${root}/common/css/overlay-ie6.css" />
<![endif]-->
<table cellspacing="0" cellpadding="0" width="740px" style="TABLE-LAYOUT: fixed; WORD-WRAP: break-word;">
                    <tr> 
                      <td width="100%"> 
                        <table>
                          <tr> 
                            <td valign="top"> 
                              <div id="apple">
                                <img src="${SMALL_PHOTO_PATH}/${hw.hwPic}" width="256px" height="188px"  style="margin-right: 10px" rel="#${hw.hwId}"/>
                                </div>
                                <div class="apple_overlay" id="${hw.hwId}">
    <img src="${PHOTO_PATH}/${hw.hwPic}" width="640px" height="470px"/>
            <div class="details">
                <h2>${hw.hwName}</h2>
            </div>
                <br>
                <a href="${PHOTO_PATH}/${hw.hwPic}" target="_blank"> [<bean:message key="view.big.picture"/>]</a>
                </div>
                            </td>
                            <td align="center" valign="top">
                            <table width="100%" border="1" cellpadding="0" cellspacing="0" 
                              style="border-collapse: collapse;margin-top: 7px" bordercolor="#808080" bgcolor="#ECECEC" >
                                <tr align="center">
                                  <td height="25" colspan="2"><bean:message key="product.name"/>：<a href="${root}/views/${hw.hwId}">${hw.hwName}</a></td>
                                </tr>
                                <tr bgcolor="#FFFFFF"> 
                                  <td width="40%" height="25"> 
                                    <div align="right"><bean:message key="sort_name"/>：</div>
                                  </td>
                                  <td width="60%" height="25"> 
                                    <div align="center">
                                    <a href="${root}/sort/${hw.sortId}">${hw.sortName}</a>
                                     <c:if test="${not empty hw.nsortName}">
                                     =&gt;  ${hw.nsortName}
                                     </c:if>   
                                     <c:if test="${not empty hw.subNsortName}">
                                     =&gt;  ${hw.subNsortName}
                                     </c:if>    
                                    </div>
                                  </td>
                                </tr>
        
                                <c:choose>
                                    <c:when test="${not empty hw.hwCash}">
                                        <tr bgcolor="#FFFFFF"> 
                                  <td width="40%" height="25"> 
                                    <div align="right"><bean:message key="hw_price"/>：</div>
                                  </td>
                                  <td width="60%" height="25"> 
                                    <div align="center"><s><fmt:formatNumber type="currency" value="${hw.hwPrice}"/></s></div>
                                  </td>
                                </tr>
                                <tr bgcolor="#FFFFFF"> 
                                  <td height="25"> 
                                    <div align="right"><bean:message key="hw_cash"/>：</div>
                                  </td>
                                  <td height="25">
                                    <div align="center">
                                    <font color="#D03430"><fmt:formatNumber type="currency" value="${hw.hwCash}"/></font></div>
                                  </td>
                                </tr>
                                <tr bgcolor="#FFFFFF">
                                  <td height="35" colspan="2">
                                    <div align="center">
                                    <c:if test="${empty userName}">
                                    <a href="${root}/basket.do?hwId=${hw.hwId}" rel="#overlay">
                                    <bean:message key="order"/><img border="0" src="${root}/img/order.gif"></a>
                                    </c:if>
                                    <c:if test="${not empty userName}">
                                    <a href="${root}/basket.do?hwId=${hw.hwId}" rel="superbox[iframe][525x420]">
                                    <bean:message key="order"/><img border="0" src="${root}/img/order.gif"></a>
                                    </c:if>
                                    </div>
                                  </td>
                                </tr>
                                    </c:when>
                                    <c:otherwise>
		                                 <tr bgcolor="#FFFFFF"> 
		                                  <td height="25" colspan="2"> 
		                                    <div><img src="${root}/img/iconWarning.gif">
		                                    <bean:message key="notsupportorder"/>
		                                    <br> E-mail：${shopDetail.userMail} &nbsp;&nbsp;<bean:message key="Phone"/> ：${shopDetail.userTel}</div>
		                                  </td>
		                                 </tr>
                                    </c:otherwise>
                                </c:choose>                         
                              
                              </table>
                              <table width="100%" border="0" cellspacing="0" cellpadding="0" style="TABLE-LAYOUT: fixed; WORD-WRAP: break-word;">
                                <tr align="left"> 
                                  <td>
                                  <pre>${hw.hwBrief}</pre>
                                  </td>
                                </tr>
                              </table>
                              <!-- 
                              <table cellspacing="0" cellpadding="0">
                                <tr> 
                                  <td align="center">&nbsp; 
                                   <c:if test="${hw.commend == 'True'}"> 
                                    <img src="${root}/img/hot_product.jpg" > 
                                     <font color="red">
                                        <bean:message key="hot.sale"/>
                                     </font>
                                    </c:if>
                                  </td>
                                </tr>
                              </table>
                               -->
                            </td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                          <tr> 
                            <td bgcolor="f7f7f7" style="border-bottom:#cccccc 1px solid" align="center">
                            <div align="center"><img src="${root}/img/dot.gif"><bean:message key="hw_content"/></div>
                               </td>
                          </tr>
                          <tr align="left"> 
                            <td>${hw.hwContent}</td>
                          </tr>
                  </table>
            <br>