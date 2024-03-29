package com.kaarel.cardgame.controller;

import com.kaarel.cardgame.entity.Game;
import com.kaarel.cardgame.entity.Player;
import com.kaarel.cardgame.repository.GameRepository;
import com.kaarel.cardgame.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 2. Tagastatakse kõik mängijad
// 3. Tagastatakse kõik mängud
// 4. Tagatatakse kõik mängud high-score järjekorras
// 5. Tagastataske kõik selle mängija mängud
// 6. Tagastataske kõik selle mängija mängud high-score järjekorras
// 7. Tagasta kõik mängud millel on vähemalt 2 õiget vastust
// 8. Tagasta kõige suurema skooriga mängija - 2te moodi (mängud järjekorda, mängijad high-score järjekorda)
//Uued:
// 9.  Tagasta kõige suurema skooriga mäng
// 10. Tagasta selle mängija kõige suurema skooriga mäng
// 11. Tagasta kõik mängijad kellel on high-score vähemalt 2
// 12. Tagasta mängud kestvuse järjekorras
// 13. Tagasta kõige lühema kestvusega mäng
@RestController
@CrossOrigin("http://localhost:3000")
public class DatabaseController {
    @Autowired
    GameRepository gameRepository;
    @Autowired
    PlayerRepository playerRepository;


    // 2. Tagastatakse kõik mängijad
    @GetMapping("players")
    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

    // 3. Tagastatakse kõik mängud
    @GetMapping("games")
    public List<Game> getGames() {
        return gameRepository.findAll();
    }

    // 4. Tagatatakse kõik mängud high-score järjekorras

    @GetMapping("card-game")
    public List<Player> getHighScore() {
        return playerRepository.findAllByOrderByHighScoreDesc();
    }

    @GetMapping("game-by-score")
    public List<Game> getHighScoreGames() {
        return gameRepository.findAllByOrderByCorrectAnswersDesc();
    }

    @GetMapping("player-by-score")
    public List<Player> getHighScorePlayer() {
        return playerRepository.findAllByOrderByHighScoreDesc();
    }

    // 5. Tagastataske kõik selle mängija mängud
    @GetMapping("/games/players")
    public List<Game> getGamesByPlayers() {
        return gameRepository.findByPlayer(playerRepository.findById("playerId").orElse(null));
    }

    // 6. Tagastataske kõik selle mängija mängud high-score järjekorras

    @GetMapping("/games/players/descending/{name}")
    public List<Game> getGamesByPlayerDescending(@PathVariable String name) {
        Player player = playerRepository.findById(name).get();
        return gameRepository.findAllByPlayerOrderByCorrectAnswersDesc(player);
    }

    // 7. Tagasta kõik mängud millel on vähemalt 2 õiget vastust
    @GetMapping("/players/correct-answers")
    public List<Game> getPlayersWithAtLeastTwoOrMoreCorrectAnswers() {
        return gameRepository.findAllByCorrectAnswersIsGreaterThan(2);
    }

    //  8. Tagasta kõige suurema skooriga mängija - 2te moodi (mängud järjekorda, mängijad high-score järjekorda)
    @GetMapping("/players/highest-score")
    public Player getPlayerWithHighestScore() {
        return playerRepository.findFirstByOrderByHighScoreDesc();
    }

    //  9.  Tagasta kõige suurema skooriga mäng

    @GetMapping("/game/highest-score")
    public Player getHighestScoreGame() {
        return playerRepository.findTopByOrderByHighScore();
    }

    // 10. Tagasta selle mängija kõige suurema skooriga mäng

    @GetMapping("/player/high-score/{name}")
    public Long getPlayerReturnHighScore(@PathVariable String name) {
        return playerRepository.findHighestScoreByName(name);
    }

    // 11. Tagasta kõik mängijad kellel on high-score vähemalt 2

    @GetMapping("/player/return-player/high-score-2")
    public List<Player> getPlayerHighScoreGreaterThan() {
        return playerRepository.findPlayerByHighScoreGreaterThanEqual(2);
    }

    // 12. Tagasta mängud kestvuse järjekorras

    @GetMapping("/game/return-duration")
    public List<Game> getGameDurationDescending() {
        return gameRepository.findAllByOrderByDurationDesc();
    }

    // 13. Tagasta kõige lühema kestvusega mäng

    @GetMapping("/game/return-shortest-duration")
    public List<Game> getGameDurationShortest() {
        return gameRepository.findFirstByOrderByDurationAsc();
    }

    @GetMapping("/game/delete/{id}")
    public List<Game> deleteGameById(@PathVariable Long id) {
        gameRepository.deleteById(id);
        return gameRepository.findAllByOrderByCorrectAnswersDesc();
    }
}



