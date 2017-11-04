package com.tangkuo.cn.rpc.mina;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @ClassName: MiNaServerHelloWorld
 * @Description: (服务器端编程MiNaServer)
 * @author tangkuo
 * @date 2017年11月4日 下午5:33:59
 *
 */
public class MiNaServer {

	private static Logger logger = LoggerFactory.getLogger(MiNaServer.class);
	static int PORT = 7080;
	static IoAcceptor accept = null;

	public static void main(String[] args) {
		accept = new NioSocketAcceptor();
		// 设置编码过滤器
		accept.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"),
						LineDelimiter.WINDOWS.getValue(), LineDelimiter.WINDOWS.getValue())));
		accept.getSessionConfig().setReadBufferSize(1024);
		accept.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
		accept.setHandler(new MyServerHandler());
		try {
			accept.bind(new InetSocketAddress(PORT));
		} catch (IOException e) {
			logger.error("server is IOException:" + e);
		}
		logger.info("server ->>>>" + PORT);
	}

}
