<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common.jsp"%>
<%@ include file="/common/jsp/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
<html>
    <head>
        <title>创建店铺</title>
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
            storeName: "required",
            sitename: "required",
            maddr: "required",
            msn: "required",
            mname: "required",
            code: "required",
            ymname: "required",
            ymaddr: "required",
            briefDesc: "required"
        },
        messages: {
           
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
         $("#colorStyle").get(0).value  ='${bean.colorStyle}';
});
</script>
</head>
    <body>
        <form action="${root}/admin/shopDetail/save.c" method="post" id="form1">
            <input id="userId" name="userId" value="${bean.userId == null ? param.userId:bean.userId}" type="hidden">
             <input id="id" name="id" value="${id}" type="hidden">
            <div align="center">
            <table border="0" align="center" class="${tableclass}" id="col1">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
                                创建店铺
                            </div>
                        </th>
                    </tr>
                </thead>
      <tr>
        <td>
          <div align="center">店名:</div>
       </td>
        <td>
           <p><input type="text" name="storeName" id="storeName" value="${bean.storeName == null ? param.userName : bean.storeName}" size="50" readonly="readonly"/></p>
        </td>
      </tr>
      <tr style="display: none;">
        <td>
          <div align="center">网站路径: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="web" id="web" value="${bean.storeName}" size="50"/></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">网站标题: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="sitename" id="sitename" value="${bean.sitename}" size="50"/></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">银行卡: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="maddr" id="maddr" value="${bean.maddr}" size="50"/></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">银行汇款帐号: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="msn" id="msn" value="${bean.msn}" size="50"/></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">收款人姓名: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="mname" id="mname" value="${bean.mname}" size="50" /></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">邮政编码: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="code" id="code" value="${bean.code}" size="50"/></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">汇款地址: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="ymaddr" id="ymaddr" value="${bean.ymaddr}" size="50"/></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">邮递接收人: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="ymname" id="ymname" value="${bean.ymname}" size="50"/></p>
        </td>
      </tr>
      <tr>
        <td>
          <div align="center">风格类型: <font color="ff0000">*</font></div>
       </td>
        <td><!-- see BusinessService getColorTyle -->
           <select id="colorStyle" name="colorStyle">
                <option value="">循环</option>
                <option value="red">红色</option>
                <option value="blue">蓝色</option>
                <option value="green">绿色</option>
           </select>
        </td>
      </tr>     
      <c:if test="${bean.addtime!=null}">
     <tr>
        <td>
          <div align="center">修改时间: <font color="ff0000">*</font></div>
       </td>
        <td>
           <c:out value="${bean.modifyTime}"></c:out>
        </td>
      </tr>
      </c:if>
      <c:if test="${bean.addtime!=null}">
     <tr>
        <td>
          <div align="center">创建时间: <font color="ff0000">*</font></div>
       </td>
        <td>
           ${bean.addtime}
        </td>
      </tr>
      </c:if>
     <tr>
        <td>
          <div align="center">简要描述: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="briefDesc" id="briefDesc" value="${bean.briefDesc}" size="50"/></p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">详细描述: </div>
       </td>
        <td>
           <p>
              <textarea  name="detailDesc" id="detailDesc">${bean.detailDesc}</textarea>
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
                                onclick="window.location='${root}/admin/shopDetail/query.c'" />
                        </div>
                    </td>
                </tr>
            </table>
            </div>
        </form>
    </body>
</html>

