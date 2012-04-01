<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ include file="/pages/common/back-common.jsp"%>
<%@ include file="/pages/common/taglib.jsp"%>
<html>
    <head>
        <title>创建Parnter</title>
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
        <form action="<ls:url address='/admin/parnter/save'/>" method="post" id="form1">
            <input id="partnerId" name="partnerId" value="${parnter.partnerId}" type="hidden">
            <div align="center">
            <table border="0" align="center" class="${tableclass}" id="col1">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
                                创建Parnter
                            </div>
                        </th>
                    </tr>
                </thead>
     			     <tr>
        <td>
          <div align="center">PartnerName: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="partnerName" id="partnerName" value="${parnter.partnerName}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">Password: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="password" id="password" value="${parnter.password}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">Title: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="title" id="title" value="${parnter.title}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">Homepage: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="homepage" id="homepage" value="${parnter.homepage}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">UserId: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="userId" id="userId" value="${parnter.userId}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">UserName: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="userName" id="userName" value="${parnter.userName}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">ShopId: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="shopId" id="shopId" value="${parnter.shopId}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">BankName: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="bankName" id="bankName" value="${parnter.bankName}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">BankNo: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="bankNo" id="bankNo" value="${parnter.bankNo}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">BankUser: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="bankUser" id="bankUser" value="${parnter.bankUser}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">Location: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="location" id="location" value="${parnter.location}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">Contact: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="contact" id="contact" value="${parnter.contact}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">Image: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="image" id="image" value="${parnter.image}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">Image1: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="image1" id="image1" value="${parnter.image1}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">Image2: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="image2" id="image2" value="${parnter.image2}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">Phone: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="phone" id="phone" value="${parnter.phone}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">Address: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="address" id="address" value="${parnter.address}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">Other: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="other" id="other" value="${parnter.other}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">Mobile: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="mobile" id="mobile" value="${parnter.mobile}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">Open: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="open" id="open" value="${parnter.open}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">Status: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="status" id="status" value="${parnter.status}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">Display: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="display" id="display" value="${parnter.display}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">CommentGood: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="commentGood" id="commentGood" value="${parnter.commentGood}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">CommentNone: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="commentNone" id="commentNone" value="${parnter.commentNone}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">CommentBad: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="commentBad" id="commentBad" value="${parnter.commentBad}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">ModifyTime: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="modifyTime" id="modifyTime" value="${parnter.modifyTime}" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">CreateTime: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="createTime" id="createTime" value="${parnter.createTime}" /></p>
        </td>
      </tr>

                <tr>
                    <td colspan="2">
                        <div align="center">
                            <input type="submit" value="添加" />
                            <input type="reset" value="重置" />
                            <input type="button" value="返回"
                                onclick="window.location='<ls:url address="/admin/parnter/query"/>'" />
                        </div>
                    </td>
                </tr>
            </table>
           </div>
        </form>
    </body>
</html>


