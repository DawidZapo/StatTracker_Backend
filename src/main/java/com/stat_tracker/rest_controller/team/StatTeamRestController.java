package com.stat_tracker.rest_controller.team;

import com.stat_tracker.entity.team.StatTeam;
import com.stat_tracker.repository.team.StatTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StatTeamRestController {
    private StatTeamRepository statTeamRepository;

    @Autowired
    public StatTeamRestController(StatTeamRepository statTeamRestController) {
        this.statTeamRepository = statTeamRestController;
    }
    @GetMapping("/stat_teams")
    public List<StatTeam> getStatTeams(){
        return statTeamRepository.findAll();
    }
}
