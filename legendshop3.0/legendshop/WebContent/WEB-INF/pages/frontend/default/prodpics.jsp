<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<link rel="stylesheet" type="text/css" media='screen' href="${pageContext.request.contextPath}/common/default/css/productpics.css" />
<script type="text/javascript">
$(function() {
    $("#apple img[rel]").overlay({effect: 'apple'}); 
});
</script>
                              <div id="apple">
                                <img src="${pageContext.request.contextPath}/photoserver/images/${prod.pic}" width="256px" height="188px"  style="margin-right: 10px" rel="#${prod.prodId}"/>
                                </div>
                                <div class="apple_overlay" id="${prod.prodId}">
							    		<img src="${pageContext.request.contextPath}/photoserver/photo/${prod.pic}" width="640px" height="470px"/>
							            <div class="details">
							                <h2>${prod.name}</h2>
							            </div>
							     </div>
							     <!-- 
							      <a href="${pageContext.request.contextPath}/photoserver/photo/${prod.pic}" target="_blank"> [<fmt:message key="view.big.picture"/>]</a>
							       -->
							       
<c:if test="${requestScope.prodPics != null && fn:length(requestScope.prodPics) > 0}">
<div class="rollBox">
     <div class="LeftBotton" onmousedown="ISL_GoUp()" onmouseup="ISL_StopUp()" onmouseout="ISL_StopUp()"></div>
     <div class="Cont" id="ISL_Cont">
      <div class="ScrCont">
       <div id="List1">
       
        <!-- 图片列表 begin -->
        <c:forEach items="${requestScope.prodPics}" var="pics">
         <div class="pic">
          	<a href="${pageContext.request.contextPath}/productGallery/${prod.prodId}${applicationScope.WEB_SUFFIX}" target="_blank"><img src="${pageContext.request.contextPath}/photoserver/images/${pics.filePath}" width="65" height="50" /></a>
         </div>
         </c:forEach>
        <!-- 图片列表 end -->
        
       </div>
       <div id="List2"></div>
      </div>
     </div>
     <div class="RightBotton" onmousedown="ISL_GoDown()" onmouseup="ISL_StopDown()" onmouseout="ISL_StopDown()"></div>
    </div>
     <script type="text/javascript" src="${pageContext.request.contextPath}/common/default/js/productpics.js"></script>
 </c:if>