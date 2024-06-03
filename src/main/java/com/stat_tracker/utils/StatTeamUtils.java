package com.stat_tracker.utils;

import com.stat_tracker.entity.team.StatTeam;

import java.util.List;

public class StatTeamUtils {
    public static List<StatTeam> getFilteredStatTeams(List<StatTeam> statTeams, String season){
        if(season.equals("all")){
            return statTeams;
        }
        else if(season.matches("\\d{4}-\\d{4}")){
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
        else{
            throw new RuntimeException("Season does not match format 'rrrr-rrrr' ");
        }
    }
}
