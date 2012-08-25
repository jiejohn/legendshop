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

	<!-----------right_con-------------->
	<div class="right_cartcon">
		<div class="cart_step">
			<ul>
				<li class="focus"><a href="#">1、我的购物车</a></li>
				<li><a href="#">2、填写核对订单信息</a></li>
				<li style="background: none;"><a href="#">3、成功提交订单</a></li>
			</ul>
		</div>

		<!-------------订单---------------->
		<img src="../images/cart.jpg" width="160" height="51" />
		<div id="recommend" class="m10 recommend" style="display: block;">

			<table width="100%" cellspacing="0" cellpadding="0" class="buytable">
				<tbody>
					<tr>
						<th width="65">商品编号</th>
						<th>商品信息</th>
						<th width="120">价格</th>
						<th width="100">赠送积分</th>
						<th width="120">商品数量</th>
						<th width="100">删除商品</th>
					</tr>

					<tr>
						<td>608244</td>
						<td>
							<div class="img-list">
								<a target="_blank" href="#"><img
									title="TP-LINK TL-WR841N  300M无线路由器（蓝色）"
									src="../images/a173ba1c-84b2-4270-a3ee-0ddaf4b95484.jpg"></a>
							</div>
							<div class="record_text">
								<a href="#">富士（FUJIFILM） FinePix T205 数码相机（黑色）</a><br />
							</div>
						</td>
						<td>￥258.00</td>
						<td>0</td>
						<td><div class="cartnum">
								<a href="javascript:void(0)" class="reduce">-</a><input
									type="text" id="pamount" value="1"><a
									href="javascript:void(0)" class="add">+</a>
							</div></td>
						<td><a href="#" target="_blank">删除</a></td>
					</tr>
					<tr>
						<td>608244</td>
						<td>
							<div class="img-list">
								<a target="_blank" href="#"><img
									title="TP-LINK TL-WR841N  300M无线路由器（蓝色）"
									src="../images/a173ba1c-84b2-4270-a3ee-0ddaf4b95484.jpg"></a>
							</div>
							<div class="record_text">
								<a href="#">富士（FUJIFILM） FinePix T205 数码相机（黑色）</a><br />
							</div>
						</td>
						<td>￥258.00</td>
						<td>0</td>
						<td><div class="cartnum">
								<a href="javascript:void(0)" class="reduce">-</a><input
									type="text" id="pamount" value="1"><a
									href="javascript:void(0)" class="add">+</a>
							</div></td>
						<td><a href="#" target="_blank">删除</a></td>
					</tr>
					<tr>
						<td>608244</td>
						<td>
							<div class="img-list">
								<a target="_blank" href="#"><img
									title="TP-LINK TL-WR841N  300M无线路由器（蓝色）"
									src="../images/a173ba1c-84b2-4270-a3ee-0ddaf4b95484.jpg"></a>
							</div>
							<div class="record_text">
								<a href="#">富士（FUJIFILM） FinePix T205 数码相机（黑色）</a><br />
							</div>
						</td>
						<td>￥258.00</td>
						<td>0</td>
						<td><div class="cartnum">
								<a href="javascript:void(0)" class="reduce">-</a><input
									type="text" id="pamount" value="1"><a
									href="javascript:void(0)" class="add">+</a>
							</div></td>
						<td><a href="#" target="_blank">删除</a></td>
					</tr>
					<tr>
						<td>608244</td>
						<td>
							<div class="img-list">
								<a target="_blank" href="#"><img
									title="TP-LINK TL-WR841N  300M无线路由器（蓝色）"
									src="../images/a173ba1c-84b2-4270-a3ee-0ddaf4b95484.jpg"></a>
							</div>
							<div class="record_text">
								<a href="#">富士（FUJIFILM） FinePix T205 数码相机（黑色）</a><br />
							</div>
						</td>
						<td>￥258.00</td>
						<td>0</td>
						<td><div class="cartnum">
								<a href="javascript:void(0)" class="reduce">-</a><input
									type="text" id="pamount" value="1"><a
									href="javascript:void(0)" class="add">+</a>
							</div></td>
						<td><a href="#" target="_blank">删除</a></td>
					</tr>
					<tr>
						<td style="height: 30px" colspan="6" class="Tfoot">重量总计：0.00kg&nbsp;&nbsp;&nbsp;原始金额：￥0.00元
							- 返现：￥0.00元<br>
						<span style="font-size: 14px"><b>商品总金额：<span
									id="cartBottom_price" class="price">￥0.00</span>元
							</b></span>
						</td>
					</tr>




				</tbody>
			</table>

			<div class="cart_op">
				<a href="#"><img src="../images/to_g.jpg" width="173"
					height="45" /></a> <a href="#"><img src="../images/to_buy.jpg"
					width="173" height="45" /></a>
			</div>


		</div>
		<!-------------订单end---------------->




		<!----填写核对订单信息---->
		<div class="List_cart marginb10" style="margin-left: 150px;">
			<h2>
				<strong>填写核对订单信息</strong>
			</h2>
			<div class="cart_table">

				<!--收货人地址开始-->
				<div id="part_consignee">
					<div class="o_show">

						<h1>
							收货人信息&nbsp;<a onclick="showForm_consignee(this)"
								clstag="checkout|keycount|jiesuan|lconsigneewrite"
								href="javascript:void(0)" id="lbtnConsigneeWrite">[修改]</a>
						</h1>

						<div class="middle">

							<table>
								<tbody>
									<tr>
										<td align="right" style="width: 80px;">收货人姓名：</td>
										<td>lg</td>
									</tr>

									<tr>
										<td align="right">省&#12288;&#12288;份：</td>
										<td>广东广州市天河区</td>
									</tr>

									<tr>
										<td align="right">地&#12288;&#12288;址：</td>
										<td>广东广州市天河区</td>
									</tr>

									<tr>
										<td align="right">手机号码：</td>
										<td>13323424345</td>
									</tr>

									<tr>
										<td align="right">固定电话：</td>
										<td>13323424345</td>
									</tr>

									<tr>
										<td align="right">电子邮件：</td>
										<td>lglereei@163.com</td>
									</tr>

									<tr>
										<td align="right">邮&#12288;&#12288;编：</td>
										<td>510630</td>
									</tr>

								</tbody>
							</table>


						</div>

					</div>

				</div>


				<!--编辑收货人地址-->
				<div class="o_write">

					<h1>
						收货人信息&nbsp;<a onclick="close_consignee(this)"
							href="javascript:void(0)" id="lbtnConsigneeWrite">[关闭]</a> <span
							style="display: none"><a
							style="color: #FF6600; display: {addAddr_Show"
							onclick="addNewAddress(this);" href="javascript:void(0)">[创建一个新地址]</a></span>

					</h1>

					<div class="middle">

						<div id="addressListPanel">
							<div class="cydz">
								<b>常用地址</b>
								<ul>
									<li id="addrIi3265226"><table cellspacing="0"
											cellpadding="0" border="0" style="width: 98%">
											<tbody>
												<tr>
													<td style="width: 20px;"><input type="radio"
														onclick="changeConsignee(this,'3265226');" name="rbtnAddr"
														addresstype="1" id="addr_3265226"></td>
													<td><label for="addr_3265226"><strong>dfdfd</strong>&nbsp;广东广州市海珠区(江海大道以西;南洲路</label></td>
													<td style="width: 40px"><a
														onclick="DelAddress(this,'3265226');" class="remark"
														href="#none">[删除]</a></td>
												</tr>
											</tbody>
										</table></li>
									<li id="addrIi3265261"><table cellspacing="0"
											cellpadding="0" border="0" style="width: 98%">
											<tbody>
												<tr>
													<td style="width: 20px;"><input type="radio"
														onclick="changeConsignee(this,'3265261');" name="rbtnAddr"
														addresstype="1" id="addr_3265261"></td>
													<td><label for="addr_3265261"><strong>fdfdg</strong>&nbsp;广东广州市天河区(环城高速东线以西;北线以南)中山大道8</label></td>
													<td style="width: 40px"><a
														onclick="DelAddress(this,'3265261');" class="remark"
														href="#none">[删除]</a></td>
												</tr>
											</tbody>
										</table></li>
									<li id="addrIi7689310"><table cellspacing="0"
											cellpadding="0" border="0" style="width: 98%">
											<tbody>
												<tr>
													<td style="width: 20px;"><input type="radio"
														onclick="changeConsignee(this,'7689310');" name="rbtnAddr"
														addresstype="1" id="addr_7689310"></td>
													<td><label for="addr_7689310"><strong>drer</strong>&nbsp;广东广州市天河区(广汕公路以南)广州市天河区中山大道89号天河</label></td>
													<td style="width: 40px"><a
														onclick="DelAddress(this,'7689310');" class="remark"
														href="#none">[删除]</a></td>
												</tr>
											</tbody>
										</table></li>
								</ul>
							</div>
						</div>

						<div id="consignee_addr">

							<table width="100%" cellspacing="0" border="0">

								<tbody>
									<tr>
										<td align="right" width="85" valign="middle"><font
											color="red">*</font>收货人姓名：</td>
										<td align="left" valign="middle"><input type="text"
											onblur="check_addressName()" value="刘飞" maxlength="20"
											class="txt" id="consignee_addressName">&nbsp;</td>
									</tr>

									<tr>
										<td align="right" valign="middle"><font color="red">*</font>省&#12288;&#12288;份：</td>
										<td align="left" valign="middle"><span
											id="consignee_arae"><select
												onchange="changeProvince(this)" id="consignee_province"><option
														value="-22">请选择</option>
													<option value="1">北京</option>
													<option value="2">上海</option>
													<option value="3">天津</option>
													<option value="4">重庆</option>
													<option value="5">河北</option>
													<option value="6">山西</option>
													<option value="7">河南</option>
													<option value="8">辽宁</option>
													<option value="9">吉林</option>
													<option value="10">黑龙江</option>
													<option value="11">内蒙古</option>
													<option value="12">江苏</option>
													<option value="13">山东</option>
													<option value="14">安徽</option>
													<option value="15">浙江</option>
													<option value="16">福建</option>
													<option value="17">湖北</option>
													<option value="18">湖南</option>
													<option selected="" value="19">广东</option>
													<option value="20">广西</option>
													<option value="21">江西</option>
													<option value="22">四川</option>
													<option value="23">海南</option>
													<option value="24">贵州</option>
													<option value="25">云南</option>
													<option value="26">西藏</option>
													<option value="27">陕西</option>
													<option value="28">甘肃</option>
													<option value="29">青海</option>
													<option value="30">宁夏</option>
													<option value="31">新疆</option>
													<option value="32">台湾</option>
													<option value="42">香港</option>
													<option value="43">澳门</option>
													<option value="84">钓鱼岛</option></select><select
												onchange="changeCity(this)" id="consignee_city"><option
														value="-22">请选择</option>
													<option selected="" value="1601">广州市*</option>
													<option value="1607">深圳市*</option>
													<option value="1609">珠海市*</option>
													<option value="1611">汕头市*</option>
													<option value="1617">韶关市*</option>
													<option value="1627">河源市*</option>
													<option value="1634">梅州市*</option>
													<option value="1643">惠州市*</option>
													<option value="1650">汕尾市*</option>
													<option value="1655">东莞市*</option>
													<option value="1657">中山市*</option>
													<option value="1659">江门市*</option>
													<option value="1666">佛山市*</option>
													<option value="1672">阳江市*</option>
													<option value="1677">湛江市*</option>
													<option value="1684">茂名市*</option>
													<option value="1690">肇庆市*</option>
													<option value="1698">云浮市*</option>
													<option value="1704">清远市*</option>
													<option value="1705">潮州市*</option>
													<option value="1709">揭阳市*</option></select><select
												onchange="check_con_area();changeCounty(this)"
												id="consignee_county"><option value="-22">请选择</option>
													<option value="3631">白云区*</option>
													<option selected="" value="3633">天河区*</option>
													<option value="3634">海珠区*</option>
													<option value="3635">荔湾区*</option>
													<option value="3637">越秀区*</option>
													<option value="4761">黄埔区*</option>
													<option value="4778">增城市(荔城、增江、新塘镇)*</option>
													<option value="4823">花都区(新华,雅瑶,狮岭,炭步,花山,花东镇)*</option>
													<option value="4880">萝岗区(夏港、东区、西区、联和、萝岗街道)*</option>
													<option value="6116">南沙区(南沙街道、黄阁)*</option>
													<option value="1603">番禺区(除榄核、大岗、万顷沙外)*</option>
													<option value="4745">番禺区(榄核、大岗、万顷沙)*</option>
													<option value="1602">花都区(赤坭、梯面镇、白云国际机场)*</option>
													<option value="4242">增城市(朱村、正果、石滩、中新、派潭、小楼镇)*</option>
													<option value="4040">南沙区(除南沙街道、黄阁外)*</option>
													<option value="4104">萝岗区(九佛、镇龙、九龙、永和街道)*</option>
													<option value="5481">从化市(街口、城郊、江埔、太平、神岗)*</option>
													<option value="1604">从化市(除街口、城郊、江埔、太平、神岗外)*</option></select></span>&nbsp;&nbsp;注:标“*”的为支持货到付款的地区，<a
											class="link_005AA0" target="_blank"
											clstag="checkout|keycount|jiesuan|link005aa0"
											href="http://help.360buy.com/help/question-67.html">查看货到付款地区</a>
										</td>
									</tr>

									<tr>
										<td align="right" valign="middle"><font color="red">*</font>地&#12288;&#12288;址：</td>
										<td align="left" valign="middle"><span
											id="consigneeShow_addressName">广东广州市天河区</span> <input
											type="text" onblur="check_address()"
											value="(广汕公路以南)广州市天河区中山大道89号天河软件园华景园区B栋3楼"
											style="margin-left: 2px; width: 327px" maxlength="50"
											class="txt" id="consignee_address">&nbsp;</td>
									</tr>

									<tr>
										<td align="right" valign="middle"><font color="red">*</font>手机号码：</td>
										<td align="left" valign="middle"><input type="text"
											onblur="check_message()" value="13719162172" class="txt"
											id="consignee_message"> &nbsp;或者 固定电话： <input
											type="text" onblur="check_phone()" value="13719162172"
											class="txt" id="consignee_phone">&nbsp;<font
											color="#000000">用于接收发货通知短信及送货前确认</font></td>
									</tr>


									<tr>
										<td align="right" valign="middle">电子邮件：</td>
										<td align="left" valign="middle"><input type="text"
											onblur="check_email()" value="lgliufei@163.com" class="txt"
											id="consignee_email"> &nbsp;<font color="#000000">用来接收订单提醒邮件，便于您及时了解订单状态</font>

										</td>
									</tr>


									<tr>
										<td align="right" valign="middle">邮政编码：</td>
										<td align="left" valign="middle"><input type="text"
											onblur="check_postcode()" value="510630" style="width: 77px"
											class="txt" id="consignee_postcode">&nbsp;<font
											color="#000000" style="margin-left: 53px">有助于快速确定送货地址</font>

										</td>
									</tr>





								</tbody>
							</table>

						</div>

					</div>
					<div style="padding: 10px 0 20px 45px;">
						<a onclick="addNewAddress(this);" href="#none"
							style="color: #185D94">[添加到常用地址]</a>
					</div>

					<div class="footer">
						<input type="button" onclick="savePart_consignee(this)"
							value="保存收货人信息" clstag="checkout|keycount|jiesuan|saveshouhuo"
							class="btn">

					</div>

				</div>
				<!--编辑收货人地址-->


				<!--收货人地址结束-->

				<div style="display: none" class="o_show">
					<h1>配送方：京东商城</h1>
				</div>
				<!--支付方式及配送方式开始-->
				<div id="part_payTypeAndShipType">
					<div class="o_write">
						<div id="updateInfo_payType"></div>
						<h1>
							支付及配送方式&nbsp;<a onclick="close_payTypeAndShipType(this);"
								href="javascript:void(0)" id="linkPayTypeShipType">[关闭]</a>
						</h1>
						<div id="part_payType" class="middle">
							<table width="100%" cellspacing="0" cellpadding="0" border="0">
								<tbody>
									<tr>
										<td style="width: 240px"><div class="grouptitle">支付方式</div></td>
										<td style="border-bottom: #eee 1px solid;">备注</td>
									</tr>
								</tbody>
							</table>

							<table width="100%" cellspacing="0" cellpadding="0" border="0">
								<tbody>
									<tr>
										<td align="left" valign="top" style="width: 240px"><input
											type="radio" value="1" checked="" onclick="changePayType(1);"
											id="IdPaymentType1" name="IdPaymentType"><label
											for="IdPaymentType1" style="margin-left: 2px;">货到付款 </label>

										</td>
										<td valign="top" class="gray">送货上门后再收款，支持现金、POS机刷卡、支票支付 <a
											href="http://www.360buy.com/help/cod.aspx" target="_blank">查看运费及配送范围</a></td>
									</tr>
								</tbody>
							</table>

							<table width="100%" cellspacing="0" cellpadding="0" border="0">
								<tbody>
									<tr>
										<td align="left" valign="top" style="width: 240px"><input
											type="radio" value="4" onclick="changePayType(4);"
											id="IdPaymentType4" name="IdPaymentType"><label
											for="IdPaymentType4" style="margin-left: 2px;">在线支付 </label>

										</td>
										<td valign="top" class="gray">即时到帐，支持绝大数银行借记卡及部分银行信用卡 <a
											href="http://www.360buy.com/help/onlinepay.aspx"
											target="_blank">查看银行及限额</a></td>
									</tr>
								</tbody>
							</table>

							<div
								style="width: 600px; display: none; padding-left: 20px; padding-bottom: 10px; margin-left: 50px;"
								class="tsbox" id="payRemark_4">
								<iframe width="600px" scrolling="no" height="364px"
									frameborder="0"
									src="http://payment.360buy.com/bank/PayHtml/PurchasePayHtml.htm"
									id="frame_parent"> </iframe>
							</div>

							<table width="100%" cellspacing="0" cellpadding="0" border="0">
								<tbody>
									<tr>
										<td align="left" valign="top" style="width: 240px"><input
											type="radio" value="5" onclick="changePayType(5);"
											id="IdPaymentType5" name="IdPaymentType"><label
											for="IdPaymentType5" style="margin-left: 2px;">公司转账 </label>

										</td>
										<td valign="top" class="gray">通过快钱平台转账 转帐后1-3个工作日内到帐 <a
											href="http://help.360buy.com/help/question-70.html"
											target="_blank">查看帐户信息</a></td>
									</tr>
								</tbody>
							</table>

							<table width="100%" cellspacing="0" cellpadding="0" border="0">
								<tbody>
									<tr>
										<td align="left" valign="top" style="width: 240px"><input
											type="radio" value="2" onclick="changePayType(2);"
											id="IdPaymentType2" name="IdPaymentType"><label
											for="IdPaymentType2" style="margin-left: 2px;">邮局汇款 </label>

										</td>
										<td valign="top" class="gray">通过快钱平台收款 汇款后1-3个工作日到账 <a
											href="http://help.360buy.com/help/question-69.html"
											target="_blank">查看帮助</a></td>
									</tr>
								</tbody>
							</table>

						</div>

						<div style="margin-top: 8px; display: " id="part_shipType"
							class="middle">

							<table align="enter" width="100%" cellspacing="0" cellpadding="0"
								border="0" id="ShipMentTab">
								<tbody>
									<tr>
										<td align="left" width="130px" valign="top" height="24"
											style="border-bottom: #eee 1px solid;">
											<div class="grouptitle">配送方式</div>
										</td>
										<td align="left" width="155px" valign="top"
											style="border-bottom: #eee 1px solid;">运费</td>

										<td align="left" width="105px" valign="top"
											style="border-bottom: #eee 1px solid;">货物在途时间</td>
										<td align="left" valign="top"
											style="border-bottom: #eee 1px solid;">备注</td>
									</tr>
								</tbody>
							</table>

							<table align="enter" width="100%" cellspacing="0" cellpadding="0"
								border="0" id="ShipMentTab">
								<tbody>
									<tr>
										<td align="left" width="130px" valign="top" height="24">
											<input type="radio" value="70" checked=""
											onclick="changeShipType(70);" id="IdShipmentType70"
											name="IdShipmentType"> <label for="IdShipmentType70"
											style="margin-left: 2px;">京东快递</label>

										</td>
										<td align="left" width="155px" valign="top">0.00元<span
											style="color: red">(免运费)</span></td>
										<td align="left" width="105px" valign="top">1-2天</td>
										<td align="left" valign="top" class="gray">由京东公司负责配送，速度很快，还接受上门刷卡付款服务。</td>
									</tr>
								</tbody>
							</table>

							<div style="display: " id="CodTimeUpdatePanel">
								<div style="margin-left: 40px; width: 648px; padding-left: 4px;"
									class="tsbox">


									<div>
										<table>
											<tbody>
												<tr>
													<td valign="top" style="width: 70px">&nbsp;送货日期：<br>&nbsp;大件商品
													</td>
													<td style="width: 93px;"><select
														onclick="g('payType_BigItemCodTime').value=this.value;"
														class="u15" id="payType_BigTick"><option
																value="-1" selected="">请选择日期</option>
															<option value="0">4.30 周一</option>
															<option value="1">5.1 周二</option>
															<option value="2">5.2 周三</option>
															<option value="3">5.3 周四</option>
															<option value="4">5.4 周五</option>
															<option value="5">5.5 周六</option>
															<option value="6">5.6 周日</option>
															<option value="7">5.7 周一</option>
															<option value="8">5.8 周二</option>
															<option value="9">5.9 周三</option>
															<option value="10">5.10 周四</option></select></td>
													<td><span style="padding-left: 4px;">编号532228为大件商品。</span></td>
												</tr>
											</tbody>
										</table>
									</div>



									<div class="gray" style="display: "></div>

									<div>
										是否送货前电话确认： <input type="radio" value="1"
											onclick="g('payType_IsCodInform').value=this.value;"
											id="idInformRad1" name="isInformRad"><label
											for="idInformRad1">是</label> <input type="radio" checked=""
											value="0"
											onclick="g('payType_IsCodInform').value=this.value;"
											id="idInformRad0" name="isInformRad"><label
											for="idInformRad0">否</label>

									</div>


									<div>
										付款方式： <input type="radio" checked=""
											onclick="g('payType_PaymentWay').value=this.value;" value="0"
											id="PaymentWay0" name="PaymentWay"><label
											for="PaymentWay0" style="margin-left: 2px;">现金</label> <input
											type="radio"
											onclick="g('payType_PaymentWay').value=this.value;" value="1"
											id="PaymentWay1" name="PaymentWay"><label
											for="PaymentWay1" style="margin-left: 2px;">POS刷卡</label> <input
											type="radio"
											onclick="g('payType_PaymentWay').value=this.value;" value="2"
											id="PaymentWay2" name="PaymentWay"><label
											for="PaymentWay2" style="margin-left: 2px;">支票</label>

									</div>

									<div style="margin-bottom: 3px">
										<span style="color: red">声明：</span>1.我们会努力按照您指定的时间配送，但因天气、交通等各类因素影响，您的订单有可能会有延误现象！<br>
										&#12288;&#12288;&#12288;2.部分服务仅在京东配送区域提供，非京东配送区域无法选择！<br>
										&#12288;&#12288;&#12288;3.为避免送货延迟，大件商品要尽快完成支付！以上敬请谅解！

									</div>
								</div>
							</div>

						</div>


						<div style="display: none">
							<input type="text" value="1" id="payType_IdPaymentType">
							<input type="text" value="70" id="payType_IdShipmentType">
							<input type="text" value="0" id="payType_IdPickSite"> <input
								type="text" value="2" id="payType_CODTime"> <input
								type="text" value="0" id="payType_PaymentWay"> <input
								type="text" value="" id="payType_IdAgent"> <input
								type="text" value="False" id="payType_IsCodInform"> <input
								type="text" value="0" id="payType_ShipTime"> <input
								type="text" value="0" id="payType_BigItemCodTime"> <input
								type="text" value="" id="payType_SubWayName">

						</div>

						<div class="footer">
							<input type="button" onclick="savePayTypeAndShipType(this)"
								value="保存支付方式及配送方式" clstag="checkout|keycount|jiesuan|savezhifu"
								class="btn">

						</div>
					</div>
				</div>
				<!--支付方式及配送方式结束-->



				<!--发票信息开始-->
				<div id="part_invoice">
					<div class="o_show">
						<h1>
							发票信息&nbsp;<a onclick="showForm_invoice(this)"
								clstag="checkout|keycount|jiesuan|xiugaifapiao"
								href="javascript:void(0)">[修改]</a>
						</h1>
						<div class="middle">
							<table style="width: 100%; display: none">
								<tbody>
									<tr>
										<td style="text-align: left; padding-left: 22px">开取类型：&nbsp;默认开取</td>
									</tr>
								</tbody>
							</table>
							<table style="width: 100%; display: ">
								<tbody>
									<tr>
										<td style="text-align: right; width: 82px;">发票类型：</td>
										<td>普通发票</td>
									</tr>
									<tr>
										<td style="text-align: right;">发票抬头：</td>
										<td>个人</td>
									</tr>



									<tr>
										<td style="text-align: right;">发票内容：</td>
										<td>
											<div>
												<span style="display: none">非图书商品：</span><span
													style="display: ;">明细</span>
											</div>
											<div>
												<span style="display: none">图书商品：</span><span
													style="display: none;">不开发票</span>
											</div>
										</td>
									</tr>
								</tbody>
							</table>
							<table style="width: 100%; display: none">
								<tbody>
									<tr>
										<td style="text-align: left; padding-left: 22px">不开发票</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="footer"></div>
					</div>





				</div>



				<!--发票信息编辑-->
				<div class="o_write">
					<h1>
						发票信息 <a onclick="close_invoice(this)" href="javascript:void(0)">[关闭]</a>
					</h1>


					<div style="padding: 10px 0 10px 20px; display: none"
						class="middle" id="invoicePutTypePanel">

						<input type="checkbox"
							onclick="if(this.checked){g('invoiceFormPanel').style.display='';g('invoice_InvoicePutType').value='0';}else{g('invoiceFormPanel').style.display='none';g('invoice_InvoicePutType').value='3';}"
							checked="" id="chkInvoicePutType"><label
							style="margin-right: 17px;" for="chkInvoicePutType">我要开发票</label>


					</div>



					<div style="display: " class="middle" id="invoiceFormPanel">
						<div style="display: " id="invoiceListPanel"></div>
						<div id="part_invoice_form">
							<div style="display: " id="invoiceContentPanel">

								<table width="100%" cellspacing="0" cellpadding="0" border="0"
									style="display: none" class="txt_12">
									<tbody>
										<tr>
											<td align="left" width="70" valign="top">开取方式：</td>
											<td align="left" valign="top"><input type="radio"
												value="0" checked="" onclick="InvoicePutTypeOnChange(this);"
												id="invoince_InvoicePutType0" name="invoince_putType"><label
												for="invoince_InvoicePutType0">默认开取</label> <input
												type="radio" value="3"
												onclick="InvoicePutTypeOnChange(this);"
												id="invoince_InvoicePutType3" name="invoince_putType"><label
												for="invoince_InvoicePutType3">自定义开取</label></td>
										</tr>
									</tbody>
								</table>
								<table width="100%" cellspacing="0" cellpadding="0" border="0"
									style="display: " id="tb_invoice" class="txt_12">
									<tbody>
										<tr>
											<td align="left" valign="top" colspan="2"><span
												style="margin-right: 8px">发票类型：</span> <input type="radio"
												value="1" checked="" onclick="invoince_setType(1)"
												id="invoince_InvoiceType1" name="invoince_type"><label
												for="invoince_InvoiceType1">普通发票</label> <input type="radio"
												value="2" onclick="invoince_setType(2)"
												id="invoince_InvoiceType2" name="invoince_type"><label
												for="invoince_InvoiceType2">增值税发票</label> <span
												id="panel_invoicetypeRemark" class="gray"></span></td>
										</tr>



									</tbody>
									<tbody style="display: " id="invoice_titleTr">
										<tr>
											<td align="left" valign="top" colspan="2"><span
												style="margin-right: 8px">发票抬头：</span> <input type="radio"
												value="4" checked="" onclick="invoince_setPttt(4)"
												id="invoince_pttt4" name="invoince_pttt"><label
												for="invoince_pttt4">个人 </label> <input type="radio"
												value="5" onclick="invoince_setPttt(5)" id="invoince_pttt5"
												name="invoince_pttt"><label for="invoince_pttt5">单位</label>
											</td>
										</tr>

										<tr style="display: none;" id="invoice_unitNameTr"
											class="txt_color_hui">
											<td align="left" width="70" valign="top">单位名称：</td>
											<td align="left" valign="top"><input type="text"
												onblur="check_invoice_unit()" value="" style="width: 260px"
												class="txt" id="invoice_Unit_TitName"><span
												class="red2">*</span><br>
											<span class="gray">温馨提示：您填写的所有内容都将被系统自动打印到发票上，所以请千万别填写和发票抬头无关的信息。</span>

											</td>
										</tr>
									</tbody>


									<tbody>
										<tr style="display: none" id="invoice_ivc_Tr"
											class="txt_color_hui">
											<td align="left" valign="top" colspan="2">
												<div id="invoiceVatListPanel" style="display: none;"></div>
												<table width="100%" cellspacing="0" cellpadding="0"
													border="0">

													<tbody>
														<tr>
															<td align="left" valign="top" colspan="2">增值税发票专用发票资质填写：</td>
														</tr>

														<tr>
															<td align="left" width="88" valign="top">单位名称：</td>
															<td align="left" width="583" valign="top"><input
																type="text" onblur="check_Ivc_TitName()" value=""
																style="width: 260px" class="txt"
																id="invoice_Ivc_TitName"><span class="red2">*</span>

															</td>
														</tr>
														<tr>
															<td align="left" valign="top">纳税人识别号：</td>
															<td align="left" valign="top"><input type="text"
																onblur="check_NsrCode()" value="" style="width: 260px"
																class="txt" id="invoice_Ivc_NsrCode"><span
																class="red2">*</span></td>
														</tr>
														<tr>
															<td align="left" valign="top">注册地址：</td>
															<td align="left" valign="top"><input type="text"
																onblur="check_InvoiceAddress()" value=""
																style="width: 260px" class="txt"
																id="invoice_Ivc_Address"><span class="red2">*</span>

															</td>
														</tr>
														<tr>
															<td align="left" valign="top">注册电话：</td>
															<td align="left" valign="top"><input type="text"
																onblur="check_InvoicePhone()" value=""
																style="width: 260px" class="txt" id="invoice_Ivc_Phone"><span
																class="red2">*</span></td>
														</tr>
														<tr>
															<td align="left" valign="top">开户银行：</td>
															<td align="left" valign="top"><input type="text"
																onblur="check_InvoiceBank()" value=""
																style="width: 260px" class="txt" id="invoice__Ivc_Bank"><span
																class="red2">*</span></td>
														</tr>
														<tr>
															<td align="left" valign="top">银行帐户：</td>
															<td align="left" valign="top"><input type="text"
																onblur="check_InvoiceBankCode()" value=""
																style="width: 260px" class="txt"
																id="invoice_Ivc_BankCode"><span class="red2">*</span>

															</td>
														</tr>

														<tr>
															<td align="left" valign="top">&nbsp;</td>
															<td align="left" valign="top">
																首次开具增值税专用发票的客户需提供加盖公章的营业执照副本、税务登记证副本、一般纳税人资格证书、银行开户许可证复印件，通过传真，或提供扫描件发送至京东客服。<br>传真号码：010-58043575<br>邮箱地址：kaipiaoxinxi@360buy.com<br>我们收到您的开票资料后，将会尽快审核。<br>
															<br>
															<font color="ff6600">注意：有效增值税发票开票资质仅为一个。</font>

															</td>
														</tr>
													</tbody>
												</table>
											</td>
										</tr>

									</tbody>
								</table>

								<table width="100%" cellspacing="0" cellpadding="0" border="0"
									style="display: " class="txt_12" id="invoice_AddInvoiceBtn">
									<tbody>
										<tr>
											<td align="left" width="70px" valign="top"
												style="padding-top: 4px"></td>
											<td><a onclick="AddInvoice(this);" href="#none">[添加至常用发票信息]</a></td>
										</tr>
									</tbody>
								</table>
							</div>




							<table width="100%" cellspacing="0" cellpadding="0" border="0"
								class="txt_12">
								<tbody>
									<tr>
										<td align="left" width="70px" valign="top"
											style="padding-top: 4px">发票内容：</td>
										<td align="left">
											<table width="100%" cellspacing="0" cellpadding="0"
												border="0" class="txt_12">
												<tbody>
													<tr style="display: none">
														<td align="left">非图书商品：</td>
													</tr>
													<tr style="display: ">
														<td><span id="invoince_contentPanel"> <input
																type="radio" value="1" checked=""
																onclick="invoince_setContent(1)" id="invoince_content1"
																name="invoince_content"><label
																for="invoince_content1">明细</label> <input type="radio"
																value="2" onclick="invoince_setContent(2)"
																id="invoince_content2" name="invoince_content"><label
																for="invoince_content2">办公用品</label> <input type="radio"
																value="3" onclick="invoince_setContent(3)"
																id="invoince_content3" name="invoince_content"><label
																for="invoince_content3">电脑配件</label> <input type="radio"
																value="19" onclick="invoince_setContent(19)"
																id="invoince_content19" name="invoince_content"><label
																for="invoince_content19">耗材</label>
														</span></td>
													</tr>
													<tr>
														<td align="left"><span style="display: none">图书商品：</span><span
															style="display: none"><input type="checkbox"
																onclick="if(this.checked){g('InvoiceContentBookPanel').style.display='';if(g('invoice_IsOnlyBook').value=='1'){g('invoiceContentPanel').style.display='';g('invoiceListPanel').style.display='';}}else{g('InvoiceContentBookPanel').style.display='none';if(g('invoice_IsOnlyBook').value=='1'){g('invoiceContentPanel').style.display='none';g('invoiceListPanel').style.display='none';}}"
																value="1" id="invoice_IsPutBookInvoice"><label
																style="margin-right: 17px;"
																for="invoice_IsPutBookInvoice">开发票</label></span></td>
													</tr>
													<tr style="display: none">
														<td align="left"><span style="display: none"
															id="InvoiceContentBookPanel"><span
																id="InvoiceContentBookPanelSpan"> <input
																	type="radio" value="4"
																	onclick="invoince_setContentBook(4)"
																	id="invoince_contentBook4" name="invoince_contentBook"><label
																	for="invoince_contentBook4">图书</label> <input
																	type="radio" value="7"
																	onclick="invoince_setContentBook(7)"
																	id="invoince_contentBook7" name="invoince_contentBook"><label
																	for="invoince_contentBook7">资料</label> <input
																	type="radio" value="6"
																	onclick="invoince_setContentBook(6)"
																	id="invoince_contentBook6" name="invoince_contentBook"><label
																	for="invoince_contentBook6">教材</label>
															</span></span></td>
													</tr>
												</tbody>
											</table>
										</td>
									</tr>
								</tbody>
							</table>


							<table width="100%" cellspacing="0" cellpadding="0" border="0"
								class="txt_12">
								<tbody>
									<tr class="txt_color_hui">
										<td align="left" valign="top"><span class="gray"
											id="invDetailTip"></span></td>
									</tr>
								</tbody>
							</table>


							<div style="display: none">
								<input type="text" value="1" id="invoice_InvoiceType"> <input
									type="text" value="4" id="invoice_InvoiceTitle"> <input
									type="text" value="1" id="invoice_Invoice_Content"> <input
									type="text" value="0" id="invoice_InvoicePutType"> <input
									type="text" value="0" id="invoice_Invoice_ContentBook">
								<input type="text" value="0" id="invoice_IsOnlyBook"> <input
									type="text" value="0" id="invoice_VatInvId">

							</div>
						</div>
					</div>
					<div class="footer">
						<input type="button" onclick="savePart_invoice(this)"
							value="保存发票信息" clstag="checkout|keycount|jiesuan|savefapiao"
							class="btn">


					</div>
				</div>
				<!--发票信息编辑结束-->





				<!--发票信息结束-->



				<!--Cart信息开始-->

				<div id="part_cart">
					<div class="o_show">
						<h1>
							<span style="margin-right: 700px;">商品清单</span><a
								style="color: #0070D7"
								clstag="checkout|keycount|jiesuan|backtocartbtn"
								href="shoppingcart_pop.aspx" id="backToCartBtn">返回修改购物车</a>
						</h1>
						<div class="middle">
							<table width="100%" cellspacing="0" cellpadding="0" class="Table">
								<tbody>
									<tr class="align_Center Thead">
										<td width="7%">商品编号</td>
										<td>商品名称</td>
										<td width="10%">京东价</td>
										<td width="8%">返现</td>
										<td width="8%">赠送积分</td>
										<td width="9%">库存状态</td>
										<td width="9%">商品数量</td>
									</tr>

									<tr class="align_Center">
										<td style="padding: 5px 0 5px 0;">532228</td>
										<td class="align_Left"><a
											clstag="clickcart|keycount|shoppingcartpop|productnamelinklistcart"
											onclick="this.blur();"
											href="http://www.360buy.com/product/532228.html"
											target="_blank">TCL 3273EDS 32英寸 LED液晶电视 高清 蓝光 黑色</a></td>
										<td><span class="price">￥1,698.00</span></td>
										<td>￥0.00</td>
										<td>0</td>
										<td>现货</td>
										<td>1</td>
									</tr>

								</tbody>
							</table>
						</div>
						<div class="footer"></div>
					</div>

				</div>
				<!--Cart信息开始-->
				<!--有货先发开始-->
				<div style="padding: 8px 0 8px 30px; color: red"
					id="part_FirstInstockShip"></div>
				<!--有货先发结束-->



				<div id="ware_info">

					<div
						style="background: #fff; font-size: 14px; font-weight: bold; padding-left: 2px;">结算信息</div>
					<h1></h1>

					<div class="middle">
						<!--订单信息-->
						<ul id="part_info">


							<li style="padding-bottom: 5px" class="info1">商品金额：1698.00元
								+ 运费：0.00元 - 优惠券：<span id="price_coupon">0.00</span>元 - 礼品卡：<span
								id="price_coupon">0.00</span>元 - 返现：0.00元 <input type="hidden"
								value="0" id="txtIsUseMoney"> <br>

							</li>

							<li style="width: 100%; overflow: hidden;" class="info2">
								<table cellspacing="0" cellpadding="0" style="width: 100%">
									<tbody>
										<tr>
											<td>

												<div style="display: ">
													<a onclick="showTicket();this.blur();"
														href="javascript:void(0);">(<span id="couponStateShow">+</span>)使用优惠券抵消部分总额
													</a><span id="schoolcoupon"></span><font color="red"
														style="font-size: 12px; display: none; margin-left: 15px;">提醒：您的优惠券有浪费使用的情况，请检查！</font>
												</div>
											</td>
											<td style="text-align: right; font-size: 15px;"><b>应付总额：<font
													color="red">￥1,698.00</font> 元
											</b>
												<div id="paypassword_panel"></div></td>
										</tr>
									</tbody>
								</table>
							</li>
						</ul>
						<!--订单信息结束-->
						<!--优惠券-->
						<div style="" id="part_ticket" class="ticket">

							<div style="margin: 7px">

								<div>
									<span style="margin-right: 340px">提示：东券每次只能使用一张，京券每次可使用多张，两种不可混合使用</span><a
										onclick="showTicket();" href="#none">[关闭]</a>
								</div>
								<div id="CouponRemark"></div>
								<h2 class="m10" style="background: none">
									<span style="font-size: 13px; font-weight: bold">请选择要使用的优惠券：</span>
								</h2>
								<p>
									<a href="#">s34345io90 5元优惠券</a>
								</p>
								<p>
									<a href="#">s34345io90 10元优惠券</a>
								</p>
								<div id="jingDiv"></div>
								<div id="dongDiv"></div>



								<div style="margin-top: 15px; padding-left: 3px;">
									有实体券？<a style="margin-right: 10px"
										onclick="var obj=g('shitiPanel');obj.style.display=(obj.style.display=='none')?'':'none';"
										href="#none">点此输入实体券密码</a><a
										href="http://www.360buy.com/help/coupon.aspx" target="_blank">了解优惠券规则</a>
								</div>
								<div style="display: none;" id="shitiPanel">
									<div style="padding-left: 10px; pading-top: 5px;">
										请输入您手中优惠券的密码：<input type="text" class="txt" id="txtInputKey">&nbsp;<input
											type="button" value="添加" ;="" onclick="addShiTiCoupon(this);"
											id="btnAddYHQ" class="btn" name="Submit2">

									</div>
									<div style="border: #fff 1px solid; padding-left: 15px;"
										id="shitiList"></div>
								</div>



								<div class="couponInfo">
									共使用了 <span id="coupon_count">0</span> 张优惠券&#12288;可以优惠 <span
										id="coupon_discount">0.00</span> 元
								</div>

							</div>

						</div>
						<!--优惠券结束-->


						<!--礼品卡-->
						<a
							style="line-height: 25px; float: left; display: block; padding-left: 10px; background: url(http://www.360buy.com/purchase/skin/images/point.jpg) left no-repeat; color: #C68600; font-weight: bold"
							onclick="showLipinka();this.blur();" href="javascript:void(0);">(<span
							id="lipinkaStateShow">+</span>)使用礼品卡
						</a>
						<div style="clear: both"></div>
						<div id="part_lipinka" class="ticket">

							<div>
								<span style="margin-right: 310px">提示：礼品卡可多张一起使用，也可与券、券混合使用。<a
									target="_blank"
									href="http://market.360buy.com/giftcard/index.html">什么是礼品卡</a></span><a
									onclick="showLipinka();" href="#none">[关闭]</a>
							</div>
							<div id="LiPinKaRemark"></div>
							<h2 style="background: none;">
								<span style="font-size: 13px; font-weight: bold">添加礼品卡</span>
							</h2>
							<div>
								请输入您手中礼品卡的密码：<input type="text" class="txt" id="txtInputLPKey">&nbsp;<input
									type="button" value="添加"
									;="" onclick="addShiTiLiPinKa(this,'1');" id="btnAddLPK"
									class="btn" name="Submit2">

							</div>
							<h2 class="m10" style="background: none;">
								<span style="font-size: 13px; font-weight: bold">已有礼品卡</span>
							</h2>
							<div style="border: #fff 1px solid" id="LiPin_DianZiItemsPanel">

								<div class="t_box">
									<table style="width: 100%">
										<tbody>
											<tr>
												<th style="width: 200px;">卡号</th>
												<th style="width: 90px;">面值</th>
												<th style="width: 90px;">本次使用</th>
												<th style="width: 80px;">余额</th>
												<th style="width: 200px;">有效期</th>
												<th style="width: 80px;">类型</th>

											</tr>
											<tr>
												<th style="width: 200px; font-weight: normal;">
													23445566</th>
												<th style="width: 90px; font-weight: normal;">15</th>
												<th style="width: 90px; font-weight: normal;">本次使用</th>
												<th style="width: 80px; font-weight: normal;">15</th>
												<th style="width: 200px; font-weight: normal;">
													2012-12-12</th>
												<th style="width: 80px; font-weight: normal;">类型</th>

											</tr>

										</tbody>
									</table>
								</div>

							</div>

							<div class="couponInfo">
								共使用了 <span id="coupon_count">0</span> 张礼品卡&#12288;可以优惠 <span
									id="coupon_discount">0.00</span> 元
							</div>

						</div>
						<!--礼品卡结束-->


					</div>
				</div>




				<div class="safepsd01" id="paypasswordPanel" style="display: none;">
					<span class="label">支付密码：</span>
					<div class="fl">
						<input type="password" class="text" id="txt_paypassword">
						<div class="msg-text">
							<a target="_blank"
								href="http://safe.360buy.com/user/paymentpassword/getBackPassword.action">忘记支付密码？</a>
						</div>
						<div class="clr"></div>

					</div>
					<div class="clr"></div>
				</div>



				<!--提交-->

				<div class="o_show submit">
					<div>
						<span id="submitWaitInfo"></span>
					</div>
					<div id="submit_error"></div>
					<div id="submit_btn">

						<span id="ccPanel"></span>

						<table width="100%" cellspacing="0" cellpadding="0">
							<tbody>
								<tr>
									<td style="padding: 0; text-align: left; vertical-align: top;">
										<div id="part_remark"
											style="width: 600px; padding-left: 10px;">
											<div>
												<input type="checkbox" onclick="showForm_remark(this)"><font
													id="showForm_remark">订单备注 </font>
											</div>
											<font id="showForm_remark"> </font>
										</div>
										<font id="showForm_remark"> <input type="hidden"
											value="68ad98eb-f5d5-4d5a-b090-0cc2885e91a2^LFM3ZXYF6ALBDNE5PWMBMDSRJ6U6MAXWYUGN5S5HXP5DQRVXCGHER3VGQYLWXELR"
											name="TrackID" id="TrackID">

									</font>
									</td>
									<td style="width: 100px; padding: 0;"><input type="button"
										style="margin-top: 2px; border: none; cursor: pointer; width: 160px; height: 53px; background: url(http://www.360buy.com/purchase/skin/images/submit.jpg)"
										clstag="checkout|keycount|jiesuan|sumbit"
										onclick="submitOrder(this);" id="submit"></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<!--提交结束-->

			</div>
			<div class="round">
				<div class="lround"></div>
				<div class="rround"></div>
			</div>
		</div>
		<!----填写核对订单信息---->
		<!----成功提交---->
		<div class="List_cart marginb10" style="margin-left: 150px;">
			<h2>
				<strong>成功提交订单</strong>
			</h2>

			<div class="cart_table">
				<div style="padding: 0px 60px 0px;" class="fbc">
					<div class="fd_title">
						<dl>
							<dt>
								订单已提交，我们会立即为您备货，当订单状态为“<font color="#FF6600">等待上门提货</font>”时，您可以到商城指定自提点提货
							</dt>
							<dd>
								您的订单号：<span>2011060401086</span>应付金额：<span>0.00<font
									color="#333333" style="font-size: 12px; font-weight: normal;">元</font></span>支付方式：<em
									style="padding-right: 30px; font-style: normal;">壹购自提支付</em>配送方式：门店自提
							</dd>
						</dl>
					</div>
					<div class="tip_2" id="SucceedTipOtherPanel">
						<table class="border_none" style="">
							<tbody>
								<tr>
									<td style="width: 45px; vertical-align: top;"><b>提示：</b></td>
									<td style="vertical-align: top; text-align: left;">
										<ul id="SucceedTipOther" style="list-style-type: disc;">
											<li style="color: red;">由于将商品由仓库送至自提点需要一定时间，所以请不要下单后立即到门店自提，如您下单时填写了手机号，可提货时会有电话通知您。</li>
											<li>当您的订单状态为“等待上门提货”时，表示商品已送至自提点，可以来中域商城自提点提货。</li>

										</ul>
									</td>
								</tr>
							</tbody>
						</table>


					</div>


					<div class="tip_3">
						<div style="line-height: 22px;">
							<p>自&#12288;提&#12288;点：潮州大道奎元广场店</p>
							<p>自提点地址：潮州市潮州大道奎元广场C幢首层中域电讯 - 中域电讯</p>
							<p>收货人姓名：杜亚</p>
							<p>收货人电话：13827312895</p>
						</div>
					</div>

					<div class="fd_link">
						<span
							style="padding-right: 10px; font-size: 14px; font-weight: bold; color: #333;">现在，您可以:</span><a
							class="fdl_1" href="/user/order_detail?order_id=29762">查看订单状态</a><a
							class="fdl_2" href="http://www.zhongyu.com">继续购物</a><a
							class="fdl_2" href="/">返回首页</a>
					</div>
				</div>

			</div>
			<div class="round">
				<div class="lround"></div>
				<div class="rround"></div>
			</div>
		</div>
		<!----成功提交---->
	</div>
	<!-----------right_con end-------------->
	<div class="clear"></div>
</div>
<!----两栏end---->
