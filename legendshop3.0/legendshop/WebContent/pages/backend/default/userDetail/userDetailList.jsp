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
		<script type='text/javascript' src="<ls:templateResource item='/dwr/util.js'/>"></script>
		<script src="<ls:templateResource item='/common/js/default/alternative.js'/>" type="text/javascript"></script>
<title>用户详细信息列表</title>
</head>
<body>
	<%Integer offset = (Integer)request.getAttribute("offset");%>
    <table class="${tableclass}" style="width: 100%">
    <thead>
    	<tr><td><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 用户管理  &raquo; <a href="${pageContext.request.contextPath}/system/userDetail/query${applicationScope.WEB_SUFFIX}">用户信息管理</a></td></tr>
    </thead>
    </table>
<form action="${pageContext.request.contextPath}/system/userDetail/query${applicationScope.WEB_SUFFIX}" id="form1" name="form1">
<input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}">
			&nbsp;用户名
			<input type="text" name="userName" maxlength="50" value="${requestScope.userName}" />
			<auth:auth ifAllGranted="F_OPERATOR">
			&nbsp;邮件
				<input type="text" name="userMail" maxlength="50" value="${requestScope.userMail}" />
			</auth:auth>
			    &nbsp;用户类型 
	            <select id="haveShop" name="haveShop">
					<option value="">请选择</option>	
			    	<option value="1">网店用户</option>	
	      			<option value="0" >普通用户</option>
			</select>
			&nbsp;状态 
			<select id="enabled" name="enabled">
					<option value="">请选择</option>	
			    	<option value="1">有效</option>	
	      			<option value="0" >无效</option>
			</select>
			<input type="submit" value="搜索"/>
</form>

<div align="center">
        <%@ include file="/pages/common/messages.jsp"%>
    <display:table name="list" requestURI="/system/userDetail/query${applicationScope.WEB_SUFFIX}" id="item"
         export="true"  class="${tableclass}" style="width:100%" sort="external">
      <display:column style="width:70px" title="<input type='checkbox'  id='checkbox' name='checkbox' onClick='javascript:selAll()' />顺序"><input type="checkbox" name="strArray" value="${item.userId}" arg="${item.userName}"/><%=offset++%></display:column>
      <display:column title="用户名" property="userName" sortable="true" sortName="u.user_name"></display:column>
      <display:column title="用户昵称" property="nickName" sortable="true" sortName="u.nick_name"></display:column>
      <auth:auth ifAllGranted="F_OPERATOR">
      	<display:column title="邮件" property="userMail"></display:column>
      </auth:auth>
      <display:column title="注册IP" property="userRegip"></display:column>
      <display:column title="修改时间" sortable="true" sortName="u.modify_time">
     	 <fmt:formatDate value="${item.modifyTime}" pattern="yyyy-MM-dd HH:mm"/>
      </display:column>
      <display:column title="注册时间" sortable="true" sortName="u.user_regtime">
      	 <fmt:formatDate value="${item.userRegtime}" pattern="yyyy-MM-dd HH:mm"/>
      </display:column>
       <display:column title="状态" sortable="true" sortName="s.enabled" style="width:40px">
       <c:choose>
       		<c:when test="${item.enabled == 1}">有效</c:when>
       		<c:otherwise><font color="red">无效</font></c:otherwise>
       </c:choose>
      </display:column>
      <auth:auth ifAnyGranted="F_OPERATOR">
	      <display:column title="操作" media="html" style="width:75px">
	      <auth:auth ifAnyGranted="F_SECUREST">
	         <a href='${pageContext.request.contextPath}/myaccount${applicationScope.WEB_SUFFIX}?userName=${item.userName}' target="_blank" title="查看用户${item.userName}信息"><img alt="查看用户${item.userName}信息" src="${pageContext.request.contextPath}/common/images/default/ind_left_login.gif"></a>
	      </auth:auth>
	      <c:if test="${'C2C' == applicationScope.BUSINESS_MODE}">
	      <c:choose>
	      	<c:when test="${item.shopId != null}">
	      		<a href='${pageContext.request.contextPath}/admin/shopDetail/load/${item.shopId}${applicationScope.WEB_SUFFIX}?userName=${item.userName}' title="修改用户${item.userName}当前商城"><img alt="修改用户当前商城" src="<ls:templateResource item='/common/images/default/grid_edit.png'/> "></a>
	      	</c:when>
	      	<c:otherwise>
	      		<c:if test="${supportOpenShop == 'true' && 'C2C' == applicationScope.BUSINESS_MODE}">
	      			<a href='${pageContext.request.contextPath}/admin/shopDetail/load/0${applicationScope.WEB_SUFFIX}?userName=${item.userName}'><img alt="为用户${item.userName}增加商城" src="${pageContext.request.contextPath}/common/images/default/grid_add.png"></a>
	      		</c:if>
	      	</c:otherwise>
	      </c:choose>
	      </c:if>
  	 <a href='javascript:confirmDelete("${item.userId}","${item.userName}")' title="删除用户${item.userName}所有信息"><img alt="删除用户${item.userName}所有信息" src="<ls:templateResource item='/common/images/default/grid_delete.png'/> "></a>
	 </display:column>
      </auth:auth>
    </display:table> 
            <auth:auth ifAnyGranted="F_OPERATOR">
                      <input type="button" value="刪除" style="float: left" onclick="return deleteAction();"/>   
            </auth:auth>
     <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
    </div>
    
    <script language="JavaScript" type="text/javascript">
<!--

 function deleteAction()
{
	//获取选择的记录集合
	selAry = document.getElementsByName("strArray");
	if(!checkSelect(selAry)){
	 window.alert('删除时至少选中一条记录！');
	 return false;
	}
	if(confirm("确定要删除吗？")){
	DWREngine.setAsync(false);
	for(i=0;i<selAry.length;i++){
	  if(selAry[i].checked){
		var userId = selAry[i].value;
		var userName = selAry[i].getAttribute("arg");
			deleteMultiUserDetail(userId,userName);
		 }
		}
	DWREngine.setAsync(true);
	}
    window.location = '${pageContext.request.contextPath}/system/userDetail/query${applicationScope.WEB_SUFFIX}';
	return true;
}

  function confirmDelete(userId,userName)
	{
	   if(confirm("确定要删除 "+userName+" 吗？")){
	   	deleteUserDetail(userId,userName);
	   }
	}
	
  function deleteUserDetail(userId,userName) {
        CommonService.deleteUserDetail(userId,userName, function(retData){
	       if(retData == null ){
	          window.location.reload() ;
	       }else{
	          alert(retData) ;
	       }
	    }) ;
	}
	
	 function deleteMultiUserDetail(userId,userName) {
        CommonService.deleteUserDetail(userId,userName, function(retData){
	    }) ;
	}
	
	

    function pager(curPageNO){
            document.getElementById("curPageNO").value=curPageNO;
            document.getElementById("form1").submit();
        }
        
        	window.onload=function(){
        	var enabled = '${enabled}';
        	var haveShop = '${haveShop}';
        	initStatus(enabled,haveShop);
        	}
        	
        function initStatus(enabledValue,haveShopValue){
			DWRUtil.setValues({enabled:enabledValue});
			DWRUtil.setValues({haveShop:haveShopValue});
		}	
		
		highlightTableRows("item");  	
//-->
</script>
</body>
</html>

