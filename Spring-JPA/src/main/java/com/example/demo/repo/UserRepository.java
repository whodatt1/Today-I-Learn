package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.User;

// 실행 코드는 SpringJpaApplicationTests 에서..
public interface UserRepository extends JpaRepository<User, String> {
	
	User findFirst1ByuserNameLike(String name);
}
