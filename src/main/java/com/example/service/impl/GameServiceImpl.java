package com.example.service.impl;

import com.example.model.Games;
import com.example.repository.GameRepository;
import com.example.service.GameService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GameServiceImpl implements GameService {

    private GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }

    @Override
    public void create(Games games) {
        gameRepository.save(games);
    }

    public List<String> gameUrlList(){

        return StreamSupport.stream(gameRepository.findAll().spliterator(), false)
                .distinct()
                .map(Games::getGameURL)
                .collect(Collectors.toList());
    }

}
