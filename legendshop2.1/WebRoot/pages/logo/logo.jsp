<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<html>
	<head>
		<title>创建 Logo</title>
		<%
		    session.setAttribute("root", request.getContextPath());
		%>
		<script src="${root}/common/js/jquery.js" type="text/javascript"></script>
		<script src="${root}/common/js/jquery.validate.js"
			type="text/javascript"></script>
		<link rel="stylesheet" type="text/css" media="screen"
			href="${root}/common/css/indexJpgForm.css" />
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
            url: "required"
        },
        messages: {
			url: {
        	required:"请输入链接地址"
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
		<form action="${root}/admin/logo/save.c" method="post" id="form1"
			enctype="multipart/form-data">
			<input id="id" name="id" value="${bean.id}" type="hidden">
			<div align="center">
			<table border="0" align="center" class="${tableclass}" id="col1">
				<thead>
					<tr class="sortable">
						<th colspan="2">
							<div align="center">
								创建 Logo
							</div>
						</th>
					</tr>
				</thead>
				<tr>
					<td>
						<div align="right">
							上传图片(170px*60px)
							<font color="ff0000">*</font>
						</div>
					</td>
					<td>
						<p>
							<input type="file" name="file" id="file"/>
							<input type="hidden" name="bannerName" id="bannerName" size="80"
								value="${bean.banner}" />
						</p>
					</td>
				</tr>
				<tr>
					<td>
						<div align="right">
							连接地址
							<font color="ff0000">*</font>
						</div>
					</td>
					<td>
						<p>
							<input type="text" name="url" id="url" size="90"
								value="${bean.url}" />
						</p>
					</td>
				</tr>
				<tr>
					<td>
						<div align="right">
							备注
						</div>
					</td>
					<td>
						<p>
							<textarea rows="6" cols="5" id="memo" name="memo">${bean.memo}</textarea>
						</p>
					</td>
				</tr>
				<c:if test="${bean.banner!=null}">
					<tr>
						<td>
							<div align="center">
								原有图片
							</div>
						</td>
						<td>

								<a href="${PHOTO_PATH}/${bean.banner}" target="_blank"> <img
									src="${PHOTO_PATH}/${bean.banner}" width="170" height="44"/>
							</a>

						</td>
					</tr>
				</c:if>
				<tr>

					<td colspan="2">
						<div align="center">
						<auth:auth ifAnyGranted="F_OPERATOR">
							<input type="submit" value="提交" />
					    </auth:auth>
							<input type="reset" value="重置" />
							<input type="button" value="返回"
								onclick="window.location='${root}/admin/logo/query.c'" />
						</div>
					</td>
				</tr>
			</table>
			</div>
		</form>
	</body>
</html>