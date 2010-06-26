<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@page import="com.done.util.SessionUserManagement"%>
<html>
<head>
<%session.setAttribute("root", request.getContextPath());%>
        <script src="${root}/common/js/jquery.js" type="text/javascript"></script>
        <script src="${root}/common/js/jquery.validate.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="${root}/common/css/indexJpgForm.css" />
		<script type='text/javascript' src='${root}/dwr/interface/CommonService.js'></script>
  		<script type='text/javascript' src='${root}/dwr/engine.js'></script>
<title>回复用户留言</title>
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
<body>
  <table class="mars" style="width: 100%" id="col1">
  <thead>
    <tr>
      <th colspan="2"><center>回复用户留言</center></th>
    </tr>
    </thead>
    <tr>
      <td width="20%"><div align="right">评论内容  <input type="hidden" id="id" value="${param.id}" name="id"/></div></td>
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
		var loginName = '<%=SessionUserManagement.getUsername(session)%>';
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