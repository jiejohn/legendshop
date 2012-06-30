<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/options.tld" prefix="option"%>
<html>
    <head>
        <title>修改供应商密码</title>
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
		                password: {
		                    required: true,
		                },
		                passwordag: {
		                    required: true,
		    		        equalTo:"#password"
		                }
		            },
		            messages: {
		                password: {
		                    required: '<fmt:message key="password.required"/>',
		                },
		                passwordag: {
		                    required: '<fmt:message key="password.required"/>',
		                    equalTo: '<fmt:message key="password.equalTo"/>'
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
			    	<a href="<ls:url address='/admin/partner/query'/>">供应商管理</a>
		    	</td>
	    	</tr>
	    </thead>
    </table>
        <form action="<ls:url address='/admin/partner/savePassword'/>" method="post" id="form1">
            <input id="partnerId" name="partnerId" value="${partner.partnerId}" type="hidden">
            <div align="center">
            <table border="0" align="center" class="${tableclass}" id="col1">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">修改供应商密码</div>
                        </th>
                    </tr>
                </thead>
     			     <tr>
        <td>
          <div align="center">供应商登录名: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p>${partner.partnerName}</p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">登录密码: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="password" name="password" id="password" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">确认密码: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="password" name="passwordag" id="passwordag" /></p>
        </td>
      </tr>
                <tr>
                    <td colspan="2">
                        <div align="center">
                            <input type="submit" value="保存" />
                            <input type="reset" value="重置" />
                            <input type="button" value="返回"
                                onclick="window.location='<ls:url address="/admin/partner/query"/>'" />
                        </div>
                    </td>
                </tr>
            </table>
           </div>
        </form>
    </body>
</html>


