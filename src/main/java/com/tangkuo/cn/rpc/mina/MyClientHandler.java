package com.tangkuo.cn.rpc.mina;

import java.util.Date;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyClientHandler extends IoHandlerAdapter {

	private static Logger logger = LoggerFactory.getLogger(MyClientHandler.class);

	@Override
	public void exceptionCaught(IoSession iosession, Throwable throwable) throws Exception {
		logger.info("exceptionCaught");
	}

	@Override
	public void messageReceived(IoSession iosession, Object obj) throws Exception {
		logger.info("messageReceived");
		String msg = (String) obj;
		logger.info("客户端接收到数据" + msg);
	}

	@Override
	public void messageSent(IoSession iosession, Object obj) throws Exception {
		logger.info("messageSent");
		String msg = (String) obj;
		logger.info("客户端发送数据:" + msg);
	}

}
