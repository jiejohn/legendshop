<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/pages/common/taglib.jsp'%>
<SCRIPT type=text/javascript> 
$(document).ready(function() {

$(".item").hoverForIE6({delay:100});
$(".nav_vbox").hoverForIE6({delay:150});
});
</SCRIPT>
<!--nav-->
 <div class="nav">
   <div class="nav">
   <div class="w wnav">
      <div class="nav_vbox">
         <div class="navbtn"><a href="#">全部商品分类</a></div> 
         
         <!------------------------------------>
           <div class="mc" >
           	<c:forEach items="${navigationSortList }" var="sort" varStatus="vs">
           		<div class="item fore1">
                 <span><h3>
                 <c:choose>
                 	<c:when test="${ not empty deputyMap[sort.sortId] }">
                 		<c:forEach items="${deputyMap[sort.sortId]}" var="dnsort" varStatus="vs">
                 			<a href="<ls:url address='/nsort/${dnsort.sortId}-${dnsort.nsortId}'/>">${dnsort.nsortName }</a><c:if test="${vs.count lt fn:length(deputyMap[sort.sortId]) }">、</c:if>
                 		</c:forEach>
                 	</c:when>
                 	<c:otherwise>
                 		<a href="<ls:url address='/sort/${sort.sortId}'/>">${sort.sortName }</a>
                 	</c:otherwise>
                 </c:choose>
                 </h3><s></s></span>
                 
                 <!--  -->
                 
                 <div class="i-mc" >                    
                    <div class="subitem">
                    <c:forEach items="${sTreeMap[sort.sortId] }" var="snsort">                    
                       <dl class="fore1"> 
                          <dt> <a href="<ls:url address='/nsort/${snsort.sortId}-${snsort.nsortId}'/>">${snsort.nsortName }</a></dt>
                          <dd>
                          <c:forEach items="${tTreeMap[snsort.nsortId] }" var="tnsort">
                          	<em><a href="<ls:url address='/nsort/${tnsort.sortId}-${tnsort.nsortId}'/>">${tnsort.nsortName }</a></em>
                          </c:forEach>
                          </dd> 
                       </dl>
                    </c:forEach>
                 </div>
                </div>
                </div>
                 
                 <!--  -->
                 
           	</c:forEach>      
<!--               <div class="item fore1">
                 <span><h3><a href="#">图书</a>、<a href="#">电子书刊</a>、<a href="#">音像</a></h3><s></s></span>
                 
                 <div class="i-mc" style=" ">                    
                    <div class="subitem">
                       <dl class="fore1"> 
                          <dt> <a href="#">电子书刊</a> </dt>
                          <dd><em><a href="#">电子书</a></em><em><a href="#">网络原创</a></em><em><a href="#">数字杂志</a></em><em><a href="#">多媒体图书</a></em></dd> 
                       </dl>
                       <dl class="fore2">
                          <dt><a href="#">音像</a></dt> 
                          <dd><em><a href="#">音乐</a></em><em><a href="#">影视</a></em><em><a href="#">教育音像</a></em></dd>
                       </dl>
                       <dl class="fore3">
                          <dt>英文原版</dt>
                          <dd><em><a href="#">英文原版首页</a></em><em><a href="#">少儿</a></em><em><a href="#">商业投资</a></em><em><a href="#">英语学习与考试</a></em><em><a href="#">小说</a></em></dd>
                       </dl>
                       <dl class="fore4">
                          <dt>文艺</dt>
                          <dd><em><a href="#">小说</a></em><em><a href="#">文学</a></em><em><a href="#">青春文学</a></em><em><a href="#">传记</a></em><em><a href="#">艺术</a></em></dd>         </dl>
                       <dl class="fore5">
                          <dt>少儿</dt>
                          <dd><em><a href="#">少儿</a></em><em><a href="#">0-2岁</a></em><em><a href="#">3-6岁</a></em><em><a href="#">7-10岁</a></em><em><a href="#">11-14岁</a></em></dd>
                       </dl>
                 </div>
                </div>
                      
              </div> -->
              <div class="extra"><a href="#">全部商品分类</a></div>
              </div>
         
         
         <!------------------------------------->  
      </div>
      <ul>
      <c:forEach items="${headerSortList}" var="item">
      <li class='<c:if test="${currentSortId eq item.sortId}">focus</c:if>'><a href="<ls:url address='/sort/${item.sortId}'/>">${item.sortName }</a></li>
      </c:forEach>        
        <!-- <li class="focus"><a href="#">团购</a></li> -->
	
        <li class='<c:if test="${currentSortId eq -1}">focus</c:if>'><a href="<ls:url address='/group/index'/>">团购</a></li>
      </ul>
   </div> 
 </div> 
 </div>
<!--nav end-->