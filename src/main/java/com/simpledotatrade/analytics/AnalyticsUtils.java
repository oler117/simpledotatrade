package com.simpledotatrade.analytics;

import com.google.gson.Gson;
import com.simpledotatrade.domain.PriceOverview;
import com.simpledotatrade.domain.TradeItem;
import com.simpledotatrade.domain.TradeOffer;
import com.simpledotatrade.utils.urlconn.UrlConn;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by oler on 11.02.2015.
 */
@Component
public class AnalyticsUtils {

    @com.simpledotatrade.misc.loginject.Logger
    private static Logger LOG;

    private static final String STEAM_PRICEOVERVIEW_URL = "http://steamcommunity.com/market/priceoverview/"
            + "?country=US&currency=1&appid=570&market_hash_name=";

    private static final String DOLLAR_SIGN_CODE = "&#36;";
    private static final List<String> otherItems = new LinkedList<String>() {
        {
            add("Offers");
            add("Any Common");
            add("Any Uncommon");
            add("Any Rare");
            add("Any Mythical");
            add("Any Legendary");
            add("Any Ancient");

            add("Any Immortal");
            add("Real Money");
            add("+ More");
            add("Any Set");
        }
    };
    private final static Gson GSON = new Gson();

    public static TradeOffer fillInTradeOfferPrices(final TradeOffer offer) {

        TradeOffer tradeOffer = new TradeOffer(
                offer.getOffering(), offer.getWants(), offer.getSummDifference());

        for (TradeItem tradeItem : tradeOffer.getOffering()) {

            String name = tradeItem.getName();
            BigDecimal lowestPrice = BigDecimal.ZERO;
            BigDecimal mediumPrice = BigDecimal.ZERO;

            if (!otherItems.contains(name)) {
                //TODO: add exception handling. Exception occures when there is no such item on steam market!
                String jsonPriceOverview = UrlConn.callURL(
                        STEAM_PRICEOVERVIEW_URL + name.replaceAll("\\s", "%20"));
                jsonPriceOverview = jsonPriceOverview.replace(DOLLAR_SIGN_CODE, "$");
                //TODO: add replace ',' with '' in volume field

                LOG.info("Steam Priceoverview[" + name + "]: " + jsonPriceOverview);

                PriceOverview po = GSON.fromJson(jsonPriceOverview, PriceOverview.class);
                LOG.info("Parsed Priceoverview[" + name + "]: " + po.toString());

                lowestPrice = new BigDecimal(
                        po.getLowest_price().replace("$", "")
                );
                mediumPrice = new BigDecimal(
                        po.getMedian_price().replace("$", "")
                );
            }

            tradeItem.setLowestPrice(lowestPrice);
            tradeItem.setMediumPrice(mediumPrice);
        }

        for (TradeItem tradeItem : tradeOffer.getWants()) {

            String name = tradeItem.getName();
            BigDecimal lowestPrice = BigDecimal.ZERO;
            BigDecimal mediumPrice = BigDecimal.ZERO;

            if (!otherItems.contains(name)) {
                String jsonPriceOverview = UrlConn.callURL(
                        STEAM_PRICEOVERVIEW_URL + name.replaceAll("\\s", "%20"));
                jsonPriceOverview = jsonPriceOverview.replace(DOLLAR_SIGN_CODE, "$");
                //TODO: add replace ',' with '' in volume field

                LOG.info("Steam Priceoverview[" + name + "]: " + jsonPriceOverview);

                PriceOverview po = GSON.fromJson(jsonPriceOverview, PriceOverview.class);
                LOG.info("Parsed Priceoverview[" + name + "]: " + po.toString());

                lowestPrice = new BigDecimal(
                        po.getLowest_price().replace("$", "")
                );
                mediumPrice = new BigDecimal(
                        po.getMedian_price().replace("$", "")
                );
            }

            tradeItem.setLowestPrice(lowestPrice);
            tradeItem.setMediumPrice(mediumPrice);
        }

        BigDecimal summDifference = null;

        BigDecimal offerItemsCost = BigDecimal.ZERO;
        for (TradeItem tradeItem : tradeOffer.getOffering()) {
            offerItemsCost = offerItemsCost.add(tradeItem.getLowestPrice());
        }

        BigDecimal wantsItemsCost = BigDecimal.ZERO;
        for (TradeItem tradeItem : tradeOffer.getWants()) {
            wantsItemsCost = wantsItemsCost.add(tradeItem.getLowestPrice());
        }

        summDifference = offerItemsCost.subtract(wantsItemsCost);
        tradeOffer.setSummDifference(summDifference);

        return tradeOffer;
    }

}
