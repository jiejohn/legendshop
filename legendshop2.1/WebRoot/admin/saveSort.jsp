<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common.jsp"%>
<%@ include file="/common/jsp/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
<html>
<head>
<LINK title=Style href="${root}/common/css/back_style.css" type=text/css rel=stylesheet>
<title>创建产品类型</title>
</head>
<br>
<br>
<script language="javascript">
   function checkform() {
	  if(document.getElementById("sortName").value==null||document.getElementById("sortName").value=='')
		{
			alert("请输入类型名称!");
			document.getElementById("sortName").focus();
			return false;
		}
		if(document.getElementById("seq").value!=null && isNaN(document.getElementById("seq").value))
        {
            alert("次序的格式必须是数字!");
            document.getElementById("seq").focus();
            return false;
        }
	  if(document.getElementById("picture").value==null||document.getElementById("picture").value=='')
		{
		if(document.getElementById("pictureName").value==null||document.getElementById("pictureName").value==''){
					alert("请上传类型图片!");
			document.getElementById("picture").focus();
			return false;
		}

		}		
		return true ;
  }
</script>
<form action="${root}/admin/fileUpload.do"  method="post" enctype="multipart/form-data" onsubmit="return checkform()">
  <table width="489" height="129" border="0" align="center" bordercolor="#0099FF" class="tableBorder">
    <tr>
      <th colspan="2">创建产品类型</th>
    </tr>
    <tr>
      <td width="30%" class="forumRow"><div align="center">类型名称：<font color="#ff0000">*</font></div></td>
      <td width="70%" class="forumRow"><input type="text" name="sortName" id="sortName" size="30" value="${sort.sortName}">
      <input id="sortId" name="sortId" value="${sort.sortId}" type="hidden">
      </td>
    </tr>
    <tr>
      <td width="30%" class="forumRow"><div align="center">次序(必须为数字)：</div></td>
      <td width="70%" class="forumRow">
      <input type="text" name="seq" id="seq" size="30" value="${sort.seq}">
      </td>
    </tr>
	<tr>
      <td class="forumRow"><div align="center">类型图片(765*180)<font color="#ff0000">*</font></div></td>
      <td class="forumRow">
      	<input type="file" name="picture" id="picture" size="30"/>
      	<input type="hidden" name="pictureName" id="pictureName" size="30" value="${sort.picture}"/>
      </td>
    </tr>
    <tr>
    <td class="forumRow" ><div align="center">原有图片</div></td>
      <td class="forumRow">
      <c:if test="${sort.picture!=null}">
      	<a href="${PHOTO_PATH}/${sort.picture}" target="_blank">
      	<img src="${PHOTO_PATH}/${sort.picture}" height="60" width="300"/></a>
      	</c:if>
      </td>
    </tr>
    <tr>
      <td colspan="2" class="forumRow"><div align="center">  
      <auth:auth ifAnyGranted="F_OPERATOR">
        <html:submit value="提交"/>
        </auth:auth>
        <html:reset  value="重置"/>
		          
      </div></td>
    </tr>
  </table>
</form>
		<table border="0" width="200" align="center">
			<tr>
				<td>
					<p align="center">
						<a href="${root}/admin/sortList.do" title="GoBack">返回</a>
					</p>
				</td>
			</tr>
		</table>
</html>