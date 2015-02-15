package com.simpledotatrade.utils.dotahtmlparse;

import com.simpledotatrade.domain.TradeItem;
import com.simpledotatrade.domain.TradeOffer;
import com.simpledotatrade.domain.Trader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by oler on 10.02.2015.
 */
public class Dota2LoungeParser {
    //TODO: replace hardcode into constants!
    private Document doc;

    public Dota2LoungeParser() {
    }

    private void init(String pageOrURL, boolean isURL) {
        try {
            if (isURL) {
                doc = Jsoup.connect(pageOrURL).get();
            } else {
                doc = Jsoup.parse(pageOrURL);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Trader parseTrader(String pageOrURL, boolean isURL) {
        init(pageOrURL, isURL);

        Trader trader = new Trader();

        Elements tempElement;

        tempElement = doc.select(".profilesmallheader span b");
        trader.setSteamName(tempElement.text().trim());

        tempElement = doc.select(".profilesmall");
        trader.setSteamURL(tempElement.attr("href").trim());

        tempElement = doc.select(".profilesmall img");
        trader.setAvatarURL(tempElement.attr("src").trim());

        return trader;
    }

    public TradeOffer parseTradeOffer(String pageOrTradeURL, boolean isURL) {
        init(pageOrTradeURL, isURL);

        Elements tradePollPart = doc.select(".tradepoll");
        TradeOffer tradeOffer = getTradeOfferFromTradepollCssClass(tradePollPart);
        return tradeOffer;
    }

    public List<TradeOffer> parseTradeOfferPage(String pageOrTradePageURL, boolean isURL) {
        init(pageOrTradePageURL, isURL);

        List<TradeOffer> tradeOffers = new LinkedList<>();

        Elements tradePollPart = doc.select(".tradepoll");
        for (Element element : tradePollPart) {
            TradeOffer tradeOffer = getTradeOfferFromTradepollCssClass(new Elements(element));
            tradeOffers.add(tradeOffer);
        }

        return tradeOffers;
    }

    private static TradeOffer getTradeOfferFromTradepollCssClass(Elements documentPart) {

        TradeOffer tradeOffer = new TradeOffer();

        Elements tempElement;

        if (tradeOffer.getId() == null) {
            tradeOffer.setId(createTradeOfferId(documentPart));
        }
        createTradeOfferNames(documentPart, ".tradecnt .left .oitm", tradeOffer.getOffering());
        createTradeOfferNames(documentPart, ".tradecnt .right .oitm", tradeOffer.getWants());

        return tradeOffer;
    }

    private static Long createTradeOfferId(Elements documentPart) {

        Long id;

        Elements tempElement = documentPart.select(".tradeheader a");
        if (!tempElement.isEmpty()) {
            id = Long.valueOf(tempElement.get(1).attr("href").split("=")[1]);
        } else {
            id = null;
        }

        return id;
    }

    private static void createTradeOfferNames(Elements documentPart, String cssQuery, List tradeOfferItems) {

        Elements tempElement;
        tempElement = documentPart.select(cssQuery);
        for (Element item : tempElement) {

            String name = item.select(".name b").text().trim();
            tradeOfferItems.add(new TradeItem(name));
        }
    }

}
