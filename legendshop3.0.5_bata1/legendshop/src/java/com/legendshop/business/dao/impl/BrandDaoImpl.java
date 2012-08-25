/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.legendshop.business.dao.BrandDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.model.dynamic.Item;
import com.legendshop.model.entity.Brand;
import com.legendshop.model.entity.NsortBrand;
import com.legendshop.model.entity.NsortBrandId;
import com.legendshop.util.AppUtils;

/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * ------------------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ------------------------------------------------------------------------------------
 * 
 * 官方网站：http://www.legendesign.net
 */

@SuppressWarnings("unchecked")
public class BrandDaoImpl extends BaseDaoImpl implements BrandDao {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(BrandDaoImpl.class);

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.BrandDao#getUsableBrand(java.lang.Long, java.lang.String)
	 */

	@Override
	public List<Item> getUsableBrand(Long nsortId, String userName) {
		log.debug("getUsableBrand nsortId = {},userName = {} ", nsortId, userName);
		return findByHQLLimit(
				"select new com.legendshop.model.dynamic.Item(b.brandId , b.brandName) from Brand b where not exists ( select n.userName from NsortBrand n where b.brandId = n.id.brandId and  n.id.nsortId = ? ) and b.userName = ? ",
				0, 30, new Object[] { nsortId, userName });
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.BrandDao#getUsableBrandByName(java.lang.Long, java.lang.String, java.lang.String)
	 */
	@Override
	public List<Item> getUsableBrandByName(Long nsortId, String userName, String brandName) {
		return findByHQLLimit(
				"select new com.legendshop.model.dynamic.Item(b.brandId , b.brandName) from Brand b where brandName like ? and not exists ( select n.userName from NsortBrand n where b.brandId = n.id.brandId and  n.id.nsortId = ? ) and b.userName = ? ",
				0, 30, brandName, nsortId, userName);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.BrandDao#getUsedBrand(java.lang.Long, java.lang.String)
	 */
	@Override
	public List<Item> getUsedBrand(Long nsortId, String userName) {
		return findByHQLLimit(
				"select new com.legendshop.model.dynamic.Item(b.brandId , b.brandName) from Brand b where exists ( select n.userName from NsortBrand n where b.brandId = n.id.brandId and  n.id.nsortId = ? )  and b.userName = ?",
				0, 30, nsortId, userName);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.BrandDao#getUsedProd(java.lang.Long, java.lang.String)
	 */
	@Override
	public List<Item> getUsedProd(Long prodId, String userName) {
		return findByHQLLimit(
				"select new com.legendshop.model.dynamic.Item(b.prodId , b.name) from Product b where b.userName = ? and exists ( select n.userName from RelProduct n where b.prodId = n.relProdId and  n.prodId = ? )",
				0, 30, userName, prodId);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.BrandDao#getUsableProductItemByName(java.lang.Long, java.lang.String, java.lang.String)
	 */
	@Override
	public List<Item> getUsableProductItemByName(Long prodId, String userName, String prodName) {
		return findByHQLLimit(
				"select new com.legendshop.model.dynamic.Item(b.prodId , b.name) from Product b where name like ? and b.userName = ? and not exists ( select n.userName from RelProduct n where b.prodId = n.relProdId and  n.prodId = ?)",
				0, 30, prodName, userName, prodId);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.BrandDao#getUsableProductItem(java.lang.Long, java.lang.String)
	 */
	@Override
	public List<Item> getUsableProductItem(Long prodId, String userName) {
		return findByHQLLimit(
				"select new com.legendshop.model.dynamic.Item(b.prodId , b.name) from Product b where not exists ( select n.userName from RelProduct n where b.prodId = n.relProdId and  n.prodId = ?  ) and b.userName = ?",
				0, 30, new Object[] { prodId, userName });
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.BrandDao#saveBrandItem(java.util.List, java.lang.Long, java.lang.String)
	 */
	@Override
	public  String saveBrandItem(List<String> idList, Long nsortId, String userName) {
		// delete all
		List<NsortBrand> list = find("from NsortBrand n where n.id.nsortId = ? and userName = ?", nsortId,
				userName);
		deleteAll(list);
		if (AppUtils.isNotBlank(idList)) {
			for (String brandId : idList) {
				NsortBrand nb = new NsortBrand();
				NsortBrandId id = new NsortBrandId();
				id.setBrandId(Long.valueOf(brandId));
				id.setNsortId(nsortId);
				nb.setId(id);
				nb.setUserName(userName);
				save(nb);
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.BrandDao#deleteBrandById(java.lang.Long)
	 */
	@Override
	public void deleteBrandById(Long id) {
		deleteById(Brand.class, id);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.BrandDao#updateBrand(com.legendshop.model.entity.Brand)
	 */
	@Override
	public void updateBrand(Brand brand) {
		update(brand);
	}

}
