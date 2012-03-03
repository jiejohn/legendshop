<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ include file="/pages/common/back-common.jsp"%>
<html>
    <head>
        <title>创建公告</title>
        <script src="${pageContext.request.contextPath}/common/js/jquery.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/common/js/jquery.validate.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/common/css/indexJpgForm.css" />
        <script language="javascript">
    $.validator.setDefaults({
    });

    $(document).ready(function() {
    jQuery("#form1").validate({
            rules: {
            title: {
                required: true,
                minlength: 2
            },
            msg: "required"
        },
        messages: {
            title: {
                required: "请输入标题",
                minlength: "请认真输入标题"
            },
            msg: {
                required: "请输入链接地址"
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
    <body class="bodymargin"> 
        <form action="${pageContext.request.contextPath}/admin/pub/save${applicationScope.WEB_SUFFIX}" method="post" id="form1">
            <input id="id" name="id" value="${bean.id}" type="hidden">
            <div align="center">
            <table class="${tableclass}" style="width: 100%">
		    <thead>
		    	<tr><td><a href="${pageContext.request.contextPath}/admin/index${applicationScope.WEB_SUFFIX}" target="_parent">首页</a> &raquo; 商城管理  &raquo; <a href="${pageContext.request.contextPath}/admin/pub/query${applicationScope.WEB_SUFFIX}">公告管理</a> &raquo; 创建公告</td></tr>
		    </thead>
		    </table>
            <table  align="center" class="${tableclass}" id="col1">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
                                创建公告
                            </div>
                        </th>
                    </tr>
                </thead>
      <tr>
        <td>
          <div align="right">标题 <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="title" id="title" value="${bean.title}" size="50"/></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="right">链接地址<font color="ff0000">*</font></div>
       </td>
        <td>
           <p>
            <input name="msg" id="msg" value="${bean.msg}" size="80"/>
           </p>
        </td>
      </tr>
      <tr>
                    <td colspan="2">
                        <div align="center">
                        <auth:auth ifAnyGranted="F_OPERATOR">
                            <input type="submit" value="提交" />
                        </auth:auth>
                            <input type="reset" value="重置" />
                            <input type="button" value="返回"
                                onclick="window.location='${pageContext.request.contextPath}/admin/pub/query${applicationScope.WEB_SUFFIX}'" />
                        </div>
                    </td>
                </tr>
            </table>
            </div>
        </form>
    </body>
</html>

