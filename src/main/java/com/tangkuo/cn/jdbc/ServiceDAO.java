package com.tangkuo.cn.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



/**
 * ��װ��SERVICE���CRUD��
 * 
 * 
 */

public class ServiceDAO extends BaseDAO {
	/**
	 * ��ȡSERVICE���е�������
	 */
	public List<Service> findAll() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = openConnection();
			stmt = conn
					.prepareStatement("select ID, ACCOUNT_ID, UNIX_HOST, OS_USERNAME, LOGIN_PASSWD, CREATE_DATE from SERVICE");
			rs = stmt.executeQuery();

			return toList(rs);

		} catch (SQLException e) {
			System.out.println("���ݿ�����쳣" + e.getMessage());
			throw e;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
	}
                   private Connection openConnection() {
		// TODO Auto-generated method stub
		return null;
	}
	//�����û��������뷵�ض�Ӧ�����
	public List<Service> findByUsernameAndPwd(String username, String pwd)
			throws ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = openConnection();
			String sql = "select ID, ACCOUNT_ID, " + "UNIX_HOST, OS_USERNAME, "
					+ "LOGIN_PASSWD, CREATE_DATE " + "from SERVICE "
					+ "where OS_USERNAME=? and LOGIN_PASSWD=?";

			stmt = conn.prepareStatement(sql);

			stmt.setString(1, username);
			stmt.setString(2, pwd);

			rs = stmt.executeQuery();

			return toList(rs);

		} catch (SQLException e) {
			System.out.println("���ݿ�����쳣" + e.getMessage());
			throw e;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
	}
                           //��list�������м�¼
	private List<Service> toList(ResultSet rs) throws SQLException {
		List<Service> list = new ArrayList<Service>();
		while (rs.next()) {
			Service service = new Service();
			service.setId(rs.getInt("ID"));
			service.setAccountId(rs.getInt("ACCOUNT_ID"));
			service.setOsUserName(rs.getString("UNIX_HOST"));
			service.setLoginPasswd(rs.getString("LOGIN_PASSWD"));
			service.setCreateDate(rs.getDate("CREATE_DATE"));
			list.add(service);
		}
		return list;
	}

}
