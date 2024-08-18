package com.stat_tracker.service.play;

import com.stat_tracker.dto.plays.*;
import com.stat_tracker.entity.game.Game;
import com.stat_tracker.entity.player.StatPlayer;
import com.stat_tracker.entity.plays.*;
import com.stat_tracker.entity.plays.abstract_play.Play;
import com.stat_tracker.repository.play.PlayRepository;
import com.stat_tracker.service.game.GameService;
import com.stat_tracker.service.player.StatPlayerService;
import com.stat_tracker.service.stat.StatLineService;
import com.stat_tracker.utils.PlayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PlayService {
    private PlayRepository playRepository;
    private GameService gameService;
    private StatPlayerService statPlayerService;
    private StatLineService statLineService;

    @Autowired
    public PlayService(PlayRepository playRepository, GameService gameService, StatPlayerService statPlayerService, StatLineService statLineService) {
        this.playRepository = playRepository;
        this.gameService = gameService;
        this.statPlayerService = statPlayerService;
        this.statLineService = statLineService;
    }

    public List<Play> findAll(){
        return playRepository.findAll();
    }

    public Play findById(Long id){
        return playRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Play not found id: " + id));
    }

    private Integer findOrderNumberOrMinusOne(Long timeRemaining, Integer quarter){
        List<Play> existingPlaysWithTheSameTimeRemaining = playRepository.findAllByTimeRemaining(timeRemaining, quarter);
        Optional<Integer> maxOrderNumber = existingPlaysWithTheSameTimeRemaining.stream()
                .map(Play::getOrder)
                .filter(Objects::nonNull)
                .max(Comparator.naturalOrder());
        return maxOrderNumber.map(integer -> integer + 1).orElse(-1);
    }

    @Transactional
    public ShotPlayDto savePlay(ShotPlayDto shotPlayDto){
        StatPlayer statPlayer = statPlayerService.findById(shotPlayDto.getStatPlayerId());
        Game game = gameService.findById(shotPlayDto.getGameId());
        Integer orderForPlay = findOrderNumberOrMinusOne(shotPlayDto.getTimeRemaining(), shotPlayDto.getQuarter());

        if(shotPlayDto.getId() != null){
            ShotPlay existingShotPlay = (ShotPlay) findById(shotPlayDto.getId());
            PlayUtils.updateShotPlay(existingShotPlay, shotPlayDto);
            return new ShotPlayDto(playRepository.save(existingShotPlay));

            // logic to be added while editing shotplay
        }
        else{
            ShotPlay newShotPlay = PlayUtils.createShotPlay(shotPlayDto, orderForPlay, game, statPlayer);

            ShotPlay shotPlay = playRepository.save(newShotPlay);
            PlayUtils.updateStatLine(shotPlay, statPlayer.getStatTeam(), statPlayer);
            statLineService.save(statPlayer.getStatTeam().getStatLine());
            statLineService.save(statPlayer.getStatLine());

            return new ShotPlayDto(shotPlay);
        }
    }

    public AssistDto saveAssist(AssistDto assistDto){
        StatPlayer statPlayer = statPlayerService.findById(assistDto.getStatPlayerId());
        StatPlayer toStatPlayer = (assistDto.getToStatPlayerId() != null) ? statPlayerService.findById(assistDto.getToStatPlayerId()) : null;
        Game game = gameService.findById(assistDto.getGameId());
        Integer orderForPlay = findOrderNumberOrMinusOne(assistDto.getTimeRemaining(), assistDto.getQuarter());

        if(assistDto.getId() != null){
            Assist existingAssist = (Assist) findById(assistDto.getId());
            PlayUtils.updateAssist(existingAssist, assistDto, toStatPlayer);
            return new AssistDto(playRepository.save(existingAssist));
        }
        else{
            Assist assist = PlayUtils.createAssist(assistDto, orderForPlay, game, statPlayer, toStatPlayer);
            return new AssistDto(playRepository.save(assist));
        }

    }

    public BlockDto saveBlock(BlockDto blockDto){
        StatPlayer statPlayer = statPlayerService.findById(blockDto.getStatPlayerId());
        StatPlayer blockedStatPlayer = (blockDto.getBlockedStatPlayerId() != null) ? statPlayerService.findById(blockDto.getBlockedStatPlayerId()) : null;
        Game game = gameService.findById(blockDto.getGameId());
        Integer orderForPlay = findOrderNumberOrMinusOne(blockDto.getTimeRemaining(), blockDto.getQuarter());


        if(blockDto.getId() != null){
            Block existingBlock = (Block) findById(blockDto.getId());
            PlayUtils.updateBlock(existingBlock, blockDto, blockedStatPlayer);
            return new BlockDto(playRepository.save(existingBlock));
        }
        else{
            Block block = PlayUtils.createBlock(blockDto, orderForPlay, game, statPlayer, blockedStatPlayer);
            return new BlockDto(playRepository.save(block));
        }
    }

    public ReboundDto saveRebound(ReboundDto reboundDto){
        StatPlayer statPlayer = statPlayerService.findById(reboundDto.getStatPlayerId());
        Game game = gameService.findById(reboundDto.getGameId());
        Integer orderForPlay = findOrderNumberOrMinusOne(reboundDto.getTimeRemaining(), reboundDto.getQuarter());


        if(reboundDto.getId() != null){
            Rebound existingRebound = (Rebound) findById(reboundDto.getId());
            PlayUtils.updateRebound(existingRebound, reboundDto);
            return new ReboundDto(playRepository.save(existingRebound));
        }
        else{
            Rebound rebound = PlayUtils.createRebound(reboundDto, orderForPlay, game, statPlayer);
            return new ReboundDto(playRepository.save(rebound));
        }

    }

    public ViolationDto saveViolation(ViolationDto violationDto){
        StatPlayer statPlayer = statPlayerService.findById(violationDto.getStatPlayerId());
        Game game = gameService.findById(violationDto.getGameId());
        Integer orderForPlay = findOrderNumberOrMinusOne(violationDto.getTimeRemaining(), violationDto.getQuarter());


        if(violationDto.getId() != null){
            Violation existingViolation = (Violation) findById(violationDto.getId());
            PlayUtils.updateViolation(existingViolation, violationDto);
            return new ViolationDto(playRepository.save(existingViolation));
        }
        else{
            Violation violation = PlayUtils.createViolation(violationDto, orderForPlay, game, statPlayer);
            return new ViolationDto(playRepository.save(violation));
        }

    }

    public FoulDto saveFoul(FoulDto foulDto){
        StatPlayer statPlayer = statPlayerService.findById(foulDto.getStatPlayerId());
        StatPlayer foulOnStatPlayer = (foulDto.getFoulOnStatPlayerId() != null) ? statPlayerService.findById(foulDto.getFoulOnStatPlayerId()) : null;
        Game game = gameService.findById(foulDto.getGameId());
        Integer orderForPlay = findOrderNumberOrMinusOne(foulDto.getTimeRemaining(), foulDto.getQuarter());


        if(foulDto.getId() != null){
            Foul existingFoul = (Foul) findById(foulDto.getId());
            PlayUtils.updateFoul(existingFoul, foulDto, foulOnStatPlayer);
            return new FoulDto(playRepository.save(existingFoul));
        }
        else{
            Foul foul = PlayUtils.createFoul(foulDto, orderForPlay, game, statPlayer, foulOnStatPlayer);
            return new FoulDto(playRepository.save(foul));
        }

//        Foul foul = PlayUtils.createFoul(foulDto, game, statPlayer, foulOnStatPlayer);
//        return new FoulDto(playRepository.save(foul));
    }

    public StealDto saveSteal(StealDto stealDto){
        StatPlayer statPlayer = statPlayerService.findById(stealDto.getStatPlayerId());
        StatPlayer turnoverForStatPlayer = (stealDto.getTurnoverForStatPlayerId() != null) ? statPlayerService.findById(stealDto.getTurnoverForStatPlayerId()) : null;
        Game game = gameService.findById(stealDto.getGameId());
        Integer orderForPlay = findOrderNumberOrMinusOne(stealDto.getTimeRemaining(), stealDto.getQuarter());

        if(stealDto.getId() != null){
            Steal existingSteal = (Steal) findById(stealDto.getId());
            PlayUtils.updateSteal(existingSteal, stealDto, turnoverForStatPlayer);
            return new StealDto(playRepository.save(existingSteal));
        }
        else{
            Steal steal = PlayUtils.createSteal(stealDto, orderForPlay, game, statPlayer, turnoverForStatPlayer);
            return new StealDto(playRepository.save(steal));
        }

    }

    public TurnoverDto saveTurnover(TurnoverDto turnoverDto){
        StatPlayer statPlayer = statPlayerService.findById(turnoverDto.getStatPlayerId());
        StatPlayer stealForStatPlayer = (turnoverDto.getStealForStatPlayerId() != null) ? statPlayerService.findById(turnoverDto.getStealForStatPlayerId()) : null;
        Game game = gameService.findById(turnoverDto.getGameId());
        Integer orderForPlay = findOrderNumberOrMinusOne(turnoverDto.getTimeRemaining(), turnoverDto.getQuarter());


        if(turnoverDto.getId() != null){
            Turnover existingTurnover = (Turnover) findById(turnoverDto.getId());
            PlayUtils.updateTurnover(existingTurnover, turnoverDto, stealForStatPlayer);
            return new TurnoverDto(playRepository.save(existingTurnover));
        }
        else{
            Turnover turnover = PlayUtils.createTurnover(turnoverDto, orderForPlay, game, statPlayer, stealForStatPlayer);
            return new TurnoverDto(playRepository.save(turnover));
        }

    }


    public ShotPlayDto createShotPlayDtoWithoutSaving(ShotPlayDto shotPlayDto){
        StatPlayer statPlayer = statPlayerService.findById(shotPlayDto.getStatPlayerId());
        shotPlayDto.setFirstName(statPlayer.getPlayer().getFirstName());
        shotPlayDto.setLastName(statPlayer.getPlayer().getLastName());
        shotPlayDto.setPlayType(ShotPlayDto.class.getSimpleName());

        return shotPlayDto;
    }
}
