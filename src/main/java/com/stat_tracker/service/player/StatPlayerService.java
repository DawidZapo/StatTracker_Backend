package com.stat_tracker.service.player;

import com.stat_tracker.entity.player.StatPlayer;
import com.stat_tracker.repository.player.StatPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatPlayerService {
    private StatPlayerRepository statPlayerService;
    @Autowired
    public StatPlayerService(StatPlayerRepository statPlayerService) {
        this.statPlayerService = statPlayerService;
    }

    public List<StatPlayer> findAll(){
        return statPlayerService.findAll();
    }
}
