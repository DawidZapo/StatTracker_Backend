package com.stat_tracker.dto.plays;

import com.stat_tracker.entity.plays.enums.Hand;

public abstract class PlayDto {
    protected Long id;
    protected Long gameId;
    protected Long statPlayerId;
    protected Long duration;
    protected String comments;
    protected String playType;
    protected Hand hand;

    public PlayDto() {
    }

    public PlayDto(Long id, Long gameId, Long statPlayerId, Long duration, String comments, String playType, Hand hand) {
        this.id = id;
        this.gameId = gameId;
        this.statPlayerId = statPlayerId;
        this.duration = duration;
        this.comments = comments;
        this.playType = playType;
        this.hand = hand;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Long getStatPlayerId() {
        return statPlayerId;
    }

    public void setStatPlayerId(Long statPlayerId) {
        this.statPlayerId = statPlayerId;
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

    public String getPlayType() {
        return playType;
    }

    public void setPlayType(String playType) {
        this.playType = playType;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }
}
