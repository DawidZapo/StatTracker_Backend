package com.stat_tracker.rest_controller.stat;

import com.stat_tracker.entity.stat.StatLine;
import com.stat_tracker.service.stat.StatLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class StatLineController {
    private StatLineService statLineService;

    @Autowired
    public StatLineController(StatLineService statLineService) {
        this.statLineService = statLineService;
    }

    @GetMapping("/stat_line/all")
    public ResponseEntity<List<StatLine>> getAllStatLines(){
        return ResponseEntity.ok(statLineService.findAllStatLines());
    }
}
