package domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class SportEvent {
	@Id
	@GeneratedValue
	private int id;

	private String title;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	@ManyToOne
	private Result result;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Bet> bets = new ArrayList<>();

	public SportEvent(String title, Date startDate, Date endDate, List<Bet> bets) {
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.bets = bets;

		for (Bet bet : this.bets)
			bet.setEvent(this);
	}

	public int getId() { return this.id; }

	public void setId(int id) { this.id = id; }

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Result getResult() {
		return this.result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public List<Bet> getBets() {
		return this.bets;
	}

	public void addBet(Bet bet) {
		this.bets.add(bet);
		bet.setEvent(this);
	}

	public void removeBet(Bet bet) {
		this.bets.remove(bet);
		bet.setEvent(null);
	}
}
