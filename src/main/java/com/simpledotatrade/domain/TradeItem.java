package com.simpledotatrade.domain;

import java.math.BigDecimal;

/**
 * Created by oler on 11.02.2015.
 */
public class TradeItem {

    private String name;
    private BigDecimal lowestPrice;
    private BigDecimal mediumPrice;

    public TradeItem() {
    }

    public TradeItem(String name) {
        this.name = name;
    }

    public TradeItem(String name, BigDecimal lowestPrice, BigDecimal mediumPrice) {
        this.name = name;
        this.lowestPrice = lowestPrice;
        this.mediumPrice = mediumPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(BigDecimal lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public BigDecimal getMediumPrice() {
        return mediumPrice;
    }

    public void setMediumPrice(BigDecimal mediumPrice) {
        this.mediumPrice = mediumPrice;
    }

    @Override
    public String toString() {
        return "TradeItem{" +
                "name='" + name + '\'' +
                ", lowestPrice=" + lowestPrice +
                ", mediumPrice=" + mediumPrice +
                '}';
    }
}
