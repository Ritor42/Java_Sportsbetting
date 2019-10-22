package com.sportsbetting.view;

import java.math.BigDecimal;

import java.util.List;
import java.util.Scanner;

import com.sportsbetting.domain.Bet;
import com.sportsbetting.domain.Currency;
import com.sportsbetting.domain.Outcome;
import com.sportsbetting.domain.OutcomeOdd;
import com.sportsbetting.domain.Player;
import com.sportsbetting.domain.SportEvent;
import com.sportsbetting.domain.Wager;

public class View implements IView {

	private Scanner scan = new Scanner(System.in);

	public View() {

	}

	@Override
	public void finalize() {
		scan.close();
	}

	@Override
	public Player readPlayerData() {
		Player player = new Player();

		player.setName(readNotEmptyString("What is your name?"));
		player.setBalance(readGreaterOrEqualBigDecimal(BigDecimal.ZERO, "How much money do you have?"));

		Currency curr = null;
		while ((curr = Currency.lookup(readNotEmptyString("What is your currency? (HUF, EUR or USD)"))) == null) {
		}

		player.setCurrency(curr);

		return player;
	}

	private String readNotEmptyString(String message) {
		String result = "";
		System.out.println(message);
		while ((result = scan.nextLine()).isEmpty()) {
			System.out.println(message);
		}

		return result;
	}

	private BigDecimal readBigDecimal(String message) {
		try {
			return new BigDecimal(readNotEmptyString(message));
		} catch (Exception e) {
			return readBigDecimal(message);
		}
	}

	private BigDecimal readGreaterBigDecimal(BigDecimal than, String message) {
		BigDecimal result = than.subtract(new BigDecimal(1));

		while ((result = readBigDecimal(message)).compareTo(than) < 1) {
		}

		return result;
	}

	private BigDecimal readGreaterOrEqualBigDecimal(BigDecimal than, String message) {
		BigDecimal result = than.subtract(new BigDecimal(1));

		while ((result = readBigDecimal(message)).compareTo(than) < 0) {
		}

		return result;
	}

	@Override
	public BigDecimal readWagerAmount() {
		return readGreaterBigDecimal(BigDecimal.ZERO, "What amount do you wish to bet on it?");
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
