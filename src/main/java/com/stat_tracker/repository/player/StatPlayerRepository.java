package com.stat_tracker.repository.player;

import com.stat_tracker.entity.player.StatPlayer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatPlayerRepository extends JpaRepository<StatPlayer, Long> {
}
