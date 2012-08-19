<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ taglib uri="/WEB-INF/tld/options.tld" prefix="option"%>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
<head>
    <script type='text/javascript' src="<ls:templateResource item='/dwr/interface/CommonService.js'/>"></script>
    <script type='text/javascript' src="<ls:templateResource item='/dwr/interface/optionService.js'/>"></script>
    <script type='text/javascript' src="<ls:templateResource item='/dwr/engine.js'/>"></script>
   <script type='text/javascript' src="<ls:templateResource item='/dwr/util.js'/>"></script>
    <script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
    <title>系统配置参数列表</title>
</head>
<body>
    <%
        Integer offset = (Integer) request.getAttribute("offset");
    %>
	 <table class="${tableclass}" style="width: 100%">
	    <thead>
	    	<tr><td><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 系统管理  &raquo; <a href="${pageContext.request.contextPath}/system/systemParameter/query${applicationScope.WEB_SUFFIX}">系统参数配置管理</a></td></tr>
	    </thead>
	    </table>
    <form action="${pageContext.request.contextPath}/system/systemParameter/query${applicationScope.WEB_SUFFIX}" id="form1" method="post">
        <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
           &nbsp; 参数名称&nbsp;<input type="text" name="name" id="name" maxlength="50" size="50" value="${bean.name}" />
            <input type="submit" value="搜索"/>
    </form>
    <div align="center">
        <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
    <display:table name="list" requestURI="/system/systemParameter/query${applicationScope.WEB_SUFFIX}" id="item"
         export="false" class="${tableclass}" style="width:100%">
      <display:column title="顺序"><%=offset++%></display:column>
      <display:column title="功能说明" property="memo"></display:column>
      <display:column title="参数名称" property="name"></display:column>
      <display:column title="参数值">
      	<c:choose>
      		<c:when test="${item.type == 'Selection'}">
      			  <option:optionGroup type="label" required="true" cache="true"
	                beanName="${item.optional}" selectedValue="${item.value}"/>
      		</c:when>
      		<c:otherwise>${item.value}</c:otherwise>
      	</c:choose>
      </display:column>
      <display:column title="操作" media="html">
      <a href= "${pageContext.request.contextPath}/system/systemParameter/load/${item.name}${applicationScope.WEB_SUFFIX}" title="修改"><img alt="修改" src="<ls:templateResource item='/common/default/images/grid_edit.png'/> "></a>
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
            window.location = "${pageContext.request.contextPath}/system/systemParameter/delete/"+id+"${applicationScope.WEB_SUFFIX}";
        }
    }

        function pager(curPageNO){
            document.getElementById("curPageNO").value=curPageNO;
            document.getElementById("form1").submit();
        }
        
        highlightTableRows("item");  
//-->
</script>
</body>
</html>

