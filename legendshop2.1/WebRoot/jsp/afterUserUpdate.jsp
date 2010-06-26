<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:directive.page import="com.done.util.UserManagement"/>
<%@include file='/common/jsp/taglib.jsp'%>
<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                <tr>
                  <td width="100%" height="28" class="headerbg"> 
                  <div align="center"><font color="#FFFFFF"><b><bean:message key="operation"/></b></font></div>
                  </td>
                </tr>
                <tr> 
                <td bgcolor="#ECECEC"> 
                  <div align="center">
                    <table width="100%" height="30" border="0" cellspacing="0">
                      <tr> 
                        <td width="100%" height="37"> 
                          <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                              <tr> 
                                <td height="110" valign="middle" align="left"> 
                                  <table border="0" align="center" cellpadding="0" cellspacing="5" width="100%">
                                    <tr> 
                                      <td colspan="2">
                                        <html:errors/>  
                                      </td>
                                    </tr>
                                  </table>
                                  <table width="100%" border="0" cellspacing="5" cellpadding="2" align="center">
                                    <tr> 
                                      <td width="80px">　</td>
                                      <td> 
                                        <div align="center"> 
                                        <c:if test="${OK==null}">
                                          <a href="${root}/myaccount.do">
                                             <SPAN class="afterOperate"><bean:message key="operation.error"/></SPAN>
                                          </a>
                                        </c:if>
                                        <c:if test="${OK!=null}">
                                        <a href="${root}/index.do">
                                             <SPAN class="afterOperate"><bean:message key="operation.successful"/></SPAN>
                                          </a>
                                         </c:if>
                                        </div>
                                      </td>
                                    </tr>
                                  </table>
                                </td>
                              </tr>
                          
                          </table>
                        </td>
                      </tr>
                    </table>
                  </div>
                </td>
                </tr>
              </table>