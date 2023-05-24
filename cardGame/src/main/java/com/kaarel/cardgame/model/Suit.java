package com.kaarel.cardgame.model;

import java.util.Random;

public enum Suit {
    SPADE,CLUB,DIAMOND,HEART;

    public static Suit getRandomSuit(){
        Random random = new Random();
        int randomNumber = random.nextInt(values().length);
        return values()[randomNumber];
    }


}
