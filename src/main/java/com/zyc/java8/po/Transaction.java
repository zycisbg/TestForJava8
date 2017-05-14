package com.zyc.java8.po;

/**
 * Created by zyc on 17/5/12.
 */
public class Transaction {
    private Integer id;

    private Currency currency;

    private Double money;

    @Override
    public String toString() {
        return "Transaction{" +
                  "id=" + id +
                  ", currency='" + currency + '\'' +
                  ", money=" + money +
                  '}';
    }

    public Transaction(Integer id, Currency currency, Double money) {
        this.id = id;
        this.currency = currency;
        this.money = money;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
