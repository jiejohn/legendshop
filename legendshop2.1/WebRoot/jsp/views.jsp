<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include flush="true" page="/top.do"></jsp:include>
<div id="bodyer">
<table width="950px" border=0 align="center" cellpadding="0" cellspacing="0" style="margin-top: 5px">
  <tr> 
    <td valign="top"> 
            <jsp:include page="/topnews.do?topsortnews=1" flush="true"/>
            <jsp:include flush="true" page="/topsort.do"></jsp:include>
            <jsp:include flush="true" page="/hoton.do?sortId=${param.sortId}"></jsp:include>
    </td>
    <td valign="top" width="740px">
     <%@ include file="productDetail.jsp"%>
    </td> 
  </tr>
</table>
</div>
<%@ include file="copy.jsp"%>