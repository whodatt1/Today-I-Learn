package com.example.tdd.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Movie {
	
	@Id
	private String movieCd;
	
	private String movieNm;
	
	private int ticketPrice;
	
	private int seat;
}
