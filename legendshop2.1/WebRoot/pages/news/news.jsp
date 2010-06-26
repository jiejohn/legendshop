<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<html>
    <head>
        <title>创建 News</title>
        <%session.setAttribute("root", request.getContextPath());%>
        <script src="${root}/common/js/jquery.js" type="text/javascript"></script>
        <script src="${root}/common/js/jquery.validate.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="${root}/common/css/indexJpgForm.css" />
        <style type="text/css" media="all">
          @import url("${root}/css/screen.css");
        </style>
        <jsp:scriptlet> String lClass = "its";
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
            banner: {
                required: true,
                minlength: 5
            },
            url: "required"
        },
        messages: {
            banner: {
                required: "Please enter banner",
                minlength: "banner must consist of at least 5 characters"
            },
            url: {
                required: "Please provide a password"
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
        <form action="${root}/admin/news/save.c" method="post" id="form1">
            <input id="newsId" name="newsId" value="${bean.newsId}" type="hidden">
            <div align="center">
            <table border="0" align="center" class="${tableclass}" id="col1">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
                                创建 News
                            </div>
                        </th>
                    </tr>
                </thead>
      <tr>
        <td>
          <div align="center">NewsTitle: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="newsTitle" id="newsTitle" value="${bean.newsTitle}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">NewsContent: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="newsContent" id="newsContent" value="${bean.newsContent}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">NewsDate: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="newsDate" id="newsDate" value="${bean.newsDate}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">UserId: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="userId" id="userId" value="${bean.userId}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">UserName: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="userName" id="userName" value="${bean.userName}" /></p>
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
                                onclick="window.location='${root}/admin/news/query.c'" />
                        </div>
                    </td>
                </tr>
            </table>
            </div>
        </form>
    </body>
</html>

