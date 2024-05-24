package com.stat_tracker.rest_controller.team;

import com.stat_tracker.entity.team.StatTeam;
import com.stat_tracker.service.team.StatTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class StatTeamController {
    private StatTeamService statTeamService;

    @Autowired
    public StatTeamController(StatTeamService statTeamRestController) {
        this.statTeamService = statTeamRestController;
    }
    @GetMapping("/stat_team/all")
    public ResponseEntity<List<StatTeam>> getStatTeams(){
        return ResponseEntity.ok(statTeamService.findAll());
    }

    @GetMapping("/stat_team/{id}")
    public ResponseEntity<StatTeam> getStatTeam(@PathVariable("id") Long id){
        return ResponseEntity.ok(statTeamService.findStatTeam(id));
    }
}
