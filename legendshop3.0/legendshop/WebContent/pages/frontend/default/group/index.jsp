<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/pages/common/taglib.jsp'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>团购</title>
<%-- <link type="text/css" href="${pageContext.request.contextPath}/css/legend.css" rel="stylesheet"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/jquery1.6.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/menu.js"></script> --%>
<script type="text/javascript">
function lxfEndtime(){
    $(".lxftime").each(function() {
        var lxfday = $(this).attr("lxfday"); //用来判断是否显示天数的变量
        
        var endtime = new Date($(this).attr("endtime")).getTime(); //取结束日期(毫秒值)
        var nowtime = new Date().getTime();        //今天的日期(毫秒值)
        var youtime = endtime - nowtime; //还有多久(毫秒值)
        var seconds = youtime / 1000;
        var minutes = Math.floor(seconds / 60);
        var hours = Math.floor(minutes / 60);
        var days = Math.floor(hours / 24);
        var CDay = days;
        var CHour = hours % 24;
        var CMinute = minutes % 60;
        var CSecond = Math.floor(seconds % 60); //"%"是取余运算，可以理解为60进一后取余数，然后只要余数。
        if (endtime <= nowtime) {
            $(this).html("<strong>已过期</strong>");//如果结束日期小于当前日期就提示过期啦
        } else {
            if ($(this).attr("lxfday") == "no") {
                $(this).html("<span>剩余</span><strong>" + CHour + "</strong>时<strong>" + CMinute + "</strong>分<strong>" + CSecond + "</strong>秒");          //输出没有天数的数据
            }
            else
            {
                $(this).html("<span>剩余</span><strong>" + days + "</strong>天<strong>" + CHour + "</strong>时<strong>" + CMinute + "</strong>分<strong>" + CSecond + "</strong>秒");          //输出有天数的数据
            }
        }
    });
 setTimeout("lxfEndtime()",1000);
};
$(function(){
    lxfEndtime();
 });
 


function pager(curPageNO){
	document.getElementById("curPageNO").value=curPageNO;
	document.getElementById("form1").submit();
}  

function changeCate(catId){
	$("#sortId").attr("value",catId);
	$("#form1").submit();
}

function changeOrder(order,seq){
	$("#order").attr("value",order);
	$("#form1").submit();
}


</script>

</head>

<body>

<jsp:include page="/common/top${applicationScope.WEB_SUFFIX}">
	<jsp:param name="sortId" value="-1" />
</jsp:include>

<!----地址---->
 <div class="w addr">
   <span><a href="#">首页</a></span>&gt;<span>团购</span> 
 </div>
<!----地址end---->
 <div class="w banner1">
 <c:forEach items="${requestScope.INDEX_ADV_TOP}" var="adv">
<table width="1216px" cellpadding="0" cellspacing="0" style="margin-top: 5px" class="picstyle">
  <tr><td><a href="${adv.linkUrl}"><img src="${pageContext.request.contextPath}/photoserver/photo/${adv.picUrl}" title="${adv.title}" width="100%"/></a></td></tr>
 </table>
</c:forEach>
 </div>

<!----两栏---->
 <div class="w"> 
    <!----右边---->
    <div class="t_right">
      <jsp:include page="/group/clientServicePanel${applicationScope.WEB_SUFFIX}" />
      <jsp:include page="/group/questionPanel${applicationScope.WEB_SUFFIX}" />
    </div>
<!----右边end---->
    
    <!----左边---->
    <div class="t_left">
      <form action="<ls:url address='/group/index'/>" method="post" id="form1">
    	<input type="hidden" id="curPageNO" name="curPageNO" value="<%=request.getAttribute("curPageNO")%>">
      	<input type="hidden" id="sortId" name="sortId" value="${prod.sortId }" />
      	<input type="hidden" id="order" name="order" value="${order }" />
      	<input type="hidden" id="seq" name="seq" value="${seq}" />
      </form>
      <div id="filter">
      <ul class="cf">
      	<li class="first <c:if test='${empty prod.sortId }'>current</c:if>"><a href="<ls:url address='/group/index'/>">全部分类</a></li>
      	
      	<c:forEach items="${groupSortList }" var="item">
      	<li class="<c:if test='${prod.sortId eq item.sortId }'>current</c:if>"><a href="javascript:changeCate(${item.sortId})">${item.sortName }<!-- <span>(33)</span> --></a></li>
      	</c:forEach>
      	
      	</ul>
      </div>
      
      <div class="along">
         <div class="aligna">
         <span class="fl">排序方式：</span> 
         
         <a href="javascript:changeOrder('recommend')" class="<c:if test="${empty order or order eq 'recommend' }">aligna_aon</c:if>">推荐</a>
         <a href="javascript:changeOrder('hot')" class="<c:if test="${order eq 'hot' }">aligna_aon</c:if>">人气</a>
         <c:set var="priceClass" value="" />
         <c:choose>
         	<c:when test="${order eq 'price' }">
         		<c:set var="priceClass" value="align_${seq }_on" />
         	</c:when>
         	<c:otherwise>
         		<c:set var="priceClass" value="align_${seq }_off" />
         	</c:otherwise>
         </c:choose>
         <a href="javascript:changeOrder('price')" class="${priceClass }">价格</a>
         
         <a href="javascript:changeOrder('time')" class="<c:if test="${order eq 'time' }">aligna_aon</c:if>">即将过期</a></div>         
         <%-- <div class="t_ss"> <span>搜索团购</span> <input name="" class="tss_input" type="text" /> <input name="" type="image" src="${pageContext.request.contextPath}/img/group/t_ss.jpg" /></div> --%>
         
      </div>
     <c:forEach items="${list }" var="item" varStatus="vs">
       
       <div class="list_item <c:if test='${vs.count % 2 eq 0 }'>odd</c:if>">
          <h1><a href="<ls:url address='/group/view/${item.prodId }'/>" >
          <c:choose>
          	<c:when test="${not empty item.brief }">${item.brief }</c:when>
          	<c:otherwise>${item.name}</c:otherwise>
          </c:choose>
          
          
          </a></h1>
          <div class="cover"><a href="<ls:url address='/group/view/${item.prodId }'/>"><img src="${pageContext.request.contextPath}/photoserver/photo/${item.pic}" width="372" height="225" /></a></div>
          
          <div class="priceit">
                        <span><font>${item.buys }</font>人已购买</span>
                        原价：<font><fmt:formatNumber value="${item.price}" pattern="#.0#"/>元</font>
                        <font class="zhe">
                        <c:if test="${item.cash > 0 and item.price > 0 }">
                        <fmt:formatNumber  value="${item.cash*10 / item.price}" pattern="#.#"/>折
                        </c:if>
                        </font>
          </div>
          <a title="团购"  href="<ls:url address='/group/view/${item.prodId }'/>">
          <div class="buyit">
                        <span><fmt:formatNumber value="${item.cash}" pattern="#.0#"/></span>
                       <span class="tg"></span>
          </div></a>
      </div>
     	
     	
     </c:forEach>
      
                
   <div class="clear"></div>
     
       <c:if test="${not empty toolBar}">
			<p align="right">
				<c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
			</p>
			</c:if>
    </div>
    <!----左边end---->
    
   <div class="clear"></div>
   
       
 </div>
<!----两栏end---->

<jsp:include page="/common/foot${applicationScope.WEB_SUFFIX}"/>
 
</body>
</html>
