package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.User;

public interface UserRepository extends JpaRepository<User, String> {
	
	User findFirst1ByuserNameContaining(String userName);
	User findFirst1ByuserEmail(String userEmail);
	List<User> findAllByOrderByRegDtDesc();
}
