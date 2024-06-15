package com.example.fluxtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.Filter;

@Configuration
public class MyFilterConfig {
	
	@Autowired
	private EventNotify eventNotify;
	
	// 커스텀한 필터를 등록
	
	@Bean
	FilterRegistrationBean<Filter> addFilter() {
		System.out.println("필터 등록 완료.");
		FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>(new MyFilter(eventNotify));
		bean.addUrlPatterns("/sse");
		return bean;
	}
	
	@Bean
	FilterRegistrationBean<Filter> addFilter2() {
		System.out.println("필터 등록 완료.");
		FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>(new MyFilter2(eventNotify));
		bean.addUrlPatterns("/add");
		return bean;
	}
}
