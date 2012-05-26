/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.dwr.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.servlet.LocaleResolver;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.business.common.DynamicPropertiesHelper;
import com.legendshop.business.dao.BasketDao;
import com.legendshop.business.dao.BrandDao;
import com.legendshop.business.dao.BusinessDao;
import com.legendshop.business.dao.ImgFileDao;
import com.legendshop.business.dao.ProductDao;
import com.legendshop.business.dao.ShopDetailDao;
import com.legendshop.business.dao.SubDao;
import com.legendshop.business.dao.UserDetailDao;
import com.legendshop.business.helper.PageGengrator;
import com.legendshop.business.search.facade.ProductSearchFacade;
import com.legendshop.business.service.AdminService;
import com.legendshop.business.service.PayTypeService;
import com.legendshop.business.service.ScoreService;
import com.legendshop.business.service.ValidateCodeUsernamePasswordAuthenticationFilter;
import com.legendshop.business.service.dwr.DwrCommonService;
import com.legendshop.core.UserManager;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.constant.ProductTypeEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.ApplicationException;
import com.legendshop.core.exception.BusinessException;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.helper.FileProcessor;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.RealPathUtil;
import com.legendshop.core.randing.CaptchaServiceSingleton;
import com.legendshop.core.randing.RandomStringUtils;
import com.legendshop.core.service.GroupProductService;
import com.legendshop.model.dynamic.Item;
import com.legendshop.model.dynamic.Model;
import com.legendshop.model.entity.Basket;
import com.legendshop.model.entity.DynamicTemp;
import com.legendshop.model.entity.ImgFile;
import com.legendshop.model.entity.Indexjpg;
import com.legendshop.model.entity.Myleague;
import com.legendshop.model.entity.PayType;
import com.legendshop.model.entity.Product;
import com.legendshop.model.entity.ProductComment;
import com.legendshop.model.entity.ProductDetail;
import com.legendshop.model.entity.RelProduct;
import com.legendshop.model.entity.ShopDetail;
import com.legendshop.model.entity.Sub;
import com.legendshop.spi.constants.Constants;
import com.legendshop.spi.constants.MyLeagueEnum;
import com.legendshop.util.AppUtils;
import com.legendshop.util.Arith;
import com.legendshop.util.ContextServiceLocator;
import com.legendshop.util.converter.ByteConverter;
import com.legendshop.util.des.DES2;

/**
 * DWR服务入口.
 */
public class DwrCommonServiceImpl implements DwrCommonService {

	/** The log. */
	private static Logger log = LoggerFactory.getLogger(DwrCommonServiceImpl.class);

	/** The business dao. */
	private BusinessDao businessDao;

	/** The admin service. */
	private AdminService adminService;

	/** The user detail dao. */
	private UserDetailDao userDetailDao;

	/** The shop detail dao. */
	private ShopDetailDao shopDetailDao;

	/** The pay type service. */
	private PayTypeService payTypeService;

	/** The product search facade. */
	private ProductSearchFacade productSearchFacade;

	/** The score service. */
	private ScoreService scoreService;

	/** The basket dao. */
	private BasketDao basketDao;

	/** The img file dao. */
	private ImgFileDao imgFileDao;

	/** The brand dao. */
	private BrandDao brandDao;

	/** The product dao. */
	private ProductDao productDao;

	/** The sub dao. */
	private SubDao subDao;

	/** The locale resolver. */
	private LocaleResolver localeResolver;
	
