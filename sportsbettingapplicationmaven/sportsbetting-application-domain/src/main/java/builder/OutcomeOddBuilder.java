package builder;

import domain.OutcomeOdd;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class OutcomeOddBuilder {
	private BigDecimal value;
	private Date validFrom;
	private Date validUntil;

	public OutcomeOdd getOutcomeOdd() {
		return new OutcomeOdd(this.value, this.validFrom, this.validUntil);
	}

	public OutcomeOddBuilder setValue(BigDecimal value) {
		this.value = value;
		return this;
	}

	public OutcomeOddBuilder setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
		return this;
	}

	public OutcomeOddBuilder setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
		return this;
	}
}
