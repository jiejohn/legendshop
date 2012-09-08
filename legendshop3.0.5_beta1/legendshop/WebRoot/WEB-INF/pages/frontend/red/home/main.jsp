<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<!--nav-->
<div class="nav navCC">
	<div class="w wnav">
		<div class="nav_vbox">
			<div class="navbtn">
				<a href="#">全部商品分类</a>
			</div>

			<!------------------------------------>
			<div class="mc">
				<div class="item fore1 ">
					<span><h3>
							<a href="#">图书</a>、<a href="#">电子书刊</a>、<a href="#">音像</a>
						</h3> <s></s></span>

					<div class="i-mc" style="">
						<div class="subitem">
							<dl class="fore1">
								<dt>
									<a href="#">电子书刊</a>
								</dt>
								<dd>
									<em><a href="#">电子书</a></em><em><a href="#">网络原创</a></em><em><a
										href="#">数字杂志</a></em><em><a href="#">多媒体图书</a></em>
								</dd>
							</dl>
							<dl class="fore2">
								<dt>
									<a href="#">音像</a>
								</dt>
								<dd>
									<em><a href="#">音乐</a></em><em><a href="#">影视</a></em><em><a
										href="#">教育音像</a></em>
								</dd>
							</dl>
							<dl class="fore3">
								<dt>英文原版</dt>
								<dd>
									<em><a href="#">英文原版首页</a></em><em><a href="#">少儿</a></em><em><a
										href="#">商业投资</a></em><em><a href="#">英语学习与考试</a></em><em><a
										href="#">小说</a></em>
								</dd>
							</dl>
							<dl class="fore4">
								<dt>文艺</dt>
								<dd>
									<em><a href="#">小说</a></em><em><a href="#">文学</a></em><em><a
										href="#">青春文学</a></em><em><a href="#">传记</a></em><em><a
										href="#">艺术</a></em>
								</dd>
							</dl>
							<dl class="fore5">
								<dt>少儿</dt>
								<dd>
									<em><a href="#">少儿</a></em><em><a href="#">0-2岁</a></em><em><a
										href="#">3-6岁</a></em><em><a href="#">7-10岁</a></em><em><a
										href="#">11-14岁</a></em>
								</dd>
							</dl>
							<dl class="fore6">
								<dt>人文社科</dt>
								<dd>
									<em><a href="#">历史</a></em><em><a href="#">哲学</a></em><em><a
										href="#">国学</a></em><em><a href="#">政治/军事</a></em><em><a
										href="#">法律</a></em><em><a href="#">宗教</a></em><em><a href="#">心理学</a></em><em><a
										href="#">文化</a></em><em><a href="#">社会科学</a></em>
								</dd>
							</dl>
							<dl class="fore7">
								<dt>经管励志</dt>
								<dd>
									<em><a href="#">经济</a></em><em><a href="#">金融与投资</a></em><em><a
										href="#">管理</a></em><em><a href="#">励志与成功</a></em>
								</dd>
							</dl>
							<dl class="fore8">
								<dt>生活</dt>
								<dd>
									<em><a href="#">生活</a></em><em><a href="#">健身与保健</a></em><em><a
										href="#">家庭与育儿</a></em><em><a href="#">旅游</a></em><em><a
										href="#">地图</a></em><em><a href="#">动漫/幽默</a></em>
								</dd>
							</dl>
							<dl class="fore9">
								<dt>科技</dt>
								<dd>
									<em><a href="#">科技</a></em><em><a href="#">工程</a></em><em><a
										href="#">建筑</a></em><em><a href="#">医学</a></em><em><a href="#">科学/自然</a></em><em><a
										href="#">计算机/互联网</a></em><em><a href="#">体育/运动</a></em>
								</dd>
							</dl>
							<dl class="fore10">
								<dt>教育</dt>
								<dd>
									<em><a href="#">教材教辅</a></em><em><a href="#">教育与考试</a></em><em><a
										href="#">外语学习</a></em><em><a href="#">新闻出版</a></em><em><a
										href="#">语言文字</a></em>
								</dd>
							</dl>
							<dl class="fore11">
								<dt>其它</dt>
								<dd>
									<em><a href="#">工具书</a></em><em><a href="#">影印版</a></em><em><a
										href="#">套装书</a></em><em><a href="#">期刊</a></em>
								</dd>
							</dl>
						</div>
					</div>

				</div>

				<div class="item fore2">
					<span><h3>
							<a href="#">家用电器、汽车用品</a>
						</h3> </span>
				</div>

				<c:forEach items="${sortList}" var="sort" end="5" varStatus="idx">
					<c:choose>
						<c:when test="${idx.index==0}">

							<div class="item fore2">
								<span><h3>
										<a href="#">${sort.sortName}</a>
									</h3> </span>
							</div>
						</c:when>
						<c:otherwise>
							<div class="item fore2">
								<span><h3>
										<a href="#">${sort.sortName}</a>
									</h3> </span>
							</div>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<div class="extra">
					<a href="#">全部商品分类</a>
				</div>
			</div>


			<!------------------------------------->
		</div>
		<ul>
			<c:forEach items="${sortList}" var="sort" end="5" varStatus="idx">
				<c:choose>
					<c:when test="${idx.index==0}">
						<li style="background: none"><a href="#">${sort.sortName}</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="">${sort.sortName}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<li class="focus"><a href="#">团购</a></li>
		</ul>
	</div>
