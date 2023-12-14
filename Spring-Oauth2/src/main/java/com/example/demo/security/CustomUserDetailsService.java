package com.example.demo.security;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.example.demo.entity.User;
import com.example.demo.repo.UserRepository;

import lombok.RequiredArgsConstructor;

// Token을검증하는 Filter에서 회원 데이터를 조회해 인증객체로 반환
@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	
	private final UserRepository userRepository;
	
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findFirstByEmailOrderByIdAsc(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with email : " + username));
		return UserPrincipal.create(user);
	}
	
	 @Transactional
	 public UserDetails loadUserById(Long id) {
		 User user = userRepository.findById(id)
				 .orElseThrow(() -> new UsernameNotFoundException("User not found with id : " + id));
		 return UserPrincipal.create(user);
	 }
}
