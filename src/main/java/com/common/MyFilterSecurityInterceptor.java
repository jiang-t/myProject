package com.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
/**
 * 首先，登陆后，每次访问资源都会被这个拦截器拦截，会执行doFilter这个方法，这个方法调用了invoke方法，其中fi断点显示是一个url（
 * 可能重写了toString方法吧，但是里面还有一些方法的），最重要的是beforeInvocation这个方法，
 * 它首先会调用MyInvocationSecurityMetadataSource类的getAttributes方法获取被拦截url所需的权限
 * ，在调用MyAccessDecisionManager类decide方法判断用户是否够权限。弄完这一切就会执行下一个拦截器。
 * 核心的InterceptorStatusToken token =
 * super.beforeInvocation(fi);会调用我们定义的accessDecisionManager:decide(Object
 * object)和securityMetadataSource:getAttributes(Object object)方法。
 *
 * 自己实现的过滤用户请求类，也可以直接使用FilterSecurityInterceptor
 * AbstractSecurityInterceptor有三个派生类：
 * FilterSecurityInterceptor，负责处理FilterInvocation，实现对URL资源的拦截。
 * MethodSecurityInterceptor，负责处理MethodInvocation，实现对方法调用的拦截。
 * AspectJSecurityInterceptor，负责处理JoinPoint，主要是用于对切面方法(AOP)调用的拦截。
 * 还可以直接使用注解对Action方法进行拦截，例如在方法上加：
 *@PreAuthorize("hasRole('ROLE_SUPER')")
 *
 * @author limingxing
 * @2015-12-3
 * version 1.0v
 */
public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter
{
	private FilterInvocationSecurityMetadataSource securityMetadataSource;

    /**
     * Method that is actually called by the filter chain. Simply delegates to
     * the {@link #invoke(FilterInvocation)} method.
     * 
     * @param request
     *            the servlet request
     * @param response
     *            the servlet response
     * @param chain
     *            the filter chain
     * 
     * @throws IOException
     *             if the filter chain fails
     * @throws ServletException
     *             if the filter chain fails
     */
    public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) 
    		throws IOException, ServletException {
        FilterInvocation fi = new FilterInvocation(request, response, chain);
        invoke(fi);
    }

    public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
        return this.securityMetadataSource;
    }

    public Class<? extends Object> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    public void invoke(FilterInvocation fi) throws IOException,
            ServletException {
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }

    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;
    }

    public void setSecurityMetadataSource(
            FilterInvocationSecurityMetadataSource newSource) {
        this.securityMetadataSource = newSource;
    }

    public void destroy() {
    }

    public void init(FilterConfig arg0) throws ServletException {
    }

}
