package com.example.service;

import com.example.model.Games;

import java.util.List;

public interface GameService extends PersistenceService {

    void create(Games games);
    List<String> gameUrlList();
}
