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
   <span><a href="#">首页</a></span>&gt;<span><a href="<ls:url address='/group/index'/>">团购</a></span>&gt;<span>${groupProduct.product.name}</span> 
 </div>
<!----地址end---->
 <div class="w banner1"><img src="${pageContext.request.contextPath}/img/group/banner1.gif" width="1216" height="128" /></div>

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
       
       <!----up---->
       <div class="t_info_box">
                <h1>
                <c:choose>
		          	<c:when test="${not empty groupProduct.product.brief }">${groupProduct.product.brief }</c:when>
		          	<c:otherwise>${groupProduct.product.name}</c:otherwise>
		          </c:choose>
          </h1>
                <div class="main">
                    <table class="deal_discount">
                        <tbody><tr>
                            <td>原价</td>
                            <td>折扣</td>
                            <td>节省</td>
                        </tr>
                        <tr>
                            <th><span><del>¥<fmt:formatNumber value="${groupProduct.product.price}" pattern="#.0#"/></del></span></th>
                            <th>
                            
	                        <c:if test="${groupProduct.product.cash > 0 and groupProduct.product.price > 0 }">
	                        <span><fmt:formatNumber  value="${groupProduct.product.cash*10 / groupProduct.product.price}" pattern="#.#"/>折</span>
	                        </c:if>
                        	</th>
                            <th><span>¥<fmt:formatNumber value="${groupProduct.product.price-groupProduct.product.cash}" pattern="#.0#"/></span></th>
                        </tr>
                    </tbody></table>
                    <div class="deal-buy">                        
                        <p class="deal-price">
                            <strong>¥<fmt:formatNumber value="${groupProduct.product.cash}" pattern="#.0#"/></strong><span><a href="#">抢购</a></span></p>
                    </div>
                    
             <div class="downinfo" style="margin-top:75px;"><p><span class="text_red">${groupProduct.product.buys+groupProduct.visualBuys }</span> 人已购买</p></div>                  
                    
            <div class="downinfo" ><p><label>本团购剩余</label>${groupProduct.product.actualStocks  }<br />
            <c:if test="${not empty  groupProduct.product.endDate }">
            <div class="lxftime" endtime="<fmt:formatDate value="${groupProduct.product.endDate }" pattern="MM/dd/yyyy hh:mm:ss" />"></div>            
            </c:if>
            <!-- <span class="text_gray">1</span>天<span class="text_gray">20</span>小时<span class="text_gray">10</span>分</p> -->
            </div>
            
            <!-- <div class="downinfo"><p  class="mailfree">网上支付满69元免运费</p></div>     -->         
             
            </div>
            
            <div class="side">
                <div class="deal-buy-cover-img"><img src="${pageContext.request.contextPath}/photoserver/photo/${groupProduct.product.pic}" width="440"></div>
            </div>
            <div class="clear"></div>
            
            
        </div>
        <!----up end---->
        
        
        <!----down---->
       <div class="t_info_box t_inback">
         <div class="infonav">
           <table width="425" height="41" >
             <tr>
             <td width="70" align="center"><strong>商品详情</strong></td><td width="3" align="center">
             <img src="${pageContext.request.contextPath}/img/group/bg2_06.gif" width="3" height="32" /></td>
             <%-- <td width="142" align="center">客服电话<span class="red"><strong>400-866-3423</strong></span></td>
             <td width="3" align="center"><img src="${pageContext.request.contextPath}/img/group/bg2_06.gif" width="3" height="32" /></td>
             <td width="52" align="center" class="text_red">$48</td> --%>
             <td align="center" ><%-- <img src="${pageContext.request.contextPath}/img/group/gobuy.gif" width="87" height="29" /> --%></td>
           </tr></table>
         </div>
         <div class="tinfo_left">         
           ${groupProduct.product.content }
         </div>
         
         <div class="tinfo_right">
         <c:if test="${not empty partner }">
           <strong>${partner.title }</strong><br />
           <c:if test="${not empty partner.homepage }">
           <strong>主页：</strong><a href="${partner.homepage}">${partner.homepage }</a><br />
           </c:if>
           <strong>地址：</strong>${partner.address}<br />
           <strong>电话：</strong>${partner.phone}<br />
         </c:if>
         </div>
         
         <div class="clear"></div>
       
       
       </div>
        <!----down end---->
    
           
       
       
       
        
                
     
    </div>
    <!----左边end---->
    
    <div class="clear"></div>
 </div>
<!----两栏end---->

<jsp:include page="/common/foot${applicationScope.WEB_SUFFIX}"/>
 
</body>
</html>