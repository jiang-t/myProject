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
 *这个用来加载资源与权限的全部对应关系的，并提供一个通过资源获取所有权限的方法。
 *首先，这里也是模拟了从数据库中获取信息。
 *其中loadResourceDefine方法不是必须的
 * ，这个只是加载所有的资源与权限的对应关系并缓存起来，避免每次获取权限都访问数据库（提高性能），然后getAttributes根据参数
 * （被拦截url）返回权限集合。
 * 这种缓存的实现其实有一个缺点，因为loadResourceDefine方法是放在构造器上调用的，而这个类的实例化只在web服务器启动时调用一次
 * ，那就是说loadResourceDefine方法只会调用一次
 * ，如果资源和权限的对应关系在启动后发生了改变，那么缓存起来的就是脏数据，现在这里使用的就是缓存数据
 * ，那就会授权错误了。但如果资源和权限对应关系是不会改变的，这种方法性能会好很多。
 * 在getAttributes方法里面调用dao（这个是加载完，后来才会调用的，所以可以使用dao）
 *  ，通过被拦截url获取数据库中的所有权限，封装成Collection<ConfigAttribute>返回就行了。（灵活、简单）
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
