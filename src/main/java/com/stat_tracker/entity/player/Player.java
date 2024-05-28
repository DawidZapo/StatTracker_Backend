package com.stat_tracker.entity.player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stat_tracker.entity.team.Team;
import jakarta.persistence.*;

import java.time.LocalDate;

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
    @Column(name = "position")
    @Enumerated(EnumType.STRING)
    private Position position;
    @Column(name = "birth")
    private LocalDate birth;
    @ManyToOne
    @JoinColumn(name = "current_team_id")
    @JsonIgnore
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

    public Position getPosition() {
        return position;
    }

    public LocalDate getBirth() {
        return birth;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", position=" + position +
                ", localDate=" + birth +
                ", currentTeam=" + currentTeam +
                '}';
    }

    public String getFullName(){
        return this.firstName + " " + this.lastName;
    }
}
