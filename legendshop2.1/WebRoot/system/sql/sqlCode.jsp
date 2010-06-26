<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
<%@page import="bingosoft.jcf.sql.ConfigCode"%>
<%@page import="java.util.Map"%>
<bean:parameter id="debug" name="isDebug" value="1"/>
<%
String signnature = request.getParameter("signnature");
ConfigCode configCode = ConfigCode.getInstance();
Map<String, String> sqls = configCode.getParameters();
request.setAttribute("sqls",sqls);
int isDebug = 0;
if(configCode.isDebug()){
	isDebug = 1;
}
request.setAttribute("isDebug",isDebug);

//搜索
String result ="";
if(signnature!=null){
	result = ConfigCode.getInstance().getCode(signnature);
	request.setAttribute("result",result);
}else{
	signnature="";
}
%>

<html>
  <head>
    <title>系统配置文件</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <body>
 
   <auth:auth ifNotGranted = "OP_DATA_RIGHT_TEST">
    <a href="refresh.jsp">刷新缓存</a><br>
    <form name="form2" method="post" action="changeLogdebug.jsp">
                设置调试模式，如果设置为“是”则每次更改都会自动加载配置文件，设置为“否”则可以提高运行效率<br><br>
               <select name="isDebug">
               <c:if test="${isDebug eq 1}">
               	<option value="1" selected="selected">是</option>
               	<option value="0" >否</option>
               	</c:if>
                <c:if test="${isDebug eq 0}">
               	<option value="1" >是</option>
               	<option value="0" selected="selected">否</option>
               	</c:if>  
               </select>
                <input type="submit" name="Submit" value="提交">
    </form>
    
    </auth:auth>
     
     <auth:auth ifNotGranted = "OP_LOGOUT">
          <a href="javascript:window.parent.location='${DOMAIN_NAME}/cas/logout?next-chain=${DOMAIN_NAME}/j_acegi_logout'">
          系统注销</a>
  	</auth:auth>
  	
  	<br><br>
      通过ID查找系统参数的对应值：
    <form name="form1" method="post" action="sqlCode.jsp">
      <input type="text" name="signnature" value="<%=signnature %>">
      <input type="submit" name="Submit" value="提交">
    </form>
    <table width="100%" border="1">
<tbody>
<c:if test="${not empty result}">
<tr>
<td>${result}&nbsp;</td>
</tr>
</c:if>
</tbody>
</table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td colspan="2"><font color="red">系统配置如下：</font></td>
      </tr>
      <c:if test="${not empty sqls}">
      <c:forEach var="sql" items="${sqls}" varStatus="rowStatus">
      	<c:if test="${rowStatus.index % 2 eq 0}">
					<tr bgcolor="#FFFFFF">
		</c:if>
		<c:if test="${rowStatus.index % 2 eq 1}">
					<tr bgcolor="#E6E6E6">
		</c:if>
		<td  style="border:#cccccc 1px solid" width="30" align="center">${rowStatus.index}</td>
        <td  style="border:#cccccc 1px solid" width="15%" nowrap="nowrap">${sql.key}</td>
        <td  style="border:#cccccc 1px solid">${sql.value}</td>
      </tr>
      </c:forEach>
      </c:if>
    </table>
    </body>
</html>