	private GroupProductService groupProductService;

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#getSessionId()
	 */
	@Override
	public String getSessionId() {
		WebContext webContext = WebContextFactory.get();
		// System.out.println(webContext.getSession().getId());
		Random random = new Random();
		String sessionId = "<b>暂无评论</b>";
		webContext.getHttpServletRequest().setAttribute("sessionId", sessionId);

		return sessionId;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#getIndexJpg(java.lang.String)
	 */
	@Override
	public List<Indexjpg> getIndexJpg(String userName) {

		return imgFileDao.getIndexJpeg(userName);

	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#deleteSub(java.lang.Long, java.lang.String)
	 */
	@Override
	public String deleteSub(Long subId, String userName) {
		log.debug("userName {} delete sub {}", userName, subId);
		return adminDeleteSub(subId);

	}

	// status，参考OrderStatusEnum
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#updateSub(java.lang.Long, java.lang.Integer, java.lang.String)
	 */
	@Override
	public String updateSub(Long subId, Integer status, String userName) {
		if (userName == null) {
			return Constants.FAIL;
		}
		Sub sub = subDao.getSubById(subId);
		if(sub == null){
			return Constants.FAIL;
		}
		if (!sub.getUserName().equals(userName) && !sub.getShopName().equals(userName)) { // 只有卖家和买家才可以更新订单状态
			if (!CommonServiceUtil.haveViewAllDataFunction(WebContextFactory.get().getHttpServletRequest())) {
				log.warn("updateSub:userName {} or shopName {} not equal userName {}", new Object[] {
						sub.getUserName(), sub.getShopName(), userName });
				return Constants.FAIL;
			}
		}
		boolean result = subDao.updateSub(sub, status, userName);
		if (result) {
			return null;
		} else {
			return Constants.FAIL;
		}

	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#adminDeleteSub(java.lang.Long)
	 */
	@Override
	public String adminDeleteSub(Long subId) {
		if (subId == null) {
			return "fail";
		}
		Sub sub = businessDao.get(Sub.class, subId);
		// 检查权限
		String userName = UserManager.getUsername(WebContextFactory.get().getSession());
		if (!CommonServiceUtil.haveViewAllDataFunction(WebContextFactory.get().getHttpServletRequest())) {
			if (!sub.getUserName().equals(userName)) {
				log.warn("{}, can not delete sub {} does not belongs to you", userName, subId);
				return "fail";
			}
		}

		boolean result = subDao.deleteSub(sub);
		if (result) {
			return null;
		} else {
			return "fail";
		}

	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#adminChangeSubPrice(java.lang.Long, java.lang.String)
	 */
	@Override
	public String adminChangeSubPrice(Long subId, String totalPrice) {
		if (subId == null || totalPrice == null) {
			return "fail";
		}
		Double price = null;
		try {
			price = Double.valueOf(totalPrice.trim());
		} catch (Exception e) {
			return "fail";
		}
		if (price == null || price < 0 || price > 9999999999999D) {
			return "fail";
		}

		Sub sub = businessDao.get(Sub.class, subId);
		// 检查权限
		String userName = UserManager.getUsername(WebContextFactory.get().getSession());
		if (!CommonServiceUtil.haveViewAllDataFunction(WebContextFactory.get().getHttpServletRequest())) {
			if (!sub.getUserName().equals(userName) && !sub.getShopName().equals(userName)) {
				log.warn("can not change sub {} does not belongs to you", subId);
				return "fail";
			}
		}
		boolean result = false;
		try {
			result = subDao.updateSubPrice(sub, userName, price);
		} catch (Exception e) {
			throw new ApplicationException("更新价格失败",EntityCodes.CASH);
		}

		if (result) {
			return null;
		} else {
			return "fail";
		}

	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#deleteProduct(java.lang.Long)
	 */
	@Override
	public String deleteProduct(Long prodId) {
		if (prodId == null) {
			return "fail";
		}
		try {
			// 删除商品
			Product product = productDao.getProduct(prodId);
			if (product == null) {
				log.warn("Product with Id {} does not exists ", prodId);
				return "fail";
			}
			// 检查权限
			String userName = UserManager.getUsername(WebContextFactory.get().getSession());
			if (!CommonServiceUtil.haveViewAllDataFunction(WebContextFactory.get().getHttpServletRequest())) {
				if (!product.getUserName().equals(userName)) {
					log.warn("can not delete Id {} does not belongs to you", prodId);
					return "fail";
				}
			}

			adminService.deleteById(Product.class, prodId);
			
			if(ProductTypeEnum.GROUP.value().equals(product.getProdType())){
				//delete group product
				if(getGroupProductService() != null){
					groupProductService.deleteProduct(prodId);
				}
			}

			// 删除相关产品
			List<RelProduct> list = businessDao.find("from RelProduct n where n.prodId = ? and userName = ?", prodId,
					product.getUserName());
			if (AppUtils.isNotBlank(list)) {
				businessDao.deleteAll(list);
			}
			// 删除相关图片
			List<ImgFile> imgFileList = businessDao.findByHQL(
					"from ImgFile where productType = 1 and  userName = ? and productId = ? ", product.getUserName(),
					prodId);
			if (AppUtils.isNotBlank(imgFileList)) {
				for (ImgFile imgFile : imgFileList) {
					String imgFileUrl = RealPathUtil.getBigPicRealPath() + "/" + imgFile.getFilePath();
					// 删除文件
					log.debug("delete Big imgFileUrl file {}", imgFileUrl);
					FileProcessor.deleteFile(imgFileUrl);
					businessDao.delete(imgFile);
				}

			}
			// 删除商品评论
			businessDao.exeByHQL("delete from ProductComment where prodId = ? and ownerName = ?", new Object[] {
					prodId, product.getUserName() });
			// 删除全文索引
			adminService.updateShopDetail(product);
			
			productSearchFacade.delete(product);
			
			
			String picUrl = RealPathUtil.getBigPicRealPath() + "/" + product.getPic();
			// 删除文件
			log.debug("delete Big Image file {}", picUrl);
			FileProcessor.deleteFile(picUrl);
			String smallPicUrl = RealPathUtil.getSmallPicRealPath() + "/" + product.getPic();
			log.debug("delete small Image file {}", smallPicUrl);
			FileProcessor.deleteFile(smallPicUrl);
			return null;
		} catch (Exception e) {
			log.error("", e);
			return "fail";
		}

	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#productTurnOn(java.lang.Long)
	 */
	@Override
	public String productTurnOn(Long prodId) {
		if (prodId == null) {
			return "fail";
		}
		try {
			if (adminService.updateProdOnline(prodId)) {
				return null;
			} else {
				return "fail";
			}
		} catch (Exception e) {
			log.error("", e);
			return "fail";
		}

	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#imgFileOnline(java.lang.Long)
	 */
	@Override
	public String imgFileOnline(Long fileId) {
		if (fileId == null) {
			return "fail";
		}
		try {
			if (adminService.updateImgFileOnline(fileId)) {
				return null;
			} else {
				return "fail";
			}
		} catch (Exception e) {
			log.error("deleteProd", e);
			return "fail";
		}

	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#productTurnOff(java.lang.Long)
	 */
	@Override
	public String productTurnOff(Long prodId) {
		if (prodId == null) {
			return "fail";
		}
		try {
			if (adminService.updateProdOffline(prodId)) {
				return null;
			} else {
				return "fail";
			}
		} catch (Exception e) {
			log.error("", e);
			return "fail";
		}

	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#imgFileOffline(java.lang.Long)
	 */
	@Override
	public String imgFileOffline(Long fileId) {
		if (fileId == null) {
			return "fail";
		}
		try {
			if (adminService.updateImgFileOffline(fileId)) {
				return null;
			} else {
				return "fail";
			}
		} catch (Exception e) {
			log.error("", e);
			return "fail";
		}

	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#setAdminService(com.legendshop.business.service.AdminService)
	 */
	@Override
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#isUserExist(java.lang.String)
	 */
	@Override
	public boolean isUserExist(String userName) {
		return userDetailDao.isUserExist(userName);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#isEmailExist(java.lang.String)
	 */
	@Override
	public boolean isEmailExist(String email) {
		return userDetailDao.isEmailExist(email);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#deleteUserDetail(java.lang.String, java.lang.String)
	 */
	@Override
	public String deleteUserDetail(String userId, String userName) {
		if (userId == null) {
			return "fail";
		}
		try {
			return adminService.deleteUserDetail(userId, userName, RealPathUtil.getBigPicRealPath(),
					RealPathUtil.getSmallPicRealPath());
		} catch (Exception e) {
			log.error("deleteUserDetail", e);
			return "fail";
		}

	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#loadDynamicAttribute(java.lang.Long, java.lang.String)
	 */
	@Override
	public List<Model> loadDynamicAttribute(Long prodId, String userName) {
		// String jsonStr =
		// "[{\"id\":\"型号\",\"items\":[{\"key\":\"型号\",\"value\":\"SANXING
		// G2000\"}],\"type\":\"Text\"},{\"id\":\"大小\",\"items\":[{\"key\":\"S\",\"value\":\"\"},{\"key\":\"M\",\"value\":\"\"},{\"key\":\"L\",\"value\":\"\"},{\"key\":\"LX\",\"value\":\"\"}],\"type\":\"CheckBox\"},{\"id\":\"颜色\",\"items\":[{\"key\":\"红色\",\"value\":\"\"},{\"key\":\"黄色\",\"value\":\"\"},{\"key\":\"蓝色\",\"value\":\"\"}],\"type\":\"Radio\"},{\"id\":\"高度\",\"items\":[{\"key\":\"100\",\"value\":\"\"},{\"key\":\"200\",\"value\":\"\"},{\"key\":\"300\",\"value\":\"\"}],\"type\":\"Select\"},{\"id\":\"厂家\",\"items\":[{\"key\":\"厂家\",\"value\":\"三星\"}],\"type\":\"Text\"}]";
		List<Model> list = new ArrayList<Model>();
		Product product = adminService.getProd(prodId, userName);
		if (AppUtils.isNotBlank(product)) {
			if (AppUtils.isNotBlank(product.getAttribute()))
				list = JSONArray.fromObject(product.getAttribute());
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#loadDynamicAttributeFromTemp(java.lang.Long, java.lang.String)
	 */
	@Override
	public List<Model> loadDynamicAttributeFromTemp(Long tempId, String userName) {
		List<Model> list = new ArrayList<Model>();
		DynamicTemp temp = adminService.getDynamicTemp(tempId, userName);
		if (AppUtils.isNotBlank(temp)) {
			if (AppUtils.isNotBlank(temp.getContent()))
				list = JSONArray.fromObject(temp.getContent());
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#saveDynamic(java.lang.Long, java.lang.String, java.lang.Short, com.legendshop.model.dynamic.Model[])
	 */
	@Override
	public boolean saveDynamic(Long prodId, String userName, Short type, Model[] model) {
		if (model != null) {
			JSONArray json = JSONArray.fromObject(model);
			String result = json.toString();
			if (AppUtils.isNotBlank(result)) {
				Product product = adminService.getProd(prodId, userName);
				if (type != null && type.equals(Constants.HW_TEMPLATE_ATTRIBUTE)) {
					product.setAttribute(result);
				} else {
					product.setParameter(result);
				}

				adminService.updateProd(product);
			}
		}

		return true;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#saveDynamicTemp(java.lang.String, java.lang.String, java.lang.Short, com.legendshop.model.dynamic.Model[])
	 */
	@Override
	public boolean saveDynamicTemp(String tempName, String userName, Short type, Model[] model) {
		boolean result = true;
		if (model != null) {
			JSONArray json = JSONArray.fromObject(model);
			result = adminService.saveDynamicTemp(tempName, userName, type, json.toString());
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#updateDynamicTemp(java.lang.Long, java.lang.String, java.lang.Short, com.legendshop.model.dynamic.Model[])
	 */
	@Override
	public boolean updateDynamicTemp(Long tempId, String userName, Short type, Model[] model) {
		boolean result = true;
		if (model != null) {
			JSONArray json = JSONArray.fromObject(model);
			result = adminService.updateDynamicTemp(tempId, userName, type, json.toString());
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#deleteDynamicTemp(java.lang.Long, java.lang.String)
	 */
	@Override
	public boolean deleteDynamicTemp(Long tempId, String userName) {
		// 检查权限
		String name = UserManager.getUsername(WebContextFactory.get().getSession());
		if (!CommonServiceUtil.haveViewAllDataFunction(WebContextFactory.get().getHttpServletRequest())) {
			if (!userName.equals(name)) {
				log.warn("can not delete DynamicTemp Id = {} does not belongs to you, userName = {}", tempId, userName);
				return false;
			}
		}
		return adminService.deleteDynamicTemp(tempId, userName);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#addMyLeague(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Integer addMyLeague(String userName, String shopName, String sitename) {
		if (AppUtils.isBlank(userName) || AppUtils.isBlank(shopName)) {
			return MyLeagueEnum.ERROR.value();
		}
		if (userName.equals(shopName)) {
			return MyLeagueEnum.THESAME.value();
		}
		Myleague myleague = shopDetailDao.getMyleague(userName, shopName);
		if (!AppUtils.isBlank(myleague)) {
			return MyLeagueEnum.DONE.value();
		}
		myleague = new Myleague();
		myleague.setAddtime(new Date());
		myleague.setFriendId(shopName);
		myleague.setStatus(MyLeagueEnum.ONGOING.value());
		myleague.setFriendName(sitename);
		myleague.setUserId(userName);
		shopDetailDao.saveMyleague(myleague);
		return MyLeagueEnum.ONGOING.value();
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#answerWord(java.lang.Long, java.lang.String, java.lang.String)
	 */
	@Override
	public String answerWord(Long id, String answer) {
		String loginName = UserManager.getUsername(WebContextFactory.get().getSession());
		if (id == null || loginName == null) {
			return "fail";
		}
		
		boolean result = businessDao.updateUserComment(id, answer, loginName);
		if (result) {
			return null;
		} else {
			return "fail";
		}

	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#changeRandImg()
	 */
	@Override
	public Map<String, String> changeRandImg() {
		Map<String, String> map = new HashMap<String, String>();
		DES2 des = new DES2();
		String ra = RandomStringUtils.random(4, true, true);
		String randStr = ByteConverter.encode(des.byteToString(des.createEncryptor(ra)));
		map.put("rand", ra);
		map.put("randStr", randStr);
		WebContext webContext = WebContextFactory.get();
		webContext.getSession().setAttribute(
				ValidateCodeUsernamePasswordAuthenticationFilter.DEFAULT_SESSION_VALIDATE_CODE_FIELD, ra);
		return map;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#auditShop(java.lang.String, java.lang.String, java.lang.Long, java.lang.Integer)
	 */
	@Override
	public String auditShop(String loginUserName, String storeName, Long shopId, Integer status) {
		if (storeName == null) {
			return Constants.FAIL;
		}
		ShopDetail shopDetail = businessDao.get(ShopDetail.class, shopId);
		if (shopDetail != null) {
			if (!loginUserName.equals(shopDetail.getStoreName())) {
				if (!CommonServiceUtil.haveViewAllDataFunction(WebContextFactory.get().getHttpServletRequest())) {
					throw new BusinessException(loginUserName + "you can not edit " + shopDetail.getStoreName()
							+ "'s data if you are not a admin",EntityCodes.PROD);
				}
			}
			boolean result = adminService.updateShop(loginUserName, storeName, shopDetail, status);
			if (result) {
				return null;
			} else {
				return Constants.FAIL;
			}
		}
		return Constants.FAIL;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#getUsableBrand(java.lang.Long, java.lang.String)
	 */
	@Override
	public List<Item> getUsableBrand(Long nsortId, String userName) {

		List<Item> list = new ArrayList<Item>();
		try {
			list = brandDao.getUsableBrand(nsortId, userName);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#getUsableBrandByName(java.lang.Long, java.lang.String, java.lang.String)
	 */
	@Override
	public List<Item> getUsableBrandByName(Long nsortId, String userName, String brandName) {
		return brandDao.getUsableBrandByName(nsortId, userName, brandName);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#getUsedBrand(java.lang.Long, java.lang.String)
	 */
	@Override
	public List<Item> getUsedBrand(Long nsortId, String userName) {
		return brandDao.getUsedBrand(nsortId, userName);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#getUsedProductItem(java.lang.Long, java.lang.String)
	 */
	@Override
	public List<Item> getUsedProductItem(Long prodId, String userName) {
		List<Item> list = brandDao.getUsedProd(prodId, userName);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#getUsableProductItemByName(java.lang.Long, java.lang.String, java.lang.String)
	 */
	@Override
	public List<Item> getUsableProductItemByName(Long prodId, String userName, String prodName) {
		return brandDao.getUsableProductItemByName(prodId, userName, prodName);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#getUsableProductItem(java.lang.Long, java.lang.String)
	 */
	@Override
	public List<Item> getUsableProductItem(Long prodId, String userName) {
		return brandDao.getUsableProductItem(prodId, userName);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#saveBrandItem(java.lang.String, java.lang.String, java.lang.Long, java.lang.String)
	 */
	@Override
	public String saveBrandItem(String idJson, String nameJson, Long nsortId, String userName) {
		JSONArray array = JSONArray.fromObject(idJson);
		return adminService.saveBrandItem(array, nsortId, userName);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#saveProdItem(java.lang.String, java.lang.String, java.lang.Long, java.lang.String)
	 */
	@Override
	public String saveProdItem(String idJson, String nameJson, Long prodId, String userName) {
		JSONArray arrayId = JSONArray.fromObject(idJson);
		JSONArray arrayName = JSONArray.fromObject(nameJson);
		return adminService.saveProdItem(arrayId, arrayName, prodId, userName);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#setBusinessDao(com.legendshop.business.dao.BusinessDao)
	 */
	@Required
	public void setBusinessDao(BusinessDao businessDao) {
		this.businessDao = businessDao;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#setUserDetailDao(com.legendshop.business.dao.UserDetailDao)
	 */
	@Required
	public void setUserDetailDao(UserDetailDao userDetailDao) {
		this.userDetailDao = userDetailDao;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#setShopDetailDao(com.legendshop.business.dao.ShopDetailDao)
	 */
	@Required
	public void setShopDetailDao(ShopDetailDao shopDetailDao) {
		this.shopDetailDao = shopDetailDao;
	}

	// 加入购物车， 用户登录后保存到数据库中，没有登录先保存到session
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#addtocart(java.lang.String, java.lang.String, java.lang.Long, java.lang.String, java.lang.String, java.lang.Double, java.lang.Double, java.lang.Integer, java.lang.String)
	 */
	@Override
	public Map addtocart(String userName, String shopName, Long prodId, String pic, String prodName, Double cash,
			Double carriage, Integer count, String attribute) {
		Map map = new HashMap();
		if (prodId == null) {
			return map;
		}
		if (attribute == null) { // default
			attribute = "";
		}
		if (count == null) { // default
			count = 1;
		}
		// 检查库存
		boolean canAddToCart = true;
		Product product = productDao.getProduct(prodId);
		if (product == null || (product.getStocks() != null && product.getStocks() < count)) {
			canAddToCart = false;
		}

		if (AppUtils.isBlank(userName)) {// 没有登录
			WebContext webContext = WebContextFactory.get();
			Map<String, Basket> basketMap = (Map<String, Basket>) webContext.getSession().getAttribute(
					Constants.BASKET_KEY);
			if (basketMap == null) {
				if (canAddToCart) {
					basketMap = new HashMap<String, Basket>();
					Basket b = new Basket();
					b.setProdId(prodId);
					b.setUserName(userName);
					b.setBasketCount(count);
					b.setProdName(prodName);
					b.setCash(cash);
					b.setPic(pic);
					b.setAttribute(attribute);
					b.setCarriage(carriage);
					b.setBasketDate(new Date());
					b.setBasketCheck(Constants.FALSE_INDICATOR);
					b.setShopName(shopName);
					basketMap.put(getBasketKey(shopName, prodId, attribute), b);
					webContext.getSession().setAttribute(Constants.BASKET_KEY, basketMap);
					map.put(Constants.BASKET_COUNT, 1);
					Double totalCash = Arith.mul(count == null ? 1d : count, cash);
					if (carriage != null) {
						totalCash = Arith.add(totalCash, carriage);
					}
					map.put(Constants.BASKET_TOTAL_CASH, totalCash);
				} else {
					map.put(Constants.BASKET_COUNT, 0);
					map.put(Constants.BASKET_TOTAL_CASH, 0);
				}

			} else {
				if (canAddToCart) {
					Basket b = basketMap.get(getBasketKey(shopName, prodId, attribute));
					if (b == null) {
						b = new Basket();
						b.setProdId(prodId);
						b.setUserName(userName);
						b.setBasketCount(count);
						b.setProdName(prodName);
						b.setCash(cash);
						b.setAttribute(attribute);
						b.setCarriage(carriage);
						b.setBasketDate(new Date());
						b.setBasketCheck(Constants.FALSE_INDICATOR);
						b.setShopName(shopName);
						basketMap.put(getBasketKey(shopName, prodId, attribute), b);
						webContext.getSession().setAttribute(Constants.BASKET_KEY, basketMap);

					} else {
						if (product.getStocks() != null && product.getStocks() < b.getBasketCount() + count) {
							canAddToCart = false;
						} else {
							b.setBasketCount(b.getBasketCount() + count);
							basketMap.put(getBasketKey(shopName, prodId, attribute), b);
							webContext.getSession().setAttribute(Constants.BASKET_KEY, basketMap);
						}
					}
				}
				map.put(Constants.BASKET_COUNT, basketMap.size());
				map.put(Constants.BASKET_TOTAL_CASH, CommonServiceUtil.calculateTotalCash(basketMap));
			}
		} else {// 已经登录
			if (canAddToCart) {
				canAddToCart = basketDao.saveToCart(prodId, pic, userName, shopName, count, attribute, prodName, cash,
						carriage);
			}
			List<Basket> baskets = basketDao.getBasketByuserName(userName);
			Double totalcash = CommonServiceUtil.calculateTotalCash(baskets);
			if (totalcash == null) {
				totalcash = 0d;
			}
			map.put(Constants.BASKET_COUNT, baskets.size());
			map.put(Constants.BASKET_TOTAL_CASH, totalcash);
		}

		map.put(Constants.PRODUCT_LESS, !canAddToCart);
		return map;
	}

	// 更新订单的支付类型，方便对方接口返回数据查询，subId：订单ID，payId：支付方式ID
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#payToOrder(java.lang.Long, java.lang.String, java.lang.Integer)
	 */
	@Override
	public void payToOrder(Long subId, String shopName, Integer payTypeId) {
		if (AppUtils.isNotBlank(subId)) {
			Sub sub = subDao.getSubById(subId);
			// if(sub.getPayId() == null){//第一次更新订单的付款方式，是用户确认订单的最后一步，更改商品订购数
			// List<Basket> basketList =
			// businessDao.getBasketBySubNumber(sub.getSubNumber());
			// if(AppUtils.isNotBlank(basketList)){
			// for (Basket basket : basketList) {
			// businessDao.increaseProdBuys(basket.getProdId(),
			// basket.getBasketCount());
			// }
			// }
			// }
			PayType payType = payTypeService.getPayTypeByIdAndName(shopName, payTypeId);
			// TODO 增加历史
			if (payType != null) {
				sub.setPayTypeName(payType.getPayTypeName());
				sub.setPayTypeId(payType.getPayTypeId());
				sub.setPayId(payType.getPayId());
				sub.setPayDate(new Date());
				businessDao.update(sub);
			}
		}
	}

	/**
	 * Gets the basket key.
	 * 
	 * @param shopName
	 *            the shop name
	 * @param prodId
	 *            the prod id
	 * @param attribute
	 *            the attribute
	 * @return the basket key
	 */
	private String getBasketKey(String shopName, Long prodId, String attribute) {
		StringBuffer sb = new StringBuffer();
		sb.append(shopName).append(prodId).append(AppUtils.getCRC32(attribute));
		return sb.toString();
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#setPayTypeService(com.legendshop.business.service.PayTypeServiceImpl)
	 */
	@Required
	public void setPayTypeService(PayTypeService payTypeService) {
		this.payTypeService = payTypeService;
	}

	// 重置密码
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#resetPassword(java.lang.String, java.lang.String)
	 */
	@Override
	public String resetPassword(String userName, String mail) {
		if (AppUtils.isBlank(userName) || AppUtils.isBlank(mail)) {
			return "fail";
		}
		try {
			WebContext webContext = WebContextFactory.get();
			String templateFilePath = webContext.getSession().getServletContext().getRealPath("/")
					+ "/system/mailTemplate/resetpassmail.jsp";
			if (adminService.updatePassword(userName, mail, templateFilePath)) {
				return null;
			} else {
				return "fail";
			}
		} catch (Exception e) {
			log.error("", e);
			return "fail";
		}

	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#addProdcutComment(java.lang.Long, java.lang.String)
	 */
	@Override
	public String addProdcutComment(Long prodId, String content) {
		String userName = UserManager.getUsername(WebContextFactory.get().getSession());
		String validation = validateComment(prodId, userName);
		if (validation != null) {
			return validation;
		}
		ProductComment productComment = new ProductComment();
		productComment.setUserName(userName == null ? "anonymous" : userName);
		productComment.setAddtime(new Date());
		productComment.setContent(content);
		productComment.setProdId(prodId);
		Product product = productDao.getProduct(prodId);
		productComment.setOwnerName(product.getUserName());
		HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();
		productComment.setPostip(request.getRemoteAddr());
		businessDao.save(productComment);
		// create HTML though Freemarker
		String curPageNO = request.getParameter("curPageNO");
		return getgetProductCommentHtml(request, prodId, curPageNO);

	}

	/**
	 * Validate comment.
	 * 
	 * @param prodId
	 *            the prod id
	 * @param userName
	 *            the user name
	 * @return the string
	 */
	private String validateComment(Long prodId, String userName) {
		String level = PropertiesUtil.getObject(ParameterEnum.COMMENT_LEVEL, String.class);
		if (Constants.LOGONED_COMMENT.equals(level)) {
			if (AppUtils.isBlank(userName)) {
				return "NOLOGON"; // 没有登录
			}
		} else if (Constants.BUYED_COMMENT.equals(level)) {
			if (AppUtils.isBlank(userName)) {
				return "NOLOGON"; // 没有登录
			}
			if (!businessDao.isUserOrderProduct(prodId, userName)) {
				return "NOBUYED";
			}
		} else if (Constants.NO_COMMENT.equals(level)) {
			return "NOCOMMENT";
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#getProductComment(java.lang.Long, java.lang.String)
	 */
	@Override
	public String getProductComment(Long prodId, String curPageNO) {
		HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();
		return getgetProductCommentHtml(request, prodId, curPageNO);
	}

	/**
	 * Gets the gets the product comment html.
	 * 
	 * @param request
	 *            the request
	 * @param prodId
	 *            the prod id
	 * @param curPageNO
	 *            the cur page no
	 * @return the gets the product comment html
	 */
	private String getgetProductCommentHtml(HttpServletRequest request, Long prodId, String curPageNO) {
		Locale locale = localeResolver.resolveLocale(request);
		if (locale == null) {
			locale = Locale.CHINA;
		}
		try {
			CriteriaQuery cq = new CriteriaQuery(ProductComment.class, curPageNO);
			cq.setPageSize(PropertiesUtil.getObject(ParameterEnum.FRONT_PAGE_SIZE, Integer.class));
			cq.addOrder("desc", "addtime");
			cq.eq("prodId", prodId);
			cq.add();
			PageSupport ps = productDao.getProdDetail(cq);
			request.setAttribute("curPageNO", new Integer(ps.getCurPageNO()));
			Map<String, Object> map = new HashMap<String, Object>();
			List<ProductComment> list = ps.getResultList();
			map.put("productCommentList", list);
			if (AppUtils.isNotBlank(list)) {
				String userName = UserManager.getUsername(request);
				if (list.get(0).getOwnerName().equals(userName)) {
					map.put("owner", true);
				}
			}
			if (ps.hasMutilPage()) {
				map.put("toolBar", ps.getToolBar(locale, Constants.SIMPLE_PAGE_PROVIDER));
			}
			map.put("totalProductComment", ps.getTotal());
			return PageGengrator.getInstance().crateHTML(request.getSession().getServletContext(),
					"productComment.ftl", map, locale);
		} catch (Exception e) {
			log.error("getProductComment", e);
			return "FAIL";
		}
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#queryParameter(java.lang.Long)
	 */
	@Override
	public String queryParameter(Long prodId) {
		HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();
		Locale locale = localeResolver.resolveLocale(request);
		ProductDetail prod = (ProductDetail) request.getAttribute("prod");
		String parameter = null;
		if (prod != null) {
			parameter = prod.getParameter();
		} else {
			parameter = adminService.getProdParameter(prodId);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if (AppUtils.isNotBlank(parameter)) {
			List<JSONObject> modelList = JSONArray.fromObject(parameter);
			DynamicPropertiesHelper helper = new DynamicPropertiesHelper();
			map.put("dynamicProperties", "<table class='goodsAttributeTable'>" + helper.gerenateHTML(modelList)
					+ "</table>");
			return PageGengrator.getInstance().crateHTML(request.getSession().getServletContext(), "showdynamic.ftl",
					map, locale);
		}
		return "";
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#deleteFile(java.lang.String)
	 */
	@Override
	public Integer deleteFile(String filePath) {
		String realPath = RealPathUtil.getFCKRealPath(filePath);
		int result = FileProcessor.deleteFile(realPath);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#setProductSearchFacade(com.legendshop.business.search.facade.ProductSearchFacade)
	 */
	@Required
	public void setProductSearchFacade(ProductSearchFacade productSearchFacade) {
		this.productSearchFacade = productSearchFacade;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#userScore(java.lang.Long, java.lang.Long)
	 */
	@Override
	public Map<String, Object> userScore(Long subId, Long score) {
		Sub sub = subDao.getSubById(subId);
		if (sub == null) {
			return null;
		}
		return scoreService.useScore(sub, score);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#calMoney(java.lang.Long)
	 */
	@Override
	public Double calMoney(Long score) {
		return scoreService.calMoney(score);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#setScoreService(com.legendshop.business.service.ScoreServiceImpl)
	 */
	@Required
	public void setScoreService(ScoreService scoreService) {
		this.scoreService = scoreService;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#setBasketDao(com.legendshop.business.dao.BasketDao)
	 */
	@Required
	public void setBasketDao(BasketDao basketDao) {
		this.basketDao = basketDao;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#setImgFileDao(com.legendshop.business.dao.ImgFileDao)
	 */
	@Required
	public void setImgFileDao(ImgFileDao imgFileDao) {
		this.imgFileDao = imgFileDao;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#setBrandDao(com.legendshop.business.dao.BrandDao)
	 */
	@Required
	public void setBrandDao(BrandDao brandDao) {
		this.brandDao = brandDao;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#setProductDao(com.legendshop.business.dao.ProductDao)
	 */
	@Required
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#setSubDao(com.legendshop.business.dao.SubDao)
	 */
	@Required
	public void setSubDao(SubDao subDao) {
		this.subDao = subDao;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.DwrCommonService#setLocaleResolver(org.springframework.web.servlet.LocaleResolver)
	 */
	@Required
	public void setLocaleResolver(LocaleResolver localeResolver) {
		this.localeResolver = localeResolver;
	}

	@Override
	public boolean validateRandImg(String validateCodeParameter) {
		if(PropertiesUtil.getObject(ParameterEnum.VALIDATION_IMAGE, Boolean.class)){
			WebContext webContext = WebContextFactory.get();
			boolean result = CaptchaServiceSingleton.getInstance().validateResponseForID(webContext.getSession().getId(), validateCodeParameter);
			return result;
		}else{
			throw new BusinessException("not support VALIDATION_IMAGE", EntityCodes.SYSTEM);
		}
	}

	public void setGroupProductService(GroupProductService groupProductService) {
		this.groupProductService = groupProductService;
	}

	public GroupProductService getGroupProductService() {
		if(groupProductService == null){
			if(ContextServiceLocator.getInstance().containsBean("groupProductService")){
				groupProductService = (GroupProductService)ContextServiceLocator.getInstance().getBean("groupProductService");
			}
		}
		return groupProductService;
	}
}
