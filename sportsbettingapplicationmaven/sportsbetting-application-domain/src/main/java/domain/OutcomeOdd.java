package domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class OutcomeOdd {
	@Id
	@GeneratedValue
	private int id;

	private BigDecimal value;

	@Temporal(TemporalType.TIMESTAMP)
	private Date validFrom;

	@Temporal(TemporalType.TIMESTAMP)
	private Date validUntil;

	@ManyToOne
	private Outcome outcome;

	public OutcomeOdd() {

	}

	public OutcomeOdd(BigDecimal value, Date validFrom, Date validUntil) {
		this.value = value;
		this.validFrom = validFrom;
		this.validUntil = validUntil;
	}

	public BigDecimal getValue() {
		return this.value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Date getValidFrom() {
		return this.validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidUntil() {
		return this.validUntil;
	}

	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}

	public Outcome getOutcome() {
		return this.outcome;
	}

	public void setOutcome(Outcome outcome) {
		this.outcome = outcome;
	}
}
