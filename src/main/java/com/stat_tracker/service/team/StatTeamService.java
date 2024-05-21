package com.stat_tracker.service.team;

import com.stat_tracker.entity.team.StatTeam;
import com.stat_tracker.repository.team.StatTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatTeamService {
    private StatTeamRepository statTeamRepository;
    @Autowired
    public StatTeamService(StatTeamRepository statTeamRepository) {
        this.statTeamRepository = statTeamRepository;
    }

    public List<StatTeam> findAll(){
        return statTeamRepository.findAll();
    }
}
