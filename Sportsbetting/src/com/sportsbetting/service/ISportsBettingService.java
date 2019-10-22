package com.sportsbetting.service;

import java.util.List;

import com.sportsbetting.domain.Player;
import com.sportsbetting.domain.SportEvent;
import com.sportsbetting.domain.Wager;
import com.sportsbetting.exception.CurrencyMismatchException;
import com.sportsbetting.exception.NotEnoughBalanceException;

public interface ISportsBettingService {
	Player findPlayer();

	List<Wager> findAllWagers();

	List<SportEvent> findAllSportEvents();

	void calculateResults() throws CurrencyMismatchException;

	void saveWager(Wager wager) throws NotEnoughBalanceException;

	void savePlayer(Player player);
}
