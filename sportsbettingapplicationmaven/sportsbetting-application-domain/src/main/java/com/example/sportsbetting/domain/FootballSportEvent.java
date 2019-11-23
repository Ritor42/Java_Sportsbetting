package com.example.sportsbetting.domain;

import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

@Entity
public class FootballSportEvent extends SportEvent {
    public FootballSportEvent() {
    }

    public FootballSportEvent(String title, Date startDate, Date endDate, List<Bet> bets) {
        super(title, startDate, endDate, bets);
    }
}
