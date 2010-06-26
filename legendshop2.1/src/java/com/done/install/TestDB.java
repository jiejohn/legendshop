package com.done.install;

import java.sql.Connection;
import java.sql.DatabaseMetaData;

import javax.servlet.http.HttpServletRequest;

public class TestDB {
	private Integer CONN_NORMAL = 0;
	private Integer VERSON_ERR = 1;
	private Integer CONN_ERR = 2;
	
	public DbInfo testDB(HttpServletRequest request){
		DbInfo info = new DbInfo();
		String sDBDriver = request.getParameter("jdbc_driver");
		String sConnStr = request.getParameter("jdbc_url");
		String User = request.getParameter("jdbc_username");
		String Pass = request.getParameter("jdbc_password");
		try {
			DBManager dBManager = new DBManager(sDBDriver, sConnStr, User, Pass);
			Connection conn = dBManager.getConnection();
			DatabaseMetaData databaseMetaData = conn.getMetaData();
			int ver =databaseMetaData.getDatabaseMajorVersion();
			if(ver < 4){
				info.setResult(VERSON_ERR);
				info.setDesc("Legend Shop 不支持MySql4");
			}else{
				info.setResult(CONN_NORMAL);
				info.setDesc("当前MySQL版本是："+databaseMetaData.getDatabaseProductVersion());
			}
		} catch (Exception e) {
			info.setResult(CONN_ERR);
			info.setDesc(e.getMessage());
		}

		return info;
	}
}
