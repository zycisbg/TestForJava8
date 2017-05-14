package com.zyc.java8.po;

/**
 * Created by zyc on 17/5/12.
 */
public class Apple {
    private String color;

    private Double weight;

    private Double price;

    public Apple(String color, Double weight, Double price) {
        this.color = color;
        this.weight = weight;
        this.price = price;
    }

    public Apple() {
    }

    @Override
    public String toString() {
        return "Apple{" +
                  "color='" + color + '\'' +
                  ", weight=" + weight +
                  ", price=" + price +
                  '}';
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public static boolean isRed(Apple apple){
        return "red".equals(apple.getColor());
    }
}
