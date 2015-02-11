package com.simpledotatrade.domain;

/**
 * Created by oles on 11.02.15.
 */
public class PriceOverview {

    private boolean success;
    private String lowest_price;
    private int volume;
    private String median_price;

    public PriceOverview() {
    }

    public PriceOverview(boolean success, String lowest_price, int volume, String median_price) {
        this.success = success;
        this.lowest_price = lowest_price;
        this.volume = volume;
        this.median_price = median_price;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getLowest_price() {
        return lowest_price;
    }

    public void setLowest_price(String lowest_price) {
        this.lowest_price = lowest_price;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getMedian_price() {
        return median_price;
    }

    public void setMedian_price(String median_price) {
        this.median_price = median_price;
    }

    @Override
    public String toString() {
        return "PriceOverview{" +
                "success=" + success +
                ", lowest_price='" + lowest_price + '\'' +
                ", volume=" + volume +
                ", median_price='" + median_price + '\'' +
                '}';
    }
}
