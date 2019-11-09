package main;

import config.AppConfig;
import config.JpaConfig;
import domain.OutcomeOdd;
import domain.Player;
import domain.Wager;
import exception.CurrencyMismatchException;
import exception.NotEnoughBalanceException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.ISportsBettingService;
import view.IView;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class App {
	private IView view;
	private Player player;
	private ISportsBettingService bettingService;

	public static void main(String[] args) {
		try (ConfigurableApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class)) {
			App app = appContext.getBean(App.class);
			app.play();
		}
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
