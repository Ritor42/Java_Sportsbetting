package builder;

import domain.Bet;
import domain.BetType;
import domain.Outcome;

import java.util.ArrayList;
import java.util.List;

public class BetBuilder {
	private String description;
	private BetType type;
	private List<Outcome> outcomes = new ArrayList<>();

	public Bet getBet() {
		Bet bet = new Bet(this.description, this.type, this.outcomes);
		this.outcomes = new ArrayList<>();
		return bet;
	}

	public BetBuilder setDescription(String description) {
		this.description = description;
		return this;
	}

	public BetBuilder setType(BetType type) {
		this.type = type;
		return this;
	}

	public BetBuilder addOutcome(Outcome outcome) {
		this.outcomes.add(outcome);
		return this;
	}

	public BetBuilder removeOutcome(Outcome outcome) {
		this.outcomes.remove(outcome);
		return this;
	}
}
