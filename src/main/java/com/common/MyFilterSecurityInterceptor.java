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
 * ���ȣ���½��ÿ�η�����Դ���ᱻ������������أ���ִ��doFilter����������������������invoke����������fi�ϵ���ʾ��һ��url��
 * ������д��toString�����ɣ��������滹��һЩ�����ģ�������Ҫ����beforeInvocation���������
 * �����Ȼ����MyInvocationSecurityMetadataSource���getAttributes������ȡ������url�����Ȩ��
 * ���ڵ���MyAccessDecisionManager��decide�����ж��û��Ƿ�Ȩ�ޡ�Ū����һ�оͻ�ִ����һ����������
 * ���ĵ�InterceptorStatusToken token =
 * super.beforeInvocation(fi);��������Ƕ����accessDecisionManager:decide(Object
 * object)��securityMetadataSource:getAttributes(Object object)������
 *
 * �Լ�ʵ�ֵĹ����û������࣬Ҳ����ֱ��ʹ��FilterSecurityInterceptor
 * AbstractSecurityInterceptor�����������ࣺ
 * FilterSecurityInterceptor��������FilterInvocation��ʵ�ֶ�URL��Դ�����ء�
 * MethodSecurityInterceptor��������MethodInvocation��ʵ�ֶԷ������õ����ء�
 * AspectJSecurityInterceptor��������JoinPoint����Ҫ�����ڶ����淽��(AOP)���õ����ء�
 * ������ֱ��ʹ��ע���Action�����������أ������ڷ����ϼӣ�
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
