package com.example.sportsbetting.service;

import com.example.sportsbetting.builder.BetBuilder;
import com.example.sportsbetting.builder.OutcomeBuilder;
import com.example.sportsbetting.builder.OutcomeOddBuilder;
import com.example.sportsbetting.builder.SportEventBuilder;
import com.example.sportsbetting.domain.BetType;
import com.example.sportsbetting.domain.SportEvent;
import com.example.sportsbetting.exception.TimeOverlapException;
import com.example.sportsbetting.repository.SportEventRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;

public class TestData {

    @Autowired
    private SportEventRepository eventRepository;

    public void Generate() {
        BetBuilder betBuilder = new BetBuilder();
        OutcomeBuilder outcomeBuilder = new OutcomeBuilder();
        OutcomeOddBuilder oddBuilder = new OutcomeOddBuilder();
        SportEventBuilder eventBuilder = new SportEventBuilder();

        Date today = new Date();

        try {
            SportEvent event = eventBuilder.setTitle("MTK-FTC")
                    .setType(false)
                    .setStartDate(today)
                    .setEndDate(new Date(today.getTime() + 2 * 3600 * 1000))
                    .addBet(betBuilder.setDescription("Winner").setType(BetType.Winner)
                            .addOutcome(outcomeBuilder.setDescription("MTK")
                                    .addOutcomeOdd(oddBuilder.setValue(new BigDecimal("7"))
                                            .setValidFrom(today)
                                            .setValidUntil(new Date(today.getTime() + 2 * 3600 * 1000))
                                            .getOutcomeOdd())
                                    .getOutcome())
                            .getBet())
                    .addBet(betBuilder.setDescription("player Oliver Giroud score").setType(BetType.Players_Score)
                            .addOutcome(outcomeBuilder.setDescription("9")
                                    .addOutcomeOdd(oddBuilder.setValue(new BigDecimal("2"))
                                            .setValidFrom(new Date(today.getTime() + 300 * 1000))
                                            .setValidUntil(new Date(today.getTime() + 2 * 3600 * 1000))
                                            .getOutcomeOdd())
                                    .getOutcome())
                            .getBet())
                    .addBet(betBuilder.setDescription("number of scored goals").setType(BetType.Goals)
                            .addOutcome(outcomeBuilder.setDescription("5")
                                    .addOutcomeOdd(oddBuilder.setValue(new BigDecimal("4"))
                                            .setValidFrom(new Date(today.getTime() + 1 * 3600 * 1000))
                                            .setValidUntil(new Date(today.getTime() + 2 * 3600 * 1000))
                                            .getOutcomeOdd())
                                    .getOutcome())
                            .addOutcome(outcomeBuilder.setDescription("3")
                                    .addOutcomeOdd(oddBuilder.setValue(new BigDecimal("3"))
                                            .setValidFrom(today)
                                            .setValidUntil(new Date(today.getTime() + 1 * 3600 * 1000 - 1))
                                            .getOutcomeOdd())
                                    .addOutcomeOdd(oddBuilder.setValue(new BigDecimal("2"))
                                            .setValidFrom(new Date(today.getTime() + 1 * 3600 * 1000))
                                            .setValidUntil(new Date(today.getTime() + 2 * 3600 * 1000))
                                            .getOutcomeOdd())
                                    .getOutcome())
                            .getBet())
                    .getEvent();

            eventRepository.save(event);

        } catch (TimeOverlapException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
