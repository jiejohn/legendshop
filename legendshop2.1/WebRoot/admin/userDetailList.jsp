<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common.jsp"%>
<%@ include file="/common/jsp/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
<html:html>
<head>
  		<script type='text/javascript' src='${root}/dwr/interface/CommonService.js'></script>
  		<script type='text/javascript' src='${root}/dwr/engine.js'></script>
		<script type='text/javascript' src='${root}/dwr/util.js'></script>
<LINK title=Style href="${root}/common/css/back_style.css" type=text/css rel=stylesheet>
<script language="JavaScript" type="text/javascript">
<!--
  function confirmDelete()
	{
	    return con = confirm("确定要删除吗？");
	}
	
  function deleteUserDetail(userId,userName) {
	  if(confirm('确定删除用户 '+userName+' ?')){
        CommonService.deleteUserDetail(userId,userName, function(retData){
	       if(retData == null ){
	          alert("成功删除该纪录！") ;
	          window.location.reload() ;
	       }else{
	          alert("删除失败！,结果是："+retData) ;
	       }
	       
	    }) ;
    }
}
//-->
</script>
<title>分类列表</title>
</head>
<body>
<%
			String curPageNO = (String) request.getParameter("curPageNO");
			int curpage = 1;
			if ("".equals(curPageNO) || curPageNO == null)
				curpage = 1;
			else {
				curpage = Integer.parseInt(curPageNO);
			}
			Integer offset = (Integer)request.getAttribute("offset");
	%>
<br>
<form action="${root}/system/userDetailList.do">
<input type="hidden" name="curPageNO" value="${curPageNO}">
			<div align="center">
			&nbsp; 根据用户名称查找 ：
			<input type="text" name="search" maxlength="50" value="<%=request.getAttribute("search")%>" />
			<html:submit value="搜索" property="Submit" />
			
			</div>

<table width="100%" border="0" align="center" class="tableBorder">
  <tr>
    <th height="29" colspan="17" scope="col">用户详细信息列表</th>
  </tr>
  <c:if test="${list != null }">
  <tr>
    <td align="center" class="forumRow" width="5%">顺序</td>
    <td align="center" class="forumRow" width="7%">用户名称</td>
    <td align="center" class="forumRow" width="7%">用户昵称</td>
    <td align="center" class="forumRow" width="8%">用户邮件</td>
    <td align="center" class="forumRow" width="16%">用户地址</td>
    <td align="center" class="forumRow" width="7%">电话</td>
    <td align="center" class="forumRow" width="7%">用户邮编</td>
<%--    <td align="center" class="forumRow" width="10%">msn</td>--%>
<%--    <td align="center" class="forumRow" width="10%">qq号码</td>--%>
    <td align="center" class="forumRow" width="7%">传真</td>
    <td align="center" class="forumRow" width="10%">修改时间</td>
    <td align="center" class="forumRow" width="10%">注册时间</td>
    <td align="center" class="forumRow" width="10%">注册IP</td>
<%--    <td align="center" class="forumRow" width="10%">最后登录时间</td>--%>
<%--    <td align="center" class="forumRow" width="10%">最后登录IP</td>--%>
<%--    <td align="center" class="forumRow" width="10%">备注</td>--%>
    <td align="center" class="forumRow" width="10%">操作&nbsp;</td>
  </tr>
  <c:forEach items="${requestScope.list}" var="userDetail">
  <tr>
    <td align="center" class="forumRow"><%=offset++%></td>
    <td align="center" class="forumRow">${userDetail.userName}</td>
    <td align="center" class="forumRow">${userDetail.nickName}</td>
    <td align="center" class="forumRow">${userDetail.userMail}</td>
    <td align="center" class="forumRow">${userDetail.userAdds}</td>
    <td align="center" class="forumRow">${userDetail.userTel}</td>
    <td align="center" class="forumRow">${userDetail.userPostcode}</td>
<%--    <td align="center" class="forumRow">${userDetail.msn}</td>--%>
<%--    <td align="center" class="forumRow">${userDetail.qq}</td>--%>
    <td align="center" class="forumRow">${userDetail.fax}</td>
    <td align="center" class="forumRow">${userDetail.modifyTime}</td>
    <td align="center" class="forumRow">${userDetail.userRegtime}</td>
    <td align="center" class="forumRow">${userDetail.userRegip}</td>
<%--    <td align="center" class="forumRow">${userDetail.userLasttime}</td>--%>
<%--    <td align="center" class="forumRow">${userDetail.userLastip}</td>--%>
<%--    <td align="center" class="forumRow">${userDetail.userMemo}</td>--%>
    <td align="center" class="forumRow">
         <a href='${root}/admin/shopDetail/load/${userDetail.userId}.c?userName=${userDetail.userName}'>建店</a>
         <br>
  		 <a href='javascript:deleteUserDetail("${userDetail.userId}","${userDetail.userName}")'>删除</a>
    </td>
    </tr>
  </c:forEach>
  </c:if>
</table>
</form>
	<div align="center">
	<logic:present name="toolBar">
		<bean:write name="toolBar" filter="false"/>
	</logic:present>
	</div>
</body>
</html:html>

