package com.example.sportsbetting.dto;

import com.example.sportsbetting.domain.Bet;
import com.example.sportsbetting.domain.SportEvent;
import com.example.sportsbetting.domain.TennisSportEvent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SportEventDto {
    private int id;
    private String title;
    private Date startDate;
    private Date endDate;
    private Boolean isTennisEvent;
    private List<BetDto> bets = new ArrayList<>();

    public SportEventDto() {
    }

    public SportEventDto(SportEvent event) {
        this.id = event.getId();
        this.title = event.getTitle();
        this.startDate = event.getStartDate();
        this.endDate = event.getEndDate();
        this.isTennisEvent = event instanceof TennisSportEvent;

        for (Bet bet : event.getBets()) {
            BetDto dto = new BetDto(bet);
            dto.setEvent(this);
            this.bets.add(dto);
        }
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public Boolean isTennisEvent() {
        return this.isTennisEvent;
    }

    public Iterable<BetDto> getBets() {
        return this.bets;
    }
}
