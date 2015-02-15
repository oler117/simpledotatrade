package com.simpledotatrade.misc;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by oles on 13.02.15.
 */
public class TradeSearchFilter {

    private Integer maxResults = null;
    private BigDecimal minProfit = null;
    private Date latestOfferPublishDate = null;

    public TradeSearchFilter() {
    }

    public TradeSearchFilter(Integer maxResults, BigDecimal minProfit, Date latestOfferPublishDate) {
        this.maxResults = maxResults;
        this.minProfit = minProfit;
        this.latestOfferPublishDate = latestOfferPublishDate;
    }

    public Integer getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(Integer maxResults) {
        this.maxResults = maxResults;
    }

    public BigDecimal getMinProfit() {
        return minProfit;
    }

    public void setMinProfit(BigDecimal minProfit) {
        this.minProfit = minProfit;
    }

    public Date getLatestOfferPublishDate() {
        return latestOfferPublishDate;
    }

    public void setLatestOfferPublishDate(Date latestOfferPublishDate) {
        this.latestOfferPublishDate = latestOfferPublishDate;
    }
}
