<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<jsp:include flush="true" page="/topall${applicationScope.WEB_SUFFIX}" />
<div id="bodyer">
<table width="100%" cellspacing="0" cellpadding="0" align="center" style="margin: 5px">
    <tr> 
      <td><tiles2:insertAttribute name="main" ignore="true" /></td>
    </tr>
    <tr>
      <td valign="top">
      <jsp:include page="/bottom${applicationScope.WEB_SUFFIX}"/></td>
    </tr>
</table>
</div>
</body>
</html>