package com.tangkuo.cn.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author 61650 
 * 
 *         自定義綫程池： 1:有界對列 2：無界隊列
 * 
 *
 */
public class MyThreadPoolExecutor001 {

	public static void main(String[] args) {
		int corePoolSize = 1;
		int maximumPoolSize = 2;
		long keepAliveTime = 60;
		TimeUnit unit = TimeUnit.SECONDS;
		// ArrayBlockingQueue:有界隊列
		BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(3);

		// LinkedBlockingQueue<Runnable>:無界隊列
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
				workQueue, new MyRejected());

		MyTask myTask1 = new MyTask(1, "任务1");
		MyTask myTask2 = new MyTask(2, "任务2");
		MyTask myTask3 = new MyTask(3, "任务3");
		MyTask myTask4 = new MyTask(4, "任务4");
		MyTask myTask5 = new MyTask(5, "任务5");
		MyTask myTask6 = new MyTask(6, "任务6");

		// 执行任务
		threadPool.execute(myTask1);
		threadPool.execute(myTask2);
		threadPool.execute(myTask3);
		threadPool.execute(myTask4);
		threadPool.execute(myTask5);
		threadPool.execute(myTask6);

		// 優雅關機
		threadPool.shutdown();

	}

}
