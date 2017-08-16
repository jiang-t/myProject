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
		//��ȡ����������Ҫini�����ļ�
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(filePath);
		//����securityManager
		SecurityManager securityManager = factory.getInstance();
		//securityManager �󶨵�utils��
		SecurityUtils.setSecurityManager(securityManager);
		//��ȡ����subject
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("admin","123");
		try {
			//4����¼���������֤
			subject.login(token);
			} catch (AuthenticationException e) {
				System.out.println(e.getMessage());
			//5�������֤ʧ��
				System.out.println("��֤ʧ��");
			}
			//Assert.assertEquals(true, subject.isAuthenticated()); //�����û��Ѿ���¼
			//6���˳�
			//subject.logout();
		
	}
	@Test
	public void test(){
		login("classpath:shiro.ini");
	}
}
