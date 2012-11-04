package com.legendshop.spi.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.cache.annotation.CacheEvict;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@CacheEvict(value = "ProductDetail", key="#product.prodId")
public @interface ProductUpdate {

}
