package org.demo01.realms;

import java.security.Principal;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class MyRealm extends AuthorizingRealm {

	//授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	
	//认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//接受username  从数据库ByName查询用户
		
		String rincipal = (String) token.getPrincipal();
		//如果用户不存在    返回null
		if (!"zhangsan".equals(rincipal)) {
			return null;
		}
		//如果用户存在   取出实体类的密码：
		String password = "123";
		Md5Hash md5Hash = new Md5Hash(password);
		String pwd = md5Hash.toString();
		
		return new SimpleAuthenticationInfo(rincipal, pwd,ByteSource.Util.bytes("admin"), this.getName());
	}

}














