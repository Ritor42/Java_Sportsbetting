package domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Result {
	@Id
	@GeneratedValue
	private int id;

	@OneToMany(cascade = CascadeType.ALL)
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
