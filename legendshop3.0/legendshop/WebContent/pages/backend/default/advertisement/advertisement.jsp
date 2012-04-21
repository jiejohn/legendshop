<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ taglib uri="/WEB-INF/tld/options.tld" prefix="option" %>
<%@ include file="/pages/common/back-common.jsp"%>
<html>
    <head>
        <title>创建广告</title>
        <script src="${pageContext.request.contextPath}/common/js/jquery.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/common/js/jquery.validate.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/common/css/indexJpgForm.css" />
        <script language="javascript">
    $.validator.setDefaults({
    });

    $(document).ready(function() {
    jQuery("#form1").validate({
            rules: {
            linkUrl: {
                required: true,
                minlength: 3
            },
            file:  {
				required: "#picUrlName:blank"
			}
        },
        messages: {
            linkUrl: {
                required: "请输入链接地址",
                minlength: "请认真填写，链接地址不能少于3个字符"
            },
        file: {
        	required : "请上传广告图片"
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
    <body class="bodymargin">
        <form action="${pageContext.request.contextPath}/admin/advertisement/save${applicationScope.WEB_SUFFIX}" method="post" id="form1" enctype="multipart/form-data">
            <input id="id" name="id" value="${bean.id}" type="hidden">
            <div align="center">
                <table class="${tableclass}" style="width: 100%">
    <thead>
    	<tr><td><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商城管理  &raquo; <a href="${pageContext.request.contextPath}/admin/advertisement/query${applicationScope.WEB_SUFFIX}">广告管理</a> &raquo; 创建广告</td></tr>
    </thead>
    </table>
            <table  align="center" class="${tableclass}" id="col1">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
                                创建广告
                            </div>
                        </th>
                    </tr>
                </thead>
      <tr>
        <td width="28%">
          <div align="right">广告类型: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p>

              <select id="type" name="type">
				  <option:optionGroup type="select" required="true" cache="true"
	                beanName="ADVERTISEMENT_TYPE" selectedValue="${bean.type}"/>
	            </select>
           
           </p>
        </td>
      </tr>
     <tr>
        <td>
          <div align="right">链接地址<font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="linkUrl" id="linkUrl" value="${bean.linkUrl}" size="50"/></p>
        </td>
      </tr>
      <tr>
        <td>
          <div align="right">标题</div>
       </td>
        <td>
           <p><input type="text" name="title" id="title" value="${bean.title}" size="50" maxlength="255"/></p>
        </td>
      </tr>
      <tr>
        <td>
          <div align="right">需求描述</div>
       </td>
        <td>
           <p><input type="text" name="sourceInput" id="sourceInput" value="${bean.sourceInput}" size="50" maxlength="255"/></p>
        </td>
      </tr>
      <tr>
        <td>
          <div align="right">状态<font color="ff0000">*</font></div>
       </td>
        <td>
           <p>
				<select id="enabled" name="enabled">
				  <option:optionGroup type="select" required="true" cache="true"
	                beanName="ONOFF_STATUS" selectedValue="${bean.enabled}"/>
	            </select>
			</p>
        </td>
      </tr>
      
            <tr>
                    <td>
                        <div align="right">
                            上传广告图片(对联广告只能上传一张，大小为110*300)
                            <font color="ff0000">*</font>
                        </div>
                    </td>
                    <td>
                        <p>
                            <input type="file" name="file" id="file" size="50"/>
                            <input type="hidden" name="picUrlName" id="picUrlName" size="80"
                                value="${bean.picUrl}" />
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
                                onclick="window.location='${pageContext.request.contextPath}/admin/advertisement/query${applicationScope.WEB_SUFFIX}'" />
                        </div>
                    </td>
                </tr>
            </table>
           </div>
        </form>
    </body>
</html>

