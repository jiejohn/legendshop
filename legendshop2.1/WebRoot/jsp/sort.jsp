<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file='/common/jsp/taglib.jsp'%>
<jsp:include flush="true" page="/top.do" />
<div id="bodyer">
<table width="950px" cellspacing="0" cellpadding="0" align="center" style="margin-top: 5px">
    <tr> 
      <td valign="top" align="left">  
         <jsp:include page="/topnews.do?topsortnews=1" flush="true"/>
         <jsp:include page="/topsort.do" flush="true" />
         <jsp:include page="/hoton.do?sortId=${sort.sortId}" flush="true"/>
        </td>
      <td valign="top" width="740px"><tiles:insert attribute="right"/></td>
    </tr>
    <tr> 
      <td valign="top" colspan="2">
      	<%@ include file="copy.jsp"%></td>
    </tr>
</table>
</div>
</body>
</html>