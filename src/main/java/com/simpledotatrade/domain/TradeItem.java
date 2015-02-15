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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TradeItem)) return false;

        TradeItem tradeItem = (TradeItem) o;

        if (lowestPrice != null ? !lowestPrice.equals(tradeItem.lowestPrice) : tradeItem.lowestPrice != null)
            return false;
        if (mediumPrice != null ? !mediumPrice.equals(tradeItem.mediumPrice) : tradeItem.mediumPrice != null)
            return false;
        if (!name.equals(tradeItem.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (lowestPrice != null ? lowestPrice.hashCode() : 0);
        result = 31 * result + (mediumPrice != null ? mediumPrice.hashCode() : 0);
        return result;
    }
}
