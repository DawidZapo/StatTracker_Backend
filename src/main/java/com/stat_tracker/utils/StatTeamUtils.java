package com.stat_tracker.utils;

import com.stat_tracker.entity.team.StatTeam;

import java.util.List;

public class StatTeamUtils {
    public static List<StatTeam> getFilteredStatTeams(List<StatTeam> statTeams, String season){

        return statTeams.stream()
                .filter(statTeam -> {
                    if (statTeam.getHomeGame() != null) {
                        return statTeam.getHomeGame().getSeason().equals(season);
                    }
                    if (statTeam.getAwayGame() != null) {
                        return statTeam.getAwayGame().getSeason().equals(season);
                    }
                    return false;
                }).toList();
    }
}
