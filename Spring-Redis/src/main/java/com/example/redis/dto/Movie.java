package com.example.redis.dto;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Movie extends BaseEntity {
	
	@Id
	private String movieCd;
	
	private String movieName;
	
	private String delYn;
	
}
