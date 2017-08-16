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
		//��֧�� UsernamePasswordToken ���͵� Token
		return token instanceof UsernamePasswordToken;
	}
	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws
		AuthenticationException {
		String username = (String)token.getPrincipal(); //�õ��û���
		String password = new String((char[])token.getCredentials()); //�õ�����
		if(!"zhang".equals(username)) {
			throw new UnknownAccountException(); //����û�������
		}
		if(!"123".equals(password)) {
			throw new IncorrectCredentialsException(); //����������
		}
		//��������֤��֤�ɹ�������һ�� AuthenticationInfo ʵ�֣�
		return new SimpleAuthenticationInfo(username, password, getName());
	}
	@Test
	public void testHelloWord(){
		//��ȡ����������Ҫini�����ļ�
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
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
}