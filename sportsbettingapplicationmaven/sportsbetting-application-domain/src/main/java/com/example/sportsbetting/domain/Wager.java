package com.example.sportsbetting.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Wager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private BigDecimal amount;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestampCreated;

    private boolean processed;
    private boolean win;

    @ManyToOne
    @NotNull
    private Player player;

    @ManyToOne
    @NotNull
    private OutcomeOdd odd;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    public Wager() {

    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getTimestampCreated() {
        return this.timestampCreated;
    }

    public void setTimestampCreated(Date timestampCreated) {
        this.timestampCreated = timestampCreated;
    }

    public boolean isProcessed() {
        return this.processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public boolean isWin() {
        return this.win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public OutcomeOdd getOutcomeOdd() {
        return this.odd;
    }

    public void setOutcomeOdd(OutcomeOdd odd) {
        this.odd = odd;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public int getId() {
        return this.id;
    }
}
