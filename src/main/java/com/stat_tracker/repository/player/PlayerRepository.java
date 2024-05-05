package com.stat_tracker.repository.player;

import com.stat_tracker.entity.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PlayerRepository extends JpaRepository<Player, Long> {
}
