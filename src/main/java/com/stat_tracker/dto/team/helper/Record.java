package com.stat_tracker.dto.team.helper;

public class Record {
    int order;
    String name;
    String playerFullName;
    Double value = 0.0;
    String date;
    String opponent;
    String score;

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getPlayerFullName() {
        return playerFullName;
    }

    public void setPlayerFullName(String playerFullName) {
        this.playerFullName = playerFullName;
    }
}
