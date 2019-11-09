package builder;

import domain.Bet;
import domain.Result;
import domain.SportEvent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SportEventBuilder {
	protected String title;
	protected Date startDate;
	protected Date endDate;
	protected Result result;
	protected List<Bet> bets = new ArrayList<>();

	public SportEvent getEvent() {
		return new SportEvent(title, startDate, endDate, bets);
	}

	public SportEventBuilder setTitle(String title) {
		this.title = title;
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
