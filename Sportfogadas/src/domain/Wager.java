package domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Wager {
	private BigDecimal amount;
	private LocalDateTime timestampCreated;
	private boolean processed;
	private boolean win;	
	private Player player;
	private OutcomeOdd odd;
	private Currency currency;
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public LocalDateTime getTimestampCreated() {
		return timestampCreated;
	}
	
	public void setTimestampCreated(LocalDateTime timestampCreated) {
		this.timestampCreated = timestampCreated;
	}
	
	public boolean isProcessed() {
		return processed;
	}
	
	public void setProcessed(boolean processed) {
		this.processed = processed;
	}
	
	public boolean isWin() {
		return win;
	}
	
	public void setWin(boolean win) {
		this.win = win;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public OutcomeOdd getOutcomeOdd() {
		return odd;
	}
	
	public void setOutcomeOdd(OutcomeOdd odd) {
		this.odd = odd;
	}
	
	public Currency getCurrency() {
		return currency;
	}
	
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
}
