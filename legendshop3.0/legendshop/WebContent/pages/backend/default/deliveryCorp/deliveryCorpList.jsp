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
    <title>物流公司列表</title>
</head>
<body>
    <form action="<ls:url address='/admin/deliveryCorp/query'/>" id="form1" method="post">
        <table class="${tableclass}" style="width: 100%">
		    <thead>
		    	<tr>
			    	<td>
				    	<a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商城管理  &raquo; 
				    	<a href="<ls:url address='/admin/deliveryCorp/query'/>">物流公司</a>
			    	</td>
		    	</tr>
		    </thead>
	    </table>
        <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
        	<auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
            	商城名称&nbsp;<input type="text" name="userName" maxlength="50" value="${deliveryCorp.userName}" />
            </auth:auth>
            	物流公司名称&nbsp;<input type="text" name="name" maxlength="50" value="${deliveryCorp.name}" />
            <input type="submit" value="搜索"/>
            <input type="button" value="创建物流公司" onclick='window.location="<ls:url address='/admin/deliveryCorp/load'/>"'/>
            <br>
    </form>
    <div align="center">
          <%@ include file="/pages/common/messages.jsp"%>
    <display:table name="list" requestURI="<ls:url address='/admin/deliveryCorp/query'/>" id="item" export="true" class="${tableclass}" style="width:100%">
      <display:column title="顺序">${item_rowNum}</display:column>
      <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
      	<display:column title="商城名称" property="userName"></display:column>            	
      </auth:auth>      
      <display:column title="公司名称" property="name"></display:column>
      <display:column title="URL" property="url"></display:column>
      <%-- <display:column title="创建时间" property="createTime"></display:column>
      <display:column title="修改时间" property="modifyTime"></display:column> --%>
      <display:column title="操作" media="html">
      <a href="<ls:url address='/admin/deliveryCorp/load/${item.dvyId}'/>" title="修改">
     		 <img alt="修改" src="<ls:templateResource item='/img/grid_edit.png'/>">
      </a>
      <a href='javascript:deleteById("${item.dvyId}")' title="删除">
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
            window.location = "<ls:url address='/admin/deliveryCorp/delete/" + id + "'/>";
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

