package com.done.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.done.common.Constants;
import com.done.util.SessionUserManagement;

import bingosoft.jcf.util.AppUtils;
import bingosoft.jcf.util.EnvironmentConfig;
import bingosoft.jcf.util.FileConfig;

public class DownloadFileServlet extends HttpServlet {
	protected Logger log = LoggerFactory.getLogger(DownloadFileServlet.class);
    String filePath = EnvironmentConfig.getInstance().getPropertyValue(FileConfig.ConfigFile, "DownloadPath");

    public DownloadFileServlet() {

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String ipAttr = request.getRemoteAddr();
        response.setContentType("text/html; charset=UTF-8");
        String filename = request.getPathInfo();
        if (!AppUtils.isBlank(filename)) {
            filename = filename.substring(1);//去掉前面的/
            if (!checkFunction(request, filename)) {
                errorFound(response, "你还没有获得访问该目录的权限，请与管理员联系");
                log.error("{} attempt to download file {}, but without function" ,ipAttr , filename);
                return;
            }
            log.info("{} download file {}" ,ipAttr , filename);
            String fullName = filePath + filename;
            File file = new File(fullName);
            if (file.isFile()) {
                String name = filename;
                if (filename.lastIndexOf("/") > -1) {
                    name = filename.substring(filename.lastIndexOf("/") + 1);
                }
                DownloadFileUtil.getInstance().downloadFile(response, fullName, name, true);
            } else {
                errorFound(response, "Could not get file name");
            }
        } else {
            errorFound(response, "Could not get file name");
        }

    }

    /**
     * true:通过 false：不通过
     * 
     * @param pathInfo
     * @return
     */
    private boolean checkFunction(HttpServletRequest request, String pathInfo) {
        boolean result = true;
        String info = pathInfo;
        int pos = pathInfo.lastIndexOf("/");
        if (pos > -1) {
            info = pathInfo.substring(0, pos);
        }
        if (!AppUtils.isBlank(info)) {
            if (info.indexOf("secured") > -1) {

                if (!SessionUserManagement.hasFunction(request.getSession(), Constants.FUNCTION_SECURED)) {
                    result = false;
                }
            }
            if (info.indexOf("securest") > -1) {
                if (!SessionUserManagement.hasFunction(request.getSession(), Constants.FUNCTION_SECUREST)) {
                    result = false;
                }
            }
        }
        return result;
    }

    private void errorFound(HttpServletResponse response, String cause) {
        PrintWriter printwriter = null;
        try {
            response.setContentType("text/html");
            printwriter = response.getWriter();
            printwriter.println("<html>");
            printwriter.println("<br><br> " + cause);
            printwriter.println("<br><br>Eamil ： <a href=\"mailto:legendesign@legendesign.com\">LEGENDESIGN</a>");
            printwriter.println("<br>QQ ：  15151191 <br> QQ讨论群 ：96642931");
            printwriter.println("</html>");
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (printwriter != null) {
                printwriter.flush();
                printwriter.close();
                try {
                    response.flushBuffer();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
