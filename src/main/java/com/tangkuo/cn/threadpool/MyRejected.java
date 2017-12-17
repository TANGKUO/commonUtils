package com.tangkuo.cn.threadpool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author 61650
 *    
 *         自定義拒絕策略 
 *
 */
public class MyRejected implements RejectedExecutionHandler {
	private static final Logger logger = LoggerFactory.getLogger(MyRejected.class);

	public MyRejected() {
	}

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		logger.info("===自定義處理===");
		logger.info("===当前被拒绝的任务为===" + r.toString());

		// 綫程池處理異常邏輯
	}

}
