package com.stat_tracker.repository.play;

import com.stat_tracker.entity.plays_inheritance.Play;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayRepository extends JpaRepository<Play, Long> {
}
