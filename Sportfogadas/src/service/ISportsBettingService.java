package service;

import java.util.List;

import domain.Player;
import domain.SportEvent;
import domain.Wager;
import exception.CurrencyMismatchException;
import exception.NotEnoughBalanceException;

public interface ISportsBettingService {
	Player findPlayer();

	List<Wager> findAllWagers();

	List<SportEvent> findAllSportEvents();

	void calculateResults() throws CurrencyMismatchException;

	void saveWager(Wager wager) throws NotEnoughBalanceException;

	void savePlayer(Player player);
}
