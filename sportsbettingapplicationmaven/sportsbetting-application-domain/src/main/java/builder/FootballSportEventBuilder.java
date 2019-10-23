package builder;

import domain.FootballSportEvent;
import domain.SportEvent;

import java.util.ArrayList;

public class FootballSportEventBuilder extends SportEventBuilder {
	@Override
	public SportEvent getEvent() {
		SportEvent event = new FootballSportEvent(this.title, this.startDate, this.endDate, this.bets);
		this.bets = new ArrayList<>();
		return event;
	}
}
