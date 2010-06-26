<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common.jsp"%>
<%@ include file="/common/jsp/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
<html>
<head>
<title>创建首页图片</title>
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
	$("#indexJpgForm").validate({
		rules: {
			alt:  "required",
			title: "required",
			stitle: "required",
			link: "required",
			titleLink: "required"
		},
		messages: {
			alt: "说明文字不能少于5个字符",
			title:"请输入说明文字",
			stitle:"请输入说明文字",
			link:"请输入说明文字",
			titleLink:"请输入说明文字"
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
<form action="${root}/admin/saveIndexJpg.do"  method="post" id="indexJpgForm" enctype="multipart/form-data" >
  <input id="id" name="id" value="${index.id}" type="hidden">
  <table class="${tableclass}" id="col1" style="width:100%">
  <thead>
    <tr>
      <th colspan="2"><div align="center">创建首页图片</div></th>
    </tr>
    </thead>
    <tr style="display: none;">
      <td width="30%"><div align="right">链接地址<font color="#ff0000">*</font></div></td>
      <td width="70%">
      <p>
          <input type="text" name="href" id="href" size="30" value="${index.href}"> </p>
      </td>
    </tr>
    <tr>
      <td width="30%"><div align="right">说明文字<font color="#ff0000">*</font></div></td>
      <td width="70%">
      <p>
      <input type="text" name="alt" id="alt" size="30" value="${index.alt}">
      </p>
      </td>
    </tr>
     <tr>
      <td width="30%"><div align="right">标题<font color="#ff0000">*</font></div></td>
      <td width="70%">
      <p>
      <input type="text" name="title" id="title" size="30" value="${index.title}">
      </p>
      </td>
    </tr>   
    <tr>
      <td width="30%"><div align="right">小标题<font color="#ff0000">*</font></div></td>
      <td width="70%">
      <p>
      <input type="text" name="stitle" id="stitle" size="30" value="${index.stitle}">
      </p>
      </td>
    </tr>   
    <tr>
      <td width="30%"><div align="right">图片链接地址<font color="#ff0000">*</font></div></td>
      <td width="70%">
      <p>
      <input type="text" name="link" id="link" size="30" value="${index.link}">
      </p>
      </td>
    </tr>  
    <tr>
      <td width="30%"><div align="right">标题链接地址<font color="#ff0000">*</font></div></td>
      <td width="70%">
      <p>
      <input type="text" name="titleLink" id="titleLink" size="30" value="${index.titleLink}">
      </p>
      </td>
    </tr> 
	<tr>
      <td>
      	<div align="right">上传图片，不能大于3M，大小为530*290<font color="#ff0000">*</font></div></td>
      <td>
      	<p>
      	<input type="file" name="img" id="img" size="30"/>
      	</p>
      	<input type="hidden" name="imgName" id="imgName" size="30" value="${index.img}"/>
      </td>
    </tr>
    <tr>
    <td ><div align="right">原有图片</div></td>
      <td>
      <c:if test="${index.img!=null}">
      	<a href="${PHOTO_PATH}/${index.img}" target="_blank">
      	<img src="${PHOTO_PATH}/${index.img}" height="145" width="265"/></a>
      	</c:if>
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
                                onclick="window.location='${root}/admin/indexjpgList.do'" />
                        </div>
      
      </td>
    </tr>
  </table>
</form>
		</body>
</html>