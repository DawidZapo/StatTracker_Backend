package com.stat_tracker.dto.stat_team;

import com.stat_tracker.dto.stat.StatLineDto;
import com.stat_tracker.dto.stat_player.StatPlayerDto;

import java.util.List;

public class StatTeamDto {
    private Long id;
    private String name;
    private String location;
    private String address;
    private String arena;
    private List<Integer> scores;
    private StatLineDto statLine;
    private List<StatPlayerDto> players;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArena() {
        return arena;
    }

    public void setArena(String arena) {
        this.arena = arena;
    }

    public List<Integer> getScores() {
        return scores;
    }

    public void setScores(List<Integer> scores) {
        this.scores = scores;
    }

    public StatLineDto getStatLine() {
        return statLine;
    }

    public void setStatLine(StatLineDto statLine) {
        this.statLine = statLine;
    }

    public List<StatPlayerDto> getPlayers() {
        return players;
    }

    public void setPlayers(List<StatPlayerDto> players) {
        this.players = players;
    }
}
