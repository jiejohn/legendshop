package com.done.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import org.springframework.jdbc.core.JdbcTemplate;

import bingosoft.jcf.dao.cache.BaseDao;
import bingosoft.jcf.dao.support.CriteriaQuery;
import bingosoft.jcf.dao.support.HqlQuery;
import bingosoft.jcf.dao.support.PageSupport;
import bingosoft.jcf.sql.ConfigCode;
import bingosoft.jcf.util.AppUtils;
import bingosoft.jcf.util.TimerUtil;

import com.done.common.Constants;
import com.done.model.Ad;
import com.done.model.Advertisement;
import com.done.model.Basket;
import com.done.model.Hotsearch;
import com.done.model.Hw;
import com.done.model.HwDetail;
import com.done.model.Indexjpg;
import com.done.model.Logo;
import com.done.model.Myleague;
import com.done.model.News;
import com.done.model.Nsort;
import com.done.model.Pub;
import com.done.model.ShopDetail;
import com.done.model.Sort;
import com.done.model.Sub;
import com.done.model.TUser;
import com.done.model.UserComment;
import com.done.model.UserDetail;
import com.done.model.UserRole;
import com.done.model.UserRoleId;
import com.done.util.MD5Util;

/**
 * 服务类
 * 
 * @author hewq
 * 
 */
public class BusinessService {

    private JdbcTemplate jdbcTemplate;

    private BaseDao baseDao;
    private BaseDao cacheDao;
    private String[] colorTypeOptions = new String[] { "blue", "green", "red" }; //see shopDetail.jsp

    public ShopDetail getShopDetail(String storeName) {
        if (storeName == null) {
            return null;
        }
        ShopDetail shopDetail = cacheDao.findUniqueBy(ConfigCode.getInstance().getCode("biz.getShopDetail"),
                ShopDetail.class, new Object[] { storeName });
        if ((shopDetail != null) && AppUtils.isBlank(shopDetail.getColorStyle())) {
            shopDetail.setColorStyle(getColorTyle(storeName));
        }
        return shopDetail;
    }
    
    public boolean isShopExists(String storeName) {
    	List list = cacheDao.findByHQL("select storeName from ShopDetail where storeName = ? ", new Object[] { storeName });
    	return list!=null&&list.size()>0;
    }
    
    public boolean isLeagueShopExists(String storeName) {
    	if(storeName == null) return false;
    	List list = cacheDao.findByHQL("select count(*) from Myleague where userId = ? ", new Object[] { storeName });
    	if(list == null){
    		return false;
    	}
    	return ((Long)list.get(0))  >0;
    }
    
    public boolean canbeLeagueShop(boolean isShopExists,String userName,String storeName) {
    	if(!isShopExists) return false;
    	if(AppUtils.isBlank(userName)) return false;
    	if(userName.equals(storeName)) return false;
    	List list = cacheDao.findByHQL("select count(*) from Myleague where userId = ? and friendId = ?", new Object[] { userName,storeName });
    	if(list == null){
    		return true;
    	}
    	return ((Long)list.get(0))  <= 0;
    }
    
    public List<Myleague> myLeagueShop(String storeName) {
    	if(storeName == null) return null;
    	List<Myleague> list = cacheDao.findByHQL("select new Myleague(m.friendId,m.friendName,l.banner) from Myleague m,Logo l where m.friendId = l.userName and m.userId = ? order by m.displayOrder desc", new Object[] { storeName });
    	return list;
    }

    private String getColorTyle(String storeName) {
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
        String s = simpledateformat.format(new Date());
        long i = (Long.valueOf(s) + Long.valueOf(storeName.hashCode())) % 3;
        if (i < 0) {
            i = -i;
        }
        String type = colorTypeOptions[(int) i];
        return type;
    }

    /**
     * 
     * @param loadAll 是否加载 nsort 1、由部分加载改为全部加在，在jsp中再做限制
     *            2、只通过top.jsp获得sort，避免多处查找
     * @return
     */
    public List<Sort> getSort(String shopName, boolean loadAll) {
        List<Sort> list = cacheDao.findByHQL("from Sort where userName = ? order by seq", new Object[] { shopName });
        if (loadAll) {
            for (Sort sort : list) {
                sort.setNsort(cacheDao.findByHQL("from Nsort where sortId = ? and parentNsortId is null order by seq",
                        new Object[] { sort.getSortId() }));
            }
        }
        return list;
    }

