<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/common.jsp'%> 
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<%@page import="com.legendshop.core.helper.PropertiesUtil"%>
<lb:shopDetail var="shopDetail" />
<link href="${pageContext.request.contextPath}/common/default/css/header.css" rel="stylesheet" type="text/css" />
<div id="pagetop">
	<div id="headertop">
		<table width="954px" cellspacing="0" cellpadding="0"
			style="margin-bottom: 5px;" align="center" border="0">
			<tr>
				<td valign="top" align="left">
					<div id="site-nav">
						<ul class="quick-menu">
							<li class="first">
								<a href="${pageContext.request.contextPath}/all" target="_blank"><b><ls:i18n key="search.total.index" /></b></a>
							</li>
							<c:if test="${'C2C' == applicationScope.BUSINESS_MODE}">
								<%
									if (PropertiesUtil.getDefaultShopName() != null) {
								%>
								<li>
									<a href=" <ls:domain shopName='<%=PropertiesUtil.getDefaultShopName()%>'/>"><ls:i18n key="shop.total.index" /></a>
								</li>
								<%
									}
								%>
							</c:if>
							<li>
								<ls:i18n key="nows.location" />
								<c:if test="${shopDetail.province != null}">
								  		 ${shopDetail.province}/${shopDetail.city}/${shopDetail.area}/
								  </c:if>
								<b><a href="${pageContext.request.contextPath}/shopcontact">${sessionScope.shopName}</a>
								</b>
							</li>
							<c:if test="${'C2C' == applicationScope.BUSINESS_MODE}">
								<li>
									<a href="${pageContext.request.contextPath}/reg?openshop=1" target="_blank"><ls:i18n
											key="register.shop" />
									</a>
								</li>
							</c:if>
							<li class="favorite">
								<a href='#'
									onclick="javascript:bookmark('<ls:i18n key="welcome.to.legendshop"/><%=PropertiesUtil.getDefaultShopName()%> - LegendShop购物商城','<ls:domain shopName='<%=PropertiesUtil.getDefaultShopName()%>' />');">
									<ls:i18n key="shop.favorite" />
								</a>
							</li>
							<li class="services menu-item last">
								<div class="menu">
									<a class="menu-hd"
										href="${applicationScope.LEGENDSHOP_DOMAIN_NAME}/club"
										target="_blank"><ls:i18n key="club.navigation" /><B></B>
									</a>
									<div class="menu-bd"
										style="WIDTH: 280px; HEIGHT: 240px; _width: 202px;">
										<div class="menu-bd-panel">
											<dl>
												<dt>
													<b>站务公告</b>
												<dd>
													<a href="${applicationScope.LEGENDSHOP_DOMAIN_NAME}/club/forums/show/14.page" target="_blank">公告专区</a>
												</dd>
												</dt>
											</dl>
											<dl>
												<dt>
													<b>网商之家</b>
												<dd>
													<a href="${applicationScope.LEGENDSHOP_DOMAIN_NAME}/club/forums/show/17.page" target="_blank">店长经验交流</a>
													<a href="${applicationScope.LEGENDSHOP_DOMAIN_NAME}/club/forums/show/3.page" target="_blank">网店营销推广</a>
												</dd>
												</dt>
											</dl>
											<dl>
												<dt>
													<b>LegendShop商城系统</b>
												<dd>
													<a href="${applicationScope.LEGENDSHOP_DOMAIN_NAME}/club/forums/show/16.page" target="_blank">程序发布与升级</a>
													<a href="${applicationScope.LEGENDSHOP_DOMAIN_NAME}/club/forums/show/12.page" target="_blank">安装与使用</a>
													<a href="${applicationScope.LEGENDSHOP_DOMAIN_NAME}/club/forums/show/13.page" target="_blank">技术交流区</a>
													<a href="${applicationScope.LEGENDSHOP_DOMAIN_NAME}/club/forums/show/15.page" target="_blank">商品资讯与销售</a>
													<a href="${applicationScope.LEGENDSHOP_DOMAIN_NAME}/club/forums/show/22.page" target="_blank">意见与建议</a>
												</dd>
												</dt>
											</dl>
											<dl class=last>
												<dd>
													<strong style="FONT-WEIGHT: bold"><a href="/club"
														target="_blank">更多内容</a>
													</strong>
												</dd>
											</dl>
										</div>
										<s class=r></s><s class=rt></s><s class=lt></s><s class=b
											style="WIDTH: 169px"></s><s class="b b2" style="WIDTH: 169px"></s><s
											class=rb></s><s class=lb></s>
									</div>
								</div>
							</li>
						</ul>
					</div>
				</td>
			</tr>
		</table>
	</div>
</div>