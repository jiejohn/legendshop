<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/common/jsp/taglib.jsp'%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
<c:if test="${requestScope.adList!=null}">
<table width="950" align="center" cellpadding="0" cellspacing="0">
<tr >
  <td height="40px" align="center">
	   <c:forEach items="${requestScope.adList}" var="ad">
      		<a href="${ad.url}" target=_blank title="${ad.content}">${ad.wordlink}</a>   
      </c:forEach>
    </td></tr>
  </table>
 </c:if> 
  
  
<table width="950px" border="0" align="center" cellpadding="0" cellspacing="0" class="tables">
<tr>
 <td>
    <table width="100%" class="titlebg">
      <tr >
        <td colspan="7" height="22">
        <div align="center">
        <font color="#ffffff">
       		<bean:message key="order.step"/> 
        </font></div>
         </td>
      </tr>
    </table>
  </td>
  </tr>
  <tr>
 <td>
<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" >
  <tr> 

  <td width="100%" height=25 bgcolor="#F8F8F8" > 
  <p align=center>
  <font face="宋体">&copy;</font>Copyright2009
  ${shopDetail.web} All Rights Reserved <a href="http://www.miibeian.gov.cn/" target="_blank">粤ICP备09191613号</a><br>
          Admin E-mail：${shopDetail.userMail} &nbsp; <bean:message key="Phone"/> ：${shopDetail.userTel}
 		 <br>
         <bean:message key="address"/> ：${shopDetail.ymaddr}&nbsp; <bean:message key="postcode"/>：${shopDetail.code}
          </p>
          </td></tr>
</table>

</td>
</tr>
</table>
