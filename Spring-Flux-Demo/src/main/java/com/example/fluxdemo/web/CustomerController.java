package com.example.fluxdemo.web;

import java.time.Duration;

import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fluxdemo.domain.Customer;
import com.example.fluxdemo.domain.CustomerRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@RestController
public class CustomerController {
	
	private final CustomerRepository customerRepository;
	// A 요청 -> Flux -> Stream
	// B 요청 -> Flux -> Stream
	// -> 두개의 스트림을 Flux.merge -> 두개의 sink가 맞춰진다.
	// 모든 클라이언트의 Flux 요청의 sink를 맞춰준다.
	private final Sinks.Many<Customer> sink;
	
	public CustomerController(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
		// multicast() => 새로 push된 데이터만 구독자에게 전해주는 방식
		// replay() => 어떤 지정된 크기를 계속 돌려주는 방식
		// unicate() => 단일 구독자만 허용하는 싱크 방식
		sink = Sinks.many().multicast().onBackpressureBuffer();
	}
	
	// onNext 할 떄마다 buffer를 flush 한 건씩 응답 가능 데이터 전부 소진되면 종료
	// MediaType.APPLICATION_STREAM_JSON_VALUE은 현재 deprecated.. application/x-ndjson이 공식 타입이 됐다.
	// application/stream+json은 스트리밍 방식으로 JSON 데이터를 전송할 때 사용하는 MIME 유형이다. 이는 JSON 객체를 지속적으로 스트리밍하여 클라이언트에 전달할 때 유용
	@GetMapping(value = "/fluxstream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<Integer> fluxStream() {
		return Flux.just(1,2,3,4,5).delayElements(Duration.ofSeconds(1)).log();
	}
	
	// 아래 코드 수행 시 브라우저는 application/ndjson 형식을 일반적으로 다운로드 가능한 파일로 인식하기 때문에, 결과가 웹 페이지에 표시되지 않고 파일로 다운로드
	// ndjson 포맷은 Newline Delimited JSON 포맷의 줄임말이며 한번의 하나의 데이터를 저장하거나 스트리밍 할 때 편리한 포맷.
	@GetMapping(value = "/customer", produces = MediaType.APPLICATION_NDJSON_VALUE)
	public Flux<Customer> findAll() {
		return customerRepository.findAll().delayElements(Duration.ofSeconds(1)).log();
	}
	
	// 모아놓은 것을 한방에 전송
	@GetMapping("/flux")
	public Flux<Integer> flux() {
		// 1,2,3,4,5 데이터를 구독후 onNext로 전송
		return Flux.just(1,2,3,4,5).delayElements(Duration.ofSeconds(1)).log();
	}
	
	// 여러개일 경우 Flux 0 ~ 1 일 경우 Mono onNext 한번으로 끝냄
	@GetMapping("/customer/{id}")
	public Mono<Customer> findById(@PathVariable(name = "id") Long id) { 
		return customerRepository.findById(id).log();
	}
	
	// MediaType.TEXT_EVENT_STREAM_VALUE => SSE Protocol이 적용되서 응답된다.
	// 앞에 prefix로 data : 가 붙는다.
	// ServerSentEvent로 리턴이 된다면 자동으로 produces가 MediaType.TEXT_EVENT_STREAM_VALUE으로 걸리게 된다. 즉 생략 가능하다.
	@GetMapping(value = "/customer/sse")
			//, produces = MediaType.TEXT_EVENT_STREAM_VALUE) 생략 가능
	public Flux<ServerSentEvent<Customer>> findAllSSE() {
		return sink.asFlux().map(customer -> ServerSentEvent.builder(customer).build())
				   .doOnCancel(() -> { // 강제로 연결이 끊길경우에 sink에서 여부를 확인할 수 없으므로
					   sink.asFlux().blockLast(); // 마지막 데이터가 들어왔으니 onComplete 자동 호출하여 재연결했을때 다시 연결시 전송받을 수 있게끔
				   });
	}
	
	@PostMapping("/customer")
	public Mono<Customer> save() {
		// 생성한것을 sink에다 doOnNext로 push한다.
		return customerRepository.save(new Customer("gildong", "Hong")).doOnNext(customer -> {
			sink.tryEmitNext(customer); // publisher 데이터 추가
		});
	}
	
}
