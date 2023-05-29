package com.kaarel.cardgame.repository;

import com.kaarel.cardgame.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// 2. Tagastatakse kõik mängijad
// 3. Tagastatakse kõik mängud
// 4. Tagatatakse kõik mängud high-score järjekorras
// 5. Tagastataske kõik selle mängija mängud
// 6. Tagastataske kõik selle mängija mängud high-score järjekorras
// 7. Tagasta kõik mängud millel on vähemalt 2 õiget vastust
// 8. Tagasta kõige suurema skooriga mängija - 2te moodi (mängud järjekorda, mängijad high-score järjekorda)
public interface PlayerRepository extends JpaRepository<Player,String> {

    List<Player> findAllByOrderByHighScoreDesc();

    Player findFirstByOrderByHighScoreDesc();

    Player findTopByOrderByHighScore();

    Long findHighestScoreByName(String name);

    List<Player> findPlayerByHighScoreGreaterThanEqual(int highestScore);




}
