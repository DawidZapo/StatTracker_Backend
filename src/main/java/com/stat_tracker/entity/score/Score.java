package com.stat_tracker.entity.score;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.stat_tracker.entity.team.StatTeam;
import jakarta.persistence.*;

@Entity
@Table(name = "score")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "stat_team_id")
    @JsonBackReference
    private StatTeam statTeam;
    @Column(name = "worth")
    private Integer worth;
    @Column(name = "part")
    private Integer part;

    public Long getId() {
        return id;
    }

    public StatTeam getStatTeam() {
        return statTeam;
    }

    public Integer getWorth() {
        return worth;
    }

    public Integer getPart() {
        return part;
    }
}
