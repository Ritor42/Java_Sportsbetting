package com.sportsbetting.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OutcomeOdd {
	private BigDecimal value;
	private LocalDateTime validFrom;
	private LocalDateTime validUntil;
	private Outcome outcome;

	public OutcomeOdd() {

	}

	public OutcomeOdd(BigDecimal value, LocalDateTime validFrom, LocalDateTime validUntil) {
		this.value = value;
		this.validFrom = validFrom;
		this.validUntil = validUntil;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public LocalDateTime getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(LocalDateTime validFrom) {
		this.validFrom = validFrom;
	}

	public LocalDateTime getValidUntil() {
		return validUntil;
	}

	public void setValidUntil(LocalDateTime validUntil) {
		this.validUntil = validUntil;
	}

	public Outcome getOutcome() {
		return outcome;
	}

	public void setOutcome(Outcome outcome) {
		this.outcome = outcome;
	}
}
