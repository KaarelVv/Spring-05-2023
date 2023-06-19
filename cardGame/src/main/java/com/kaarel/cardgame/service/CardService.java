package com.kaarel.cardgame.service;

import com.kaarel.cardgame.entity.Game;
import com.kaarel.cardgame.entity.Player;
import com.kaarel.cardgame.model.Card;
import com.kaarel.cardgame.repository.GameRepository;
import com.kaarel.cardgame.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
public class CardService { // Service musta töö tegija
    @Autowired
    GameRepository gameRepository;

    @Autowired
    PlayerRepository playerRepository;

    Card baseCard;
    LocalDateTime roundStartTime;
    Player player;
    int lives = 3;
    int correctAnswers = 0;
    private long gameStartTime;

    public Card getCard(String playerName) {
        if (baseCard == null) {
            gameStartTime = System.currentTimeMillis();
            baseCard = new Card();
            getPlayer(playerName);
            correctAnswers = 0;
            lives = 3;
        }
        roundStartTime = LocalDateTime.now();
        return baseCard;
    }

    private void getPlayer(String playerName) {
        Optional<Player> playerOptional = playerRepository.findById(playerName);
        if(playerOptional.isPresent()){
            player = playerOptional.get();
        }else {
            player = new Player();
            player.setName(playerName);
            player.setFirstCreated(new Date());
            playerRepository.save(player);
        }
    }





    public String checkIfCorrect(String input) {
        if (lives<= 0){
            return "Out of guesses!";
        }
        if(!input.equals("lower") && !input.equals("higher") && !input.equals("equal"))
            return "Wrong Input";
        return null;
    }
    public String checkIfAnsweredCorrectTime() {
        LocalDateTime limitTime = roundStartTime.plusSeconds(10);
        LocalDateTime answerTime = LocalDateTime.now();
        if (answerTime.isAfter(limitTime)) {
            lives--;   //võtab ühe elu ära
            if(lives == 0){
                sendGameToDatabase();
                return "Game Over!";
            }
            return "Too late!";
        }
        return null;
    }
    public String checkIfAnsweredCorrectly(String input) {
        Card newCard = new Card();
        if (baseCard.getValue() < newCard.getValue() && input.equals("higher") ||
                baseCard.getValue() > newCard.getValue() && input.equals("lower") ||
                baseCard.getValue() == newCard.getValue() && input.equals("equal")){
            baseCard = newCard;
            correctAnswers ++;
            return "Correct answer";
        }
        else{
            baseCard = newCard;
            lives--;
            if(lives == 0){
                sendGameToDatabase();
                return "Game Over!";
            }
            return "Wrong Answer!";
        }
    }
    private void sendGameToDatabase() {
        Game game = new Game();
        game.setPlayer(player);
        game.setCorrectAnswers(correctAnswers);
        long duration = System.currentTimeMillis() - gameStartTime;
        game.setDuration(duration);
        gameRepository.save(game);
        baseCard = null;
        if(player.getHighScore() < correctAnswers){
            player.setHighScore(correctAnswers);
            playerRepository.save(player);
        }

    }

}
