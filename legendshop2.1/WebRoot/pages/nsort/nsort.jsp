<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<html>
    <head>
        <title>创建 Nsort</title>
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
            nsortName: "required",
            seq: {
              number:true
            }
        },
        messages: {
            nsortName: {
                required: "请输入分类名称"
            },
            seq: {
                number: "请输入数字"
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
        <form action="${root}/admin/nsort/save.c" method="post" id="form1">
            <input id="nsortId" name="nsortId" value="${bean.nsortId}" type="hidden">
            <input id="sortId" name="sortId" value="${param.sortId}" type="hidden">
            <input id="parentNsortId" name="parentNsortId" value="${param.parentNsortId}" type="hidden">
            <div align="center">
            <table border="0" align="center" class="${tableclass}" id="col1">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
                                创建分类
                            </div>
                        </th>
                    </tr>
                </thead>
      <tr>
        <td>
          <div align="center">分类名称: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="nsortName" id="nsortName" value="${bean.nsortName}" /></p>
        </td>
      </tr>
      <tr>
        <td>
          <div align="center">排序(必须为数字)：</div>
       </td>
        <td>
           <p><input type="text" name="seq" id="seq" value="${bean.seq}" /></p>
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
                                onclick="window.location='${root}/admin/sortList.do'" />
                        </div>
                    </td>
                </tr>
            </table>
           </div>
        </form>
    </body>
</html>

