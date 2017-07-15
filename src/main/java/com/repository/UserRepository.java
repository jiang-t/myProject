package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.User;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findById(Long id);
	
}
