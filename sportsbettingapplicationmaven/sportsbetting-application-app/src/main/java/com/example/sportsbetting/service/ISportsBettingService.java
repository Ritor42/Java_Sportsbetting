package com.example.sportsbetting.service;

import com.example.sportsbetting.domain.OutcomeOdd;
import com.example.sportsbetting.domain.Player;
import com.example.sportsbetting.domain.SportEvent;
import com.example.sportsbetting.domain.Wager;
import com.example.sportsbetting.exception.CurrencyMismatchException;
import com.example.sportsbetting.exception.NotEnoughBalanceException;

import java.util.List;

public interface ISportsBettingService {
    List<SportEvent> findAllSportEvents();

    List<Wager> findAllWagers();

    Wager findWager(Integer id);

    void saveWager(Wager wager) throws NotEnoughBalanceException;

    void deleteWager(Integer id) throws CurrencyMismatchException;

    void deleteWager(Wager wager) throws CurrencyMismatchException;

    Player findPlayer(Integer id);

    Player findPlayer(String email, String password);

    void savePlayer(Player player);

    List<OutcomeOdd> findAllOutcomeOdds();

    OutcomeOdd findOutcomeOdd(Integer id);

    void calculateResults() throws CurrencyMismatchException;
}
