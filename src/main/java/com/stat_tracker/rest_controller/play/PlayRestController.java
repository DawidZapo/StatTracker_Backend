package com.stat_tracker.rest_controller.play;

import com.stat_tracker.model.entity.plays.Play;
import com.stat_tracker.repository.play.PlayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlayRestController {
    private PlayRepository playRepository;

    @Autowired
    public PlayRestController(PlayRepository playRepository) {
        this.playRepository = playRepository;
    }

    @GetMapping("/plays")
    public List<Play> getPlays(){
        return playRepository.findAll();
    }
}
