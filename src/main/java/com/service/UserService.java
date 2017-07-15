/**
 * @author Administrator
 *
 */
package com.service;

import com.entity.User;

public interface UserService{
	User findById(Long id);
	void save(User test);
}