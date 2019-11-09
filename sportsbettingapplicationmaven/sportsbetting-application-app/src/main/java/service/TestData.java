package service;


import builder.BetBuilder;
import builder.OutcomeBuilder;
import builder.OutcomeOddBuilder;
import builder.SportEventBuilder;
import config.AppConfig;
import config.JpaConfig;
import domain.BetType;
import domain.Player;
import domain.SportEvent;
import exception.TimeOverlapException;
import main.App;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class TestData {

	public static void Generate() {
		BetBuilder betBuilder = new BetBuilder();
		OutcomeBuilder outcomeBuilder = new OutcomeBuilder();
		OutcomeOddBuilder oddBuilder = new OutcomeOddBuilder();
		SportEventBuilder eventBuilder = new SportEventBuilder();

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

		try {
			SportEvent event = eventBuilder.setTitle("Arsenal vs Chelsea")
					.setStartDate(format.parse("2019-09-25 19:03:00"))
					.setEndDate(format.parse("2019-09-25 21:03:00"))
					.addBet(betBuilder.setDescription("player Oliver Giroud score").setType(BetType.PLAYERS_SCORE)
							.addOutcome(outcomeBuilder.setDescription("1")
									.addOutcomeOdd(oddBuilder.setValue(new BigDecimal("2"))
											.setValidFrom(format.parse("2019-09-25 19:03:00"))
											.setValidUntil(format.parse("2019-09-25 21:03:00"))
											.getOutcomeOdd())
									.getOutcome())
							.getBet())
					.addBet(betBuilder.setDescription("number of scored goals").setType(BetType.GOALS)
							.addOutcome(outcomeBuilder.setDescription("2")
									.addOutcomeOdd(oddBuilder.setValue(new BigDecimal("3"))
											.setValidFrom(format.parse("2019-09-25 19:03:00"))
											.setValidUntil(format.parse("2019-09-25 21:03:00"))
											.getOutcomeOdd())
									.getOutcome())
							.addOutcome(outcomeBuilder.setDescription("3")
									.addOutcomeOdd(oddBuilder.setValue(new BigDecimal("4"))
											.setValidFrom(format.parse("2019-09-25 19:03:00"))
											.setValidUntil(format.parse("2019-09-25 20:03:00"))
											.getOutcomeOdd())
									.addOutcomeOdd(oddBuilder.setValue(new BigDecimal("5"))
											.setValidFrom(format.parse("2019-09-25 20:03:01"))
											.setValidUntil(format.parse("2019-09-25 21:03:00"))
											.getOutcomeOdd())
									.getOutcome())
							.getBet())
					.getEvent();

			DbService.GetDbService().Add(event);

		} catch (TimeOverlapException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
