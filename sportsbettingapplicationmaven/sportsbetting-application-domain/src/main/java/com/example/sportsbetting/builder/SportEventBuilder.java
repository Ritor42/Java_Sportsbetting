package com.example.sportsbetting.builder;

import com.example.sportsbetting.domain.Bet;
import com.example.sportsbetting.domain.FootballSportEvent;
import com.example.sportsbetting.domain.SportEvent;
import com.example.sportsbetting.domain.TennisSportEvent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class SportEventBuilder {
    private String title;
    private Date startDate;
    private Date endDate;
    private List<Bet> bets = new ArrayList<>();
    private Boolean isTennisEvent;

    public SportEvent getEvent() {
        if (isTennisEvent) {
            return new TennisSportEvent(title, startDate, endDate, bets);
        } else {
            return new FootballSportEvent(title, startDate, endDate, bets);
        }
    }

    public SportEventBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public SportEventBuilder setType(Boolean isTennisEvent) {
        this.isTennisEvent = isTennisEvent;
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

    public SportEventBuilder addBet(Bet bet) {
        this.bets.add(bet);
        return this;
    }

    public SportEventBuilder removeBet(Bet bet) {
        this.bets.remove(bet);
        return this;
    }
}
