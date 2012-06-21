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
              <div class="extra"><a href="#">全部商品分类</a></div>
              </div>
         
         
         <!------------------------------------->  
      </div>
      <ul >
      <c:forEach items="${headerSortList}" var="item">
      <li style="overflow:hidden" class='<c:if test="${currentSortId eq item.sortId}">focus</c:if>'><a href="<ls:url address='/sort/${item.sortId}'/>">${item.sortName }</a></li>
      </c:forEach>        
        <!-- <li class="focus"><a href="#">团购</a></li> -->
	
        <li class='<c:if test="${currentSortId eq -1}">focus</c:if>'><a href="<ls:url address='/group/index'/>">团购</a></li>
      </ul>
   </div> 
 </div> 
 </div>
<!--nav end-->