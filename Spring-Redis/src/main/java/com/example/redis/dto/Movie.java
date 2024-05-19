package com.example.redis.dto;



import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Movie extends BaseEntity implements Serializable {
	
	@Id
	private String movieCd;
	
	private String movieName;
	
	private String delYn;
	
}
