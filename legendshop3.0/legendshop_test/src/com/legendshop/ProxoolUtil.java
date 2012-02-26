package com.legendshop;

import java.io.InputStreamReader;
import java.sql.SQLException;

import org.logicalcobwebs.proxool.ProxoolDataSource;
import org.logicalcobwebs.proxool.ProxoolException;
import org.logicalcobwebs.proxool.configuration.JAXPConfigurator;
import org.springframework.jdbc.datasource.embedded.DataSourceFactory;

public class ProxoolUtil {
	public static void main(String[] args) {
		/**
		 * 普通配置文件实现方式，用的是proxool.xml的配置
		 */
		ProxoolDataSource proxoolDataSource = null;
		try {
			Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");
			JAXPConfigurator.configure(new InputStreamReader(DataSourceFactory.class.getClassLoader()
					.getResourceAsStream("proxool.xml")), false);

			proxoolDataSource = new ProxoolDataSource("db");
			proxoolDataSource.setDelegateProperties("user='root',password='gmhewq'");
			// proxoolDataSource.setUser("root1");
			// proxoolDataSource.setDriver("com.mysql.jdbc.Driver");
			// proxoolDataSource.setDriverUrl("jdbc:mysql://localhost:3306/legendshop?characterEncoding=UTF-8");
			// proxoolDataSource.setPassword("gmhewq");

			System.out.println("proxoolDataSource = " + proxoolDataSource);
			System.out.println("Connection = " + proxoolDataSource.getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (ProxoolException e) {
			e.printStackTrace();
		}

	}
}
