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
    <title>Event列表</title>
</head>
<body>
    <form action="<ls:url address='/admin/event/query'/>" id="form1" method="post">
        <table class="${tableclass}" style="width: 100%">
		    <thead>
		    	<tr>
			    	<td>
				    	<a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商城管理  &raquo; 
				    	<a href="<ls:url address='/admin/event/query'/>">Event</a>
			    	</td>
		    	</tr>
		    </thead>
	    </table>
        <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
        	<auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
            	商城名称&nbsp;<input type="text" name="userName" maxlength="50" value="${event.userName}" />
            </auth:auth>
            <input type="submit" value="搜索"/>
            <input type="button" value="创建Event" onclick='window.location="<ls:url address='/admin/event/load'/>"'/>
            <br>
    </form>
    <div align="center">
          <%@ include file="/pages/common/messages.jsp"%>
		<display:table name="list" requestURI="/admin/event/query" id="item" export="false" class="${tableclass}" style="width:100%">
	             <display:column title="EventId" property="eventId"></display:column>
      <display:column title="UserId" property="userId"></display:column>
      <display:column title="UserName" property="userName"></display:column>
      <display:column title="Type" property="type"></display:column>
      <display:column title="Operation" property="operation"></display:column>
      <display:column title="RelateData" property="relateData"></display:column>
      <display:column title="CreateTime" property="createTime"></display:column>

	    <display:column title="Action" media="html">
		      <a href="<ls:url address='/admin/event/load/${item.eventId}'/>" title="修改">
		     		 <img alt="修改" src="<ls:templateResource item='/img/grid_edit.png'/>">
		      </a>
		      <a href='javascript:deleteById("${item.eventId}")' title="删除">
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
			            window.location = "<ls:url address='/admin/event/delete/" + id + "'/>";
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


