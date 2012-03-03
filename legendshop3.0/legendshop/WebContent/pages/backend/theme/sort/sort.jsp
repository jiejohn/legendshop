<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/back-common.jsp"%>
<%@ include file="/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
<html>
<head>
<script src="${pageContext.request.contextPath}/common/js/jquery.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/common/css/indexJpgForm.css" />
<title>创建商品类型</title>
</head>
<body class="bodymargin">
<script language="javascript">
  $.validator.setDefaults({
	});
 $(document).ready(function() {
	$("#sortForm").validate({
		rules: {
			sortName:  "required",
			 seq: {
			  required: true,
              number: true
            },
			file: {
				required: "#pictureName:blank"
			}
		},
		messages: {
			sortName: "请输入类型名称",
			seq: {
			    required: "请输入次序",
                number: "请输入数字"
            },
			file:"请上传商品类型图片"
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
<form action="${pageContext.request.contextPath}/admin/sort/save${applicationScope.WEB_SUFFIX}" id="sortForm" method="post" enctype="multipart/form-data">
    <table class="${tableclass}" style="width: 100%">
    <thead>
    	<tr><td><a href="${pageContext.request.contextPath}/admin/index${applicationScope.WEB_SUFFIX}" target="_parent">首页</a> &raquo; 商品管理  &raquo; <a href="${pageContext.request.contextPath}/admin/sort/query${applicationScope.WEB_SUFFIX}">类型管理</a> &raquo; 创建商品类型</td></tr>
    </thead>
    </table>
  <table class="${tableclass}" style="width: 100%" id="col1" >
     <thead>
    <tr>
      <td width="30%" ><div align="center">类型名称：<font color="#ff0000">*</font></div></td>
      <td width="70%" ><input type="text" name="sortName" id="sortName" size="30" value="${sort.sortName}">
      <input id="sortId" name="sortId" value="${sort.sortId}" type="hidden">
      </td>
    </tr>
    </thead>
    <tbody>
    <tr>
      <td width="30%" ><div align="center">次序(必须为数字)：</div></td>
      <td width="70%" >
      <input type="text" name="seq" id="seq" size="30" value="${sort.seq}">
      </td>
    </tr>
	<tr>
      <td ><div align="center">类型图片(765*180)<font color="#ff0000">*</font></div></td>
      <td >
      	<input type="file" name="file" id="file" size="30"/>
      	<input type="hidden" name="pictureName" id="pictureName" size="30" value="${sort.picture}"/>
      </td>
    </tr>
    <tr>
    <td  ><div align="center">原有图片</div></td>
      <td >
      <c:if test="${sort.picture!=null}">
      	<a href="${pageContext.request.contextPath}/photoserver/photo/${sort.picture}" target="_blank">
      	<img src="${pageContext.request.contextPath}/photoserver/photo/${sort.picture}" height="60" width="300"/></a>
      	</c:if>
      </td>
    </tr>
    <tr>
      <td colspan="2" ><div align="center">  
      <auth:auth ifAnyGranted="F_OPERATOR">
        <input type="submit" value="提交"/>
        </auth:auth>
        <input type="reset" value="重置"/>
        <input type="button" value="返回"  onclick='window.location="${pageContext.request.contextPath}/admin/sort/query${applicationScope.WEB_SUFFIX}"' />  
      </div></td>
    </tr>
    </tbody>
  </table>
</form>
		</body>
</html>