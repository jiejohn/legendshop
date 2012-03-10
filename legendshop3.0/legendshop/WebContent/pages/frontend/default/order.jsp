<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/pages/common/taglib.jsp'%>
<%@ taglib uri="/WEB-INF/tld/options.tld" prefix="option"%>
	<link href="${pageContext.request.contextPath}/common/css/css.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/common/style/style_${shopDetail.colorStyle}.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/common/style/global_${shopDetail.colorStyle}.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/dwr/engine.js" ></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/dwr/util.js" ></script>
	<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/CommonService.js'></script>
	<script src="${pageContext.request.contextPath}/common/js/jquery.js" type="text/javascript"></script>
	<style type="text/css" media="all">
       tr.tableRowEven,tr.even {
		background-color: #f5f5f5
	}
    </style>
<%
 Integer offset = (Integer) request.getAttribute("offset");
%>
<c:if test="${sessionScope.SPRING_SECURITY_LAST_USERNAME !=null}">
    <c:forEach items="${requestScope.USER_REG_ADV_950}" var="adv">
		<table width="100%" cellpadding="0" cellspacing="0" style="margin-bottom: 4px; margin-right: 5px;" class="picstyle">
			<tr><td><a href="${adv.linkUrl}"><img src="${pageContext.request.contextPath}/photoserver/photo/${adv.picUrl}" title="${adv.title}" width="100%"/></a></td></tr>
		 </table>
	</c:forEach>
	
	<table class="tables" width="100%" cellpadding="0" cellspacing="0">
	<form action="${pageContext.request.contextPath}/order${applicationScope.WEB_SUFFIX}" id="orderForm" name="orderForm" method="post">
      <tr>
        <td class="titlebgnormal" align="left" valign="middle">&nbsp;
         <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}"/>
         <input type="hidden" name="subCheck" id="subCheck" value="N"/>
        <a href="javascript:void(0)" onclick="changetab(1);"  id="processingbutton" name="processingbutton">
            <fmt:message key="order.processing"/>
        </a>
        <a href="javascript:void(0)" onclick="changetab(2);" id="processedbutton" name="processedbutton"><fmt:message key="order.processed"/></a>
        &nbsp;<fmt:message key="order.number"/>：<input type="text" value="${subForm.subNumber}" maxlength="30" name="subNumber" id="subNumber" class="input2"/>
               &nbsp; <fmt:message key="Order.Status"/>：
                	<select id="status" name="status" style="height: 1.50em;">
        				<option:optionGroup type="select" required="false" cache="true"
	                		beanName="ORDER_STATUS" selectedValue="${subForm.status}"/>
			     </select>
		<input type="submit" value="<fmt:message key="search"/>" class="s"/>
        </td>
      </tr>
       </form>
      <tr>
        <td valign="top" >
