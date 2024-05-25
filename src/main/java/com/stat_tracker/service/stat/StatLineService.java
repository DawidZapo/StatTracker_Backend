package com.stat_tracker.service.stat;

import com.stat_tracker.entity.stat.StatLine;
import com.stat_tracker.repository.stat.StatLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatLineService {
    private StatLineRepository statLineRepository;

    @Autowired
    public StatLineService(StatLineRepository statLineRepository) {
        this.statLineRepository = statLineRepository;
    }
    public List<StatLine> findAllStatLines(){
        return statLineRepository.findAll();
    }
}
