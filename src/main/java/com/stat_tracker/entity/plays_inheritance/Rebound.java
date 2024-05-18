package com.stat_tracker.entity.plays_inheritance;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "rebound")
public class Rebound extends Play{
    @Column(name = "is_offensive")
    private Boolean isOffensive;

    public Boolean getOffensive() {
        return isOffensive;
    }

    public void setOffensive(Boolean offensive) {
        isOffensive = offensive;
    }
}
