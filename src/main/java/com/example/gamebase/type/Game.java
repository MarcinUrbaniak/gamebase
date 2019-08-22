package com.example.gamebase.type;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString

public class Game {
    private long id;
    private String title;
    private String producer;
    private LocalDate releaseDate;
    private List<PlatformType> platforms;
    private List<GameRating> ratings;




}
