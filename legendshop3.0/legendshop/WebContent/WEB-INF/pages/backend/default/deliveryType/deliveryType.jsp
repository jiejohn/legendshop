<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/options.tld" prefix="option" %>
<html>
    <head>
        <title>创建DeliveryType</title>
         <script type='text/javascript' src="<ls:templateResource item='/common/js/jquery.js'/>"></script>
         <script type='text/javascript' src="<ls:templateResource item='/common/js/jquery.validate.js'/>" /></script>
        <link rel="stylesheet" type="text/css" media="screen" href="<ls:templateResource item='/common/default/css/indexJpgForm.css'/>" />
        <style type="text/css" media="all">
          @import url(<ls:templateResource item='/common/default/css/screen.css'/>);
        </style>
        <script language="javascript">
		    $.validator.setDefaults({
		    });

    $(document).ready(function() {
    jQuery("#form1").validate({
        rules: {
        	name: {
                required: true,
            },
            dvyId: {
                required: true,
            }
        },
        messages: {
        	name: {
                required: '<fmt:message key="name.required"/>',
            },
            dvyId: {
                required: '<fmt:message key="deliverytype.dvyid.required"/>',
            }
        }
    });
 
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
</script>
</head>
    <body>
        <table class="${tableclass}" style="width: 100%">
		    <thead>
		    	<tr>
			    	<td>
				    	<a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商城管理  &raquo; 
				    	<a href="<ls:url address='/admin/deliveryType/query'/>">配送方式</a>			    	
			    	</td>
		    	</tr>
		    </thead>
	    </table>
        <form action="<ls:url address='/admin/deliveryType/save'/>" method="post" id="form1">
            <input id="dvyTypeId" name="dvyTypeId" value="${deliveryType.dvyTypeId}" type="hidden">
            <div align="center">
            <table border="0" align="center" class="${tableclass}" id="col1">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
							<c:choose>
			                	<c:when test="${not empty deliveryType}">修改配送方式</c:when>
			                	<c:otherwise>创建配送方式</c:otherwise>
			                </c:choose>				
                            </div>
                        </th>
                    </tr>
                </thead>
     
     <tr>
        <td>
          <div align="center">配送名称: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="name" id="name" value="${deliveryType.name}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">默认物流公司: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p>
           	<select id="dvyId" name="dvyId">
	            <option:optionGroup type="select" required="true" cache="fasle"
	                beanId="dvyId" beanDisp="name" defaultDisp="-- 物流公司 --" 
	                hql="select t.dvyId, t.name from DeliveryCorp t where t.userName = ?" param="${sessionScope.SPRING_SECURITY_LAST_USERNAME}"
	                selectedValue="${deliveryType.dvyId}"
	                />
			</select>
           
           
           </p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">描述: </div>
       </td>
        <td>
           <p><input type="text" name="notes" id="notes" value="${deliveryType.notes}" /></p>
        </td>
      </tr>

                <tr>
                    <td colspan="2">
                        <div align="center">
                            <input type="submit" value="保存" />
                            <input type="reset" value="重置" />
                            <input type="button" value="返回"
                                onclick="window.location='<ls:url address="/admin/deliveryType/query"/>'" />
                        </div>
                    </td>
                </tr>
            </table>
           </div>
        </form>
    </body>
</html>


