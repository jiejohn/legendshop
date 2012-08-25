<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html>
    <head>
        <title>创建Tag</title>
         <script type='text/javascript' src="<ls:templateResource item='/common/js/jquery.js'/>"></script>
         <script type='text/javascript' src="<ls:templateResource item='/common/js/jquery.validate.js'/>" /></script>
        <link rel="stylesheet" type="text/css" media="screen" href="<ls:templateResource item='/common/css/indexJpgForm.css'/>" />
        <style type="text/css" media="all">
          @import url(<ls:templateResource item='/css/screen.css'/>);
        </style>
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
        <form action="<ls:url address='/admin/tag/save'/>" method="post" id="form1">
            <input id="tagId" name="tagId" value="${tag.tagId}" type="hidden">
            <div align="center">
            <table border="0" align="center" class="${tableclass}" id="col1">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
                                创建Tag
                            </div>
                        </th>
                    </tr>
                </thead>
     			     <tr>
        <td>
          <div align="center">Name: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="name" id="name" value="${tag.name}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">SortId: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="sortId" id="sortId" value="${tag.sortId}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">NewsCategoryId: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="newsCategoryId" id="newsCategoryId" value="${tag.newsCategoryId}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">Type: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="type" id="type" value="${tag.type}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">Status: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="status" id="status" value="${tag.status}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">CreateTime: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="createTime" id="createTime" value="${tag.createTime}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">UserId: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="userId" id="userId" value="${tag.userId}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">UserName: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="userName" id="userName" value="${tag.userName}" /></p>
        </td>
      </tr>

                <tr>
                    <td colspan="2">
                        <div align="center">
                            <input type="submit" value="添加" />
                            <input type="reset" value="重置" />
                            <input type="button" value="返回"
                                onclick="window.location='<ls:url address="/admin/tag/query"/>'" />
                        </div>
                    </td>
                </tr>
            </table>
           </div>
        </form>
    </body>
</html>


