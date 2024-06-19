package com.stat_tracker.utils;

import com.stat_tracker.dto.game.GameCreatedDto;
import com.stat_tracker.dto.stat_player.StatPlayerDto;
import com.stat_tracker.dto.stat_team.StatTeamDto;
import com.stat_tracker.entity.game.Game;
import com.stat_tracker.entity.player.Player;
import com.stat_tracker.entity.player.StatPlayer;
import com.stat_tracker.entity.score.Score;
import com.stat_tracker.entity.stat.StatLine;
import com.stat_tracker.entity.team.StatTeam;
import com.stat_tracker.entity.team.Team;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StatTeamAndPlayerUtils {
    public static StatTeamDto createStatTeamDto(StatTeam statTeam){
        StatTeamDto statTeamDto = new StatTeamDto();
        statTeamDto.setId(statTeam.getTeam().getId());
        statTeamDto.setName(statTeam.getTeam().getName());
        statTeamDto.setAddress(statTeam.getTeam().getAddress());
        statTeamDto.setLocation(statTeam.getTeam().getLocation());
        statTeamDto.setArena(statTeam.getTeam().getArena());

        List<Integer> scores = statTeam.getScores().stream()
                .sorted(Comparator.comparingInt(Score::getPart))
                .map(Score::getWorth)
                .collect(Collectors.toList());

        statTeamDto.setScores(scores);


        statTeamDto.setStatLine(StatsUtils.createStatLineDto(statTeam.getStatLine()));

        List<StatPlayerDto> players = statTeam.getStatPlayers().stream().map(StatTeamAndPlayerUtils::createStatPlayerDto).toList();
        statTeamDto.setPlayers(players);

        return statTeamDto;
    }

    public static StatPlayerDto createStatPlayerDto(StatPlayer player){
        StatPlayerDto statPlayerDto = new StatPlayerDto();
        statPlayerDto.setId(player.getPlayer().getId());
        statPlayerDto.setFirstName(player.getPlayer().getFirstName());
        statPlayerDto.setLastName(player.getPlayer().getLastName());
        statPlayerDto.setShirtNumber(player.getShirtNumber());
        statPlayerDto.setStartingFive(player.getStartingFive());
        statPlayerDto.setStatLine(StatsUtils.createStatLineDto(player.getStatLine()));

        return statPlayerDto;
    }

    public static StatTeam createStatTeam(Team team, Game game, boolean setHome, GameCreatedDto gameCreatedDto, List<Player> players){
        StatTeam statTeam = new StatTeam();
        statTeam.setTeam(team);
        team.addStatTeam(statTeam);
        statTeam.setStatLine(new StatLine());
        if(setHome){
            statTeam.setHomeGame(game);
        }
        else{
            statTeam.setAwayGame(game);
        }

        statTeam.setStatPlayers(createStatPlayers(gameCreatedDto,setHome,players,statTeam));

        return statTeam;

    }

    public static List<StatPlayer> createStatPlayers(GameCreatedDto gameCreatedDto, boolean setHome, List<Player> players, StatTeam statTeam){
        List<StatPlayer> statPlayers = new ArrayList<>();
        for(var player : players){
            StatPlayer statPlayer = new StatPlayer();
            statPlayer.setStatTeam(statTeam);
            statPlayer.setStatLine(new StatLine());
            statPlayers.add(statPlayer);
            statPlayer.setPlayer(player);
        }

        if(setHome){
            for(var playerDto : gameCreatedDto.getHome().getPlayers()){
                for(var statPlayer  : statPlayers){
                    if(playerDto.getId().equals(statPlayer.getPlayer().getId())){
                        statPlayer.setStartingFive(playerDto.isStartingFive());
                        statPlayer.setShirtNumber(playerDto.getShirtNumber());
                    }
                }
            }
        }
        else{
            for(var playerDto : gameCreatedDto.getAway().getPlayers()){
                for(var statPlayer  : statPlayers){
                    if(playerDto.getId().equals(statPlayer.getPlayer().getId())){
                        statPlayer.setStartingFive(playerDto.isStartingFive());
                        statPlayer.setShirtNumber(playerDto.getShirtNumber());
                    }
                }
            }
        }

        return statPlayers;
    }
    public static StatPlayer createStatPlayer(GameCreatedDto.PlayerDto playerDto, Player player, StatTeam statTeam){
        StatPlayer statPlayer = new StatPlayer();
        statPlayer.setPlayer(player);
        statPlayer.setStatTeam(statTeam);
        statPlayer.setStatLine(new StatLine());
        statPlayer.setShirtNumber(playerDto.getShirtNumber());
        statPlayer.setStartingFive(playerDto.isStartingFive());

        return statPlayer;
    }
}
