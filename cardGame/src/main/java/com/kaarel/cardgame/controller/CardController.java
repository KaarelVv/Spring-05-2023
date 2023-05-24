package com.kaarel.cardgame.controller;

import com.kaarel.cardgame.model.Card;
import com.kaarel.cardgame.repository.GameRepository;
import com.kaarel.cardgame.entity.Player;
import com.kaarel.cardgame.repository.PlayerRepository;
import com.kaarel.cardgame.entity.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class CardController {
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
    @GetMapping("start/{playerName}")
    public Card start(@PathVariable String playerName){

        if (baseCard == null) {
            gameStartTime = System.currentTimeMillis();
            baseCard = new Card();
            Optional<Player> playerOptional = playerRepository.findById(playerName);
            if(playerOptional.isPresent()){
                player = playerOptional.get();
            }else {
            player = new Player();
            player.setName(playerName);
            player.setFirstCreated(new Date());
            playerRepository.save(player);
            }
            correctAnswers = 0;
            lives = 3;
        }
        roundStartTime = LocalDateTime.now();
        return baseCard;
    }

    @GetMapping("guess/{input}")
        public String guess(@PathVariable String input ){

        if (lives<= 0){
            return "Elud on otsas!";

        }
            if(!input.equals("lower")&&!input.equals("higher")&&!input.equals("equal"))
                return "Vale sisend";

            LocalDateTime limitTime = roundStartTime.plusSeconds(10);
            LocalDateTime answerTime = LocalDateTime.now();
            if (answerTime.isAfter(limitTime)) {
                lives--;   //võtab ühe elu ära
                if(lives == 0){
                    sendGameToDatabase();
                    return "Mäng läbi!";
                }
                return "Vastasid liiga hilja";
            }

            Card newCard = new Card();

            if (baseCard.getValue() < newCard.getValue() && input.equals("higher") ||
                    baseCard.getValue() > newCard.getValue() && input.equals("lower") ||
                    baseCard.getValue() == newCard.getValue() && input.equals("equal")){
                baseCard = newCard;
                correctAnswers ++;
                return "Vastasid õigesti!";
            }
            else{
                baseCard = newCard;
                lives--;
                if(lives == 0){
                    sendGameToDatabase();
                    return "Mäng läbi!";
                }
            return "Vastasid valesti!";
            }
        }




        private void sendGameToDatabase() {
            Game game = new Game();
            game.setPlayer(player);
            game.setCorrectAnswers(correctAnswers);
            long duration = System.currentTimeMillis()- gameStartTime;
            game.setDuration(duration);
            gameRepository.save(game);
            baseCard = null;
            if(player.getHighScore() < correctAnswers){
                player.setHighScore(correctAnswers);
                playerRepository.save(player);
            }

    }
}

