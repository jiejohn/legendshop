<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<html>
    <head>
        <title>创建广告</title>
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
            linkUrl: {
                required: true,
                minlength: 3
            }
        },
        messages: {
            linkUrl: {
                required: "请输入链接地址",
                minlength: "请认真填写，链接地址不能少于3个字符"
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
        <form action="${root}/admin/advertisement/save.c" method="post" id="form1" enctype="multipart/form-data">
            <input id="id" name="id" value="${bean.id}" type="hidden">
            <div align="center">
            <table border="0" align="center" class="${tableclass}" id="col1">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
                                创建广告
                            </div>
                        </th>
                    </tr>
                </thead>
      <tr>
        <td width="28%">
          <div align="right">广告类型: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p>
           <select id="type"name="type">
                <option value="COUPLET">对联广告</option>     
           </select>
           </p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="right">链接地址<font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="linkUrl" id="linkUrl" value="${bean.linkUrl}" size="50"/></p>
        </td>
      </tr>
            <tr>
                    <td>
                        <div align="right">
                            上传广告图片(对联广告只能上传一张，大小为110*300)
                            <font color="ff0000">*</font>
                        </div>
                    </td>
                    <td>
                        <p>
                            <input type="file" name="file" id="file" size="50"/>
                            <input type="hidden" name="picUrlName" id="picUrlName" size="80"
                                value="${bean.picUrl}" />
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
                                onclick="window.location='${root}/admin/advertisement/query.c'" />
                        </div>
                    </td>
                </tr>
            </table>
           </div>
        </form>
    </body>
</html>

