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

		<div class="o-mt">
			<h2>订单中心</h2>
		</div>
		<div class="form search-01">
			<div class="item">
				<div class="fl fore">
					<select name="" class="sele" id="ordertype">
						<option value="1">近一个月订单</option>
						<option value="2">一个月前订单</option>
					</select> <select name="" class="sele" id="orderstateselect">
						<option value="1">订单状态</option>
						<option value="2">已取消订单</option>
					</select>
				</div>
				<div class="fr">
					<input type="text" value="商品名称、商品编号、订单编号" class="text" name=""
						id="ip_keyword"> <input type="button" class="bti"
						value="查 询" name="">
				</div>
				<div class="clear"></div>
			</div>
		</div>

		<!--user info-->
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
						<td>￥258.00<br>货到付款</td>
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
						<td>￥258.00<br>货到付款</td>
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