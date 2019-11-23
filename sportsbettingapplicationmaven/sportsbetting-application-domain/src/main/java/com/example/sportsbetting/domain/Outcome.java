package com.example.sportsbetting.domain;

import com.example.sportsbetting.exception.TimeOverlapException;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Outcome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @NotNull
    private Bet bet;

    @NotEmpty
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OutcomeOdd> outcomeOdds = new ArrayList<>();

    public Outcome() {
    }

    public Outcome(String description, List<OutcomeOdd> outcomeOdds) {
        this.description = description;
        this.outcomeOdds = outcomeOdds;

        for (OutcomeOdd odd : this.outcomeOdds)
            odd.setOutcome(this);
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return this.id;
    }

    public Bet getBet() {
        return this.bet;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }

    public List<OutcomeOdd> getOutcomeOdds() {
        return this.outcomeOdds;
    }

    public void addOutcomeOdd(OutcomeOdd outcomeOdd) throws TimeOverlapException {
        for (OutcomeOdd odd : this.outcomeOdds) {
            Boolean isAfterCollision = odd.getValidFrom().compareTo(outcomeOdd.getValidFrom()) > -1
                    && odd.getValidFrom().compareTo(outcomeOdd.getValidUntil()) < 1;

            Boolean isBeforeCollision = odd.getValidFrom().compareTo(outcomeOdd.getValidFrom()) < 1
                    && odd.getValidUntil().compareTo(outcomeOdd.getValidFrom()) > -1;

            if (isAfterCollision || isBeforeCollision) {
                throw new TimeOverlapException();
            }
        }

        this.outcomeOdds.add(outcomeOdd);
        outcomeOdd.setOutcome(this);
    }

    public void removeOutcomeOdd(OutcomeOdd outcomeOdd) {
        this.outcomeOdds.remove(outcomeOdd);
        outcomeOdd.setOutcome(null);
    }
}
