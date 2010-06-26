<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<jsp:directive.page import="com.done.util.UserManagement"/>
<%
 session.setAttribute("userName",UserManagement.getUsername());
 Integer offset = (Integer) request.getAttribute("offset");
%>
<%@ include file="/common/jsp/common.jsp"%>
<%@ include file="/common/jsp/taglib.jsp"%> 
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<link href="${root}/common/css/css.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${root}/dwr/engine.js" ></script>
	<script type="text/javascript" src="${root}/dwr/util.js" ></script>
	<script type='text/javascript' src='${root}/dwr/interface/CommonService.js'></script>
<script>
  function openbag(subNumber,userName) {
   	window.open("${root}/admin/adminOrderDetail.do?subNumber="+subNumber+"&userName="+userName,"","height=420,width=530,left=190,top=0,resizable=yes,scrollbars=yes,status=no,toolbar=no,menubar=no,location=no");
   } 
</script>
    <link href="${root}/common/style/style_${shopDetail.colorStyle}.css" rel="stylesheet" type="text/css" />
    <link href="${root}/common/style/global_${shopDetail.colorStyle}.css" rel="stylesheet" type="text/css" />
<table border="0" width="100%" cellspacing="0" cellpadding="0" class="bodybg">
      <tr>     
        <td colspan="8" align="right" class="bodybg"><c:out value="${toolBar}" escapeXml="${toolBar}"></c:out></td>
      </tr>
      <tr>
        <td width="2%" height="25">&nbsp;</td>
        <td width="18%"  height="25" >正在处理订单号</td>
        <td width="10%"  height="25">用户</td>
        <td width="10%"  height="25">订货人</td>
        <td width="28%"  height="25">地址</td>
        <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
            <td width="10%"  height="25">店铺</td>
        </auth:auth>
        <td width="15%"  height="25">日期</td>
        <td width="17%" height="25">操作</td>
      </tr>
	<c:forEach items="${requestScope.processOrderList}" var="order" varStatus="status">
      <tr>
        <td height="25">&nbsp;<%=offset++%>.&nbsp;</td>
        <td  height="25"><a href='javascript:openbag("${order.subNumber}","${order.userName}")'>${order.subNumber}</a></td>
        <td  height="25">${order.userName}</td>
        <td  height="25">${order.orderName}</td>
        <td  height="25">${order.subAdds}</td>
        <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
        <td  height="25">${order.shopName}</td>
        </auth:auth>
        <td  height="25"><fmt:formatDate value="${order.subDate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
        <td  height="25">
        <%-- 
        <a href='javascript:openbag("${order.subNumber}","${order.userName}")'>详情</a>
        --%>
        <auth:auth ifAnyGranted="F_OPERATOR">
            <a href='javascript:processSub("${order.subId}","${order.subNumber}")'>处理</a>
        </auth:auth>
        <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
            <a href='javascript:deleteSub("${order.subId}","${order.subNumber}");'>删除</a>
        </auth:auth>
        </td>
        
      </tr>
      </c:forEach>
     <tr>
        <td colspan="8" align="right" class="bodybg"><c:out value="${toolBar}" escapeXml="${toolBar}"></c:out></td>
      </tr>
    </table>
    <script>
     function deleteSub(subId,subNumber) {
	  if(confirm('确定删除订单 '+subNumber+' ?')){
        CommonService.adminDeleteSub(subId,'${userName}', function(retData){
        
	       if(retData == null ){
	          alert("成功删除该纪录！") ;
	          window.location.reload() ;
	       }else{
	          alert("删除失败！请确认改订单的状态和所有人。") ;
	       }
	       
	    }) ;
    }
}
     function processSub(subId,subNumber) {
	  if(confirm('确定处理订单 '+subNumber+' ?')){
        CommonService.processSub(subId,'${userName}', function(retData){
        
	       if(retData == null ){
	          alert("成功处理该纪录！") ;
	          window.location.reload() ;
	       }else{
	          alert("处理失败！请确认改订单的状态和所有人。 " ) ;
	       }
	       
	    }) ;
    }
}
</script>