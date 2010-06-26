<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file='/common/jsp/taglib.jsp'%>
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="48">
          <tr> 
            
          <td height="108">
<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                <tr>
                  <td width="100%" height="28" class="headerbg"> 
                  <div align="center"><font color="#FFFFFF"><b><bean:message key="operation"/></b></font></div>
                  </td>
                </tr>
                <tr> 
                  
                <td height="63" bgcolor="#ECECEC"> 
                  <div align="center">
                    <table width="100%" height="30" border="0" cellspacing="1">
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
                                      <td width="80">　</td>
                                      <td> 
                                        <div align="left"> 
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <a href="${root}/index.do">
                                              <SPAN class="afterOperate"><bean:message key="operation.successful"/></SPAN>
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
                        </td>
                      </tr>
                    </table>
                  </div>
                </td>
                </tr>
              </table></td>
          </tr>
        </table>