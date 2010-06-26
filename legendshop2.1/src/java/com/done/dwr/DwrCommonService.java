package com.done.dwr;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import bingosoft.jcf.util.AppUtils;
import bingosoft.jcf.util.FileProcessor;

import com.done.common.MyLeagueEnum;
import com.done.entity.Item;
import com.done.entity.Model;
import com.done.entity.ModelType;
import com.done.model.Hw;
import com.done.model.Indexjpg;
import com.done.model.Myleague;
import com.done.service.AdminService;
import com.done.service.BusinessService;
import com.done.util.RealPathUtil;
import com.done.util.SessionUserManagement;

/**
 * 2000-02-28
 * 
 * @author hewq ReadMe:DWR服务基类 ChangeLog:
 */
public class DwrCommonService {
    private static Log log = LogFactory.getLog(DwrCommonService.class);
    private BusinessService businessService;
    private AdminService adminService;

	public String getSessionId() {
        WebContext webContext = WebContextFactory.get();
        System.out.println(webContext.getSession().getId());
        return webContext.getSession().getId();
    }

    public List<Indexjpg> getIndexJpg(String userName) {

        return businessService.getIndexJpeg(userName);

    }

    public String deleteSub(Integer subId, String userName) {
        if (userName == null) {
            return "fail";
        }
        boolean result = businessService.deleteSub(subId, userName);
        if (result) {
            return null;
        } else {
            return "fail";
        }

    }

    public String adminDeleteSub(Integer subId, String userName) {
        if (userName == null) {
            return "fail";
        }
        boolean result = businessService.adminDeleteSub(subId, userName);
        if (result) {
            return null;
        } else {
            return "fail";
        }

    }

    public String processSub(Integer subId, String userName) {
        if (userName == null) {
            return "fail";
        }
        boolean result = businessService.processSub(subId, userName);
        if (result) {
            return null;
        } else {
            return "fail";
        }

    }

    public void setBusinessService(BusinessService businessService) {
        this.businessService = businessService;
    }

    /**
     * 删除产品
     * 
     * @param subId
     * @param userName
     * @return
     */
    public String deleteHw(Integer hwId) {
        if (hwId == null) {
            return "fail";
        }
        WebContext webContext = WebContextFactory.get();
        String realPath = RealPathUtil.getRealPath(webContext.getServletContext());
        try {
            Hw hw = businessService.loadHw(hwId);
            adminService.deleteById(Hw.class, hwId);
            String picUrl = realPath + hw.getHwPic();
            log.debug("delete file " + picUrl);
            FileProcessor.deleteFile(realPath + hw.getHwPic());
            return null;
        } catch (Exception e) {
            log.error("deleteHw", e);
            return "fail";
        }

    }

    /**
     * 产品上线
     * 
     * @param hwId
     * @return
     */
    public String hwOnline(Integer hwId) {
        if (hwId == null) {
            return "fail";
        }
        try {
            if (adminService.hwOnline(hwId)) {
                return null;
            } else {
                return "fail";
            }
        } catch (Exception e) {
            log.error("deleteHw", e);
            return "fail";
        }

    }

    /**
     * 产品下线
     * 
     * @param hwId
     * @return
     */
    public String hwOffline(Integer hwId) {
        if (hwId == null) {
            return "fail";
        }
        try {
            if (adminService.hwOffline(hwId)) {
                return null;
            } else {
                return "fail";
            }
        } catch (Exception e) {
            log.error("deleteHw", e);
            return "fail";
        }

    }

    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    //用户是否存在，true存在，false不存在
    public boolean isUserExist(String userName) {
        return businessService.isUserExist(userName);
    }

    /**
     * 删除用户
     * 
     * @param subId
     * @param userName
     * @return
     */
    public String deleteUserDetail(String userId, String userName) {
        if (userId == null) {
            return "fail";
        }
        try {
            return adminService.deleteUserDetail(userId, userName);
        } catch (Exception e) {
            log.error("deleteHw", e);
            return "fail";
        }

    }

    public List<Model> loadDynamic(String hwId) {
        Model mod1 = new Model();
        mod1.setId("1");
        mod1.setType(ModelType.Text);

        Item item1 = new Item();
        item1.setNullEnabled(true);
        item1.setValue(null);
        item1.setKey("key1");

        Item item2 = new Item();
        item2.setNullEnabled(true);
        item2.setValue("value2");
        item2.setKey("key2");
        mod1.setItems(new Item[] { item1, item2 });
        Model mod2 = new Model();
        mod2.setId("2");
        mod2.setType(ModelType.CheckBox);

        Item item3 = new Item();
        item3.setNullEnabled(true);
        item3.setValue("value3");
        item3.setKey("key3");
        mod2.setItems(new Item[] { item1, item2, item3 });

        Model mod3 = mod2.clone();
        mod3.setType(ModelType.Radio);
        Model mod4 = mod2.clone();
        mod4.setType(ModelType.Select);

        List<Model> list = new ArrayList<Model>();
        list.add(mod1);
        list.add(mod2);
        list.add(mod3);
        list.add(mod4);
        System.out.println("loadDynamic calling hwId = " + hwId);
        return list;
    }

    public boolean saveDynamic(Model[] model) {
        if (model != null) {
            System.out.println("model.length = " + model.length);
            JSONArray json = JSONArray.fromObject(model);
            String result = json.toString();
            System.out.println(result);
        }

        return true;
    }
    /**
     * 
     * @param userName
     * @param shopName
     * @return
     */
    public Integer addMyLeague(String userName,String shopName,String sitename){
    	if(AppUtils.isBlank(userName)||AppUtils.isBlank( shopName)){
    		return MyLeagueEnum.ERROR.getNum();
    	}
    	if(userName.equals(shopName)){
    		return MyLeagueEnum.THESAME.getNum();
    	}
    	Myleague  myleague = businessService.findMyleagueByUserNameAndShopName(userName, shopName);
    	if(!AppUtils.isBlank(myleague)){
    		return MyLeagueEnum.DONE.getNum();
    	}
    	myleague = new Myleague();
    	myleague.setAddtime(new Date());
    	myleague.setFriendId(shopName);
    	myleague.setStatus(MyLeagueEnum.ONGOING.getNum());
    	myleague.setFriendName(sitename);
    	myleague.setUserId(userName);
    	businessService.saveMyleague(myleague);
    	return MyLeagueEnum.ONGOING.getNum();
    }
    
    /**
     * 回复留言
     * @param subId
     * @param userName
     * @return
     */
    public String answerWord(Integer id, String answer,String loginName) {
        if (id == null) {
            return "fail";
        }
        boolean result = businessService.answreWord(id, loginName,answer);
        if (result) {
            return null;
        } else {
            return "fail";
        }

    }
    
}
