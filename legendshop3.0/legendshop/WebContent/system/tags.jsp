<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file='/pages/common/taglib.jsp'%>
<%@include file='/pages/common/localeBundle.jsp'%>
<%@ taglib uri="http://www.legendesign.net/tags" prefix="ls"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<title>LegendShop Tags Example</title>
</head>
<body>
	<table border="1" width="100%">
		<tr>
			<td>Tags 名称</td>
			<td>用法</td>
			<td>示例</td>
		</tr>
		<tr>
			<td>国际化com.legendshop.core.tag.I18nTag（i18n）</td>
			<td>
				支持无参数和有参数两种，根据Locale从对应的文件拿到记录<br>
				支持参数嵌套，例如message:user.name
			</td>
			<td>1.无参数<br><ls:i18n key="user.name"/>
			    <br><br>
			    2.有参数，动态参数名,start with message: will replace with locale message<br>
			    <ls:i18n key="errors.minlength" name="message:user.name" length="8"/> 
			      <br><br>
			    </td>
		</tr>
				<tr>
			<td>地址com.legendshop.core.tag.URLTag（url）</td>
			<td>主动加上前后缀：ContextPath + address + WEB_SUFFIX</td>
			<td>
				<a href="<ls:url address='/index'/>" target="blank">首页</a>
			</td>
		</tr>
				<tr>
			<td>com.legendshop.core.tag.TemplateResourceTag（templateResource）</td>
			<td>资源定位器，主要针对css js 图片等资源</td>
			<td>
				<style type="text/css">@import url( <ls:templateResource item='/styles/SyntaxHighlighter.css'/> );</style>
				<br><img src="<ls:templateResource item='/img/addtocart.jpg'/>" />
			</td>
		</tr>
				<tr>
			<td>com.legendshop.core.tag.SettingsTag（settings）</td>
			<td>用于显示系统参数配置</td>
			<td><ls:settings key="USE_SCORE" >USE_SCORE is true</ls:settings><br>
			<ls:settings key="LOGIN_LOG_ENABLE" >VISIT_HW_LOG_ENABLE is true</ls:settings><br>
			<ls:settings key="OPEN_SHOP" >OPEN_SHOP is true</ls:settings><br>
			<ls:settings key="VISIT_HW_LOG_ENABLE" >VISIT_HW_LOG_ENABLE is true</ls:settings><br>
			
			</td>
		</tr>
				<tr>
			<td>com.legendshop.core.tag.AuthorizeActionTag（auth）</td>
			<td>权限管理</td>
			<td><ls:auth ifAnyGranted="F_VIEW_ALL_DATA">have access right F_VIEW_ALL_DATA</ls:auth></td>
		</tr>
				<tr>
			<td>com.legendshop.core.tag.OptionGroupTag（optionGroup）</td>
			<td>用法</td>
			<td>
			<select id="status" name="status">
				<ls:optionGroup type="select" required="true" cache="true"
		                beanName="SHOP_STATUS" selectedValue="${bean.status}"/>
	         </select>
	                </td>
		</tr>
	</table>
</body>
</html>