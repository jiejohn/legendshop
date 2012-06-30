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
    <script src="<ls:templateResource item='/common/js/default/alternative.js'/>" type="text/javascript"></script>
    <title>热门列表</title>
</head>
<body class="bodymargin">
    <%
        Integer offset = (Integer) request.getAttribute("offset");
    %>
    <form action="<ls:url address='/admin/hotsearch/query'/>" id="form1" method="post">
    <table class="${tableclass}" style="width: 100%">
    <thead>
    	<tr><td><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商品管理  &raquo; <a href="<ls:url address='/admin/hotsearch/query'/>">热门商品管理</a></td></tr>
    </thead>
    </table>
        <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
	            标题
	            <input type="text" name="title" maxlength="50" value="${bean.title}" />
	           分类
	           <select id="sort" name="sort">
	                 <option:optionGroup type="select" required="false" cache="fasle"
	                beanName="Sort" beanId="sortId" beanDisp="sortName" 
	                hql="select t.sortId, t.sortName from Sort t where t.userName = ?" param="${sessionScope.SPRING_SECURITY_LAST_USERNAME}" selectedValue="${bean.sort}"/>
	            </select> 
         <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
             商城名称&nbsp;<input type="text" name="userName" maxlength="50" value="${bean.userName}" />
         </auth:auth>
         <input type="submit" value="搜索"/>
         <input type="button" value="创建热门商品" onclick='window.location="${pageContext.request.contextPath}/admin/hotsearch/load${applicationScope.WEB_SUFFIX}"'/>
    </form>
    <div align="center">
        <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
    <display:table name="list" requestURI="/admin/hotsearch/query${applicationScope.WEB_SUFFIX}" id="item"
         export="true" class="${tableclass}" style="width:100%">
      <display:column title="顺序" class="orderwidth"><%=offset++%></display:column>
      <display:column title="标题" property="title"></display:column>
      <display:column title="分类">
	                 <option:optionGroup type="label" required="false" cache="fasle"
	                beanName="Sort" beanId="sortId" beanDisp="sortName" 
	                 selectedValue="${item.sort}" defaultDisp="首页"/>
      </display:column>
      <display:column title="链接地址"><a href="${item.msg}" target="_blank">${item.msg}</a></display:column>
      <display:column title="日期" property="date" format="{0,date,yyyy-MM-dd HH:mm}" sortable="true"></display:column>
      <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
            <display:column title="商城名称" property="userName"></display:column>
      </auth:auth>
      <display:column title="操作" media="html" class="actionwidth">
      <a href="<ls:url address='/admin/hotsearch/load/${item.id}'/>" title="修改"><img alt="修改" src="<ls:templateResource item='/common/images/default/grid_edit.png'/> "></a>
      <auth:auth ifAnyGranted="F_OPERATOR">
        <a href='javascript:deleteById("${item.id}")' title="删除"><img alt="删除" src="<ls:templateResource item='/common/images/default/grid_delete.png'/> "></a>
      </auth:auth>
      </display:column>
    </display:table>
        <c:if test="${not empty toolBar}">
            <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
        </c:if>
        </div>
   <table style="width: 100%; border: 0px"><tr><td align="left">说明：<br>
   1. 热门商品出现在一级商品类型的首页<br>
   2. <img alt="修改" src="<ls:templateResource item='/common/images/default/grid_edit.png'/> "> 修改按钮<br>
   3. <img alt="删除" src="<ls:templateResource item='/common/images/default/grid_delete.png'/> "> 删除按钮<br>
   </td><tr></table> 
   
    <script language="JavaScript" type="text/javascript">
<!--

  function deleteById(id) {
      if(confirm("  确定删除 ?")){
            window.location = "${pageContext.request.contextPath}/admin/hotsearch/delete/"+id+"${applicationScope.WEB_SUFFIX}";
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

