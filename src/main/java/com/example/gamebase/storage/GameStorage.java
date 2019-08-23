package com.example.gamebase.storage;

import com.example.gamebase.type.Game;
import com.example.gamebase.type.GameRating;
import com.example.gamebase.type.GameReview;

import java.util.List;

public interface GameStorage {

    Game getGame(long id);
    List<Game> getAllGames();
    void addGame(Game game);
    void addReview(GameReview gameReview, long gameId);
    void addRating(GameRating gameRating, long gameId);
}
