<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<html>
    <head>
        <title>创建热点产品</title>
        <%session.setAttribute("root", request.getContextPath());%>
        <script src="${root}/common/js/jquery.js" type="text/javascript"></script>
        <script src="${root}/common/js/jquery.validate.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="${root}/common/css/indexJpgForm.css" />
        <style type="text/css" media="all">
          @import url("${root}/css/screen.css");
        </style>
        <jsp:scriptlet> String lClass = "mars";
           if( request.getParameter( "class" ) != null ) {
              lClass = request.getParameter( "class" );
              if (!("isis".equals(lClass) || "its".equals(lClass) || "mars".equals(lClass) || "simple".equals(lClass) || "report".equals(lClass) || "mark".equals(lClass)))
              {
                lClass="";
              }
           }
           pageContext.setAttribute("tableclass", lClass);
        </jsp:scriptlet>
        <script language="javascript">
    $.validator.setDefaults({
    });

    $(document).ready(function() {
    jQuery("#form1").validate({
            rules: {
    	    title: {
                required: true,
                minlength: 5
            },
            msg: "required"
        },
        messages: {
        	title: {
                required: "请输入标题",
                minlength: "请认真输入标题，不能少于5个字符"
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
    <body>
        <form action="${root}/admin/hotsearch/save.c" method="post" id="form1">
            <input id="id" name="id" value="${bean.id}" type="hidden">
            <div align="center">
            <table border="0" align="center" class="${tableclass}" id="col1">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
                                创建热点产品
                            </div>
                        </th>
                    </tr>
                </thead>
      <tr>
        <td>
          <div align="right">标题<font color="ff0000">*</font></div>
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
           <p><input type="text" name="msg" id="msg" value="${bean.msg}" size="50"/></p>
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
                                onclick="window.location='${root}/admin/hotsearch/query.c'" />
                        </div>
                    </td>
                </tr>
            </table>
           </div>
        </form>
    </body>
</html>

