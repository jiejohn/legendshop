package com.done.install;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import bingosoft.jcf.util.AppUtils;
import bingosoft.jcf.util.EnvironmentConfig;
import bingosoft.jcf.util.FileProcessor;
import bingosoft.jcf.util.StringUtil;


public class Setup {
	private DBManager dBManager = null;
	private String sDBDriver="com.mysql.jdbc.Driver";
	private String sConnStr;
	private String User;
	private String Pass;
	private String domainName;
	private String SEPARATOR = "/";
	
	
	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	private Setup(){
		
	}

	public Setup(String jdbc_username, String jdbc_password ,String dbname,String dbhost,String dbport,String domainName){
		this.User = jdbc_username;
		this.Pass = jdbc_password;
		this.domainName = domainName;
		this.sConnStr = "jdbc:mysql://"+dbhost+":"+dbport+"/"+dbname+"?characterEncoding=UTF-8";
		dBManager = new DBManager(sDBDriver, sConnStr, User, Pass);
	}

	public void startSetup(HttpServletRequest request){
		String realPath = StringUtils.replace(request.getSession().getServletContext().getRealPath("/"),"\\","/");
		System.out.println("realPath " + realPath);
		
		//1.更改配置文件
		try {
			changePropertiesFile(realPath,request);
		} catch (Exception e) {
			throw new PropertiesException();
		}
		
		
		//2.更改运行时参数
		request.getSession().getServletContext().setAttribute("DOMAIN_NAME", domainName);
		try {
			//3.建立数据库
			String filePath = realPath + "/install/legendshop.sql";
			 String sql = readFile(filePath);
			if(!AppUtils.isBlank(sql)){
				dBManager.batchUpdate(sql.split(";"));
			}
		} catch (Exception e) {
			throw new DBException();
		}

		//4.server reload
	}
	
	public boolean changePropertiesFile(String realPath,HttpServletRequest request){
		String contextPath = request.getContextPath();
		//classes/config/jdbc.properties
		String file = realPath + "WEB-INF/classes/config/jdbc.properties";
		Map<String, String> map = new HashMap<String, String>();
		map.put("jdbc.url", sConnStr);
		map.put("jdbc.username", User);
		map.put("jdbc.password", Pass);
		map.put("alias", User);
		EnvironmentConfig.getInstance().writeProperties(file, map);
		
		//classes/config/acegi-cas.properties
		file = realPath + "WEB-INF/classes/config/acegi-cas.properties";
		map = new HashMap<String, String>();
		map.put("cas.server.url", domainName+"cas");
		if(AppUtils.isBlank(contextPath)){
			map.put("cas.service.url", domainName);
		}else{
			map.put("cas.service.url", domainName + contextPath.substring(1));
		}
		
		EnvironmentConfig.getInstance().writeProperties(file, map);
		
		//classes/config/common.properties
		file = realPath + "WEB-INF/classes/config/common.properties";
		map = new HashMap<String, String>();
		map.put("DownloadPath", realPath+"dowonload");
		map.put("connector.smallFilesAbsolutePath", getWepAppsPath(realPath,contextPath, "photoserver/smallImage"));
		EnvironmentConfig.getInstance().writeProperties(file, map);
		
		//classes/config/common.properties
		file = realPath + "WEB-INF/classes/fckeditor.properties";
		map = new HashMap<String, String>();
		String userFilesAbsolutePath = "connector.userFilesAbsolutePath";
		map.put(userFilesAbsolutePath, getWepAppsPath(realPath,contextPath,"photoserver/bigImage"));
		//request.getSession().getServletContext().setAttribute("PHOTO_PATH", map.get(userFilesPath));
		EnvironmentConfig.getInstance().writeProperties(file, map); 
		
		///////////////////cas classes/config/common.properties///////////////////////////////
		file = getWepAppsPath(realPath,contextPath, "cas/") + "WEB-INF/classes/config/common.properties";
		map = new HashMap<String, String>();
		int port = request.getServerPort();
		if(port == 80){
			map.put("cas.server.url", "http://"+request.getServerName() + "/cas");
		}else{
			map.put("cas.server.url", "http://"+request.getServerName() + ":"+request.getServerPort() + "/cas");
		}
		EnvironmentConfig.getInstance().writeProperties(file, map); 

		
		//cas WEB-INF/cas.properties
		file = getWepAppsPath(realPath,contextPath, "cas/") + "WEB-INF/cas.properties";
		map = new HashMap<String, String>();
		map.put("jdbc.url", sConnStr);
		map.put("jdbc.username", User);
		map.put("jdbc.password", Pass);
		map.put("alias", User);
		EnvironmentConfig.getInstance().writeProperties(file, map); 
		
		
		///////////////////////managerWeb classes/config/jdbc.properties//////////////////////
		String managerWeb =  getWepAppsPath(realPath,contextPath, "managerWeb/");
		file = managerWeb + "WEB-INF/classes/config.properties";
		map = new HashMap<String, String>();
		map.put("upLoadPath", managerWeb+"dowonload");
		EnvironmentConfig.getInstance().writeProperties(file, map); 
		
		file = managerWeb + "WEB-INF/classes/config/jdbc.properties";
		map = new HashMap<String, String>();
		map.put("jdbc.url", sConnStr);
		map.put("jdbc.username", User);
		map.put("jdbc.password", Pass);
		map.put("alias", User);
		EnvironmentConfig.getInstance().writeProperties(file, map); 
		
		file = managerWeb + "WEB-INF/classes/config/acegi-cas.properties";
		map = new HashMap<String, String>();
		map.put("cas.server.url", domainName+"cas");
		map.put("cas.service.url", domainName + "managerWeb");
		EnvironmentConfig.getInstance().writeProperties(file, map); 		
		
		return true;
	}
	
    private String getWepAppsPath(String path,String context,String subPath) {
        if (path == null) {
            return null;
        }
        String filePath = removeContextPath(path,context) + subPath;
        File dir = new File(filePath);
        if (!dir.exists()) {
        	dir.mkdirs();
        }
        return filePath;
    }
    
    private String removeContextPath(String path,String context){
    	if(AppUtils.isBlank(context)){
    		context="/ROOT";
    	}
    	String result = path.substring(0,path.length() - context.length());
    	System.out.println("removeContextPath = " +result +" , context = " + context);
    	return result;
    }
	
    private String getSubPath(String path) {
        if (path == null) {
            return null;
        }
        int last = path.lastIndexOf(SEPARATOR);
        String result = path.substring(0, last);
        int last2 = result.lastIndexOf(SEPARATOR);
        return result.substring(last2 + 1);
    }
	
	public  String readFile(String inFileName) {
        StringBuffer sb = new StringBuffer();
        String ret = "";

        try {
            File inFile = new File(inFileName);
            if (!inFile.exists()) {
            	System.out.println(inFileName + " 文件不存在");
                return sb.toString();
            }
            FileInputStream fileInputStream = new FileInputStream(inFile);
            BufferedReader inBufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));
            String strLine = "";

            while ((strLine = inBufferedReader.readLine()) != null) {

                if ((strLine != null) && !"".equals(strLine)) {
                    sb.append(strLine);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
	
	public static void main(String[] args) {
		Setup setup = new Setup();
		setup.changePropertiesFile("D:/AppServer/apache-tomcat-6.0.26/webapps/ROOT/",null);
	}
	
}
