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
    <script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
    <title>二级分类列表</title>
</head>
<body>
    <%
        Integer offset = (Integer) request.getAttribute("offset");
    %>
    <form action="${pageContext.request.contextPath}/admin/nsort/query" id="form1" method="post">
        <table class="${tableclass}" style="width: 100%">
    <thead>
    	<tr><td><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商品管理  &raquo; 
    	<a href="${pageContext.request.contextPath}/admin/sort/query">类型管理</a> &raquo; 
    	<a href="${pageContext.request.contextPath}/admin/nsort/query?sortId=${param.sortId}">二级商品类型管理</a></td></tr>
    </thead>
     <tbody><tr><td>
 <div align="left" style="padding: 3px">
 <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
        <input type="hidden" id="sortId" name="sortId" value="${bean.sortId}" />
        二级类型
            <input type="text" name="name" maxlength="50" value="${name}" />
            <input type="submit" value="搜索"/>
            <input type="button" value="创建二级商品类型" onclick='window.location="${pageContext.request.contextPath}/admin/nsort/load?sortId=${bean.sortId}"'/>
 </div>
 </td></tr></tbody>
    </table>
    </form>

    <div align="center">
        <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
    <display:table name="list" requestURI="/admin/nsort/query" id="parent"
         export="false" class="${tableclass}" style="width:100%">
      <display:column title="顺序"><%=offset++%></display:column>
      <display:column title="二级商品类型" property="nsortName"></display:column>
      <display:column title="次序" property="seq"></display:column>
      <display:column title="分类代表">
            	<option:optionGroup type="label" required="true" cache="true"
	                beanName="YES_NO" selectedValue="${parent.sortDeputy}" defaultDisp="否"/>
      </display:column>
      <c:set var="subSortList" value="${parent.subSort}" scope="request"/>
      <display:column title="三级商品类型" >
      <c:if test="${fn:length(subSortList) > 0}">
	    <display:table name="subSortList"  id="child" cellpadding="0" cellspacing="0">
	      <display:column title="三级商品类型" property="nsortName"></display:column>
	      <display:column title="次序" property="seq"></display:column>
	      <display:column title="操作" media="html" autolink="true">
	      <auth:auth ifAnyGranted="F_OPERATOR">
	      <a href= "${pageContext.request.contextPath}/admin/nsort/update/${child.nsortId}"><img alt="修改三级商品类型" src="<ls:templateResource item='/common/default/images/grid_edit.png'/> " title="修改三级商品类型"></a>
	      <a href='javascript:deleteById("${child.nsortId}")'><img alt="删除" src="<ls:templateResource item='/common/default/images/grid_delete.png'/> " title="删除三级商品类型"></a>
	      </auth:auth>
      </display:column>
	    </display:table>
	    </c:if>
      </display:column>
      <display:column title="操作" media="html" style="width:80px">
      <auth:auth ifAnyGranted="F_OPERATOR">
      <a href= "${pageContext.request.contextPath}/admin/nsort/load?sortId=${bean.sortId}&parentNsortId=${parent.nsortId}"><img alt="创建三级商品类型" src="${pageContext.request.contextPath}/common/default/images/grid_add.png" title="创建三级商品类型"></a>
      <a href= "${pageContext.request.contextPath}/admin/nsort/update/${parent.nsortId}"><img alt="修改二级商品类型" src="<ls:templateResource item='/common/default/images/grid_edit.png'/> " title="修改二级商品类型"></a>
      <a href='javascript:deleteById("${parent.nsortId}")'><img alt="删除" src="<ls:templateResource item='/common/default/images/grid_delete.png'/> " title="删除二级商品类型"></a>
      </auth:auth>
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
            window.location = "${pageContext.request.contextPath}/admin/nsort/delete/"+id;
        }
    }

        function pager(curPageNO){
            document.getElementById("curPageNO").value=curPageNO;
            document.getElementById("form1").submit();
        }
        
        highlightTableRows("parent");  
        highlightTableRows("child");  
//-->
</script>
    
</body>
</html>

