/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.legendshop.business.common.page.FowardPage;
import com.legendshop.business.common.page.FrontPage;
import com.legendshop.business.event.impl.VisitLogEvent;
import com.legendshop.business.service.ImgFileService;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.constant.ProductTypeEnum;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.exception.ErrorCodes;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.event.EventHome;
import com.legendshop.model.UserMessages;
import com.legendshop.model.entity.ImgFile;
import com.legendshop.model.entity.Product;
import com.legendshop.model.entity.ProductDetail;
import com.legendshop.model.entity.ShopDetailView;
import com.legendshop.model.visit.VisitHistory;
import com.legendshop.spi.constants.Constants;
import com.legendshop.spi.constants.VisitTypeEnum;
import com.legendshop.spi.service.ProductService;
import com.legendshop.util.AppUtils;

/**
 * 产品分类控制器。.
 */
@Controller
public class ProductController extends BaseController{
	
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ImgFileService imgFileService;

	/**
	 * Views.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param prodId
	 *            the prod id
	 * @return the string
	 */
	@RequestMapping("/views/{prodId}")
	public String views(HttpServletRequest request, HttpServletResponse response,@PathVariable Long prodId) {
		if (prodId == null) {
			return PathResolver.getPath(FowardPage.INDEX_QUERY);
		}
		ProductDetail prod = productService.getProdDetail(prodId);
		

		if (prod != null) {
			if(ProductTypeEnum.GROUP.value().equals(prod.getProdType())){
				return PathResolver.getPath("/group/view/" + prodId, FowardPage.VARIABLE);
			}
			if (!Constants.ONLINE.equals(prod.getStatus())) {
				throw new NotFoundException("Product " + prod.getName() + " does not online.",EntityCodes.PROD);
			}
			// 查看商品的说明图片
			List<ImgFile> prodPics = imgFileService.getProductPics(prod.getUserName(), prodId);
			if (AppUtils.isNotBlank(prodPics)) {
				request.setAttribute("prodPics", prodPics);
			}
			// 商家详细说明
			
			ShopDetailView shopDetail = ThreadLocalContext.getShopDetailView(prod.getUserName());
			if (shopDetail == null) {
				return PathResolver.getPath(FrontPage.TOPALL);
			} 
			ThreadLocalContext.setCurrentShopName(prod.getUserName());
			// 相关商品
			List<Product> releationProds = productService.getReleationProd(prod.getUserName(), prod.getProdId(), 30);
			if (!AppUtils.isBlank(releationProds)) {
				request.setAttribute("productList", releationProds);
			}

			request.setAttribute("prod", prod);
			
			productService.getAndSetAdvertisement(prod.getUserName());

			// 更新查看次数
			if (PropertiesUtil.getObject(ParameterEnum.VISIT_HW_LOG_ENABLE, Boolean.class)) {
				productService.updateProdViews(prodId);
			}

			String userName = UserManager.getUsername(request.getSession());

			if (log.isInfoEnabled()) {
				log.info(
						"{},{},{},{},viewsprod",
						new Object[] { request.getRemoteAddr(), userName == null ? "" : userName,
								getSessionAttribute(request, Constants.SHOP_NAME), prod.getName() });
			}

			// 记录登录历史
			visit(prod, request);
			// 多线程记录访问历史
			if (PropertiesUtil.getObject(ParameterEnum.VISIT_LOG_ENABLE, Boolean.class)) {
				EventHome.publishEvent(new VisitLogEvent(request.getRemoteAddr(),prod.getUserName(),userName,prod.getProdId(),prod.getName(),VisitTypeEnum.HW.value()));
			}
			return PathResolver.getPath(FrontPage.VIEWS);
		} else {
			UserMessages uem = new UserMessages();
			Locale locale = localeResolver.resolveLocale(request);
			uem.setTitle(ResourceBundleHelper.getString(locale, "product.not.found"));
			uem.setDesc(ResourceBundleHelper.getString(locale, "product.status.check"));
			uem.setCode(ErrorCodes.PRODUCT_NO_FOUND);
			request.setAttribute(UserMessages.MESSAGE_KEY, uem);
			return PathResolver.getPath(FrontPage.FAIL);
		}
	}
	
	/**
	 * Visit.
	 * 
	 * @param prod
	 *            the prod
	 * @param request
	 *            the request
	 */
	private void visit(ProductDetail prod, HttpServletRequest request) {
		VisitHistory visitHistory = (VisitHistory) request.getSession().getAttribute(Constants.VISIT_HISTORY);
		if (visitHistory == null) {
			visitHistory = new VisitHistory();
		}
		visitHistory.visit(prod);
		request.getSession().setAttribute(Constants.VISIT_HISTORY, visitHistory);
	}

	/**
	 * Hotsale.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param curPageNO
	 *            the cur page no
	 * @param newsCategory
	 *            the news category
	 * @return the string
	 */
	@RequestMapping("/hotsale")
	public String hotsale(HttpServletRequest request, HttpServletResponse response,String curPageNO,String newsCategory) {
		String shopName = getShopName(request, response);
		List<Product> hotsaleList = productService.getHotSale(shopName);
		if (AppUtils.isNotBlank(hotsaleList)) {
			request.setAttribute("hotsaleList", hotsaleList);
		}
		return PathResolver.getPath(FrontPage.HOTSALE);
	}

	/**
	 * Hoton.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param curPageNO
	 *            the cur page no
	 * @param newsCategory
	 *            the news category
	 * @return the string
	 */
	@RequestMapping("/hoton")
	public String hoton(HttpServletRequest request, HttpServletResponse response,String curPageNO,String newsCategory) {
		String sortId = request.getParameter("sortId");
		List<Product> hotonList = productService.getHotOn(sortId);
		if (AppUtils.isNotBlank(hotonList)) {
			request.setAttribute("hotonList", hotonList);
		}
		return PathResolver.getPath(FrontPage.HOTON);
	}
	
	/**
	 * Hotview.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping("/hotview")
	public String hotview(HttpServletRequest request, HttpServletResponse response) {
		String shopName = getShopName(request, response);
		List<Product> hotViewList = productService.getHotViewProd(shopName, 10);
		if (AppUtils.isNotBlank(hotViewList)) {
			request.setAttribute("hotViewList", hotViewList);
		}
		return PathResolver.getPath(FrontPage.HOTVIEW);
	}
	
	/**
	 * Product gallery.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param prodId
	 *            the prod id
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping("/productGallery/{prodId}")
	public String productGallery(HttpServletRequest request, HttpServletResponse response, @PathVariable Long prodId) throws Exception {
		if (AppUtils.isBlank(prodId)) {
			return PathResolver.getPath(FowardPage.INDEX_QUERY);
		}
		return productService.getProductGallery(request, response,prodId);
	}
}
