<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/back-common.jsp"%>
<%@ include file="/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
<html>
<head>

        <script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/common/js/jquery.validate.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/common/css/default/indexJpgForm.css" />
<title>创建用户</title>

   <script language="javascript">
    $.validator.setDefaults({
    });

    $(document).ready(function() {
    jQuery("#form1").validate({
        rules: {
            name: {
                required: true
            },
            password: {
		        required: true
		    },
		    passwordag: {
		        required: true,
		        equalTo:"#password" 
		    }
        },
        messages: {
        	 name: {
                 required: '<fmt:message key="username.required"/>',
                 minlength: '<fmt:message key="username.minlength"/>'
             },
             password: {
                 required: '<fmt:message key="password.required"/>',
                 minlength: '<fmt:message key="password.minlength"/>'
             },
             passwordag: {
                 required: '<fmt:message key="password.required"/>',
                 minlength: '<fmt:message key="password.minlength"/>',
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

	// check if confirm password is still valid after password changed
	jQuery("#password").blur(function() {
		jQuery("#passwordag").valid();
	})
</script>
</head>


      <form  action="${pageContext.request.contextPath}/member/user/save${applicationScope.WEB_SUFFIX}" id="form1" method="post">
      
        <table class="${tableclass}" style="width: 100%">
	    <thead>
	    	<tr><td><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 用户管理  &raquo; <a href="${pageContext.request.contextPath}/member/user/query${applicationScope.WEB_SUFFIX}">权限用户管理</a> &raquo; 创建用户</td></tr>
	    </thead>
	    </table>
            <div align="center">
            <table  align="center" class="${tableclass}" id="col1">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
                               创建用户
                            </div>
                        </th>
                    </tr>
                </thead>
      <tr>
        <td>
          <div align="center">名称 <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="name" id="name" value="" maxlength="30" /></p>
        </td>
      </tr>
      <tr>
        <td>
          <div align="center">密码 <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="password" name="password" id="password" value="" maxlength="30"/></p>
        </td>
      </tr>
      <tr>
        <td>
          <div align="center">确认密码 <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="password" name="passwordag" id="passwordag" value="" maxlength="30"/></p>
        </td>
      </tr>

      <tr>
        <td>
          <div align="center">状态</div>
       </td>
        <td>
           <p>           
          <label>
            <input type="radio" name="enabled" value="1"  checked="checked" />
            有效</label> &nbsp; &nbsp; 
          <label>
            <input type="radio" name="enabled" value="0"
        <c:if test="${bean.enabled eq 0}">
            checked="checked"
        </c:if>  
            />
            无效</label>
           </p>
        </td>
      </tr>
      <tr>
        <td>
          <div align="center">注释</div>
       </td>
        <td>
           <p><input type="text" name="note" id="note" value=""/></p>
        </td>
      </tr>
       <tr>
           <td colspan="2">
               <div align="center">
    				<auth:auth ifAnyGranted="F_OPERATOR"><input type="submit" value="提交" /></auth:auth>
                   	<input type="reset" value="重置" />
                   	<input type="button" value="返回"
                       onclick="window.location='${pageContext.request.contextPath}/member/user/query${applicationScope.WEB_SUFFIX}'" />
               </div>
           </td>
       </tr>
   </table>
  </div>
       
      </form>


</html>