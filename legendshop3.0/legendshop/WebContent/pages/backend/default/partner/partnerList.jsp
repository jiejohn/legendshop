<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/back-common.jsp"%>
<%@ include file="/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ taglib uri="/WEB-INF/tld/options.tld" prefix="option"%>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
<head>
    <script type='text/javascript' src="<ls:templateResource item='/dwr/interface/CommonService.js'/>"></script>
    <script type='text/javascript' src="<ls:templateResource item='/dwr/interface/optionService.js'/>"></script>
    <script type='text/javascript' src="<ls:templateResource item='/dwr/engine.js'/>"></script>
    <script type='text/javascript' src="<ls:templateResource item='/dwr/util.js'/>"></script>
    <style type="text/css" media="all">
       @import url("<ls:templateResource item='/css/screen.css'/>");
    </style>
    <title>供应商列表</title>
</head>
<body>
    <form action="<ls:url address='/admin/partner/query'/>" id="form1" method="post">
        <table class="${tableclass}" style="width: 100%">
		    <thead>
		    	<tr>
			    	<td>
				    	<a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商城管理  &raquo; 
				    	<a href="<ls:url address='/admin/partner/query'/>">供应商管理</a>
			    	</td>
		    	</tr>
		    </thead>
	    </table>
        <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
        	<auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
            	商城名称&nbsp;<input type="text" name="userName" maxlength="50" value="${partner.userName}" />
            </auth:auth>
            <input type="submit" value="搜索"/>
            <input type="button" value="创建供应商" onclick='window.location="<ls:url address='/admin/partner/load'/>"'/>
            <br>
    </form>
    <div align="center">
          <%@ include file="/pages/common/messages.jsp"%>
		<display:table name="list" requestURI="/admin/partner/query" id="item" export="false" class="${tableclass}" style="width:100%">
	<display:column title="顺序">${item_rowNum}</display:column>
      <display:column title="登录名" property="partnerName"></display:column>
      <display:column title="名称" property="title"></display:column>
      <display:column title="主页" property="homepage"></display:column>
      <display:column title="银行名称" property="bankName"></display:column>
      <display:column title="所在位置" property="location"></display:column>
      <display:column title="联系人" property="contact"></display:column>
      <display:column title="联系电话" property="phone"></display:column>
      <display:column title="展示相关信息"> 
      	<option:optionGroup type="label" required="true" cache="true"
	                beanName="YES_NO" selectedValue="${item.open}" defaultDisp=""/>
      </display:column>
      <display:column title="状态">      
      	<option:optionGroup type="label" required="true" cache="true"
	                beanName="ENABLED" selectedValue="${item.status}" defaultDisp=""/>
      </display:column>
      <display:column title="显示首页">      
      	<option:optionGroup type="label" required="true" cache="true"
	                beanName="YES_NO" selectedValue="${item.display}" defaultDisp=""/>
      </display:column>
      <display:column title="评论满意" property="commentGood"></display:column>
      <display:column title="评论一般" property="commentNone"></display:column>
      <display:column title="评论失望" property="commentBad"></display:column>

	    <display:column title="操作" media="html">
		      <a href="<ls:url address='/admin/partner/load/${item.partnerId}'/>" title="修改">
		     		 <img alt="修改" src="<ls:templateResource item='/img/grid_edit.png'/>">
		      </a>
		      <a href='javascript:deleteById("${item.partnerId}")' title="删除">
		      		<img alt="删除" src="<ls:templateResource item='/img/grid_delete.png'/>">
		      </a>
		      <a>修改密码</a>
	      </display:column>
	    </display:table>
        <c:if test="${not empty toolBar}">
            <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
        </c:if>
    </div>
        <script language="JavaScript" type="text/javascript">
			<!--
			  function deleteById(id) {
			      if(confirm("  确定删除 ?")){
			            window.location = "<ls:url address='/admin/partner/delete/" + id + "'/>";
			        }
			    }
			
			        function pager(curPageNO){
			            document.getElementById("curPageNO").value=curPageNO;
			            document.getElementById("form1").submit();
			        }
			//-->
		</script>
</body>
</html>


