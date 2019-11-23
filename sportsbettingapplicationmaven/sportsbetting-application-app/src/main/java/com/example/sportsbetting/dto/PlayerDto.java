package com.example.sportsbetting.dto;

import com.example.sportsbetting.domain.Currency;
import com.example.sportsbetting.domain.Player;

import java.math.BigDecimal;
import java.util.Date;

public class PlayerDto {
    private int id;
    private String email;
    private String name;
    private String accountNumber;
    private BigDecimal balance;
    private Date birth;
    private Currency currency;

    public PlayerDto() {
    }

    public PlayerDto(Player player) {
        this.id = player.getId();
        this.email = player.getEmail();
        this.name = player.getName();
        this.accountNumber = player.getAccountNumber();
        this.balance = player.getBalance();
        this.birth = player.getBirth();
        this.currency = player.getCurrency();
    }

    public String getName() {
        return this.name;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public Date getBirth() {
        return this.birth;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public int getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }
}
