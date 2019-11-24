package com.example.sportsbetting.dto;

import com.example.sportsbetting.domain.Outcome;
import com.example.sportsbetting.domain.OutcomeOdd;

import java.util.ArrayList;
import java.util.List;

public class OutcomeDto {
    private int id;
    private String description;
    private BetDto bet;
    private List<OutcomeOddDto> outcomeOdds = new ArrayList<>();

    public OutcomeDto() {
    }

    public OutcomeDto(Outcome outcome) {
        this.id = outcome.getId();
        this.description = outcome.getDescription();

        for (OutcomeOdd odd : outcome.getOutcomeOdds()) {
            OutcomeOddDto dto = new OutcomeOddDto(odd);
            dto.setOutcome(this);
            this.outcomeOdds.add(dto);
        }
    }

    public int getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    public Iterable<OutcomeOddDto> getOutcomeOdds() {
        return this.outcomeOdds;
    }

    public BetDto getBet() {
        return this.bet;
    }

    public void setBet(BetDto bet) {
        this.bet = bet;
    }
}
