<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<jsp:include flush="true" page="/top${applicationScope.WEB_SUFFIX}">
	<jsp:param name="sortId" value="-1" />
</jsp:include>
<!----地址---->
 <div class="w addr">
   <span><a href="#">首页</a></span>&gt;<span><a href="#">电脑办公</a></span>&gt;<span><a href="#">电脑整机</a></span>&gt;<span>笔记本</span>
 </div>
<!----地址end---->


<!----两栏---->
 <div class="w" style="padding-top:10px;"> 
    <!-----------leftm-------------->    
    <!-----------leftm end-------------->
    <!-- 左边的内容 -->
<tiles2:insertAttribute name="right" ignore="true" />
    <div class="clear"></div>
    
 </div>
 
<!----两栏end---->
<jsp:include page="/bottom${applicationScope.WEB_SUFFIX}"/>