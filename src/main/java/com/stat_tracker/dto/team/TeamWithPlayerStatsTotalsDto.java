package com.stat_tracker.dto.team;

import com.stat_tracker.dto.player.PlayerWithStatsTotalsDto;

import java.util.ArrayList;
import java.util.List;

public class TeamWithPlayerStatsTotalsDto {
    List<PlayerWithStatsTotalsDto> players = new ArrayList<>();

    public List<PlayerWithStatsTotalsDto> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerWithStatsTotalsDto> players) {
        this.players = players;
    }

    public void addPlayer(PlayerWithStatsTotalsDto player){
        players.add(player);
    }
}
