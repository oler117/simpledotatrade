package com.simpledotatrade.controller;

import com.google.gson.Gson;
import com.simpledotatrade.domain.PriceOverview;
import com.simpledotatrade.domain.TradeItem;
import com.simpledotatrade.domain.TradeOffer;
import com.simpledotatrade.domain.Trader;
import com.simpledotatrade.domain.wrappers.Wrapper;
import com.simpledotatrade.utils.dotahtmlparse.Dota2LoungeParser;
import com.simpledotatrade.utils.urlconn.UrlConn;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * Created by oles on 10.02.15.
 */
@Controller
@RequestMapping("/trade")
public class TradeController {

    private static final String STEAM_PRICEOVERVIEW_URL = "http://steamcommunity.com/market/priceoverview/"
            + "?country=US&currency=1&appid=570&market_hash_name=";

    private static final String D2L_TRADE_URL = "http://dota2lounge.com/trade?t=";

    private static final String DOLLAR_SIGN_CODE = "&#36;";

    final Gson gson = new Gson();

    @RequestMapping(value = "/{tradeId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String getTradeAnalysis(@PathVariable String tradeId) {

        System.out.println("Given trade ID: " + tradeId);
        tradeId = tradeId.replaceAll("\\s", "%20");

        Dota2LoungeParser d2lParser = new Dota2LoungeParser(D2L_TRADE_URL + tradeId);

        Trader trader = d2lParser.getTrader();
        TradeOffer tradeOfferInfo = d2lParser.getTradeOfferInfo();

        for (TradeItem tradeItem : tradeOfferInfo.getOffering()) {

            String name = tradeItem.getName().replaceAll("\\s", "%20");
            String jsonPriceOverview = UrlConn.callURL(STEAM_PRICEOVERVIEW_URL + name);
            jsonPriceOverview = jsonPriceOverview.replace(DOLLAR_SIGN_CODE, "$");
//            System.out.println("PO = " + jsonPriceOverview);

            PriceOverview po = gson.fromJson(jsonPriceOverview, PriceOverview.class);
//            System.out.println("Parsed PO = " + po.toString());

            tradeItem.setPrice(new BigDecimal(
                    po.getMedian_price().replace("$", "")
            ));
        }

        for (TradeItem tradeItem : tradeOfferInfo.getWants()) {

            String name = tradeItem.getName().replaceAll("\\s", "%20");
            String jsonPriceOverview = UrlConn.callURL(STEAM_PRICEOVERVIEW_URL + name);
            jsonPriceOverview = jsonPriceOverview.replace(DOLLAR_SIGN_CODE, "$");
//            System.out.println("PO = " + jsonPriceOverview);

            PriceOverview po = gson.fromJson(jsonPriceOverview, PriceOverview.class);
//            System.out.println("Parsed PO = " + po.toString());

            tradeItem.setPrice(new BigDecimal(
                    po.getMedian_price().replace("$", "")
            ));
        }

        tradeOfferInfo.setSummDifference(getSummDiff(tradeOfferInfo));

        System.out.println(trader.toString());
        System.out.println(tradeOfferInfo.toString());


        return gson.toJson(
                new Wrapper(trader, tradeOfferInfo));
    }

    private BigDecimal getSummDiff(TradeOffer offer) {

        BigDecimal summDifference = null;

        BigDecimal offerItemsCost = BigDecimal.ZERO;
        for (TradeItem tradeItem : offer.getOffering()) {
            offerItemsCost = offerItemsCost.add(tradeItem.getPrice());
        }

        BigDecimal wantsItemsCost = BigDecimal.ZERO;
        for (TradeItem tradeItem : offer.getWants()) {
            wantsItemsCost = wantsItemsCost.add(tradeItem.getPrice());
        }

        summDifference = offerItemsCost.subtract(wantsItemsCost);

        return summDifference;
    }

}
