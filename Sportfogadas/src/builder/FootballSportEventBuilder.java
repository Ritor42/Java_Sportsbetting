package builder;

import java.util.ArrayList;

import domain.Bet;
import domain.FootballSportEvent;
import domain.SportEvent;

public class FootballSportEventBuilder extends SportEventBuilder {
	@Override
	public SportEvent getEvent() {
		SportEvent event = new FootballSportEvent(this.title, this.startDate, this.endDate, this.bets);
		this.bets = new ArrayList<Bet>();
		return event;
	}
}
