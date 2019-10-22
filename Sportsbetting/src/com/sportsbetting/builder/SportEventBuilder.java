package com.sportsbetting.builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.sportsbetting.domain.Bet;
import com.sportsbetting.domain.Result;
import com.sportsbetting.domain.SportEvent;

public abstract class SportEventBuilder {
	protected String title;
	protected LocalDateTime startDate;
	protected LocalDateTime endDate;	
	protected Result result;
	protected List<Bet> bets;
	
	public SportEventBuilder() {
		this.bets = new ArrayList<Bet>();
	}
	
	public abstract SportEvent getEvent();
	
	public SportEventBuilder setTitle(String title) {
		this.title = title;
		return this;
	}
	
	public SportEventBuilder setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
		return this;
	}
	
	public SportEventBuilder setEndDate(LocalDateTime endDate) {
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
