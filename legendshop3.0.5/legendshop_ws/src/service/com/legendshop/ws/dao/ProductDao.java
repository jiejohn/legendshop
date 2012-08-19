package com.legendshop.ws.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.ws.model.Product;

/**
 * @author gmhwq
 * 
 */
public interface ProductDao extends BaseDao{

	public String importProductService(Product product);
}
