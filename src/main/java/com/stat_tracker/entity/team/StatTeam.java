package com.stat_tracker.entity.team;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.stat_tracker.entity.game.Game;
import com.stat_tracker.entity.player.StatPlayer;
import com.stat_tracker.entity.score.Score;
import com.stat_tracker.entity.stat.StatLine;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "stat_team")
public class StatTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "team_id")
    @JsonBackReference
    private Team team;
    @OneToMany(mappedBy = "statTeam", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<StatPlayer> statPlayers;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "home_game_id")
    private Game homeGame;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "away_game_id")
    private Game awayGame;
    @OneToMany(mappedBy = "statTeam", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Score> scores;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stat_line_id", referencedColumnName = "id")
    private StatLine statLine;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<StatPlayer> getStatPlayers() {
        return statPlayers;
    }

    public void setStatPlayers(List<StatPlayer> statPlayers) {
        this.statPlayers = statPlayers;
    }

    public Game getHomeGame() {
        return homeGame;
    }

    public void setHomeGame(Game homeGame) {
        this.homeGame = homeGame;
    }

    public Game getAwayGame() {
        return awayGame;
    }

    public void setAwayGame(Game awayGame) {
        this.awayGame = awayGame;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    public StatLine getStatLine() {
        return statLine;
    }

    public void setStatLine(StatLine statLine) {
        this.statLine = statLine;
    }

    @Override
    public String toString() {
        return "StatTeam{" +
                "id=" + id +
                ", team=" + team +
                ", statPlayers=" + statPlayers +
                ", scores=" + scores +
                ", statLine=" + statLine +
                '}';
    }
}
