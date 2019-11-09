package domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Bet {
	@Id
	@GeneratedValue
	private int id;

	private String description;

	@ManyToOne
	private SportEvent event;

	@Enumerated(EnumType.STRING)
	private BetType type;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Outcome> outcomes = new ArrayList<>();

	public Bet() {

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
		return this.event;
	}

	public void setEvent(SportEvent event) {
		this.event = event;
	}

	public BetType getType() {
		return this.type;
	}

	public void setType(BetType type) {
		this.type = type;
	}

	public List<Outcome> getOutcomes() {
		return this.outcomes;
	}

	public void addOutcome(Outcome outcome) {
		this.outcomes.add(outcome);
		outcome.setBet(this);
	}

	public void removeOutcome(Outcome outcome) {
		this.outcomes.remove(outcome);
		outcome.setBet(null);
	}
}
