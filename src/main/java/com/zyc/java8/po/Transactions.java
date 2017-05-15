package com.zyc.java8.po;

/**
 * Created by zyc on 17/5/15.
 */
public class Transactions {
    private Traders traders;

    private Integer year;

    private Integer value;

    @Override
    public String toString() {
        return "Transactions{" +
                  "traders=" + traders +
                  ", year=" + year +
                  ", value=" + value +
                  '}';
    }

    public Transactions() {
    }

    public Transactions(Traders traders, Integer year, Integer value) {
        this.traders = traders;
        this.year = year;
        this.value = value;
    }

    public Traders getTraders() {
        return traders;
    }

    public void setTraders(Traders traders) {
        this.traders = traders;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
