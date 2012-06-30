<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ include file="/pages/common/back-common.jsp"%>
<%@ include file="/pages/common/taglib.jsp"%>
<html>
    <head>
        <title>创建物流公司</title>
         <script type='text/javascript' src="<ls:templateResource item='/common/js/jquery.js'/>"></script>
         <script type='text/javascript' src="<ls:templateResource item='/common/js/jquery.validate.js'/>" /></script>
        <link rel="stylesheet" type="text/css" media="screen" href="<ls:templateResource item='/common/css/default/indexJpgForm.css'/>" />
        <style type="text/css" media="all">
          @import url(<ls:templateResource item='/common/css/default/screen.css'/>);
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
        	url: {
                required: true,
            }
        },
        messages: {
        	name: {
                required: '<fmt:message key="deliverycorp.name.required"/>',
            },
        	url: {
                required: '<fmt:message key="deliverycorp.url.required"/>',
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
			    	<a href="<ls:url address='/admin/deliveryCorp/query'/>">物流公司</a>
		    	</td>
	    	</tr>
	    </thead>
    </table>
	<form action="<ls:url address='/admin/deliveryCorp/save'/>"
		method="post" id="form1">
		<input id="dvyId" name="dvyId" value="${deliveryCorp.dvyId}"
			type="hidden">
		<div align="center">
			<table border="0" align="center" class="${tableclass}" id="col1">
				<thead>
					<tr class="sortable">
						<th colspan="2">
							<div align="center">
							<c:choose>
			                	<c:when test="${not empty deliveryCorp}">修改物流公司</c:when>
			                	<c:otherwise>创建物流公司</c:otherwise>
			                </c:choose>							
							</div>
						</th>
					</tr>
				</thead>
				<tr>
					<td>
						<div align="center">
							物流公司名称: <font color="ff0000">*</font>
						</div>
					</td>
					<td>
						<p>
							<input type="text" name="name" id="name"
								value="${deliveryCorp.name}" />
						</p>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							URL: <font color="ff0000">*</font>
						</div>
					</td>
					<td>
						<p>
							<input type="text" name="url" id="url"
								value="${deliveryCorp.url}" />
						</p>
					</td>
				</tr>

				<tr>
					<td colspan="2">
						<div align="center">
							<input type="submit" value="保存" /> <input type="reset"
								value="重置" /> <input type="button" value="返回"
								onclick="window.location='<ls:url address="/admin/deliveryCorp/query"/>'" />
						</div>
					</td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>

