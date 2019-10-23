package builder;


import domain.SportEvent;
import domain.TennisSportEvent;

import java.util.ArrayList;

public class TennisSportEventBuilder extends SportEventBuilder {
	@Override
	public SportEvent getEvent() {
		SportEvent event = new TennisSportEvent(this.title, this.startDate, this.endDate, this.bets);
		this.bets = new ArrayList<>();
		return event;
	}
}
