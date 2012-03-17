<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ include file="header.jsp" %>

<!--logo+search--> 
<div id="logo" class="w">
  
      <p class="logopic"><img src="${pageContext.request.contextPath}/img/group/logo.gif" width="257" height="42" /></p>
      
      <div class="searchwrap">
        <div class="search">
          <div class="sleft"><select><option>商品</option><option>商场</option></select></div>
          <input type="text"/>
        </div>
         <p class="searchpic"><a href="#"><img src="${pageContext.request.contextPath}/img/group/search.gif" width="104" height="40" /></a></p>
         <div class="clear"></div>
         <p class="hotword">热门搜索：希捷移动硬盘  减肥  北欧欧慕空间大师  i589电子书  瑞士军刀  方正笔记本</p>
      </div>
       
      <p class="shoplist"><img src="${pageContext.request.contextPath}/img/group/geren.gif" width="142" height="46" /> &nbsp; <img src="${pageContext.request.contextPath}/img/group/shoplist.gif" width="156" height="47" /></p>
     
   
  <div class="clear"></div>
</div>
<!--logo+search-->
<%@ include file="nav.jsp" %>
