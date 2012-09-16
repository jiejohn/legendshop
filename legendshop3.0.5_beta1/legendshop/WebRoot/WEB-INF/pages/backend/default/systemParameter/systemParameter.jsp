<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/options.tld" prefix="option" %>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<html>
    <head>
        <title>创建 SystemParameter</title>
        <script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/common/js/jquery.validate.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/common/default/css/indexJpgForm.css" />
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
        <form action="${pageContext.request.contextPath}/system/systemParameter/save" method="post" id="form1">
            <input id="name" name="name" value="${bean.name}" type="hidden">
            <div align="center">
            <table  align="center" class="${tableclass}" id="col1">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
                                ${bean.name} 参数
                            </div>
                        </th>
                    </tr>
                </thead>
        <tr>
        <td>
          <div align="center">功能说明</div>
       </td>
        <td>
           <p>
			${bean.memo}
	       </p>
        </td>
      </tr>
      <tr>
        <td>
          <div align="center">参数值</div>
       </td>
        <td>
           <p>
         <c:choose>
           	<c:when test="${bean.type == 'Selection' || bean.type == 'Boolean'}">
		       <select id="value" name="value">
				  <option:optionGroup type="select" required="true" cache="true"
	                beanName="${bean.optional}" selectedValue="${bean.value}"/>
	            </select>
           	</c:when>
           	<c:otherwise>
           		<input type="text" name="value" id="value" value="${bean.value}" size="50" maxlength="200"/>
           	</c:otherwise>
           </c:choose>
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
                                onclick="window.location='${pageContext.request.contextPath}/system/systemParameter/query'" />
                        </div>
                    </td>
                </tr>
            </table>
           </div>
        </form>
    </body>
</html>

