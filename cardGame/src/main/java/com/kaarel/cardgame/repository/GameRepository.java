package com.kaarel.cardgame.repository;

import com.kaarel.cardgame.entity.Game;
import com.kaarel.cardgame.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// 2. Tagastatakse kõik mängijad
// 3. Tagastatakse kõik mängud
// 4. Tagatatakse kõik mängud high-score järjekorras
// 5. Tagastataske kõik selle mängija mängud
// 6. Tagastataske kõik selle mängija mängud high-score järjekorras
// 7. Tagasta kõik mängud millel on vähemalt 2 õiget vastust
// 8. Tagasta kõige suurema skooriga mängija - 2te moodi (mängud järjekorda, mängijad high-score järjekorda)
public interface GameRepository extends JpaRepository<Game,Long> {

    List<Game> findByPlayer(Player player);

    List<Game> findAllByPlayerOrderByCorrectAnswersDesc(Player player);

    List<Game> findAllByCorrectAnswersIsGreaterThan(int correctAnswers);

    List<Game> findAllByOrderByDurationDesc();

    List<Game> findAllByOrderByCorrectAnswersDesc();//viimane

    List<Game> findFirstByOrderByDurationAsc();







}