</div>
<!--nav end-->

<!----advtisement---->
<div class="w" style="margin-top: 10px; height: 485px;">
	<div class="indexgg indexggCC">
		<div class="ggtit">
			<div class="ggmore">
				<a href="#"><img
					src="<ls:templateResource item='/common/red/images/gg_more.jpg'/>"
					width="56" height="39" /></a>
			</div>
			<h3>商城公告</h3>
		</div>
		<ul class="lglist">
			<c:forEach items="${pubList}" var="pub" begin="0" end="5">
				<li><a href="${pub.msg}" title="${pub.title}">${pub.title}</a></li>
			</c:forEach>
		</ul>


		<div class="setshop">
			<a href="#"><img src="../images/setshop.jpg" width="324"
				height="57" /></a>
		</div>
	</div>

	<div class="indexflash">
		<c:forEach items="${indexJpgList}" var="slideAdv" begin="0" end="0">
			<a href="${slideAdv.link}" title="${slideAdv.title}"><img
				src="<ls:images item='${slideAdv.img}'/>"
				alt="${slideAdv.alt}" width="650" height="335"> </a>
		</c:forEach>
		<div class="fla_num">
			<a href="#">1</a><a href="#" class="focus">2</a><a href="#">3</a><a
				href="#">4</a><a href="#">5</a><a href="#">6</a>
		</div>
	</div>
	<div class="indexhot">
		<img src="../images/222_150_MdQlQS.jpg" width="222" height="150" /> <img
			src="../images/222_150_MdQlQS.jpg" width="222" height="150" /> <img
			src="../images/222_150_MdQlQS.jpg" width="222" height="150" /> <img
			src="../images/222_150_MdQlQS.jpg" width="222" height="150" />
	</div>
	<div class="clear"></div>
</div>


