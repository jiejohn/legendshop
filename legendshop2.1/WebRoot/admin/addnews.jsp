<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@include file='/common/jsp/common.jsp'%>
<%@include file='/common/jsp/taglib.jsp'%>
<html>
<head>
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
<script type="text/javascript">
<!--
 function submitForm(){
 	var reslut = true;
 	var newsTitle = $("#newsTitle").val();
 	if(newsTitle == null|| newsTitle ==''){
 		reslut = false;
 		alert("新闻标题不能为空");
 		return;
 	}
 	if(reslut){
			document.getElementById('form1').submit();
		}
 }


$(document).ready(function() {
jQuery("#form1").validate({
        rules: {
	   newsTitle: {
           required: true,
           minlength: 3
       }
    },
    messages: {
    	newsTitle: {
        required: "请输入新闻标题",
         minlength: "请认真填写，新闻标题不能少于3个字符"
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
//-->
</script>
</head>
<body>
<center>
<form method="post" action="${root}/admin/addNews.do" id="form1">
        <table style="width:80%;" class="${tableclass}" id="col1">
          <thead>
                    <tr>
                        <th colspan="2">
                            <div align="center">创建新闻</div>
                        </th>
                    </tr>
                </thead>
         <tbody>
          <tr>
            <td>
                                新闻标题<font color="FF0000">*</font>
            <input type="text" id="newsTitle" name="newsTitle" id="newsTitle" size="20" class=input value="${news.newsTitle}" />
            <input type="hidden" id="newsId" name="newsId" value="${news.newsId}"/>
            </td>
          </tr>
              <tr>
      <td><span style="margin-left: 33px">类型</span>
            <select id="status" name="status">
            <c:choose>
                <c:when test="${news.status == '0'}">
                    <option value="1">新闻</option> 
                    <option value="0" selected="selected">链接</option>
                    <option value="2">下线</option>
                </c:when>
                <c:when test="${news.status == '2'}">
                    <option value="1">新闻</option> 
                    <option value="0">链接</option>
                    <option value="2" selected="selected">下线</option>
                </c:when>
                <c:otherwise>
                    <option value="1" selected="selected">新闻</option> 
                    <option value="0">链接</option>
                    <option value="2">下线</option>
                </c:otherwise>
            </c:choose>
            </select>      
           </td>
          </tr>
          <tr>
            <td>
            <auth:auth ifAnyGranted="F_OPERATOR">
			<FCK:editor instanceName="newsContent" height="400px" width="100%">
                <jsp:attribute name="value">${news.newsContent}</jsp:attribute>
            </FCK:editor>
            </auth:auth>
            <auth:auth ifNotGranted="F_OPERATOR">
                你无权编辑该内容，请与管理员联系
            </auth:auth>
            </td>
          </tr>
          </tbody>
        </table>
        <auth:auth ifAnyGranted="F_OPERATOR">
        <input type="submit" value="提交" />
        <input type="button" value="返回" onclick="window.location='${root}/admin/news/query.c'" />
        </auth:auth>
      </form>
      </center>
</body>
</html>