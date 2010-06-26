<%@ page contentType="text/html;charset=utf-8"%>
<%
	String jdbc_username = request.getParameter("jdbc_username");
	String jdbc_password = request.getParameter("jdbc_password");
	String dbname = request.getParameter("dbname");
	String dbhost = request.getParameter("dbhost");
	String dbport = request.getParameter("dbport");
	String domainName = request.getParameter("domainName");
	try{
		(new com.done.install.Setup(jdbc_username, jdbc_password ,dbname,dbhost,dbport,domainName)).startSetup(request);
	}catch(DBException e){
		   RequestDispatcher rd = request.getRequestDispatcher("step_db_f.jsp");  
		   rd.forward(request, response);
	}catch(PropertiesException e){
		   RequestDispatcher rd = request.getRequestDispatcher("step_issp_f.jsp");  
		   rd.forward(request, response);
	}catch(Exception e){
		   RequestDispatcher rd = request.getRequestDispatcher("step_path_f.jsp");  
		   rd.forward(request, response);
	}
 
%>
<%
Runtime runtime = Runtime.getRuntime();
long totalMemory = runtime.totalMemory();
long freeMemory  = runtime.freeMemory();
long totalKB = totalMemory/1024;
long freeKB  = freeMemory/1024;
%>

<%@page import="com.done.install.PropertiesException"%>
<%@page import="com.done.install.DBException"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>欢迎使用LegendShop - 高性能的JAVA网购平台</title>

<link href="s.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
.STYLE3 {
	font-size: 16px;
	color: #000000;
	font-weight: bold;
}
.STYLE4 {color: #0000FF}
.STYLE5 {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-weight: bold;
}
-->
</style>
<script>
function s(theForm)
{
	theForm.action = theForm.systenFolder.value+theForm.action;
}

function go(url)
{
	window.open(url,'','');
}
</script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="98%" border="0" align="center" cellpadding="3" cellspacing="0">
  <tr>
    <td width="87%" height="59" class="line"><img src="../img/logo.png"></td>
    <td width="13%" align="right" valign="middle" class="line"><CENTER>
      <span class="STYLE5">LegendShop  V2.0      </span>
    </CENTER></td>
  </tr>
</table>
<br>
<table width="98%" border="0" align="center" cellpadding="3" cellspacing="0">
  <tr>
    <td width="21%" align="left" valign="top"><table width="162" border="0" cellpadding="3" cellspacing="1" bgcolor="#999999">
      <tr>
        <td width="215" bgcolor="#FFFFFF"><table width="163" border="0" cellspacing="0" cellpadding="5">
          <tr>
            <td width="217" align="center" valign="middle" bgcolor="#eeeeee" class="line"><strong>安装步骤</strong></td>
          </tr>
          <tr>
            <td><img src="imgs/select.gif" width="14" height="13" align="absmiddle"> 设置系统域名</td>
          </tr>
          <tr>
            <td> <img src="imgs/select.gif" width="14" height="13" align="absmiddle"> 配置数据库帐号</td>
          </tr>
          <tr>
            <td><img src="imgs/select.gif" width="14" height="13" align="absmiddle"> 导入数据</td>
          </tr>
          <tr>
            <td><img src="imgs/select.gif" width="14" height="13" align="absmiddle"> 安装完成</td>
          </tr>
        </table></td>
      </tr>
    </table></td>
    <td width="79%" align="left" valign="top"><table width="778" height="297" border="0" cellpadding="3" cellspacing="0">

      <tr>
        <td width="760" height="25"><span class="STYLE3"><img src="imgs/p_success.gif" width="36" height="33" align="absmiddle">&nbsp;&nbsp;&nbsp;&nbsp;LegendShop安装成功！</span></td>
      </tr>
      <tr>
        <td valign="top" style="line-height:20px;"><p><br>
          * <span class="STYLE4">为了确保您网站安全，安装成功后，请把install目录删除</span><br>
          <br>
          <br><b>LegendShop<b/> 默认安装已经带有4个网店<br>
     <input name="Submit" type="button" class="btn" value="legenddesign" onClick="go('../shop/legenddesign')"> 默认店铺 ,如果不带后缀则显示该店铺。  朗信家居 legenddesign，密码 legenddesign<br>
      <input name="Submit" type="button" class="btn" value="360buy" onClick="go('../shop/360buy')">天天购物网 360buy，密码 360buy<br>
      <input name="Submit" type="button" class="btn" value="123done" onClick="go('../shop/123done')">LegendShop功能说明网站 123done，密码 123done<br>
      <input name="Submit" type="button" class="btn" value="demo" onClick="go('../shop/demo')">空店铺模板 demo，密码 demo<br>
          <br>
      <input name="Submit" type="button" class="btn" value="权限管理系统" onClick="go('../managerWeb')">超级管理员 root，密码 root<br>
          <br><br>
          	你也可以&nbsp;<a href="javascript:go('../reg.do')"><b>注册<b/></a>&nbsp; 一个用户并拥有你自己的独立网店，试试看。
          </p>
          </td>
      </tr>
      <tr>
        <td align="center" valign="middle">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td colspan="2" align="center" valign="middle"><hr width="100%" size="1" noshade></td>
  </tr>
  <tr>
    <td colspan="2" align="center" valign="middle">LegendShop - All Rights Reserved.</td>
  </tr>
</table>
</body>
</html>
