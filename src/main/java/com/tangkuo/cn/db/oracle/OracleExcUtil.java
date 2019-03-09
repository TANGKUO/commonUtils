package com.tangkuo.cn.db.oracle;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import com.tangkuo.cn.db.datasource.LogUtil;
import com.tangkuo.cn.db.mongodb.Configuration;
import com.tangkuo.cn.db.mysql.ServerData;

/**
 * 
 * @author 61650
 *
 */
public class OracleExcUtil {
	public static void exeSqlQueue(){
		int num = Integer.parseInt(Configuration.getValue("oracle.queue.count"));//批量sql语句
		LogUtil.saveQueLog("创建跑数据库队列...");
		while(true){
			Queue<String> queue = ServerData.sqlQueue;
			LogUtil.saveQueLog("是否放入队列:"+ServerData.isSetQueue.get("isSetQueue")+" -- "+queue.size());
			if(queue.size()>=num*10){
				ServerData.isSetQueue.put("isSetQueue",1l);//数据不放入队列中
			}
			
			if(queue.size()<num){
				ServerData.isSetQueue.put("isSetQueue",0l);//数据放入队列中
			}
			
			if(queue!=null && queue.size()>=num){//当队列中大于设置的sql数量则执行
				LogUtil.saveQueLog("sql总数0:"+queue.size());
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
        	}
        	
        	//异步保存
        	final String sqlArray[]=(String[])sqlList.toArray(new String[sqlList.size()]);
        	new Thread(new Runnable() {
        		public void run() {
        			  Connection conn = null;
					  Statement stm = null;
					  try {
						  conn = OracleConnection.getConnection();
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
				                    LogUtil.saveSqlLog("执行的sql:"+sql);
				                    if (i == len - 1) { 
				                        int[] result = stm.executeBatch();  
				                        LogUtil.saveSqlLog("批量执行sql返回结果"+IntegerToString(result));
				                        break;  
				                    }  
				                    if (i % 30 == 0 && i != 0) {
				                        int[] result = stm.executeBatch();  
				                        LogUtil.saveSqlLog("批量执行sql返回结果"+IntegerToString(result));
				                    }  
			                      }
				        	 }
				        	 
				        	 LogUtil.saveSqlLog("。。。。。。。。。循环执行sql完毕。。。。。。。。"+len);
				         }
					   }catch (Exception e) {
							e.printStackTrace();
							LogUtil.saveQueLog("队列sql保存异常:"+e.getMessage());
					   }finally{
					    	OracleConnection.closeStatement(stm);
							OracleConnection.closeCon(conn);
					   }
        		}
			}).start();
        	
        } catch (Exception e1) {  
            e1.printStackTrace();  
            LogUtil.saveQueLog("异常:"+e1.getMessage());
        }
            
	}
	
	private static String IntegerToString(int[] result){
		StringBuffer sb = new StringBuffer();
		for(int i : result){
			sb.append(i).append(",");
		}
		return sb.toString();
	}
}
