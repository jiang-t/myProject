package shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class MyRealm1 implements Realm {
	@Override
	public String getName() {
		return "myrealm1";
	}
	@Override
	public boolean supports(AuthenticationToken token) {
		//仅支持 UsernamePasswordToken 类型的 Token
		return token instanceof UsernamePasswordToken;
	}
	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws
		AuthenticationException {
		String username = (String)token.getPrincipal(); //得到用户名
		String password = new String((char[])token.getCredentials()); //得到密码
		if(!"zhang".equals(username)) {
			throw new UnknownAccountException(); //如果用户名错误
		}
		if(!"123".equals(password)) {
			throw new IncorrectCredentialsException(); //如果密码错误
		}
		//如果身份认证验证成功，返回一个 AuthenticationInfo 实现；
		return new SimpleAuthenticationInfo(username, password, getName());
	}
	@Test
	public void testHelloWord(){
		//获取工厂对象，需要ini配置文件
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		//生成securityManager
		SecurityManager securityManager = factory.getInstance();
		//securityManager 绑定到utils上
		SecurityUtils.setSecurityManager(securityManager);
		//获取主体subject
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("admin","123");
		try {
			//4、登录，即身份验证
			subject.login(token);
			} catch (AuthenticationException e) {
				System.out.println(e.getMessage());
			//5、身份验证失败
				System.out.println("验证失败");
			}
			//Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录
			//6、退出
			//subject.logout();
		
	}
}