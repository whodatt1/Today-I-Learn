package com.example.fluxdemo.domain;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;

import com.example.fluxdemo.DBInit;

import reactor.test.StepVerifier;

// Repository 테스트
@DataR2dbcTest
@Import(DBInit.class)
public class CustomerRepositoryTest {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Test
	public void 한건찾기_테스트() {
		
		StepVerifier
			.create(customerRepository.findById(2L))
			.expectNextMatches((c) -> {
				// return c.getFirstName().equals("Chloe");
				return c.getFirstName().equals("Chloe2");
			})
			.expectComplete()
			.verify();
		
	}
}
