package com.example.sportsbetting.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class WagerRequest {
    @NotNull
    private Integer oddId;

    @Min(0)
    private BigDecimal amount;

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
