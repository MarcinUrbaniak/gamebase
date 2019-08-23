package com.example.gamebase;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.NanoHTTPD.IHTTPSession;
import fi.iki.elonen.NanoHTTPD.Response;

import static fi.iki.elonen.NanoHTTPD.Method.GET;
import static fi.iki.elonen.NanoHTTPD.Method.POST;
import static fi.iki.elonen.NanoHTTPD.Response.Status.NOT_FOUND;

public class RequestUrlMapper {

    private final static String ADD_GAME_URL = "/game/add";
    private final static String GET_ALL_GAME_URL = "/game/getAll";
    private final static String GET_GAME_URL = "/game/get";
    private final static String ADD_GAME_REVIEW_URL = "/game/addReview";
    private final static String ADD_GAME_RATING_URL = "/game/addRating";

    public Response delegateRequest(IHTTPSession session){

        if (POST.equals(session.getMethod()) && ADD_GAME_URL.equals(session.getUri())){
            return null;
        } else if (GET.equals(session.getMethod()) && GET_ALL_GAME_URL.equals(session.getUri())) {
            return null;
        } else if(GET.equals(session.getMethod()) && GET_GAME_URL.equals(session.getUri())){
            return null;
        } else if(POST.equals(session.getMethod()) && ADD_GAME_REVIEW_URL.equals(session.getUri())){
            return null;
        } else if (POST.equals(session.getMethod()) && ADD_GAME_RATING_URL.equals(session.getUri())){
            return null;
        }

        return NanoHTTPD.newFixedLengthResponse(NOT_FOUND,"text/plain" ,"Not found" );
    }
}
