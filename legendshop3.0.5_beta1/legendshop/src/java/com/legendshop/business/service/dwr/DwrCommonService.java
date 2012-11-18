/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.dwr;

import java.util.List;
import java.util.Map;

import com.legendshop.model.dynamic.Item;
import com.legendshop.model.dynamic.Model;
import com.legendshop.model.entity.Indexjpg;

/**
 * The Interface DwrCommonService.
 */
public interface DwrCommonService {

	/**
	 * Gets the session id.
	 * 
	 * @return the session id
	 */
	public abstract String getSessionId();

	/**
	 * Gets the index jpg.
	 * 
	 * @param userName
	 *            the user name
	 * @return the index jpg
	 */
	public abstract List<Indexjpg> getIndexJpg(String userName);

	/**
	 * Delete sub.
	 * 
	 * @param subId
	 *            the sub id
	 * @param userName
	 *            the user name
	 * @return the string
	 */
	public abstract String deleteSub(Long subId, String userName);

	// status，参考OrderStatusEnum
	/**
	 * Update sub.
	 * 
	 * @param subId
	 *            the sub id
	 * @param status
	 *            the status
	 * @param userName
	 *            the user name
	 * @return the string
	 */
	public abstract String updateSub(Long subId, Integer status, String userName);

	/**
	 * Admin delete sub.
	 * 
	 * @param subId
	 *            the sub id
	 * @return the string
	 */
	public abstract String adminDeleteSub(Long subId);

	/**
	 * Admin change sub price.
	 * 
	 * @param subId
	 *            the sub id
	 * @param totalPrice
	 *            the total price
	 * @return the string
	 */
	public abstract String adminChangeSubPrice(Long subId, String totalPrice);

	/**
	 * 删除商品.
	 * 
	 * @param prodId
	 *            the prod id
	 * @return the string
	 */
	public abstract String deleteProduct(Long prodId);

	/**
	 * 商品上线.
	 * 
	 * @param prodId
	 *            the prod id
	 * @return the string
	 */
	public abstract String productTurnOn(Long prodId);

	/**
	 * 商品相关图片上线.
	 * 
	 * @param fileId
	 *            the file id
	 * @return the string
	 */
	public abstract String imgFileOnline(Long fileId);

	/**
	 * 商品下线.
	 * 
	 * @param prodId
	 *            the prod id
	 * @return the string
	 */
	public abstract String productTurnOff(Long prodId);

	/**
	 * 商品下线.
	 * 
	 * @param fileId
	 *            the file id
	 * @return the string
	 */
	public abstract String imgFileOffline(Long fileId);


	/**
	 * 客户是否存在,Email是否存在 1: 客户已经存在 2：Email已经存在 0: Normal.
	 * 
	 * @param userName
	 *            the user name
	 * @return true, if is user exist
	 */
	public abstract boolean isUserExist(String userName);

	/**
	 * Checks if is email exist.
	 * 
	 * @param email
	 *            the email
	 * @return true, if is email exist
	 */
	public abstract boolean isEmailExist(String email);

	/**
	 * 删除用户.
	 * 
	 * @param userId
	 *            the user id
	 * @param userName
	 *            the user name
	 * @return the string
	 */
	public abstract String deleteUserDetail(String userId, String userName);

	/**
	 * Load dynamic attribute.
	 * 
	 * @param prodId
	 *            the prod id
	 * @param userName
	 *            the user name
	 * @return the list
	 */
	public abstract List<Model> loadDynamicAttribute(Long prodId, String userName);

	/**
	 * Load dynamic attribute from temp.
	 * 
	 * @param tempId
	 *            the temp id
	 * @param userName
	 *            the user name
	 * @return the list
	 */
	public abstract List<Model> loadDynamicAttributeFromTemp(Long tempId, String userName);

	/**
	 * Save dynamic.
	 * 
	 * @param prodId
	 *            the prod id
	 * @param userName
	 *            the user name
	 * @param type
	 *            the type
	 * @param model
	 *            the model
	 * @return true, if successful
	 */
	public abstract boolean saveDynamic(Long prodId, String userName, Short type, Model[] model);

	/**
	 * Save dynamic temp.
	 * 
	 * @param tempName
	 *            the temp name
	 * @param userName
	 *            the user name
	 * @param type
	 *            the type
	 * @param model
	 *            the model
	 * @return true, if successful
	 */
	public abstract boolean saveDynamicTemp(String tempName, String userName, Short type, Model[] model);

	/**
	 * Update dynamic temp.
	 * 
	 * @param tempId
	 *            the temp id
	 * @param userName
	 *            the user name
	 * @param type
	 *            the type
	 * @param model
	 *            the model
	 * @return true, if successful
	 */
	public abstract boolean updateDynamicTemp(Long tempId, String userName, Short type, Model[] model);

	/**
	 * Delete dynamic temp.
	 * 
	 * @param tempId
	 *            the temp id
	 * @param userName
	 *            the user name
	 * @return true, if successful
	 */
	public abstract boolean deleteDynamicTemp(Long tempId, String userName);

	/**
	 * Adds the my league.
	 * 
	 * @param userName
	 *            the user name
	 * @param shopName
	 *            the shop name
	 * @param siteName
	 *            the siteName
	 * @return the integer
	 */
	public abstract Integer addMyLeague(String userName, String shopName, String siteName);

