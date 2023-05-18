package com.kaarel.playtech;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController

public class Controller {
    private final Cards cards = new Cards();
    private final Random random = new Random();
    private int remainingGuesses = 10;
    private String baseCard;
    private String guessCard;
    private List<String> allGuesses = new ArrayList<>();
    @GetMapping("/start")
    public String startGame() {
        int randomIndex = random.nextInt(cards.getCardNumber().size());
        baseCard = cards.getCardNumber().get(randomIndex);
        remainingGuesses = 10;
        allGuesses.clear();
        return "Game started! Your base card is: " + baseCard;
    }
    @GetMapping("/guess")
    public String makeGuess(@RequestParam String card) {
        if (remainingGuesses <= 0) {
            return "Game over! The base card was: " + baseCard + ". Your guesses were: " + allGuesses.toString();
        }
        if (!cards.getCardNumber().contains(card)) {
            return "Invalid card entered. Please enter a valid card.";
        }
        guessCard = card;
        if (baseCard.equals(guessCard)) {
            remainingGuesses--;
            allGuesses.add(guessCard);
            return "Same card! You have " + remainingGuesses + " guess(es) remaining.";
        } else {
            int baseCardValue = getCardValue(baseCard);
            int guessCardValue = getCardValue(guessCard);
            String hint;

            if (guessCardValue > baseCardValue) {
                hint = "Bigger card!";
            } else if (guessCardValue == baseCardValue) {
                hint = "Same card value! Try a card with a higher suit value.";
            } else {
                hint = "Smaller card!";
            }

            remainingGuesses--;
            allGuesses.add(guessCard);

            if (remainingGuesses <= 0) {
                return "Game over! The base card was: " + baseCard + ". Your guesses were: " + allGuesses.toString();
            } else {
                return hint + " You have " + remainingGuesses + " guess remaining.";
            }
        }
    }
    private int getCardValue(String card) {
        if (card.endsWith("10") || cards.getCardCourt().contains(card)) {
            return 10;
        }
        String cardNumber = card.replaceAll("[^\\d]", "");
        try {
            return Integer.parseInt(cardNumber);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}

