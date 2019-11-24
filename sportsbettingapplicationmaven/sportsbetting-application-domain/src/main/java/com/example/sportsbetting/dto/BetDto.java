package com.example.sportsbetting.dto;

import com.example.sportsbetting.domain.Bet;
import com.example.sportsbetting.domain.BetType;
import com.example.sportsbetting.domain.Outcome;

import java.util.ArrayList;
import java.util.List;

public class BetDto {
    private int id;
    private String description;
    private BetType type;
    private SportEventDto event;
    private List<OutcomeDto> outcomes = new ArrayList<>();

    public BetDto() {
    }

    public BetDto(Bet bet) {
        this.id = bet.getId();
        this.description = bet.getDescription();
        this.type = bet.getType();

        for (Outcome outcome : bet.getOutcomes()) {
            OutcomeDto dto = new OutcomeDto(outcome);
            dto.setBet(this);
            this.outcomes.add(dto);
        }
    }

    public int getId() {
        return this.id;
    }

    public String getDescription() {
        return description;
    }

    public BetType getType() {
        return this.type;
    }

    public Iterable<OutcomeDto> getOutcomes() {
        return this.outcomes;
    }

    public SportEventDto getEvent() {
        return this.event;
    }

    public void setEvent(SportEventDto event) {
        this.event = event;
    }
}
