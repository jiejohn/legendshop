/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.helper;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.LocaleResolver;

import com.legendshop.business.common.Constants;
import com.legendshop.business.common.ShopStatusEnum;
import com.legendshop.core.exception.ErrorCodes;
import com.legendshop.core.helper.Checker;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.model.UserMessages;
import com.legendshop.model.entity.ShopDetailView;

/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * ----------------------------------------------------------------------------
 * -------- 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * --------
 * ----------------------------------------------------------------------------
 * 
 * 官方网站：http://www.legendesign.net
 */
public class ShopStatusChecker implements Checker<ShopDetailView> {

	/** The log. */
	private static Logger log = LoggerFactory.getLogger(ShopStatusChecker.class);

	/** The locale resolver. */
	private LocaleResolver localeResolver;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.legendshop.core.helper.Checker#check(java.lang.Object,
	 * javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public boolean check(ShopDetailView shopDetail, HttpServletRequest request) {
		boolean result = true;
		Integer status = shopDetail.getStatus();
		if (!ShopStatusEnum.ONLINE.value().equals(status)) {
			String shopName = shopDetail.getStoreName();
			Locale locale = localeResolver.resolveLocale(request);
			if (Constants.OFFLINE.equals(status)) {
				log.warn("shop {} off line ", shopName);
				UserMessages uem = new UserMessages();
				uem.setTitle(ResourceBundleHelper.getString(locale, "shop.name") + shopName
						+ ResourceBundleHelper.getString(locale, "shop.is.closed"));
				uem.setDesc("Shop " + shopName + " have bean closed");
				uem.setCode(ErrorCodes.CLOSED);
				request.setAttribute(UserMessages.MESSAGE_KEY, uem);
			} else if (ShopStatusEnum.AUDITING.value().equals(status)) {
				log.warn("shop {} auditing ", shopName);
				UserMessages uem = new UserMessages();
				uem.setTitle(ResourceBundleHelper.getString(locale, "shop.name") + shopName
						+ ResourceBundleHelper.getString(locale, "shop.is.auditing"));
				uem.setDesc("Shop " + shopName + " is Auditing, Please wait.");
				uem.setCode(ErrorCodes.AUDITING);
				uem.addCallBackList(ResourceBundleHelper.getString(locale, "shop.my.shop"),
						ResourceBundleHelper.getString(locale, "lookup.shop.status"), "myaccount.do");
				request.setAttribute(UserMessages.MESSAGE_KEY, uem);
			}
			result = false;
		}
		return result;
	}

	/**
	 * Sets the locale resolver.
	 * 
	 * @param localeResolver
	 *            the new locale resolver
	 */
	public void setLocaleResolver(LocaleResolver localeResolver) {
		this.localeResolver = localeResolver;
	}

}
