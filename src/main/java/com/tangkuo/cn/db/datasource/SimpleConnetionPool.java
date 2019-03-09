package com.tangkuo.cn.db.datasource;

import java.io.PrintStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import com.tangkuo.cn.db.mongodb.Configuration;

/**
 * 
 * @author 61650
 *
 */
public class SimpleConnetionPool {
	private static LinkedList m_notUsedConnection = new LinkedList();

	private static HashSet m_usedUsedConnection = new HashSet();

	private static String m_url = "";

	private static String m_user = "";

	private static String m_password = "";

	static final boolean DEBUG = true;

	static private long m_lastClearClosedConnection = System.currentTimeMillis();

	public static long CHECK_CLOSED_CONNECTION_TIME = 30 * 1000; //5分钟 后清除无用链接
	
	private static int maxConnection=0;//最大连接数   超过此连接数则不再创建链接
	
	static {
		initDriver();
		maxConnection = Integer.parseInt(Configuration.getValue("oracle.jdbc.maxConnect"));
	}


	private SimpleConnetionPool() {
	}

	private static void initDriver() {
		Driver driver = null;
		// load oracle driver
		try {
			driver = (Driver) Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			installDriver(driver);
		} catch (Exception e) {
		}

		// load postgresql driver
		try {
			driver = (Driver) Class.forName("org.postgresql.Driver").newInstance();
			installDriver(driver);
		} catch (Exception e) {
		}
	}

