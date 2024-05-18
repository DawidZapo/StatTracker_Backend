package com.stat_tracker.entity.plays_inheritance;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.stat_tracker.entity.game.Game;
import com.stat_tracker.entity.player.StatPlayer;
import jakarta.persistence.*;

import java.time.Duration;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Play {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    protected Long id;
    @ManyToOne
    @JoinColumn(name = "game_id")
    @JsonBackReference
    protected Game game;
    @ManyToOne
    @JoinColumn(name = "stat_player_id")
    @JsonBackReference
    protected StatPlayer statPlayer;
    @Column(name = "duration")
    protected Duration duration;
    @Column(name = "comments")
    protected String comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public StatPlayer getStatPlayer() {
        return statPlayer;
    }

    public void setStatPlayer(StatPlayer statPlayer) {
        this.statPlayer = statPlayer;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
