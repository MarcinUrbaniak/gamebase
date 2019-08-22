package com.example.gamebase.storage.impl;

import com.example.gamebase.storage.GameStorage;
import com.example.gamebase.type.Game;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class StaticListGameStorageImpl implements GameStorage {

    private static List<Game> gameStorage = new ArrayList<Game>();

    @Override
    public Game getGame(long id) {
        for (Game game: gameStorage){
            if(game.getId() == id){
                return game;
            }
        }
        return null;
    }

    @Override
    public List<Game> getAllGames() {
        return null;
    }

    @Override
    public void addGame(Game game) {

    }
}
