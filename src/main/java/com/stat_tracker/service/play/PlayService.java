package com.stat_tracker.service.play;

import com.stat_tracker.dto.plays.ShotPlayDto;
import com.stat_tracker.entity.plays.abstract_play.Play;
import com.stat_tracker.repository.play.PlayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayService {
    private PlayRepository playRepository;

    @Autowired
    public PlayService(PlayRepository playRepository) {
        this.playRepository = playRepository;
    }

    public List<Play> findAll(){
        return playRepository.findAll();
    }

    public ShotPlayDto savePlay(ShotPlayDto shotPlayDto){
        System.out.println(shotPlayDto);
        return null;
    }
}
