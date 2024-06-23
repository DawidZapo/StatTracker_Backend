package com.stat_tracker.dto.game;

import com.stat_tracker.dto.stat.StatLineDto;
import com.stat_tracker.entity.plays.abstract_play.Play;

import java.time.LocalDateTime;
import java.util.List;

public class GameToHandleDto {
    private Long id;
    private LocalDateTime dateTime;
    private String season;
    private Integer quarterLengthMin;
    private Long timeRemainingMs;
    private TeamDto home;
    private TeamDto away;

    // PlayDto to be introduced
    private List<Play> plays;

    public static class TeamDto{
        private Long teamId;
        private Long statTeamId;
        private String name;
        List<PlayerDto> players;
        List<ScoreDto> scores;
        private StatLineDto stats;

        public Long getTeamId() {
            return teamId;
        }

        public void setTeamId(Long teamId) {
            this.teamId = teamId;
        }

        public Long getStatTeamId() {
            return statTeamId;
        }

        public void setStatTeamId(Long statTeamId) {
            this.statTeamId = statTeamId;
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

        public StatLineDto getStats() {
            return stats;
        }

        public void setStats(StatLineDto stats) {
            this.stats = stats;
        }

        public List<ScoreDto> getScores() {
            return scores;
        }

        public void setScores(List<ScoreDto> scores) {
            this.scores = scores;
        }
    }

    public static class PlayerDto{
        private Long playerId;
        private Long statPlayerId;
        private String firstName;
        private String lastName;
        private Integer shirtNumber;
        private Boolean startingFive;
        private StatLineDto stats;

        // PlayDto to be introduced
        private List<Play> plays;

        public Long getPlayerId() {
            return playerId;
        }

        public void setPlayerId(Long playerId) {
            this.playerId = playerId;
        }

        public Long getStatPlayerId() {
            return statPlayerId;
        }

        public void setStatPlayerId(Long statPlayerId) {
            this.statPlayerId = statPlayerId;
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

        public Boolean getStartingFive() {
            return startingFive;
        }

        public void setStartingFive(Boolean startingFive) {
            this.startingFive = startingFive;
        }

        public StatLineDto getStats() {
            return stats;
        }

        public void setStats(StatLineDto stats) {
            this.stats = stats;
        }

        public List<Play> getPlays() {
            return plays;
        }

        public void setPlays(List<Play> plays) {
            this.plays = plays;
        }
    }

    public static class ScoreDto{
        private Long id;
        private Integer worth;
        private Integer part;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Integer getWorth() {
            return worth;
        }

        public void setWorth(Integer worth) {
            this.worth = worth;
        }

        public Integer getPart() {
            return part;
        }

        public void setPart(Integer part) {
            this.part = part;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public Integer getQuarterLengthMin() {
        return quarterLengthMin;
    }

    public void setQuarterLengthMin(Integer quarterLengthMin) {
        this.quarterLengthMin = quarterLengthMin;
    }

    public Long getTimeRemainingMs() {
        return timeRemainingMs;
    }

    public void setTimeRemainingMs(Long timeRemainingMs) {
        this.timeRemainingMs = timeRemainingMs;
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

    public List<Play> getPlays() {
        return plays;
    }

    public void setPlays(List<Play> plays) {
        this.plays = plays;
    }
}
