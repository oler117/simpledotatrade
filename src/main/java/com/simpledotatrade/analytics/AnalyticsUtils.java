package com.simpledotatrade.analytics;

import com.google.gson.Gson;
import com.simpledotatrade.domain.PriceOverview;
import com.simpledotatrade.domain.TradeItem;
import com.simpledotatrade.domain.TradeOffer;
import com.simpledotatrade.utils.urlconn.UrlConn;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
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

        TradeOffer tradeOffer = new TradeOffer(offer.getId(),
                offer.getOffering(), offer.getWants(), offer.getSummDifference());

        boolean isTradeAcceptable;
        isTradeAcceptable = fillInTradeItemsListWithPrices(tradeOffer.getOffering());
        if (!isTradeAcceptable) {
            return null;
        }
        isTradeAcceptable = fillInTradeItemsListWithPrices(tradeOffer.getWants());
        if (!isTradeAcceptable) {
            return null;
        }


        BigDecimal summDifference;
        BigDecimal offerItemsCost = calculateTradeItemsCost(tradeOffer.getOffering());
        BigDecimal wantsItemsCost = calculateTradeItemsCost(tradeOffer.getWants());
        summDifference = offerItemsCost.subtract(wantsItemsCost);

        tradeOffer.setSummDifference(summDifference);
        LOG.info("REVENUE = " + tradeOffer.getSummDifference());

        return tradeOffer;
    }

    private static BigDecimal calculateTradeItemsCost(List<TradeItem> tradeItems) {

        BigDecimal itemsCost = BigDecimal.ZERO;
        for (TradeItem tradeItem : tradeItems) {

            /*
             * If actual lowest price was null, then it would be set to medium price.
             */
            itemsCost = itemsCost.add(tradeItem.getLowestPrice());
        }

        return itemsCost;
    }

    private static boolean fillInTradeItemsListWithPrices(List<TradeItem> tradeItems) {

        for (TradeItem tradeItem : tradeItems) {

            String name = tradeItem.getName();
            BigDecimal lowestPrice = null;
            BigDecimal mediumPrice = null;

            if (!otherItems.contains(name)) {
                //TODO: add exception handling. Exception occures when there is no such item on steam market!
                String jsonPriceOverview = null;
                try {
                    jsonPriceOverview = UrlConn.callURL(
                            STEAM_PRICEOVERVIEW_URL + name.replaceAll("\\s", "%20"));
                } catch (IOException e) {
                    LOG.error("UrlConn.callURL ERROR!");
                    e.printStackTrace();
                    return false;
                }

                jsonPriceOverview = preparePriceOverviewJson(jsonPriceOverview);

                PriceOverview po = GSON.fromJson(jsonPriceOverview, PriceOverview.class);
                LOG.info("Parsed Priceoverview[" + name + "]: " + po.toString());

                if (po.getLowest_price() == null && po.getMedian_price() == null) {
                    return false;
                }

                if (po.getLowest_price() != null) {

                    lowestPrice = BigDecimal.valueOf(
                            Double.parseDouble(po.getLowest_price().replace("$", ""))
                    );
                    if (po.getMedian_price() != null) {

                        mediumPrice = BigDecimal.valueOf(
                                Double.parseDouble(po.getMedian_price().replace("$", ""))
                        );
                    } else {
                        mediumPrice = lowestPrice;
                    }
                } else if (po.getMedian_price() != null) {

                    mediumPrice = BigDecimal.valueOf(
                            Double.parseDouble(po.getMedian_price().replace("$", ""))
                    );
                    lowestPrice = mediumPrice;
                }
            } else {
                //Here will be actions that will be done if there are
                //undefinite items like Any, Real money, Common, Legendary etc.

                //lowestPrice = mediumPrice = BigDecimal.ZERO;

                return false;
            }

            tradeItem.setLowestPrice(lowestPrice);
            tradeItem.setMediumPrice(mediumPrice);
        }

        return true;
    }

    /**
     * Is used for preparing priceoverview json, got from steam market
     * for further converting in TradeItem object.
     * 1. Replace dollar code with dollar sign
     * 2. Resolve "," issues in volume field (1,000 -> 1000)
     *
     * @param jsonPriceOverview
     * @return
     */
    private static String preparePriceOverviewJson(String jsonPriceOverview) {

        jsonPriceOverview = jsonPriceOverview.replace(DOLLAR_SIGN_CODE, "$");

        String[] temp = jsonPriceOverview.split(",");

        String newJsonPriceOverview = "";
        for (int i = 0; i < temp.length; i++) {
            newJsonPriceOverview = newJsonPriceOverview.concat(temp[i]);
            if (i == temp.length - 1) {
                break;
            } else if (i == 0) {
                newJsonPriceOverview = newJsonPriceOverview.concat(",");
            } else if (temp[i + 1].charAt(0) == '"') {
                newJsonPriceOverview = newJsonPriceOverview.concat(",");
            } else {
            }
        }

        return newJsonPriceOverview;
    }

}
