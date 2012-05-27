package com.legendshop.spi.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.legendshop.model.entity.Product;

public interface GroupService extends BaseService{
	public String index(HttpServletRequest request, HttpServletResponse response,String curPageNO,String order,String seq,Product product);
	
	public String view(HttpServletRequest request, HttpServletResponse response,Long id) ;
}
