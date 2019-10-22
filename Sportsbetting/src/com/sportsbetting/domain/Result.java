package com.sportsbetting.domain;

import java.util.ArrayList;
import java.util.List;

public class Result {
	private List<Outcome> winnerOutcomes = new ArrayList<>();

	public Result() {

	}

	public void addWinnerOutcome(Outcome outcome) {
		if (!this.winnerOutcomes.contains(outcome)) {
			this.winnerOutcomes.add(outcome);
		}
	}

	public void removeWinnerOutcome(Outcome outcome) {
		this.winnerOutcomes.remove(outcome);
	}
}
