package com.sportsbetting.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.sportsbetting.domain.Bet;
import com.sportsbetting.domain.Outcome;
import com.sportsbetting.domain.OutcomeOdd;
import com.sportsbetting.domain.Player;
import com.sportsbetting.domain.Result;
import com.sportsbetting.domain.SportEvent;
import com.sportsbetting.domain.Wager;
import com.sportsbetting.exception.CurrencyMismatchException;
import com.sportsbetting.exception.NotEnoughBalanceException;

public class SportsBettingService implements ISportsBettingService {
	private List<Wager> wagers = new ArrayList<>();
	private List<Player> players = new ArrayList<>();
	private List<SportEvent> events = new ArrayList<>();

	private static Random rand = new Random();

	public SportsBettingService() {
		TestData.Generate(this.events);
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

			Result result = new Result();

			for (Wager wager : this.wagers) {
				if (rand.nextBoolean()) {
					OutcomeOdd odd = wager.getOutcomeOdd();
					result.addWinnerOutcome(odd.getOutcome());

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

			event.setResult(result);
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
}
