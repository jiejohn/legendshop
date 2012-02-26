/**
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.legendshop.model.entity.ProductDetail;
import com.legendshop.util.AppUtils;
import com.legendshop.util.sql.ConfigCode;


/**
 * 产品Dao.
 */
public class ProductJdbcDaoImpl extends ProductDaoImpl {
	
	/** The jdbc template. */
	private JdbcTemplate jdbcTemplate;
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(ProductJdbcDaoImpl.class);


	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ProductDao#getProdDetail(java.lang.Long)
	 */
	@Override
	public ProductDetail getProdDetail(final Long prodId) {
		List<ProductDetail> list= null;
		list = jdbcTemplate.query(ConfigCode.getInstance().getCode("biz.getProdDetail"),new Object[] { prodId }, new ProductDetailRowMapper());
		if(AppUtils.isBlank(list)){
			return null;
		}
		return list.get(0);
	}

	
	/**
	 * The Class ProductDetailRowMapper.
	 */
	class ProductDetailRowMapper implements RowMapper<ProductDetail>{
		
		/* (non-Javadoc)
		 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
		 */
		@Override
		public ProductDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
			ProductDetail product = new ProductDetail();
			product.setProdId(rs.getLong("prod_id"));
			product.setSortId(rs.getLong("sort_id"));
			product.setNsortId(rs.getLong("nsort_id"));
			product.setSubNsortId(rs.getLong("sub_nsort_id"));
			product.setName(rs.getString("name"));
			product.setPrice(rs.getDouble("price"));
			product.setCash(rs.getDouble("cash"));
			product.setProxyPrice(rs.getDouble("proxy_price"));
			product.setCarriage(rs.getDouble("carriage"));
			product.setBrief(rs.getString("brief"));
			product.setContent(rs.getString("content"));
			product.setViews(rs.getInt("views"));
			product.setBuys(rs.getInt("buys"));
			product.setRecDate(rs.getDate("rec_date"));
			product.setPic(rs.getString("pic"));
			product.setCommend(rs.getString("commend"));
			product.setStatus(rs.getInt("status"));
			product.setModifyDate(rs.getDate("modify_date"));
			product.setUserId(rs.getString("user_id"));
			product.setUserName(rs.getString("user_name"));
			product.setStartDate(rs.getDate("start_date"));
			product.setEndDate(rs.getDate("end_date"));
			product.setStocks(rs.getInt("stocks"));
			product.setProdType(rs.getString("prod_type"));
			product.setKeyWord(rs.getString("key_word"));
			product.setAttribute(rs.getString("attribute"));
			product.setParameter(rs.getString("parameter"));
			product.setBrandId(rs.getLong("brand_id"));
			product.setSortName(rs.getString("sort_name"));
			product.setNsortName(rs.getString("nsort_name"));
			product.setSubNsortName(rs.getString("sub_nsort_name"));
			product.setBrandName(rs.getString("brand_name"));
			return product;
		}
	}

	/**
	 * Sets the jdbc template.
	 * 
	 * @param jdbcTemplate
	 *            the new jdbc template
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	

}



