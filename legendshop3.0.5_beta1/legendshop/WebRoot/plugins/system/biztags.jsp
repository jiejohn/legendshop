<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<%@include file='/WEB-INF/pages/common/localeBundle.jsp'%>
<%@ taglib uri="http://www.legendesign.net/tags" prefix="ls"%>
<%@ taglib uri="http://www.legendesign.net/biz" prefix="lb"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<title>LegendShop Biz Tags Example</title>
</head>
<body>
	<table border="1" width="100%">
		<tr>
			<td>Biz Tags名称</td>
			<td>用法</td>
			<td>示例</td>
		</tr>
		<tr>
			<td>com.legendshop.business.tag.SortTag（sort）</td>
			<td>用法</td>
			<td>
			<table border="1" style="border-collapse: collapse;"> 
				<td>Sort List</td><td>sortId</td><td>sortName</td><td>nsort size</td> 
				<lb:sort var="sort" loadAll="true" sortType="P">
				<tr><td>${sortIndex}</td><td>${sort.sortId}</td><td>${sort.sortName}</td>
				<td>
				<c:forEach items="${sort.nsort}" var="nsort" >${nsort.nsortName}<br></c:forEach>
				</td></tr>
				</lb:sort>
			</table>
			</td>
		</tr>
		<tr>
			<td>com.legendshop.business.tag.ShopDetailTag（ShopDetailTag）</td>
			<td>用法</td>
			<td>
				<lb:shopDetail var="shopDetail" />
				${shopDetail.siteName}
				
			</td>
		</tr>
				<tr>
			<td>com.legendshop.core.tag.CurrentShopTag</td>
			<td>用法：当前商城名称</td>
			<td><lb:currentShop /></td>
		</tr>
				<tr>
			<td>com.legendshop.core.tag.CurrentUserTag</td>
			<td>用法：当前用户</td>
			<td><lb:currentUser /></td>
		</tr>
		<tr>
			<td>com.legendshop.core.tag.LastLogingUserTag</td>
			<td>用法：最后登录用户</td>
			<td><lb:lastLogingUser/></td>
		</tr>
				<tr>
			<td>com.legendshop.core.tag.UserLoginedTag</td>
			<td>用法：用户是否已经登录</td>
			<td><lb:logined >当前登录用户: <lb:currentUser /></lb:logined></td>
		</tr>
		<tr>
			<td>com.legendshop.business.tag.UserValidationImageTag</td>
			<td>用法：需要验证码</td>
			<td><lb:userValidationImage>本次需要验证码</lb:userValidationImage></td>
		</tr>		
		
	</table>
</body>
</html>