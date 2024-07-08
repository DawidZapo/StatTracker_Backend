package com.stat_tracker.rest_controller.play;

import com.stat_tracker.dto.plays.ShotPlayDto;
import com.stat_tracker.entity.plays.abstract_play.Play;
import com.stat_tracker.service.play.PlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class PlayController {
    private PlayService playService;

    @Autowired
    public PlayController(PlayService playService) {
        this.playService = playService;
    }

    @GetMapping("/plays")
    public ResponseEntity<List<Play>> getPlays(){
        return ResponseEntity.ok(playService.findAll());
    }

    @PostMapping("/play/save/shot_play")
    public ShotPlayDto savePlay(@RequestBody ShotPlayDto shotPlayDto){
        return playService.savePlay(shotPlayDto);
    }
}
