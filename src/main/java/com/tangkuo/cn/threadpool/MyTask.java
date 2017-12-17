package com.tangkuo.cn.threadpool;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * 
 * @author 61650 
 *
 */
public class MyTask implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(MyTask.class);

	private String taskId;
	private String taskName;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public MyTask(int i, String str) {
		// logger.info("第" + i + "========" + str);
		this.taskId = String.valueOf(i);
		this.taskName = str;
	}

	@Override
	public void run() {
		logger.info("run taskId ========" + this.taskId);
	}

	@Override
	public String toString() {
		return "MyTask [taskId=" + taskId + ", taskName=" + taskName + "]";
	}

}
