package com.sportsbetting.domain;

import java.util.ArrayList;
import java.util.List;

public class Result {
	private List<Outcome> winnerOutcomes;

	public Result() {
		this.winnerOutcomes = new ArrayList<Outcome>();
	}

	public void addWinnerOutcome(Outcome outcome) {
		this.winnerOutcomes.add(outcome);
	}

	public void removeWinnerOutcome(Outcome outcome) {
		this.winnerOutcomes.remove(outcome);
	}
}
