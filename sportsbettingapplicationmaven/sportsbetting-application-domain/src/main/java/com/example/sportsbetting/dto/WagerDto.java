package com.example.sportsbetting.dto;

import com.example.sportsbetting.domain.Currency;
import com.example.sportsbetting.domain.Wager;

import java.math.BigDecimal;
import java.util.Date;

public class WagerDto {
    private int id;
    private BigDecimal amount;
    private Date timestampCreated;
    private boolean processed;
    private boolean win;
    private OutcomeOddDto odd;
    private Currency currency;

    public WagerDto() {
    }

    public WagerDto(Wager wager) {
        this.id = wager.getId();
        this.amount = wager.getAmount();
        this.timestampCreated = wager.getTimestampCreated();
        this.processed = wager.isProcessed();
        this.win = wager.isWin();
        this.currency = wager.getCurrency();
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public Date getTimestampCreated() {
        return this.timestampCreated;
    }

    public boolean isProcessed() {
        return this.processed;
    }

    public boolean isWin() {
        return this.win;
    }

    public OutcomeOddDto getOutcomeOdd() {
        return this.odd;
    }

    public void setOutcomeOdd(OutcomeOddDto odd) {
        this.odd = odd;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public int getId() {
        return this.id;
    }
}
