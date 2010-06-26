<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/common/jsp/common.jsp"%>
<%@ include file="/common/jsp/taglib.jsp"%> 
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%
 Integer offset = (Integer) request.getAttribute("offset");
%>
<link href="${root}/common/css/css.css" rel="stylesheet" type="text/css" />
<script>
  function openbag(subNumber,userName) {
   window.open("${root}/admin/adminOrderDetail.do?subNumber="+subNumber+"&userName="+userName,"","height=420,width=530,left=190,top=0,resizable=yes,scrollbars=yes,status=no,toolbar=no,menubar=no,location=no");
   } 
</script>
    <link href="${root}/common/style/style_${shopDetail.colorStyle}.css" rel="stylesheet" type="text/css" />
    <link href="${root}/common/style/global_${shopDetail.colorStyle}.css" rel="stylesheet" type="text/css" />
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="bodybg">
      <tr>     
        <td colspan="7" align="right" class="bodybg"><c:out value="${toolBar}" escapeXml="${toolBar}"></c:out></td>
      </tr>
      <tr>
        <td width="2%" height="25">&nbsp;</td>
        <td width="18%"  height="25" >已经处理订单号</td>
        <td width="10%"  height="25">用户</td>
        <td width="10%"  height="25">订货人</td>
        <td width="28%"  height="25">地址</td>
        <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
            <td width="10%"  height="25">店铺</td>
        </auth:auth>
        <td width="20%"  height="25">日期</td>
        <td width="8%" height="25">&nbsp;</td>
      </tr>
	<c:forEach items="${requestScope.processOrderList}" var="order" varStatus="status">
      <tr> 
        <td  height="25">&nbsp;<%=offset++%>.&nbsp;</td>
        <td  height="25"><a href='javascript:openbag("${order.subNumber}","${order.userName}")'>${order.subNumber}</a></td>
        <td  height="25">${order.userName}</td>
        <td  height="25">${order.userName}</td>
        <td  height="25">${order.subAdds}</td>
        <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
            <td  height="25">${order.shopName}</td>
        </auth:auth>
        <td  height="25"><fmt:formatDate value="${order.subDate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
        <td  height="25">&nbsp;</td>
      </tr>
      </c:forEach>
      <tr>     
        <td colspan="7" align="right" class="bodybg"><c:out value="${toolBar}" escapeXml="${toolBar}"></c:out></td>
      </tr>
    </table>