<!----up两栏---->
<div class="w">
	<!----右边---->
	<div class="index_right">
		<div class="side2">
			<h3>热销产品</h3>

			<div class="CChot">
				<div class="CCpic">
					<img
						src="<ls:templateResource item='/common/red/images/cloth.jpg'/>"
						width="150" height="150" />
				</div>
				<p class="CC-name">新款长袖针织衫</p>
				<p class="CC-price">
					￥<span>148.00</span>
				</p>
				<p class="CC-num mailfree">
					<input name="" type="image" src="../images/mailfree.jpg" />月销<span>279</span>
				</p>
			</div>

			<div class="CChot">
				<div class="CCpic">
					<img src="../images/cloth.jpg" width="150" height="150" />
				</div>
				<p class="CC-name">新款长袖针织衫</p>
				<p class="CC-price">
					￥<span>148.00</span>
				</p>
				<p class="CC-num mailfree">
					<input name="" type="image" src="../images/mailfree.jpg" />月销<span>279</span>
				</p>
			</div>

			<div class="ccline"></div>

			<div class="CChot">
				<div class="CCpic">
					<img src="../images/cloth.jpg" width="150" height="150" />
				</div>
				<p class="CC-name">新款长袖针织衫</p>
				<p class="CC-price">
					￥<span>148.00</span>
				</p>
				<p class="CC-num mailfree">
					<input name="" type="image" src="../images/mailfree.jpg" />月销<span>279</span>
				</p>
			</div>

			<div class="CChot">
				<div class="CCpic">
					<img src="../images/cloth.jpg" width="150" height="150" />
				</div>
				<p class="CC-name">新款长袖针织衫</p>
				<p class="CC-price">
					￥<span>148.00</span>
				</p>
				<p class="CC-num">
					<input name="" type="image" src="../images/mailfree.jpg" />月销<span>279</span>
				</p>
			</div>
			<div class="clear"></div>
		</div>
	</div>
	<!----右边end---->

	<!----左边---->
	<div class="index_left">
		<div class="CCbox">
			<h2 class="CCtit">
				<span><a href="#">更多品牌 &gt;&gt;</a></span>品牌导航
			</h2>
			<div class="banddiv" style="padding: 0;">
				<table border="1">
					<tr>
						<td><a href="#"><img src="../images/bandl.jpg" /></a></td>
						<td><a href="#"><img src="../images/bandl.jpg" /></a></td>
						<td><a href="#"><img src="../images/bandl.jpg" /></a></td>
						<td><a href="#"><img src="../images/bandl.jpg" /></a></td>
						<td><a href="#"><img src="../images/bandl.jpg" /></a></td>
						<td><a href="#"><img src="../images/bandl.jpg" /></a></td>
						<td><a href="#"><img src="../images/bandl.jpg" /></a></td>
					</tr>
					<tr>
						<td><a href="#"><img src="../images/bandl.jpg" /></a></td>
						<td><a href="#"><img src="../images/bandl.jpg" /></a></td>
						<td><a href="#"><img src="../images/bandl.jpg" /></a></td>
						<td><a href="#"><img src="../images/bandl.jpg" /></a></td>
						<td><a href="#"><img src="../images/bandl.jpg" /></a></td>
						<td><a href="#"><img src="../images/bandl.jpg" /></a></td>
						<td><a href="#"><img src="../images/bandl.jpg" /></a></td>
					</tr>

				</table>
			</div>
		</div>

		<div class="CCbox" style="margin-top: 10px;">
			<h2 class="CCtit">
				<span><a href="#">更多商城 &gt;&gt;</a></span>明星商城
			</h2>
			<div class="startable">
				<table>
					<tr>
						<td><div class="starpic">
								<img src="../images/star.jpg" width="125" height="125" />
							</div>
							<p class="storename">
								<a href="#">优质店铺珍藏馆</a>
							</p>
							<p class="starname">
								<a href="#">@lw050123</a>
							</p></td>
						<td><div class="starpic">
								<img src="../images/star.jpg" width="125" height="125" />
							</div>
							<p class="storename">
								<a href="#">优质店铺珍藏馆</a>
							</p>
							<p class="starname">
								<a href="#">@lw050123</a>
							</p></td>
						<td><div class="starpic">
								<img src="../images/star.jpg" width="125" height="125" />
							</div>
							<p class="storename">
								<a href="#">优质店铺珍藏馆</a>
							</p>
							<p class="starname">
								<a href="#">@lw050123</a>
							</p></td>
						<td><div class="starpic">
								<img src="../images/star.jpg" width="125" height="125" />
							</div>
							<p class="storename">
								<a href="#">优质店铺珍藏馆</a>
							</p>
							<p class="starname">
								<a href="#">@lw050123</a>
							</p></td>
						<td><div class="starpic">
								<img src="../images/star.jpg" width="125" height="125" />
							</div>
							<p class="storename">
								<a href="#">优质店铺珍藏馆</a>
							</p>
							<p class="starname">
								<a href="#">@lw050123</a>
							</p></td>
						<td><div class="starpic">
								<img src="../images/star.jpg" width="125" height="125" />
							</div>
							<p class="storename">
								<a href="#">优质店铺珍藏馆</a>
							</p>
							<p class="starname">
								<a href="#">@lw050123</a>
							</p></td>

					</tr>
					<tr>
						<td><div class="starpic">
								<img src="../images/star.jpg" width="125" height="125" />
							</div>
							<p class="storename">
								<a href="#">优质店铺珍藏馆</a>
							</p>
							<p class="starname">
								<a href="#">@lw050123</a>
							</p></td>
						<td><div class="starpic">
								<img src="../images/star.jpg" width="125" height="125" />
							</div>
							<p class="storename">
								<a href="#">优质店铺珍藏馆</a>
							</p>
							<p class="starname">
								<a href="#">@lw050123</a>
							</p></td>
						<td><div class="starpic">
								<img src="../images/star.jpg" width="125" height="125" />
							</div>
							<p class="storename">
								<a href="#">优质店铺珍藏馆</a>
							</p>
							<p class="starname">
								<a href="#">@lw050123</a>
							</p></td>
						<td><div class="starpic">
								<img src="../images/star.jpg" width="125" height="125" />
							</div>
							<p class="storename">
								<a href="#">优质店铺珍藏馆</a>
							</p>
							<p class="starname">
								<a href="#">@lw050123</a>
							</p></td>
						<td><div class="starpic">
								<img src="../images/star.jpg" width="125" height="125" />
							</div>
							<p class="storename">
								<a href="#">优质店铺珍藏馆</a>
							</p>
							<p class="starname">
								<a href="#">@lw050123</a>
							</p></td>
						<td><div class="starpic">
								<img src="../images/star.jpg" width="125" height="125" />
							</div>
							<p class="storename">
								<a href="#">优质店铺珍藏馆</a>
							</p>
							<p class="starname">
								<a href="#">@lw050123</a>
							</p></td>

					</tr>

				</table>

			</div>

		</div>
	</div>
	<!----左边end---->
	<div class="clear"></div>
