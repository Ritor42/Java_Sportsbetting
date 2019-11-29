package com.example.sportsbetting.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class WagerRequest {
    @NotNull
    private int oddId;

    @Min(0)
    private BigDecimal amount;

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getOddId() {
        return this.oddId;
    }

    public void setOddId(int oddId) {
        this.oddId = oddId;
    }
}
