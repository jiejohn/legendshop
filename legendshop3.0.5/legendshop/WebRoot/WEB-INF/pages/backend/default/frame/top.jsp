<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@page import="com.legendshop.core.UserManager"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<head>
<title>top</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${pageContext.request.contextPath}/plugins/theme/skin/css/base.css" rel="stylesheet" type="text/css" />
<script language='javascript'>
var preFrameW = '206,*';
var FrameHide = 0;
var curStyle = 1;
var totalItem = 4;
function ChangeMenu(way){
	
	var addwidth = 10;
	var fcol = window.top.document.getElementById("btFrame").cols;//top.document.all.btFrame.cols;
	if(way==1) addwidth = 10;
	else if(way==-1) addwidth = -10;
	else if(way==0){
		if(FrameHide == 0){
			preFrameW = top.document.all.btFrame.cols;
			top.document.all.btFrame.cols = '0,*';
			FrameHide = 1;
			return;
		}else{
			top.document.all.btFrame.cols = preFrameW;
			FrameHide = 0;
			return;
		}
	}
	fcols = fcol.split(',');
	fcols[0] = parseInt(fcols[0]) + addwidth;
	top.document.all.btFrame.cols = fcols[0]+',*';
}


function mv(selobj,moveout,itemnum)
{
   if(itemnum==curStyle) return false;
   if(moveout=='m') selobj.className = 'itemsel';
   if(moveout=='o') selobj.className = 'item';
   return true;
}

function changeSel(itemnum)
{
  curStyle = itemnum;
  for(i=1;i<=totalItem;i++)
  {
     if(document.getElementById('item'+i)) document.getElementById('item'+i).className='item';
  }
  document.getElementById('item'+itemnum).className='itemsel';
}

function  logout()
{
   if  (confirm("确认退出管理系统?"))
   {
     document.location.href="${pageContext.request.contextPath}/p/logout";
   }
}
</script>
<style>
body { padding:0px; margin:0px; }
#tpa {
	color: #009933;
	margin:0px;
	padding:0px;
	float:right;
	padding-right:10px;
}

#tpa dd {
	margin:0px;
	padding:0px;
	float:left;
	margin-right:2px;
}

#tpa dd.ditem {
	margin-right:8px;
}

#tpa dd.img {
  padding-top:6px;
}

div.item
{
  text-align:center;
	background:url(${pageContext.request.contextPath}/plugins/theme/skin/images/frame/topitembg.gif) 0px 3px no-repeat;
	width:82px;
	height:26px;
	line-height:29px;
}

.itemsel {
  width:80px;
  text-align:center;
  background:#226411;
	border-left:1px solid #c5f097;
	border-right:1px solid #c5f097;
	border-top:1px solid #c5f097;
	height:26px;
	line-height:28px;
}

*html .itemsel {
	height:26px;
	line-height:26px;
}

a:link,a:visited {
 text-decoration: underline;
}

.item a:link, .item a:visited {
	font-size: 12px;
	color: #ffffff;
	text-decoration: none;
	font-weight: bold;
}

.itemsel a:hover {
	color: #ffffff;
	font-weight: bold;
	border-bottom:2px solid #E9FC65;
}

.itemsel a:link, .itemsel a:visited {
	font-size: 12px;
	color: #ffffff;
	text-decoration: none;
	font-weight: bold;
}

.itemsel a:hover {
	color: #ffffff;
	border-bottom:2px solid #E9FC65;
}

.rmain {
  padding-left:10px;
  /* background:url(skin/images/frame/toprightbg.gif) no-repeat; */
}
</style>
</head>
<body bgColor='#ffffff'>
<table width="100%"  cellpadding="0" cellspacing="0" background="${pageContext.request.contextPath}/plugins/theme/skin/images/frame/topbg.gif">
  <tr>
    <td width='20%' height="60"><!-- <img src="skin/images/frame/logo.gif" />  --></td>
    <td width='80%' align="right" valign="bottom">
    	<table width="750"  cellspacing="0" cellpadding="0">
      <tr>
      <td align="right" height="26" style="padding-right:10px;line-height:26px;">
      	         当前用户：<b>${sessionScope.SPRING_SECURITY_LAST_USERNAME}</b> &nbsp;
      	    <span class="username"><a href="${pageContext.request.contextPath}/all${applicationScope.WEB_SUFFIX}"" target="_parent">搜索商城</a></span>
      	    <span class="username"><a href="${pageContext.request.contextPath}/index${applicationScope.WEB_SUFFIX}" target="_parent">返回前台</a></span>
      	    <c:if test="${'C2C' == applicationScope.BUSINESS_MODE}">
            <span class="username"><a href="${pageContext.request.contextPath}/shop/${sessionScope.SPRING_SECURITY_LAST_USERNAME}${applicationScope.WEB_SUFFIX}" target="_parent">我的商城</a></span>
            </c:if>
        	<span class="username"><a href="${pageContext.request.contextPath}/p/logout" target="_parent">退出</a></span>
      </td>
      </tr>
      <tr>
        <td align="right" height="34" class="rmain">
		<dl id="tpa">
		<dd class='img'><a href="javascript:ChangeMenu(-1);"><img vspace="5" src="${pageContext.request.contextPath}/plugins/theme/skin/images/frame/arrl.gif"  width="5" height="8" alt="Reduce left frame"  title="Reduce left frame" /></a></dd>
		<dd class='img'><a href="javascript:ChangeMenu(0);"><img vspace="3" src="${pageContext.request.contextPath}/plugins/theme/skin/images/frame/arrfc.gif"  width="12" height="12" alt="Show/Hide left frame" title="Show/Hide left frame" /></a></dd>
		<dd class='img' style="margin-right:10px;"><a href="javascript:ChangeMenu(1);"><img vspace="5" src="${pageContext.request.contextPath}/plugins/theme/skin/images/frame/arrr.gif"  width="5" height="8" alt="Add left frame" title="Add left frame" /></a></dd>
		<dd><div class='itemsel' id='item1' onMouseMove="mv(this,'m',1);" onMouseOut="mv(this,'o',1);"><a href="${pageContext.request.contextPath}/admin/menu/1${applicationScope.WEB_SUFFIX}" onclick="changeSel(1)" target="menu">商品管理</a></div></dd>
		<dd><div class='item' id='item2' onMouseMove="mv(this,'m',2);" onMouseOut="mv(this,'o',2);"><a href="${pageContext.request.contextPath}/admin/menu/2${applicationScope.WEB_SUFFIX}" onclick="changeSel(2)" target="menu">商城管理</a></div></dd>
		 <auth:auth ifAnyGranted="F_SYSTEM">
		<dd><div class='item' id='item3' onMouseMove="mv(this,'m',3);" onMouseOut="mv(this,'o',3);"><a href="${pageContext.request.contextPath}/admin/menu/0${applicationScope.WEB_SUFFIX}" onclick="changeSel(3)" target="menu">用户管理</a></div></dd>
		<dd><div class='item' id='item4' onMouseMove="mv(this,'m',4);" onMouseOut="mv(this,'o', 4);"><a href="${pageContext.request.contextPath}/admin/menu/3${applicationScope.WEB_SUFFIX}" onclick="changeSel(4)" target="menu">系统管理</a></div></dd>
		</auth:auth>
		</dl>
		</td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>