    public List<Pub> getPub(String shopName) {
        CriteriaQuery cq = new CriteriaQuery(Pub.class);
        cq.eq("userName", shopName);
        cq.addOrder("desc", "date");
        cq.add();
        return cacheDao.findListByCriteria(cq, 0, 4);
    }

    public List<Hotsearch> getSearch(String shopName) {
        return cacheDao.findByHQL("from Hotsearch where userName = ? order by Id DESC", new Object[] { shopName });
    }

    public UserDetail getUserDetail(String userName) {
        return baseDao.findUniqueBy("from UserDetail where userName= ?", UserDetail.class, new Object[] { userName });
    }

    public void saveSub(Sub sub) {
        baseDao.saveOrUpdate(sub);
    }

    public boolean deleteSub(Integer id, String userName) {
        Sub sub = baseDao.get(Sub.class, id);
        if (sub != null) {
            if (userName.equals(sub.getUserName())) {
                baseDao.delete(sub);
                deleteBasketBySubNumber(sub.getSubNumber());
                return true;
            }

        }
        return false;
    }

    public boolean adminDeleteSub(Integer id, String userName) {
        Sub sub = baseDao.get(Sub.class, id);
        if (sub != null) {
            baseDao.delete(sub);
            deleteBasketBySubNumber(sub.getSubNumber());
            return true;
        }
        return false;
    }

    public Sub findSubBySubNumber(String subNumber) {
        List<Sub> list = baseDao.findByHQL("from Sub where subNumber = ?", new Object[] { subNumber });
        if (AppUtils.isBlank(list)) {
            return null;
        }
        return list.get(0);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    public Logo getLogo(String shopName) {
        return cacheDao.findUniqueBy("from Logo where userName = ?", Logo.class, new Object[] { shopName });
    }

    public List<Advertisement> getAdvertisement(String shopName) {
        return cacheDao.find("from Advertisement where enabled = '1' and userName = ? ", new Object[] { shopName });
    }

    public List<Hw> getCommend(String shopName, int total) {
        CriteriaQuery cq = new CriteriaQuery(Hw.class);
        cq.eq("commend", "true");
        cq.eq("userName", shopName);
        cq.eq("status", 1);
        cq.addOrder("desc", "modifyDate");
        cq.add();
        return cacheDao.findListByCriteria(cq, 0, total);
    }

    public List<Hw> getNewest(String shopName, int total) {
        CriteriaQuery cq = new CriteriaQuery(Hw.class);
        cq.addOrder("desc", "modifyDate");
        cq.eq("userName", shopName);
        cq.eq("status", Constants.ONLINE);
        cq.add();
        return cacheDao.findListByCriteria(cq, 0, total);
    }

    public List<Hw> getPaihang(String name) {
        HqlQuery hqlQuery = new HqlQuery(ConfigCode.getInstance().getCode("biz.getPaihang"), new Object[] { name },
                new Type[] { Hibernate.STRING });
        return baseDao.findListByHQL(hqlQuery, 0, 6);
    }

    public PageSupport getHwDetail(CriteriaQuery cq) {
        return baseDao.find(cq);
    }

    public HwDetail getHwDetail(Integer hwId) {
        return baseDao.get(HwDetail.class, hwId);
    }

    public Hw getHwById(Integer hwId) {
        return baseDao.get(Hw.class, hwId);
    }

    public List<Basket> getBasketByuserName(String userName, String shopName) {
        return baseDao.findByHQL("from Basket where userName=? and basket_check=? and shopName=?", new Object[] {
                userName, "False", shopName });

    }

    public List<Basket> getBasketBySubNumber(String userName, String subNumber) {
        return baseDao.findByHQL("from Basket where userName = ? and subNumber=? and basket_check=?", new Object[] {
                userName, subNumber, "True" });
    }

    public Basket getBasketById(Integer id) {
        return baseDao.get(Basket.class, id);

    }

    /*
     * 得到随机数
     */
    public synchronized String getSubNember(String userName) {
        String subNumber = "";
        String now = TimerUtil.dateToStr(new Date());
        subNumber = now;
        subNumber = subNumber.replace("-", "");
        subNumber = subNumber.replace(" ", "");
        subNumber = subNumber.replace(":", "");
        Random r = new Random();
        subNumber = subNumber + randomNumeric(r, 6);
        return subNumber;
    }

    private String randomNumeric(Random random, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            int val = random.nextInt(10);
            sb.append(String.valueOf(val));
        }
        return sb.toString();
    }

