<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ taglib uri="/WEB-INF/tld/options.tld" prefix="option"%>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
    <script type='text/javascript' src="<ls:templateResource item='/dwr/engine.js'/>"></script>
    <script type='text/javascript' src="<ls:templateResource item='/dwr/util.js'/>"></script>
    <script type='text/javascript' src="<ls:templateResource item='/dwr/interface/optionService.js'/>"></script>
    <title>标签列表</title>
</head>
<body>
    <form action="<ls:url address='/admin/tag/query'/>" id="form1" method="post">
        <table class="${tableclass}" style="width: 100%">
		    <thead>
		    	<tr>
			    	<td>
				    	<a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商城管理  &raquo; 
				    	<a href="<ls:url address='/admin/tag/query'/>">标签管理</a>
			    	</td>
		    	</tr>
		    </thead>
		     <tbody><tr><td>
 <div align="left" style="padding: 3px">
<input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
          标签名称&nbsp;<input type="text" name="name" maxlength="50" value="${tag.name}" />
        	<auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
            	商城名称&nbsp;<input type="text" name="userName" maxlength="50" value="${tag.userName}" />
            </auth:auth>
            <input type="submit" value="搜索"/>
            <input type="button" value="创建标签" onclick='window.location="<ls:url address='/admin/tag/load'/>"'/>
 </div>
 </td></tr></tbody>
	    </table>
        
    </form>
    <div align="center">
          <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
		<display:table name="list" requestURI="/admin/tag/query" id="item" export="false" class="${tableclass}" style="width:100%">
      <display:column title="名称" property="name"></display:column>
      <display:column title="商品分类" property="sortName"></display:column>
      <display:column title="新闻栏目" property="newsCategoryName"></display:column>
       <display:column title="状态" style="width:40px">
      <font color="red">
				  <option:optionGroup type="label" required="false" cache="true"
	                beanName="SHOP_STATUS" selectedValue="${item.status}" defaultDisp=""/></font>
      </display:column>
     <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
     <display:column title="商城" property="userName" sortable="true" sortName="userName" style="width: 100px">
     	<a href="${pageContext.request.contextPath}/shop/${item.userName}" target="_blank">${item.userName}</a>
     </display:column>
     </auth:auth>
	    <display:column title="操作" media="html">
		      <a href="<ls:url address='/admin/tag/load/${item.tagId}'/>" title="修改">
		     		 <img alt="修改" src="<ls:templateResource item='/common/default/images/grid_edit.png'/>">
		      </a>
		      <a href='javascript:deleteById("${item.tagId}")' title="删除">
		      		<img alt="删除" src="<ls:templateResource item='/common/default/images/grid_delete.png'/>">
		      </a>
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
			            window.location = "<ls:url address='/admin/tag/delete/" + id + "'/>";
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


