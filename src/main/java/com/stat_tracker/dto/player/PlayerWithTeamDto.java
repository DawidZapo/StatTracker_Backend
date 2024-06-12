package com.stat_tracker.dto.player;

import com.stat_tracker.entity.player.Position;

import java.time.LocalDate;

public class PlayerWithTeamDto extends PlayerDto{
    private String teamName;
    private Long teamId;
    public PlayerWithTeamDto(){
    }

    public PlayerWithTeamDto(Long id, String firstName, String lastName, Double height, Double weight, Position position, LocalDate birth, String teamName, Long teamId) {
        super(id, firstName, lastName, height, weight, position, birth);
        this.teamName = teamName;
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    @Override
    public String toString() {
        return "PlayerWithTeamDto{" +
                ", teamId=" + teamId +
                "} " + super.toString();
    }
}
