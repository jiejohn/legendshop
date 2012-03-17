<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>团购</title>
<link type="text/css" href="${pageContext.request.contextPath}/css/legend.css" rel="stylesheet"/>
</head>

<body>
<%@ include file="top.jsp" %>

<!----地址---->
 <div class="w addr">
   <span><a href="#">首页</a></span>&gt;<span>团购</span> 
 </div>
<!----地址end---->
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
      
      <div class="sidebox mar12" style="border:1px solid #fff;"></div>
       
       
    </div>
<!----右边end---->
    
    <!----左边---->
    <div class="t_wrapbig">
    <div class="t_wrap">
    
       <p class="w_title">往期团购</p>
    
      <div id="filter"><ul class="cf"><li class="first">全部分类</li><li class="current"><a href="/category/all">今日新单<span>(1)</span></a></li><li class=""><a href="/category/new">餐饮美食<span>(33)</span></a></li><li class=""><a href="/category/food">休闲娱乐<span>(12)</span></a></li><li class=""><a href="/category/life">生活服务<span>(14)</span></a></li><li class=""><a href="/category/online">网购精品<span>(9)</span></a></li></ul></div>
      
      <div class="along">
         <div class="aligna"><span class="fl">排序方式：</span> <a href="#">推荐</a><a href="#">人气</a><a href="#">价格</a><a href="#">即将过期</a></div>
         <div class="t_ss"> <span>搜索团购</span> <input name="" class="tss_input" type="text" /> <input name="" type="image" src="${pageContext.request.contextPath}/img/group/t_ss.jpg" /></div>
      </div>
     
     
     
        <!--------line1---------> 
         <div class="t_boxa t_past">
           <p><strong>2012年3月3日</strong></p>
           <p><a href="${pageContext.request.contextPath}/group/view/1${applicationScope.WEB_SUFFIX}">仅售55元！价值180元的华影星美双人观影套餐（含3D）</a></p>
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
         
         <div class="line"></div>
         
         <div class="t_boxa t_past">
           <p><strong>2012年3月3日</strong></p>
           <p><a href="${pageContext.request.contextPath}/group/view/1${applicationScope.WEB_SUFFIX}">仅售55元！价值180元的华影星美双人观影套餐（含3D）</a></p>
           <div class="t_imga">
             <a href="#"><img src="${pageContext.request.contextPath}/img/group/wq_timg.jpg" width="330" height="170" /></a>
             <div class="off"></div>
           </div>
           <div class="numboxa">
              <div class="totala"><span>1789</span>人已购买</div>
              <div class="partnuma">原价<br /><span>¥298</span></div>
              <div class="partnuma">折扣<br /><span>2.32</span></div>
              <div class="partnuma">节省<br /><span>¥229</span></div>
           </div>
           <div class="clear"></div>
         </div>
        <!--------line1--------->  
        
        
        
         <!--------line1---------> 
         <div class="t_boxa t_past">
           <p><strong>2012年3月3日</strong></p>
           <p><a href="${pageContext.request.contextPath}/group/view/1${applicationScope.WEB_SUFFIX}">仅售55元！价值180元的华影星美双人观影套餐（含3D）</a></p>
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
         
         <div class="line"></div>
         
             
        <div class="clear"></div>
        
         
         
         
         <div class="fanye">
          <span class="ysel">1</span>
          <span class="nsel"><a href="#">2</a></span>
          <span class="nsel"><a href="#">3</a></span>
          <span class="nsel"><a href="#">4</a></span>
          <span class="nsel"><a href="#">5</a></span>
          <span class="nsel"><a href="#">6</a></span>
          <span class="nsel"><a href="#">下一页</a></span>
          <span class="nsel"><a href="#">尾页</a></span>
         
         </div>     
     
    </div></div>
    <!----左边end---->
    
   <div class="clear"></div>
</div>
<!----两栏end---->


<%@ include file="foot.jsp" %>
 
</body>
</html>
