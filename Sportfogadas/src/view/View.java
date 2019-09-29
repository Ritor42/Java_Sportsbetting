package view;

import java.math.BigDecimal;

import java.util.List;
import java.util.Scanner;

import domain.Bet;
import domain.Currency;
import domain.Outcome;
import domain.OutcomeOdd;
import domain.Player;
import domain.SportEvent;
import domain.Wager;

public class View implements IView {

	private Scanner scan;

	public View() {
		scan = new Scanner(System.in);
	}

	@Override
	public void finalize() {
		scan.close();
	}

	@Override
	public Player readPlayerData() {
		try {
			Player player = new Player();

			System.out.println("What is your name?");
			player.setName(scan.nextLine());

			System.out.println("How much money do you have?");
			player.setBalance(new BigDecimal(scan.nextLine()));

			System.out.println("What is your currency? (HUF, EUR or USD)");
			player.setCurrency(Currency.valueOf(scan.nextLine()));

			return player;
		} catch (Exception e) {
			return readPlayerData();
		}
	}

	@Override
	public BigDecimal readWagerAmount() {
		try {
			System.out.println("What amount do you wish to bet on it?");
			return new BigDecimal(scan.nextLine());
		} catch (Exception e) {
			return readWagerAmount();
		}
	}

	@Override
	public OutcomeOdd selectOutcomeOdd(List<SportEvent> events) {
		System.out.println("What are you want to bet on? (choose a number or press q for quit)");
		this.printOutcomeOdds(events);

		try {
			String input = scan.nextLine();

			if (!input.equals("q")) {
				Integer i = 0;
				Integer index = new Integer(input) - 1;

				for (SportEvent event : events) {
					for (Bet bet : event.getBets()) {
						for (Outcome outcome : bet.getOutcomes()) {
							for (OutcomeOdd odd : outcome.getOutcomeOdds()) {
								if (index == i) {
									return odd;
								}
								i++;
							}
						}
					}
				}
			} else {
				return null;
			}
		} catch (Exception e) {

		}

		return selectOutcomeOdd(events);
	}

	@Override
	public void printBalance(Player player) {
		System.out.println("Your balance is " + player.getBalance() + " " + player.getCurrency() + ".");
	}

	@Override
	public void printWagerSaved(Wager wager) {
		System.out.println("Wager saved!");
	}

	@Override
	public void printWelcomeMessage(Player player) {
		System.out.println("Welcome " + player.getName() + "!");
	}

	@Override
	public void printNotEnoughBalance(Player player) {
		System.out.println("You don't have enough money for this transaction!");
	}

	@Override
	public void printOutcomeOdds(List<SportEvent> events) {
		Integer i = 1;
		for (SportEvent event : events) {
			for (Bet bet : event.getBets()) {
				for (Outcome outcome : bet.getOutcomes()) {
					for (OutcomeOdd odd : outcome.getOutcomeOdds()) {
						System.out.println(i + ": Sport event: " + event.getTitle() + " (start: " + event.getStartDate()
								+ "), Bet: " + bet.getDescription() + ", Outcome: " + outcome.getDescription()
								+ " Actual odd: " + odd.getValue() + ", Valid between " + odd.getValidFrom() + " and "
								+ odd.getValidUntil());
						i++;
					}
				}
			}
		}
	}

	@Override
	public void printResults(Player player, List<Wager> wagers) {
		System.out.println("Results:");
		for (Wager wager : wagers) {
			OutcomeOdd odd = wager.getOutcomeOdd();
			Outcome outcome = odd.getOutcome();

			System.out.println("Wager '" + outcome.getBet().getDescription() + "' of " + outcome.getDescription()
					+ " [odd: " + odd.getValue() + ", amount: " + wager.getAmount() + "], win: " + wager.isWin());
		}
	}
}
