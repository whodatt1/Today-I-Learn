package com.example.fluxdemo.domain;

import org.springframework.data.annotation.Id;

public class Customer {

    @Id
    private Long id;

    private String firstName;

    private String lastName;
    
    // 기본 생성자를 만들지 않으니 매핑 오류가 발생했다.. 이유 확인해보기
    public Customer() {
	}

	public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    @Override
    public String toString() {
        return String.format(
            "Customer[id=%d, firstName='%s', lastName='%s']",
            id, firstName, lastName);
    }
}