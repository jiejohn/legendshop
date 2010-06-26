<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/common/jsp/taglib.jsp'%>   
<%
 Integer offset = (Integer) request.getAttribute("offset");
%>
<link href="${root}/common/css/css.css" rel="stylesheet" type="text/css" />
<script>
  function openbag(subNumber) {
	   window.open("${root}/orderDetail.do?subNumber="+subNumber,"","height=420,width=530,left=190,top=0,resizable=yes,scrollbars=yes,status=no,toolbar=no,menubar=no,location=no");
   } 
 function pager(curPageNO){
            document.getElementById("curPageNO").value=curPageNO;
            document.getElementById("form1").submit();
        }
</script>
    <link href="${root}/common/style/style_${shopDetail.colorStyle}.css" rel="stylesheet" type="text/css" />
    <link href="${root}/common/style/global_${shopDetail.colorStyle}.css" rel="stylesheet" type="text/css" />
<form action="${root}/processOrder.do" id="form1">
<input type="hidden" id="curPageNO" name="curPageNO" value="${hw.curPageNO}"/>
<input type="hidden" id="subCheck" name="subCheck" value="true"/>
<table width="740" border="0" cellspacing="0" cellpadding="0" bgcolor="#ECECEC">
      <tr>     
        <td colspan="6" align="right"><c:out value="${toolBar}" escapeXml="${toolBar}"></c:out></td>
      </tr>
      <tr>
        <td width="2%" height="25">&nbsp;</td>
        <td width="20%"  height="25" ><bean:message key="order.processed"/></td>
        <td width="15%"  height="25"><bean:message key="orderer"/></td>
        <td width="28%"  height="25"><bean:message key="address"/></td>
        <td width="10%"  height="25"><bean:message key="shop.name"/></td>
        <td width="20%"  height="25"><bean:message key="dateStr"/></td>
      </tr>
	<c:forEach items="${requestScope.processOrderList}" var="order" varStatus="status">
      <tr> 
        <td  height="25">&nbsp;<%=offset++%>.&nbsp;</td>
        <td  height="25"><a href="javascript:openbag('${order.subNumber}')">${order.subNumber}</a>　</td>
        <td  height="25">${order.orderName}</td>
        <td  height="25">${order.subAdds}</td>
        <td  height="25">${order.shopName}</td>
        <td  height="25"><fmt:formatDate value="${order.subDate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
      </tr>
   </c:forEach>
      <tr>     
        <td colspan="6" align="right"><c:out value="${toolBar}" escapeXml="${toolBar}"></c:out></td>
      </tr>
	
    </table>
   </form>