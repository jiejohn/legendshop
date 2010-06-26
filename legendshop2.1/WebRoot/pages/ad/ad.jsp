<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<html>
    <head>
        <title>创建 Ad</title>
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
            url: "required",
            wordlink: "required",
            content: "required",
            bs: "number"
        },
        messages: {
        	url: {
            required: "请输入链接地址"
        },
        wordlink: {
        	required: "请输入链接显示文字"
        },
        content: {
        	required: "请输入描述"
        },
        bs: {
			number: "显示顺序必须为数字"
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
        <form action="${root}/admin/ad/save.c" method="post" id="form1">
            <input id="id" name="id" value="${bean.id}" type="hidden">
            <div align="center">
            <table border="0" align="center" class="${tableclass}" id="col1">
                <thead>
                    <tr>
                        <th colspan="2">
                            <div align="center">创建友情链接</div>
                        </th>
                    </tr>
                </thead>
      <tr>
        <td>
          <div align="right">链接地址<font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="url" id="url" value="${bean.url}" size="50"/></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="right">链接显示文字<font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="wordlink" id="wordlink" value="${bean.wordlink}" size="50"/></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="right">描述<font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="content" id="content" value="${bean.content}" size="50"/></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="right">显示顺序（数字，越小越靠前）</div>
       </td>
        <td>
           <p><input type="text" name="bs" id="bs" value="${bean.bs}" /></p>
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
                                onclick="window.location='${root}/admin/ad/query.c'" />
                        </div>
                    </td>
                </tr>
            </table>
            </div>
        </form>
    </body>
</html>

