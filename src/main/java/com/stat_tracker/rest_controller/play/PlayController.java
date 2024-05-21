package com.stat_tracker.rest_controller.play;

import com.stat_tracker.entity.plays.abstract_play.Play;
import com.stat_tracker.service.play.PlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
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
}
