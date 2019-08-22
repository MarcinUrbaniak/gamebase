package com.example.gamebase.type;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class GameReview {

    private long id;
    private String nick;
    private String rewiew;
}
