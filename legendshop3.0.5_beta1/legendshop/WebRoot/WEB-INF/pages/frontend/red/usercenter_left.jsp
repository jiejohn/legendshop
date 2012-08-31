<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<div class="leftm">
	<div class="uc_minb">
		<div class="dback">
			<h3>
				<span><lb:currentUser /></span>，欢迎您！
			</h3>
			<p>会员等级：普通会员</p>
		</div>
	</div>
	<div class="uctit m10">
		<h3 class="focus">
		
			<a href="<ls:url address='/p/usercenter'/>">个人中心首页</a>
		</h3>
		<h3>
			<a href="<ls:url address='/p/order'/>">我的订单</a>
		</h3>
		<h3>
			<a href="<ls:url address='/p/buy'/>">我的购物车</a>
		</h3>
		<h3>
			<a href="<ls:url address='/p/favourite'/>">我的收藏</a>
		</h3>
		<h3>
			<a href="<ls:url address='/p/myaccount'/>">个人信息</a>
		</h3>
		<h3 class="open last">
			我的消息
			<ul>
				<li><a href="<ls:url address='/p/outbox'/>">发件箱</a></li>
				<li><a href="<ls:url address='/p/inbox'/>">收件箱</a></li>
			</ul>
		</h3>
	</div>

</div>