</div>
<!----up两栏end---->

<!----advtisementend---->


<div class="w banner1">
	<img src="../images/index_adv.jpg" />
</div>

<!----blue两栏---->
<div class="w">
	<!----右边---->
	<div class="index_right">
		<div class="side2">
			<h3>
				<span><a href="#">更多团购 &gt;</a></span>今日团购
			</h3>
			<div class="sm_pic">
				<img src="../images/promins.jpg" width="85" height="85" />
			</div>
			<p class="it_info">
				<a href="#">仅38元，欢享价值300元iMovie影城套餐，24小时可选，1张券可供2-3人看，在1间情侣包厢浪漫观影，第84届奥斯卡佳片通看。超</a><br />
				<span class="graytxt" style="text-decoration: line-through;">原价：300.00</span>
			</p>
			<div class="itprice">
				<a href="#">团购价：<span>￥80</span></a>
			</div>

		</div>

		<div style="margin-top: 10px;">
			<img src="../images/radv1.jpg" />
		</div>
		<div style="margin-top: 10px;">
			<img src="../images/radv2.jpg" />
		</div>


	</div>
	<!----右边end---->

	<!----左边---->
	<div class="index_left">
		<div class="indexlp blueback">
			<h3>电脑数码</h3>
			<ul>
				<li class="focus"><a href="#">特价商品</a></li>
				<li><a href="#">笔记本</a></li>
				<li><a href="#">影音数码</a></li>
				<li><a href="#">DIY攒机</a></li>
				<li><a href="#">办公打印</a></li>
			</ul>
		</div>
		<div class="indexlpdiv">
			<div class="lpleft smback">
				<ul>
					<li><a href="#">• 台式机</a></li>
					<li><a href="#">• 数码相机</a></li>
					<li><a href="#">• 笔记本</a></li>
					<li><a href="#">• 单反相机</a></li>
					<li><a href="#">• 平板电脑</a></li>
					<li><a href="#">• MP3/MP4</a></li>
					<li><a href="#">• 台式机</a></li>
					<li><a href="#">• 数码相机</a></li>
					<li><a href="#">• 笔记本</a></li>
					<li><a href="#">• 单反相机</a></li>
					<li><a href="#">• 平板电脑</a></li>
					<li><a href="#">• MP3/MP4</a></li>
				</ul>
			</div>
			<div class="lptable">
				<table>
					<tr>
						<td>
							<div class="ppicdiv">
								<a href="#"><img src="../images/sm1.jpg" /></a>
							</div>
							<p>
								G4 i5 14寸笔记本<br />商城价：<span>￥3629.00</span>
							</p>
						</td>
						<td>
							<div class="ppicdiv">
								<a href="#"><img src="../images/sm2.jpg" /></a>
							</div>
							<p>
								G4 i5 14寸笔记本<br />商城价：<span>￥3629.00</span>
							</p>
						</td>
						<td>
							<div class="ppicdiv">
								<a href="#"><img src="../images/sm2.jpg" /></a>
							</div>
							<p>
								G4 i5 14寸笔记本<br />商城价：<span>￥3629.00</span>
							</p>
						</td>
						<td>
							<div class="ppicdiv">
								<a href="#"><img src="../images/sm1.jpg" /></a>
							</div>
							<p>
								G4 i5 14寸笔记本<br />商城价：<span>￥3629.00</span>
							</p>
						</td>
					</tr>
					<tr>
						<td>
							<div class="ppicdiv">
								<a href="#"><img src="../images/sm2.jpg" /></a>
							</div>
							<p>
								G4 i5 14寸笔记本<br />商城价：<span>￥3629.00</span>
							</p>
						</td>
						<td>
							<div class="ppicdiv">
								<a href="#"><img src="../images/sm1.jpg" /></a>
							</div>
							<p>
								G4 i5 14寸笔记本<br />商城价：<span>￥3629.00</span>
							</p>
						</td>
						<td>
							<div class="ppicdiv">
								<a href="#"><img src="../images/sm1.jpg" /></a>
							</div>
							<p>
								G4 i5 14寸笔记本<br />商城价：<span>￥3629.00</span>
							</p>
						</td>
						<td>
							<div class="ppicdiv">
								<a href="#"><img src="../images/sm2.jpg" /></a>
							</div>
							<p>
								G4 i5 14寸笔记本<br />商城价：<span>￥3629.00</span>
							</p>
						</td>
					</tr>
				</table>
			</div>
			<div class="clear"></div>
		</div>




	</div>
	<!----左边end---->

	<div class="clear"></div>
