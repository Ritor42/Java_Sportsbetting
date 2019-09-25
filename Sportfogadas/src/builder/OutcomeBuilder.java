package builder;

import java.util.ArrayList;
import java.util.List;

import domain.Outcome;
import domain.OutcomeOdd;
import exception.TimeOverlapException;

public class OutcomeBuilder {
	private String description;	
	private List<OutcomeOdd> outcomeOdds;
	
	public OutcomeBuilder() {
		this.outcomeOdds = new ArrayList<OutcomeOdd>();
	}
	
	public Outcome getOutcome() {
		Outcome outcome = new Outcome(this.description, this.outcomeOdds);
		this.outcomeOdds = new ArrayList<OutcomeOdd>();
		return outcome;
	}
	
	public OutcomeBuilder setDescription(String description) {
		this.description = description;
		return this;
	}
	
	public OutcomeBuilder addOutcomeOdd(OutcomeOdd outcomeOdd) throws TimeOverlapException {
		for(OutcomeOdd odd : this.outcomeOdds) {
			if((odd.getValidFrom().isAfter(outcomeOdd.getValidFrom()) || odd.getValidFrom().isEqual(outcomeOdd.getValidFrom())) &&
				(odd.getValidFrom().isBefore(outcomeOdd.getValidUntil()) || odd.getValidFrom().isEqual(outcomeOdd.getValidUntil())) ||
				(odd.getValidFrom().isBefore(outcomeOdd.getValidFrom()) || odd.getValidFrom().isEqual(outcomeOdd.getValidFrom())) &&
				(odd.getValidUntil().isAfter(outcomeOdd.getValidFrom()) || odd.getValidUntil().isEqual(outcomeOdd.getValidFrom()))) {
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
