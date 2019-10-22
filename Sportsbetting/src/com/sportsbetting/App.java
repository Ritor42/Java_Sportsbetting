package com.sportsbetting;

import com.sportsbetting.domain.OutcomeOdd;
import com.sportsbetting.domain.Player;
import com.sportsbetting.domain.Wager;
import com.sportsbetting.exception.CurrencyMismatchException;
import com.sportsbetting.exception.NotEnoughBalanceException;
import com.sportsbetting.service.ISportsBettingService;
import com.sportsbetting.service.SportsBettingService;
import com.sportsbetting.view.IView;
import com.sportsbetting.view.View;

public class App {
	private IView view;
	private Player player;
	private ISportsBettingService bettingService;

	public static void main(String[] args) {
		App app = new App(new SportsBettingService(), new View());
		app.play();
	}

	public App(ISportsBettingService bettingService, IView view) {
		this.view = view;
		this.bettingService = bettingService;
	}

	public void play() {
		createPlayer();
		doBetting();
		calculateResults();
		printResults();
	}

	private void createPlayer() {
		player = view.readPlayerData();
		bettingService.savePlayer(player);
		view.printWelcomeMessage(player);
		view.printBalance(player);
	}

	private void doBetting() {
		OutcomeOdd outcomeOdd = view.selectOutcomeOdd(bettingService.findAllSportEvents());

		while (outcomeOdd != null) {
			Wager wager = new Wager();
			wager.setPlayer(player);
			wager.setCurrency(player.getCurrency());
			wager.setAmount(view.readWagerAmount());
			wager.setOutcomeOdd(outcomeOdd);

			try {
				bettingService.saveWager(wager);
				view.printWagerSaved(wager);
				view.printBalance(player);
			} catch (NotEnoughBalanceException e) {
				view.printNotEnoughBalance(player);
			}

			outcomeOdd = view.selectOutcomeOdd(bettingService.findAllSportEvents());
		}
	}

	private void calculateResults() {
		try {
			bettingService.calculateResults();
		} catch (CurrencyMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void printResults() {
		view.printResults(player, bettingService.findAllWagers());
		view.printBalance(player);
	}
}
