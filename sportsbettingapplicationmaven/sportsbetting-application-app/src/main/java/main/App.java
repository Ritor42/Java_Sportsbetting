package main;

import domain.OutcomeOdd;
import domain.Player;
import domain.Wager;
import exception.CurrencyMismatchException;
import exception.NotEnoughBalanceException;
import service.ISportsBettingService;
import service.SportsBettingService;
import view.IView;
import view.View;

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
