<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>

<!--nav-->
 <div class="nav">
   <div class="w wnav">
      <div class="nav_vbox">
         <div class="navbtn"><a href="#">全部商品分类</a></div> 
         

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
<!----地址---->
 <div class="w addr">
   <span><a href="#">首页</a></span>&gt;<span><a href="#">电脑办公</a></span>&gt;<span><a href="#">电脑整机</a></span>&gt;<span>笔记本</span>
 </div>
<!----地址end---->



<div class="w main">
        <div class="leftm" style="padding-top:10px;">


			<div class="mini_m" >
				<div class="mt">
					<h2>手机数码</h2>
				</div>
				<div class="mc">
                    <div class="item current"><h3><b></b>手机通讯</h3>
                       <ul><li><a href="#">手机</a></li><li><a href="#">对讲机</a></li><li><a href="#">手机</a></li></ul>
                    </div>
                    <div class="item"><h3><b></b>运营商</h3></div>
                    <div class="item"><h3><b></b>手机配件</h3></div>
                    <div class="item"><h3><b></b>摄影摄像</h3></div>
                    <div class="item"><h3><b></b>数码配件</h3></div>
                    <div class="item"><h3><b></b>时尚影音</h3></div>
				</div>
			</div>

			
			<!--关注手机最终购买-->
			
			<div class=" rank" >
				<div class="mt">
					<h2>一周销量排行榜</h2>
				</div>
				<div class="mc">
					<ul class="tabcon">
                        <li class="fore"><span>1</span>
                          <div class="p-img"><a href="#">
                          <img src="http://img10.360buyimg.com/n5/358/e2d99477-aa07-4a75-9b1e-909a20a7b129.jpg" alt="诺基亚（NOKIA）5233 GSM手机（黑）非定制机" height="50" width="50"></a></div>
                          <div class="p-name"><a href="#">诺基亚（NOKIA）5233 GSM手机（黑）非定制机<font color="#ff6600"></font></a></div>
                          <div class="p-price"><strong><img src="http://price.360buyimg.com//gp198955,1.png"></strong></div></li>
                      <li><span>2</span><div class="p-name"><a href="#">诺基亚（NOKIA）5233 GSM手机（白）非定制机<font color="#ff6600"></font></a></div></li>
                      <li><span>3</span><div class="p-name"><a href="#">诺基亚（NOKIA）1000 GSM手机（蓝色）非定制机<font color="#ff6600">超值直板设计！</font></a></div></li>
                      <li><span>4</span><div class="p-name"><a href="#">诺基亚（NOKIA）C1-02 GSM手机（黑）移动定制机<font color="#ff6600">特价直降10元！超值直板设计！</font></a></div></li>
                      <li><span>5</span><div class="p-name"><a href="#">诺基亚（NOKIA）N1280 GSM手机（黑）非定制机<font color="#ff6600">大品牌！还有比这款便宜好用的诺基亚手机么？</font></a></div></li>
                      <li><span>6</span><div class="p-name"><a href="#">欧谷（OUGU）EG318 青花馆 3G手机（白色）<font color="#ff6600">CDMA2000/GSM 双模双待 世博限量版青花真瓷手机</font></a></div></li>
                      <li><span>7</span><div class="p-name"><a href="#">三星（SAMSUNG）S5830I 3G手机（玛瑙黑）WCDMA/GSM<font color="#ff6600">Android 2.3系统，500万像素摄像~</font></a></div></li>
                      <li><span>8</span><div class="p-name"><a href="#">国虹 W100 水滴 3G手机（钢琴黑＋珍珠白）WCDMA/GSM 双卡双待<font color="#ff6600">京东全国独家震撼首发！首单数量有限，下手要快！Android2.3,3.5寸电容屏，800MHZ主频，精工品质！</font></a></div></li>

					</ul>
                    <div class="clear"></div>
				</div>
			</div><!--rank end-->
            
            
            
			
	</div>
        <!--left end-->
        
        
        <div class="right-extra">
            <div class="m" id="pubcomment" style="margin-top:10px;">
                <div class="mt">
                    <h2>顾客留言</h2>
                </div>
                   
                 <div class="mc form">
    <div class="prompt">
	        如果您对本网站有任何的意见建议<br>	        
	        欢迎留下您的见解，我们将与您共同进步，创造更为良好的购物体验。
    </div>

    <dl>
        <dt>&nbsp;&nbsp;&nbsp;&nbsp;用户名</dt>
        <dd>
            <input type="text" class="text input" name="title" id="title" value="aaa">
        </dd>
    </dl>
    
    
    <div id="Re_Explain">
    <dl>
        <dt>&nbsp;&nbsp;&nbsp;&nbsp;内容</dt>

        <dd>
            <textarea class="text textarea" name="content" id="content" style="height:200px;"></textarea>
            <label>
                填写您的宝贵意见。最多可输入200字。</label>
        </dd>
    </dl>
    <dl>

        <dt>&nbsp;&nbsp;&nbsp;&nbsp;验证码</dt>
        <dd>
            <input type="text" class="text input" name="pros" id="pros" style="width:120px;">
            <div class="fl" style="padding:0 10px;"><img src="../images/yzm.jpg" width="128" height="50" /></div>
            <label id="proserror">点击图片刷新</label>
        </dd>
    </dl>
    <input type="button" class="btn-img btn-submit" value="提交" id="saveComment" style="margin-left:15px;">

    </div>
</div>  
            </div>
            <!--pubcomment end-->
        </div>
        <!--right-extra end-->
        <div class="clear"></div>
    </div>