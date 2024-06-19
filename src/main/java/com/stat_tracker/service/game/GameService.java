package com.stat_tracker.service.game;

import com.stat_tracker.dto.game.GameCreatedDto;
import com.stat_tracker.dto.game.GameWithPlaysDto;
import com.stat_tracker.dto.game.GameWithStatTeamsDto;
import com.stat_tracker.dto.game.GameWithTeamNamesDto;
import com.stat_tracker.entity.game.Game;
import com.stat_tracker.entity.player.Player;
import com.stat_tracker.entity.team.Team;
import com.stat_tracker.repository.game.GameRepository;
import com.stat_tracker.service.player.PlayerService;
import com.stat_tracker.service.team.TeamService;
import com.stat_tracker.utils.GameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameService {
    private GameRepository gameRepository;
    private TeamService teamService;
    private PlayerService playerService;

    @Autowired
    public GameService(GameRepository gameRepository, TeamService teamService, PlayerService playerService) {
        this.gameRepository = gameRepository;
        this.teamService = teamService;
        this.playerService = playerService;
    }
    public Game findGame(Long id){
        return gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Game not found id: " + id));
    }
    public GameWithPlaysDto getGameWithPlays(Long id){
        Optional<Game> game = gameRepository.findById(id);
        if(!game.isPresent()){
            throw new RuntimeException("Game not found - id: " + id);
        }
        return new GameWithPlaysDto(game.get().getId(), game.get().getPlays());
    }
    public GameWithStatTeamsDto getGameWithStatsTeam(Long id){
        Game game = findGame(id);
        return GameUtils.createGameWithStatTeams(game);
    }
    public List<GameWithTeamNamesDto> getAllGamesWithTeamNamesDto(){
        List<Game> games = gameRepository.findAll();
        return games.stream().map(game -> GameUtils.createGameWithTeamNamesDto(game)).collect(Collectors.toList());
    }

    public void createGame(GameCreatedDto gameCreatedDto){
        Team home = teamService.findTeam(gameCreatedDto.getHome().getId());
        Team away = teamService.findTeam(gameCreatedDto.getAway().getId());

        List<Long> homePlayerIds = gameCreatedDto.getHome().getPlayers().stream()
                .map(GameCreatedDto.PlayerDto::getId)
                .toList();
        List<Player> homePlayers = playerService.findPlayerWithIds(homePlayerIds);

        List<Long> awayPlayerIds = gameCreatedDto.getAway().getPlayers().stream()
                .map(GameCreatedDto.PlayerDto::getId)
                .toList();
        List<Player> awayPlayers = playerService.findPlayerWithIds(awayPlayerIds);

        Game game = GameUtils.createGame(gameCreatedDto,home, away, homePlayers, awayPlayers);

        System.out.println(game);
    }

}
