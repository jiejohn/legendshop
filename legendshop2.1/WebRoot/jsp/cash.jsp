<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/common/jsp/taglib.jsp'%>
<jsp:directive.page import="com.done.util.UserManagement"/>
<html>
<head>
<%@include file='/common/jsp/common.jsp'%>
<%
	session.setAttribute("userName",UserManagement.getUsername());
 %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
    function submitForm(){
        document.forms[0].submit();
    }
</script>
<title><bean:message key="cash.bar"/></title></head> 
<body topmargin="0">
  <c:choose>
   <c:when test="${userName == null}">
   	  <logic:forward name="nologon"/>
   </c:when>
   	<c:otherwise>
<table width="500" border="0" cellspacing="0" cellpadding="0" align="center" style="margin-top: 5px">
  <tr> 
    <td height="28" class="titlebg" align="center"><font color="white"><bean:message key="cash.bar"/></font></td>
  </tr>
  <tr> 
                    <td valign="top" bgcolor="#ECECEC">
                         <div align="center">
                          <center>
                            <jsp:include flush="true" page="/bought.do"></jsp:include>
                          </center>
                         </div>
                     </td>
  </tr>
  <tr> 
    <td height="28" class="titlebg" align="center" valign="middle">
                            
                        <c:choose>
                           <c:when test="${not empty baskets }">

                              <input type="submit" value="<bean:message key="Next"/>" class="s" onclick="javascrpit:submitForm()"/>
                             
                           </c:when>
                           <c:otherwise>
                            <font color="#FF6600">
                                <bean:message key="cash.alerm"/>
                            </font>
                           </c:otherwise>
                        </c:choose>
                     
    </td>
  </tr>
</table>
	</c:otherwise>
</c:choose>
                            <form method="POST" action="${root}/cashsave.do">
                             <input name="total" value="${requestScope.totalcash}" type="hidden"/>
                             </form>
</body>
</html>