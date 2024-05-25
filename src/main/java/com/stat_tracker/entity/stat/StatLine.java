package com.stat_tracker.entity.stat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stat_tracker.entity.player.StatPlayer;
import com.stat_tracker.entity.team.StatTeam;
import jakarta.persistence.*;

@Entity
@Table(name = "stat_line")
public class StatLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "time_on_court_in_ms")
    private Long timeOnCourtInMs;
    @Column(name = "two_attempted")
    private Integer twoAttempted;
    @Column(name = "two_made")
    private Integer twoMade;
    @Column(name = "three_attempted")
    private Integer threeAttempted;
    @Column(name = "three_made")
    private Integer threeMade;
    @Column(name = "free_throw_attempted")
    private Integer freeThrowAttempted;
    @Column(name = "free_throw_made")
    private Integer freeThrowMade;
    @Column(name = "total_points")
    private Integer totalPoints;
    @Column(name = "off_rebounds")
    private Integer offRebounds;
    @Column(name = "def_rebounds")
    private Integer defRebounds;
    @Column(name = "assists")
    private Integer assists;
    @Column(name = "fouls")
    private Integer fouls;
    @Column(name = "forced_fouls")
    private Integer forcedFouls;
    @Column(name = "turnovers")
    private Integer turnovers;
    @Column(name = "steals")
    private Integer steals;
    @Column(name = "blocks")
    private Integer blocks;
    @Column(name = "blocks_received")
    private Integer blocksReceived;
    @Column(name = "eval")
    private Integer eval;
    @Column(name = "plus_minus")
    private Integer plusMinus;
    @Column(name = "possessions")
    private Integer possessions;
    @OneToOne(mappedBy = "statLine")
    @JsonIgnore
    private StatPlayer statPlayer;
    @OneToOne(mappedBy = "statLine")
    @JsonIgnore
    private StatTeam statTeam;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTimeOnCourtInMs() {
        return timeOnCourtInMs;
    }

    public void setTimeOnCourtInMs(Long timeOnCourtInMs) {
        this.timeOnCourtInMs = timeOnCourtInMs;
    }

    public Integer getTwoAttempted() {
        return twoAttempted;
    }

    public void setTwoAttempted(Integer twoAttempted) {
        this.twoAttempted = twoAttempted;
    }

    public Integer getTwoMade() {
        return twoMade;
    }

    public void setTwoMade(Integer twoMade) {
        this.twoMade = twoMade;
    }

    public Integer getThreeAttempted() {
        return threeAttempted;
    }

    public void setThreeAttempted(Integer threeAttempted) {
        this.threeAttempted = threeAttempted;
    }

    public Integer getThreeMade() {
        return threeMade;
    }

    public void setThreeMade(Integer threeMade) {
        this.threeMade = threeMade;
    }

    public Integer getFreeThrowAttempted() {
        return freeThrowAttempted;
    }

    public void setFreeThrowAttempted(Integer freeThrowAttempted) {
        this.freeThrowAttempted = freeThrowAttempted;
    }

    public Integer getFreeThrowMade() {
        return freeThrowMade;
    }

    public void setFreeThrowMade(Integer freeThrowMade) {
        this.freeThrowMade = freeThrowMade;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Integer getOffRebounds() {
        return offRebounds;
    }

    public void setOffRebounds(Integer offRebounds) {
        this.offRebounds = offRebounds;
    }

    public Integer getDefRebounds() {
        return defRebounds;
    }

    public void setDefRebounds(Integer defRebounds) {
        this.defRebounds = defRebounds;
    }

    public Integer getAssists() {
        return assists;
    }

    public void setAssists(Integer assists) {
        this.assists = assists;
    }

    public Integer getFouls() {
        return fouls;
    }

    public void setFouls(Integer fouls) {
        this.fouls = fouls;
    }

    public Integer getForcedFouls() {
        return forcedFouls;
    }

    public void setForcedFouls(Integer forcedFouls) {
        this.forcedFouls = forcedFouls;
    }

    public Integer getTurnovers() {
        return turnovers;
    }

    public void setTurnovers(Integer turnovers) {
        this.turnovers = turnovers;
    }

    public Integer getSteals() {
        return steals;
    }

    public void setSteals(Integer steals) {
        this.steals = steals;
    }

    public Integer getBlocks() {
        return blocks;
    }

    public void setBlocks(Integer blocks) {
        this.blocks = blocks;
    }

    public Integer getBlocksReceived() {
        return blocksReceived;
    }

    public void setBlocksReceived(Integer blocksReceived) {
        this.blocksReceived = blocksReceived;
    }

    public Integer getEval() {
        return eval;
    }

    public void setEval(Integer eval) {
        this.eval = eval;
    }

    public Integer getPlusMinus() {
        return plusMinus;
    }

    public void setPlusMinus(Integer plusMinus) {
        this.plusMinus = plusMinus;
    }

    public Integer getPossessions() {
        return possessions;
    }

    public void setPossessions(Integer possessions) {
        this.possessions = possessions;
    }

    public StatPlayer getStatPlayer() {
        return statPlayer;
    }

    public void setStatPlayer(StatPlayer statPlayer) {
        this.statPlayer = statPlayer;
    }

    public StatTeam getStatTeam() {
        return statTeam;
    }

    public void setStatTeam(StatTeam statTeam) {
        this.statTeam = statTeam;
    }
}
