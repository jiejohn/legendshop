package com.legendshop.spi.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Caching(evict = { 
		@CacheEvict(value = "ShopDetailView", key="#shopDetail.userName"), 
		@CacheEvict(value = "ShopDetailView", key= "'DM_' + #shopDetail.domainName")
		})
public @interface ShopDetailUpdate {

}
