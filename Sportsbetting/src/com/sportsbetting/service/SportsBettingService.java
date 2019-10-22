package com.sportsbetting.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.sportsbetting.builder.BetBuilder;
import com.sportsbetting.builder.FootballSportEventBuilder;
import com.sportsbetting.builder.OutcomeBuilder;
import com.sportsbetting.builder.OutcomeOddBuilder;
import com.sportsbetting.builder.SportEventBuilder;
import com.sportsbetting.domain.Bet;
import com.sportsbetting.domain.BetType;
import com.sportsbetting.domain.Outcome;
import com.sportsbetting.domain.OutcomeOdd;
import com.sportsbetting.domain.Player;
import com.sportsbetting.domain.Result;
import com.sportsbetting.domain.SportEvent;
import com.sportsbetting.domain.Wager;
import com.sportsbetting.exception.CurrencyMismatchException;
import com.sportsbetting.exception.NotEnoughBalanceException;
import com.sportsbetting.exception.TimeOverlapException;

public class SportsBettingService implements ISportsBettingService {
	private List<Wager> wagers;
	private List<Player> players;
	private List<SportEvent> events;

	private static Random rand;

	static {
		rand = new Random();
	}

	public SportsBettingService() {
		this.wagers = new ArrayList<Wager>();
		this.players = new ArrayList<Player>();
		this.events = new ArrayList<SportEvent>();
		this.generateTest();
	}

	@Override
	public Player findPlayer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Wager> findAllWagers() {
		return this.wagers;
	}

	@Override
	public List<SportEvent> findAllSportEvents() {
		return this.events;
	}

	@Override
	public void calculateResults() throws CurrencyMismatchException {
		for (SportEvent event : this.events) {
			List<Outcome> outcomes = new ArrayList<Outcome>();

			for (Bet bet : event.getBets()) {
				outcomes.addAll(bet.getOutcomes());
			}

			if (outcomes.size() > 0) {
				int winnerIndex = rand.nextInt(outcomes.size());
				Outcome outcome = outcomes.get(winnerIndex);
				List<OutcomeOdd> odds = outcome.getOutcomeOdds();

				Result result = new Result();
				result.addWinnerOutcome(outcome);
				event.setResult(result);

				for (Wager wager : this.wagers) {
					OutcomeOdd odd = wager.getOutcomeOdd();

					if (odds.contains(odd)) {
						Player player = wager.getPlayer();
						wager.setWin(true);

						if (wager.getCurrency() == player.getCurrency()) {
							player.setBalance(player.getBalance().add(wager.getAmount().multiply(odd.getValue())));
							wager.setProcessed(true);
						} else {
							throw new CurrencyMismatchException();
						}
					}
				}
			}
		}
	}

	@Override
	public void saveWager(Wager wager) throws NotEnoughBalanceException {

		if (wager != null && wager.getPlayer() != null) {
			Player player = wager.getPlayer();

			if (player.getBalance().compareTo(wager.getAmount()) != -1) {
				player.setBalance(player.getBalance().subtract(wager.getAmount()));
				wager.setTimestampCreated(LocalDateTime.now());
				this.wagers.add(wager);
			} else {
				throw new NotEnoughBalanceException();
			}
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void savePlayer(Player player) {
		if (player != null) {
			this.players.add(player);
		} else {
			throw new IllegalArgumentException();
		}
	}

	private void generateTest() {
		BetBuilder betBuilder = new BetBuilder();
		OutcomeBuilder outcomeBuilder = new OutcomeBuilder();
		OutcomeOddBuilder oddBuilder = new OutcomeOddBuilder();
		SportEventBuilder eventBuilder = new FootballSportEventBuilder();

		try {
			SportEvent event = eventBuilder.setTitle("Arsenal vs Chelsea")
					.setStartDate(LocalDateTime.parse("2019-09-25T19:03:00.000"))
					.setEndDate(LocalDateTime.parse("2019-09-25T21:03:00.000"))
					.addBet(betBuilder.setDescription("player Oliver Giroud score").setType(BetType.PLAYERS_SCORE)
							.addOutcome(outcomeBuilder.setDescription("1")
									.addOutcomeOdd(oddBuilder.setValue(new BigDecimal("2"))
											.setValidFrom(LocalDateTime.parse("2019-09-25T19:03:00.000"))
											.setValidUntil(LocalDateTime.parse("2019-09-25T21:03:00.000"))
											.getOutcomeOdd())
									.getOutcome())
							.getBet())
					.addBet(betBuilder.setDescription("number of scored goals").setType(BetType.GOALS)
							.addOutcome(outcomeBuilder.setDescription("2")
									.addOutcomeOdd(oddBuilder.setValue(new BigDecimal("3"))
											.setValidFrom(LocalDateTime.parse("2019-09-25T19:03:00.000"))
											.setValidUntil(LocalDateTime.parse("2019-09-25T21:03:00.000"))
											.getOutcomeOdd())
									.getOutcome())
							.addOutcome(outcomeBuilder.setDescription("3")
									.addOutcomeOdd(oddBuilder.setValue(new BigDecimal("4"))
											.setValidFrom(LocalDateTime.parse("2019-09-25T19:03:00.000"))
											.setValidUntil(LocalDateTime.parse("2019-09-25T20:03:00.000"))
											.getOutcomeOdd())
									.addOutcomeOdd(oddBuilder.setValue(new BigDecimal("5"))
											.setValidFrom(LocalDateTime.parse("2019-09-25T20:03:00.001"))
											.setValidUntil(LocalDateTime.parse("2019-09-25T21:03:00.000"))
											.getOutcomeOdd())
									.getOutcome())
							.getBet())
					.getEvent();

			Player player = new Player();
			player.setName("Test Player");

			this.events.add(event);
			this.players.add(player);

		} catch (TimeOverlapException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
