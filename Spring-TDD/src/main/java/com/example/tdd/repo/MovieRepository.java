package com.example.tdd.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tdd.dto.MovieDto;

public interface MovieRepository extends JpaRepository<MovieDto, String> {

}
