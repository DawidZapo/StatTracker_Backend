package com.stat_tracker.service.player;

import com.stat_tracker.entity.player.StatPlayer;
import com.stat_tracker.repository.player.StatPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatPlayerService {
    private StatPlayerRepository statPlayerRepository;
    @Autowired
    public StatPlayerService(StatPlayerRepository statPlayerRepository) {
        this.statPlayerRepository = statPlayerRepository;
    }

    public List<StatPlayer> findAll(){
        return statPlayerRepository.findAll();
    }

    public StatPlayer findById(Long id){
        return statPlayerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("StatPlayer not found id: " + id));
    }
}
