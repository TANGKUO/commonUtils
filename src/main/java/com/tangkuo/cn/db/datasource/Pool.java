package com.tangkuo.cn.db.datasource;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tangkuo.cn.db.mongodb.Configuration;
/**
 * 
 * @author 61650
 *
 */
public class Pool {
	private final static Logger logger = LoggerFactory.getLogger(Pool.class);
	static private Pool instance = null; // 定义唯一实例

	private int maxConnect = 200;// 最大连接数
	private int normalConnect = 30;// 保持连接数
	private String driverName = "";// 驱动类
	private String url = "";// 连接URL
	private String user = "";// 用户名
	private String password = "";// 密码
	Driver driver = null;// 驱动变量
	DBConnectionPool pool = null;// 连接池实例变量

	// 返回唯一实例
	static synchronized public Pool getInstance() {
		if (instance == null) {
			instance = new Pool();
		}
		return instance;
	}

	// 将构造函数私有,不允许外界访问
	private Pool() {
		driverName = Configuration.getValue("oracle.jdbc.driverClassName");
		url = Configuration.getValue("oracle.jdbc.url");
		user = Configuration.getValue("oracle.jdbc.username");
		password = Configuration.getValue("oracle.jdbc.password");
		maxConnect = Integer.parseInt(Configuration.getValue("oracle.jdbc.maxConnect"));
		normalConnect = Integer.parseInt(Configuration.getValue("oracle.jdbc.normalConnect"));
	    loadDrivers(driverName);
	    createPool();
	  }

	// 装载和注册所有JDBC驱动程序
	private void loadDrivers(String dri) {
		String driverClassName = dri;
		try {
			driver = (Driver) Class.forName(driverClassName).newInstance();
			DriverManager.registerDriver(driver);
		} catch (Exception e) {
//			 LogUtil.saveRtuLog("无法注册驱动程序:" + driverClassName + ",错误:" + e.getMessage());
		}
	}

	// 创建连接池
	private void createPool() {
		pool = new DBConnectionPool(password, url, user, normalConnect, maxConnect);
		if (pool != null) {
//			 LogUtil.saveRtuLog("创建连接池成功,最大连接数" + maxConnect+" 保持连接数:"+normalConnect);
		} else {
//			LogUtil.saveRtuLog("创建连接池失败");
//			LogUtil.saveRtuLog("创建连接池失败");
//			LogUtil.saveRtuLog("创建连接池失败");
		}
	}

	// 获得一个可用的连接,如果没有则创建一个连接,且小于最大连接限制
	public Connection getConnection() {
		if (pool != null) {
			return pool.getConnection();
		}
		return null;
	}

	// 获得一个连接,有时间限制
	public Connection getConnection(long time) {
		if (pool != null) {
			return pool.getConnection(time);
		}
		return null;
	}

	// 将连接对象返回给连接池
	public void freeConnection(Connection con) {
		if (pool != null) {
			pool.freeConnection(con);
			getnum();
			getnumActive();
		}
	}

	// 返回当前空闲连接数
	public int getnum() {
//		LogUtil.saveRtuLog("当前空闲连接数:"+pool.getnum());
		return pool.getnum();
	}

	// 返回当前连接数
	public int getnumActive() {
//		LogUtil.saveRtuLog("当前总连接数:"+pool.getnumActive());
		return pool.getnumActive();
	}

	// 关闭所有连接,撤销驱动注册
	public synchronized void release() {
		/// 关闭连接
		pool.release();
		/// 撤销驱动
		try {
			DriverManager.deregisterDriver(driver);
//			System.out.println("撤销JDBC驱动程序 " + driver.getClass().getName());
		} catch (SQLException e) {
//			System.out.println("无法撤销JDBC驱动程序的注册:" + driver.getClass().getName());
		}

	}
	public static void main(String[] args) {
		Pool p = Pool.getInstance();
		p.getConnection();
		System.out.println("空闲:"+p.getnum());
		System.out.println("连接数:"+p.getnumActive());
		p.getConnection();

		System.out.println("空闲:"+p.getnum());
		System.out.println("连接数:"+p.getnumActive());
		Connection con = p.getConnection();

		System.out.println("空闲:"+p.getnum());
		System.out.println("连接数:"+p.getnumActive());
		p.freeConnection(con);

		System.out.println("空闲:"+p.getnum());
		System.out.println("连接数:"+p.getnumActive());
		p.getConnection();

		System.out.println("空闲:"+p.getnum());
		System.out.println("连接数:"+p.getnumActive());
		p.getConnection();
		System.out.println("空闲:"+p.getnum());
		System.out.println("连接数:"+p.getnumActive());
		logger.info(p.toString());
	}
}
