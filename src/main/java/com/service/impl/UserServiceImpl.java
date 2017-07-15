/**
 * @author Administrator
 *
 */
package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entity.User;
import com.repository.UserRepository;
import com.service.UserService;

@Transactional
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	@Override
	public User findById(Long id) {
		return userRepository.findById(id);
	}
	@Override
	public void save(User test) {
		// TODO Auto-generated method stub
		
	}
	
	
}