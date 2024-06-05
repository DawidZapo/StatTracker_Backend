package com.stat_tracker.utils;

import com.stat_tracker.entity.player.StatPlayer;
import com.stat_tracker.entity.team.StatTeam;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

public class StatsUtils {
    public static double calculatePercentage(int made, int attempted){
        if(attempted == 0) return 0.0;

        double percentage = (made * 100.0) / attempted;
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat df = new DecimalFormat("#.00", symbols);
        return Double.parseDouble(df.format(percentage));
    }

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

    public static List<StatPlayer> getFilteredStatPlayer(List<StatPlayer> statPlayers, String season){
        if(season.equals("all")){
            return statPlayers;
        }
        else if(season.matches("\\d{4}-\\d{4}")){
            return statPlayers.stream()
                    .filter(statPlayer -> {
                        if (statPlayer.getStatTeam().getHomeGame() != null) {
                            return statPlayer.getStatTeam().getHomeGame().getSeason().equals(season);
                        }
                        if (statPlayer.getStatTeam().getAwayGame() != null) {
                            return statPlayer.getStatTeam().getAwayGame().getSeason().equals(season);
                        }
                        return false;
                    }).toList();
        }
        else{
            throw new RuntimeException("Season does not match format 'rrrr-rrrr' ");
        }
    }

}
