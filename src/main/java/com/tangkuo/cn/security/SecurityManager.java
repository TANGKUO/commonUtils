package com.tangkuo.cn.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecurityManager {
	private static Logger log = LoggerFactory.getLogger(SecurityManager.class);

	/**
	 * 
	 */
	public void testShiro() { 
		// 1:获取安全管理器
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:");
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();

		// 2:设置安全管理器
		SecurityUtils.setSecurityManager(securityManager);

		// 3:获取Subject对象，登录用户.
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();

		session.setAttribute("name", "tangkuo");

		// 获取
		String value = (String) session.getAttribute("name");
		if (null != value) {
			log.info("shiro 已经获取到session会话对象.");
			//
			String username = "tangkuo";
			String password = "tangkuo";
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			token.setRememberMe(Boolean.TRUE);
			try {
				currentUser.login(token);
			} catch (UnknownAccountException uae) {
				log.info("用户名不正确.");
			} catch (IncorrectCredentialsException ice) {
				log.info("密码不正确");
			} catch (LockedAccountException lae) {
				log.info("账户被锁定");
			}

			log.info("账户登录成功");

		}
	}
}
