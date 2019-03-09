package com.tangkuo.cn.db.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DBConnectionPool {
	
	private final static Logger logger = LoggerFactory.getLogger(DBConnectionPool.class);
	private int checkedOut;
	private Vector freeConnections = new Vector();
	private int maxConn;
	private int normalConn;
	private String password;
	private String url;
	private String user;
	private static int num = 0;// 空闲的连接数
	private static int numActive = 0;// 当前的连接数
	private int freeConn;
	public DBConnectionPool(String password, String url, String user,
	                          int normalConn, int maxConn) {
	    this.password = password;
	    this.url = url;
	    this.user = user;
	    this.maxConn = maxConn;
	    this.normalConn = normalConn;
	    this.freeConn = maxConn+10;
	    for (int i = 0; i < normalConn; i++) { //初始normalConn个连接
	      Connection c = newConnection();
	      if (c != null)
	        {freeConnections.addElement(c);num++;}
	    }
	  }

	// 释放不用的连接到连接池
	public synchronized void freeConnection(Connection con) {
		freeConnections.addElement(con);
		num++;
		checkedOut--;
		numActive--;
		notifyAll();
	}

	// 获取一个可用连接
	public synchronized Connection getConnection() {
		Connection con = null;

		if (freeConnections.size() > 0) { // 还有空闲的连接
			num--;

			con = (Connection) freeConnections.firstElement();
			freeConnections.removeElementAt(0);
			try {
				if (con.isClosed()) {
					logger.debug("从连接池删除一个无效连接");
					con = getConnection();
				}
			} catch (SQLException e) {
				logger.debug("从连接池删除一个无效连接");
				con = getConnection();
			}
		}

		else if (maxConn == 0 || checkedOut < maxConn) { // 没有空闲连接且当前连接小于最大允许值,最大值为0则不限制
			con = newConnection();
		}

		if (con != null) { // 当前连接数加1
			checkedOut++;
		}

		numActive++;
		
		if(numActive>=maxConn || numActive<0 || freeConnections.size()>=freeConn){//超出总连接数 或者当前总连接数为负数 则释放链接重新创建
			release();//清除所有链接  重新创建链接
			for (int i = 0; i < normalConn; i++) { //初始normalConn个连接
			      Connection c = newConnection();
			      if (c != null)
			        {freeConnections.addElement(c);
			        num++;
			      }
			}
		}
		
		return con;

	}

	// 获取一个连接,并加上等待时间限制,时间为毫秒
	public synchronized Connection getConnection(long timeout) {
		long startTime = new Date().getTime();
		Connection con;
		while ((con = getConnection()) == null) {

			try {
				wait(timeout);
			} catch (InterruptedException e) {
			}

			if ((new Date().getTime() - startTime) >= timeout) {
				return null; // 超时返回
			}
		}
		return con;
	}

	// 关闭所有连接
	public synchronized void release() {
		Enumeration allConnections = freeConnections.elements();
		while (allConnections.hasMoreElements()) {
			Connection con = (Connection) allConnections.nextElement();
			try {
				con.close();
				num--;
			} catch (SQLException e) {
				logger.debug("无法关闭连接池中的连接");
			}
		}
		freeConnections.removeAllElements();
		numActive = 0;
	}

	// 创建一个新连接
	private Connection newConnection() {
		Connection con = null;
		try {
			if (user == null) { // 用户,密码都为空
				con = DriverManager.getConnection(url);
			} else {
				con = DriverManager.getConnection(url, user, password);
			}
//			 LogUtil.saveRtuLog("连接池创建一个新的连接");
		} catch (SQLException e) {
//			LogUtil.saveRtuLog("无法创建这个URL的连接" + url);
			return null;
		}
		return con;
	}

	// 返回当前空闲连接数
	public int getnum() {
		return num;
	}

	// 返回当前连接数
	public int getnumActive() {
		return numActive;
	}

	public static void main(String[] args) {
		
	}
}