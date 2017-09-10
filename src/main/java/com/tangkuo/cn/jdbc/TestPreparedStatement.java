package com.tangkuo.cn.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestPreparedStatement {
	public static void main(String[] args) throws Exception {
		Class.forName("oracle.jdbc.OracleDriver");
		Connection conn = DriverManager.getConnection(
				"jdbc:oracle:thin:@192.168.0.26:1521:tk", "tk", "tk");
		// Statement stmt = conn.createStatement();
		// ResultSet rs = stmt.executeQuery("sql");
		PreparedStatement stmt = conn
				.prepareStatement("select id, account_id, os_username "
						+ "from service where os_username=? and login_passwd=?");
		stmt.setString(1, "a");
		stmt.setString(2, "b' or 'a'='a");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getInt("id") + "," + rs.getInt("account_id") + ","
					+ rs.getString("os_username"));
		}

		rs.close();
		stmt.close();
		conn.close();

	}
}
