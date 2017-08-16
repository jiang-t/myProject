package shiro;

import junit.framework.Assert;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;


public class ShiroTest {
	
	public void login(String filePath){
		//获取工厂对象，需要ini配置文件
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(filePath);
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
	@Test
	public void test(){
		login("classpath:shiro.ini");
	}
}
