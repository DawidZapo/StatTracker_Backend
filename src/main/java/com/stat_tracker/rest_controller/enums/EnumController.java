package com.stat_tracker.rest_controller.enums;

import com.stat_tracker.entity.plays.enums.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class EnumController {

    @GetMapping("/type/assist")
    public List<AssistType> getAssistTypes(){
        return Arrays.stream(AssistType.values())
                .sorted(Comparator.comparing(Enum::name))
                .toList();
    }

    @GetMapping("/type/contested")
    public List<Contested> getContestedTypes(){
        return Arrays.stream(Contested.values()).toList();
    }

    @GetMapping("/type/foul")
    public List<FoulType> getFoulTypes(){
        return Arrays.stream(FoulType.values())
                .sorted(Comparator.comparing(Enum::name))
                .toList();
    }

    @GetMapping("/type/hand")
    public List<Hand> getHandsType(){
        return Arrays.stream(Hand.values())
                .sorted(Comparator.comparing(Enum::name))
                .toList();
    }

    @GetMapping("/type/shot")
    public List<ShotType> getShotTypes(){
        return Arrays.stream(ShotType.values())
                .sorted(Comparator.comparing(Enum::name))
                .toList();
    }

    @GetMapping("/type/turnover")
    public List<TurnoverType> getTurnoverTypes(){
        return Arrays.stream(TurnoverType.values())
                .sorted(Comparator.comparing(Enum::name))
                .toList();
    }

    @GetMapping("/type/zone")
    public List<Zone> getZonesType(){
        return Arrays.stream(Zone.values())
                .sorted(Comparator.comparing(Enum::name))
                .toList();
    }

    @GetMapping("/type/violation")
    public List<ViolationType> getViolationType(){
        return Arrays.stream(ViolationType.values())
                .sorted(Comparator.comparing(Enum::name))
                .toList();
    }
}
