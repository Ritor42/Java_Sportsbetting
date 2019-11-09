package view;

import com.sun.org.apache.xml.internal.utils.res.XResourceBundle;
import domain.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class View implements IView {
	private ResourceBundle bundle = ResourceBundle.getBundle("ApplicationMessages_hu_HU");
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

		player.setName(readNotEmptyString(bundle.getString("QName")));
		player.setBalance(readGreaterOrEqualBigDecimal(BigDecimal.ZERO, bundle.getString("QMoney")));

		Currency curr = null;
		while ((curr = Currency.lookup(readNotEmptyString(bundle.getString("QCurrency")))) == null) {
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
		return readGreaterBigDecimal(BigDecimal.ZERO, bundle.getString("QAmount"));
	}

	@Override
	public OutcomeOdd selectOutcomeOdd(List<SportEvent> events) {
		System.out.println(bundle.getString("QBet"));
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
		System.out.println(bundle.getString("Balance") + " " + player.getBalance() + " " + player.getCurrency() + ".");
	}

	@Override
	public void printWagerSaved(Wager wager) {
		System.out.println(bundle.getString("WagerSaved"));
	}

	@Override
	public void printWelcomeMessage(Player player) {
		System.out.println(bundle.getString("Welcome") + " " + player.getName() + "!");
	}

	@Override
	public void printNotEnoughBalance(Player player) {
		System.out.println(bundle.getString("EMoney"));
	}

	@Override
	public void printOutcomeOdds(List<SportEvent> events) {
		Integer i = 1;
		for (SportEvent event : events) {
			for (Bet bet : event.getBets()) {
				for (Outcome outcome : bet.getOutcomes()) {
					for (OutcomeOdd odd : outcome.getOutcomeOdds()) {
						System.out.println(i + ": " + bundle.getString("SportEvent") + ": " + event.getTitle() + " ("+bundle.getString("Start")+": " + event.getStartDate()
								+ "), "+bundle.getString("Bet")+": " + bet.getDescription() + ", "+bundle.getString("Outcome")+": " + outcome.getDescription()
								+ " "+bundle.getString("ActualOdd")+": " + odd.getValue() + ", "+bundle.getString("ValidBetween")+" " + odd.getValidFrom() + " "+bundle.getString("And")+" "
								+ odd.getValidUntil());
						i++;
					}
				}
			}
		}
	}

	@Override
	public void printResults(Player player, List<Wager> wagers) {
		System.out.println(bundle.getString("Results")+":");
		for (Wager wager : wagers) {
			OutcomeOdd odd = wager.getOutcomeOdd();
			Outcome outcome = odd.getOutcome();

			System.out.println(bundle.getString("Wager")+"'" + outcome.getBet().getDescription() + "' "+bundle.getString("Of")+" " + outcome.getDescription()
					+ " ["+bundle.getString("Odd")+": " + odd.getValue() + ", "+bundle.getString("Amount")+": " + wager.getAmount() + "], "+bundle.getString("Win")+": " + wager.isWin());
		}
	}
}
