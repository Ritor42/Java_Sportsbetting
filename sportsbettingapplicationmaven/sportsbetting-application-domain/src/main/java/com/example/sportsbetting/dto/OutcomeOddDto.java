package com.example.sportsbetting.dto;

import com.example.sportsbetting.domain.OutcomeOdd;

import java.math.BigDecimal;
import java.util.Date;

public class OutcomeOddDto {
    private int id;
    private BigDecimal value;
    private Date validFrom;
    private Date validUntil;
    private OutcomeDto outcome;

    public OutcomeOddDto() {
    }

    public OutcomeOddDto(OutcomeOdd odd) {
        this.id = odd.getId();
        this.value = odd.getValue();
        this.validFrom = odd.getValidFrom();
        this.validUntil = odd.getValidUntil();
    }

    public BigDecimal getValue() {
        return this.value;
    }

    public Date getValidFrom() {
        return this.validFrom;
    }

    public Date getValidUntil() {
        return this.validUntil;
    }

    public int getId() {
        return this.id;
    }

    public OutcomeDto getOutcome() {
        return this.outcome;
    }

    public void setOutcome(OutcomeDto outcome) {
        this.outcome = outcome;
    }
}
