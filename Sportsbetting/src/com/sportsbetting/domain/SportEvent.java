package com.sportsbetting.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class SportEvent {
	private String title;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private Result result;
	private List<Bet> bets;

	public SportEvent() {
		bets = new ArrayList<Bet>();
	}

	public SportEvent(String title, LocalDateTime startDate, LocalDateTime endDate, List<Bet> bets) {
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.bets = bets;

		for (Bet bet : this.bets)
			bet.setEvent(this);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public List<Bet> getBets() {
		return bets;
	}

	public void addBet(Bet bet) {
		if (this.bets.add(bet)) {
			bet.setEvent(this);
		}
	}

	public void removeBet(Bet bet) {
		if (this.bets.remove(bet)) {
			bet.setEvent(null);
		}
	}
}
