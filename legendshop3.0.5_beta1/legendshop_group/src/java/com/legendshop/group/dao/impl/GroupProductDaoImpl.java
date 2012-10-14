/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.group.dao.impl;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.legendshop.core.OperationTypeEnum;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.event.impl.FireEvent;
import com.legendshop.event.EventHome;
import com.legendshop.group.dao.GroupProductDao;
import com.legendshop.model.entity.GroupProduct;
import com.legendshop.model.entity.Product;
import com.legendshop.util.AppUtils;

/**
 * The Class GroupProductDaoImpl.
 */
public class GroupProductDaoImpl extends BaseDaoImpl implements GroupProductDao {

	/* (non-Javadoc)
	 * @see com.legendshop.group.dao.GroupProductDao#getProductById(java.lang.Long)
	 */
	@Override
	public Product getProductById(Long prodId) {
		return get(Product.class, prodId);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.group.dao.GroupProductDao#updateProduct(com.legendshop.model.entity.GroupProduct)
	 */
	@Override
	@CacheEvict(value = "Product", key = "#product.prodId")
	public void updateProduct(GroupProduct product) {
		EventHome.publishEvent(new FireEvent(product, OperationTypeEnum.SAVE));
		update(product);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.group.dao.GroupProductDao#saveProduct(com.legendshop.model.entity.GroupProduct)
	 */
	@Override
	public void saveProduct(GroupProduct product) {
		EventHome.publishEvent(new FireEvent(product, OperationTypeEnum.SAVE));
		save(product);
	}
	
	/* (non-Javadoc)
	 * @see com.legendshop.group.dao.GroupProductDao#getGroupProduct(java.lang.Long)
	 */
	@Override
	@Cacheable(value="Product",key="#prodId")
	public GroupProduct getGroupProduct(Long prodId){
		//团购产品
		String strHQL = "select p,g from Product p, GroupProduct g where p.prodId = g.prodId and p.prodId = ? and p.prodType = 'G'";
		List<Object[]> list = findByHQL(strHQL, prodId);
		if(AppUtils.isNotBlank(list)){
			Product p = (Product)list.get(0)[0];
			GroupProduct g = (GroupProduct)list.get(0)[1];
			g.setProduct(p);
			return g;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.group.dao.GroupProductDao#deleteProduct(java.lang.Long)
	 */
	@Override
	@CacheEvict(value = "Product", key = "#prodId")
	public void deleteProduct(Long prodId) {
		GroupProduct product = getGroupProduct(prodId);
		if(product != null){
			EventHome.publishEvent(new FireEvent(product, OperationTypeEnum.SAVE));
			delete(product);
		}
	}

}
