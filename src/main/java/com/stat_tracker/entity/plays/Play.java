package com.stat_tracker.entity.plays;

import com.stat_tracker.entity.player.StatPlayer;
import jakarta.persistence.*;

@Entity
@Table(name = "plays")
public class Play{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "stat_player_id")
    private StatPlayer statPlayer;
}
