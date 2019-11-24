package com.example.sportsbetting.model;

import java.math.BigDecimal;

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
