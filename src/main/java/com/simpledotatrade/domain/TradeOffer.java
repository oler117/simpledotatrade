package com.simpledotatrade.domain;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by oler on 11.02.2015.
 */
public class TradeOffer {

    private Long id;
    private List<TradeItem> offering;
    private List<TradeItem> wants;

    private Marker marker;
    private BigDecimal summDifference; // offering - wants

    public TradeOffer() {
        offering = new LinkedList<>();
        wants = new LinkedList<>();
    }

    public TradeOffer(Long id, List<TradeItem> offering, List<TradeItem> wants, BigDecimal summDifference) {
        this.id = id;
        this.offering = offering;
        this.wants = wants;
        this.summDifference = summDifference;
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

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TradeOffer)) return false;

        TradeOffer that = (TradeOffer) o;

        if (!id.equals(that.id)) return false;
        if (marker != that.marker) return false;
        if (!offering.equals(that.offering)) return false;
        if (summDifference != null ? !summDifference.equals(that.summDifference) : that.summDifference != null)
            return false;
        if (!wants.equals(that.wants)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + offering.hashCode();
        result = 31 * result + wants.hashCode();
        result = 31 * result + (marker != null ? marker.hashCode() : 0);
        result = 31 * result + (summDifference != null ? summDifference.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TradeOffer{" +
                "id=" + id +
                ", offering=" + offering +
                ", wants=" + wants +
                ", marker=" + marker +
                ", summDifference=" + summDifference +
                '}';
    }
}
