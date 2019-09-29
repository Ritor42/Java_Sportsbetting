package domain;

import java.util.ArrayList;
import java.util.List;

import exception.TimeOverlapException;

public class Outcome {
	private String description;
	private Bet bet;
	private List<OutcomeOdd> outcomeOdds;

	public Outcome() {
		this.outcomeOdds = new ArrayList<OutcomeOdd>();
	}

	public Outcome(String description, List<OutcomeOdd> outcomeOdds) {
		this.description = description;
		this.outcomeOdds = outcomeOdds;

		for (OutcomeOdd odd : this.outcomeOdds)
			odd.setOutcome(this);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Bet getBet() {
		return bet;
	}

	public void setBet(Bet bet) {
		this.bet = bet;
	}

	public List<OutcomeOdd> getOutcomeOdds() {
		return outcomeOdds;
	}

	public void addOutcomeOdd(OutcomeOdd outcomeOdd) throws TimeOverlapException {
		for (OutcomeOdd odd : this.outcomeOdds) {
			if ((odd.getValidFrom().isAfter(outcomeOdd.getValidFrom())
					|| odd.getValidFrom().isEqual(outcomeOdd.getValidFrom()))
					&& (odd.getValidFrom().isBefore(outcomeOdd.getValidUntil())
							|| odd.getValidFrom().isEqual(outcomeOdd.getValidUntil()))
					|| (odd.getValidFrom().isBefore(outcomeOdd.getValidFrom())
							|| odd.getValidFrom().isEqual(outcomeOdd.getValidFrom()))
							&& (odd.getValidUntil().isAfter(outcomeOdd.getValidFrom())
									|| odd.getValidUntil().isEqual(outcomeOdd.getValidFrom()))) {
				throw new TimeOverlapException();
			}
		}

		if (this.outcomeOdds.add(outcomeOdd)) {
			outcomeOdd.setOutcome(this);
		}
	}

	public void removeOutcomeOdd(OutcomeOdd outcomeOdd) {
		if (this.outcomeOdds.remove(outcomeOdd)) {
			outcomeOdd.setOutcome(null);
		}
	}
}
