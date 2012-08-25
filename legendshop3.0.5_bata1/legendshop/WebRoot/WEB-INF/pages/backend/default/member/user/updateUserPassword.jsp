<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
<html>
<head>
        <script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/common/js/jquery.validate.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/common/default/css/indexJpgForm.css" />
<title>修改密码</title>

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
            brandName: {
                required: "请输入商品品牌名称"
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
      <form  action="${pageContext.request.contextPath}/admin/member/user/updatePassword" id="form1" method="post">
      
        <table class="${tableclass}" style="width: 100%">
	    <thead>
	    	<tr><td><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 用户管理  &raquo; <a href="${pageContext.request.contextPath}/admin/member/user/query">权限用户管理</a> &raquo; 修改用户</td></tr>
	    </thead>
	    </table>
      
       <input type="hidden" name="id" value="${bean.id }">
       
       
            <div align="center">
            <table  align="center" class="${tableclass}" id="col1">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
                               修改用户
                            </div>
                        </th>
                    </tr>
                </thead>
      <tr>
        <td>
          <div align="center">名称 <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="name" id="name" value="${bean.name}" maxlength="30"            
        <c:if test="${not empty bean}">
            <c:out value="readonly=\"true\""></c:out>
        </c:if>  
           /></p>
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
           <td colspan="2">
               <div align="center">
    				<auth:auth ifAnyGranted="F_OPERATOR"><input type="submit" value="提交" /></auth:auth>
                   	<input type="reset" value="重置" />
                   	<input type="button" value="返回"
                       onclick="window.location='${pageContext.request.contextPath}/admin/member/user/query'" />
               </div>
           </td>
       </tr>
   </table>
  </div>
       
      </form>
</body>
</html>

