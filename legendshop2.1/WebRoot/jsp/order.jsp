<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/common/jsp/taglib.jsp'%>
<script>
function changetab(a){
		if(a==1){
			document.getElementById('processingbutton').className='selected';
			document.getElementById('processedbutton').className='';
			document.getElementById('order').src="${root}/processOrder.do";
		}else{
			document.getElementById('processedbutton').className='selected';
			document.getElementById('processingbutton').className='';
			document.getElementById('order').src="${root}/processOrder.do?subCheck=True";
		}
	}
	
	function search(){
	var orderId = document.getElementById('orderId').value;
	if(orderId == null||orderId== ''){
	alert('<bean:message key="order.notnull"/>');
	return false;
	}
			if(document.getElementById('processedbutton').className=='selected'){
				document.getElementById('order').src="${root}/processOrder.do?subCheck=True&subNumber="+document.getElementById('orderId').value;
				}else{
				document.getElementById('order').src="${root}/processOrder.do?subNumber="+document.getElementById('orderId').value;
				}	
	}
	
</script>

<table width="740px" cellspacing="0" cellpadding="0" border="0" >
      <tr class="titlebg" >
        <td height="30" align="left">&nbsp;
        <a href="javascript:void(0)" onclick="changetab(1);" id="processingbutton" class="selected">
            <bean:message key="order.processing"/>
        </a>
        <a href="javascript:void(0)" onclick="changetab(2);" id="processedbutton" class="">
            <span><bean:message key="order.processed"/></span>
        </a>
        </td>
        <td align="right">
                <bean:message key="order.number"/>：<input type="text" value="" name="orderId" id="orderId" />
                <input type="submit" value="<bean:message key="search"/>" class="s" onclick="javascript:search();"/>
        </td>
      </tr>
      <tr>
        <td valign="top" colspan="2">
	      <iframe id="order" width="100%" height="600px" scrolling="no" frameborder="0" marginheight="0" marginwidth="0" src="${root}/processOrder.do"></iframe>
        </td>
      </tr>

    </table>