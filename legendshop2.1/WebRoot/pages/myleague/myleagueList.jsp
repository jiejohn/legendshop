<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common.jsp"%>
<%@ include file="/common/jsp/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ taglib uri="/WEB-INF/tld/options.tld" prefix="option"%>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
<head>
    <script type='text/javascript' src='${root}/dwr/interface/CommonService.js'></script>
    <script type='text/javascript' src='${root}/dwr/interface/optionService.js'></script>
    <script type='text/javascript' src='${root}/dwr/engine.js'></script>
    <script type='text/javascript' src='${root}/dwr/util.js'></script>
    <style type="text/css" media="all">
       @import url("${root}/css/screen.css");
    </style>
    <script language="JavaScript" type="text/javascript">
<!--

  function deleteById(id) {
      if(confirm("  确定删除 ?")){
            window.location = "${root}/admin/myleague/delete/"+id+".c";
        }
    }

        function pager(curPageNO){
            document.getElementById("curPageNO").value=curPageNO;
            document.getElementById("form1").submit();
        }
//-->
</script>

<jsp:scriptlet> String lClass = "mars";
   if( request.getParameter( "class" ) != null ) {
      lClass = request.getParameter( "class" );
      if (!("isis".equals(lClass) || "its".equals(lClass) || "mars".equals(lClass) || "simple".equals(lClass) || "report".equals(lClass) || "mark".equals(lClass)))
      {
        lClass="";
      }
   }
   pageContext.setAttribute("tableclass", lClass);
</jsp:scriptlet>

    <title>加盟店列表</title>
</head>
<body class="bodymargin">
    <%
        Integer offset = (Integer) request.getAttribute("offset");
    %>
    <form action="${root}/admin/myleague/query.c" id="form1" method="post">
        <table class="mars" style="width: 100%">
	    <thead>
	    	<tr><td>商家管理  >> 加盟店管理</td></tr>
	    </thead>
	    </table>

            <!-- 
            	<select id="status" name="status">
				  <option:optionGroup type="select" required="true" cache="true"
	                beanName="LEAGUE_STAUS" selectedValue="${bean.status}"/>
	            </select>
	             -->
	         <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">&nbsp;店铺
               <input type="text" name="userId" maxlength="50" value="${bean.userId}" />
             </auth:auth>
            <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />加盟店
            <input type="text" name="friendId" maxlength="50" value="${bean.friendId}" />
	          <input type="submit" value="搜索"/>

    </form>
    <div align="center">
        <%@ include file="/common/jsp/messages.jsp"%>
    <display:table name="list" requestURI="/admin/myleague/query.c" id="item"
         export="false" class="${tableclass}" style="width:100%">
      <display:column title="顺序" class="orderwidth"><%=offset++%></display:column>
      <display:column title="加盟店" property="friendId"></display:column>
      <display:column title="加盟店说明" property="friendName"></display:column>
      <display:column title="显示顺序" property="displayOrder" sortable="true"></display:column>
      <display:column title="申请时间" property="addtime" sortable="true"></display:column>
       <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
        <display:column title="店铺" property="userId" sortable="true"></display:column>
      </auth:auth>
      <display:column title="操作" media="html" class="actionwidth">
      <a href= "${root}/admin/myleague/load/${item.id}.c"><img alt="修改" src="${root}/img/grid_edit.png"></a>
      <a href='javascript:deleteById("${item.id}")'><img alt="删除" src="${root}/img/grid_delete.png"></a>
      </display:column>
    </display:table>
        <c:if test="${not empty toolBar}">
            <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
        </c:if>
    </div>
</body>
</html>

