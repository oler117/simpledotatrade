package com.testproject.controller;

import com.google.gson.Gson;
import com.testproject.domain.TradeOffer;
import com.testproject.domain.Trader;
import com.testproject.domain.wrappers.Wrapper;
import com.testproject.utils.dotahtmlparse.Dota2LoungeParser;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/{tradeId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String getTradeAnalysis(@PathVariable String tradeId) {
        System.out.println("Givven trade ID: " + tradeId);
        tradeId = tradeId.replaceAll("\\s", "%20");

        Dota2LoungeParser d2lParser = new Dota2LoungeParser(D2L_TRADE_URL + tradeId);

        Trader trader = d2lParser.getTrader();
        TradeOffer tradeOfferInfo = d2lParser.getTradeOfferInfo();

        System.out.println(trader.toString());
        System.out.println(tradeOfferInfo.toString());

//        String response = UrlConn.callURL("http://steamcommunity.com/market/priceoverview/?country=US&currency=1&appid=570"
//                + "&market_hash_name=" + tradeId);
//        System.out.println("\nOutput: \n" +  response);


        final Gson gson = new Gson();

        String jsonResult = gson.toJson(new Wrapper(trader, tradeOfferInfo));
        System.out.println(jsonResult);

        return jsonResult;
    }


}
