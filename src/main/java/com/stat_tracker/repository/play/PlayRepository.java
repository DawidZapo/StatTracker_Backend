package com.stat_tracker.repository.play;

import com.stat_tracker.entity.plays.abstract_play.Play;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlayRepository extends JpaRepository<Play, Long> {
    @Query("select p from Play p where p.timeRemaining = :timeRemaining and p.quarter = :quarter ")
    List<Play> findAllByTimeRemaining(@Param("timeRemaining") Long timeRemaining, @Param("quarter") Integer quarter);
}
