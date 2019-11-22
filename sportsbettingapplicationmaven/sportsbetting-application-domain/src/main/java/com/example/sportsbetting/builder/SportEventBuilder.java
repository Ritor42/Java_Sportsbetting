package com.example.sportsbetting.builder;

import com.example.sportsbetting.domain.Bet;
import com.example.sportsbetting.domain.Result;
import com.example.sportsbetting.domain.SportEvent;
import com.example.sportsbetting.domain.SportEventType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class SportEventBuilder {
    protected String title;
    protected Date startDate;
    protected Date endDate;
    protected Result result;
    protected List<Bet> bets = new ArrayList<>();
    private SportEventType type;

    public SportEvent getEvent() {
        return new SportEvent(title, startDate, endDate, bets, type);
    }

    public SportEventBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public SportEventBuilder setType(SportEventType type) {
        this.type = type;
        return this;
    }

    public SportEventBuilder setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public SportEventBuilder setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public SportEventBuilder setResult(Result result) {
        this.result = result;
        return this;
    }

    public SportEventBuilder addBet(Bet bet) {
        this.bets.add(bet);
        return this;
    }

    public SportEventBuilder removeBet(Bet bet) {
        this.bets.remove(bet);
        return this;
    }
}