</div>
<!----blue两栏end---->


<!----advtisementend---->


<div class="w banner1">
	<img src="../images/index_adv2.jpg" />
</div>

<!----red两栏---->
<div class="w">
	<!----右边---->
	<div class="index_right">
		<div class="side2">
			<h3>
				<span><a href="#">更多品牌 &gt;</a></span>品牌旗舰店
			</h3>
			<div class="banddiv">
				<table border="1">
					<tr>
						<td><a href="#"><img src="../images/band1.jpg" /></a></td>
						<td><a href="#"><img src="../images/band2.jpg" /></a></td>
						<td><a href="#"><img src="../images/band3.jpg" /></a></td>
					</tr>
					<tr>
						<td><a href="#"><img src="../images/band4.jpg" /></a></td>
						<td><a href="#"><img src="../images/band5.jpg" /></a></td>
						<td><a href="#"><img src="../images/band6.jpg" /></a></td>
					</tr>
					<tr>
						<td><a href="#"><img src="../images/band7.jpg" /></a></td>
						<td><a href="#"><img src="../images/band8.jpg" /></a></td>
						<td><a href="#"><img src="../images/band9.jpg" /></a></td>
					</tr>
				</table>
			</div>
		</div>

		<div class="side2" style="margin-top: 10px;">
			<h3>特价促销</h3>
			<ul class="lista font12 blue_a listimg1"
				style="margin-left: 10px; height: 212px;">
				<li><a href="#">品牌女包季末清仓69元起</a></li>
				<li><a href="#">我比美食更有诱惑力！---韩国明进工坊</a></li>
				<li><a href="#">樱春季 忙护理 资生堂全场满196赠！</a></li>
				<li><a href="#">IDF买就送精美丝巾</a></li>
				<li><a href="#">三月迎春，最爱就“饰”你！</a></li>
				<li><a href="#">三月迎春，最爱就“饰”你！</a></li>
				<li><a href="#">三月迎春，最爱就“饰”你！</a></li>
				<li><a href="#">三月迎春，最爱就“饰”你！</a></li>
			</ul>
		</div>
	</div>
	<!----右边end---->

	<!----左边---->
	<div class="index_left">
		<div class="indexlp redback">
			<h3>电脑数码</h3>
			<ul>
				<li class="focus"><a href="#">特价商品</a></li>
				<li><a href="#">笔记本</a></li>
				<li><a href="#">影音数码</a></li>
				<li><a href="#">DIY攒机</a></li>
				<li><a href="#">办公打印</a></li>
			</ul>
		</div>
		<div class="indexlpdiv">
			<div class="lpleft ssback">
				<ul>
					<li><a href="#">• 女装</a></li>
					<li><a href="#">• 女鞋</a></li>
					<li><a href="#">• 面部护理</a></li>
					<li><a href="#">• 身体护理</a></li>
					<li><a href="#">• 魅力彩妆</a></li>
					<li><a href="#">• 香水</a></li>
					<li><a href="#">• 女装</a></li>
					<li><a href="#">• 女鞋</a></li>
					<li><a href="#">• 面部护理</a></li>
					<li><a href="#">• 身体护理</a></li>
					<li><a href="#">• 魅力彩妆</a></li>
					<li><a href="#">• 香水</a></li>
				</ul>
			</div>
			<div class="lptable">
				<table>
					<tr>
						<td>
							<div class="ppicdiv">
								<a href="#"><img src="../images/ss1.jpg" /></a>
							</div>
							<p>
								G4 i5 14寸笔记本<br />商城价：<span>￥3629.00</span>
							</p>
						</td>
						<td>
							<div class="ppicdiv">
								<a href="#"><img src="../images/ss2.jpg" /></a>
							</div>
							<p>
								G4 i5 14寸笔记本<br />商城价：<span>￥3629.00</span>
							</p>
						</td>
						<td>
							<div class="ppicdiv">
								<a href="#"><img src="../images/ss2.jpg" /></a>
							</div>
							<p>
								G4 i5 14寸笔记本<br />商城价：<span>￥3629.00</span>
							</p>
						</td>
						<td>
							<div class="ppicdiv">
								<a href="#"><img src="../images/ss1.jpg" /></a>
							</div>
							<p>
								G4 i5 14寸笔记本<br />商城价：<span>￥3629.00</span>
							</p>
						</td>
					</tr>
					<tr>
						<td>
							<div class="ppicdiv">
								<a href="#"><img src="../images/ss2.jpg" /></a>
							</div>
							<p>
								G4 i5 14寸笔记本<br />商城价：<span>￥3629.00</span>
							</p>
						</td>
						<td>
							<div class="ppicdiv">
								<a href="#"><img src="../images/ss1.jpg" /></a>
							</div>
							<p>
								G4 i5 14寸笔记本<br />商城价：<span>￥3629.00</span>
							</p>
						</td>
						<td>
							<div class="ppicdiv">
								<a href="#"><img src="../images/ss1.jpg" /></a>
							</div>
							<p>
								G4 i5 14寸笔记本<br />商城价：<span>￥3629.00</span>
							</p>
						</td>
						<td>
							<div class="ppicdiv">
								<a href="#"><img src="../images/ss2.jpg" /></a>
							</div>
							<p>
								G4 i5 14寸笔记本<br />商城价：<span>￥3629.00</span>
							</p>
						</td>
					</tr>
				</table>
			</div>
			<div class="clear"></div>
		</div>




	</div>
	<!----左边end---->

	<div class="clear"></div>
