/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.common.download;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.legendshop.command.framework.State;
import com.legendshop.command.framework.StateImpl;
import com.legendshop.core.UserManager;
import com.legendshop.core.constant.FunctionEnum;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.model.entity.DownloadLog;
import com.legendshop.util.AppUtils;
import com.legendshop.util.ContextServiceLocator;
import com.legendshop.util.ip.IPSeeker;

/**
 * 下载Servlet.
 */
public class DownloadFileServlet extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6658159370029368321L;
	
	/** The log. */
	protected Logger log = LoggerFactory.getLogger(DownloadFileServlet.class);
	
	/** The file path. */
	String filePath = PropertiesUtil.getDownloadFilePath();

	/**
	 * Instantiates a new download file servlet.
	 */
	public DownloadFileServlet() {

	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String ipAttr = request.getRemoteAddr();
		response.setContentType("text/html; charset=UTF-8");

		String filename = request.getPathInfo();
		if (!AppUtils.isBlank(filename)) {
			filename = filename.substring(1);// 去掉前面的/
			State state = checkFunction(request, filename);
			if (!state.isOK()) {
				errorFound(response, state.getErrCode());
				log.error("{} attempt to download file {}, but without function", ipAttr, filename);
				return;
			}

			String fullName = filePath + "/" + filename;
			File file = new File(fullName);
			if (file.isFile()) {
				// 检测下载次数，防止攻击
				if (getDownloadLogService().checkCanDownload(ipAttr, filename)) {
					errorFound(response, "超过下载次数");
				} else {
					log.info("{} download file {}", ipAttr, filename);
					String name = filename;
					if (filename.lastIndexOf("/") > -1) {
						name = filename.substring(filename.lastIndexOf("/") + 1);
					}
					DownloadLog downloadLog = new DownloadLog();
					downloadLog.setDate(new Date());
					downloadLog.setFileName(filename);
					downloadLog.setIp(ipAttr);
					downloadLog.setUserName(UserManager.getUsername(request.getSession()));
					downloadLog.setArea(IPSeeker.getInstance().getArea(downloadLog.getIp()));
					downloadLog.setCountry(IPSeeker.getInstance().getCountry(downloadLog.getIp()));
					downloadLog.setShopName(ThreadLocalContext.getCurrentShopName(request, response));

					DownloadFileUtil.getInstance().downloadFile(response, fullName, name, true,
							getDownloadLogService(), downloadLog);
				}

			} else {
				errorFound(response, "Could not get file name");
			}
		} else {
			errorFound(response, "Could not get file name");
		}

	}

	/**
	 * true:通过 false：不通过.
	 * 
	 * @param request
	 *            the request
	 * @param pathInfo
	 *            the path info
	 * @return the state
	 */
	private State checkFunction(HttpServletRequest request, String pathInfo) {
		State state = new StateImpl();
		String info = pathInfo;
		int pos = pathInfo.lastIndexOf("/");
		if (pos > -1) {
			info = pathInfo.substring(0, pos);
		}
		if (!AppUtils.isBlank(info)) {
			if (info.indexOf("secured") > -1) {

				if (!UserManager.hasFunction(request.getSession(), FunctionEnum.FUNCTION_SECURED.value())) {
					state.setErrCode("你还没有获得访问该目录的权限，请与管理员联系");
				}
			}
			if (info.indexOf("securest") > -1) {
				if (!UserManager.hasFunction(request.getSession(), FunctionEnum.FUNCTION_SECUREST.value())) {
					state.setErrCode("你还没有获得访问该保密目录的权限，请与管理员联系");
				}
			}

			if (info.indexOf("order") > -1) {
				String userName = UserManager.getUsername(request);
				if (AppUtils.isBlank(userName)) {
					state.setErrCode("你还没有登录系，不能下载");
				}
				// 格式必须是order/1234567890/xxxxx
				String prodId = info.substring(info.indexOf("order/") + 6, info.length());
				if (prodId.indexOf("/") > -1) {
					prodId = prodId.substring(0, prodId.indexOf("/"));
				}
				if (!getDownloadLogService().isUserOrderProduct(Long.valueOf(prodId), userName)) {
					state.setErrCode("你还没有购买该商品，不能下载");
				}
			}
		}
		return state;
	}

	/**
	 * Error found.
	 * 
	 * @param response
	 *            the response
	 * @param cause
	 *            the cause
	 */
	private void errorFound(HttpServletResponse response, String cause) {
		PrintWriter printwriter = null;
		try {
			response.setContentType("text/html");
			printwriter = response.getWriter();
			printwriter.println("<html>");
			printwriter.println("<br><br> " + cause);
			printwriter.println("</html>");
		} catch (Exception e) {
		} finally {
			if (printwriter != null) {
				printwriter.flush();
				printwriter.close();
				try {
					response.flushBuffer();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	/**
	 * Gets the download log service.
	 * 
	 * @return the download log service
	 */
	public DownLoadCallBack getDownloadLogService() {
		return (DownLoadCallBack) ContextServiceLocator.getInstance().getBean("downloadLogService");
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		String info = "D:/AppServer/apache-tomcat-6.0.18/upload/order/3454/sxdf";
		String prodId = info.substring(info.indexOf("order/") + 6, info.length());
		System.out.println(prodId);
		prodId = prodId.substring(0, prodId.indexOf("/"));
		System.out.println(prodId);
	}
}
