<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>

<!--nav-->
 <div class="nav">
   <div class="w wnav">
      <div class="nav_vbox">
         <div class="navbtn"><a href="#">全部商品分类</a></div> 
         
         <!------------------------------------>
           <div class="mc"  style="display:none;" >        
              <div class="item fore1 hover" >
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
                       <dl class="fore6"> 
                          <dt>人文社科</dt>
                          <dd><em><a href="#">历史</a></em><em><a href="#">哲学</a></em><em><a href="#">国学</a></em><em><a href="#">政治/军事</a></em><em><a href="#">法律</a></em><em><a href="#">宗教</a></em><em><a href="#">心理学</a></em><em><a href="#">文化</a></em><em><a href="#">社会科学</a></em></dd>
                       </dl>
                       <dl class="fore7">
                          <dt>经管励志</dt> 
                          <dd><em><a href="#">经济</a></em><em><a href="#">金融与投资</a></em><em><a href="#">管理</a></em><em><a href="#">励志与成功</a></em></dd>                   </dl>
                       <dl class="fore8">
                          <dt>生活</dt>
                          <dd><em><a href="#">生活</a></em><em><a href="#">健身与保健</a></em><em><a href="#">家庭与育儿</a></em><em><a href="#">旅游</a></em><em><a href="#">地图</a></em><em><a href="#">动漫/幽默</a></em></dd>
                       </dl>
                       <dl class="fore9">
                          <dt>科技</dt>
                          <dd><em><a href="#">科技</a></em><em><a href="#">工程</a></em><em><a href="#">建筑</a></em><em><a href="#">医学</a></em><em><a href="#">科学/自然</a></em><em><a href="#">计算机/互联网</a></em><em><a href="#">体育/运动</a></em></dd>
                       </dl>
                       <dl class="fore10">
                          <dt>教育</dt>
                          <dd><em><a href="#">教材教辅</a></em><em><a href="#">教育与考试</a></em><em><a href="#">外语学习</a></em><em><a href="#">新闻出版</a></em><em><a href="#">语言文字</a></em></dd>
                      </dl>
                      <dl class="fore11">
                         <dt>其它</dt>
                         <dd><em><a href="#">工具书</a></em><em><a href="#">影印版</a></em><em><a href="#">套装书</a></em><em><a href="#">期刊</a></em></dd>               
                      </dl>
                 </div></div>
                      
              </div>
              
              <div class="item fore2">
                  <span><h3><a href="#">家用电器、汽车用品</a></h3><s></s></span>
              </div> 
              
              <div class="item fore3">
                  <span><h3><a href="#">手机数码</a></h3><s></s></span>
              </div>        
                     
              <div class="item fore4">
                  <span><h3><a href="#">电脑、办公</a></h3><s></s></span>
              </div>        
                     
              <div class="item fore5">
                  <span><h3><a href="#">家居家装</a>、<a href="#">厨具</a></h3><s></s></span>
              </div>        
                     
              <div class="item fore6">
                  <span><h3><a href="#">服饰鞋帽</a></h3><s></s></span>
              </div>        
                     
              <div class="item fore7">
                  <span><h3><a href="#">个护化妆</a></h3><s></s></span>
              </div>        
              <div class="item fore8">
                  <span><h3><a href="#">礼品箱包</a>、<a href="#">钟表</a>、<a href="#">珠宝</a></h3><s></s></span>
              </div>        
                     
              <div class="item fore9">
                  <span><h3><a href="#">运动健康</a></h3><s></s></span>
              </div>       
                     
              <div class="item fore10">
                  <span><h3><a href="#">母婴</a>、<a href="#">玩具乐器</a></h3><s></s></span>
              </div>        
                     
              <div class="item fore11">
                   <span><h3><a href="#">食品饮料、保健食品</a></h3><s></s></span>
              </div>        
                     
               <div class="item fore12">
                   <span><h3><a href="#">彩票</a>、<a href="#">旅行</a>、<a href="#">充值</a>、<a href="#">票务</a></h3><s></s></span>               </div>
               <div class="extra"><a href="#">全部商品分类</a></div></div>
         
         
         <!------------------------------------->  
      </div>
      <ul>
        <li style="background:none"><a href="#">家用电器</a></li>
        <li><a href="#">手机数码</a></li>
        <li><a href="#">电脑产品</a></li>
        <li><a href="#">个人护理</a></li>
        <li><a href="#">日用百货</a></li>
        <li><a href="#">时尚服饰</a></li>
        <li class="focus"><a href="#">团购</a></li>
      </ul>
   </div> 
 </div>