<table style="width: 100%" id="col1">
      <tr style="font-weight: bold" class="even">
        <td width="20px">&nbsp;</td>
        <td width="160px"><fmt:message key="order.number"/></td>
        <td width="50px"><fmt:message key="price.hint"/></td>
        <td><fmt:message key="product.hint"/></td>
        <c:if test="${'C2C' == applicationScope.BUSINESS_MODE}">
        	<td width="100px"><fmt:message key="shop.name"/></td>
        </c:if>
        <td width="120px"><fmt:message key="dateStr"/></td>
        <td width="100px"><fmt:message key="Order.Status"/></td>
        <td width="100px" height="25" align="center"><fmt:message key="operation"/></td>
      </tr>
	<c:forEach items="${requestScope.list}" var="order" varStatus="status">
      <tr>
        <td height="25">&nbsp;<%=offset++%>&nbsp;</td>
        <td height="25"><a href='${pageContext.request.contextPath}/orderDetail/${order.subNumber}${applicationScope.WEB_SUFFIX}' target="_blank">${order.subNumber}</a></td>
        <td height="25"><b><fmt:formatNumber type="currency" value="${order.total}" pattern="${CURRENCY_PATTERN}"/></b></td>
        <td height="25">${order.prodName}</td>
        <c:if test="${'C2C' == applicationScope.BUSINESS_MODE}">
        <td height="25"><a href="${pageContext.request.contextPath}/shop/${order.shopName}${applicationScope.WEB_SUFFIX}" target="_blank">${order.shopName}</a></td>
        </c:if>
        <td height="25"><fmt:formatDate value="${order.subDate}" pattern="yyyy-MM-dd HH:mm"/></td>
        <td height="25">
        		<option:optionGroup type="label" required="true" cache="true"
	                beanName="ORDER_STATUS" selectedValue="${order.status}"/>
        </td>
        <td height="25" align="center">
        <c:if test="${order.status == 1 or order.status == 7}">
        <a href='${pageContext.request.contextPath}/orderDetail/${order.subNumber}${applicationScope.WEB_SUFFIX}' target="_blank"><fmt:message key="payment.hint"/></a> 
        </c:if>
       
         <c:if test="${order.status == 3}"> <!-- 3:卖家已经发货 -->
             <!-- 4:交易成功 -->
         	<a href="javascript:void(0)" onclick='javascript:updateSubStatus("${order.subId}",4,"${order.subNumber}");'><fmt:message key="ensure.recieve"/></a>
         	 <!-- 6:退款中的订单 -->
            <a href="javascript:void(0)" onclick='javascript:updateSubStatus("${order.subId}",6,"${order.subNumber}");'><fmt:message key="apply.for.refundment"/></a>
         </c:if>
         <c:if test="${order.status == 6}"><!-- 6:退款中的订单 -->
         	 <!-- 5:交易关闭 -->
            <a href="javascript:void(0)" onclick='javascript:updateSubStatus("${order.subId}",5,"${order.subNumber}");'><fmt:message key="trade.close"/></a>
        </c:if>
       <!-- 用户不可删除，系统在30天后自动housekeep
        <c:if test="${order.status == 1 or order.status == 7}">
        	<a href='javascript:deleteSub("${order.subId}","${order.subNumber}");'><fmt:message key="delete"/></a>
        </c:if>
         -->
        </td>
      
      </tr>
      </c:forEach>
      </table>
        </td>
      </tr>
      <tr>     
        <td align="right" ><c:out value="${toolBar}" escapeXml="${toolBar}"></c:out></td>
      </tr>
    </table>
    <script type=text/javascript>

	  function submitOrderForm(subCheck){
            document.getElementById("subCheck").value= subCheck;
            document.getElementById("orderForm").submit();
        }
	
	function changetab(a){
		if(a==1){
			document.getElementById('processingbutton').className='selected';
			document.getElementById('processedbutton').className='';
			submitOrderForm("N");
		}else{
			document.getElementById('processedbutton').className='selected';
			document.getElementById('processingbutton').className='';
			submitOrderForm("Y");
		}
	}
	
 	
   function pager(curPageNO){
			document.getElementById("curPageNO").value=curPageNO;
			document.getElementById("orderForm").submit();
	}
        
    $(document).ready(function() {
	      $("#col1 tr").each(function(i){
	      if(i>0){
	         if(i%2 == 0){
	             $(this).addClass('even');
	         }else{    
	              $(this).addClass('odd'); 
	         }   
	    }
	     });   
	         $("#col1 th").addClass('sortable'); 
	});
	
function deleteSub(subId,subNumber) {
	  if(confirm('<fmt:message key="delete.order"/> '+subNumber+' ?')){
        CommonService.deleteSub(subId,'${sessionScope.SPRING_SECURITY_LAST_USERNAME}', function(retData){
        
	       if(retData == null ){
	          //alert('<fmt:message key="operation.successful"/>') ;
	          window.location.reload() ;
	       }else{
	          alert('<fmt:message key="entity.deleted"/>') ;
	       }
	       
	    }) ;
    }
}

     function updateSubStatus(subId,status,subNumber) {
	  if(confirm('<fmt:message key="confirm.order"/> '+subNumber+' ?')){
        CommonService.updateSub(subId,status,'${sessionScope.SPRING_SECURITY_LAST_USERNAME}', function(retData){
	       if(retData == null ){
	          //alert('<fmt:message key="operation.successful"/>') ;
	          window.location.reload() ;
	       }else{
	          alert('<fmt:message key="operation.fail"/>' + retData) ;
	       }
	       
	    }) ;
    }
}	

	
 	function onloadSetup(){
		if(${subForm.subCheck == 'Y'}){
			document.getElementById('processedbutton').className='selected';
			document.getElementById('processingbutton').className='';
			document.getElementById("subCheck").value= 'Y';
		}else{
			document.getElementById('processingbutton').className='selected';
			document.getElementById('processedbutton').className='';
			document.getElementById("subCheck").value= 'N';
		}
	}
	
	onloadSetup();
</script>
    </c:if>