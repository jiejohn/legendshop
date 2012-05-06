<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/pages/common/taglib.jsp'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>团购</title>
<link type="text/css" href="${pageContext.request.contextPath}/css/legend.css" rel="stylesheet"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/jquery1.6.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/menu.js"></script>
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
      <div class="sidebox">
        <div class="boxtit">
           <span><a href="#">更多 &gt;&gt;</a></span><b>客户服务</b>
         </div>
         <div class="phone">
           <p class="pimg"><img src="${pageContext.request.contextPath}/img/group/phone.png" width="75" height="56" /></p>
           <div class="pword">
             <p class="phonenumber">400-100-1234</p>
             <p class="phonetime">周一至周日 9:00-20:00</p>
           </div>
           <div class="clear"></div>
        </div>
         <div class="word">
           <p><span><img src="${pageContext.request.contextPath}/img/group/dot.gif" width="7" height="7" /></span>
           <span>10天无条件付款+先行赔付</span></p>
           <p><span><img src="${pageContext.request.contextPath}/img/group/dot.gif" width="7" height="7" /></span>
           <span>10天无条件付款+先行赔付</span></p>
         </div>
         <div class="clear"></div>
      </div>
      
      <div class="sidebox mar12">
        <div class="boxtit">
           <span><a href="${pageContext.request.contextPath}/group/question${applicationScope.WEB_SUFFIX}">更多 &gt;&gt;</a></span><b>常见问题</b>
         </div>
         <ul class="lista listimg1">
            <li><a href="#">如何付款，安全么？</a> </li>
            <li><a href="#">如果参加团购人数不足，怎么办？</a> </li>
            <li><a href="#">今天的团购看起来不错，怎么购买？</a> </li> 
            <li><a href="#">如何付款，安全么？</a> </li> 
            <li><a href="#">如果参加团购人数不足，怎么办？</a> </li>
            <li><a href="#">今天的团购看起来不错，怎么购买？</a> </li>  
            <li><a href="#">如果参加团购人数不足，怎么办？</a> </li>
         </ul>         
      </div>
      
      <div class="sidebox mar12" style="border:1px solid #fff;">
         <div class="boxtit">
           <span><a href="#">更多 &gt;&gt;</a></span><b>往期团购</b>
         </div>
         
        <div class="t_boxa">
           <p>2012年3月3日</p>
           <p><a href="#">仅售55元！价值180元的华影星美双人观影套餐（含3D）</a></p>
           <div class="t_imga">
             <a href="#"><img src="${pageContext.request.contextPath}/img/group/wq_timg.jpg" width="330" height="170" /></a>
             <div class="on"></div>
           </div>
           <div class="numboxa">
              <div class="totala"><span>1789</span>人已购买</div>
              <div class="partnuma">原价<br /><span>¥298</span></div>
              <div class="partnuma">折扣<br /><span>2.32</span></div>
              <div class="partnuma">节省<br /><span>¥229</span></div>
           </div>
           <div class="clear"></div>
         </div>
         
         <div class="t_boxa">
           <p>2012年3月3日</p>
           <p><a href="#">仅售55元！价值180元的华影星美双人观影套餐（含3D）</a></p>
           <div class="t_imga">
             <a href="#"><img src="${pageContext.request.contextPath}/img/group/wq_timg.jpg" width="330" height="170" /></a>
             <div class="out"></div>
           </div>
           <div class="numboxa">
              <div class="totala"><span>1789</span>人已购买</div>
              <div class="partnuma">原价<br /><span>¥298</span></div>
              <div class="partnuma">折扣<br /><span>2.32</span></div>
              <div class="partnuma">节省<br /><span>¥229</span></div>
           </div>
           <div class="clear"></div>
         </div> 
         
         <div class="t_boxa">
           <p>2012年3月3日</p>
           <p><a href="#">仅售55元！价值180元的华影星美双人观影套餐（含3D）</a></p>
           <div class="t_imga">
             <a href="#"><img src="${pageContext.request.contextPath}/img/group/wq_timg.jpg" width="330" height="170" /></a>
             <div class="out"></div>
           </div>
           <div class="numboxa">
              <div class="totala"><span>1789</span>人已购买</div>
              <div class="partnuma">原价<br /><span>¥298</span></div>
              <div class="partnuma">折扣<br /><span>2.32</span></div>
              <div class="partnuma">节省<br /><span>¥229</span></div>
           </div>
           <div class="clear"></div>
         </div> 
                
      </div>
       
       
    </div>
<!----右边end---->
    
    <!----左边---->
    <div class="t_left">
    
      <div id="filter">
      <ul class="cf">
      	<li class="first <c:if test='${empty prod.sortId }'>current</c:if>"><a href="<ls:url address='/group/index'/>">全部分类</a></li>
      	
      	<c:forEach items="${groupSortList }" var="item">
      	<li class="<c:if test='${prod.sortId eq item.sortId }'>current</c:if>"><a href="<ls:url address='/group/index'/>?product.sortId=${item.sortId}">${item.sortName }<span>(33)</span></a></li>
      	</c:forEach>
      	
      	</ul></div>
      
      <div class="along">
         <div class="aligna">
         <span class="fl">排序方式：</span> 
         <a href="#" class="aligna_aon">推荐</a>
         <a href="#">人气</a>
         <a href="#">价格</a>
         <a href="#">即将过期</a></div>
         
         <%-- <div class="t_ss"> <span>搜索团购</span> <input name="" class="tss_input" type="text" /> <input name="" type="image" src="${pageContext.request.contextPath}/img/group/t_ss.jpg" /></div> --%>
         
      </div>
     <c:forEach items="${list }" var="item" varStatus="vs">
       
       <div class="list_item <c:if test='${vs.count % 2 eq 0 }'>odd</c:if>">
          <h1><a href="${pageContext.request.contextPath}/group/view/1${applicationScope.WEB_SUFFIX}" >${item.name}</a></h1>
          <div class="cover"><a href="#"><img src="${pageContext.request.contextPath}/photoserver/photo/${item.pic}" width="372" height="195" /></a></div>
          
          <div class="detail">             
            <ul class="info">               
                <li>原价<br /><label><fmt:formatNumber type="currency" value="${item.price}" pattern="${CURRENCY_PATTERN}"/></label></li>
                <li>折扣<br /><label><fmt:formatNumber  value="${item.cash*10 / item.price}" pattern="#.#"/>折</label></li>
                <li>节省<br /><label><fmt:formatNumber type="currency" value="${item.price-item.cash}" pattern="${CURRENCY_PATTERN}"/></label></li>
            </ul>
            <ul class="status">
                <li class="volume"><strong>${item.buys }</strong>人已购买</li>                
                <li class="timeout"><div class="lxftime" endtime="<fmt:formatDate value="${item.endDate }" pattern="MM/dd/yyyy hh:mm:ss" />"></div></li>
            </ul>
          </div>
          <div class="action" >
              <label><fmt:formatNumber type="currency" value="${item.cash}" pattern="${CURRENCY_PATTERN}"/></label>
              <a href="#" target="_blank" hidefocus="true" rel="nofollow">去看看</a>
          </div>
      </div>
     	
     </c:forEach>
      
       <c:if test="${toolBar!=null}">
			<p align="right">
				<c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
			</p>
			</c:if>
       
                
     
    </div>
    <!----左边end---->
    
   <div class="clear"></div>
 </div>
<!----两栏end---->


<%@ include file="foot.jsp" %>
 
</body>
</html>
