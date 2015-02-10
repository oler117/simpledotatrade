package com.testproject.domain;

import java.math.BigDecimal;

/**
 * Created by oler on 11.02.2015.
 */
public class TradeItem {

    private String name;
    private BigDecimal price;

    public TradeItem() {
    }

    public TradeItem(String name) {
        this.name = name;
    }

    public TradeItem(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "TradeItem{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
