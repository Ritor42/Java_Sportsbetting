package com.example.sportsbetting.service;


import com.example.sportsbetting.domain.*;
import com.example.sportsbetting.exception.CurrencyMismatchException;
import com.example.sportsbetting.exception.NotEnoughBalanceException;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class SportsBettingService implements ISportsBettingService {
    private static Random rand = new Random();
    private static SportsBettingService model;

    private DbService dbService = DbService.GetInstance();

    private SportsBettingService() {
        TestData.Generate();
        TestData.Generate();
    }

    public static SportsBettingService GetInstance() {
        if (model == null) {
            model = new SportsBettingService();
        }

        return model;
    }

    @Override
    public List<Wager> findAllWagers() {
        return dbService.getWagers();
    }

    @Override
    public Wager findWager(Integer id) {
        return dbService.getWager(id);
    }

    @Override
    public void saveWager(Wager wager) throws NotEnoughBalanceException {

        if (wager != null && wager.getPlayer() != null) {
            Player player = wager.getPlayer();

            if (player.getBalance().compareTo(wager.getAmount()) != -1) {
                player.setBalance(player.getBalance().subtract(wager.getAmount()));
                wager.setTimestampCreated(new Date());
                wager.setProcessed(false);
                wager.setWin(false);

                dbService.Save(player);
                dbService.Save(wager);
            } else {
                throw new NotEnoughBalanceException();
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void deleteWager(Integer id) throws CurrencyMismatchException {
        Wager wager = dbService.getWager(id);
        deleteWager(wager);
    }

    @Override
    public void deleteWager(Wager wager) throws CurrencyMismatchException {
        if (wager != null && wager.getPlayer() != null && !wager.isProcessed()) {
            Player player = wager.getPlayer();

            if (wager.getOutcomeOdd().getValidFrom().compareTo(new Date()) != -1) {
                if (player.getCurrency() == wager.getCurrency()) {
                    player.setBalance(player.getBalance().add(wager.getAmount()));
                    dbService.Save(player);
                    dbService.Delete(wager);
                } else {
                    throw new CurrencyMismatchException();
                }
            }
        }
    }

    @Override
    public Player findPlayer(Integer id) {
        return dbService.getPlayer(id);
    }

    @Override
    public Player findPlayer(String email, String password) {
        return dbService.getPlayer(email, password);
    }

    @Override
    public void savePlayer(Player player) {
        if (player != null) {
            dbService.Save(player);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public List<OutcomeOdd> findAllOutcomeOdds() {
        return dbService.getOutcomeOdds();
    }

    @Override
    public OutcomeOdd findOutcomeOdd(Integer id) {
        return dbService.getOutcomeOdd(id);
    }

    @Override
    public List<SportEvent> findAllSportEvents() {
        return dbService.getEvents();
    }

    @Override
    public void calculateResults() throws CurrencyMismatchException {
        for (SportEvent event : dbService.getEvents()) {
            Result result = new Result();

            for (Wager wager : dbService.getWagers()) {
                if (rand.nextBoolean() && !wager.isProcessed()) {
                    OutcomeOdd odd = wager.getOutcomeOdd();
                    result.addWinnerOutcome(odd.getOutcome());

                    Player player = wager.getPlayer();
                    wager.setWin(true);

                    if (wager.getCurrency() == player.getCurrency()) {
                        player.setBalance(player.getBalance().add(wager.getAmount().multiply(odd.getValue())));
                        dbService.Save(player);
                    } else {
                        throw new CurrencyMismatchException();
                    }
                }
                wager.setProcessed(true);
                dbService.Save(wager);
            }

            event.setResult(result);

            dbService.Save(result);
            dbService.Save(event);
        }
    }
}
