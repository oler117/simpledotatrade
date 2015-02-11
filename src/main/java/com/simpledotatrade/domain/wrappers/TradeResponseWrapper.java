package com.simpledotatrade.domain.wrappers;

import com.simpledotatrade.domain.TradeOffer;
import com.simpledotatrade.domain.Trader;

/**
 * Created by oler on 11.02.2015.
 */
public class TradeResponseWrapper {

    private Trader trader;
    private TradeOffer tradeOffer;

    public TradeResponseWrapper() {
    }

    public TradeResponseWrapper(Trader trader, TradeOffer tradeOffer) {
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
