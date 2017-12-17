package com.tangkuo.cn.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author 61650
 * 
 *         自定義綫程池： 1:有界對列 2：無界隊列
 * 
 *
 */
public class MyThreadPoolExecutor2 implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(MyThreadPoolExecutor2.class);

	private static AtomicInteger count = new AtomicInteger(0);

	@Override
	public void run() {
		try {
			int temp = count.incrementAndGet();
			logger.info("任務" + temp);
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		int corePoolSize = 5;
		int maximumPoolSize = 10;
		long keepAliveTime = 120L;
		TimeUnit unit = TimeUnit.SECONDS;
		// LinkedBlockingQueue<Runnable>:無界隊列
		BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();

		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
				workQueue);

		// 执行任务
		for (int i = 1; i <= 20; i++) {
			threadPool.execute(new MyThreadPoolExecutor2());
		}

		Thread.sleep(1000);
		logger.info("queue size:" + workQueue.size());//
		Thread.sleep(2000);

		// 優雅關機
		threadPool.shutdown();

	}

}
