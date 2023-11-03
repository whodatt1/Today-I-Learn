package com.example.demo;

import java.io.BufferedReader;
import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.domain.User;
import com.example.demo.repo.UserRepository;

import java.io.InputStreamReader;
import java.time.LocalDateTime;

@SpringBootTest
class SpringJpaApplicationTests {
	
	@Autowired
	private UserRepository userRepository;

	@Test
	void saveUser() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("이메일 : ");
		
		String userEmail = br.readLine();
		
		System.out.println("비밀번호 : ");
		
		String password = br.readLine();
		
		System.out.println("이름 : ");
		
		String userName = br.readLine();
		
		System.out.println("성별 ( M or W 으로 입력 ) : ");
		
		String gender = br.readLine();
		
		if (gender.equals("M") || gender.equals("W")) {
			
			User userParams = User.builder()
					              .password(password)
					              .userEmail(userEmail)
					              .userName(userName)
					              .gender(gender)
					              .delYn("N")
					              .regDt(LocalDateTime.now())
					              .modDt(LocalDateTime.now())
					              .build();
			
			User user = userRepository.save(userParams);
			Assertions.assertEquals(user.getUserEmail(), userEmail);
		} else {
			System.out.println("W , M 둘 중 하나로 입력해주세요.");
			return;
		}
	}

}
