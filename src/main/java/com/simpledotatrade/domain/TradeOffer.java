package com.simpledotatrade.domain;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by oler on 11.02.2015.
 */
public class TradeOffer {

    private List<TradeItem> offering;
    private List<TradeItem> wants;

    private BigDecimal summDifference; // offering - wants

    public TradeOffer() {
        offering = new LinkedList<TradeItem>();
        wants = new LinkedList<TradeItem>();
    }

    public List<TradeItem> getOffering() {
        return offering;
    }

    public void setOffering(List<TradeItem> offering) {
        this.offering = offering;
    }

    public List<TradeItem> getWants() {
        return wants;
    }

    public void setWants(List<TradeItem> wants) {
        this.wants = wants;
    }

    public BigDecimal getSummDifference() {
        return summDifference;
    }

    public void setSummDifference(BigDecimal summDifference) {
        this.summDifference = summDifference;
    }

    @Override
    public String toString() {
        return "TradeOffer{" +
                "offering=" + offering +
                ", wants=" + wants +
                ", summDifference=" + summDifference +
                '}';
    }

}
