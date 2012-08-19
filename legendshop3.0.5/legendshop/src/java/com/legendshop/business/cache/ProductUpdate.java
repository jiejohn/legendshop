package com.legendshop.business.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Caching(evict = { 
		@CacheEvict(value = "ProductDetail", key="#product.prodId"), 
		@CacheEvict(value = "Product", key="#product.prodId")
		})
public @interface ProductUpdate {

}
