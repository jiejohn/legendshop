/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.controller;

import java.io.Serializable;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.Ehcache;
import net.spy.memcached.MemcachedClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.legendshop.business.common.page.BackPage;
import com.legendshop.business.common.page.FowardPage;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.cache.LegendCache;
import com.legendshop.core.cache.MemCachedManager;
import com.legendshop.core.cache.MemcachedCache;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.model.KeyValueEntity;
import com.legendshop.util.AppUtils;

/**
 * 系统缓存控制器.
 */
@Controller
@RequestMapping("/system/cache")
public class SecondLevelCacheController extends BaseController {
	
	@Autowired
	private CacheManager cacheManager;

	/**
	 * Query.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param curPageNO
	 *            the cur page no
	 * @param systemParameter
	 *            the system parameter
	 * @return the string
	 */
	@RequestMapping("/query")
	public String query(HttpServletRequest request, HttpServletResponse response) {
		List<KeyValueEntity> cacheList = null;
		if(cacheManager instanceof MemCachedManager){
			cacheList = parseMemcache(cacheManager);
		}else{
			cacheList = parseEhcache(cacheManager);
		}
		request.setAttribute("cacheList", cacheList);
		
		return PathResolver.getPath(request,response,BackPage.CACHE_LIST_PAGE);
	}
	
	private  List<KeyValueEntity> parseMemcache(CacheManager cacheManager){
		 List<KeyValueEntity> result = new ArrayList<KeyValueEntity>();
		Collection<String> cacheNames = cacheManager.getCacheNames();
		
		if(AppUtils.isNotBlank(cacheNames)){
			for (String cacheName : cacheNames) {
				MemcachedClient cache = ((MemcachedCache)cacheManager.getCache(cacheName)).getNativeCache();
				if(cache != null){
					Map<SocketAddress, Map<String,String>> map = cache.getStats();
					Set<SocketAddress> socketAddresses = map.keySet();
					Collection<Map<String,String>>  values = map.values();
					for(SocketAddress address: socketAddresses){
						KeyValueEntity entity = new KeyValueEntity();
						entity.setKey(cacheName + ":" + address.toString());
						   Map<String,String> valueMap =  map.get(address);
							Set<String> keyValue  = valueMap.keySet();
							if(keyValue != null){
								StringBuilder sb = new StringBuilder();
								for(String key: keyValue){
									String valueValue = valueMap.get(key);
									sb.append(valueValue).append(",");
								}
								entity.setValue(sb.toString());
							}
							result.add(entity);
							
					}
				}
			}
		}
		return result;
	}
	
	private List<KeyValueEntity> parseEhcache( CacheManager cacheManager){
		 List<KeyValueEntity> result = new ArrayList<KeyValueEntity>();
		Collection<String> cacheNames = cacheManager.getCacheNames();
		if(AppUtils.isNotBlank(cacheNames)){
			for (String cacheName : cacheNames) {
				KeyValueEntity entity = new KeyValueEntity();
				entity.setKey(cacheName);
				Ehcache cache = ((LegendCache)cacheManager.getCache(cacheName)).getNativeCache();
				List<Serializable> keys = cache.getKeys();
				if(AppUtils.isNotBlank(keys)){
					StringBuilder sb = new StringBuilder(keys.get(0).toString());
					for (int i = 1; i < keys.size(); i++) {
						sb.append(",").append(keys.get(i));
					}
					entity.setValue(sb.toString());
				}
				
				result.add(entity);
			}
		}
		return result;
	}

	/**
	 * Delete.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param id
	 *            the id
	 * @return the string
	 */
	@RequestMapping(value = "/clear")
	public String clear(HttpServletRequest request, HttpServletResponse response, @PathVariable
	String id) {
		return PathResolver.getPath(request,response,FowardPage.PARAM_LIST_QUERY);
	}

	

}
