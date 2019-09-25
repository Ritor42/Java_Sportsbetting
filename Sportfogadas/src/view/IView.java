package view;

import java.math.BigDecimal;
import java.util.List;

import domain.OutcomeOdd;
import domain.Player;
import domain.SportEvent;
import domain.Wager;

public interface IView {
	Player readPlayerData();
	BigDecimal readWagerAmount();	
	OutcomeOdd selectOutcomeOdd(List<SportEvent> events);	
	void printBalance(Player player);
	void printWagerSaved(Wager wager);
	void printWelcomeMessage(Player player);
	void printNotEnoughBalance(Player player);
	void printOutcomeOdds(List<SportEvent> events);
	void printResults(Player player, List<Wager> wagers);
}
