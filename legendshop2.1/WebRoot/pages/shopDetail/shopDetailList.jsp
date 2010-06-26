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
            window.location = "${root}/admin/shopDetail/delete/"+id+".c";
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
    <title>店铺信息</title>
</head>
<body class="bodymargin">
    <%
        Integer offset = (Integer) request.getAttribute("offset");
    %>
    <form action="${root}/admin/shopDetail/query.c" id="form1" method="post">
    <table class="mars" style="width: 100%">
    <thead>
    	<tr><td>商家管理  >> 店铺管理</td></tr>
    </thead>
    </table>
        <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
        <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
           店铺 <input type="text" name="storeName" maxlength="50"
                value="${bean.storeName}" size="15" />
            <input type="submit" value="搜索"/>
             <c:if test="${fn:length(list) == 0}">
            &nbsp;&nbsp;
            <a href="${root}/pages/shopDetail/shopDetail.jsp" title="创建店铺"><img src="${root}/img/grid_add.png"></a>
            </c:if>
            </auth:auth>

    </form>
    <div align="center">
        <%@ include file="/common/jsp/messages.jsp"%>
    <display:table name="list" requestURI="/admin/shopDetail/query.c" id="item"
         export="false" class="${tableclass}" style="width:100%">
      <display:column title="顺序" class="orderwidth"><%=offset++%></display:column>
      <display:column title="网站名称" property="sitename"></display:column>
      <display:column title="银行卡" property="maddr"></display:column>
      <display:column title="银行汇款帐号" property="msn"></display:column>
      <display:column title="收款人姓名" property="mname"></display:column>
      <display:column title="邮政编码" property="code"></display:column>
      <%-- 
      <display:column title="汇款地址" property="ymaddr"></display:column>
      --%>
      <display:column title="邮递接收人" property="ymname"></display:column>
      <display:column title="修改时间" property="modifyTime"></display:column>
      <display:column title="风格类型" class="orderwidth">
	      <c:if test="${item.colorStyle eq '' or item.colorStyle eq null}">循环</c:if> 
	      <c:if test="${item.colorStyle eq 'green'}">绿色</c:if> 
          <c:if test="${item.colorStyle eq 'red'}">红色</c:if> 
          <c:if test="${item.colorStyle eq 'blue'}">蓝色</c:if> 
      </display:column>
      <display:column title="店铺" property="storeName"></display:column>
      <display:column title="操作" media="html" class="actionwidth">
      <a href= "${root}/admin/shopDetail/load/${item.userId}.c" title="修改"><img alt="修改" src="${root}/img/grid_edit.png"></a>
         <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">   
              <a href='javascript:deleteById("${item.userId}")' title="删除"><img alt="删除" src="${root}/img/grid_delete.png"></a>
         </auth:auth>
      </display:column>
    </display:table>
        <c:if test="${not empty toolBar}">
            <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
        </c:if>
    </div>
</body>
</html>

