package domain;

import java.util.ArrayList;
import java.util.List;

public class Bet {
	private String description;
	private SportEvent event;
	private BetType type;
	private List<Outcome> outcomes;

	public Bet() {
		this.outcomes = new ArrayList<Outcome>();
	}

	public Bet(String description, BetType type, List<Outcome> outcomes) {
		this.description = description;
		this.type = type;
		this.outcomes = outcomes;

		for (Outcome outcome : this.outcomes)
			outcome.setBet(this);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public SportEvent getEvent() {
		return event;
	}

	public void setEvent(SportEvent event) {
		this.event = event;
	}

	public BetType getType() {
		return type;
	}

	public void setType(BetType type) {
		this.type = type;
	}

	public List<Outcome> getOutcomes() {
		return outcomes;
	}

	public void addOutcome(Outcome outcome) {
		if (this.outcomes.add(outcome)) {
			outcome.setBet(this);
		}
	}

	public void removeOutcome(Outcome outcome) {
		if (this.outcomes.remove(outcome)) {
			outcome.setBet(null);
		}
	}
}
