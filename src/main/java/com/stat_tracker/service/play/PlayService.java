package com.stat_tracker.service.play;

import com.stat_tracker.dto.plays.*;
import com.stat_tracker.entity.game.Game;
import com.stat_tracker.entity.player.StatPlayer;
import com.stat_tracker.entity.plays.*;
import com.stat_tracker.entity.plays.abstract_play.Play;
import com.stat_tracker.repository.play.PlayRepository;
import com.stat_tracker.service.game.GameService;
import com.stat_tracker.service.player.StatPlayerService;
import com.stat_tracker.utils.PlayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlayService {
    private PlayRepository playRepository;
    private GameService gameService;
    private StatPlayerService statPlayerService;

    @Autowired
    public PlayService(PlayRepository playRepository, GameService gameService, StatPlayerService statPlayerService) {
        this.playRepository = playRepository;
        this.gameService = gameService;
        this.statPlayerService = statPlayerService;
    }

    public List<Play> findAll(){
        return playRepository.findAll();
    }

    public Play findById(Long id){
        return playRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Play not found id: " + id));
    }

    @Transactional
    public ShotPlayDto savePlay(ShotPlayDto shotPlayDto){
        StatPlayer statPlayer = statPlayerService.findById(shotPlayDto.getStatPlayerId());
        Game game = gameService.findById(shotPlayDto.getGameId());


        if(shotPlayDto.getId() != null){
            ShotPlay existingShotPlay = (ShotPlay) findById(shotPlayDto.getId());
            PlayUtils.updateShotPlay(existingShotPlay, shotPlayDto);
            return new ShotPlayDto(playRepository.save(existingShotPlay));
        }
        else{
            ShotPlay shotPlay = PlayUtils.createShotPlay(shotPlayDto, game, statPlayer);
            return new ShotPlayDto(playRepository.save(shotPlay));
        }
    }

    public AssistDto saveAssist(AssistDto assistDto){
        StatPlayer statPlayer = statPlayerService.findById(assistDto.getStatPlayerId());
        StatPlayer toStatPlayer = (assistDto.getToStatPlayerId() != null) ? statPlayerService.findById(assistDto.getToStatPlayerId()) : null;
        Game game = gameService.findById(assistDto.getGameId());


        if(assistDto.getId() != null){
            Assist existingAssist = (Assist) findById(assistDto.getId());
            PlayUtils.updateAssist(existingAssist, assistDto, toStatPlayer);
            return new AssistDto(playRepository.save(existingAssist));
        }
        else{
            Assist assist = PlayUtils.createAssist(assistDto, game, statPlayer, toStatPlayer);
            return new AssistDto(playRepository.save(assist));
        }

    }

    public BlockDto saveBlock(BlockDto blockDto){
        StatPlayer statPlayer = statPlayerService.findById(blockDto.getStatPlayerId());
        StatPlayer blockedStatPlayer = (blockDto.getBlockedStatPlayerId() != null) ? statPlayerService.findById(blockDto.getBlockedStatPlayerId()) : null;
        Game game = gameService.findById(blockDto.getGameId());

        if(blockDto.getId() != null){
            Block existingBlock = (Block) findById(blockDto.getId());
            PlayUtils.updateBlock(existingBlock, blockDto, blockedStatPlayer);
            return new BlockDto(playRepository.save(existingBlock));
        }
        else{
            Block block = PlayUtils.createBlock(blockDto, game, statPlayer, blockedStatPlayer);
            return new BlockDto(playRepository.save(block));
        }
    }

    public ReboundDto saveRebound(ReboundDto reboundDto){
        StatPlayer statPlayer = statPlayerService.findById(reboundDto.getStatPlayerId());

        Game game = gameService.findById(reboundDto.getGameId());

        Rebound rebound = PlayUtils.createRebound(reboundDto, game, statPlayer);

        return new ReboundDto(playRepository.save(rebound));
    }

    public FoulDto saveFoul(FoulDto foulDto){
        StatPlayer statPlayer = statPlayerService.findById(foulDto.getStatPlayerId());
        StatPlayer foulOnStatPlayer = (foulDto.getFoulOnStatPlayerId() != null) ? statPlayerService.findById(foulDto.getFoulOnStatPlayerId()) : null;
        Game game = gameService.findById(foulDto.getGameId());

        if(foulDto.getId() != null){
            Foul existingFoul = (Foul) findById(foulDto.getId());
            PlayUtils.updateFoul(existingFoul, foulDto, foulOnStatPlayer);
            return new FoulDto(playRepository.save(existingFoul));
        }
        else{
            Foul foul = PlayUtils.createFoul(foulDto, game, statPlayer, foulOnStatPlayer);
            return new FoulDto(playRepository.save(foul));
        }

//        Foul foul = PlayUtils.createFoul(foulDto, game, statPlayer, foulOnStatPlayer);
//        return new FoulDto(playRepository.save(foul));
    }

    public StealDto saveSteal(StealDto stealDto){
        StatPlayer statPlayer = statPlayerService.findById(stealDto.getStatPlayerId());
        StatPlayer turnoverForStatPlayer = (stealDto.getTurnoverForStatPlayerId() != null) ? statPlayerService.findById(stealDto.getTurnoverForStatPlayerId()) : null;

        Game game = gameService.findById(stealDto.getGameId());

        Steal steal = PlayUtils.createSteal(stealDto, game, statPlayer, turnoverForStatPlayer);

        return new StealDto(playRepository.save(steal));
    }

    public TurnoverDto saveTurnover(TurnoverDto turnoverDto){
        StatPlayer statPlayer = statPlayerService.findById(turnoverDto.getStatPlayerId());
        StatPlayer stealForStatPlayer = (turnoverDto.getStealForStatPlayerId() != null) ? statPlayerService.findById(turnoverDto.getStealForStatPlayerId()) : null;

        Game game = gameService.findById(turnoverDto.getGameId());

        Turnover turnover = PlayUtils.createTurnover(turnoverDto, game, statPlayer, stealForStatPlayer);

        return new TurnoverDto(playRepository.save(turnover));
    }

    public ShotPlayDto createShotPlayDtoWithoutSaving(ShotPlayDto shotPlayDto){
        StatPlayer statPlayer = statPlayerService.findById(shotPlayDto.getStatPlayerId());
        shotPlayDto.setFirstName(statPlayer.getPlayer().getFirstName());
        shotPlayDto.setLastName(statPlayer.getPlayer().getLastName());
        shotPlayDto.setPlayType(ShotPlayDto.class.getSimpleName());

        return shotPlayDto;
    }
}
