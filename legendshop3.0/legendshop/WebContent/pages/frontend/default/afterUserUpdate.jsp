<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file='/pages/common/taglib.jsp'%>
<table class="tables" cellpadding="0" cellspacing="0">
                <tr>
                  <td class="titlebg"><fmt:message key="operation"/></td>
                </tr>
                <tr> 
                <td bgcolor="#ECECEC"> 
                  <div align="center">
                    <table width="100%" height="30" cellspacing="0">
                      <tr> 
                        <td width="100%" height="37"> 
                          <table width="100%" cellspacing="0" cellpadding="0" align="center">
                              <tr> 
                                <td height="110" valign="middle" align="left"> 
                                  <table width="100%" cellspacing="5" cellpadding="2" align="center">
                                    <tr> 
                                      <td width="80px">　</td>
                                      <td> 
                                        <div align="center"> 
                                        <c:if test="${OK==null}">
                                          <a href="${pageContext.request.contextPath}/myaccount${applicationScope.WEB_SUFFIX}">
                                             <SPAN class="afterOperate"><fmt:message key="operation.error"/></SPAN>
                                          </a>
                                        </c:if>
                                        <c:if test="${OK!=null}">
                                        
                                             <SPAN class="afterOperate"><fmt:message key="operation.successful"/></SPAN>
                                         <br><br><br>
                                         <input type="submit" onclick="javascript:turnToIndex()" value='<fmt:message key="submit"/>' class="s"/>
                                        
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
          <script type="text/javascript">
             function turnToIndex(){
             	window.location.href = "${pageContext.request.contextPath}/index${applicationScope.WEB_SUFFIX}";
             }
          </script>