package com.stat_tracker.dto.team;

import com.stat_tracker.dto.player.PlayerDto;

import java.util.List;

public class TeamDto {
    private Long id;
    private String name;
    private String location;
    private String arena;
    private String address;
    private List<PlayerDto> currentPlayers;

    public TeamDto(Long id, String name, String location, String arena, String address, List<PlayerDto> currentPlayers) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.arena = arena;
        this.address = address;
        this.currentPlayers = currentPlayers;
    }

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

    public String getArena() {
        return arena;
    }

    public void setArena(String arena) {
        this.arena = arena;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<PlayerDto> getCurrentPlayers() {
        return currentPlayers;
    }

    public void setCurrentPlayers(List<PlayerDto> currentPlayers) {
        this.currentPlayers = currentPlayers;
    }
}
