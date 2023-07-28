package com.kaarel.cardgame.service;

import com.kaarel.cardgame.model.Card;
import com.kaarel.cardgame.model.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CardServiceTest {

    @Autowired
    CardService cardService;
    @BeforeEach
    void setUp() {
    }
    @Test
    void Card_suit_is_one_of_the_suits() {
        Card card =cardService.getCard("name");
        List<Suit> suits = new ArrayList<>(Arrays.asList(Suit.values()));
//        Suit [] suits1 = Suit.values();
        boolean isSuit = suits.contains(card.getSuit());
//        boolean isSuit1 = Arrays.stream(suits1).anyMatch(e -> e.equals(card.getSuit()));
        assertTrue(isSuit);
    }

    @Test
    void Card_value_is_between_two_and_ten() {
        Card card =cardService.getCard("name");
        boolean isBetween = card.getValue() <= 2 && card.getValue() <= 10;
        assertTrue(isBetween);
    }
    @Test
    void Returns_Timeout_when_slower_than_10_seconds() throws InterruptedException {
        cardService.getCard("name");
        Thread.sleep(10001);
        String response = cardService.checkIfAnsweredCorrectTime();
        assertEquals("Too late!",response);
    }
    @Test
    void Returns_wrong_when_wrong_input()  {
        cardService.getCard("name");
        String response = cardService.checkIfAnsweredCorrectly("random_input");
        assertEquals("Wrong Answer!", response);
    }
    @Test
    void Returns_Timeout_when_slower_than_10_seconds_three_times() throws InterruptedException {
        cardService.getCard("name");
        Thread.sleep(10001);
        cardService.checkIfAnsweredCorrectTime();
        cardService.checkIfAnsweredCorrectTime();
        String response = cardService.checkIfAnsweredCorrectTime();

        assertEquals("Game Over!",response);
    }


    @Test
    void checkIfAnsweredCorrectTime() {
    }

    @Test
    void checkIfAnsweredCorrectly() {
    }
}