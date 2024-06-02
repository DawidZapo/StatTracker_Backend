package com.stat_tracker.dto.player;

import com.stat_tracker.entity.player.Position;

import java.time.LocalDate;

public class PlayerWithTeamDto extends PlayerDto{
    private String teamName;
    public PlayerWithTeamDto(){
    }

    public PlayerWithTeamDto(Long id, String firstName, String lastName, Double height, Double weight, Position position, LocalDate birth, String teamName) {
        super(id, firstName, lastName, height, weight, position, birth);
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
