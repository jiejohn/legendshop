<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html>
    <head>
        <title>创建 NewsCategory</title>
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
        <form action="${root}/admin/newsCategory/save.c" method="post" id="form1">
            <input id="newsCategoryId" name="newsCategoryId" value="${bean.newsCategoryId}" type="hidden">
            <div align="center">
            <table border="0" align="center" class="${tableclass}" id="col1">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
                                创建 NewsCategory
                            </div>
                        </th>
                    </tr>
                </thead>
      <tr>
        <td>
          <div align="center">NewsCategoryName: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="newsCategoryName" id="newsCategoryName" value="${bean.newsCategoryName}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">Status: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="status" id="status" value="${bean.status}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">NewsCategoryDate: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="newsCategoryDate" id="newsCategoryDate" value="${bean.newsCategoryDate}" /></p>
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
                            <input type="submit" value="添加" />
                            <input type="reset" value="重置" />
                            <input type="button" value="返回"
                                onclick="window.location='${root}/admin/newsCategory/query.c'" />
                        </div>
                    </td>
                </tr>
            </table>
           </div>
        </form>
    </body>
</html>

