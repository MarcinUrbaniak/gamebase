package com.example.gamebase.type;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class GameRating {

    private long rating;

    public void setRating(long rating) {
        if(rating >= 1 && rating <=10){
            this.rating = rating;
        }
    }
}
