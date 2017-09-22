package com.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import antlr.StringUtils;
/**
 *�������������Դ��Ȩ�޵�ȫ����Ӧ��ϵ�ģ����ṩһ��ͨ����Դ��ȡ����Ȩ�޵ķ�����
 *���ȣ�����Ҳ��ģ���˴����ݿ��л�ȡ��Ϣ��
 *����loadResourceDefine�������Ǳ����
 * �����ֻ�Ǽ������е���Դ��Ȩ�޵Ķ�Ӧ��ϵ����������������ÿ�λ�ȡȨ�޶��������ݿ⣨������ܣ���Ȼ��getAttributes���ݲ���
 * ��������url������Ȩ�޼��ϡ�
 * ���ֻ����ʵ����ʵ��һ��ȱ�㣬��ΪloadResourceDefine�����Ƿ��ڹ������ϵ��õģ���������ʵ����ֻ��web����������ʱ����һ��
 * ���Ǿ���˵loadResourceDefine����ֻ�����һ��
 * �������Դ��Ȩ�޵Ķ�Ӧ��ϵ�����������˸ı䣬��ô���������ľ��������ݣ���������ʹ�õľ��ǻ�������
 * ���Ǿͻ���Ȩ�����ˡ��������Դ��Ȩ�޶�Ӧ��ϵ�ǲ���ı�ģ����ַ������ܻ�úܶࡣ
 * ��getAttributes�����������dao������Ǽ����꣬�����Ż���õģ����Կ���ʹ��dao��
 *  ��ͨ��������url��ȡ���ݿ��е�����Ȩ�ޣ���װ��Collection<ConfigAttribute>���ؾ����ˡ������򵥣�
 * @author limingxing
 * @2015-12-3
 * version 1.0v
 */
public class MyInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource{
    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    public MyInvocationSecurityMetadataSource() {
        loadResourceDefine();
    }

    private void loadResourceDefine() {
        resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
        Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
        ConfigAttribute ca = new SecurityConfig("ROLE_USER");
        atts.add(ca);
        resourceMap.put("/index.jsp", atts);
        
        Collection<ConfigAttribute> attsno = new ArrayList<ConfigAttribute>();
        ConfigAttribute cano = new SecurityConfig("ROLE_NO");
        attsno.add(cano);
        resourceMap.put("/other.jsp", attsno);
    }

    // According to a URL, Find out permission configuration of this URL.
    public Collection<ConfigAttribute> getAttributes(Object object)
            throws IllegalArgumentException {
    	RequestMatcher  urlMatcher =null;
        // guess object is a URL.
        String url = ((FilterInvocation)object).getRequestUrl();
        Iterator<String> ite = resourceMap.keySet().iterator();
        while (ite.hasNext()) {
            String resURL = ite.next();
            urlMatcher = new AntPathRequestMatcher(resURL);  
            if (urlMatcher.matches(((FilterInvocation)object).getHttpRequest()) || url.equals(resURL)) {
                return resourceMap.get(resURL);
            }
        }
        return null;
    }

    public boolean supports(Class<?> clazz) {
        return true;
    }
    
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

}
