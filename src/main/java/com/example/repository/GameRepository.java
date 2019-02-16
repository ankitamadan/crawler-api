package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.model.Games;

public interface GameRepository extends CrudRepository<Games, Long>{
}
