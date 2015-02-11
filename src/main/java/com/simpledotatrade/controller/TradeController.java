package com.simpledotatrade.controller;

import com.google.gson.Gson;
import com.simpledotatrade.analytics.AnalyticsUtils;
import com.simpledotatrade.domain.TradeOffer;
import com.simpledotatrade.domain.Trader;
import com.simpledotatrade.domain.wrappers.TradeResponseWrapper;
import com.simpledotatrade.utils.dotahtmlparse.Dota2LoungeParser;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by oles on 10.02.15.
 */
@Controller
@RequestMapping("/trade")
public class TradeController {

    @com.simpledotatrade.misc.loginject.Logger
    private Logger LOG;

    private static final String D2L_TRADE_URL = "http://dota2lounge.com/trade?t=";
    private static final String D2L_TRADE_PAGE_URL = "http://dota2lounge.com/?p=";

    final Gson gson = new Gson();

    @RequestMapping(value = "/{tradeId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String getTradeAnalysis(@PathVariable String tradeId) {
        LOG.info("Got trade ID: " + tradeId);

        Trader trader;
        TradeOffer tradeOffer;

        Dota2LoungeParser d2lParser = new Dota2LoungeParser();
        final String URL = D2L_TRADE_URL + tradeId.replaceAll("\\s", "%20");

        trader = d2lParser.parseTrader(URL, true);
        LOG.info(trader.toString());

        tradeOffer = d2lParser.parseTradeOffer(URL, true);
        tradeOffer = AnalyticsUtils.fillInTradeOfferPrices(tradeOffer);
        LOG.info(tradeOffer.toString());

        return gson.toJson(new TradeResponseWrapper(trader, tradeOffer));
    }

    @RequestMapping(value = "/profitable", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String getProfitableTradeOffers() {
        LOG.info("Start parsing D2L trade pages... ");

        Dota2LoungeParser d2lParser = new Dota2LoungeParser();

        final String pageURL = D2L_TRADE_PAGE_URL + "1";
        List<TradeOffer> tradeOfferList = d2lParser.parseTradeOfferPage(pageURL, true);
        for (TradeOffer offer : tradeOfferList) {
            LOG.debug(offer.toString());
        }

        return gson.toJson(tradeOfferList);
    }

}
