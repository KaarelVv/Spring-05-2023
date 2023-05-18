package com.kaarel.playtech;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor

public class Cards {
    private final List<String> cardNumber = Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10");
    private final List<String> cardCourt = Arrays.asList( "Ace", "Jack", "Queen", "King");
    private final List<Integer> courtValues = Collections.nCopies(cardCourt.size(), 10);

    public List<String> getCardNumber() {
        return cardNumber;
    }

    public List<Integer> getCourtValues() {
        return courtValues;
    }
}