</div>
<!----red两栏end---->



<!----advtisementend---->


<div class="w banner1">
	<img src="../images/index_adv3.jpg" />
</div>

<!----green两栏---->
<div class="w">
	<!----右边---->
	<div class="index_right">
		<div class="side2">
			<h3>
				<span><a href="#">更多品牌 &gt;</a></span>品牌旗舰店
			</h3>
			<div class="banddiv">
				<table height="119" border="1">
					<tr>
						<td><a href="#"><img src="../images/band1.jpg" /></a></td>
						<td><a href="#"><img src="../images/band2.jpg" /></a></td>
						<td><a href="#"><img src="../images/band3.jpg" /></a></td>
					</tr>
					<tr>
						<td><a href="#"><img src="../images/band4.jpg" /></a></td>
						<td><a href="#"><img src="../images/band5.jpg" /></a></td>
						<td><a href="#"><img src="../images/band6.jpg" /></a></td>
					</tr>
					<tr>
						<td><a href="#"><img src="../images/band7.jpg" /></a></td>
						<td><a href="#"><img src="../images/band8.jpg" /></a></td>
						<td><a href="#"><img src="../images/band9.jpg" /></a></td>
					</tr>
				</table>
			</div>
		</div>

		<div style="margin-top: 18px;">
			<img src="../images/radv1.jpg" />
		</div>
		<div style="margin-top: 18px;">
			<img src="../images/radv2.jpg" />
		</div>
	</div>
	<!----右边end---->

	<!----左边---->
	<div class="index_left">
		<div class="indexlp greenback">
			<h3>电脑数码</h3>
			<ul>
				<li class="focus"><a href="#">特价商品</a></li>
				<li><a href="#">笔记本</a></li>
				<li><a href="#">影音数码</a></li>
				<li><a href="#">DIY攒机</a></li>
				<li><a href="#">办公打印</a></li>
			</ul>
		</div>
		<div class="indexlpdiv">
			<div class="lpleft spback">
				<ul>
					<li><a href="#">• 进口食品</a></li>
					<li><a href="#">• 休闲食品</a></li>
					<li><a href="#">• 面部护理</a></li>
					<li><a href="#">• 身体护理</a></li>
					<li><a href="#">• 魅力彩妆</a></li>
					<li><a href="#">• 香水</a></li>
					<li><a href="#">• 美体养颜</a></li>
					<li><a href="#">• 进口食品</a></li>
					<li><a href="#">• 休闲食品</a></li>
					<li><a href="#">• 面部护理</a></li>
					<li><a href="#">• 身体护理</a></li>
					<li><a href="#">• 魅力彩妆</a></li>
					<li><a href="#">• 香水</a></li>
					<li><a href="#">• 美体养颜</a></li>
				</ul>
			</div>
			<div class="lptable">
				<table>
					<tr>
						<td>
							<div class="ppicdiv">
								<a href="#"><img src="../images/sp1.jpg" /></a>
							</div>
							<p>
								G4 i5 14寸笔记本<br />商城价：<span>￥3629.00</span>
							</p>
						</td>
						<td>
							<div class="ppicdiv">
								<a href="#"><img src="../images/sp2.jpg" /></a>
							</div>
							<p>
								G4 i5 14寸笔记本<br />商城价：<span>￥3629.00</span>
							</p>
						</td>
						<td>
							<div class="ppicdiv">
								<a href="#"><img src="../images/sp2.jpg" /></a>
							</div>
							<p>
								G4 i5 14寸笔记本<br />商城价：<span>￥3629.00</span>
							</p>
						</td>
						<td>
							<div class="ppicdiv">
								<a href="#"><img src="../images/sp1.jpg" /></a>
							</div>
							<p>
								G4 i5 14寸笔记本<br />商城价：<span>￥3629.00</span>
							</p>
						</td>
					</tr>
					<tr>
						<td>
							<div class="ppicdiv">
								<a href="#"><img src="../images/sp2.jpg" /></a>
							</div>
							<p>
								G4 i5 14寸笔记本<br />商城价：<span>￥3629.00</span>
							</p>
						</td>
						<td>
							<div class="ppicdiv">
								<a href="#"><img src="../images/sp1.jpg" /></a>
							</div>
							<p>
								G4 i5 14寸笔记本<br />商城价：<span>￥3629.00</span>
							</p>
						</td>
						<td>
							<div class="ppicdiv">
								<a href="#"><img src="../images/sp1.jpg" /></a>
							</div>
							<p>
								G4 i5 14寸笔记本<br />商城价：<span>￥3629.00</span>
							</p>
						</td>
						<td>
							<div class="ppicdiv">
								<a href="#"><img src="../images/sp2.jpg" /></a>
							</div>
							<p>
								G4 i5 14寸笔记本<br />商城价：<span>￥3629.00</span>
							</p>
						</td>
					</tr>
				</table>
			</div>
			<div class="clear"></div>
		</div>




	</div>
	<!----左边end---->

	<div class="clear"></div>
