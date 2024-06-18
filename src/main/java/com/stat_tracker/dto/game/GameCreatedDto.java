package com.stat_tracker.dto.game;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class GameCreatedDto {
    private Long id;
    private LocalDate date;
    private LocalTime time;
    private String season;
    private boolean official;
    private int quarterLengthMin;
    private TeamDto home;
    private TeamDto away;

    @Override
    public String toString() {
        return "GameCreatedDto{" +
                "id=" + id +
                ", date=" + date +
                ", time=" + time +
                ", season='" + season + '\'' +
                ", official=" + official +
                ", quarterLengthMin=" + quarterLengthMin +
                ", home=" + home +
                ", away=" + away +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public boolean isOfficial() {
        return official;
    }

    public void setOfficial(boolean official) {
        this.official = official;
    }

    public int getQuarterLengthMin() {
        return quarterLengthMin;
    }

    public void setQuarterLengthMin(int quarterLengthMin) {
        this.quarterLengthMin = quarterLengthMin;
    }

    public TeamDto getHome() {
        return home;
    }

    public void setHome(TeamDto home) {
        this.home = home;
    }

    public TeamDto getAway() {
        return away;
    }

    public void setAway(TeamDto away) {
        this.away = away;
    }

    public static class TeamDto {
        private Long id;
        private String name;
        private List<PlayerDto> players;

        @Override
        public String toString() {
            return "TeamDto{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", players=" + players +
                    '}';
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

        public List<PlayerDto> getPlayers() {
            return players;
        }

        public void setPlayers(List<PlayerDto> players) {
            this.players = players;
        }
    }

    public static class PlayerDto {
        private Long id;
        private String firstName;
        private String lastName;
        private Integer shirtNumber;
        private boolean startingFive;

        @Override
        public String toString() {
            return "PlayerDto{" +
                    "id=" + id +
                    ", firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", shirtNumber=" + shirtNumber +
                    ", startingFive=" + startingFive +
                    '}';
        }

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

        public Integer getShirtNumber() {
            return shirtNumber;
        }

        public void setShirtNumber(Integer shirtNumber) {
            this.shirtNumber = shirtNumber;
        }

        public boolean isStartingFive() {
            return startingFive;
        }

        public void setStartingFive(boolean startingFive) {
            this.startingFive = startingFive;
        }
    }
}
