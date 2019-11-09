package service;

import domain.Player;
import domain.SportEvent;
import domain.Wager;
import exception.CurrencyMismatchException;
import exception.NotEnoughBalanceException;

import java.util.List;

public interface ISportsBettingService {
	Player findPlayer();

	List<Wager> findAllWagers();

	List<SportEvent> findAllSportEvents();

	void calculateResults() throws CurrencyMismatchException;

	void saveWager(Wager wager) throws NotEnoughBalanceException;

	void savePlayer(Player player);
}
