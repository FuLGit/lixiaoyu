package org.demo01.text;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class TextAuthenticator {
	public static void main(String[] args) {
		
		//1.构建SecurityManager环境
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-cryptography.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		
		//创建一个Subject实例,该实例运行要使用上面创建的securityManager运行
		Subject subject = SecurityUtils.getSubject();
		//创建token令牌，记录用户认证的身份凭证及账号和密码
		UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123");
		
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			System.out.println("Login.........");
			e.printStackTrace();
		}
		
		System.out.println("用户状态："+subject.isAuthenticated());
		
		//注销
		subject.logout();
		
		System.err.println("状态："+subject.isAuthenticated());
		
	}
}









