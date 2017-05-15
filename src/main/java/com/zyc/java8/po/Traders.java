package com.zyc.java8.po;

/**
 * Created by zyc on 17/5/15.
 */
public class Traders {

    private String name;

    private String city;

    @Override
    public String toString() {
        return "Traders{" +
                  "name='" + name + '\'' +
                  ", city='" + city + '\'' +
                  '}';
    }

    public Traders() {
    }

    public Traders(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