    public Basket getBasketByIdName(String hwId, String userName, String shopName) {
        Basket basket = null;
        List<Basket> list = baseDao.findByHQL(
                "from Basket where hwId=? and userName=? and basket_check=? and shopName=?", new Object[] { hwId,
                        userName, "False", shopName });
        if (!AppUtils.isBlank(list)) {
            basket = list.get(0);
        }
        return basket;
    }

    public Integer saveBasket(Basket basket) {
        return (Integer) baseDao.save(basket);
    }

    public void updateBasket(Basket basket) {
        baseDao.update(basket);
    }

    //更新商品查看数
    public void updateHwViews(Integer hwId) {
        baseDao.exeByHQL("update Hw set hwViews = hwViews+1 where hwId = ?", new Object[] { hwId },
                new Type[] { Hibernate.INTEGER });
    }

    //更新商品购买数
    public void updateHwBuys(Integer hwId, String basketCount) {
        baseDao.exeByHQL("update Hw set hwBuys = hwBuys + " + basketCount + " where hwId = ?", new Object[] { hwId },
                new Type[] { Hibernate.INTEGER });
    }

    public List<Basket> getBasket(String hwId, String userName) {
        return baseDao.findByHQL("from Basket where hwId = ? and userName = ? and basketCheck='False'", new Object[] {
                hwId, userName });
    }

    //热门产品
    public List<Hw> getHotOn(String sortId) {
        CriteriaQuery cq = new CriteriaQuery(Hw.class);
        cq.addOrder("desc", "hwBuys");
        if (!AppUtils.isBlank(sortId)) {
            cq.eq("sortId", Integer.valueOf(sortId));
        } else {
            return Collections.EMPTY_LIST;
        }
        cq.add();
        return cacheDao.findListByCriteria(cq, 0, 21);
    }

    public List<News> getNews(String name,Integer num) {
        CriteriaQuery cq = new CriteriaQuery(News.class);
        cq.eq("userName", name);
        cq.eq("status", Constants.ONLINE);
        cq.addOrder("desc", "newsDate");
        cq.add();
        return cacheDao.findListByCriteria(cq, 0, num);
    }

    public List<News> getOffLineNews(String name) {
        CriteriaQuery cq = new CriteriaQuery(News.class);
        cq.eq("userName", name);
        cq.eq("status", Constants.OFFLINE);
        cq.addOrder("desc", "newsDate");
        cq.add();
        return cacheDao.findListByCriteria(cq, 0, 8);
    }

    public Sort getSort(Integer sortId) {
        return cacheDao.get(Sort.class, sortId);
    }

    public Nsort getNsort(Integer nsortId) {
        Nsort nsort = cacheDao.get(Nsort.class, nsortId);
        if (nsort != null) {
            nsort.setSubSort(cacheDao.findByHQL("from Nsort where parent_nsort_id = ?", new Object[] { nsort
                    .getNsortId() }));
        }
        return nsort;
    }

    //得到其他的相关小类
    public List<Nsort> getNsortList(Integer sortId, Integer nsortId) {
        return cacheDao.findByHQL("from Nsort where sortId = ? and nsortId <> ?", new Object[] { sortId, nsortId });
    }

    //得到其他的相关小类
    public List<Nsort> getNsortList(Integer sortId) {
        return cacheDao.findByHQL("from Nsort where sortId = ?", new Object[] { sortId });
    }

    //得到相关二级小类
    public List<Nsort> getOthorNsort(List<Nsort> list) {
        List<Nsort> result = new ArrayList<Nsort>();
        for (Nsort nsort : list) {
            if (nsort.getParentNsortId() == null) {
                result.add(nsort);
            }
        }
        return result;
    }

    //得到相关三级小类
    public List<Nsort> getOthorSubNsort(Integer InsortId, List<Nsort> list) {
        List<Nsort> result = new ArrayList<Nsort>();
        for (Nsort nsort : list) {
            if ((nsort.getParentNsortId() != null) && nsort.getParentNsortId().equals(InsortId)) {
                result.add(nsort);
            }
        }
        return result;
    }

