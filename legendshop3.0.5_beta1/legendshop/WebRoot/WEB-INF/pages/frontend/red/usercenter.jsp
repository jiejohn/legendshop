<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<!----地址---->
<div class="w addr">
	<span><a href="#">首页</a></span>&gt;<span><a href="#">电脑办公</a></span>&gt;<span><a
		href="#">电脑整机</a></span>&gt;<span>笔记本</span>
</div>
<!----地址end---->


<!----两栏---->
<div class="w" style="padding-top: 10px;">
	<!-----------leftm-------------->
	<c:import url="usercenter_left.jsp" />
	<!-----------leftm end-------------->

	<!-----------right_con-------------->
	<div class="right_con">

		<!--user info-->
		<div id="userinfo" class="m3">
			<div class="user">
				<div class="u-icon">
					<img alt="用户头像" src="../images/no-img_mid_.jpg">
				</div>
				<div class="extra">
					<a href="#">修改头像</a>
				</div>
			</div>
			<!--user-->
			<div id="i-userinfo">
				<div class="username">
					<div id="userinfodisp" class="fl ftx-03">
						<strong>gln13571357</strong>,欢迎您！上一次登录时间：2012-04-15 12:14:51
					</div>
					<div id="jdTask" class="extra"></div>
				</div>

				<div class="account">
					<div class="member" id="memberInfo">
						<div class="rank r3">
							<s></s><a href="#" target="_blank">铜牌用户</a>
						</div>
						<div class="memb-info">
							今年已消费￥4569，再消费￥431可升级到<a href="#" target="_blank">银牌用户</a>
						</div>
					</div>

				</div>
				<div class="acco-safe">
					<span class="fl">账户安全：</span> <a class="teln" href="#"><s></s>手机未验证</a>
					<a href="#" class="mail"><s></s>邮箱已验证</a>
				</div>


				<!--end-->
				<div id="remind">
					<div class="oinfo">
						<dl class="fore">

							<dt>订单提醒：</dt>
							<dd>
								<span id="orderCount"><a href="#" class="flk-03">待处理订单(0)</a></span>
							</dd>
							<dd>
								<a href="#" id="reviewCountHref">待评价商品(<span
									id="reviewCount">1</span>)
								</a>
							</dd>
							<dd>
								<a href="#" id="sdCountHref">待晒单商品(<span id="sdCount">1</span>)
								</a>
							</dd>
						</dl>
						<dl>

							<dt>我的关注：</dt>
							<dd>
								<a href="#" id="priceReductionHref" class="flk-03">降价商品(<span
									id="priceReduction">0</span>)
								</a>
							</dd>
							<dd>
								<a href="#" id="promotionHref" class="flk-03">促销商品<span>(<span
										id="promotion">0</span>)
								</span></a>
							</dd>
							<dd>
								<a href="#" id="arriveStockHref" class="flk-03">新到货商品(<span
									id="arriveStock">0</span>)
								</a>
							</dd>
						</dl>
						<dl>

							<dt>消息精灵：</dt>
							<dd>
								<a href="#" id="messageCountHref" class="flk-03">提醒/通知：(<span
									id="messageCount">0</span>)
								</a>
							</dd>
							<dd>
								<a href="#" id="notificationCountHref" class="flk-03">已回复的咨询(<span
									id="notificationCount">0</span>)
								</a>
							</dd>
						</dl>
					</div>
					<div class="ainfo">
						<dl class="fore">

							<dt>账户余额：</dt>
							<dd>
								<a class="flk-01" href="#"><span id="divBalance"><strong
										class="ftx-03">￥0.00</strong></span></a>
							</dd>
						</dl>
						<dl>
							<dt>我的积分：</dt>
							<dd>
								<a class="flk-03" href="#"><span id="divContent"
									class="ftx-04">0</span>分</a>
							</dd>
						</dl>
						<dl>
							<dt>优惠券：</dt>
							<dd class="fore1">
								<a href="#" id="CouponCountHref" class="flk-03"><span
									class="ftx-04" id="CouponCount">0</span>张</a>
							</dd>
							<dt>礼品卡：</dt>
							<dd>
								<a href="#" id="LipinkaCountHref" class="flk-03"><span
									class="ftx-03" id="LipinkaCount">0</span>张</a>
							</dd>

						</dl>
					</div>
				</div>
			</div>
		</div>
		<!--user info end-->


		<!-------------订单---------------->
		<div id="recommend" class="m10 recommend" style="display: block;">
			<div class="pagetab m10">
				<ul>
					<li class="on"><span>近一个月订单</span></li>
					<li><span>待评价商品</span></li>
				</ul>
			</div>

			<table width="100%" cellspacing="0" cellpadding="0" class="buytable">
				<tbody>
					<tr>
						<th width="12%">订单编号</th>
						<th width="28%">订单商品</th>
						<th width="10%">收货人</th>
						<th width="12%">订单金额</th>
						<th width="12%">下单时间</th>
						<th width="12%">订单状态</th>
						<th width="14%">操作</th>
					</tr>

					<tr>
						<td><a href="#" target="_blank">158861817</a></td>
						<td><div class="img-list">
								<a target="_blank" href="#"><img
									title="TP-LINK TL-WR841N  300M无线路由器（蓝色）"
									src="../images/a173ba1c-84b2-4270-a3ee-0ddaf4b95484.jpg"></a>
							</div></td>
						<td>lf</td>
						<td>￥258.00<br>货到付款
						</td>
						<td><span class="ftx-03">2012-02-23 <br /> 11:10:00
						</span></td>
						<td><span class="ftx-03">已完成</span></td>
						<td class="order-doi"><a href="#" target="_blank">查看</a>|<a
							target="_blank" href="#">晒单</a>|<a target="_blank" href="#">评价</a><a
							target="_blank" href="#" class="btn-again">还要买</a></td>
					</tr>
					<tr>
						<td><a href="#" target="_blank">158861817</a></td>
						<td><div class="img-list">
								<a target="_blank" href="#"><img
									title="TP-LINK TL-WR841N  300M无线路由器（蓝色）"
									src="../images/a173ba1c-84b2-4270-a3ee-0ddaf4b95484.jpg"></a>
							</div></td>
						<td>lf</td>
						<td>￥258.00<br>货到付款
						</td>
						<td><span class="ftx-03">2012-02-23 <br /> 11:10:00
						</span></td>
						<td><span class="ftx-03">已完成</span></td>
						<td class="order-doi"><a href="#" target="_blank">查看</a>|<a
							target="_blank" href="#">晒单</a>|<a target="_blank" href="#">评价</a><a
							target="_blank" href="#" class="btn-again">还要买</a></td>
					</tr>

				</tbody>
			</table>


		</div>
		<!-------------订单end---------------->

		<div id="recommend" class="m10 recommend" style="display: block;">
			<div class="pagetab m10">
				<ul>
					<li class="on"><span>猜你喜欢</span></li>
					<li><span>推荐服务</span></li>
				</ul>
			</div>

			<div id="group" class="tabcon">

				<div class="suits" style="width: 100%;">
					<ul class="list">
						<li>
							<div class="p-img">
								<a target="_blank" href="#"><img width="100" height="100"
									src="../images/sample100.jpg"></a>
							</div>
							<div class="p-name">
								<a target="_blank" href="#">现代（HYUNDAI）HY-N11 2.4G无线鼠标</a>
							</div>
							<div class="choose">
								<span class="p-price"><strong>￥39.00</strong></span>
							</div>
						</li>
						<li>
							<div class="p-img">
								<a target="_blank" href="#"><img width="100" height="100"
									src="../images/sample100.jpg"></a>
							</div>
							<div class="p-name">
								<a target="_blank" href="#">现代（HYUNDAI）HY-N11 2.4G无线鼠标</a>
							</div>
							<div class="choose">
								<span class="p-price"><strong>￥39.00</strong></span>
							</div>
						</li>
						<li>
							<div class="p-img">
								<a target="_blank" href="#"><img width="100" height="100"
									src="../images/sample100.jpg"></a>
							</div>
							<div class="p-name">
								<a target="_blank" href="#">现代（HYUNDAI）HY-N11 2.4G无线鼠标</a>
							</div>
							<div class="choose">
								<span class="p-price"><strong>￥39.00</strong></span>
							</div>
						</li>
						<li>
							<div class="p-img">
								<a target="_blank" href="#"><img width="100" height="100"
									src="../images/sample100.jpg"></a>
							</div>
							<div class="p-name">
								<a target="_blank" href="#">现代（HYUNDAI）HY-N11 2.4G无线鼠标</a>
							</div>
							<div class="choose">
								<span class="p-price"><strong>￥39.00</strong></span>
							</div>
						</li>
						<li>
							<div class="p-img">
								<a target="_blank" href="#"><img width="100" height="100"
									src="../images/sample100.jpg"></a>
							</div>
							<div class="p-name">
								<a target="_blank" href="#">现代（HYUNDAI）HY-N11 2.4G无线鼠标</a>
							</div>
							<div class="choose">
								<span class="p-price"><strong>￥39.00</strong></span>
							</div>
						</li>
						<li>
							<div class="p-img">
								<a target="_blank" href="#"><img width="100" height="100"
									src="../images/sample100.jpg"></a>
							</div>
							<div class="p-name">
								<a target="_blank" href="#">现代（HYUNDAI）HY-N11 2.4G无线鼠标</a>
							</div>
							<div class="choose">
								<span class="p-price"><strong>￥39.00</strong></span>
							</div>
						</li>
					</ul>
				</div>

				<div class="clear"></div>
			</div>
			<!--group end-->
		</div>
		<div class="m10">
			<img src="../images/usadd.jpg" width="984" height="118" />
		</div>

	</div>
	<!-----------right_con end-------------->

	<div class="clear"></div>
</div>
<!----两栏end---->