	/**
	 * 回复留言.
	 * 
	 * @param id
	 *            the id
	 * @param answer
	 *            the answer
	 * @return the string
	 */
	public abstract String answerWord(Long id, String answer);

	/**
	 * 生成加密后的随机数.
	 * 
	 * @return the map
	 */
	public abstract Map<String, String> changeRandImg();
	
	/**
	 * Validate rand img.
	 * 
	 * @return true, if successful
	 */
	public abstract boolean validateRandImg(String validateCodeParameter);

	/**
	 * 审核商城.
	 * 
	 * @param loginUserName
	 *            the login user name
	 * @param userName
	 *            商城名称
	 * @param shopId
	 *            the shop id
	 * @param status
	 *            状态,是否上线1：在线，0下线，-1审核中,-2拒绝,-3关闭（管理员操作）
	 * @return the string
	 */
	public abstract String auditShop(String loginUserName, String userName, Long shopId, Integer status);

	/**
	 * Gets the usable brand.
	 * 
	 * @param nsortId
	 *            the nsort id
	 * @param userName
	 *            the user name
	 * @return the usable brand
	 */
	public abstract List<Item> getUsableBrand(Long nsortId, String userName);

	/**
	 * Gets the usable brand by name.
	 * 
	 * @param nsortId
	 *            the nsort id
	 * @param userName
	 *            the user name
	 * @param brandName
	 *            the brand name
	 * @return the usable brand by name
	 */
	public abstract List<Item> getUsableBrandByName(Long nsortId, String userName, String brandName);

	/**
	 * Gets the used brand.
	 * 
	 * @param nsortId
	 *            the nsort id
	 * @param userName
	 *            the user name
	 * @return the used brand
	 */
	public abstract List<Item> getUsedBrand(Long nsortId, String userName);

	/**
	 * Gets the used product item.
	 * 
	 * @param prodId
	 *            the prod id
	 * @param userName
	 *            the user name
	 * @return the used product item
	 */
	public abstract List<Item> getUsedProductItem(Long prodId, String userName);

	/**
	 * Gets the usable product item by name.
	 * 
	 * @param prodId
	 *            the prod id
	 * @param userName
	 *            the user name
	 * @param prodName
	 *            the prod name
	 * @return the usable product item by name
	 */
	public abstract List<Item> getUsableProductItemByName(Long prodId, String userName, String prodName);

	/**
	 * Gets the usable product item.
	 * 
	 * @param prodId
	 *            the prod id
	 * @param userName
	 *            the user name
	 * @return the usable product item
	 */
	public abstract List<Item> getUsableProductItem(Long prodId, String userName);

	/**
	 * Save brand item.
	 * 
	 * @param idJson
	 *            the id json
	 * @param nameJson
	 *            the name json
	 * @param nsortId
	 *            the nsort id
	 * @param userName
	 *            the user name
	 * @return the string
	 */
	public abstract String saveBrandItem(String idJson, String nameJson, Long nsortId, String userName);

	/**
	 * Save prod item.
	 * 
	 * @param idJson
	 *            the id json
	 * @param nameJson
	 *            the name json
	 * @param prodId
	 *            the prod id
	 * @param userName
	 *            the user name
	 * @return the string
	 */
	public abstract String saveProdItem(String idJson, String nameJson, Long prodId, String userName);

	// 加入购物车， 用户登录后保存到数据库中，没有登录先保存到session

	/**
	 * Addtocart.
	 *
	 * @param prodId the prod id
	 * @param count the count
	 * @param attribute the attribute
	 * @return the map
	 */
	public Map<String, Object> addtocart(Long prodId, Integer count, String attribute);

	// 更新订单的支付类型，方便对方接口返回数据查询，subId：订单ID，payId：支付方式ID
	/**
	 * Pay to order.
	 * 
	 * @param subId
	 *            the sub id
	 * @param shopName
	 *            the shop name
	 * @param payTypeId
	 *            the pay type id
	 */
	public abstract void payToOrder(Long subId, String shopName, Integer payTypeId);

	// 重置密码
	/**
	 * Reset password.
	 * 
	 * @param userName
	 *            the user name
	 * @param mail
	 *            the mail
	 * @return the string
	 */
	public abstract String resetPassword(String userName, String mail);

	/**
	 * Adds the prodcut comment.
	 * 
	 * @param prodId
	 *            the prod id
	 * @param content
	 *            the content
	 * @return the string
	 */
	public abstract String addProdcutComment(Long prodId, String content);

	/**
	 * Gets the product comment.
	 * 
	 * @param prodId
	 *            the prod id
	 * @param curPageNO
	 *            the cur page no
	 * @return the product comment
	 */
	public abstract String getProductComment(Long prodId, String curPageNO);

	/**
	 * Query parameter.
	 * 
	 * @param prodId
	 *            the prod id
	 * @return the string
	 */
	public abstract String queryParameter(Long prodId);

	/**
	 * Delete file.
	 * 
	 * @param filePath
	 *            the file path
	 * @return the integer
	 */
	public abstract Integer deleteFile(String filePath);

	/**
	 * User score.
	 * 
	 * @param subId
	 *            the sub id
	 * @param score
	 *            the score
	 * @return the map
	 */
	public abstract Map<String, Object> userScore(Long subId, Long score);

	/**
	 * Cal money.
	 * 
	 * @param score
	 *            the score
	 * @return the double
	 */
	public abstract Double calMoney(Long score);

}