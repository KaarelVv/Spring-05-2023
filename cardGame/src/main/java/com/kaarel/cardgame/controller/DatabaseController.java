package com.kaarel.cardgame.controller;

import com.kaarel.cardgame.entity.Game;
import com.kaarel.cardgame.entity.Player;
import com.kaarel.cardgame.repository.GameRepository;
import com.kaarel.cardgame.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
// 2. Tagastatakse kõik mängijad
// 3. Tagastatakse kõik mängud
// 4. Tagatatakse kõik mängud high-score järjekorras
// 5. Tagastataske kõik selle mängija mängud
// 6. Tagastataske kõik selle mängija mängud high-score järjekorras
// 7. Tagasta kõik mängud millel on vähemalt 2 õiget vastust
// 8. Tagasta kõige suurema skooriga mängija - 2te moodi (mängud järjekorda, mängijad high-score järjekorda)
@RestController
public class DatabaseController {
    @Autowired
    GameRepository gameRepository;
    @Autowired
    PlayerRepository playerRepository;


    // 2. Tagastatakse kõik mängijad
    @GetMapping("players")
    public List getPlayers(){
        return playerRepository.findAll();
    }

    // 3. Tagastatakse kõik mängud
    @GetMapping("games")
    public List games(){
        return gameRepository.findAll();
    }

    // 4. Tagatatakse kõik mängud high-score järjekorras

    @GetMapping("card-game")
    public List getHighScore(){
        return playerRepository.findAllByOrderByHighScoreDesc();
    }

    // 5. Tagastataske kõik selle mängija mängud
    @GetMapping("/games/players")
    public List<Game> getGamesByPlayers() {
        return gameRepository.findByPlayer(playerRepository.findById("playerId").orElse(null));
    }

    // 6. Tagastataske kõik selle mängija mängud high-score järjekorras

    @GetMapping("/games/players/descending")
    public List<Game> getGamesByPlayerDescending() {
        return gameRepository.findAllByOrderByPlayerDesc();
    }

    // 7. Tagasta kõik mängud millel on vähemalt 2 õiget vastust
    @GetMapping("/players/correct-answers")
    public List<Player> getPlayersWithTwoCorrectAnswers() {
        return playerRepository.findByCorrectAnswers(2);
    }

    //  8. Tagasta kõige suurema skooriga mängija - 2te moodi (mängud järjekorda, mängijad high-score järjekorda)
    @GetMapping("/players/highest-score")
    public Player getPlayerWithHighestScore() {
        return playerRepository.findTopByOrderByScoreDesc().orElse(null);
    }
}
