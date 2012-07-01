<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html>
    <head>
        <title>创建UserAddress</title>
         <script type='text/javascript' src="<ls:templateResource item='/common/js/jquery.js'/>"></script>
         <script type='text/javascript' src="<ls:templateResource item='/common/js/jquery.validate.js'/>" /></script>
        <link rel="stylesheet" type="text/css" media="screen" href="<ls:templateResource item='/common/default/css/indexJpgForm.css'/>" />
        <style type="text/css" media="all">
          @import url(<ls:templateResource item='/common/default/css/screen.css'/>);
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
        <form action="<ls:url address='/admin/userAddress/save'/>" method="post" id="form1">
            <input id="addrId" name="addrId" value="${userAddress.addrId}" type="hidden">
            <div align="center">
            <table border="0" align="center" class="${tableclass}" id="col1">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
                                创建UserAddress
                            </div>
                        </th>
                    </tr>
                </thead>
     			     <tr>
        <td>
          <div align="center">UserId: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="userId" id="userId" value="${userAddress.userId}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">UserName: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="userName" id="userName" value="${userAddress.userName}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">Receiver: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="receiver" id="receiver" value="${userAddress.receiver}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">SubAdds: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="subAdds" id="subAdds" value="${userAddress.subAdds}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">SubPost: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="subPost" id="subPost" value="${userAddress.subPost}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">ProvinceId: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="provinceId" id="provinceId" value="${userAddress.provinceId}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">CityId: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="cityId" id="cityId" value="${userAddress.cityId}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">Mobile: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="mobile" id="mobile" value="${userAddress.mobile}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">Telphone: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="telphone" id="telphone" value="${userAddress.telphone}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">Email: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="email" id="email" value="${userAddress.email}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">Status: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="status" id="status" value="${userAddress.status}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">CommonAddr: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="commonAddr" id="commonAddr" value="${userAddress.commonAddr}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">CreateTime: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="createTime" id="createTime" value="${userAddress.createTime}" /></p>
        </td>
      </tr>

                <tr>
                    <td colspan="2">
                        <div align="center">
                            <input type="submit" value="添加" />
                            <input type="reset" value="重置" />
                            <input type="button" value="返回"
                                onclick="window.location='<ls:url address="/admin/userAddress/query"/>'" />
                        </div>
                    </td>
                </tr>
            </table>
           </div>
        </form>
    </body>
</html>


