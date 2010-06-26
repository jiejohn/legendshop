<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file='/common/jsp/taglib.jsp'%>
<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                <tr>
                  <td width="100%" height="28" class="headerbg"> 
                  <div align="center"><font color="#FFFFFF"><b><bean:message key="operation"/></b></font></div>
                  </td>
                </tr>
                <tr> 
                <td height="63" bgcolor="#ECECEC"> 
                  <table width="100%" border="0" cellspacing="0" cellpadding="0"  style="margin: 10px">
                                     <tr> 
                                      <td>
                                      <div style="margin-left: 30px"> 
                                      <a href="${root}/index.do">
                                          <c:choose>
                                            <c:when test="${not empty ERROR}">
                                                <SPAN class="afterOperate"><bean:message key="operation.fail"/></SPAN>
                                            </c:when>
                                            <c:otherwise>
                                                <SPAN class="afterOperate"><bean:message key="operation.successful"/></SPAN>
                                            </c:otherwise>
                                        </c:choose>
                                        </a>
                                        </div>
                                        <br>
                                      </td>
                                    </tr>
                                    <tr> 
                
                                      <td> 
                                        <div style="margin-left: 30px"> 
                                        &nbsp;&nbsp;
                                        <a href="${root}/index.do">
                                                <html:errors/>
                                        </a>
                                          <br><br>
                                          <bean:message key="has.problem"/>:<br>
                                          Admin E-mail：${shopDetail.userMail} <br>
                                          <bean:message key="Phone"/> ：${shopDetail.userTel}<br>
                                         <bean:message key="address"/> ：${shopDetail.ymaddr}<br>
                                         <bean:message key="postcode"/>：${shopDetail.code}
                                        </div>
                                      </td>
                                    </tr>
                                  </table>
                </td>
                </tr>
              </table>