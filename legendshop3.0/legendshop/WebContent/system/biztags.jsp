<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file='/pages/common/taglib.jsp'%>
<%@include file='/pages/common/localeBundle.jsp'%>
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
				<lb:sort var="sort" loadAll="true">
				<tr><td>${sortIndex}</td><td>${sort.sortId}</td><td>${sort.sortName}</td>
				
				<td>
				<c:forEach items="${sort.nsort}" var="nsort" >${nsort}<br></c:forEach>
				</td></tr>
				</lb:sort>
			</table>
			</td>
		</tr>
				<tr>
			<td>com.legendshop.core.tag.I18nTag（i18n）</td>
			<td>用法</td>
			<td>示例</td>
		</tr>
				<tr>
			<td>com.legendshop.core.tag.I18nTag（i18n）</td>
			<td>用法</td>
			<td>示例</td>
		</tr>
				<tr>
			<td>com.legendshop.core.tag.I18nTag（i18n）</td>
			<td>用法</td>
			<td>示例</td>
		</tr>
				<tr>
			<td>com.legendshop.core.tag.I18nTag（i18n）</td>
			<td>用法</td>
			<td>示例</td>
		</tr>
	</table>
</body>
</html>