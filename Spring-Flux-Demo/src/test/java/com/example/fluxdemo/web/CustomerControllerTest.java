package com.example.fluxdemo.web;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.fluxdemo.domain.Customer;
import com.example.fluxdemo.domain.CustomerRepository;

import reactor.core.publisher.Mono;

// 통합 테스트
//@SpringBootTest
//@AutoConfigureWebTestClient

// Controller를 테스트 하는 것
@WebFluxTest
public class CustomerControllerTest {
	
	@MockBean
	CustomerRepository customerRepository;
	
	@Autowired
	private WebTestClient webClient;
	
	@Test
	public void 한건찾기_테스트() {
		
		// given
		Mono<Customer> givenData = Mono.just(new Customer("Jack", "Bauer"));
		
		// stub -> 행동 지시 (가정) findById 1(파라미터값) 요청시 리턴
		when(customerRepository.findById(1L)).thenReturn(givenData); // Customer를 Mono 타입으로
		
		webClient.get().uri("/customer/{id}", 1L)
				 .exchange()
				 .expectBody()
				 .jsonPath("$.firstName").isEqualTo("Jack")
				 .jsonPath("$.firstName").isEqualTo("Bauer");
	}

}
