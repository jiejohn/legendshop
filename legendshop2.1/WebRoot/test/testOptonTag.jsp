<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/options.tld" prefix="option" %>
<%@ include file="/common/jsp/common.jsp"%>
<%@ include file="/common/jsp/taglib.jsp"%>
	<script type="text/javascript" src="${root}/dwr/engine.js" ></script>
	<script type="text/javascript" src="${root}/dwr/util.js" ></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <title>OptionTag测试</title>
    
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
  <br>1.select 
 	           <select id="status" name="status">
				  <option:optionGroup type="select" required="false" cache="true"
	                beanName="NEW_STAUS" defaultDisp="-- 状态 --" selectedValue="1"/>
	            </select>
	            <br>2. radio 
	            	<option:optionGroup type="radio" required="false" cache="true"
	                beanName="NEW_STAUS" defaultDisp="-- 状态 --" name="radio" selectedValue="1"/>
	                <br>3. json 
	               <option:optionGroup type="json" required="true" cache="true"
	                beanName="NEW_STAUS" defaultDisp="-- 状态 --" selectedValue="1"/>
	               <br>4. checkbox
	               <option:optionGroup type="checkbox" cache="true"
	                beanName="NEW_STAUS" defaultDisp="-- 状态 --" name="checkbox" selectedValue="1"/>
	                <br>5.label 
	               <option:optionGroup type="label" cache="true"
	                beanName="NEW_STAUS" selectedValue="1"/>
                        
  </body>
</html>
