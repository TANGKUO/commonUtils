package com.tangkuo.cn.db.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.tangkuo.cn.db.mongodb.Configuration;

/**
 * 
 * @author 61650
 *
 */
public class OracleDBConnection {
	static Connection conn = null;
	private static String driverName = null;  
	private static String url = null;  
	private static String userName = null;  
	private static String userPassword = null;  

	protected OracleDBConnection() {
	}

	static {
		try {
//			Configuration objConfiguration = new Configuration();
			driverName = Configuration.getValue("oracle.db.driverClassName");
			url = Configuration.getValue("oracle.db.url");
			userName = Configuration.getValue("oracle.db.username");
			userPassword = Configuration.getValue("oracle.db.password");
			Class.forName(driverName.toString()).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException {
		conn = DriverManager.getConnection(url, userName, userPassword);
		return conn;
	}

	public static ResultSet executeQuery(String sql, Connection con) {
		Statement stm = null;
		ResultSet rs = null;
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	public static boolean execute(String sql) throws SQLException,
			InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		return execute(sql, getConnection());
	}

	public static boolean execute(String sql, Connection con)
			throws SQLException {
		boolean ret = false;
		Statement stm = null;
		try {
			stm = con.createStatement();
			ret = stm.execute(sql);

		} catch (SQLException e) {
			throw e;
		} finally {
			closeStatement(stm);
			closeCon(con);
		}
		return ret;
	}

	public static void closeCon(Connection con) {
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeStatement(Statement sm) {
		try {
			if (sm != null)
				sm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeRs(ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static boolean executeKeepCon(String sql, Connection con)
			throws SQLException {
		boolean ret = false;
		Statement stm = null;
		try {
			stm = con.createStatement();
			ret = stm.execute(sql);
		} catch (SQLException e) {
			throw e;
		} finally {
			closeStatement(stm);
		}
		return ret;
	}

}
