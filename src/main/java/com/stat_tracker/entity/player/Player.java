package com.stat_tracker.entity.player;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.stat_tracker.entity.team.Team;
import jakarta.persistence.*;

@Entity
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "height")
    private Double height;
    @Column(name = "weight")
    private Double weight;
    @ManyToOne
    @JoinColumn(name = "current_team_id")
    @JsonManagedReference
    private Team currentTeam;

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Double getHeight() {
        return height;
    }

    public Double getWeight() {
        return weight;
    }

    public Team getCurrentTeam() {
        return currentTeam;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", currentTeam=" + currentTeam +
                '}';
    }
}
