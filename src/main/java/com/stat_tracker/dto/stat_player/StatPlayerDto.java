package com.stat_tracker.dto.stat_player;

import com.stat_tracker.dto.stat.StatLineDto;

public class StatPlayerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private int shirtNumber;
    private boolean startingFive;
    private StatLineDto statLine;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getShirtNumber() {
        return shirtNumber;
    }

    public void setShirtNumber(int shirtNumber) {
        this.shirtNumber = shirtNumber;
    }

    public boolean isStartingFive() {
        return startingFive;
    }

    public void setStartingFive(boolean startingFive) {
        this.startingFive = startingFive;
    }

    public StatLineDto getStatLine() {
        return statLine;
    }

    public void setStatLine(StatLineDto statLine) {
        this.statLine = statLine;
    }
}
