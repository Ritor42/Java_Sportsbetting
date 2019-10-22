package com.sportsbetting.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.sportsbetting.builder.BetBuilder;
import com.sportsbetting.builder.FootballSportEventBuilder;
import com.sportsbetting.builder.OutcomeBuilder;
import com.sportsbetting.builder.OutcomeOddBuilder;
import com.sportsbetting.builder.SportEventBuilder;
import com.sportsbetting.domain.BetType;
import com.sportsbetting.domain.SportEvent;
import com.sportsbetting.exception.TimeOverlapException;

public class TestData {
	public static void Generate(List<SportEvent> events) {
		BetBuilder betBuilder = new BetBuilder();
		OutcomeBuilder outcomeBuilder = new OutcomeBuilder();
		OutcomeOddBuilder oddBuilder = new OutcomeOddBuilder();
		SportEventBuilder eventBuilder = new FootballSportEventBuilder();

		try {
			SportEvent event = eventBuilder.setTitle("Arsenal vs Chelsea")
					.setStartDate(LocalDateTime.parse("2019-09-25T19:03:00.000"))
					.setEndDate(LocalDateTime.parse("2019-09-25T21:03:00.000"))
					.addBet(betBuilder.setDescription("player Oliver Giroud score").setType(BetType.PLAYERS_SCORE)
							.addOutcome(outcomeBuilder.setDescription("1")
									.addOutcomeOdd(oddBuilder.setValue(new BigDecimal("2"))
											.setValidFrom(LocalDateTime.parse("2019-09-25T19:03:00.000"))
											.setValidUntil(LocalDateTime.parse("2019-09-25T21:03:00.000"))
											.getOutcomeOdd())
									.getOutcome())
							.getBet())
					.addBet(betBuilder.setDescription("number of scored goals").setType(BetType.GOALS)
							.addOutcome(outcomeBuilder.setDescription("2")
									.addOutcomeOdd(oddBuilder.setValue(new BigDecimal("3"))
											.setValidFrom(LocalDateTime.parse("2019-09-25T19:03:00.000"))
											.setValidUntil(LocalDateTime.parse("2019-09-25T21:03:00.000"))
											.getOutcomeOdd())
									.getOutcome())
							.addOutcome(outcomeBuilder.setDescription("3")
									.addOutcomeOdd(oddBuilder.setValue(new BigDecimal("4"))
											.setValidFrom(LocalDateTime.parse("2019-09-25T19:03:00.000"))
											.setValidUntil(LocalDateTime.parse("2019-09-25T20:03:00.000"))
											.getOutcomeOdd())
									.addOutcomeOdd(oddBuilder.setValue(new BigDecimal("5"))
											.setValidFrom(LocalDateTime.parse("2019-09-25T20:03:00.001"))
											.setValidUntil(LocalDateTime.parse("2019-09-25T21:03:00.000"))
											.getOutcomeOdd())
									.getOutcome())
							.getBet())
					.getEvent();

			events.add(event);

		} catch (TimeOverlapException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
