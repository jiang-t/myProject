package com.common;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.service.UserService;

public class MyUserDetailService implements UserDetailsService{
	@Autowired
	private UserService userService;
	
	public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException, DataAccessException {
        Collection<GrantedAuthority> auths=new ArrayList<GrantedAuthority>();
        com.entity.User myUser = userService.findById(1l);
        if (myUser == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        
        
        SimpleGrantedAuthority auth2=new SimpleGrantedAuthority("ROLE_ADMIN");
//        auths.add(auth2);
        if(username.equals("cyu")){
            auths=new ArrayList<GrantedAuthority>();
            SimpleGrantedAuthority auth1=new SimpleGrantedAuthority("ROLE_USER");
            auths.add(auth1);
            auths.add(auth2);
        }
        UserDetails userDetail = new User(username,myUser.getValuess(), true, true, true, true, auths);//验证成功赋予权限
        return userDetail;
    }

}
