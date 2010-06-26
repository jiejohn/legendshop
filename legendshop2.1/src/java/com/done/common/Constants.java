package com.done.common;

import bingosoft.jcf.sql.ConfigCode;
import bingosoft.jcf.util.EnvironmentConfig;

/**
 * 常量表
 * 
 * @author HEWQ. 2009-3-30
 */
public class Constants {
    /**
     * 服务支持
     */
    public static final String CUSTOMER_SUPPORT = "gmhwq@163.com";

    public static final String SHOP_NAME = "shopName";

    /**
     * 分页每页大小
     */
    public static final int PAGE_SIZE = 16;

    public static final String DEFAULT_SHOP = EnvironmentConfig.getInstance().getPropertyValue("config/common.properties","DEFAULT_SHOP");

    //普通谈话
    public static final Integer COMMONTALK = 2;
    //投诉
    public static final Integer COMPLAIN = 1;
    //消息未读
    public static final Integer COMMENT_UN_READ = 0;
    //消息已读
    public static final Integer COMMENT_READED = 1;
    //产品正常上线状态
    public static final Integer HW_ONLINE = 1;

    //产品下线状态
    public static final Integer HW_OFFLINE = 0;
    //普通用户权限
    public static final String ROLE_USER = "3";

    //管理员权限
    public static final String ROLE_Admin = "2";

    //超级管理员权限
    public static final String ROLE_SUPERVISOR = "1";

    //查看所有数据的权限
    public static final String FUNCTION_VIEW_ALL_DATA = "F_VIEW_ALL_DATA";

    public static final String FUNCTION_SECURED = "F_SECURED";

    public static final String FUNCTION_SECUREST = "F_SECUREST";
    //商店上线状态
    public static final Integer SHOP_ONLINE = 1;
    //商店下线状态
    public static final Integer SHOP_OFFLINE = 0;

    public static final Integer ONLINE = 1;

    public static final Integer OFFLINE = 0;
    
    public static final String LOCALE_FILE = "i18n/ApplicationResources";

}
