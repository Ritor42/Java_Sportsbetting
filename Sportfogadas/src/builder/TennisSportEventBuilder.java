package builder;

import java.util.ArrayList;

import domain.Bet;
import domain.SportEvent;
import domain.TennisSportEvent;

public class TennisSportEventBuilder extends SportEventBuilder {
	@Override
	public SportEvent getEvent() {
		SportEvent event = new TennisSportEvent(this.title, this.startDate, this.endDate, this.bets);
		this.bets = new ArrayList<Bet>();
		return event;
	}
}
