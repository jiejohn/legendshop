package com.done.fckeditor;

import javax.servlet.http.HttpServletRequest;

import net.fckeditor.requestcycle.UserAction;

/**
 * 此类可以从session中读取用户信息，判断是否有权限进行相关操作
 */
public class MyEnabledUserAction implements UserAction {

    //权限--新建文件夹
    public boolean isCreateFolderEnabled(HttpServletRequest request) {
        //System.out.println("isCreateFolderEnabled calling");
        return true;
    }

    //权限--浏览服务器上的文件列表
    public boolean isEnabledForFileBrowsing(HttpServletRequest request) {
        //System.out.println("isEnabledForFileBrowsing calling");
        return true;
    }

    //权限--上传文件
    public boolean isEnabledForFileUpload(HttpServletRequest request) {
        //System.out.println("isEnabledForFileUpload calling");
        return true;
    }

}
