package com.simpledotatrade.controller;

import com.google.gson.Gson;
import com.simpledotatrade.analytics.AnalyticsUtils;
import com.simpledotatrade.domain.TradeOffer;
import com.simpledotatrade.domain.Trader;
import com.simpledotatrade.domain.wrappers.TradeResponseWrapper;
import com.simpledotatrade.misc.TradeSearchFilter;
import com.simpledotatrade.service.AnalyticsService;
import com.simpledotatrade.utils.dotahtmlparse.Dota2LoungeParser;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by oles on 10.02.15.
 */
@Controller
@RequestMapping("/trade")
public class TradeController {

    @com.simpledotatrade.misc.loginject.Logger
    private Logger LOG;

    @Autowired
    private AnalyticsService analyticsService;

    private static final String D2L_TRADE_URL = "http://dota2lounge.com/trade?t=";

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
        tradeOffer.setId(Long.valueOf(tradeId));

        String response;
        if (tradeOffer != null) {
            LOG.info(tradeOffer.toString());
            response = gson.toJson(new TradeResponseWrapper(trader, tradeOffer));
        } else {
            response = gson.toJson(new TradeResponseWrapper("Trade is unacceptable!"));
        }

        return response;
    }

    @RequestMapping(value = "/profitable", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String getProfitableTradeOffers() {

        Dota2LoungeParser d2lParser = new Dota2LoungeParser();

        //Add parse trader

        TradeSearchFilter tradeSearchFilter = new TradeSearchFilter();
        tradeSearchFilter.setMaxResults(2);
        tradeSearchFilter.setMinProfit(BigDecimal.valueOf(0.0));

        List<TradeOffer> tradeOfferList = analyticsService.getProfitableTradeOffers(
                tradeSearchFilter);

        List<TradeResponseWrapper> tradesList = new LinkedList<>();
        for (TradeOffer tradeOffer : tradeOfferList) {
            tradesList.add(new TradeResponseWrapper(null, tradeOffer));
        }
        String response = gson.toJson(tradesList);

        return response;
    }

}
