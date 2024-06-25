package com.stat_tracker.entity.plays.abstract_play;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.stat_tracker.entity.game.Game;
import com.stat_tracker.entity.player.StatPlayer;
import com.stat_tracker.entity.plays.enums.Hand;
import jakarta.persistence.*;

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
    protected Long duration;
    @Column(name = "comments")
    protected String comments;
    @Column(name = "hand")
    @Enumerated(EnumType.STRING)
    protected Hand hand;
    @Transient
    protected String playType = writePlayType();

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

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public void setPlayType(String playType) {
        this.playType = playType;
    }

    public String getPlayType() {
        return playType;
    }

    private String writePlayType() {
        String className = this.getClass().getSimpleName().toUpperCase();

        return switch (className) {
            case "ASSIST", "BLOCK", "FOUL", "REBOUND", "SHOTPLAY", "STEAL", "TURNOVER" -> className;
            default -> throw new RuntimeException("No play type match");
        };
    }
}
