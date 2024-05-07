package com.stat_tracker.repository.game;

import com.stat_tracker.model.entity.game.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game,Long> {
}