</div>
<!----green两栏end---->




<!----down两栏---->
<div class="w">
	<!----右边---->
	<div class="index_right">
		<div class="side2">
			<h3>时尚资讯</h3>
			<ul class="lista font12 blue_a listimg1" style="margin-left: 10px;">
				<li><a href="#">品牌女包季末清仓69元起</a></li>
				<li><a href="#">我比美食更有诱惑力！---韩国明进工坊</a></li>
				<li><a href="#">樱春季 忙护理 资生堂全场满196赠！</a></li>
				<li><a href="#">IDF买就送精美丝巾</a></li>
				<li><a href="#">三月迎春，最爱就“饰”你！</a></li>
				<li><a href="#">三月迎春，最爱就“饰”你！</a></li>
				<li><a href="#">三月迎春，最爱就“饰”你！</a></li>
			</ul>
		</div>
	</div>
	<!----右边end---->

	<!----左边---->
	<div class="index_left">
		<div class="hotevent">
			<div class="eventbox">
				<h3>热门晒单</h3>
				<div class="eventlist">
					<ul>
						<li style="height: 60px; display: list-item;">
							<div class="p-img">
								<a href="#"><img width="50" height="50"
									src="http://img10.360buyimg.com/N5/3015/b0724ba8-7f85-4f3a-bb61-3c2bc7a8e341.jpg"><b
									class="ci cix1"></b></a>
							</div>
							<div class="p-name">
								<a href="#">质量可靠，系统稳定，值得拥有</a>
							</div>
							<div class="p-detail">
								<a href="#">dell+百度确实是个好的组，便于上手。大屏耗电是软肋，节约情况下2至3天待机应该无悬念，具体见我的评价。</a>
							</div>
						</li>
						<li style="height: 60px; display: list-item;">
							<div class="p-img">
								<a href="#"><img width="50" height="50"
									src="http://img10.360buyimg.com/N5/3015/b0724ba8-7f85-4f3a-bb61-3c2bc7a8e341.jpg"><b
									class="ci cix1"></b></a>
							</div>
							<div class="p-name">
								<a href="#">质量可靠，系统稳定，值得拥有</a>
							</div>
							<div class="p-detail">
								<a href="#">dell+百度确实是个好的组，便于上手。大屏耗电是软肋，节约情况下2至3天待机应该无悬念，具体见我的评价。</a>
							</div>
						</li>
					</ul>
				</div>
			</div>
			<div class="eventbox">
				<h3>热门活动</h3>
				<div class="eventlist">
					<ul style="border: 0;">
						<li style="height: 60px; display: list-item;">
							<div class="p-img">
								<a href="#"><img width="50" height="50"
									src="http://img10.360buyimg.com/N5/3015/b0724ba8-7f85-4f3a-bb61-3c2bc7a8e341.jpg"><b
									class="ci cix2"></b></a>
							</div>
							<div class="p-name">
								<a href="#">质量可靠，系统稳定，值得拥有</a>
							</div>
							<div class="p-detail">
								<a href="#">dell+百度确实是个好的组，便于上手。大屏耗电是软肋，节约情况下2至3天待机应该无悬念，具体见我的评价。</a>
							</div>
						</li>
						<li style="height: 60px; display: list-item;">
							<div class="p-img">
								<a href="#"><img width="50" height="50"
									src="http://img10.360buyimg.com/N5/3015/b0724ba8-7f85-4f3a-bb61-3c2bc7a8e341.jpg"><b
									class="ci cix2"></b></a>
							</div>
							<div class="p-name">
								<a href="#">质量可靠，系统稳定，值得拥有</a>
							</div>
							<div class="p-detail">
								<a href="#">dell+百度确实是个好的组，便于上手。大屏耗电是软肋，节约情况下2至3天待机应该无悬念，具体见我的评价。</a>
							</div>
						</li>
					</ul>
				</div>
			</div>

			<div class="clear"></div>
		</div>

	</div>
	<!----左边end---->
	<div class="clear"></div>
</div>
<script type="text/javascript"
	src="${contextPath}/common/js/jquery1.6.js"></script>
<script type="text/javascript">
<!--
	var c2c = {
		//Display the sort navigation
		showCategory : function() {
			$(".mc").css("display", "block");
			$(".subitem").css("display", "none");

		}
	};

	$(document).ready(function() {
		c2c.showCategory();
	});
//-->
</script>
<!----down两栏end---->
