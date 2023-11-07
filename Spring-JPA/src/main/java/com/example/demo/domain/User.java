package com.example.demo.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbuser")
public class User {
	
	@Id
	@Column(name = "Useremail")
	private String userEmail;
	
	@Column(name = "Password")
	private String password;
	
	@Column(name = "Username")
	private String userName;
	
	@Column(name = "Gender")
	private String gender;
	
	@Column(name = "Delyn")
	private String delYn;
	
	@Column(name = "Regdt")
	private LocalDateTime regDt;
	
	@Column(name = "Moddt")
	private LocalDateTime modDt;
	
	@Builder
	public User(String userEmail, String password, String userName, String gender, String delYn,
			LocalDateTime regDt, LocalDateTime modDt) {
		this.userEmail = userEmail;
		this.password = password;
		this.userName = userName;
		this.gender = gender;
		this.delYn = delYn;
		this.regDt = regDt;
		this.modDt = modDt;
	}
	
	
}
