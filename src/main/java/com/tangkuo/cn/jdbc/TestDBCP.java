package com.tangkuo.cn.jdbc;

import java.sql.Connection;

import org.apache.commons.dbcp.BasicDataSource;

public class TestDBCP {
	public static void main(String[] args) throws Exception {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("oracle.jdbc.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@192.168.0.1:1521:tk");
		ds.setUsername("tk");
		ds.setPassword("tk");

		ds.setInitialSize(5);  //�������ӳصĴ�С
		ds.setMaxActive(100);  //�������ӳ�������Զ����ӵĸ���
		ds.setMaxIdle(20);     //���������е����ӳ�

		Connection con = ds.getConnection(); 
                //���ص���con����������
		System.out.println(con.getClass());
		con.close();
		

	}
}
