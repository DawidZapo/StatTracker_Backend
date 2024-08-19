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

    @Transactional
    public AssistDto saveAssist(AssistDto assistDto){
        StatPlayer statPlayer = statPlayerService.findById(assistDto.getStatPlayerId());
        StatPlayer toStatPlayer = (assistDto.getToStatPlayerId() != null) ? statPlayerService.findById(assistDto.getToStatPlayerId()) : null;
        Game game = gameService.findById(assistDto.getGameId());
        Integer orderForPlay = findOrderNumberOrMinusOne(assistDto.getTimeRemaining(), assistDto.getQuarter());

        if(assistDto.getId() != null){
            Assist existingAssist = (Assist) findById(assistDto.getId());
            PlayUtils.updateAssist(existingAssist, assistDto, toStatPlayer);
            return new AssistDto(playRepository.save(existingAssist));

            // logic to be added while editing assist
        }
        else{
            Assist newAssist = PlayUtils.createAssist(assistDto, orderForPlay, game, statPlayer, toStatPlayer);
            Assist assist = playRepository.save(newAssist);

            PlayUtils.updateStatLine(assist, statPlayer.getStatTeam(), statPlayer);

            statLineService.save(statPlayer.getStatLine());
            statLineService.save(statPlayer.getStatTeam().getStatLine());

            return new AssistDto(assist);
        }

    }

    @Transactional
    public BlockDto saveBlock(BlockDto blockDto){
        StatPlayer statPlayer = statPlayerService.findById(blockDto.getStatPlayerId());
        StatPlayer blockedStatPlayer = (blockDto.getBlockedStatPlayerId() != null) ? statPlayerService.findById(blockDto.getBlockedStatPlayerId()) : null;
        Game game = gameService.findById(blockDto.getGameId());
        Integer orderForPlay = findOrderNumberOrMinusOne(blockDto.getTimeRemaining(), blockDto.getQuarter());


        if(blockDto.getId() != null){
            Block existingBlock = (Block) findById(blockDto.getId());
            PlayUtils.updateBlock(existingBlock, blockDto, blockedStatPlayer);
            return new BlockDto(playRepository.save(existingBlock));

            //logic to be added while editing block
        }
        else{
            Block newBlock = PlayUtils.createBlock(blockDto, orderForPlay, game, statPlayer, blockedStatPlayer);
            Block block = playRepository.save(newBlock);

            PlayUtils.updateStatLine(block, statPlayer.getStatTeam(), statPlayer);

            statLineService.save(statPlayer.getStatTeam().getStatLine());
            statLineService.save(statPlayer.getStatLine());

            return new BlockDto(block);
        }
    }

    @Transactional
    public ReboundDto saveRebound(ReboundDto reboundDto){
        StatPlayer statPlayer = statPlayerService.findById(reboundDto.getStatPlayerId());
        Game game = gameService.findById(reboundDto.getGameId());
        Integer orderForPlay = findOrderNumberOrMinusOne(reboundDto.getTimeRemaining(), reboundDto.getQuarter());


        if(reboundDto.getId() != null){
            Rebound existingRebound = (Rebound) findById(reboundDto.getId());
            PlayUtils.updateRebound(existingRebound, reboundDto);
            return new ReboundDto(playRepository.save(existingRebound));

            // logic to be added
        }
        else{
            Rebound newRebound = PlayUtils.createRebound(reboundDto, orderForPlay, game, statPlayer);
            Rebound rebound = playRepository.save(newRebound);

            PlayUtils.updateStatLine(rebound, statPlayer.getStatTeam(), statPlayer);

            statLineService.save(statPlayer.getStatTeam().getStatLine());
            statLineService.save(statPlayer.getStatLine());

            return new ReboundDto(rebound);
        }

    }

    @Transactional
    public ViolationDto saveViolation(ViolationDto violationDto){
        StatPlayer statPlayer = statPlayerService.findById(violationDto.getStatPlayerId());
        Game game = gameService.findById(violationDto.getGameId());
        Integer orderForPlay = findOrderNumberOrMinusOne(violationDto.getTimeRemaining(), violationDto.getQuarter());


        if(violationDto.getId() != null){
            Violation existingViolation = (Violation) findById(violationDto.getId());
            PlayUtils.updateViolation(existingViolation, violationDto);
            return new ViolationDto(playRepository.save(existingViolation));

            // logic to be added while editing violation
        }
        else{
            Violation newViolation = PlayUtils.createViolation(violationDto, orderForPlay, game, statPlayer);
            Violation violation = playRepository.save(newViolation);

            PlayUtils.updateStatLine(violation, statPlayer.getStatTeam(), statPlayer);

            statLineService.save(statPlayer.getStatLine());
            statLineService.save(statPlayer.getStatTeam().getStatLine());

            return new ViolationDto(violation);
        }

    }

    @Transactional
    public FoulDto saveFoul(FoulDto foulDto){
        StatPlayer statPlayer = statPlayerService.findById(foulDto.getStatPlayerId());
        StatPlayer foulOnStatPlayer = (foulDto.getFoulOnStatPlayerId() != null) ? statPlayerService.findById(foulDto.getFoulOnStatPlayerId()) : null;
        Game game = gameService.findById(foulDto.getGameId());
        Integer orderForPlay = findOrderNumberOrMinusOne(foulDto.getTimeRemaining(), foulDto.getQuarter());


        if(foulDto.getId() != null){
            Foul existingFoul = (Foul) findById(foulDto.getId());
            PlayUtils.updateFoul(existingFoul, foulDto, foulOnStatPlayer);
            return new FoulDto(playRepository.save(existingFoul));

            // logic to be added while editing foul
        }
        else{
            Foul newFoul = PlayUtils.createFoul(foulDto, orderForPlay, game, statPlayer, foulOnStatPlayer);
            Foul foul = playRepository.save(newFoul);

            PlayUtils.updateStatLine(foul, statPlayer.getStatTeam(), statPlayer);

            statLineService.save(statPlayer.getStatTeam().getStatLine());
            statLineService.save(statPlayer.getStatLine());

            return new FoulDto(foul);
        }

    }

    @Transactional
    public StealDto saveSteal(StealDto stealDto){
        StatPlayer statPlayer = statPlayerService.findById(stealDto.getStatPlayerId());
        StatPlayer turnoverForStatPlayer = (stealDto.getTurnoverForStatPlayerId() != null) ? statPlayerService.findById(stealDto.getTurnoverForStatPlayerId()) : null;
        Game game = gameService.findById(stealDto.getGameId());
        Integer orderForPlay = findOrderNumberOrMinusOne(stealDto.getTimeRemaining(), stealDto.getQuarter());

        if(stealDto.getId() != null){
            Steal existingSteal = (Steal) findById(stealDto.getId());
            PlayUtils.updateSteal(existingSteal, stealDto, turnoverForStatPlayer);
            return new StealDto(playRepository.save(existingSteal));

            // logic to be added while editing steal
        }
        else{
            Steal newSteal = PlayUtils.createSteal(stealDto, orderForPlay, game, statPlayer, turnoverForStatPlayer);
            Steal steal = playRepository.save(newSteal);

            PlayUtils.updateStatLine(steal, statPlayer.getStatTeam(), statPlayer);

            statLineService.save(statPlayer.getStatTeam().getStatLine());
            statLineService.save(statPlayer.getStatLine());

            return new StealDto(steal);
        }

    }

    @Transactional
    public TurnoverDto saveTurnover(TurnoverDto turnoverDto){
        StatPlayer statPlayer = statPlayerService.findById(turnoverDto.getStatPlayerId());
        StatPlayer stealForStatPlayer = (turnoverDto.getStealForStatPlayerId() != null) ? statPlayerService.findById(turnoverDto.getStealForStatPlayerId()) : null;
        Game game = gameService.findById(turnoverDto.getGameId());
        Integer orderForPlay = findOrderNumberOrMinusOne(turnoverDto.getTimeRemaining(), turnoverDto.getQuarter());


        if(turnoverDto.getId() != null){
            Turnover existingTurnover = (Turnover) findById(turnoverDto.getId());
            PlayUtils.updateTurnover(existingTurnover, turnoverDto, stealForStatPlayer);
            return new TurnoverDto(playRepository.save(existingTurnover));

            // logic to be added while editing turnover
        }
        else{
            Turnover newTurnover = PlayUtils.createTurnover(turnoverDto, orderForPlay, game, statPlayer, stealForStatPlayer);
            Turnover turnover = playRepository.save(newTurnover);

            PlayUtils.updateStatLine(turnover, statPlayer.getStatTeam(), statPlayer);

            statLineService.save(statPlayer.getStatTeam().getStatLine());
            statLineService.save(statPlayer.getStatLine());

            return new TurnoverDto(turnover);
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
