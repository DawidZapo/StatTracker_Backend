package com.stat_tracker.repository.stat;

import com.stat_tracker.entity.stat.StatLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatLineRepository extends JpaRepository<StatLine, Long> {
}
