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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PriceOverview)) return false;

        PriceOverview that = (PriceOverview) o;

        if (success != that.success) return false;
        if (volume != that.volume) return false;
        if (lowest_price != null ? !lowest_price.equals(that.lowest_price) : that.lowest_price != null) return false;
        if (median_price != null ? !median_price.equals(that.median_price) : that.median_price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (success ? 1 : 0);
        result = 31 * result + (lowest_price != null ? lowest_price.hashCode() : 0);
        result = 31 * result + volume;
        result = 31 * result + (median_price != null ? median_price.hashCode() : 0);
        return result;
    }
}
