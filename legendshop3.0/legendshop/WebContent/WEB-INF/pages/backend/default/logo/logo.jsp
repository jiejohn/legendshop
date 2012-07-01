<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<html>
	<head>
		<title>创建 Logo</title>
		<script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/common/js/jquery.validate.js" type="text/javascript"></script>
		<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/common/default/css/indexJpgForm.css" />
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
	<body class="bodymargin">
		<form action="${pageContext.request.contextPath}/admin/logo/save${applicationScope.WEB_SUFFIX}" method="post" id="form1"
			enctype="multipart/form-data">
			<input id="id" name="id" value="${bean.id}" type="hidden">
			<div align="center">
				    <table class="${tableclass}" style="width: 100%">
	    <thead>
	    	<tr><td><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商城管理  &raquo; <a href="${pageContext.request.contextPath}/admin/logo/query${applicationScope.WEB_SUFFIX}">Logo管理</a> &raquo; 创建 Logo</td></tr>
	    </thead>
	    </table>
			<table  align="center" class="${tableclass}" id="col1">
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
							<input type="text" name="url" id="url" size="81" value="${bean.url}" />
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
						<input type="text" name="memo" id="memo" size="81" maxlength="100" value="${bean.memo}" />
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

								<a href="${pageContext.request.contextPath}/photoserver/photo/${bean.banner}" target="_blank"> <img
									src="${pageContext.request.contextPath}/photoserver/photo/${bean.banner}" width="170" height="44"/>
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
								onclick="window.location='${pageContext.request.contextPath}/admin/logo/query${applicationScope.WEB_SUFFIX}'" />
						</div>
					</td>
				</tr>
			</table>
			</div>
		</form>
	</body>
</html>