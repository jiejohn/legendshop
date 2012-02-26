<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ include file="/pages/common/back-common.jsp"%>
<%@page import="com.legendshop.core.UserManager"%>
<html>
<head>
        <script src="${pageContext.request.contextPath}/common/js/jquery.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/common/js/jquery.validate.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/common/css/indexJpgForm.css" />
		<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/CommonService.js'></script>
  		<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/engine.js'></script>
<title>回复用户消息</title>
<script type="text/javascript">
jQuery(document).ready(function() {
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
	<table class="${tableclass}" style="width: 100%">
    <thead>
    	<tr><td><a href="/member/" target="_parent">首页</a> &raquo; 商城管理  &raquo; <a href="${pageContext.request.contextPath}/admin/userComment/query${applicationScope.WEB_SUFFIX}?status=0">消息管理</a> &raquo; 回复用户消息</td></tr>
    </thead>
    </table>
  <table class="${tableclass}" style="width: 100%" id="col1">
  <thead>
    <tr>
      <th colspan="2"><center>回复用户消息</center></th>
    </tr>
    </thead>
    <tr>
      <td width="20%"><div align="right">评论内容  <input type="hidden" id="id" value="${comment.id}" name="id"/></div></td>
      <td width="80%">
          ${comment.content}
      </td>
    </tr>
     <tr>
      <td width="20%"><div align="right">回复<font color="#ff0000">*</font></div></td>
      <td width="80%">
      		<p><textarea rows="6" id="answer" name="answer"></textarea></p>
      </td>
    </tr>  
    <tr>
      <td colspan="2">
                  <div align="center">
                        <auth:auth ifAnyGranted="F_OPERATOR">
                            <input type="button" value="回复" onclick="javascript:answerWord();"/>
                         </auth:auth>
                            <input type="reset" value="重置" />
                            <input type="button" value="返回"
                                onclick="javasrcript:closeWin();" />
                        </div>
                      </td>
    </tr>
  </table>
  <script type="text/javascript">
  function answerWord() {
		var id = $("#id").val();
		var answer = $("#answer").val();
		if(answer == null || answer.length <5){
				alert("请认真填写回复内容，不能少于5个字");
				return;
			}
		var loginName = '<%=gement.getUsername(session)%>';
	      CommonService.answerWord(id,loginName,answer, function(retData){
	          if(retData == null){
					alert("回复成功");
				    window.opener.location.reload(); //关键是这句：刷新父窗口
				    window.close();
	              }else{
	            	 alert("回复失败");
	              }	       
			});
		}
	
	function closeWin(){
	    window.opener.location.reload(); //关键是这句：刷新父窗口
	    window.close();
	}
  </script>
 </body>
</html>