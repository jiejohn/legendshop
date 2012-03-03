<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/back-common.jsp"%>
<%@ include file="/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
<%@ taglib uri="/WEB-INF/tld/options.tld" prefix="option" %>
<html>
<head>
	<title>修改权限</title>
        <script src="${pageContext.request.contextPath}/common/js/jquery.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/common/js/jquery.validate.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/common/css/indexJpgForm.css" />
</head>


<body class="bodymargin">
<div align="center">      
      <form  action="/member/right/updateFunctionById${applicationScope.WEB_SUFFIX}">
        <table class="${tableclass}" style="width: 100%">
			 <thead>
			  <tr><td><a href="${pageContext.request.contextPath}/admin/index${applicationScope.WEB_SUFFIX}" target="_parent">首页</a> &raquo; 用户管理  &raquo; 权限管理 &raquo; 修改权限</td></tr>
			 </thead>
		</table>
      
      
        <table align="center" class="${tableclass}" id="col1">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
                                 修改权限
                            </div>
                        </th>
                    </tr>
                </thead>
     
          
		 <tr style="display: none">
            <td width="163" height="29" align="center" class="forumRow"> 主键：</td>
            <td width="211" align="center" class="forumRow">
			<input type="text" name="function.id" value=<%=request.getParameter("id")%> readonly="true">
			</td>
        </tr>
          <tr>
            <td height="27" align="center" class="forumRow">
     <div align="right">名称 <font color="ff0000">*</font></div></td>
            <td align="center" class="forumRow"><input type="text" name="function.name" value="${function.name}"></td>
          </tr>
          <tr>
            <td height="27" align="center" class="forumRow"><div align="right">权限名称 <font color="#ff0000">*</font></div></td>
            <td align="center" class="forumRow"><input type="text" name="function.protectFunction" value="${function.protectFunction }"/></td>
          </tr>
          <tr>
            <td height="27" align="center" class="forumRow"><div align="right">备注：</div></td>
            <td align="center" class="forumRow"><input type="text" name="function.note" value="${function.note}"></td>
          </tr>
          <tr >
            <td height="42" colspan="2" class="forumRow">
                  <div align="center">
                   <input type="submit" name="Submit" value="修改">
                   <input type="reset" name="cancel" value="重置">
                   </div>
            </td>
          </tr>
</table>
      </form>
</div>
<p>&nbsp; </p>
</body>
</html>

