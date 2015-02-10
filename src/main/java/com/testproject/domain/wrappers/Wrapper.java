package com.testproject.domain.wrappers;

import com.testproject.domain.TradeOffer;
import com.testproject.domain.Trader;

/**
 * Created by oler on 11.02.2015.
 */
public class Wrapper {

    private Trader trader;
    private TradeOffer tradeOffer;

    public Wrapper() {
    }

    public Wrapper(Trader trader, TradeOffer tradeOffer) {
        this.trader = trader;
        this.tradeOffer = tradeOffer;
    }

    public Trader getTrader() {
        return trader;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    public TradeOffer getTradeOffer() {
        return tradeOffer;
    }

    public void setTradeOffer(TradeOffer tradeOffer) {
        this.tradeOffer = tradeOffer;
    }
}
