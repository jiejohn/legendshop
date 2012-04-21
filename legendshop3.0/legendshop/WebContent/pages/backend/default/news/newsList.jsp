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
    <title>新闻列表</title>
</head>
<body class="bodymargin">
    <%
        Integer offset = (Integer) request.getAttribute("offset");
    %>
    <form action="${pageContext.request.contextPath}/admin/news/query${applicationScope.WEB_SUFFIX}" id="form1" method="post">
    <table class="${tableclass}" style="width: 100%">
    <thead>
    	<tr><td><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商城管理  &raquo; <a href="${pageContext.request.contextPath}/admin/news/query${applicationScope.WEB_SUFFIX}">新闻管理</a></td></tr>
    </thead>
    </table>
    <div align="left" style="padding: 5px">
        <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
	            标题
	            <input type="text" name="newsTitle" maxlength="50" size="30" value="${bean.newsTitle}"/>
	     
         <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
                 商城 
               <input type="text" name="userName" maxlength="50" value="${bean.userName}" />
        </auth:auth>
	</div><div align="left" style="padding: 5px">
            新闻类型
	           <select id="status" name="status">
				  <option:optionGroup type="select" required="false" cache="true"
	                beanName="NEWCATEGORY_STAUS" selectedValue="${bean.status}"/>
	            </select>
	      栏目
	           <select id="newsCategoryId" name="newsCategoryId">
	                 <option:optionGroup type="select" required="false" cache="fasle"
	                beanName="NewsCategory" beanId="newsCategoryId" beanDisp="newsCategoryName" 
	                hql="select t.newsCategoryId, t.newsCategoryName from NewsCategory t where t.userName = ?" param="${sessionScope.SPRING_SECURITY_LAST_USERNAME}" selectedValue="${bean.newsCategoryId}"/>
	            </select>
	       产品分类
	           <select id="sortId" name="sortId">
	                 <option:optionGroup type="select" required="false" cache="fasle"
	                beanName="Sort" beanId="sortId" beanDisp="sortName"
	                hql="select t.sortId, t.sortName from Sort t where t.userName = ?" param="${sessionScope.SPRING_SECURITY_LAST_USERNAME}" selectedValue="${bean.sortId}"/>
	            </select>
	         <input type="submit" value="搜索"/>
            <input type="button" value="创建新闻" onclick='window.location="${pageContext.request.contextPath}/admin/news/load${applicationScope.WEB_SUFFIX}"'/>
            <input type="button" value="创建新闻栏目" onclick='window.location="${pageContext.request.contextPath}/admin/newsCategory/load${applicationScope.WEB_SUFFIX}"'/>
            </div>
    </form>
    <div align="center">
        <%@ include file="/pages/common/messages.jsp"%>
    <display:table name="list" requestURI="/admin/news/query${applicationScope.WEB_SUFFIX}" id="item"
         export="true" class="${tableclass}" style="width:100%"  sort="external">
      <display:column title="顺序" class="orderwidth"><%=offset++%></display:column>
      <display:column title="标题"><a href="${pageContext.request.contextPath}/news/${item.newsId}${applicationScope.WEB_SUFFIX}" target="_blank">${item.newsTitle}</a></display:column>
      <display:column title="栏目">
	                 ${item.newsCategoryName}
      </display:column>
      <display:column title="分类">
	                 ${item.sortName}
      </display:column>
      <display:column title="录入时间" property="newsDate" format="{0,date,yyyy-MM-dd HH:mm}" sortable="true" sortName="n.newsDate"></display:column>
      <display:column title="状态" sortable="true" sortName="n.status">
           <c:choose>
                <c:when test="${item.status == 0}"><font color="red">下线</font></c:when>
                <c:otherwise>上线</c:otherwise>
           </c:choose>
      </display:column>
      <display:column title="重点" sortable="true" sortName="n.highLine">
      		 <c:choose>
                <c:when test="${item.highLine == 1}"><font color="red">是</font></c:when>
                <c:otherwise></c:otherwise>
           </c:choose>
      </display:column>
      <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
        <display:column title="商城" property="userName" sortable="true" sortName="n.userName"></display:column>
      </auth:auth>
      <display:column title="操作" media="html" style="width:40px">
      <a href= "${pageContext.request.contextPath}/admin/news/load/${item.newsId}${applicationScope.WEB_SUFFIX}" title="修改"><img alt="修改" src="<ls:templateResource item='/img/grid_edit.png'/> "></a>
      <auth:auth ifAnyGranted="F_OPERATOR">
        <a href='javascript:deleteById("${item.newsId}")' title="删除"><img alt="删除" src="<ls:templateResource item='/img/grid_delete.png'/> "></a>
      </auth:auth>
      </display:column>
    </display:table>
        <c:if test="${not empty toolBar}">
            <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
        </c:if>
        </div>
        
         <table style="width: 100%; border: 0px"><tr><td align="left">说明：<br>
   1. 每个文章都是挂在栏目下面<br>
   2. 文章处于上线状态页面可见，处于下线状态页面不可见<br>
   3. 分类，指产品分类，用于标识该文章是描述那一个产品类型<br>
   4. 是否重点，如果是重点则在页面中以醒目颜色标注<br>
   </td><tr></table> 
        
         <script language="JavaScript" type="text/javascript">
<!--

  function deleteById(id) {
      if(confirm("  确定删除 ?")){
            window.location = "${pageContext.request.contextPath}/admin/news/delete/"+id+"${applicationScope.WEB_SUFFIX}";
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

