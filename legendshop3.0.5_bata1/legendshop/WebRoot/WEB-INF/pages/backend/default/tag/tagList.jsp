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
    <style type="text/css" media="all">
       @import url("<ls:templateResource item='/css/screen.css'/>");
    </style>
    <title>Tag列表</title>
</head>
<body>
    <form action="<ls:url address='/admin/tag/query'/>" id="form1" method="post">
        <table class="${tableclass}" style="width: 100%">
		    <thead>
		    	<tr>
			    	<td>
				    	<a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商城管理  &raquo; 
				    	<a href="<ls:url address='/admin/tag/query'/>">Tag</a>
			    	</td>
		    	</tr>
		    </thead>
	    </table>
        <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
        	<auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
            	商城名称&nbsp;<input type="text" name="userName" maxlength="50" value="${tag.userName}" />
            </auth:auth>
            <input type="submit" value="搜索"/>
            <input type="button" value="创建Tag" onclick='window.location="<ls:url address='/admin/tag/load'/>"'/>
            <br>
    </form>
    <div align="center">
          <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
		<display:table name="list" requestURI="/admin/tag/query" id="item" export="false" class="${tableclass}" style="width:100%">
	             <display:column title="TagId" property="tagId"></display:column>
      <display:column title="Name" property="name"></display:column>
      <display:column title="SortId" property="sortId"></display:column>
      <display:column title="NewsCategoryId" property="newsCategoryId"></display:column>
      <display:column title="Type" property="type"></display:column>
      <display:column title="Status" property="status"></display:column>
      <display:column title="CreateTime" property="createTime"></display:column>
      <display:column title="UserId" property="userId"></display:column>
      <display:column title="UserName" property="userName"></display:column>

	    <display:column title="Action" media="html">
		      <a href="<ls:url address='/admin/tag/load/${item.tagId}'/>" title="修改">
		     		 <img alt="修改" src="<ls:templateResource item='/img/grid_edit.png'/>">
		      </a>
		      <a href='javascript:deleteById("${item.tagId}")' title="删除">
		      		<img alt="删除" src="<ls:templateResource item='/img/grid_delete.png'/>">
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