    public List<Nsort> getNSort(Integer sortId) {
        return cacheDao.findByHQL("from Nsort where sortId = ? and parent_nsort_id is null", new Object[] { sortId });
    }

    public List<Ad> getAdOrderbybs(String name) {
        return cacheDao.findByHQL("from Ad where userName = ? order by bs", new Object[] { name });
    }

    public void deleteBasketByUserName(String userName) {
        List<Basket> list = baseDao.findByHQL("from Basket where userName=? and basketCheck='False'",
                new Object[] { userName });
        if (!AppUtils.isBlank(list)) {
            baseDao.deleteAll(list);
        }
        //jdbcTemplate.update("delete from basket where user_name=? and basket_check='False'", new Object[]{userName});
    }

    public void deleteBasketBySubNumber(String subNumber) {
        List<Basket> list = baseDao.findByHQL("from Basket where subNumber=?", new Object[] { subNumber });
        if (!AppUtils.isBlank(list)) {
            baseDao.deleteAll(list);
        }
        //jdbcTemplate.update("delete from basket where sub_number = ?", new Object[]{subNumber});
    }

    public void deleteBasketById(Integer basketId) {
        //baseDao.deleteById(Basket.class, basketId);
        //jdbcTemplate.update("delete from basket where basket_id=?", new Object[]{basketId});
        baseDao.exeByHQL("delete from Basket where basketId=?", new Object[] { basketId },
                new Type[] { Hibernate.INTEGER });
    }

    public News getNewsById(Integer newsId) {
        return baseDao.get(News.class, newsId);
    }

    public PageSupport getNews(CriteriaQuery cq) {
        return baseDao.find(cq);
    }

    public PageSupport getByCQ(CriteriaQuery cq) {
        return baseDao.find(cq, true);
    }

    public PageSupport processOrder(CriteriaQuery cq) {
        return baseDao.find(cq);
    }

    //得到首页图片
    public List<Indexjpg> getIndexJpeg(String userName) {
        String name = Constants.DEFAULT_SHOP;
        if (!AppUtils.isBlank(userName)) {
            name = userName;
        }
        List<Indexjpg> list = cacheDao.findByHQL("from Indexjpg where userName = ? OR userName = 'common'",
                new Object[] { name });
        return list;
    }

    //@test
    public Hw loadHw(Integer id) {
        return baseDao.get(Hw.class, id);
    }

    //@test
    public int executeByHQL(Integer id) {
        return baseDao.exeByHQL("update Ad ad set ad.content = 'bbbb' where ad.id = ?", new Object[] { id },
                new Type[] { Hibernate.INTEGER });
    }

    public void setCacheDao(BaseDao cacheDao) {
        this.cacheDao = cacheDao;
    }

    public boolean processSub(Integer subId, String userName) {
        Sub sub = baseDao.get(Sub.class, subId);
        if (sub != null) {
            if (!sub.getShopName().equals(userName)) {
                return false;
            }
            sub.setSubCheck("True");
            baseDao.update(sub);
            return true;
        }
        return false;
    }
    
    public boolean answreWord(Integer id, String answer,String toUserName) {
    	UserComment comment = baseDao.get(UserComment.class, id);
        if (comment != null) {
            if (!comment.getToUserName().equals(toUserName)) {
            	System.out.println("toUserName try to answer comments own to " + comment.getToUserName() +" ,but fail");
                return false;
            }
            comment.setAnswer(answer);
            comment.setAnswertime(new Date());
            baseDao.update(comment);
            return true;
        }
        return false;
    }

    //普通用户注册
    public String saveUser(TUser user, UserDetail userDetail,boolean isAdmin) {
        //1/保存t_user
        String userId = (String) baseDao.save(user);
        //2/保存用户详细信息user_detail
        userDetail.setUserId(userId);
        userDetail.setUserName(user.getName());
        saveUerDetail(userDetail,isAdmin);
        return userId;
    }

    public void saveUerDetail(UserDetail userDetail, boolean isAdmin) {
        String sql = null;
        if(isAdmin){
        	sql = "select id from t_role where name = 'ROLE_ADMIN' or name = 'ROLE_OPERATOR'";
            //save shopDetail
            saveShopDetail(userDetail);
        }else{
        	sql = "select id from t_role where name = 'ROLE_USER'";
        }
        baseDao.save(userDetail);
        //3/保存用户角色t_user_role
        List<String> roles = baseDao.findBySQL(sql);
        for (String roleId : roles) {
            UserRole userRole = new UserRole();
            UserRoleId id = new UserRoleId();
            id.setRoleId(roleId);
            id.setUserId(userDetail.getUserId());
            userRole.setId(id);
            if(baseDao.get(UserRole.class, id) == null){
            	baseDao.save(userRole);
            }
		}

    }
    
