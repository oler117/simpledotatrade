package com.testproject.utils.dotahtmlparse;

import com.testproject.domain.TradeItem;
import com.testproject.domain.TradeOffer;
import com.testproject.domain.Trader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by oler on 10.02.2015.
 */
public class Dota2LoungeParser {

    private String URL;
    private Document doc;

    public Dota2LoungeParser(String URL) {
        this.URL = URL;
        init();
    }

    private void init() {
        try {
            doc = Jsoup.connect(URL).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Trader getTrader() {

        Trader trader = new Trader();

        Elements tempElement;

        tempElement = doc.select(".profilesmallheader span b");
        trader.setSteamName(tempElement.text());

        tempElement = doc.select(".profilesmall");
        trader.setSteamURL(tempElement.attr("href"));

        tempElement = doc.select(".profilesmall img");
        trader.setAvatarURL(tempElement.attr("src"));

        return trader;
    }

    public TradeOffer getTradeOfferInfo() {

        TradeOffer tradeOffer = new TradeOffer();

        Elements tempElement;

        tempElement = doc.select("#tradediv .tradepoll .tradecnt .left .oitm");
        for (Element item : tempElement) {
            tradeOffer.getOffering().add(
                    new TradeItem(item.select(".name b").text()));
        }


        tempElement = doc.select("#tradediv .tradepoll .tradecnt .right .oitm");
        for (Element item : tempElement) {
            tradeOffer.getWants().add(
                    new TradeItem(item.select(".name b").text()));
        }

        return tradeOffer;
    }

}
