package com.stat_tracker.model.entity.plays;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.stat_tracker.model.entity.game.Game;
import com.stat_tracker.model.entity.player.StatPlayer;
import jakarta.persistence.*;

@Entity
@Table(name = "plays")
public class Play{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "game_id")
    @JsonBackReference
    private Game game;
    @ManyToOne
    @JoinColumn(name = "stat_player_id")
    private StatPlayer statPlayer;

    public Long getId() {
        return id;
    }

    public Game getGame() {
        return game;
    }

    public StatPlayer getStatPlayer() {
        return statPlayer;
    }
}
