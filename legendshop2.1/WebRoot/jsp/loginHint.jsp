<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file='/common/jsp/taglib.jsp'%>
<%session.setAttribute("root", request.getContextPath());%>
 <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center" class="tables">
  <tr> 
    <td height="28" class="titlebg" align="center" width="90%"><font color="white"><bean:message key="no.login.hint"/>:</font></td>
  </tr>
  <tr> 
    <td height="50"  class="bodybg" align="left" style="margin-left: 80px;">
     <bean:message key="nologin.hint.self" arg0="${root}/login.do" arg1="${root}/reg.do"/>
      </td>
  </tr>
</table>