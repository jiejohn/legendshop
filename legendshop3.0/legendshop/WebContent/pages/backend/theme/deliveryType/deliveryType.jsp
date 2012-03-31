<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ include file="/pages/common/back-common.jsp"%>
<%@ include file="/pages/common/taglib.jsp"%>
<html>
    <head>
        <title>创建DeliveryType</title>
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
        <form action="<ls:url address='/admin/deliveryType/save'/>" method="post" id="form1">
            <input id="dvyTypeId" name="dvyTypeId" value="${deliveryType.dvyTypeId}" type="hidden">
            <div align="center">
            <table border="0" align="center" class="${tableclass}" id="col1">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
                                创建DeliveryType
                            </div>
                        </th>
                    </tr>
                </thead>
     			     <tr>
        <td>
          <div align="center">UserId: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="userId" id="userId" value="${deliveryType.userId}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">UserName: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="userName" id="userName" value="${deliveryType.userName}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">DvyId: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="dvyId" id="dvyId" value="${deliveryType.dvyId}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">Type: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="type" id="type" value="${deliveryType.type}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">Name: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="name" id="name" value="${deliveryType.name}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">Desc: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="desc" id="desc" value="${deliveryType.desc}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">CreateTime: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="createTime" id="createTime" value="${deliveryType.createTime}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">ModifyTime: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="modifyTime" id="modifyTime" value="${deliveryType.modifyTime}" /></p>
        </td>
      </tr>

                <tr>
                    <td colspan="2">
                        <div align="center">
                            <input type="submit" value="添加" />
                            <input type="reset" value="重置" />
                            <input type="button" value="返回"
                                onclick="window.location='<ls:url address="/admin/deliveryType/query"/>'" />
                        </div>
                    </td>
                </tr>
            </table>
           </div>
        </form>
    </body>
</html>


