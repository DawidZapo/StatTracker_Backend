package com.stat_tracker.service.player;

import com.stat_tracker.entity.player.Player;
import com.stat_tracker.repository.player.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    private PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }
    public List<Player> findALl(){
        return playerRepository.findAll();
    }
}
