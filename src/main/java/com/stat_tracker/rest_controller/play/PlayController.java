package com.stat_tracker.rest_controller.play;

import com.stat_tracker.dto.plays.*;
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
    public ResponseEntity<Long> savePlay(@RequestBody ShotPlayDto shotPlayDto){
        return ResponseEntity.ok(playService.savePlay(shotPlayDto));
    }

    @PostMapping("/play/save/assist")
    public ResponseEntity<Long> saveAssist(@RequestBody AssistDto assistDto){
        return ResponseEntity.ok(playService.saveAssist(assistDto));
    }

    @PostMapping("/play/save/block")
    public ResponseEntity<Long> saveBody(@RequestBody BlockDto blockDto){
        return ResponseEntity.ok(playService.saveBlock(blockDto));
    }

    @PostMapping("/play/save/rebound")
    public ResponseEntity<Long> saveRebound(@RequestBody ReboundDto reboundDto){
        return ResponseEntity.ok(playService.saveRebound(reboundDto));
    }

    @PostMapping("/play/save/foul")
    public ResponseEntity<Long> saveFoul(@RequestBody FoulDto foulDto){
        return ResponseEntity.ok(playService.saveFoul(foulDto));
    }

    @PostMapping("/play/save/steal")
    public ResponseEntity<Long> saveSteal(@RequestBody StealDto stealDto){
        return ResponseEntity.ok(playService.saveSteal(stealDto));
    }

    @PostMapping("/play/save/turnover")
    public ResponseEntity<Long> saveTurnover(@RequestBody TurnoverDto turnoverDto){
        return ResponseEntity.ok(playService.saveTurnover(turnoverDto));
    }

}
