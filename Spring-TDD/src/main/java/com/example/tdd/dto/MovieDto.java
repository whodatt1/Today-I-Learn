package com.example.tdd.dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class MovieDto {
	
	@Id
	private String movieCd;
	
	private String movieNm;
	
	private int ticketPrice;
	
	private int seat;
}
