package service;


import domain.*;
import exception.CurrencyMismatchException;
import exception.NotEnoughBalanceException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class SportsBettingService implements ISportsBettingService {
	private static Random rand = new Random();

	public SportsBettingService() {
		TestData.Generate();
	}

	@Override
	public Player findPlayer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Wager> findAllWagers() {
		return DbService.GetDbService().getWagers();
	}

	@Override
	public List<SportEvent> findAllSportEvents() {
		return DbService.GetDbService().getEvents();
	}

	@Override
	public void calculateResults() throws CurrencyMismatchException {
		for (SportEvent event : DbService.GetDbService().getEvents()) {

			Result result = new Result();

			for (Wager wager : DbService.GetDbService().getWagers()) {
				if (rand.nextBoolean()) {
					OutcomeOdd odd = wager.getOutcomeOdd();
					result.addWinnerOutcome(odd.getOutcome());

					Player player = wager.getPlayer();
					wager.setWin(true);

					if (wager.getCurrency() == player.getCurrency()) {
						player.setBalance(player.getBalance().add(wager.getAmount().multiply(odd.getValue())));
						DbService.GetDbService().Add(player);
					} else {
						throw new CurrencyMismatchException();
					}
				}
				wager.setProcessed(true);
				DbService.GetDbService().Add(wager);
			}

			event.setResult(result);

			DbService.GetDbService().Add(result);
			DbService.GetDbService().Add(event);
		}
	}

	@Override
	public void saveWager(Wager wager) throws NotEnoughBalanceException {

		if (wager != null && wager.getPlayer() != null) {
			Player player = wager.getPlayer();

			if (player.getBalance().compareTo(wager.getAmount()) != -1) {
				player.setBalance(player.getBalance().subtract(wager.getAmount()));
				wager.setTimestampCreated(new Date());

				DbService.GetDbService().Add(player);
				DbService.GetDbService().Add(wager);
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
			DbService.GetDbService().Add(player);
		} else {
			throw new IllegalArgumentException();
		}
	}
}
