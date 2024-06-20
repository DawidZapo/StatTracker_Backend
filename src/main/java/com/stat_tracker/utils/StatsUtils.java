package com.stat_tracker.utils;

import com.stat_tracker.dto.stat.StatLineDto;
import com.stat_tracker.dto.team.helper.StatsTotals;
import com.stat_tracker.entity.player.StatPlayer;
import com.stat_tracker.entity.stat.StatLine;
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

    public static List<StatPlayer> getFilteredStatPlayers(List<StatPlayer> statPlayers, String season){
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

    public static void updateStatsTotals(StatsTotals item, StatLine stats) {
        item.setTotalPoints(item.getTotalPoints() + stats.getTotalPoints());
        item.setTwoPointShotsAttempted(item.getTwoPointShotsAttempted() + stats.getTwoAttempted());
        item.setTwoPointShotsMade(item.getTwoPointShotsMade() + stats.getTwoMade());
        item.setThreePointShotsAttempted(item.getThreePointShotsAttempted() + stats.getThreeAttempted());
        item.setThreePointShotsMade(item.getThreePointShotsMade() + stats.getThreeMade());
        item.setFreeThrowsAttempted(item.getFreeThrowsAttempted() + stats.getFreeThrowAttempted());
        item.setFreeThrowsMade(item.getFreeThrowsMade() + stats.getFreeThrowMade());
        item.setOffRebounds(item.getOffRebounds() + stats.getOffRebounds());
        item.setDefRebounds(item.getDefRebounds() + stats.getDefRebounds());
        item.setAssists(item.getAssists() + stats.getAssists());
        item.setFouls(item.getFouls() + stats.getFouls());
        item.setForcedFouls(item.getForcedFouls() + stats.getForcedFouls());
        item.setTurnOvers(item.getTurnOvers() + stats.getTurnovers());
        item.setSteals(item.getSteals() + stats.getSteals());
        item.setBlocks(item.getBlocks() + stats.getBlocks());
        item.setBlocksReceived(item.getBlocksReceived() + stats.getBlocksReceived());
        item.setEvaluation(item.getEvaluation() + stats.getEvaluation());
        item.setPossessions(item.getPossessions() + stats.getPossessions());
        item.setNumberOfGames(item.getNumberOfGames() + 1);
    }

    public static String getSeasonFromStatPlayer(StatPlayer statPlayer){
        String season;
        if(statPlayer.getStatTeam().getHomeGame() != null){
            season = statPlayer.getStatTeam().getHomeGame().getSeason();
        }
        else if(statPlayer.getStatTeam().getAwayGame() != null){
            season = statPlayer.getStatTeam().getAwayGame().getSeason();
        }
        else{
            throw new RuntimeException("Stat team does not belong to either home or away game");
        }
        return season;
    }

    public static StatLineDto createStatLineDto(StatLine stats){
        StatLineDto statLineDto = new StatLineDto();
        statLineDto.setTimeOnCourtInMs(stats.getTimeOnCourtInMs());
        statLineDto.setTwoAttempted(stats.getTwoAttempted());
        statLineDto.setTwoMade(stats.getTwoMade());
        statLineDto.setThreeAttempted(stats.getThreeAttempted());
        statLineDto.setThreeMade(stats.getThreeMade());
        statLineDto.setFreeThrowAttempted(stats.getFreeThrowAttempted());
        statLineDto.setFreeThrowMade(stats.getFreeThrowMade());
        statLineDto.setTotalPoints(stats.getTotalPoints());
        statLineDto.setOffRebounds(stats.getOffRebounds());
        statLineDto.setDefRebounds(stats.getDefRebounds());
        statLineDto.setAssists(stats.getAssists());
        statLineDto.setFouls(stats.getFouls());
        statLineDto.setForcedFouls(stats.getForcedFouls());
        statLineDto.setTurnovers(stats.getTurnovers());
        statLineDto.setBlocks(stats.getBlocks());
        statLineDto.setBlocksReceived(stats.getBlocksReceived());
        statLineDto.setSteals(stats.getSteals());
        statLineDto.setEvaluation(stats.getEvaluation());
        statLineDto.setPlusMinus(stats.getPlusMinus());
        statLineDto.setPossessions(stats.getPossessions());

        return statLineDto;
    }

}
