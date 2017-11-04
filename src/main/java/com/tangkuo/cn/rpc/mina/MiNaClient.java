package com.tangkuo.cn.rpc.mina;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.ThreadUtils;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @ClassName: MiNaClient
 * @Description: (客户端程序)
 * @author tangkuo
 * @date 2017年11月4日 下午6:40:34
 *
 */
public class MiNaClient {
	private static Logger logger = LoggerFactory.getLogger(MiNaServer.class);
	static String IP = "127.0.0.1";
	static int PORT = 7080;

	public static void main(String[] args) {
		IoSession session = null;
		IoConnector connector = new NioSocketConnector();
		connector.setConnectTimeout(3000);
		// 设置过滤器
		connector.getFilterChain().addLast("coderc",
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"),
						LineDelimiter.WINDOWS.getValue(), LineDelimiter.WINDOWS.getValue())));

		connector.setHandler(new MyClientHandler());
		ConnectFuture future = connector.connect(new InetSocketAddress(IP, PORT));
		future.awaitUninterruptibly();// 等待我们的连接
		session = future.getSession();
		session.write("exit");
		session.getCloseFuture().awaitUninterruptibly();// 等待关闭连接

		/*try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/

		//
		connector.dispose();
	}

}
