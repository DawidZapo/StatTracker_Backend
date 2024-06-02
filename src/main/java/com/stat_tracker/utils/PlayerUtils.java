package com.stat_tracker.utils;

import com.stat_tracker.dto.player.PlayerDto;
import com.stat_tracker.dto.player.PlayerWithTeamDto;
import com.stat_tracker.entity.player.Player;

public class PlayerUtils {
    public static Player playerDtoToPlayer(PlayerDto playerDto){
        Player player = new Player();
        player.setId(playerDto.getId());
        player.setFirstName(playerDto.getFirstName());
        player.setLastName(playerDto.getLastName());
        player.setHeight(playerDto.getHeight());
        player.setWeight(playerDto.getWeight());
        player.setPosition(playerDto.getPosition());
        player.setBirth(playerDto.getBirth());

        return player;
    }

    public static PlayerWithTeamDto createPlayerWithTeamDto(Player player){
        PlayerWithTeamDto playerWithTeamDto = new PlayerWithTeamDto();
        playerWithTeamDto.setId(player.getId());
        playerWithTeamDto.setFirstName(player.getFirstName());
        playerWithTeamDto.setLastName(player.getLastName());
        playerWithTeamDto.setHeight(player.getHeight());
        playerWithTeamDto.setWeight(player.getWeight());
        playerWithTeamDto.setBirth(player.getBirth());
        playerWithTeamDto.setPosition(player.getPosition());
        playerWithTeamDto.setTeamName(player.getCurrentTeam().getName());

        return playerWithTeamDto;
    }
}
