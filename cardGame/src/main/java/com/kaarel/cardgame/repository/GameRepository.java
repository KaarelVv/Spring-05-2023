package com.kaarel.cardgame.repository;

import com.kaarel.cardgame.entity.Game;
import com.kaarel.cardgame.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game,Long> {


    List<Game> findByPlayer(Player player);
    List<Game> findAllByOrderByPlayerDesc();

}
