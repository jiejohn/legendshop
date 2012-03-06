/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.legendshop.business.dao.ShopDetailDao;
import com.legendshop.model.entity.ShopDetailView;
import com.legendshop.util.AppUtils;
import com.legendshop.util.sql.ConfigCode;

/**
 * ShopDetail Jdbc implement.
 */
public class ShopDetailJdbcDaoImpl extends ShopDetailDaoImpl implements ShopDetailDao {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(ShopDetailJdbcDaoImpl.class);

	/** The jdbc template. */
	private JdbcTemplate jdbcTemplate;

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ShopDetailDao#getSimpleInfoShopDetail(java.lang.String)
	 */
	@Override
	public ShopDetailView getSimpleInfoShopDetail(final String storeName) {
		List<ShopDetailView> list= null;
		list = jdbcTemplate.query(ConfigCode.getInstance().getCode("biz.getShopDetailView"),new Object[] { storeName }, new ShopDetailRowMapper());
		if(AppUtils.isBlank(list)){
			return null;
		}
		return list.get(0);
	}
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ShopDetailDaoImpl#getShopDetail(java.lang.Long[])
	 */
	@Override
	public List<ShopDetailView> getShopDetail(Long[] shopId) {
		List<Long> postIdList = new ArrayList<Long>();
		StringBuffer sb = new StringBuffer(ConfigCode.getInstance().getCode("biz.getShopDetailViewList"));
		for (int i = 0; i < shopId.length - 1; i++) {
			if (shopId[i] != null) {
				sb.append("?,");
				postIdList.add(shopId[i]);
			}
		}
		if (postIdList.size() == 0) {
			return new ArrayList<ShopDetailView>();
		}
		sb.setLength(sb.length() - 1);
		sb.append(")");
		return jdbcTemplate.query(sb.toString(),postIdList.toArray(), new ShopDetailRowMapper());
	}
	
	/**
	 * The Class ShopDetailRowMapper.
	 */
	class ShopDetailRowMapper implements RowMapper<ShopDetailView>{
		
		/* (non-Javadoc)
		 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
		 */
		@Override
		public ShopDetailView mapRow(ResultSet rs, int rowNum) throws SQLException {
			ShopDetailView shopDetail = new ShopDetailView();
			shopDetail.setShopId(rs.getLong("shop_id"));
			shopDetail.setUserId(rs.getString("user_id"));
			shopDetail.setWeb(rs.getString("web"));
			shopDetail.setSitename(rs.getString("sitename"));
			shopDetail.setMaddr(rs.getString("maddr"));
			shopDetail.setMsn(rs.getString("msn"));
			shopDetail.setMname(rs.getString("mname"));
			shopDetail.setCode(rs.getString("code"));
			shopDetail.setYmaddr(rs.getString("ymaddr"));
			shopDetail.setYmname(rs.getString("ymname"));
			shopDetail.setStatus(rs.getInt("status"));
			shopDetail.setStoreName(rs.getString("store_name"));
			shopDetail.setVisitTimes(rs.getInt("visit_times"));
			shopDetail.setAddtime(rs.getDate("addtime"));
			shopDetail.setBriefDesc(rs.getString("brief_desc"));
			shopDetail.setDetailDesc(rs.getString("detail_desc"));
			shopDetail.setShopPic(rs.getString("shop_pic"));
			shopDetail.setColorStyle(rs.getString("color_style"));
			shopDetail.setLangStyle(rs.getString("lang_style"));
			shopDetail.setGradeId(rs.getInt("grade_id"));
			shopDetail.setType(rs.getInt("type"));
			shopDetail.setIdCardPic(rs.getString("id_card_pic"));
			shopDetail.setTrafficPic(rs.getString("traffic_pic"));
			shopDetail.setIdCardNum(rs.getString("id_card_num"));
			shopDetail.setCreateCountryCode(rs.getString("create_country_code"));
			shopDetail.setCreateAreaCode(rs.getString("create_area_code"));
			shopDetail.setProvinceid(rs.getInt("provinceid"));
			shopDetail.setCityid(rs.getInt("cityid"));
			shopDetail.setAreaid(rs.getInt("areaid"));
			shopDetail.setProvince(rs.getString("province"));
			shopDetail.setCity(rs.getString("city"));
			shopDetail.setArea(rs.getString("area"));
			shopDetail.setUserTel(rs.getString("user_tel"));
			shopDetail.setNickName(rs.getString("nick_name"));
			shopDetail.setUserMobile(rs.getString("user_mobile"));
			shopDetail.setQq(rs.getString("qq"));
			shopDetail.setMsn(rs.getString("msnNumber"));
			shopDetail.setUserPostcode(rs.getString("user_postcode"));
			shopDetail.setFax(rs.getString("fax"));
			
			return shopDetail;
		}
		
	}

	/**
	 * Sets the jdbc template.
	 * 
	 * @param jdbcTemplate
	 *            the new jdbc template
	 */
	@Required
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
