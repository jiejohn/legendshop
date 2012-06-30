<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/pages/common/taglib.jsp'%>
<link type="text/css" href="<ls:templateResource item='/common/css/red/legend.css'/>" rel="stylesheet"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/jquery1.6.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/red/menu.js"></script>
<%@ include file="header.jsp" %>
<script type="text/javascript">
<!--
function searchSubmit(){
	$("#searchAllform").submit();
}
//-->
</script>
<!--logo+search--> 
<div id="logo" class="w">
  
      <p class="logopic"><img src="${pageContext.request.contextPath}/img/group/logo.gif" width="257" height="42" /></p>
      
      <div class="searchwrap">
      	<form action="${pageContext.request.contextPath}/searchall${applicationScope.WEB_SUFFIX}" method="get" id="searchAllform" name="searchAllform">
        <div class="search">
          <div class="sleft">
          <select name="entityType">
          <option value="0">商品</option>
          <option value="1">商城</option>
          </select>
          </div>
          <input name="keyword" type="text"/>
        </div>
         <p class="searchpic"><a href="javascript:searchSubmit()"><img src="${pageContext.request.contextPath}/img/group/search.gif" width="104" height="40" /></a></p>
         
        </form>
         <div class="clear"></div>
         <p class="hotword">热门搜索：
   			<c:forEach items="${searchList }" var="hot"> 
   			<a href="<ls:url address='/searchall'/>?keyword=${hot.title }">${hot.title }</a>&nbsp;
   			</c:forEach>      
         </p>
      </div>
       
      <p class="shoplist">
      
	  <c:if test="${sessionScope.SPRING_SECURITY_LAST_USERNAME != null}">
      <a href="<ls:url address='/myaccount'/>">
      <img src="${pageContext.request.contextPath}/img/group/geren.gif" width="142" height="46" /> 
      </a>
      </c:if>
      &nbsp;       
      <a href="<ls:url address='/buy'/>">
      <img src="${pageContext.request.contextPath}/img/group/shoplist.gif" width="156" height="47" />
      </a>
      </p>
     
   
  <div class="clear"></div>
</div>
<!--logo+search-->
<%@ include file="nav.jsp" %>