    public void updateShopDetail(UserDetail userDetail, boolean isAdmin) {
    	if(isAdmin){
    		//增加权限,去掉user_role
    		String sql = "select id from t_role where name = 'ROLE_ADMIN' or name = 'ROLE_OPERATOR'";
            //3/保存用户角色t_user_role
            List<String> roles = baseDao.findBySQL(sql);
            for (String roleId : roles) {
                UserRole userRole = new UserRole();
                UserRoleId id = new UserRoleId();
                id.setRoleId(roleId);
                id.setUserId(userDetail.getUserId());
                userRole.setId(id);
                if(baseDao.get(UserRole.class, id) == null){
                	baseDao.save(userRole);
                }
    		}
            UserRole userRole = new UserRole();
            UserRoleId id = new UserRoleId();
            id.setRoleId(Constants.ROLE_USER);
            id.setUserId(userDetail.getUserId());
            userRole.setId(id);
            baseDao.delete(userRole);
            
    		//增加shopDetail
            saveShopDetail(userDetail);
    	}
    }
    
    //UserId 一定不能为空
    public void saveShopDetail(UserDetail userDetail) {
            ShopDetail shop = baseDao.get(ShopDetail.class, userDetail.getUserId());
            if (shop == null) {
            	ShopDetail shopDetail = new ShopDetail();
                Date date = new Date();
                shopDetail.setWeb(userDetail.getUserName());
                shopDetail.setStoreName(userDetail.getUserName());
                shopDetail.setSitename(userDetail.getUserName());
                shopDetail.setMaddr(userDetail.getUserAdds());
                shopDetail.setModifyTime(date);
                shopDetail.setUserId(userDetail.getUserId());
                shopDetail.setAddtime(date);
                shopDetail.setVisitTimes(0);
                shopDetail.setWeb(shopDetail.getStoreName());
                shopDetail.setStatus(Constants.SHOP_ONLINE);
                shopDetail.setStoreStatus(Constants.SHOP_ONLINE);
                baseDao.save(shopDetail);
            }
    }
    

    //普通用户信息修改
    public void updateUser(UserDetail userDetail) {
        baseDao.update(userDetail);
        changePassword(userDetail);
    }

    //修改用户密码
    public void changePassword(UserDetail userDetail) {
        if (!AppUtils.isBlank(userDetail.getPassword())) {
            TUser user = baseDao.get(TUser.class, userDetail.getUserId());
            user.setPassword(MD5Util.Md5Password(userDetail.getUserName(), userDetail.getPassword()));
            baseDao.update(user);
        }
    }

    //客户是否存在
    public boolean isUserExist(String userName) {
        List list = baseDao.findByHQL("from TUser where name = ?", new Object[] { userName });
        if (AppUtils.isBlank(list)) {
            return false;
        }
        return true;
    }

    //店名是否存在
    public boolean isShopExist(String shopName) {
        List list = cacheDao.findByHQL("from ShopDetail where web = ?", new Object[] { shopName });
        if (AppUtils.isBlank(list)) {
            return false;
        }
        return true;
    }

    public TUser getUser(String userId) {
        return baseDao.get(TUser.class, userId);
    }

    public TUser getUserByName(String userName) {
        List<TUser> list = baseDao.findByHQL("from TUser where name = ?", new Object[] { userName });
        if (list != null && list.size()>0) {
            return list.get(0);
        }
        return null;
    }
    
    public Myleague findMyleagueByUserNameAndShopName(String userName,String shopName) {
    	Myleague myleague = null;
        List<Myleague> list =  baseDao.findByHQL("from Myleague where userId = ? and friendId = ?", new Object[] { userName,shopName });
        if(list!=null && list.size()>0){
        	myleague = list.get(0);
        }
         return myleague;
    }
    
    public Integer saveMyleague(Myleague myleague) {
        Integer result = (Integer) baseDao.save(myleague);
        return result;
    }
}
