<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/common/jsp/taglib.jsp'%>
<%@include file="/common/jsp/common.jsp"%>
<LINK title=Style href="${root}/common/css/back_style.css" type=text/css rel=stylesheet>
<script>
function changetab(a){
		if(a==1){
			document.getElementById('processingbutton').className='selected';
			document.getElementById('processedbutton').className='';
			document.getElementById('order').src="${root}/admin/adminProcessOrder.do";
		}else{
			document.getElementById('processedbutton').className='selected';
			document.getElementById('processingbutton').className='';
			document.getElementById('order').src="${root}/admin/adminProcessOrder.do?subCheck=True";
		}
	}
	
	function search(){
			var orderId = document.getElementById('orderId').value;
			var userName = document.getElementById('userName').value;
			/*
			if((orderId == null||orderId== '')||(userName == null||userName== '')){
				alert("参数不能为空");
				return false;
			}
			*/
			var srcStr = "${root}/admin/adminProcessOrder.do?";
			if(document.getElementById('processedbutton').className=='selected'){
				srcStr =srcStr+"subCheck=True&subNumber="+orderId+"&userName="+userName;
				}else{
				srcStr =srcStr+"subNumber="+orderId+"&userName="+userName;
				}	
			document.getElementById('order').src = srcStr;
	}
</script>
<table width="100%" cellspacing="0" cellpadding="0" border="0" >
      <tr>
        <td class="headerbg" height="24" align="left">&nbsp;
        <a href="javascript:void(0)" onclick="changetab(1);" id="processingbutton" class="selected">未处理订单</a>
        <a href="javascript:void(0)" onclick="changetab(2);" id="processedbutton" class=""><span>已处理订单</span></a>

        </td>
        <td class="headerbg" align="right">
                订单号：<input type="text" value="" name="orderId" id="orderId" />
                用户名：<input type="text" value="" name="userName" id="userName" />
        </td>
        <td class="headerbg" align="left">
        <input type="submit" value="搜索" class="s" onclick="javascript:search();"/>
        </td>
      </tr>
      <tr>
        <td valign="top" colspan="3" width="100%">
	      <iframe id="order" width="100%" height="500" frameborder="0" marginheight="0" marginwidth="0" src="${root}/admin/adminProcessOrder.do"></iframe>
        </td>
      </tr>

    </table>