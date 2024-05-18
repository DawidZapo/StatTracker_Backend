package com.stat_tracker.rest_controller.play;

import com.stat_tracker.entity.plays_inheritance.Play;
import com.stat_tracker.repository.play.PlayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PlayController {
    private PlayRepository playRepository;

    @Autowired
    public PlayController(PlayRepository playRepository) {
        this.playRepository = playRepository;
    }

    @GetMapping("/plays")
    public List<Play> getPlays(){
        return playRepository.findAll();
    }
}