<!--nav end-->

<div class="w pagetab "  style="padding-top:20px;">
       <ul>           
          <li><span><a href="#">全部商品分类</a></span></li>                  
         <li class="on"><span><a href="#">全部品牌</a></span></li>
         <li><span><a href="#">全部商品</a></span></li>      
       </ul>       
</div>

<!----两栏---->
 <div class="w" style="padding-top:10px;"> 
      <div class="brandbox">
        <div class="mt">
			<h2>推荐品牌</h2>
		</div>
		<div class="mc brandslist">
					<ul class="list-h">
                    <li><div><span class="b-img"><a href="#" ><img width="138" height="46" src="../images/138_46_sMKggh.jpg" /></a></span><span class="b-name"><a href="#" >Razer-始于玩家赋予玩家</a></span></div></li>
                    <li><div><span class="b-img"><a href="#" ><img width="138" height="46" src="../images/138_46_sMKggh.jpg" /></a></span><span class="b-name"><a href="#" >Razer-始于玩家赋予玩家</a></span></div></li>
                    <li><div><span class="b-img"><a href="#" ><img width="138" height="46" src="../images/138_46_sMKggh.jpg" /></a></span><span class="b-name"><a href="#" >Razer-始于玩家赋予玩家</a></span></div></li>
                    <li><div><span class="b-img"><a href="#" ><img width="138" height="46" src="../images/138_46_sMKggh.jpg" /></a></span><span class="b-name"><a href="#" >Razer-始于玩家赋予玩家</a></span></div></li>
                    <li><div><span class="b-img"><a href="#" ><img width="138" height="46" src="../images/138_46_sMKggh.jpg" /></a></span><span class="b-name"><a href="#" >Razer-始于玩家赋予玩家</a></span></div></li>
                    <li><div><span class="b-img"><a href="#" ><img width="138" height="46" src="../images/138_46_sMKggh.jpg" /></a></span><span class="b-name"><a href="#" >Razer-始于玩家赋予玩家</a></span></div></li>
                    <li><div><span class="b-img"><a href="#" ><img width="138" height="46" src="../images/138_46_sMKggh.jpg" /></a></span><span class="b-name"><a href="#" >Razer-始于玩家赋予玩家</a></span></div></li>
                    <li><div><span class="b-img"><a href="#" ><img width="138" height="46" src="../images/138_46_sMKggh.jpg" /></a></span><span class="b-name"><a href="#" >Razer-始于玩家赋予玩家</a></span></div></li>
                    <li><div><span class="b-img"><a href="#" ><img width="138" height="46" src="../images/138_46_sMKggh.jpg" /></a></span><span class="b-name"><a href="#" >Razer-始于玩家赋予玩家</a></span></div></li>
                    <li><div><span class="b-img"><a href="#" ><img width="138" height="46" src="../images/138_46_sMKggh.jpg" /></a></span><span class="b-name"><a href="#" >Razer-始于玩家赋予玩家</a></span></div></li>
                    <li><div><span class="b-img"><a href="#" ><img width="138" height="46" src="../images/138_46_sMKggh.jpg" /></a></span><span class="b-name"><a href="#" >Razer-始于玩家赋予玩家</a></span></div></li>
                    <li><div><span class="b-img"><a href="#" ><img width="138" height="46" src="../images/138_46_sMKggh.jpg" /></a></span><span class="b-name"><a href="#" >Razer-始于玩家赋予玩家</a></span></div></li>
                    <li><div><span class="b-img"><a href="#" ><img width="138" height="46" src="../images/138_46_sMKggh.jpg" /></a></span><span class="b-name"><a href="#" >Razer-始于玩家赋予玩家</a></span></div></li>
                    <li><div><span class="b-img"><a href="#" ><img width="138" height="46" src="../images/138_46_sMKggh.jpg" /></a></span><span class="b-name"><a href="#" >Razer-始于玩家赋予玩家</a></span></div></li>
                    <li><div><span class="b-img"><a href="#" ><img width="138" height="46" src="../images/138_46_sMKggh.jpg" /></a></span><span class="b-name"><a href="#" >Razer-始于玩家赋予玩家</a></span></div></li>
                    <li><div><span class="b-img"><a href="#" ><img width="138" height="46" src="../images/138_46_sMKggh.jpg" /></a></span><span class="b-name"><a href="#" >Razer-始于玩家赋予玩家</a></span></div></li>
                    <li><div><span class="b-img"><a href="#" ><img width="138" height="46" src="../images/138_46_sMKggh.jpg" /></a></span><span class="b-name"><a href="#" >Razer-始于玩家赋予玩家</a></span></div></li>
                    <li><div><span class="b-img"><a href="#" ><img width="138" height="46" src="../images/138_46_sMKggh.jpg" /></a></span><span class="b-name"><a href="#" >Razer-始于玩家赋予玩家</a></span></div></li></ul>
		</div>
        
        
        <div class="clear"></div>
        <div class="i-w" style="background:none; border:0; padding-top:30px;">			
			<ul class="tab">
			    <li class="l1 curr"><a href="#">图书、音像</a></li>
				<li><a href="#">家用电器<br>汽车用品</a></li>
				<li class="l1"><a href="#">手机数码</a></li>
				<li><a href="#">电脑<br>软件、办公</a></li>
				<li><a href="#">家具<br>厨具、家装</a></li>
				<li class="l1"><a href="#">服饰鞋帽</a></li>
				<li class="l1"><a href="#">个护化妆</a></li>
				<li><a href="#">礼品箱包<br>钟表首饰</a></li>
				<li class="l1"><a href="#">运动健康</a></li>
				<li><a href="#">母婴<br>玩具、乐器</a></li>
			</ul>
            <div class="clear"></div>
        </div>
        
        
		<div class="mc brandslist">
					<ul class="list-h">
                    <li><div><span class="b-img"><a href="#" ><img width="138" height="46" src="../images/138_46_sMKggh.jpg" /></a></span><span class="b-name"><a href="#" >Razer-始于玩家赋予玩家</a></span></div></li>
                    <li><div><span class="b-img"><a href="#" ><img width="138" height="46" src="../images/138_46_sMKggh.jpg" /></a></span><span class="b-name"><a href="#" >Razer-始于玩家赋予玩家</a></span></div></li>
                    <li><div><span class="b-img"><a href="#" ><img width="138" height="46" src="../images/138_46_sMKggh.jpg" /></a></span><span class="b-name"><a href="#" >Razer-始于玩家赋予玩家</a></span></div></li>
                    <li><div><span class="b-img"><a href="#" ><img width="138" height="46" src="../images/138_46_sMKggh.jpg" /></a></span><span class="b-name"><a href="#" >Razer-始于玩家赋予玩家</a></span></div></li>
                    <li><div><span class="b-img"><a href="#" ><img width="138" height="46" src="../images/138_46_sMKggh.jpg" /></a></span><span class="b-name"><a href="#" >Razer-始于玩家赋予玩家</a></span></div></li>
                    <li><div><span class="b-img"><a href="#" ><img width="138" height="46" src="../images/138_46_sMKggh.jpg" /></a></span><span class="b-name"><a href="#" >Razer-始于玩家赋予玩家</a></span></div></li>
                    <li><div><span class="b-img"><a href="#" ><img width="138" height="46" src="../images/138_46_sMKggh.jpg" /></a></span><span class="b-name"><a href="#" >Razer-始于玩家赋予玩家</a></span></div></li>
                    <li><div><span class="b-img"><a href="#" ><img width="138" height="46" src="../images/138_46_sMKggh.jpg" /></a></span><span class="b-name"><a href="#" >Razer-始于玩家赋予玩家</a></span></div></li>
                    <li><div><span class="b-img"><a href="#" ><img width="138" height="46" src="../images/138_46_sMKggh.jpg" /></a></span><span class="b-name"><a href="#" >Razer-始于玩家赋予玩家</a></span></div></li>
                    <li><div><span class="b-img"><a href="#" ><img width="138" height="46" src="../images/138_46_sMKggh.jpg" /></a></span><span class="b-name"><a href="#" >Razer-始于玩家赋予玩家</a></span></div></li>
                    <li><div><span class="b-img"><a href="#" ><img width="138" height="46" src="../images/138_46_sMKggh.jpg" /></a></span><span class="b-name"><a href="#" >Razer-始于玩家赋予玩家</a></span></div></li>
                    <li><div><span class="b-img"><a href="#" ><img width="138" height="46" src="../images/138_46_sMKggh.jpg" /></a></span><span class="b-name"><a href="#" >Razer-始于玩家赋予玩家</a></span></div></li>
                    <li><div><span class="b-img"><a href="#" ><img width="138" height="46" src="../images/138_46_sMKggh.jpg" /></a></span><span class="b-name"><a href="#" >Razer-始于玩家赋予玩家</a></span></div></li>
                    <li><div><span class="b-img"><a href="#" ><img width="138" height="46" src="../images/138_46_sMKggh.jpg" /></a></span><span class="b-name"><a href="#" >Razer-始于玩家赋予玩家</a></span></div></li>
                    <li><div><span class="b-img"><a href="#" ><img width="138" height="46" src="../images/138_46_sMKggh.jpg" /></a></span><span class="b-name"><a href="#" >Razer-始于玩家赋予玩家</a></span></div></li>
                    <li><div><span class="b-img"><a href="#" ><img width="138" height="46" src="../images/138_46_sMKggh.jpg" /></a></span><span class="b-name"><a href="#" >Razer-始于玩家赋予玩家</a></span></div></li>
                    <li><div><span class="b-img"><a href="#" ><img width="138" height="46" src="../images/138_46_sMKggh.jpg" /></a></span><span class="b-name"><a href="#" >Razer-始于玩家赋予玩家</a></span></div></li>
                    <li><div><span class="b-img"><a href="#" ><img width="138" height="46" src="../images/138_46_sMKggh.jpg" /></a></span><span class="b-name"><a href="#" >Razer-始于玩家赋予玩家</a></span></div></li></ul>
					</div>
       <div class="clear"></div>
       
       <div class="extra"><strong>更多品牌：</strong><a href="#" target="_blank">力易得</a><a href="#" target="_blank">迪美</a><a href="#" target="_blank">巧太太</a><a href="#" target="_blank">九安</a><a href="#" target="_blank">步步高</a><a href="#" target="_blank">SKG</a><a href="#" target="_blank">大金</a><a href="#" target="_blank">格来德</a><a href="#" target="_blank">西门子</a><a href="#" target="_blank">minimum</a><a href="#" target="_blank">金渴望</a><a href="#" target="_blank">莱尔诗丹</a><a href="#" target="_blank">轻松伴侣</a><a href="#" target="_blank">逸花</a><a href="#" target="_blank">荣泰</a><a href="#" target="_blank">乐上</a><a href="#">小鸭</a><a href="#" target="_blank">乐华</a><a href="#" target="_blank">现代</a><a href="#" target="_blank">凯仕乐</a><a href="#" target="_blank">韩京姬</a><a href="#" target="_blank">小天鹅</a><a href="#" target="_blank">突破</a><a href="#" target="_blank">日立</a><a href="#" target="_blank">浩泽</a><a href="#" target="_blank">虎牌</a><a href="#" target="_blank">璐瑶</a><a href="#" target="_blank">艾力斯特</a><a href="#" target="_blank">星星</a><a href="#" target="_blank">多固</a><a href="#" target="_blank">山鹰</a><a href="#" target="_blank">利仁</a><a href="#" target="_blank">法恩莎</a><a href="#" target="_blank">CE-LINK</a><a href="#" target="_blank">圣宝</a></div>
      </div>    
  </div>
<!----两栏end---->
