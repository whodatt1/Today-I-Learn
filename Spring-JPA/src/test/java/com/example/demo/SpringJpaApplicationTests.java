package com.example.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.List;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.domain.User;
import com.example.demo.repo.UserRepository;

@SpringBootTest
class SpringJpaApplicationTests {
	
	@Autowired
	private UserRepository userRepository;

	@Test
	// 로우에 INSERT
	void saveUser() throws IOException { // 테이블에 새로운 데이터 로우를 저장한다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("이메일 : ");
		
		String userEmail = br.readLine();
		
		User existsUser = userRepository.findFirst1ByuserEmail(userEmail);
		
		if (existsUser != null && existsUser.getUserEmail().equals(userEmail)) {
			System.out.println("이메일이 중복됩니다. 다른 이메일을 입력해주세요.");
			return;
		}
		
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
	
	@Test
	// 모든 로우 생성일 내림차순으로 데이터 받기
	void findAllOrderByRegDtDesc() {
		List<User> userList = userRepository.findAllByOrderByRegDtDesc();
		userList.forEach(user -> System.out.println("[OrderByRegDtAllUser] : " + user.getUserEmail() + " | " + user.getUserName()));
	}
	
	@Test
	void findAllUser() { // 저장된 모든 데이터를 가져온다.
		List<User> userList = userRepository.findAll();
		userList.forEach(user -> System.out.println("[AllUser] : " + user.getUserEmail() + " | " + user.getUserName()));
	}
	
	@Test
	void find1ByName() throws IOException { // Like 검색으로 1개의 값만 가져온다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("이름을 입력하세요. : ");
		
		String userName = br.readLine();
		
		User user = userRepository.findFirst1ByuserNameContaining(userName);
		
		System.out.println("[FindOneByUserName] : " + user.getUserEmail() + " | " + user.getUserName());
	}
	
	@Test
	// EMAIL 키값으로 해당 로우 수정
	void updateFind1ByEmail() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("수정할 이메일을 입력하세요. : ");
		
		String userEmail = br.readLine();
		
		User existsUser = userRepository.findFirst1ByuserEmail(userEmail);
		
		if (existsUser == null) {
			System.out.println("계정이 존재하지 않습니다. 다른 이메일을 입력해주세요.");
			return;
		}
		
		System.out.println("수정할 비밀번호를 입력하세요. : ");
		String newPassword = br.readLine();
		
		System.out.println("수정할 이름을 입력하세요. : ");
		String newUserName = br.readLine();
		
		System.out.println("수정할 성별을 입력하세요. : ");
		String newGender = br.readLine();
		
		if (!newGender.equals("M") && !newGender.equals("W")) {
			System.out.println("W , M 둘 중 하나로 입력해주세요.");
			return;
		}
		
		System.out.println("삭제 여부를 선택 하세요. ( Y or N 으로 입력 ) : ");
		String newDelYn = br.readLine();
		
		if (!newDelYn.equals("Y") && !newDelYn.equals("N")) {
			System.out.println("Y , N 둘 중 하나로 입력해주세요.");
			return;
		}
		
		existsUser.setPassword(newPassword);
		existsUser.setUserName(newUserName);
		existsUser.setGender(newGender);
		existsUser.setDelYn(newDelYn);
		existsUser.setModDt(LocalDateTime.now());
		
		userRepository.save(existsUser);
	}
}