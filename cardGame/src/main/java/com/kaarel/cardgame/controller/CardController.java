package com.kaarel.cardgame.controller;

import com.kaarel.cardgame.model.Card;
import com.kaarel.cardgame.entity.Game;
import com.kaarel.cardgame.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class CardController { //controller päring sisse võtta ja väljastada
   
    @Autowired
    CardService cardService;
    @GetMapping("start/{playerName}")
    public Card start(@PathVariable String playerName){
        return cardService.getCard(playerName);
    }

    @GetMapping("guess/{input}")
        public String guess(@PathVariable String input ){
        String errorMessage = cardService.checkIfCorrect(input);
        if (errorMessage != null) return errorMessage;

        errorMessage = cardService.checkIfAnsweredCorrectTime();
        if (errorMessage != null) return errorMessage;

        return cardService.checkIfAnsweredCorrectly(input);
    }





}

