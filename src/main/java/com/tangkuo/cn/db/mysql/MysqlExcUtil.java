package com.tangkuo.cn.db.mysql;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import com.tangkuo.cn.db.datasource.LogUtil;
import com.tangkuo.cn.db.oracle.OracleDBConnection;


public class MysqlExcUtil {
	public static void exeSqlQueue(){
		int num = 300;//批量sql语句
		while(true){
			Queue<String> queue = ServerData.mysqlQueue;
			if(queue!=null && queue.size() >= num){//当队列中大于设置的sql数量则执行
//				LogUtil.saveQueLog("sql总数0:"+queue.size());
				//System.out.println("sql总数-----------------:"+queue.size());
				int exeNum = queue.size();
				batchExeSql(queue, exeNum);
			}
			try {
				Thread.sleep(1500);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	
	
	public static void batchExeSql(Queue<String> queue,int exeNum){
        try {  
        	List<String> sqlList = new ArrayList<String>();
        	for(int i=0;i<exeNum;i++){
        		String sql = queue.poll();
    			if(sql == null || sql.equals("") || sql.equals("null")){
    				continue;
    			}else{
    				sqlList.add(sql);
    			}
//        		objSql[i]=sql;
        	}
        	
        	//异步保存
        	final String sqlArray[]=(String[])sqlList.toArray(new String[sqlList.size()]);
        	new Thread(new Runnable() {
        		public void run() {
        			  Connection conn = null;
					  Statement stm = null;
					  try {
						  conn = OracleDBConnection.getConnection();
					  } catch (Exception e) {
						// TODO: handle exception
						  if(sqlArray!=null && sqlArray.length>0){
					        	 int len=sqlArray.length;
					        	 for(int i=0;i<len;i++){
					        		 String sql = sqlArray[i];
					        		 LogUtil.saveSql(sql);
					        	 }
						  }
					  }
					  
					  
					  try {
				         stm = conn.createStatement(); 
				         if(sqlArray!=null && sqlArray.length>0){
				        	 int len=sqlArray.length;
				        	 for(int i=0;i<sqlArray.length;i++){
				        		 String sql = sqlArray[i];
				        		 if(null!=sql && !"".equals(sql)){
				                    stm.addBatch(sql);  
				                    if (i == len - 1) { 
				                        stm.executeBatch();  
				                        break;  
				                    }  
				                    if (i % 30 == 0 && i != 0) {
				                        stm.executeBatch();  
				                    }  
			                      }
				        	 }
				         }
					   }catch (Exception e) {
							e.printStackTrace();
//							LogUtil.saveQueLog("队列sql保存异常:"+e.getMessage());
					   }finally{
						   OracleDBConnection.closeStatement(stm);
						   OracleDBConnection.closeCon(conn);
					   }
        		}
			}).start();
        	
        } catch (Exception e1) {  
            e1.printStackTrace();  
//            LogUtil.saveQueLog("异常:"+e1.getMessage());
        }
            
	}
	
	
}
