<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ taglib uri="/WEB-INF/tld/options.tld" prefix="option"%>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
<head>
    <script type='text/javascript' src="<ls:templateResource item='/dwr/engine.js'/>"></script>
    <script type='text/javascript' src="<ls:templateResource item='/dwr/util.js'/>"></script>
    <script type='text/javascript' src="<ls:templateResource item='/dwr/interface/optionService.js'/>"></script>
    <script src="<ls:templateResource item='/plugins/My97DatePicker/WdatePicker.js'/>" type="text/javascript"></script>

    <style type="text/css" media="all">
       @import url("<ls:templateResource item='/common/default/css/screen.css'/>");
    </style>
    <title>用户事件列表</title>
</head>
<body>
    <form action="<ls:url address='/admin/event/query'/>" id="form1" method="post">
        <table class="${tableclass}" style="width: 100%">
		    <thead>
		    	<tr>
			    	<td>
				    	<a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 日志管理  &raquo; 
				    	<a href="<ls:url address='/admin/event/query'/>">系统日志</a>
			    	</td>
		    	</tr>
		    </thead> 
		    <tbody><tr><td>
				<input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
				<div align="left" style="padding: 5px;">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;操作人<input type="text" name="modifyUser" maxlength="50" value="${event.modifyUser}" />&nbsp;
            					用户名称<input type="text" name="userName" maxlength="50" value="${event.userName}" />&nbsp;
				</div>
				<div align="left" style="padding: 5px;">
				            &nbsp;开始时间
							 <input readonly="readonly"  name="startTime" id="startTime" class="Wdate" type="text" onClick="WdatePicker()" value='<fmt:formatDate value="${event.startTime}" pattern="yyyy-MM-dd"/>' />
							&nbsp;结束时间 
							 <input readonly="readonly" name="endTime" id="endTime" class="Wdate" type="text" onClick="WdatePicker()" value='<fmt:formatDate value="${event.endTime}" pattern="yyyy-MM-dd"/>' />
				            <input type="submit" value="搜索"/>
				</div>
 </td></tr></tbody>
	    </table>
    </form>
    <div align="center">
          <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
		<display:table name="list" requestURI="/admin/event/query" id="item" export="false" class="${tableclass}" style="width:100%">
	  <display:column title="操作人" property="modifyUser"></display:column>
      <display:column title="用户名称" property="userName"></display:column>
      <display:column title="对象" property="type"></display:column>
      <display:column title="类型" property="operation"></display:column>
      <display:column title="对应类型的ID" property="relateId"></display:column>
      <display:column title="时间" property="createTime"   sortable="true" sortName="createTime"></display:column>
	    <display:column title="操作" media="html" style="width: 60px">
		      <a href="<ls:url address='/admin/event/load/${item.eventId}'/>" title="修改">
		     		 <img alt="修改" src="<ls:templateResource item='/common/default/images/grid_edit.png'/>">
		      </a>
		      <!-- 
		      <a href='javascript:deleteById("${item.eventId}")' title="删除">
		      		<img alt="删除" src="<ls:templateResource item='/common/default/images/grid_delete.png'/>">
		      </a>
		       -->
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


