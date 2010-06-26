package com.done.fckeditor;

import javax.servlet.http.HttpServletRequest;

import net.fckeditor.requestcycle.impl.ServerRootPathBuilder;

/**
 * 路径
 */
public class MyContextPathBuilder extends ServerRootPathBuilder {

    @Override
    public String getUserFilesPath(HttpServletRequest request) {
        //在此可以根据从session中读取的用户名或者ID来对应其可以操作的目录
        String userName = (String) request.getSession().getAttribute("userName");
        if (userName == null) {
            throw new RuntimeException("You are no logging!");
        }
        String s = super.getUserFilesPath(request) + "/" + userName + "/fckeditor";
        return s;
    }

    @Override
    public String getUserFilesAbsolutePath(HttpServletRequest request) {
        String userName = (String) request.getSession().getAttribute("userName");
        if (userName == null) {
            throw new RuntimeException("You are no logging!");
        }
        String s = super.getUserFilesAbsolutePath(request) + "/" + userName + "/fckeditor";
        return s;
    }

}
