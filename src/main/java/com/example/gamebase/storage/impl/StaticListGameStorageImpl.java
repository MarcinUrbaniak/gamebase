package com.example.gamebase.storage.impl;

import com.example.gamebase.storage.GameStorage;
import com.example.gamebase.type.Game;
import com.example.gamebase.type.GameRating;
import com.example.gamebase.type.GameReview;
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
        return gameStorage;
    }

    @Override
    public void addGame(Game game) {
        gameStorage.add(game);

    }

    public void addReview(GameReview gameReview, long gameId){
        for(Game game:gameStorage){
            if(game.getId() == gameId){
                game.getReviews().add(gameReview);
            }
        }
    }

    public void addRating(GameRating gameRating, long gameId){
        for(Game game: gameStorage){
            if(game.getId() == gameId){
                game.getRatings().add(gameRating);
            }
        }
    }
}
