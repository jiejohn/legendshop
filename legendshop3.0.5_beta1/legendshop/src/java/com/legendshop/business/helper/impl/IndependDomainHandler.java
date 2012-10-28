package com.legendshop.business.helper.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.legendshop.business.helper.AbstractHandler;
import com.legendshop.core.helper.Handler;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.core.service.ShopService;
import com.legendshop.util.AppUtils;
/**
 * 
 * 独立域名
 *
 */
public class IndependDomainHandler extends AbstractHandler implements Handler {
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(IndependDomainHandler.class);
	private ShopService shopService;
	private boolean  supportIndepend = PropertiesUtil.isSupportIndependDomainName();
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(supportIndepend){
     		String serverName = request.getServerName();
		   // String uri = request.getRequestURI();
			log.info("incoming serverName = {} ,remoteAddr = {},   X-Real-IP = {}" , new Object[]{serverName, request.getRemoteAddr(), request.getHeader("X-Real-IP") } );
			String domainName = resolveDomainName(serverName);
			if(AppUtils.isNotBlank(domainName)){
				String shopName = shopService.getShopNameByDomain(domainName);
				if(AppUtils.isNotBlank(shopName)){
					log.debug("processing domainName {}, shopName {}", domainName, shopName);
					ThreadLocalContext.setCurrentShopName(request, response, shopName);
				}
			}
		}
	}

	private String resolveDomainName(String serverName){
		if(AppUtils.isBlank(serverName)){
			return null;
		}
		if(serverName.toLowerCase().startsWith("www.")){
			return serverName.substring(4);
		}
		return serverName;
	}

	public void setShopService(ShopService shopService) {
		this.shopService = shopService;
	}
	
	public static void main(String[] args) {
		String serverName = "www.legendshop.cn";
		System.out.println(serverName.substring(4));
	}

}
