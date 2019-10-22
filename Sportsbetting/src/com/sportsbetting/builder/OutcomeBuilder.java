package com.sportsbetting.builder;

import java.util.ArrayList;
import java.util.List;

import com.sportsbetting.domain.Outcome;
import com.sportsbetting.domain.OutcomeOdd;
import com.sportsbetting.exception.TimeOverlapException;

public class OutcomeBuilder {
	private String description;
	private List<OutcomeOdd> outcomeOdds = new ArrayList<>();

	public Outcome getOutcome() {
		Outcome outcome = new Outcome(this.description, this.outcomeOdds);
		this.outcomeOdds = new ArrayList<>();
		return outcome;
	}

	public OutcomeBuilder setDescription(String description) {
		this.description = description;
		return this;
	}

	public OutcomeBuilder addOutcomeOdd(OutcomeOdd outcomeOdd) throws TimeOverlapException {
		for (OutcomeOdd odd : this.outcomeOdds) {
			Boolean isAfterCollision = odd.getValidFrom().compareTo(outcomeOdd.getValidFrom()) > -1
					&& odd.getValidFrom().compareTo(outcomeOdd.getValidUntil()) < 1;

			Boolean isBeforeCollision = odd.getValidFrom().compareTo(outcomeOdd.getValidFrom()) < 1
					&& odd.getValidUntil().compareTo(outcomeOdd.getValidFrom()) > -1;

			if (isAfterCollision || isBeforeCollision) {
				throw new TimeOverlapException();
			}
		}

		this.outcomeOdds.add(outcomeOdd);
		return this;
	}

	public OutcomeBuilder removeOutcomeOdd(OutcomeOdd outcomeOdd) {
		this.outcomeOdds.remove(outcomeOdd);
		return this;
	}
}
