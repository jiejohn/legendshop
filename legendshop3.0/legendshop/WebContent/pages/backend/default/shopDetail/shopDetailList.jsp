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
    <script src="<ls:templateResource item='/css/alternative.js'/>" type="text/javascript"></script>
    <title>商城信息</title>
</head>
<body class="bodymargin">
    <%
        Integer offset = (Integer) request.getAttribute("offset");
    %>
    <form action="${pageContext.request.contextPath}/admin/shopDetail/query${applicationScope.WEB_SUFFIX}" id="form1" method="post">
    <table class="${tableclass}" style="width: 100%">
    <thead>
    	<tr><td><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商城管理  &raquo; <a href="${pageContext.request.contextPath}/admin/shopDetail/query${applicationScope.WEB_SUFFIX}">商城管理</a></td></tr>
    </thead>
    </table>
        <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
        <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
           商城名称&nbsp;<input type="text" name="storeName" maxlength="50" value="${shopDetail.storeName}" size="30"/>
           &nbsp;类型&nbsp;	
           <select id="type" name="type">
           <option:optionGroup type="select" required="false" cache="true"
	                beanName="SHOP_TYPE" selectedValue="${shopDetail.type}"/>
	        </select>
	       &nbsp;风格&nbsp;	
           <select id="colorStyle" name="colorStyle">
				  <option:optionGroup type="select" required="false" cache="true"
	                beanName="COLOR_STYLE" selectedValue="${shopDetail.colorStyle}"/>
	        </select>
	      &nbsp;状态&nbsp;
	      <select id="status" name="status">
	      <option:optionGroup type="select" required="false" cache="true"
	                beanName="SHOP_STATUS" selectedValue="${shopDetail.status}" />
	        </select>	
            <input type="submit" value="搜索"/>
             <c:if test="${fn:length(list) == 0}">
            <input type="button" value="创建商城" onclick='window.location="${pageContext.request.contextPath}/admin/shopDetail/load${applicationScope.WEB_SUFFIX}"'/>
            </c:if>
            </auth:auth>
    </form>
    <div align="center">
        <%@ include file="/pages/common/messages.jsp"%>
    <display:table name="list" requestURI="/admin/shopDetail/query${applicationScope.WEB_SUFFIX}" id="item"
         export="true" class="${tableclass}" style="width:100%" sort="external">
      <display:column title="顺序" class="orderwidth"><%=offset++%></display:column>
      <c:if test="${'C2C' == applicationScope.BUSINESS_MODE }">
      <display:column title="商城名称">
      	<a href="${pageContext.request.contextPath}/shop/${item.storeName}${applicationScope.WEB_SUFFIX}">${item.storeName}</a>
      </display:column>
      </c:if>
      <display:column title="网站名称" property="sitename">
      </display:column>
      <display:column title="商品数量" property="productNum"  sortable="true" sortName="productNum" style="width:65px"></display:column>
      <display:column title="下线商品" property="offProductNum" sortable="true" sortName="offProductNum" style="width:65px"></display:column> 

      <display:column title="类型" sortable="true" sortName="type" style="width:40px">
		  <option:optionGroup type="label" required="false" cache="true"
	                beanName="SHOP_TYPE" selectedValue="${item.type}"/>
      </display:column>
      <%--
      <display:column title="语言" style="width:55px">
				  <option:optionGroup type="label" required="false" cache="true"
	                beanName="LANG_STYLE" selectedValue="${item.langStyle}" defaultDisp=""/>
	   </display:column>  
	      --%>          
	  <display:column title="风格" style="width:70px">
				  <option:optionGroup type="label" required="false" cache="true"
	                beanName="COLOR_STYLE" selectedValue="${item.colorStyle}" defaultDisp=""/>
	  </display:column>
	
	  <auth:auth ifAllGranted="F_VIEW_ALL_DATA">
		  <display:column title="创建国家" property="createCountryCode" />		  
		  <display:column title="地区"  property="createAreaCode" />		  
     </auth:auth>
      <display:column title="状态" style="width:30px">
      <font color="red">
				  <option:optionGroup type="label" required="false" cache="true"
	                beanName="SHOP_STATUS" selectedValue="${item.status}" defaultDisp=""/></font>
      </display:column>
      <display:column title="操作" media="html" class="actionwidth">
      <a href= "${pageContext.request.contextPath}/admin/shopDetail/load/${item.shopId}${applicationScope.WEB_SUFFIX}" title="修改"><img alt="修改" src="<ls:templateResource item='/img/grid_edit.png'/> "></a>
         <auth:auth ifAllGranted="F_VIEW_ALL_DATA,F_OPERATOR">
              <a href='javascript:deleteById("${item.shopId}")' title="删除"><img alt="删除" src="<ls:templateResource item='/img/grid_delete.png'/> "></a>
         </auth:auth>
      </display:column>
    </display:table>
        <c:if test="${not empty toolBar}">
            <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
        </c:if>
    </div>
         <table style="width: 100%; border: 0px"><tr><td align="left">说明：<br>
   1. 商城为上线状态才可以正常访问<br>
   2. 您可以选择不同的页面风格，或者每天随即挑取其中一个风格<br>
   </td><tr></table>
   
    <script language="JavaScript" type="text/javascript">
<!--

  function deleteById(id) {
      if(confirm("  确定删除 ?")){
            window.location = "${pageContext.request.contextPath}/admin/shopDetail/delete/"+id+"${applicationScope.WEB_SUFFIX}";
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

