package domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Player extends User {
	private String name;
	private Integer accountNumber;
	private BigDecimal balance;
	private LocalDate birth;
	private Currency currency;

	public Player() {

	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getBalance() {
		return this.balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public LocalDate getBirth() {
		return this.birth;
	}

	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}

	public Currency getCurrency() {
		return this.currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
}
