package com.tangkuo.cn.threadpool;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Executors 创建线程池的方式
 * 
 * @author 61650
 *
 */
public class UseExecutors {
	private static final int nThreads = 1;

	public static void main(String[] args) {
		Executor ex1 = Executors.newFixedThreadPool(nThreads);
		Executor ex2 = Executors.newCachedThreadPool();
		Executor ex3 = Executors.newScheduledThreadPool(0);
		Executor ex4 = Executors.newSingleThreadExecutor();
	}

}