	public static void installDriver(Driver driver) {
		try {
			DriverManager.registerDriver(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized Connection getConnection() {
		clearClosedConnection();
//		LogUtil.saveRtuLog("Oracle总连接数    :"+getConnectionCount());
//		LogUtil.saveRtuLog("Oracle空闲连接数  :"+getNotUsedConnectionCount());
//		LogUtil.saveRtuLog("Oracle使用连接数  :"+getUsedConnectionCount());
		while (m_notUsedConnection.size() > 0) {
			try {
				ConnectionWrapper wrapper = (ConnectionWrapper) m_notUsedConnection.removeFirst();
				if (wrapper.connection.isClosed()) {
					continue;
				}
				m_usedUsedConnection.add(wrapper);
				if (DEBUG) {
					wrapper.debugInfo = new Throwable("Connection initial statement");
				}
				return wrapper.connection;
			} catch (Exception e) {
			}
		}
		int newCount = getIncreasingConnectionCount();
		LinkedList list = new LinkedList();
		ConnectionWrapper wrapper = null;
		for (int i = 0; i < newCount; i++) {
			wrapper = getNewConnection();
			if (wrapper != null) {
				list.add(wrapper);
			}
		}
		if (list.size() == 0) {
			return null;
		}
		wrapper = (ConnectionWrapper) list.removeFirst();
		m_usedUsedConnection.add(wrapper);

		m_notUsedConnection.addAll(list);
		list.clear();

		return wrapper.connection;
	}

	private static ConnectionWrapper getNewConnection() {
		try {
			if(getConnectionCount() < maxConnection){//当前总创建连接数 如果 小于最大连接数就创建链接
//				LogUtil.saveRtuLog(".............创建新的Oracle链接.............");
				Connection con = DriverManager.getConnection(m_url, m_user,m_password);
				ConnectionWrapper wrapper = new ConnectionWrapper(con);
				return wrapper;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	static synchronized void pushConnectionBackToPool(ConnectionWrapper con) {
		boolean exist = m_usedUsedConnection.remove(con);
		if (exist) {
			m_notUsedConnection.addLast(con);
		}
	}

	public static int close() {
		int count = 0;

		Iterator iterator = m_notUsedConnection.iterator();
		while (iterator.hasNext()) {
			try {
				((ConnectionWrapper) iterator.next()).close();
				count++;
			} catch (Exception e) {
			}
		}
		m_notUsedConnection.clear();

		iterator = m_usedUsedConnection.iterator();
		while (iterator.hasNext()) {
			try {
				ConnectionWrapper wrapper = (ConnectionWrapper) iterator.next();
				wrapper.close();
				if (DEBUG) {
					wrapper.debugInfo.printStackTrace();
				}
				count++;
			} catch (Exception e) {
			}
		}
		m_usedUsedConnection.clear();

		return count;
	}

	private static void clearClosedConnection() {
		long time = System.currentTimeMillis();
		// sometimes user change system time,just return
		if (time < m_lastClearClosedConnection) {
			time = m_lastClearClosedConnection;
			return;
		}
		// no need check very often
		if (time - m_lastClearClosedConnection < CHECK_CLOSED_CONNECTION_TIME) {
			return;
		}
		m_lastClearClosedConnection = time;

		// begin check
		Iterator iterator = m_notUsedConnection.iterator();
		int num=0;
		while (iterator.hasNext()) {
			ConnectionWrapper wrapper = (ConnectionWrapper) iterator.next();
			try {
//				LogUtil.saveRtuLog(".............开始清除无用链接.............");
				if (wrapper.connection.isClosed()) {
//					LogUtil.saveRtuLog(".............链接已关闭，马上清除.............");
					iterator.remove();
					num++;
				}
			} catch (Exception e) {
				iterator.remove();
				if (DEBUG) {
					System.out.println("connection is closed, this connection initial StackTrace");
					wrapper.debugInfo.printStackTrace();
				}
			}
		}
//		LogUtil.saveRtuLog("Oracle总连接数    :"+getConnectionCount());
//		LogUtil.saveRtuLog("Oracle空闲连接数  :"+getNotUsedConnectionCount());
//		LogUtil.saveRtuLog("Oracle使用连接数  :"+getUsedConnectionCount());
		
		// make connection pool size smaller if too big
		int decrease = getDecreasingConnectionCount();
		if (m_notUsedConnection.size() < decrease) {
			return;
		}

		while (decrease-- > 0) {
			ConnectionWrapper wrapper = (ConnectionWrapper) m_notUsedConnection.removeFirst();
			try {
				wrapper.connection.close();
			} catch (Exception e) {
			}
		}
	}

	public static int getIncreasingConnectionCount() {
		int count = 1;
		int current = getConnectionCount();
		count = current / 4;
		if (count < 1) {
			count = 1;
		}
		return count;
	}

	public static int getDecreasingConnectionCount() {
		int count = 0;
		int current = getConnectionCount();
		if (current < 10) {
			return 0;
		}
		return current / 3;
	}

	public synchronized static void printDebugMsg() {
		printDebugMsg(System.out);
	}

	public synchronized static void printDebugMsg(PrintStream out) {
		if (DEBUG == false) {
			return;
		}
		StringBuffer msg = new StringBuffer();
		msg.append("debug message in " + SimpleConnetionPool.class.getName());
		msg.append("\r\n");
		msg.append("total count is connection pool: " + getConnectionCount());
		msg.append("\r\n");
		msg.append("not used connection count: " + getNotUsedConnectionCount());
		msg.append("\r\n");
		msg.append("used connection, count: " + getUsedConnectionCount());
		out.println(msg);
		Iterator iterator = m_usedUsedConnection.iterator();
		while (iterator.hasNext()) {
			ConnectionWrapper wrapper = (ConnectionWrapper) iterator.next();
			wrapper.debugInfo.printStackTrace(out);
		}
		out.println();
	}

	public static synchronized int getNotUsedConnectionCount() {
		return m_notUsedConnection.size();
	}

	public static synchronized int getUsedConnectionCount() {
		return m_usedUsedConnection.size();
	}

	public static synchronized int getConnectionCount() {
		return m_notUsedConnection.size() + m_usedUsedConnection.size();
	}

	public static String getUrl() {
		return m_url;
	}

	public static void setUrl(String url) {
		if (url == null) {
			return;
		}
		m_url = url.trim();
	}

	public static String getUser() {
		return m_user;
	}

	public static void setUser(String user) {
		if (user == null) {
			return;
		}
		m_user = user.trim();
	}

	public static String getPassword() {
		return m_password;
	}

	public static void setPassword(String password) {
		if (password == null) {
			return;
		}
		m_password = password.trim();
	}

}

class ConnectionWrapper implements InvocationHandler {
	private final static String CLOSE_METHOD_NAME = "close";

	public Connection connection = null;

	private Connection m_originConnection = null;

	public long lastAccessTime = System.currentTimeMillis();

	Throwable debugInfo = new Throwable("Connection initial statement");

	ConnectionWrapper(Connection con) {
		this.connection = (Connection) Proxy.newProxyInstance(con.getClass().getClassLoader(), new Class[]{Connection.class}, this);
		m_originConnection = con;
	}

	void close() throws SQLException {
		m_originConnection.close();
	}

	public Object invoke(Object proxy, Method m, Object[] args)
			throws Throwable {
		Object obj = null;
		if (CLOSE_METHOD_NAME.equals(m.getName())) {
			SimpleConnetionPool.pushConnectionBackToPool(this);
		} else {
			obj = m.invoke(m_originConnection, args);
		}
		lastAccessTime = System.currentTimeMillis();
		return obj;
	}

}