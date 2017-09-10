package com.tangkuo.cn.task;

import java.util.TimerTask;//定时任务
import java.util.Timer;//定时器

/**
 * 
 * @ClassName: TimerTaskDemo1
 * @Description: (定时器演示)
 * @author tangkuo
 * @date 2017年9月10日 下午5:25:46
 *
 */
public class TimerTaskDemo1 {
	public static void main(String[] args) {
		Timer timer = new Timer();// 定时器
		timer.schedule(new MyTask(), 1000, 500);
	}
}

class MyTask extends TimerTask {
	public void run() {
		System.out.println("执行一次");
	}
}
