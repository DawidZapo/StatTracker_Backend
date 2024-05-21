package com.stat_tracker.rest_controller.team;

import com.stat_tracker.entity.team.StatTeam;
import com.stat_tracker.service.team.StatTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StatTeamController {
    private StatTeamService statTeamService;

    @Autowired
    public StatTeamController(StatTeamService statTeamRestController) {
        this.statTeamService = statTeamRestController;
    }
    @GetMapping("/stat_teams")
    public ResponseEntity<List<StatTeam>> getStatTeams(){
        return ResponseEntity.ok(statTeamService.findAll());
    }
}
