<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<jsp:directive.page import="com.done.util.UserManagement"/>
<%
session.setAttribute("userName",UserManagement.getUsername());
 Integer offset = (Integer) request.getAttribute("offset");
%>
<%@include file='/common/jsp/taglib.jsp'%>
<link href="${root}/common/css/css.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${root}/dwr/engine.js" ></script>
	<script type="text/javascript" src="${root}/dwr/util.js" ></script>
	<script type='text/javascript' src='${root}/dwr/interface/CommonService.js'></script>
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
<table border="0" width="740" cellspacing="0" cellpadding="0" bgcolor="#ECECEC">
      <tr>     
        <td colspan="7" align="right"><c:out value="${toolBar}" escapeXml="${toolBar}"></c:out></td>
      </tr>
      <tr>
        <td width="2%" height="25">&nbsp;</td>
        <td width="20%"  height="25" ><bean:message key="order.processing"/></td>
        <td width="10%"  height="25"><bean:message key="orderer"/></td>
        <td width="25%"  height="25"><bean:message key="address"/></td>
        <td width="10%"  height="25"><bean:message key="shop.name"/></td>
        <td width="20%"  height="25"><bean:message key="dateStr"/></td>
        <td width="13%" height="25"><bean:message key="operation"/></td>
      </tr>
	<c:forEach items="${requestScope.processOrderList}" var="order" varStatus="status">
      <tr>
        <td height="25">&nbsp;<%=offset++%>.&nbsp;</td>
        <td  height="25"><a href='javascript:openbag("${order.subNumber}")'>${order.subNumber}</a></td>
        <td  height="25">${order.orderName}</td>
        <td  height="25">${order.subAdds}</td>
        <td  height="25">${order.shopName}</td>
        <td  height="25"><fmt:formatDate value="${order.subDate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
        <td  height="25">
        <a href='javascript:deleteSub("${order.subId}","${order.subNumber}");'><bean:message key="delete"/></a></td>
      </tr>
      </c:forEach>
     <tr>     
        <td colspan="7" align="right" ><c:out value="${toolBar}" escapeXml="${toolBar}"></c:out></td>
      </tr>
    </table>
    </form>
    <script>
     function deleteSub(subId,subNumber) {
	  if(confirm('<bean:message key="delete.order"/> '+subNumber+' ?')){
        CommonService.deleteSub(subId,'${userName}', function(retData){
        
	       if(retData == null ){
	          alert('<bean:message key="operation.successful"/>') ;
	          window.location.reload() ;
	       }else{
	          alert('<bean:message key="entity.deleted"/>') ;
	       }
	       
	    }) ;
    }
}
</script>