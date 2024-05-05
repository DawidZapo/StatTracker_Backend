package com.stat_tracker.repository.team;

import com.stat_tracker.entity.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
