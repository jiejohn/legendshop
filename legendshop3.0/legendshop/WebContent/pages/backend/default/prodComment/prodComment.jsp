<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file='/pages/common/taglib.jsp'%>
<%@ include file="/pages/common/back-common.jsp"%>
<html>
    <head>
        <title>回复商品评论</title>
        <script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/common/js/jquery.validate.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/common/css/indexJpgForm.css" />
        <script language="javascript">
    $.validator.setDefaults({
    });

    $(document).ready(function() {
    jQuery("#form1").validate({
            rules: {
            replyContent: {
                required: true,
                minlength: 5,
                maxlength:300
            }
        },
        messages: {
            replyContent: {
                required: "请输入回复内容",
                minlength: "最少不能少于5个字",
                maxlength: "最大不能超过300字"
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
        <form action="${pageContext.request.contextPath}/admin/productcomment/save${applicationScope.WEB_SUFFIX}" method="post" id="form1">
            <input id="id" name="id" value="${bean.id}" type="hidden">
            <div align="center">
            <table  align="center" class="${tableclass}" id="col1">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
                                回复商品评论
                            </div>
                        </th>
                    </tr>
                </thead>
      <tr>
        <td>
          <div align="center">商品名称</div>
       </td>
        <td>
           <p>${bean.prodName}</p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">评价人</div>
       </td>
        <td>
           <p>${bean.userName}</p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">评论内容</div>
       </td>
        <td>
           <p>${bean.content}</p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">评论时间</div>
       </td>
        <td>
           <p><fmt:formatDate value="${bean.addtime}" pattern="yyyy-MM-dd HH:mm"/></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">回复内容 <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><textarea rows="5" cols="50" id="replyContent" name="replyContent">${bean.replyContent}</textarea></p>
        </td>
      </tr>
                <tr>
                    <td colspan="2">
                        <div align="center">
                            <input type="submit" value="提交" />
                            <input type="reset" value="重置" />
                            <input type="button" value="返回"
                                onclick="window.location='${pageContext.request.contextPath}/admin/productcomment/query${applicationScope.WEB_SUFFIX}'" />
                        </div>
                    </td>
                </tr>
            </table>
           </div>
        </form>
    </body>
</html>

