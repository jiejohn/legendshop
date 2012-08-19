package com.legendshop.ws.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.ws.Constants;
import com.legendshop.ws.model.Product;

public class ProductDaoImpl extends BaseDaoImpl implements ProductDao {
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(ProductDaoImpl.class);

	@Override
	public String importProductService(Product product) {
		log.debug("save product name = {}, userName = {}",product.getName(),product.getUserName());
		try {
			save(product);
			return Constants.STATUS_OK;
		} catch (Exception e) {
			return Constants.STATUS_FAILED;
		}
		
	}


}
