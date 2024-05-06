package com.stat_tracker.repository.team;

import com.stat_tracker.entity.team.StatTeam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatTeamRepository extends JpaRepository<StatTeam, Long> {
}
