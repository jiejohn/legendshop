<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file='/pages/common/taglib.jsp'%>
<jsp:include flush="true" page="/top${applicationScope.WEB_SUFFIX}" />
<div id="bodyer">
<table width="954px" cellspacing="0" cellpadding="0" align="center" style="margin-top: 5px">
    <tr> 
      <td valign="top" align="left">  
         <jsp:include page="/topnews${applicationScope.WEB_SUFFIX}?topsortnews=1" flush="true"/>
         <jsp:include page="/topsort${applicationScope.WEB_SUFFIX}" flush="true" />
         <jsp:include page="/hoton${applicationScope.WEB_SUFFIX}?sortId=${sort.sortId}" flush="true"/>
        </td>
      <td valign="top" width="744px"><tiles2:insertAttribute name="right" ignore="true" /></td>
    </tr>
    <tr> 
      <td valign="top" colspan="2">
      	<%@ include file="copy.jsp"%></td>
    </tr>
</table>
</div>
</body>
</html>