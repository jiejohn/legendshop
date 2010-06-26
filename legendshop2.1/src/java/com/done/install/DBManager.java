package com.done.install;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import sun.jdbc.rowset.CachedRowSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBManager {
	Logger log = LoggerFactory.getLogger(DBManager.class);
	private static DBManager instance = null;
	private String sDBDriver;
	private String sConnStr;
	private String User;
	private String Pass;

	public DBManager(String sDBDriver, String sConnStr, String User, String Pass) {
		this.sDBDriver = sDBDriver;
		this.sConnStr = sConnStr;
		this.User = User;
		this.Pass = Pass;
		try {
			Class.forName(sDBDriver);
		} catch (ClassNotFoundException classnotfoundexception) {
			System.err.println("connectDB(): "
					+ classnotfoundexception.getMessage());
		}
	}

	public Connection getConnection() throws SQLException {
		DriverManager.setLoginTimeout(10);
		Connection conn = DriverManager.getConnection(sConnStr, User, Pass);
		return conn;
	}

	public Connection getConnection(String sConnStr, String User, String Pass)
			throws SQLException {
		return DriverManager.getConnection(sConnStr, User, Pass);
	}

	public void cleanup(Connection conn, Statement ps, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
		} catch (Exception e) {
			log.error("", e);

		}
		try {
			if (ps != null) {
				ps.close();
				ps = null;
			}
		} catch (Exception e) {
			log.error("", e);
		}
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
				conn = null;
			}
		} catch (Exception e) {
			log.error("", e);
		}
	}

	public CachedRowSet executeQuery(String sql, Object values[]) {
		CachedRowSet crs = null;
		PreparedStatement st = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			crs = new CachedRowSet();
			conn = getConnection();
			st = conn.prepareStatement(sql);
			for (int i = 0; values != null && i < values.length; i++)
				st.setObject(i + 1, values[i]);

			rs = st.executeQuery();
			crs.populate(rs);
		} catch (Exception se) {
			log.error("SQLException in DBManager.exceuteQuery, sql is :\r\n"
					+ sql, 2);
			log.error("", se);
		} finally {
			cleanup(conn, st, rs);
		}
		return crs;
	}
	public void batchUpdate(String as[]){
		Statement statement = null;
		Connection conn = null;
		try {
			conn = getConnection();
			statement = conn.createStatement();
			for (int i = 0; i < as.length; i++) {
				if (as[i] != null && !as[i].equals(""))
					statement.addBatch(as[i]);
			}
			statement.executeBatch();
		} catch (Exception e) {
			log.error("", e);
		}finally {
			cleanup(conn, statement, null);
		}
	}
	

}
