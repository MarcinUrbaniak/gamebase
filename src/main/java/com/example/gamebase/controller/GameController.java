package com.example.gamebase.controller;

import com.example.gamebase.storage.GameStorage;
import com.example.gamebase.storage.impl.StaticListGameStorageImpl;
import com.example.gamebase.type.Game;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fi.iki.elonen.NanoHTTPD.IHTTPSession;
import fi.iki.elonen.NanoHTTPD.Response;

import static fi.iki.elonen.NanoHTTPD.Response.Status.OK;
import static fi.iki.elonen.NanoHTTPD.newFixedLengthResponse;
import static fi.iki.elonen.NanoHTTPD.Response.Status.INTERNAL_ERROR;


public class GameController {

    private GameStorage gameStorage = new StaticListGameStorageImpl();

    public Response serveGetGameRequest(IHTTPSession session){
        return null;
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

        String lengthHeader = session.getHeaders().get("content-lenght");
        int contentLenght = Integer.parseInt(lengthHeader);
        byte[] buffer = new byte[contentLenght];

        try {
            session.getInputStream().read(buffer,0, contentLenght);
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
        return null;
    }
    public Response serveAddRatingRequest(IHTTPSession session){
        return null;
    }

}
