package com.tangkuo.cn.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 定时执行调度任务
 * 
 * @author 61650
 *
 */
public class ScheduledJob {
	private static final Logger logger = LoggerFactory.getLogger(ScheduledJob.class);

	public static void main(String[] args) {
		Temp command = new Temp();
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		int initialDelay = 1;
		int delay = 3;
		TimeUnit unit = TimeUnit.SECONDS;
		java.util.concurrent.ScheduledFuture<?> scheduledFuture = scheduler.scheduleWithFixedDelay(command,
				initialDelay, delay, unit);
	}

}

class Temp implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(Temp.class);

	@Override
	public void run() {
		logger.info("run");
	}

}
