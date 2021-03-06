package com.example.gamebase.controller;

import com.example.gamebase.storage.GameStorage;
import com.example.gamebase.storage.impl.StaticListGameStorageImpl;
import com.example.gamebase.type.Game;
import com.example.gamebase.type.GameRating;
import com.example.gamebase.type.GameReview;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fi.iki.elonen.NanoHTTPD.IHTTPSession;
import fi.iki.elonen.NanoHTTPD.Response;

import java.util.List;
import java.util.Map;

import static fi.iki.elonen.NanoHTTPD.Response.Status.*;
import static fi.iki.elonen.NanoHTTPD.newFixedLengthResponse;


public class GameController {

    private final static String GAME_ID_PARAM_NAME = "gameId";
    private GameStorage gameStorage = new StaticListGameStorageImpl();

    public Response serveGetGameRequest(IHTTPSession session){
        Map<String, List<String>> requestParameters = session.getParameters();
        if(requestParameters.containsKey(GAME_ID_PARAM_NAME)){
            long gameId = getGameId(requestParameters);
            Game game = gameStorage.getGame(gameId);
            if(game != null){
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    String response = objectMapper.writeValueAsString(game);
                    return newFixedLengthResponse(OK, "application/json", response);
                }catch (JsonProcessingException e){
                    System.err.println("Error during process request: \n " + e);
                    return newFixedLengthResponse(INTERNAL_ERROR, "text/plain", "Internal error: can't read all games");
                }
            }
            return newFixedLengthResponse(NOT_FOUND, "text/plain", "");
        }

        return newFixedLengthResponse(BAD_REQUEST, "text/plain", "Uncorrect request params.");
    }

    public Response serveGetGamesRequest(IHTTPSession session){
        ObjectMapper objectMapper = new ObjectMapper();
        String response = "";

        try {
            response = objectMapper.writeValueAsString(gameStorage.getAllGames());
        } catch (JsonProcessingException e){
            System.err.println("Error during process request \n" + e );
            return newFixedLengthResponse(INTERNAL_ERROR, "text/plain","Internal server error, can't read all games");
        }

        return newFixedLengthResponse(OK, "application/json", response);
    }

    public Response serveAddGameRequest(IHTTPSession session){
        ObjectMapper objectMapper = new ObjectMapper();
        long randomGameId = System.currentTimeMillis();

        String lengthHeader = session.getHeaders().get("content-length");
        int contentLength = Integer.parseInt(lengthHeader);
        byte[] buffer = new byte[contentLength];

        try {
            session.getInputStream().read(buffer,0, contentLength);
            String requestBody = new String(buffer).trim();
            Game requestGame = objectMapper.readValue(requestBody, Game.class);
            requestGame.setId(randomGameId);
            gameStorage.addGame(requestGame);
        } catch (Exception e){
            System.err.println("Error during process request \n" + e);
            return newFixedLengthResponse(INTERNAL_ERROR, "text/plain", "Internal error, game hasn't been added");
        }
        return newFixedLengthResponse(OK, "text/plain", "Game has been successfully added, id: " + randomGameId );
    }

    public Response serveAddReviewRequest(IHTTPSession session){
        Map<String, List<String>> requestParameters = session.getParameters();
        if(requestParameters.containsKey(GAME_ID_PARAM_NAME)){
            long gameId = getGameId(requestParameters);
            Game game = gameStorage.getGame(gameId);
            if(game != null){
                ObjectMapper objectMapper = new ObjectMapper();
                String lengthHeader = session.getHeaders().get("content-length");
                int contentLength = Integer.parseInt(lengthHeader);
                byte[] buffer = new byte[contentLength];

                try {
                    session.getInputStream().read(buffer, 0, contentLength);
                    String requestBody = new String(buffer).trim();
                    GameReview requestGameReview = objectMapper.readValue(requestBody, GameReview.class);
                    requestGameReview.setId(System.currentTimeMillis());
                    gameStorage.addReview(requestGameReview, gameId);

                }catch (Exception e){
                    System.err.println("Error during process request \n" + e);
                    return newFixedLengthResponse(INTERNAL_ERROR, "text/plain", "Internal error, review hasn't been added");
                }
            }
            else return newFixedLengthResponse(NOT_FOUND, "application/json", "" );
        }
        return newFixedLengthResponse(OK, "text/plain", "Review has been successfully added ");
    }

    private long getGameId(Map<String, List<String>> requestParameters) {
        List<String> gameIdParams = requestParameters.get(GAME_ID_PARAM_NAME);
        String gameIdParam = gameIdParams.get(0);
        long gameId = 0;

        try {
            gameId = Long.parseLong(gameIdParam);
        } catch (NumberFormatException e) {
            System.err.println("Error during convert request param: \n" + e);
        }
        return gameId;
    }

    public Response serveAddRatingRequest(IHTTPSession session){

        Map<String, List<String>> requestParameters = session.getParameters();
        if(requestParameters.containsKey(GAME_ID_PARAM_NAME)){
            long gameId = getGameId(requestParameters);
            Game game = gameStorage.getGame(gameId);
            if(game != null){
                ObjectMapper objectMapper = new ObjectMapper();
                String lengthHeader = session.getHeaders().get("content-length");
                int contentLength = Integer.parseInt(lengthHeader);
                byte[] buffer = new byte[contentLength];

                try {
                    session.getInputStream().read(buffer, 0, contentLength);
                    String requestBody = new String(buffer).trim();
                    GameRating requestGameRating = objectMapper.readValue(requestBody, GameRating.class);
                    gameStorage.addRating(requestGameRating, gameId);

                }catch (Exception e){
                    System.err.println("Error during process request \n" + e);
                    return newFixedLengthResponse(INTERNAL_ERROR, "text/plain", "Internal error, review hasn't been added");
                }
            }
             else return newFixedLengthResponse(NOT_FOUND, "application/json", "" );
        }
        return newFixedLengthResponse(OK, "text/plain", "Rating has been successfully added ");
    }
}
