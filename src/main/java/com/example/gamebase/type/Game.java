package com.example.gamebase.type;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode

public class Game {
    private long id;
    private String title;
    private String producer;
    private String releaseDate;
    private List<PlatformType> platforms;
    private List<GameRating> ratings;
    private List<GameReview> reviews;

}
