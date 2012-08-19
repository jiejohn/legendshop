<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ taglib uri="/WEB-INF/tld/options.tld" prefix="option" %>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<html>
<head>
        <script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/common/js/jquery.validate.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/common/default/css/indexJpgForm.css" />
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
<body class="bodymargin">
<center>
<form method="post" action="${pageContext.request.contextPath}/admin/news/save${applicationScope.WEB_SUFFIX}" id="form1">
    <table class="${tableclass}" style="width: 100%">
    <thead>
    	<tr><td><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商城管理  &raquo; <a href="${pageContext.request.contextPath}/admin/news/query${applicationScope.WEB_SUFFIX}">新闻管理</a> &raquo; 创建新闻</td></tr>
    </thead>
        <table style="width:100%;" class="${tableclass}" id="col1">
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
            </td>
            <td>
            <input type="text" name="newsTitle" id="newsTitle" size="40" class=input value="${news.newsTitle}" />
            <input type="hidden" id="newsId" name="newsId" value="${news.newsId}"/>
            </td>
          </tr>
         <tr><td>新闻类型</td>
         <td>
				<select id="position" name="position">
				  <option:optionGroup type="select" required="true" cache="true"
	                beanName="NEWS_POSITION" selectedValue="${news.position}"/>
	            </select>
	            		   栏目
	            <select id="newsCategoryId" name="newsCategoryId">
	                 <option:optionGroup type="select" required="false" cache="fasle"
	                beanName="NewsCategory" beanId="newsCategoryId" beanDisp="newsCategoryName" defaultDisp="-- 请选择 --"
	                hql="select t.newsCategoryId, t.newsCategoryName from NewsCategory t where t.status = 1 and  t.userName = ?" param="${sessionScope.SPRING_SECURITY_LAST_USERNAME}" selectedValue="${news.newsCategoryId}"/>
	            </select>
	           
	           
	            还没有商品类型？请先&nbsp;<a href="${pageContext.request.contextPath}/admin/sort/load${applicationScope.WEB_SUFFIX}">商品类型</a>&nbsp;<a href="${pageContext.request.contextPath}/WEB-INF/pages/newsCategory/newsCategory.jsp">创建栏目</a>
	            </td> 
		</tr>
		  <tr><td></td>
		  <td>状态
	           <select id="status" name="status">
				  <option:optionGroup type="select" required="true" cache="true"
	                beanName="ONOFF_STATUS" selectedValue="${bean.status}"/>
	            </select>
	            &nbsp;产品分类            
	            <select id="sortId" name="sortId">
	                 <option:optionGroup type="select" required="false" cache="fasle"
	                beanName="Sort" beanId="sortId" beanDisp="sortName" defaultDisp="-- 请选择 --"
	                hql="select t.sortId, t.sortName from Sort t where t.userName = ?" param="${sessionScope.SPRING_SECURITY_LAST_USERNAME}" selectedValue="${news.sortId}"/>
	            </select>
	            &nbsp;高亮
	            <select id="highLine" name="highLine">
				  <option:optionGroup type="select" required="false" cache="true"
	                beanName="YES_NO" selectedValue="${news.highLine}"/>
	            </select>
		  </td></tr>
		   <tr>
            <td>
                 <span title="如果不填写则以新闻内容前面部分代替" style="cursor: pointer;">新闻提要</span>
                 
            </td>
            <td><input type="text" name="newsBrief" id="newsBrief" size="120" maxlength="200" class=input value="${news.newsBrief}" /></td>
          </tr>
          <tr>
            <td colspan="2">
            <auth:auth ifAnyGranted="F_OPERATOR">
			<FCK:editor instanceName="newsContent" height="400px" width="100%" basePath="/plugins/fckeditor">
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
        <input type="button" value="返回" onclick="window.location='${pageContext.request.contextPath}/admin/news/query${applicationScope.WEB_SUFFIX}'" />
        </auth:auth>
      </form>
      </center>
</body>
</html>