package com.example.gamebase;

import fi.iki.elonen.NanoHTTPD;

import java.io.IOException;

public class GamebaseApp extends NanoHTTPD {


    public GamebaseApp(int port) throws IOException {
        super(port);
        start(5000, false);
        System.out.println("Server has been started");
    }

    public static void main(String[] args) {
        try {
            new GamebaseApp(8080);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
