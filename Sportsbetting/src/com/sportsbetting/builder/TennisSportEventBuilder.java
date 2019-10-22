package com.sportsbetting.builder;

import java.util.ArrayList;

import com.sportsbetting.domain.Bet;
import com.sportsbetting.domain.SportEvent;
import com.sportsbetting.domain.TennisSportEvent;

public class TennisSportEventBuilder extends SportEventBuilder {
	@Override
	public SportEvent getEvent() {
		SportEvent event = new TennisSportEvent(this.title, this.startDate, this.endDate, this.bets);
		this.bets = new ArrayList<Bet>();
		return event;
	}
}
