<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/back-common.jsp"%>
<%@ include file="/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ taglib uri="/WEB-INF/tld/options.tld" prefix="option"%>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
<head>
  		<script type='text/javascript' src="<ls:templateResource item='/dwr/interface/CommonService.js'/>"></script>
  		<script type='text/javascript' src="<ls:templateResource item='/dwr/engine.js'/>"></script>
		<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/util.js'></script>
		<script src="<ls:templateResource item='/css/alternative.js'/>" type="text/javascript"></script>
<script language="JavaScript" type="text/javascript">
<!--
  function confirmDelete()
	{
	    return con = confirm("确定要删除吗？");
	}
  function selAll(){
    chk = document.getElementById("checkbox");
	allSel = document.getElementsByName("strArray");
	
	if(!chk.checked)
		for(i=0;i<allSel.length;i++)
		{
			   allSel[i].checked=false;
		}
	else
		for(i=0;i<allSel.length;i++)
		{
			   allSel[i].checked=true;
		}
	}
	function  saveLeastOne()
{
	 //获取选择的记录集合
	selAry = document.getElementsByName("strArray");
	count = 0;
	selValue = "";
	//判断是否只有一条记录被选中
	for(i=0;i<selAry.length;i++)
	{
		if(selAry[i].checked)
		{
			count++;
			selValue = selAry[i].value;
		}
	}
	if(count==0)
	{
		window.alert('至少选中一条记录！');
	}
	else
	{
      return true;
	}
	  return false;
} 
//-->
</script>
<title>角色列表</title>
</head>
<body>
<%int offset=((Integer)request.getAttribute("offset")).intValue();%>

    <table class="${tableclass}" style="width: 100%">
    <thead>
    	<tr><td><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 用户管理  &raquo; <a href="${pageContext.request.contextPath}/member/user/query${applicationScope.WEB_SUFFIX}">权限用户管理</a>&raquo;增加用户[${bean.name }]角色</td></tr>
    </thead>
    </table>
<form action="${pageContext.request.contextPath}/member/user/saveRoleToUser${applicationScope.WEB_SUFFIX}" id="form1" name="form1">
<input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}">
<input type="hidden" name="userId" value="${bean.id }">
 

	 <div align="center">
        <%@ include file="/pages/common/messages.jsp"%>
        
    <display:table name="list" id="item" export="true" class="${tableclass}" style="width:100%">
      <display:column style="width:70px" title="<input type='checkbox'  id='checkbox' name='checkbox' onClick='javascript:selAll()' />顺序"><input type="checkbox" name="strArray" value="${item.id}" /><%=offset++%></display:column>
      <display:column title="名称 " property="name" sortable="true"></display:column>
      <display:column title="角色名称 " property="roleType" sortable="true"></display:column>
      <display:column title="状态">
      <option:optionGroup type="label" required="true" cache="true"
	                beanName="ENABLED" selectedValue="${item.enabled}" defaultDisp=""/>
      </display:column>
      <display:column title="备注" property="note"></display:column>
    </display:table>
     </div>
 
 
	<div align="center">
		<input type="submit" value="添加" onclick="return saveLeastOne();"/>
	</div>
	</form>
	<div align="center">
	<c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
	</div>  
</body>
</html>

