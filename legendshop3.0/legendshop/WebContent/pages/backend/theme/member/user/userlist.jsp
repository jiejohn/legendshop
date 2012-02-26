<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/common.jsp"%>
<%@ include file="/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
<html:html>
<head>
	<title>用户列表</title>
<LINK title=Style href="${pageContext.request.contextPath}/common/css/back_style.css" type=text/css rel=stylesheet>
  		<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/CommonService.js'></script>
  		<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/engine.js'></script>
		<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/util.js'></script>
	<script language="JavaScript" type="text/javascript">
<!--
  function confirmDelete()
	{
	    return con = confirm("确定要删除吗？");
	}
	
	     window.onload=function(){
        	var enabled = '${enabled}';
        	initStatus(enabled);
        	}
        	
        function initStatus(statusValue){
			DWRUtil.setValues({enabled:statusValue});
		}	

//-->
</script>
</head>

<body>
	<%
			int offset=((Integer)request.getAttribute("offset")).intValue();
	%>
	
	<br>
	<form action="/member/user/usersList${applicationScope.WEB_SUFFIX}">
    <input type="hidden" name="curPageNO" value="<%=request.getAttribute("curPageNO")%>">
		<div align="center">
			&nbsp; 用户名
			<input type="text" id="search" name="search" maxlength="50" value="<%=request.getAttribute("search")%>" />
				&nbsp;状态 
	            <select id="enabled" name="enabled">
					<option value="">请选择</option>	
			    	<option value="1">有效</option>	
	      			<option value="0" >无效</option>
			</select>
			<input type="submit" value="添加"/>
			<table class="tableBorder"  width="100%"  style="margin-top: 5px">
			 <logic:present name="list" scope="request">
				<tr bordercolor="#0099ff">
					<th colspan="9" height="32">
						用户信息查询
					</th>
				</tr>
				<tr bordercolor="#0099ff">
					<td class="forumRow">
						顺序
					</td>
					<td class="forumRow">
						用户名
					</td>
					<td class="forumRow">
						状态
					</td>
					<td class="forumRow">
						注释
					</td>
					<td class="forumRow">
						用户角色
					</td>
					<td class="forumRow">
						用户权限
					</td>
					<td class="forumRow"> 
						修改密码
					</td>
					<td class="forumRow"> 
						修改状态
					</td>
				</tr>

					<logic:iterate id="user" name="list">
						<logic:present name="user">
							<tr bordercolor="#0099ff">
								<td class="forumRow"><%=offset++%></td>
								<td class="forumRow"><bean:write name="user" property="name" />&nbsp;</td>
								<td class="forumRow">
								<!--  
									<bean:write name="user" property="enabled" />
									-->
									<logic:equal name="user" property="enabled" value="0"><font color="red">无效</font></logic:equal>
									<logic:equal name="user" property="enabled" value="1">有效</logic:equal>
								
									&nbsp;
								</td>
								<td class="forumRow"><bean:write name="user" property="note" />&nbsp;</td>
								<td class="forumRow"><html:link page="/member/user/findRoleByUser${applicationScope.WEB_SUFFIX}" paramId="userId" paramName="user" paramProperty="id">用户角色</html:link>
								</td>
								<td class="forumRow">
									<html:link page="/member/user/findFunctionByUser${applicationScope.WEB_SUFFIX}" paramId="userId" paramName="user" paramProperty="id">用户权限</html:link>
								</td>
								<td align="center" class="forumRow">
								<html:link href="findUserById${applicationScope.WEB_SUFFIX}" paramId="id" paramName="user" paramProperty="id">修改密码</html:link> 
								</td>
								<td align="center" class="forumRow">
								<html:link href="preupdateStatus${applicationScope.WEB_SUFFIX}" paramId="id" paramName="user" paramProperty="id">修改状态</html:link>
								 </td>
							</tr>
						</logic:present>
					</logic:iterate>
				</logic:present>

			</table>
		</div>
	</form>
	<div align="center">
	<logic:present name="toolBar">
		<bean:write name="toolBar" filter="false"/>
	</logic:present>
	</div>
	<div align="center">
    <P> 点击此处
    <html:link page="/member/user/saveUser.jsp">创建用户</html:link>
       </div>
</body>
</html:html>
