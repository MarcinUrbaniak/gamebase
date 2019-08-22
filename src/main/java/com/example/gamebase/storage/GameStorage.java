package com.example.gamebase.storage;

import com.example.gamebase.type.Game;

import java.util.List;

public interface GameStorage {

    Game getGame(long id);
    List<Game> getAllGames();
    void addGame(Game game);
}
