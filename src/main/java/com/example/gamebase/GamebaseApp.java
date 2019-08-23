package com.example.gamebase;

import fi.iki.elonen.NanoHTTPD;

import java.io.IOException;

public class GamebaseApp extends NanoHTTPD {

    RequestUrlMapper requestUrlMapper = new RequestUrlMapper();

    public GamebaseApp(int port) throws IOException {
        super(port);
        start(5000, false);
        System.out.println("Server has been started");
    }

    @Override
    public Response serve(IHTTPSession session){
        return requestUrlMapper.delegateRequest(session);
    }

    public static void main(String[] args) {
        try {
            new GamebaseApp(8080);





        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
