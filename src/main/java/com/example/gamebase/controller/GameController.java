package com.example.gamebase.controller;

import com.example.gamebase.storage.GameStorage;
import com.example.gamebase.storage.impl.StaticListGameStorageImpl;
import fi.iki.elonen.NanoHTTPD.IHTTPSession;
import fi.iki.elonen.NanoHTTPD.Response;

public class GameController {

    private GameStorage gameStorage = new StaticListGameStorageImpl();

    public Response serveGetGameRequest(IHTTPSession session){
        return null;
    }

    public Response serveGetGamesRequest(IHTTPSession session){
        return null;
    }

    public Response serveAddGameRequest(IHTTPSession session){
        return null;
    }

    public Response serveAddReviewRequest(IHTTPSession session){
        return null;
    }
    public Response serveAddRatingRequest(IHTTPSession session){
        return null;
    }

}
