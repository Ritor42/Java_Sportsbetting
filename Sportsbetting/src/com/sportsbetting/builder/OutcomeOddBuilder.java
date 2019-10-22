package com.sportsbetting.builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.sportsbetting.domain.OutcomeOdd;

public class OutcomeOddBuilder {
	private BigDecimal value;
	private LocalDateTime validFrom;
	private LocalDateTime validUntil;

	public OutcomeOdd getOutcomeOdd() {
		return new OutcomeOdd(this.value, this.validFrom, this.validUntil);
	}

	public OutcomeOddBuilder setValue(BigDecimal value) {
		this.value = value;
		return this;
	}

	public OutcomeOddBuilder setValidFrom(LocalDateTime validFrom) {
		this.validFrom = validFrom;
		return this;
	}

	public OutcomeOddBuilder setValidUntil(LocalDateTime validUntil) {
		this.validUntil = validUntil;
		return this;
	}
}
