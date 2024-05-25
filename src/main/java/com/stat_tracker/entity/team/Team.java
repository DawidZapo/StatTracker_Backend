package com.stat_tracker.entity.team;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.stat_tracker.entity.player.Player;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "team_name")
    private String name;
    @Column(name = "location")
    private String location;
    @Column(name = "arena")
    private String arena;
    @Column(name = "address")
    private String address;
    @OneToMany(mappedBy = "currentTeam")
    @JsonBackReference
    private List<Player> currentPlayers;
    @OneToMany(mappedBy = "team")
    @JsonManagedReference
    private List<StatTeam> statTeams;

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

    public List<Player> getCurrentPlayers() {
        return currentPlayers;
    }

    public void setCurrentPlayers(List<Player> currentPlayers) {
        this.currentPlayers = currentPlayers;
    }

    public List<StatTeam> getStatTeams() {
        return statTeams;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", arena='" + arena + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
