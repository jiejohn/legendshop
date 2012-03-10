<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/pages/common/common.jsp'%> 
<%@include file='/pages/common/taglib.jsp'%>
<%@page import="com.legendshop.core.helper.PropertiesUtil"%>
<link href="${pageContext.request.contextPath}/common/css/header.css" rel="stylesheet" type="text/css" />
<div id="pagetop">
	<div id="headertop">
		<table width="954px" cellspacing="0" cellpadding="0"
			style="margin-bottom: 5px;" align="center" border="0">
			<tr>
				<td valign="top" align="left">
					<div id="site-nav">
						<UL class="quick-menu">
							<LI class="first">
								<A href="${pageContext.request.contextPath}/all${applicationScope.WEB_SUFFIX}" target="_blank"><b><fmt:message key="search.total.index" />
								</b>
								</A>
							</LI>
							<c:if test="${'C2C' == applicationScope.BUSINESS_MODE}">
								<%
									if (PropertiesUtil.getDefaultShopName() != null && PropertiesUtil.getDefaultShopName().length() > 0) {
								%>
								<LI>
									<a href="${pageContext.request.contextPath}/shop/<%=PropertiesUtil.getDefaultShopName()%>${applicationScope.WEB_SUFFIX}"><fmt:message key="shop.total.index" /></a>
								</LI>
								<%
									}
								%>
							</c:if>
							<LI>
								<fmt:message key="nows.location" />
								<c:if test="${sessionScope.shopDetail.province != null}">
								  		 ${sessionScope.shopDetail.province}/${sessionScope.shopDetail.city}/${sessionScope.shopDetail.area}/
								  </c:if>
								<b><a href="${pageContext.request.contextPath}/shopcontact${applicationScope.WEB_SUFFIX}">${sessionScope.shopName}</a>
								</b>
							</LI>
							<c:if test="${'C2C' == applicationScope.BUSINESS_MODE}">
								<LI>
									<A href="${pageContext.request.contextPath}/reg${applicationScope.WEB_SUFFIX}?openshop=1" target="_blank"><fmt:message
											key="register.shop" />
									</A>
								</LI>
							</c:if>
							<LI class="favorite">
								<a href='#'
									onclick="javascript:bookmark('<fmt:message key="welcome.to.legendshop"/><%=PropertiesUtil.getDefaultShopName()%> - LegendShop购物商城','${DOMAIN_NAME}/shop/<%=PropertiesUtil.getDefaultShopName()%>${applicationScope.WEB_SUFFIX}');">
									<fmt:message key="shop.favorite" />
								</a>
							</LI>


							<LI class="services menu-item last">
								<div class="menu">
									<A class="menu-hd"
										href="${applicationScope.LEGENDSHOP_DOMAIN_NAME}/club"
										target="_blank"><fmt:message key="club.navigation" /><B></B>
									</A>
									<div class="menu-bd"
										style="WIDTH: 280px; HEIGHT: 240px; _width: 202px;">
										<div class="menu-bd-panel">
											<DL>
												<DT>
													<b>站务公告</b>
												<DD>
													<A
														href="${applicationScope.LEGENDSHOP_DOMAIN_NAME}/club/forums/show/14.page"
														target="_blank">公告专区</A>
												</DD>
											</DL>
											<DL>
												<DT>
													<b>网商之家</b>
												<DD>
													<A
														href="${applicationScope.LEGENDSHOP_DOMAIN_NAME}/club/forums/show/17.page"
														target="_blank">店长经验交流</A>
													<A
														href="${applicationScope.LEGENDSHOP_DOMAIN_NAME}/club/forums/show/3.page"
														target="_blank">网店营销推广</A>
												</DD>
											</DL>
											<DL>
												<DT>
													<b>LegendShop商城系统</b>
												<DD>
													<A
														href="${applicationScope.LEGENDSHOP_DOMAIN_NAME}/club/forums/show/16.page"
														target="_blank">程序发布与升级</A>
													<A
														href="${applicationScope.LEGENDSHOP_DOMAIN_NAME}/club/forums/show/12.page"
														target="_blank">安装与使用</A>
													<A
														href="${applicationScope.LEGENDSHOP_DOMAIN_NAME}/club/forums/show/13.page"
														target="_blank">技术交流区</A>
													<A
														href="${applicationScope.LEGENDSHOP_DOMAIN_NAME}/club/forums/show/15.page"
														target="_blank">产品资讯与销售</A>
													<A
														href="${applicationScope.LEGENDSHOP_DOMAIN_NAME}/club/forums/show/22.page"
														target="_blank">意见与建议</A>
												</DD>
											</DL>
											<DL class=last>
												<DD>
													<STRONG style="FONT-WEIGHT: bold"><A href="/club"
														target="_blank">更多内容</A>
													</STRONG>
												</DD>
											</DL>
										</div>
										<S class=r></S><S class=rt></S><S class=lt></S><S class=b
											style="WIDTH: 169px"></S><S class="b b2" style="WIDTH: 169px"></S><S
											class=rb></S><S class=lb></S>
									</div>
								</div>
							</LI>
						</UL>
					</div>
				</td>
			</tr>
		</table>
	</div>
</div>