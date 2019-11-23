package com.example.sportsbetting.model;

import com.example.sportsbetting.domain.Currency;
import com.example.sportsbetting.domain.OutcomeOdd;
import com.example.sportsbetting.domain.Player;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

public class WagerRequest {
    private Integer oddId;
    private BigDecimal amount;

    public Boolean validate() {
        return oddId != null && amount != null;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getOddId() {
        return this.oddId;
    }

    public void setOddId(Integer oddId) {
        this.oddId = oddId;
    }
}
