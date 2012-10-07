<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<!----地址---->
<div class="w addr">
	<span><a href="#">首页</a>
	</span>&gt;<span><a href="#">个人中心</a>
	</span>&gt;<span><a href="#">个人信息</a>
	</span>&gt;<span>&nbsp;</span>
</div>
<!----地址end---->


<!----两栏---->
<div class="w" style="padding-top:10px;">
	<!-----------leftm-------------->
	<c:import url="usercenter_left.jsp" />
	<!-----------leftm end-------------->

	<!-----------right_con-------------->
	<div class="right_con">

		<div class="o-mt">
			<h2>个人信息</h2>
		</div>

		<form action="" method="post">
			<div class="formtable">
				<table>
					<tbody>						
						<tr>
							<td>真实姓名：</td>
							<td><input type="text" class="inputstyle" value="hwww">
							</td>
							<td class="hint">&nbsp;</td>
						</tr>
						<tr>
							<td>性别：</td>
							<td><input type="radio" value="" name="sex"> 男
								&nbsp; <input type="radio" value="" name="sex"> 女</td>
							<td class="hint">&nbsp;</td>
						</tr>
						<tr>
							<td>邮箱：</td>
							<td><input type="text" class="inputstyle" readonly="readonly"
								value="agaee@163.com">
							</td>
							<td class="hint">&nbsp;</td>
						</tr>
						<tr>
							<td>手机号码：</td>
							<td><input type="text" class="inputstyle">
							</td>
							<td class="hint">&nbsp;</td>
						</tr>		
						<tr>
							<td>QQ：</td>
							<td><input type="text" class="inputstyle">
							</td>
							<td class="hint">&nbsp;</td>
						</tr>	
						<tr>
							<td>生日：</td>
							<td><input type="text" class="inputstyle">
							</td>
							<td class="hint">&nbsp;</td>
						</tr>			
						<!-- 
              <tr><td>所在地：</td><td><select name=""><option>请选择省</option></select> <select name=""><option>请选择市</option></select> <select name=""><option>请选择区</option></select></td><td class="hint"></td></tr>
               -->
						<tr>
							<td>联系地址：</td>
							<td><input type="text" class="inputstyle">
							</td>
							<td class="hint">&nbsp;</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td><input type="submit" value="确定" /></td>
							<td class="hint">&nbsp;</td>
						</tr>
					</tbody>

				</table>

			</div>
		</form>


	</div>
	<!-----------right_con end-------------->

	<div class="clear"></div>
</div>
<!----两栏end---->