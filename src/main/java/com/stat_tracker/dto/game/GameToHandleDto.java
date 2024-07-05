package com.stat_tracker.dto.game;

import com.stat_tracker.dto.plays.PlayDto;
import com.stat_tracker.dto.stat.StatLineDto;
import com.stat_tracker.entity.plays.enums.Hand;

import java.time.LocalDateTime;
import java.util.List;

public class GameToHandleDto {
    private Long id;
    private LocalDateTime localDateTime;
    private String season;
    private Integer quarterLengthMin;
    private Integer overtimeLengthMin;
    private Long currentQuarterTimeMs;
    private Integer currentQuarter;
    private TeamDto home;
    private TeamDto away;
    private List<PlayDto> plays;

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
        private Boolean onCourt;
        private StatLineDto stats;
        private List<PlayDto> plays;
        private Hand dominantHand;

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

        public List<PlayDto> getPlays() {
            return plays;
        }

        public void setPlays(List<PlayDto> plays) {
            this.plays = plays;
        }

        public Boolean getOnCourt() {
            return onCourt;
        }

        public void setOnCourt(Boolean onCourt) {
            this.onCourt = onCourt;
        }

        public Hand getDominantHand() {
            return dominantHand;
        }

        public void setDominantHand(Hand dominantHand) {
            this.dominantHand = dominantHand;
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

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
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

    public Long getCurrentQuarterTimeMs() {
        return currentQuarterTimeMs;
    }

    public void setCurrentQuarterTimeMs(Long currentQuarterTimeMs) {
        this.currentQuarterTimeMs = currentQuarterTimeMs;
    }

    public Integer getCurrentQuarter() {
        return currentQuarter;
    }

    public void setCurrentQuarter(Integer currentQuarter) {
        this.currentQuarter = currentQuarter;
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

    public List<PlayDto> getPlays() {
        return plays;
    }

    public void setPlays(List<PlayDto> plays) {
        this.plays = plays;
    }

    public Integer getOvertimeLengthMin() {
        return overtimeLengthMin;
    }

    public void setOvertimeLengthMin(Integer overtimeLengthMin) {
        this.overtimeLengthMin = overtimeLengthMin;
    }
}
