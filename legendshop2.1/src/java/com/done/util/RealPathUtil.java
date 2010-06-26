package com.done.util;

import javax.servlet.ServletContext;

import bingosoft.jcf.util.EnvironmentConfig;

public class RealPathUtil {
    /**
     * //取当前系统路径
     * 
     * @param request
     * @return
     */
    public static String getRealPath(ServletContext context) {
        String path = EnvironmentConfig.getInstance().getPropertyValue("fckeditor.properties",
                "connector.userFilesAbsolutePath");
        if (path == null) {
            if (context != null) {
                path = context.getRealPath("/") + "image";
            }
        }
        return path;
    }

    /**
     * //取缩略图当前系统路径
     * 
     * @param request
     * @return
     */
    public static String getSmallPath(ServletContext context) {
        String path = EnvironmentConfig.getInstance().getPropertyValue("config/common.properties",
                "connector.smallFilesAbsolutePath");
        if (path == null) {
            if (context != null) {
                path = context.getRealPath("/") + "images";
            }
        }
        return path;
    }

    /**
     * //取当FCKEditor文件系统路径
     * 
     * @param request
     * @return
     */
    public static String getFCKRealPath(ServletContext context, String filePath) {
        String userFilesAbsolutePath = EnvironmentConfig.getInstance().getPropertyValue("fckeditor.properties",
                "connector.userFilesAbsolutePath");
        String userFilesPath = EnvironmentConfig.getInstance().getPropertyValue("fckeditor.properties",
                "connector.userFilesPath");
        String path = null;
        if ((filePath != null) && (userFilesPath != null)) {
            path = filePath.substring(userFilesPath.length() + 1);
        }
        if (userFilesAbsolutePath == null) {
            if (context != null) {
                userFilesAbsolutePath = context.getRealPath("/") + "images";
            }
        }
        System.out.println(" getFCKRealPath = " + userFilesAbsolutePath + path);
        return userFilesAbsolutePath + path;
    }
}
