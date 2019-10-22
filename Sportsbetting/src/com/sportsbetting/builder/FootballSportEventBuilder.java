package com.sportsbetting.builder;

import java.util.ArrayList;

import com.sportsbetting.domain.Bet;
import com.sportsbetting.domain.FootballSportEvent;
import com.sportsbetting.domain.SportEvent;

public class FootballSportEventBuilder extends SportEventBuilder {
	@Override
	public SportEvent getEvent() {
		SportEvent event = new FootballSportEvent(this.title, this.startDate, this.endDate, this.bets);
		this.bets = new ArrayList<Bet>();
		return event;
	}
}
