package com.stat_tracker.dto.plays;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.stat_tracker.entity.plays.enums.Hand;
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "playType",  // Property used to determine the concrete class
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = AssistDto.class, name = "ASSIST"),
        @JsonSubTypes.Type(value = BlockDto.class, name = "BLOCK"),
        @JsonSubTypes.Type(value = FoulDto.class, name = "FOUL"),
        @JsonSubTypes.Type(value = ReboundDto.class, name = "REBOUND"),
        @JsonSubTypes.Type(value = ShotPlayDto.class, name = "SHOTPLAY"),
        @JsonSubTypes.Type(value = StealDto.class, name = "STEAL"),
        @JsonSubTypes.Type(value = TurnoverDto.class, name = "TURNOVER"),
        @JsonSubTypes.Type(value = ViolationDto.class, name = "VIOLATION")
})
public class PlayDto {
    protected Long id;
    protected Long gameId;
    protected Long statPlayerId;
    protected String firstName;
    protected String lastName;
    protected Long timeRemaining;
    protected Integer quarter;
    protected Integer order;
    protected String comments;
    protected String playType;
    protected Hand hand;

    public PlayDto() {
    }

    public PlayDto(Long id, Long gameId, Long statPlayerId, String firstName, String lastName, Long timeRemaining, Integer quarter, Integer order, String comments, String playType, Hand hand) {
        this.id = id;
        this.gameId = gameId;
        this.statPlayerId = statPlayerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.timeRemaining = timeRemaining;
        this.quarter = quarter;
        this.order = order;
        this.comments = comments;
        this.playType = playType;
        this.hand = hand;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Long getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(Long timeRemaining) {
        this.timeRemaining = timeRemaining;
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

    public Integer getQuarter() {
        return quarter;
    }

    public void setQuarter(Integer quarter) {
        this.quarter = quarter;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "PlayDto{" +
                "id=" + id +
                ", gameId=" + gameId +
                ", statPlayerId=" + statPlayerId +
                ", timeRemaining=" + timeRemaining +
                ", quarter=" + quarter +
                ", comments='" + comments + '\'' +
                ", playType='" + playType + '\'' +
                ", hand=" + hand +
                '}';
    }
}
