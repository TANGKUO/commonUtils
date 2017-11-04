package com.tangkuo.cn.rpc.mina;

import java.util.Date;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @ClassName: MyHandler
 * @Description: ()
 * @author tangkuo
 * @date 2017年11月4日 下午5:49:27
 *
 */
public class MyServerHandler extends IoHandlerAdapter {
	private static Logger logger = LoggerFactory.getLogger(MyServerHandler.class);

	@Override
	public void sessionCreated(IoSession iosession) throws Exception {
		logger.info("sessionCreated");
	}

	@Override
	public void sessionOpened(IoSession iosession) throws Exception {
		logger.info("sessionOpened");
	}

	@Override
	public void sessionClosed(IoSession iosession) throws Exception {
		logger.info("sessionClosed");
	}

	@Override
	public void sessionIdle(IoSession iosession, IdleStatus idlestatus) throws Exception {
		logger.info("sessionIdle");
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		logger.info("exceptionCaught");
	}

	@Override
	public void messageReceived(IoSession iosession, Object obj) throws Exception {
		logger.info("messageReceived");
		String msg = (String) obj;
		logger.info("服务器端接收到数据" + msg);
		if (msg.equals("exit")) {
			iosession.close();
		}
		Date date = new Date();
		iosession.write(date);
	}

	@Override
	public void messageSent(IoSession iosession, Object obj) throws Exception {
		logger.info("messageSent");
	}

	@Override
	public void inputClosed(IoSession session) throws Exception {
		logger.info("inputClosed");
	}

}
