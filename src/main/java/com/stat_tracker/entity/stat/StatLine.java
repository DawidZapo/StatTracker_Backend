package com.stat_tracker.entity.stat;

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
    @OneToOne(mappedBy = "statLine")
    private StatPlayer statPlayer;
    @OneToOne(mappedBy = "statLine")
    private StatTeam statTeam;

    public Long getId() {
        return id;
    }

    public Long getTimeOnCourtInMs() {
        return timeOnCourtInMs;
    }

    public Integer getTwoAttempted() {
        return twoAttempted;
    }

    public Integer getTwoMade() {
        return twoMade;
    }

    public Integer getThreeAttempted() {
        return threeAttempted;
    }

    public Integer getThreeMade() {
        return threeMade;
    }

    public Integer getFreeThrowAttempted() {
        return freeThrowAttempted;
    }

    public Integer getFreeThrowMade() {
        return freeThrowMade;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public Integer getOffRebounds() {
        return offRebounds;
    }

    public Integer getDefRebounds() {
        return defRebounds;
    }

    public Integer getAssists() {
        return assists;
    }

    public Integer getFouls() {
        return fouls;
    }

    public Integer getForcedFouls() {
        return forcedFouls;
    }

    public Integer getTurnovers() {
        return turnovers;
    }

    public Integer getSteals() {
        return steals;
    }

    public Integer getBlocks() {
        return blocks;
    }

    public Integer getBlocksReceived() {
        return blocksReceived;
    }

    public Integer getEval() {
        return eval;
    }

    public Integer getPlusMinus() {
        return plusMinus;
    }

    public StatPlayer getStatPlayer() {
        return statPlayer;
    }

    public StatTeam getStatTeam() {
        return statTeam;
    }
}
